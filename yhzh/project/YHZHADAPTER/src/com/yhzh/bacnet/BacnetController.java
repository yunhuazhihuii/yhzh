package com.yhzh.bacnet;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.util.logging.resources.logging;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.service.acknowledgement.AcknowledgementService;
import com.serotonin.bacnet4j.service.acknowledgement.ReadPropertyAck;
import com.serotonin.bacnet4j.service.confirmed.ConfirmedRequestService;
import com.serotonin.bacnet4j.service.confirmed.ReadPropertyRequest;
import com.serotonin.bacnet4j.service.confirmed.SubscribeCOVPropertyRequest;
import com.serotonin.bacnet4j.service.confirmed.SubscribeCOVRequest;
import com.serotonin.bacnet4j.service.confirmed.WritePropertyRequest;
import com.serotonin.bacnet4j.type.constructed.PropertyReference;
import com.serotonin.bacnet4j.type.enumerated.BinaryPV;
import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.Boolean;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.Real;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.yhzh.pub.bean.PointBean;

/**Bacnet控制器
 * @author samsun 2018-1-6
 *
 */
public class BacnetController {
	

	public static Log LOG = LogFactory.getLog(BacnetController.class); 
	
	//点的集合
	private List<PointBean> pointList = null;
	//本地设备
	private LocalDevice localDevice = null;
	//远程设备
	private RemoteDevice remoteDevice = null;
	//控制器编号
	private String controllerid;
	//控制器名称
	private String ontrollername;
	//IP地址
	private String ip;
	//设备物理编号
	private int deviceid;
	
	
	/**构造方法
	 * @param controllerid
	 * @param ontrollername
	 * @param ip
	 * @param deviceid
	 * @param pointList
	 */
	public BacnetController(String controllerid,String ontrollername,String ip,String sDeviceid,List<PointBean> pointList,LocalDevice localDevice,RemoteDevice remoteDevice){
		this.controllerid = controllerid;
		this.ontrollername = ontrollername;
		this.ip = ip;
		this.deviceid = Integer.parseInt(sDeviceid);
		this.pointList = pointList;
		this.localDevice= localDevice;
		this.remoteDevice= remoteDevice;
		
		try {
			//订阅输入点
			this.setSubscribe();
			//初始化读所有输入点的值并更新 
			this.initAllPointValue();
		} catch (BACnetException e) {
			LOG.error("初始化Bacnet控制器错误BACnetException，controllerid：" + controllerid + ";ip:" + ip +"；异常："+e.getMessage());
			e.printStackTrace();
		}
		
		/*this.localDevice = new LocalDevice(this.deviceid, "255.255.255.255");
		try {
			this.localDevice.initialize();
			this.localDevice.getEventHandler().addListener(new BacnetListener());
			this.localDevice.sendBroadcast(this.port, this.localDevice.getIAm());
			String [] ips = ip.split(".");
			//直接用ip实例化RemoteDevice的方法
			this.remoteDevice = localDevice
			        .findRemoteDevice(new Address(new byte[] { (byte) Integer.parseInt(ips[0]), 
			        				(byte) Integer.parseInt(ips[1]), (byte) Integer.parseInt(ips[2]), 
			        				(byte) Integer.parseInt(ips[3]) }, this.port), null, this.deviceid);
			//订阅输入点
			this.setSubscribe();
			//初始化读所有输入点的值并更新 
			this.initAllPointValue();
		} catch (IOException e) {
			LOG.error("初始化Bacnet控制器错误IOException，controllerid：" + controllerid + ";ip:" + ip + "；异常："+e.getMessage());
			e.printStackTrace();
			this.release();
		} catch (BACnetException e) {
			LOG.error("初始化Bacnet控制器错误BACnetException，controllerid：" + controllerid + ";ip:" + ip +"；异常："+e.getMessage());
			e.printStackTrace();
			this.release();
		} catch (PropertyValueException e) {
			LOG.error("初始化Bacnet控制器错误PropertyValueException，controllerid：" + controllerid + ";ip:" + ip +"；异常："+e.getMessage());
			e.printStackTrace();
			this.release();
		}*/
	}
	
