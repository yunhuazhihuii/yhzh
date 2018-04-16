package com.yhzh.bacnet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.service.unconfirmed.WhoIsRequest;
import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.yhzh.bacnet.dao.AirCondiDao;
import com.yhzh.pub.bean.PointBean;
import com.yhzh.zhyq.websocket.WSocketClientEng;

/**bacnet控制器 的管理器
 * @author samsun 2018-1-6
 *
 */
public class BacnetManager {
	
	//数据库中配置的ba 控制器集合
	private List<Map<String,Object>> baList;
	//本地网段设备集合(有多少个网段就有多少个，这里的网段默认是前3节，如：192.168.1)
	private List<LocalDevice> localDeviceList;
	//bacnet控制器集合（多个设备id就有多个）
	private List<BacnetController> baConList;
	
	private int localDeviceId = 1063; //本地设备id，这个应该是随便设都可以的吧？
	//端口
	private int port = 47808;
	
	@Resource //非单例不能用注入
	AirCondiDao airDao;
	
	static Log LOG = LogFactory.getLog(BacnetController.class); 

	@Resource
	private WSocketClientEng wSocketClientEng; //websocket客户端管理器

	public boolean isCshFinish; //是否初始化完成标志
	
	/**
	 * 初始化bacnet控制器列表
	 */
	public void initBaConList(){
		isCshFinish = false;
		baConList = new ArrayList<BacnetController>();
		baList = airDao.getBaControllerList();

		
		
		List<String> networkList = new ArrayList<String>(); //网段集合
		localDeviceList = new ArrayList<LocalDevice>();
		//获取到数据库控制器，一共三个。开始遍历控制器对应1001，1002，1003。
		for(Map<String,Object> baMap: baList){
			String controllerid = "";
			try {
				controllerid = (String)baMap.get("controllerid");
				String ontrollername = (String)baMap.get("ontrollername");
				String ip = (String)baMap.get("ip");
	
				//网段
				String networkIp = ip.substring(0, ip.lastIndexOf(".")) + ".255";
				if(!networkList.contains(networkIp)){ //该网段还未初始化过
					networkList.add(networkIp);
					LocalDevice localDevice = new LocalDevice(++localDeviceId, networkIp);
					localDevice.setPort(this.port);
					BacnetListener bacnetListener = new BacnetListener();
					bacnetListener.setBacnetManager(this);
					localDevice.getEventHandler().addListener(bacnetListener);
					localDevice.initialize();
					localDevice.sendBroadcast(new WhoIsRequest()); //发送广播
					Thread.sleep(1000); //暂时用这种方式

					
					localDeviceList.add(localDevice);
					List<RemoteDevice> remoList = localDevice.getRemoteDevices();
					if(null != remoList && remoList.size() > 0){
						for(RemoteDevice rd : remoList){
							String remoteIp = rd.getAddress().toIpString();
							String realDeviceid = rd.getInstanceNumber() + "";
							Map<String,Object> baConMap = this.getBaConMap(remoteIp, realDeviceid);
							
							if(null != baConMap){
								LOG.debug("开始初始化ip为" + remoteIp + "设备id为" + realDeviceid + "的设备");
								//该ba设备在数据库中有配置，可以初始化
								String realControllerid = (String)baConMap.get("controllerid");
								List<Map<String,Object>> pointList = airDao.getPointListByConId(realControllerid);
								List<PointBean> pointBeanList = new ArrayList<PointBean>();
								for(Map<String,Object> pointMap: pointList){
									PointBean pb = new PointBean();
									pb.setPointId((String)pointMap.get("pointid"));
									pb.setPhisId((String)pointMap.get("phisid"));
									pb.setPointName((String)pointMap.get("pointname"));
									pb.setIoProperty((String)pointMap.get("ioproperty"));
									pb.setPointType((String)pointMap.get("pointtype"));
									pb.setPointValue((String)pointMap.get("pointvalue"));
									pb.setParaType((String)pointMap.get("paratype"));
									pb.setControllerid((String)pointMap.get("controllerid"));
									pb.setInstanceNumber(Integer.parseInt((String)pointMap.get("instanceNumber")));
									pb.setIssubs((String)pointMap.get("issubs"));
									String increment = pointMap.get("increment") + "";
									if("null".equals(increment.toLowerCase())){
										increment = "";
									}
									pb.setIncrement(increment);
									pb.setObjectType();
									pointBeanList.add(pb);
								}
								
								BacnetController baCon = new BacnetController(realControllerid,(String)baConMap.get("ontrollername"),remoteIp,realDeviceid,pointBeanList,localDevice,rd);
								baConList.add(baCon);
								LOG.debug("初始化ip为" + remoteIp + "设备id为" + realDeviceid + "的设备成功！");
							}else{
								LOG.error("系统检索到ip为" + remoteIp + "、deviceid为" + rd.getInstanceNumber() + 
										"的ba控制器，但在数据库中并没有配置，请检查！");
							}
				        }
					}else{
						LOG.error("ba控制器[controllerid=" + controllerid + "]ip为" + ip + 
								",对应网段" + networkIp + "下检索不到任何设备，请检查！");
					}
				}
			} catch (IOException e) {
				LOG.error("初始化ba设备[controllerid=" + controllerid + "]IOException异常：" + e.getMessage());
				e.printStackTrace();
			} catch (BACnetException e) {
				LOG.error("初始化ba设备[controllerid=" + controllerid + "]BACnetException异常：" + e.getMessage());
				e.printStackTrace();
			} catch (InterruptedException e) {
				LOG.error("初始化ba设备[controllerid=" + controllerid + "]InterruptedException异常：" + e.getMessage());
				e.printStackTrace();
			}
		}
		//将所有输入点的值发给websocket服务器
		initSendPointValueToWebs();
		isCshFinish = true;
	}
	
	
	/**
	 * 初始化时将所有输入点的值发给websocket服务器
	 */
	private void initSendPointValueToWebs(){
		System.out.print("初始化时将所有输入点的值发给websocket服务器");
		List<PointBean> allPointList = new ArrayList<PointBean>();
		for(BacnetController bCon : baConList){
			allPointList.addAll(bCon.getPointList());
		}
		if(null != allPointList && allPointList.size() > 0){
			LOG.debug("---初始化：开始向websocketserver发送所有bacnet控制器所有点的值---");
			wSocketClientEng.sendDataToWServer(allPointList);
			LOG.debug("---初始化：向websocketserver发送所有bacnet控制器所有点的值完成---");
		}
	}
	
