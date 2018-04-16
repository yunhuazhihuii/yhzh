package com.yhzh.znzm.mqtt.server;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.yhzh.znzm.mqtt.client.CheckSome;
import com.yhzh.znzm.mqtt.client.LogObb;
import com.yhzh.znzm.socketdc.SocketClientdc;
import com.yhzh.znzm.socketdc.Test;

public class Json {
	static Server server;
	static LogObb logbb;

	public static void main(String args[]) {

	}

	public static void setvalue(String ser) throws Exception {
	//	Log.controllog(ser);

		JSONObject obj = new JSONObject(ser);
		String act = (String) obj.get("act");
		// 区分控制数据

		switch (act) {
		case "control":
			JSONObject object = (JSONObject) obj.get("content");
			String type = (String) object.get("type");

			// 待会统一修改

			if (type.equals("cellDimmer")) {
				// 调用回路
				cellDimmer(object);
				// 返回success;
				String back = "{'act':' control ','succcess':'0', 'message':''}";

				boolean bRst = SocketClientdc.sentMsgToSserver(back);

				break;
			}
			if (type == "groupDimmer") {
				// 调用组
				// 返回success;
				break;
			}
			if (type == "allmode") {
				mode(object);
				String back = "{'act':' control ','succcess':'0', 'message':''}";

				boolean bRst = SocketClientdc.sentMsgToSserver(back);
				// 返回success;
				break;
			}
			break;
		case "loopStatusRequ":
			JSONObject nv = (JSONObject) obj.get("content");
			loopStatusRequ(nv);
			break;
		case "register":
			String register = (String) obj.get("succcess");

			// 写死的数据

			/*String back = "{\"act\":\"update\",\"content\":{\"type\":\"cellDimmer\",\"mac\":\"0011c1c8016b\",\"data\":["
					+ "{\"ModuleMac\":\"200f82a01436\",\"cellIndex\":\"1\",\"value\":\"1\"},"
					+ "{\"ModuleMac\":\"200f82a01436\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f82a01436\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f82a01436\",\"cellIndex\":\"0\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"100f6298103e\",\"cellIndex\":\"1\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"100f6298103e\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"100f6298103e\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"100f6298103e\",\"cellIndex\":\"0\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f829813fe\",\"cellIndex\":\"1\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f829813fe\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f829813fe\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f829813fe\",\"cellIndex\":\"0\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062c00278\",\"cellIndex\":\"1\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062c00278\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062c00278\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062c00278\",\"cellIndex\":\"0\",\"value\":\"0\"}]}}";
			String back1 = "{\"act\":\"update\",\"content\":{\"type\":\"cellDimmer\",\"mac\":\"0011c1d00171\",\"data\":["
					+ "{\"ModuleMac\":\"701062c00271\",\"cellIndex\":\"1\",\"value\":\"1\"},"
					+ "{\"ModuleMac\":\"701062c00271\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062c00271\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062c00271\",\"cellIndex\":\"0\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701072280373\",\"cellIndex\":\"1\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701072280373\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701072280373\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701072280373\",\"cellIndex\":\"0\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f82a0145f\",\"cellIndex\":\"1\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f82a0145f\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f82a0145f\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f82a0145f\",\"cellIndex\":\"0\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701072280387\",\"cellIndex\":\"1\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701072280387\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701072280387\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701072280387\",\"cellIndex\":\"0\",\"value\":\"0\"}]}}";
			String back2 = "{\"act\":\"update\",\"content\":{\"type\":\"cellDimmer\",\"mac\":\"4011c1d0021a\",\"data\":["
					+ "{\"ModuleMac\":\"200f82a01440\",\"cellIndex\":\"1\",\"value\":\"1\"},"
					+ "{\"ModuleMac\":\"200f82a01440\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f82a01440\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"200f82a01440\",\"cellIndex\":\"0\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062c00244\",\"cellIndex\":\"1\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062c00244\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062c00244\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062c00244\",\"cellIndex\":\"0\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062e002f0\",\"cellIndex\":\"1\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062e002f0\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062e002f0\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701062e002f0\",\"cellIndex\":\"0\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701072280383\",\"cellIndex\":\"1\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701072280383\",\"cellIndex\":\"2\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"111111111111\",\"cellIndex\":\"0\",\"value\":\"3\"},"
					+ "{\"ModuleMac\":\"701072280383\",\"cellIndex\":\"3\",\"value\":\"0\"},"
					+ "{\"ModuleMac\":\"701072280383\",\"cellIndex\":\"0\",\"value\":\"0\"}]}}";
		String back3 = "{\"act\":\"update\",\"content\":{\"type\":\"cellDimmer\",\"mac\":\"4011c1d0021a\",\"data\":["
					+ "{\"ModuleMac\":\"111111111111\",\"cellIndex\":\"0\",\"value\":\"3\"}]}}";
			boolean bRst3 = SocketClientdc.sentMsgToSserver(back3);*/
			/*boolean bRst = SocketClientdc.sentMsgToSserver(back);
			boolean bRst1 = SocketClientdc.sentMsgToSserver(back1);
			boolean bRst2 = SocketClientdc.sentMsgToSserver(back2);*/
			

			break;

		case "update ":
			String update = (String) obj.get("succcess");
			// 如果不是0，则报错
			break;
		default:
			break;
		}

	}