	/**
	 * 设置订阅
	 */
	public void setSubscribe(){
		for(PointBean point: pointList){
			ObjectIdentifier oid = null;
			if("1".equals(point.getIoProperty())){
				oid = new ObjectIdentifier(ObjectType.binaryInput, point.getInstanceNumber());
			}else if("4".equals(point.getIoProperty())){
				oid = new ObjectIdentifier(ObjectType.analogInput, point.getInstanceNumber());
			}else if("3".equals(point.getIoProperty())){
				oid = new ObjectIdentifier(ObjectType.analogValue, point.getInstanceNumber());
			}else if("2".equals(point.getIoProperty())){
				oid = new ObjectIdentifier(ObjectType.binaryOutput, point.getInstanceNumber());
			}else if("5".equals(point.getIoProperty())){
				oid = new ObjectIdentifier(ObjectType.analogOutput, point.getInstanceNumber());
			}else if("6".equals(point.getIoProperty())){
				oid = new ObjectIdentifier(ObjectType.binaryValue, point.getInstanceNumber());
			}
			if(null != oid){
				try {
					String ip = remoteDevice.getAddress().toIpString();
					String deviceId = remoteDevice.getInstanceNumber() + "";
					if("1".equals(point.getIssubs())){ //订阅
						if(null != point.getIncrement() && !"".equals(point.getIncrement())){//订阅变化范围(对模拟量点) 不为空
							Real covIncrement = new Real(Float.parseFloat(point.getIncrement()));
							PropertyReference pr = new PropertyReference(PropertyIdentifier.presentValue);//只监听presentValue这一个属性
							SubscribeCOVPropertyRequest preq = new SubscribeCOVPropertyRequest(new UnsignedInteger(0), oid, 
									new Boolean(true), new UnsignedInteger(0), pr, covIncrement);
							localDevice.send(remoteDevice, preq);
							LOG.debug("订阅ip:"  + ip + "、deviceId: " + deviceId + "、" + point.getObjectType() + "、点实例号" + point.getInstanceNumber() + "、模拟变化量范围为" + point.getIncrement() + "成功........................");
						}else{
							//Subscribe订阅
							SubscribeCOVRequest req = new SubscribeCOVRequest(new UnsignedInteger(0), oid, new Boolean(true), 
					                new UnsignedInteger(0));
							localDevice.send(remoteDevice, req);
							LOG.debug("订阅ip:"  + ip + "、deviceId: " + deviceId + "、" + point.getObjectType() + "、点实例号" + point.getInstanceNumber() + "成功........................");
						}
					}else{
						//不订阅，则取消订阅
						localDevice.send(remoteDevice, new SubscribeCOVRequest(new UnsignedInteger(0), oid, null, null));
						//对于范围变化的取消订阅上面不知是否生效，后期看
						LOG.debug("取消订阅ip:"  + ip + "、deviceId: " + deviceId + "、" + point.getObjectType() + "、点实例号" + point.getInstanceNumber() + "成功........................");
					}
				} catch (BACnetException e) {
					LOG.error("订阅或取消Bacnet点失败，controllerid：" + controllerid + ";ip:" + ip +
							";PointId：" + point.getPointId() + ";InstanceNumber：" + point.getInstanceNumber()+"；BACnetException异常："+e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 初始化时更新所有要读的点的点值
	 * @throws BACnetException 
	 */
	public void initAllPointValue() throws BACnetException{
		for(PointBean point: pointList){
//			String ioProperty = point.getIoProperty();
			String isSubs = point.getIssubs();
			if("1".equals(isSubs)){ //一般要订阅的点才需要读
				ReadPropertyAck ack = readPoint(point);
				String value = ack.getValue().toString(); //现在默认都是一个字符串
				point.setPointValue(value);
			}
		}
		
	}
	
	/**
	 * 写点
	 */
	public AcknowledgementService writePointById(String pointId,String value) throws Exception{
		AcknowledgementService as = null;
		PointBean point = getPointById(pointId);
		if(null == point){
			LOG.error("点编号：" + pointId + "在控制器[controllerid=" + controllerid + "]中不存在，写入失败！");
			throw new Exception("点编号：" + pointId + "在控制器[controllerid=" + controllerid + "]中不存在，写入失败！");
		}
		ConfirmedRequestService cs = null;
		if("2".equals(point.getIoProperty())){ //数字输出点
			ObjectIdentifier oi = new ObjectIdentifier(ObjectType.binaryOutput, point.getInstanceNumber());
			cs = new WritePropertyRequest(oi, PropertyIdentifier.presentValue, null, 
					new BinaryPV(Integer.parseInt(value)), new UnsignedInteger(8));// new UnsignedInteger(8),优先权不写也没关系吧
		}else if("3".equals(point.getIoProperty())){ //通用模拟量
			ObjectIdentifier oi = new ObjectIdentifier(ObjectType.analogValue, point.getInstanceNumber());
			cs = new WritePropertyRequest(oi, PropertyIdentifier.presentValue, null, new Real(
	                Float.parseFloat(value)), new UnsignedInteger(8));
		}else if("5".equals(point.getIoProperty())){ //模拟量输出
			ObjectIdentifier oi = new ObjectIdentifier(ObjectType.analogOutput, point.getInstanceNumber());
			cs = new WritePropertyRequest(oi, PropertyIdentifier.presentValue, null, new Real(
	                Float.parseFloat(value)), new UnsignedInteger(8));
		}else if("1".equals(point.getIoProperty())){//数据量输入
			ObjectIdentifier oi = new ObjectIdentifier(ObjectType.binaryInput, point.getInstanceNumber());
			cs = new WritePropertyRequest(oi, PropertyIdentifier.presentValue, null, 
					new BinaryPV(Integer.parseInt(value)), null);
		}else if("4".equals(point.getIoProperty())){ //模拟量输入
			ObjectIdentifier oi = new ObjectIdentifier(ObjectType.analogInput, point.getInstanceNumber());
			cs = new WritePropertyRequest(oi, PropertyIdentifier.presentValue, null, new Real(
	                Float.parseFloat(value)), null);
		}else if("6".equals(point.getIoProperty())){ //通用数据量
			ObjectIdentifier oi = new ObjectIdentifier(ObjectType.binaryValue, point.getInstanceNumber());
			cs = new WritePropertyRequest(oi, PropertyIdentifier.presentValue, null, 
					new BinaryPV(Integer.parseInt(value)), new UnsignedInteger(8));
		}
		if(null == cs){
//			LOG.error("点编号：" + pointId + "在控制器[controllerid=" + controllerid + "]中不是输出点，写入失败！");
//			throw new Exception("点编号：" + pointId + "在控制器[controllerid=" + controllerid + "]中不是输出点，写入失败！");
		}
		as = localDevice.send(remoteDevice, cs);
		LOG.info("点编号：" + pointId + "写入数据value=" + value + "成功。");
		return as;
	}
	
	/**根据点编号读取点值(暂时没有使用该方法)
	 * @param pointId
	 * @return
	 */
	public String readPointById(String pointId){
		String value = null;
		
		return value;
	}
	
	/**根据点对象去读点值
	 * @param point
	 * @return
	 * @throws BACnetException 
	 */
	public ReadPropertyAck readPoint(PointBean point) throws BACnetException{
		ReadPropertyAck ack = null;
		ObjectType ot = null;
		if("1".equals(point.getIoProperty())){ //输入数据点
			ot = ObjectType.binaryInput; //建议后期将这个改为对象的一个属性
		}else if("3".equals(point.getIoProperty())){ //通用模拟量点
			ot = ObjectType.analogValue; 
		}else if("4".equals(point.getIoProperty())){ //输入模拟量点
			ot = ObjectType.analogInput; 
		}else if("2".equals(point.getIoProperty())){
			ot = ObjectType.binaryOutput;
		}else if("5".equals(point.getIoProperty())){
			ot = ObjectType.analogOutput;
		}else if("6".equals(point.getIoProperty())){
			ot = ObjectType.binaryValue;
		}
		if(ot != null){
			try {
				ReadPropertyRequest req = new ReadPropertyRequest(new ObjectIdentifier(ot,point.getInstanceNumber()),
				          PropertyIdentifier.presentValue); 
				ack = (ReadPropertyAck) localDevice.send(remoteDevice, req);
			} catch (BACnetException e) {
				LOG.error("点编号：" + point.getPointId() + "读取异常：" + e.getMessage());
				e.printStackTrace();
				throw e;
			}
		}else{
//			LOG.error("点编号：" + point.getPointId() + "在控制器[controllerid=" + controllerid + "]中不是输入点，读取失败！");
		}
		return ack;
	}

	public String getControllerid() {
		return controllerid;
	}

	public void setControllerid(String controllerid) {
		this.controllerid = controllerid;
	}
	
	/**根据点编号获取点对象
	 * @param pointId
	 * @return
	 */
	public PointBean getPointById(String pointId){
		PointBean point = null;
		for(PointBean point1: pointList){
			if(pointId.equals(point1.getPointId())){
				point = point1;
				break;
			}
		}
		return point;
	}
	
	/**根据点对象类型与实例号获取点对象
	 * @param objectType
	 * @param instanceNumber
	 * @return
	 */
	public PointBean getPoint(ObjectType objectType,int instanceNumber){
		PointBean point = null;
		for(PointBean point1: pointList){
			if(objectType.equals(point1.getObjectType()) && instanceNumber == point1.getInstanceNumber()){
				point = point1;
				break;
			}
		}
		return point;
	}
	
	/**
	 * 释放资源
	 */
	public void release(){
		if(null != this.localDevice){
			// Unsubscribe 取消订阅,待补充代码
			
			localDevice.terminate();
			LOG.info("释放ip为[" + this.ip + "]的控制器[" + this.ontrollername +  "]成功。");
		}
	}

	public List<PointBean> getPointList() {
		return pointList;
	}

	public void setPointList(List<PointBean> pointList) {
		this.pointList = pointList;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}
	
	
	
}