	/**根据ip与远程设备id返回ba控制器
	 * @param ip
	 * @param deviceId
	 * @return
	 */
	private BacnetController getBaController(String ip,int deviceId){
		BacnetController baCon = null;
		for(BacnetController baCon1: baConList){
			if(ip.equals(baCon1.getIp()) && deviceId == baCon1.getDeviceid()){
				baCon = baCon1;
				break;
			}
		}
		return baCon;
	}
	
	/**根据控制器id获取控制器
	 * @param controllerId
	 * @return
	 */
	public BacnetController getBaControllerById(String controllerId){
		BacnetController baCon = null;
		for(BacnetController baCon1: baConList){
			if(controllerId.equals(baCon1.getControllerid())){
				baCon = baCon1;
				break;
			}
		}
		return baCon;
	}
	
	/**根据ip与远程设备id返回ba控制器在数据库中的map
	 * @param ip
	 * @param deviceId
	 * @return
	 */
	private Map<String,Object> getBaConMap(String ip,String deviceId){
		Map<String,Object> baConMap = null;
		for(Map<String,Object> map : baList){
			if(ip.equals((String)map.get("ip")) && deviceId.equals((String)map.get("deviceid"))){
				baConMap = map;
				break;
			}
		}
		return baConMap;
	}
	