	// 回路，区域回路，命令
	public static void cellDimmer(JSONObject objectin) throws Exception {

		JSONArray object2 = (JSONArray) objectin.get("data");
		// 轮询将数据封装
		for (int i = 0; i < object2.length(); i++) {
			JSONObject object3 = (JSONObject) object2.get(i);
			String mac1 = (String) object3.get("mac");
			String value = (String) object3.get("value");
			String areaCode = (String) object3.get("areaCode");

			String cc = null;
			switch (value) {
			case "0":
				cc = "close";
				break;
			case "1":
				cc = "open";
				break;
			case "2":
				cc = "switch";
				break;
			case "3":
				 mode(objectin);
				break;
			case "4":
				 mode(objectin);
				break;
			default:
				break;
			}
			String str2 = "{\"act\":\"control\",\"data\":{\"type\":\"cellDimmer\","
					+ "\"areaCode\":"
					+ areaCode
					+ ",\"cellCode\":"
					+ object3.get("cellCode") + ",\"object\":\"" + cc + "\"}}";
			
			logbb.controllog("封装回路控制数据，并发送到zmzn网关：" + mac1+"数据为："+str2);
			//System.out.println("封装回路控制数据，并发送到zmzn网关：" + mac1+"数据为："+str2);

			CheckSome.senttomac(mac1, str2);

		}

	}

	// 组，组名和命令
	public static void groupDimmer(String mac, String object, int groupCode)
			throws MqttException {

		String str = "{\"act\":\"control\",\"data\":{\"type\":\"groupDimmer\",\"groupCode\":"
				+ groupCode + ",\"object\":\"" + object + "\"}}";
		CheckSome.senttomac(mac, str);
		
	}

	// 模式控制，接收网关地址，区域号，模式号。定义3为模式一，表示全关；定义4为模式2，表示全开；
	public static void mode(JSONObject objectin) throws MqttException {

	JSONArray object2 = (JSONArray) objectin.get("data");
		// 轮询将数据封装
		for (int i = 0; i < object2.length(); i++) {
			JSONObject object3 = (JSONObject) object2.get(i);
			String mac1 = (String) object3.get("mac");
			String value = (String) object3.get("value");
			String areaCode = (String) object3.get("areaCode");
			String modeCode=null;
switch (value) {
case "3":
	modeCode="2";
	break;
case "4":
	modeCode="1";
	break;

default:
	break;
}
			
			 String str =
			 "{\"act\":\"control\",\"data\":{\"type\":\"mode\",\"areaCode\":"
			 + areaCode + ",\"modeCode\":" + modeCode + "}}";
			

			System.out.println("封装模式控制数据：" + str);

			CheckSome.senttomac(mac1, str);

		}

	
	}

	// 指定回路,主要是mac地址加回路编号 @不常用
	public static void celltype(String value) {
		String str1 = "{\"act\":\"loopIdentify\",\"moduleMac\":\"1070125945\",\"cellIndex\":1,\"cellType\":\"dimmer\",\"actType\":\"open\"}";

		JSONObject obj = new JSONObject(str1);
		// 写数据进入
		obj.put("actType", value);// 点值
	}

	// 查询回路信息
	public static void loopStatusRequ(JSONObject objectin) throws MqttException {
		String areaCode=objectin.getString("areaCode");
		String cellCode=objectin.getString("cellCode");
		String mac=objectin.getString("mac");
		
		String str = "{\"act\":\"loopStatusRequ\",\"areaCode\":"+areaCode+ ",\"cellCode\":"+cellCode+ ",\"cellType\":\"dimmer\"}";
		System.out.print("封装查询数据：" + str);
		CheckSome.senttomac(mac, str);

	}

	// 重启网关
	public static void softRestart() throws MqttException {
		String str1 = "{\"act\":\"softRestart\"}";
		System.out.println("网关软重启成功");
		Server.open(str1);
		Server.open2(str1);
		Server.open3(str1);
		Server.open4(str1);
		Server.open5(str1);
		Server.open6(str1);
		/*
		 * Server.open1(str1); Server.open3(str1);
		 */

	}

	// 获取网关数据（暂时搁置）
	public static void getDataRequest() throws MqttException {
		String str1 = "{\"act\":\"getDataRequest\"}";

		Server.open(str1);
		Server.open2(str1);
		Server.open3(str1);
		Server.open4(str1);
		Server.open5(str1);
		Server.open6(str1);
	}

}