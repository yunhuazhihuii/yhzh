package com.yhzh.zhyq.socket;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yhzh.util.JsonUtil;
import com.yhzh.zhyq.dao.YhzhDao;
import com.yhzh.zhyq.websocket.WSocketClientEng;
import com.yhzh.znzm.dao.ZnzmDao;
import com.yhzh.znzm.mqtt.server.Json;

public class Nsocket {
	@Resource
	// 非单例不能用注入
	YhzhDao yhzhDao;
	@Resource
	private WSocketClientEng wSocketClientEng;
	@Resource
	private ZnzmDao znzmDao;
	public static Log LOG = LogFactory.getLog(Nsocket.class);
	private JsonUtil jsonUtil = new JsonUtil();


	public void nosocket(String strRec) {

		LOG.debug("接收到nosocket发送过来的内容:" + strRec);
		if ("{".equals(strRec.substring(0, 1))) {
			Map<String, Object> mapRes = jsonUtil.jsonStrToMap(strRec);
			// 智能照明驱动向主适配器发送数据
			if ("update".equals((String) mapRes.get("act"))) {

				// 转发数据给websocket服务器
				// 去socket,其五
				//LOG.debug("接收到内容为智能照明驱动发过来的数据" + mapRes);
				reciLightData(mapRes);
			} else if ("control".equals((String) mapRes.get("act"))) {
				LOG.debug("接收到内容为智能照明驱动发过来的控制命令回馈");
				reciLightConBak(mapRes);
			}
		}
	}
	/**写点  2018-1-27
 * @param listData
 * @return
 */
public boolean writePoint(String pointId,String value){
	boolean bRst = false;
	String strSend = "{\"act\":\"control\",\"content\":{\"type\":\"cellDimmer\",\"data\":[";
	//获取网关地址
	String mac = znzmDao.getMacBypointId(pointId);
	//区域号
	String areaCode = znzmDao.getAreaCodeBypointId(pointId);
	//区域回路号
	String cellCode = znzmDao.getCellCodeBypointId(pointId);
	strSend += "{\"mac\":\"" + mac + "\",\"areaCode\":\"" + areaCode + "\",\"cellCode\":\"" + cellCode + "\",\"value\":\"" + value + "\"}";
	strSend += "]}}";
	
	this.send(strSend);
	bRst = true;
	return bRst;
}