	/**接收点监控数据，更新点对象值并发送给websocketserver
	 * @param ip
	 * @param deviceId
	 * @param objectType
	 * @param instanceNumber
	 * @param encodable 值
	 * @throws InterruptedException 
	 */
	public void listenPointChg(String ip,int deviceId,ObjectType objectType,int instanceNumber,Encodable encodable) throws InterruptedException{
		java.text.DecimalFormat df=new java.text.DecimalFormat(".00");
		if(this.isCshFinish){ //初始化完成后才需要转发监控
			BacnetController baCon = this.getBaController(ip, deviceId);
			if(null != baCon){
				PointBean point = baCon.getPoint(objectType, instanceNumber);
				if(null != point){
					
					Double oldvalue=Double.valueOf(point.getPointValue()); 
					Double value=Double.valueOf(encodable.toString()); 
				
					//if(oldvalue-value>0.01||oldvalue-value<-0.01){
						if(oldvalue-value>0.3||oldvalue-value<-0.3){
						if(point.getIncrement().equals("0.01")){
							point.setPointValue(df.format(value).toString());
						}else{
							point.setPointValue(encodable.toString());
						}
						List<PointBean> pointList = new ArrayList<PointBean>();
						pointList.add(point);
						//LOG.debug("---开始向websocketserver发送bacnet点[pointid=" + point.getPointId() + "]监听数据---");
						wSocketClientEng.sendDataToWServer(pointList);
						LOG.debug("---向websocketserver发送bacnet点[pointid=" + point.getPointId() +"PointValue="+point.getPointValue()+ "]监听数据完成---");
					}
					
					
					/*
					Double oldvalue=Double.valueOf(point.getPointValue()); 
					Double value=Double.valueOf(encodable.toString()); 
					Double Increment=Double.valueOf(point.getIncrement()); 
					if(null !=Increment&& (oldvalue-value>Increment||oldvalue-value<-Increment)){
						point.setPointValue(df.format(value).toString());
						List<PointBean> pointList = new ArrayList<PointBean>();
						pointList.add(point);
						LOG.debug("---开始向websocketserver发送bacnet点[pointid=" + point.getPointId() + "]监听数据---");
						wSocketClientEng.sendDataToWServer(pointList);
						LOG.debug("---向websocketserver发送bacnet点[pointid=" + point.getPointId() + "]监听数据完成---");
					}else{	
						if(null!=oldvalue&&(oldvalue-value>0.1||oldvalue-value<-0.1)){
							point.setPointValue(encodable.toString());
						    List<PointBean> pointList = new ArrayList<PointBean>();
						    pointList.add(point);
				    		LOG.debug("---开始向websocketserver发送bacnet点[pointid=" + point.getPointId() + "]监听数据---");
					     	wSocketClientEng.sendDataToWServer(pointList);
					   	    LOG.debug("---向websocketserver发送bacnet点[pointid=" + point.getPointId() + "]监听数据完成---");
							
							
						}else{
							point.setPointValue(encodable.toString());
						    List<PointBean> pointList = new ArrayList<PointBean>();
						    pointList.add(point);
				    		LOG.debug("---开始向websocketserver发送bacnet点[pointid=" + point.getPointId() + "]监听数据---");
					     	wSocketClientEng.sendDataToWServer(pointList);
					   	    LOG.debug("---向websocketserver发送bacnet点[pointid=" + point.getPointId() + "]监听数据完成---");
							
						}
					
					}
				
					System.out.println("oldvalue:"+oldvalue+"value:"+df.format(value)+"ed:"+(oldvalue-value));
					*/
				}else{
					LOG.error("ba控制器[Controllerid=" + baCon.getControllerid() +  
							"]监听到点[ " + objectType + " " + instanceNumber + " ]数据变化，但该监听器在系统中不存在这样的点，请检查！");
				}
			}else{
				LOG.error("set :监控接收到ip为:" + ip +"、设备id为：" + deviceId + "的控制器的点数据，但该控制器并没有在系统中存在，请检查！");
			}
		}
	}
	
	/**写点
	 * @param controllerId
	 * @param pointId
	 * @param value
	 * @return
	 */
	public boolean writePoint(String controllerId,String pointId,String value){
		boolean bRst = false;
		BacnetController baCon = this.getBaControllerById(controllerId);
		if(null != baCon){
			try {
				baCon.writePointById(pointId,value);
			} catch (Exception e) {
				LOG.error("写点[pointId=" + pointId + "]异常：" + e.getMessage());
				e.printStackTrace();
			}
		}else{
			LOG.error("写点[pointId=" + pointId + "]失败，系统找不到这样的ba控制器controllerId=" + controllerId);
		}
		bRst = true;
		return bRst;
	}
}
