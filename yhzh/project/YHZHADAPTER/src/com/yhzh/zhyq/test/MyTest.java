package com.yhzh.zhyq.test;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yhzh.util.JsonUtil;

public class MyTest {

	public static Log LOG = LogFactory.getLog(MyTest.class); 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String str = "{'type':'register','content':{'clientId':'000001','name':'智能照明'}}";
		JsonUtil jsonUtil = new JsonUtil();
		Map<String,Object> map = jsonUtil.jsonStrToMap(str);
		Map<String,Object> map1 = (Map<String,Object>)map.get("content");
		System.out.println(map.get("type"));
		System.out.println(map1.get("clientId"));
		System.out.println(map1.get("name"));*/
		
		/*String message = "42[A000000001,{callback:45,data:true,message:OK}]";
		String strCon = message.substring(message.indexOf(",") + 1, message.lastIndexOf("]"));
		System.out.println(strCon);
		JsonUtil jsonUtil = new JsonUtil();
		Map<String,Object> map = jsonUtil.jsonStrToMap(strCon);*/
		/*PropertyConfigurator.configure("E:\\spaceall\\space2\\YHZHADAPTER\\WebContent\\WEB-INF\\log4j.properties");
		LOG.debug("我是debug");
		LOG.info("我是info");
		LOG.error("我是error");*/
		
		String str = "{\"type\":\"rtsub\", \"content\":{" +
				"\"subscribeSubitem\":[\"adapter1.controller1\",\"adapter1.controller2\",\"adapter2.controller1\",\"adapter2.controller2\"]}," + 
				"\"callback\": 21}";
		JsonUtil jsonUtil = new JsonUtil();
		Map<String,Object> map = jsonUtil.jsonStrToMap(str);
//		System.out.println(map.get("content"));
		Map<String,Object> mapCon = (Map<String,Object>)map.get("content");
		List<String> list = (List<String>)mapCon.get("subscribeSubitem");
//		System.out.println(list);
		
		/*String str1 = "{\"type\":\"update\",\"content\":{\"type\":\"cellDimmer\",\"mac\":\"000001\",\"data\":[" +
		"{\"ModuleMac\":\"1\",\"cellIndex\":\"1\",\"value\":\"1\"}," +
		"{\"ModuleMac\":\"1\",\"cellIndex\":\"2\",\"value\":\"0\"}," +
		"{\"ModuleMac\":\"2\",\"cellIndex\":\"1\",\"value\":\"1\"}," +
		"{\"ModuleMac\":\"5\",\"cellIndex\":\"5\",\"value\":\"1\"}" +
		"]}}";
		Map<String,Object> map1 = jsonUtil.jsonStrToMap(str1);
		Map<String,Object> mapCon1 = (Map<String,Object>)map1.get("content");
		List<Map<String, Object>> listData = (List<Map<String, Object>>)mapCon1.get("data");
		Map<String, Object> mapData2 = listData.get(1);
		System.out.println(mapData2);*/
		
		String str2 = "{\"type\":\"register\",content:{\"clientId\":\"000001\",\"name\":\"智能照明\"}}";
		String str3 = "{\"type\":\"register\",\"content\":{\"clientId\":\"000001\",\"name\":\"znzm\"}}";
		Map<String,Object> mapRes = jsonUtil.jsonStrToMap(str3);
		System.out.println(mapRes);
		
//		Map<String,Object> map1 = (Map<String,Object>)map.get("content");
//		System.out.println(map.get("type"));
//		System.out.println(map1.get("clientId"));
//		System.out.println(map1.get("name"));
		/*String str2 = "{" +
				"\"subscribeSubitem\":[\"adapter1.controller1\",\"adapter1.controller2\",\"adapter2.controller1\",\"adapter2.controller2\"]}";
		String str3 = "[{'aa':'1','bb':'2','cc':'3'},{'aa':'11','bb':'12','cc':'13'}," +
				"{'aa':'1','bb':'2','cc':'3'}]";
		JSONObject json = JSONObject.fromObject(str2);
//		System.out.println(json.get("subscribeSubitem"));
		JSONArray ja = (JSONArray)json.get("content");
		System.out.println(ja.get(0));
		if(ja.get(0) instanceof JSONObject){
			System.out.println("是JSONObject");
		}
		if(ja.get(0) instanceof String){
			System.out.println("是String");
		}*/
	}

}