	/**
	 * 接收照明驱动发送过来的数据
	 * 
	 * @param mapRes
	 */
	public boolean reciLightData(Map<String, Object> mapRes) {
		boolean bRst = false;

		@SuppressWarnings("unchecked")
		Map<String, Object> mapCon = (Map<String, Object>) mapRes
				.get("content");
		@SuppressWarnings("unused")
		String mac = (String) mapCon.get("mac");
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> listData = (List<Map<String, Object>>) mapCon
				.get("data");
		// [{"name":点名1,"value":值},{"name":点名1,"value":值},...]
		String sData = "[";
		for (Map<String, Object> mapData : listData) {

			String ModuleMac = (String) mapData.get("ModuleMac");
			String cellIndex = (String) mapData.get("cellIndex");
			String value = (String) mapData.get("value");
			//System.out.println("nsocket测试用：ModuleMac：" + ModuleMac+ ",cellIndex:" + cellIndex + ",2");
			// 去socket id不管了，就先改为000001 A001.000001.1000000000000276
			String pointId = znzmDao.getPointId(ModuleMac, cellIndex, "2");// 2是输出点
			pointId = wSocketClientEng.getClientId() + "." + "000001" + "."
					+ pointId;
			sData += "{\"name\":\"" + pointId + "\",\"value\":\"" + value
					+ "\",\"ioproperty\":\"" + "DI" + "\"},";
		
			// 将数据保存到数据库
			yhzhDao.setpointValuebypointid(
					znzmDao.getPointId(ModuleMac, cellIndex, "2"), value);
		}
		sData = sData.substring(0, sData.length() - 1);
		sData += "]";
		// 将数据转发给websocketserver
		LOG.debug("---nsocket开始向websocketserver发送灯光驱动发过来的数据---");
		bRst = wSocketClientEng.sendDataToWServer(sData);
		if (bRst) {
			LOG.debug("向websocketserver发送灯光驱动发过来的数据成功！");
		} else {
			LOG.debug("向websocketserver发送灯光驱动发过来的数据失败，请检查。");
		}
		// 返回回馈信息给照明驱动
		// {"act":"update","succcess":"0","message":""}
		String str = "{\"act\":\"update\",\"succcess\":\"0\",\"message\":\"\"}";
		// ////////// this.send(str);
		try {
			Json.setvalue(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bRst = true;
		return bRst;
	}

	/**
	 * 发送控制命名给照明驱动后接收回馈
	 * 
	 * @param mapRes
	 */
	public boolean reciLightConBak(Map<String, Object> mapRes) {
		boolean bRst = false;
		/*
		 * 驱动返回: {"type":"control","succcess":"0","message":""}
		 */
		String succcess = (String) mapRes.get("succcess");
		if ("0".equals(succcess)) {
			LOG.debug("---照明驱动控制灯光成功！---");
		} else {
			LOG.info("照明驱动控制灯光失败：" + mapRes.get("message"));
		}
		return bRst;
	}
	/**发送控制命名给照明驱动
	 * @param listData
	 * @return
	 */
	public boolean sendConToLigthEng(List<Map<String,Object>> listData){
		boolean bRst = false;

		String strSend = "{\"act\":\"control\",\"content\":{\"type\":\"cellDimmer\",\"data\":[";
		for(Map<String,Object> mapData : listData){
			String pointNames = (String)mapData.get("name");
			String value = (String)mapData.get("value");
			String [] pointArr = pointNames.split("\\."); //适配器id.驱动id.点编号
		
			String pointId = pointArr[2]; //点编号
			//获取网关地址
			String mac = znzmDao.getMacBypointId(pointId);
			//区域号
			String areaCode = znzmDao.getAreaCodeBypointId(pointId);
			//区域回路号
			String cellCode = znzmDao.getCellCodeBypointId(pointId);
			strSend += "{\"mac\":\"" + mac + "\",\"areaCode\":\"" + areaCode + "\",\"cellCode\":\"" + cellCode + "\",\"value\":\"" + value + "\"}";
		}
		strSend += "]}}";
		this.send(strSend);
		bRst = true;
		return bRst;
	}
	/**发送数据给znzm
	 * @param str
	 */
	public void send(String str) {
		LOG.debug("nsocket准备发送数据到驱动：" + str);
		//去socket，其一
		try {
			Json.setvalue(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**适配器向驱动请求数据接口
	 * @param pointId 点编号
	 * @return
	 */
	public boolean loopStatusRequ(){
		// 增加初始化查询znzm点
		boolean bRst = false;
//		List<Map<String, Object>> list = znzmDao.getallzmMac();
//		for (Map<String, Object> map : list) {
//		String mac = (String) map.get("mac");
//		String DataRequest= 	"{\"act\":\"getDataRequest\",\"mac\":\""+mac+ "\"}";
//		this.send(DataRequest);	}
		
					List<Map<String, Object>> list = znzmDao.getallpointid();
					
					for (Map<String, Object> map : list) {
						String pointid = (String) map.get("pointid");
						String mac = znzmDao.getMacBypointId(pointid);
						String areaCode = znzmDao.getAreaCodeBypointId(pointid);
						String cellCode = znzmDao.getCellCodeBypointId(pointid);
				
						String str_loopStatusRequ = "{\"act\":\"loopStatusRequ\",\"content\":{\"areaCode\":\"" + areaCode + "\"," +
								"\"cellCode\":\"" + cellCode + "\",\"mac\":\"" + mac + "\"}}";
						this.send(str_loopStatusRequ);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
		
//		
//	
//		
		

		return bRst;
	}
}
