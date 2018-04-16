package com.yhzh.znzm.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;


public class init {
public static void main(String[] args) {
String ser="	{  \"form\": {\"scheduleid\":\"22222222\", \"DateValue\": [\"星期四\", \"星期五\"],\"sureTime\": [{\"start\": \"上午8:40:00\",\"end\": \" 上午9:40:00\"}, {\"start\": \"上午10:40:00\",\"end\": \" 下午19:40:00\"}],\"checked\": true,\"radio\": \"1\",\"light\": [{\"OutSport\": \"A001.000001.1000000000000099\" }, {\"OutSport\": \"A001.000001.1000000000000096\"}],\"title\": \"周四周五白天开\"}}}";


JSONObject obj = new JSONObject(ser);
JSONObject form = (JSONObject) obj.get("form");
String scheduleid=(String)form.get("scheduleid");
System.out.println("scheduleid"+scheduleid);
String schedulename=(String)form.get("title");
String reversecontrolmode=(String)form.get("radio");
String enabled=(String)form.get("checked");
if (null==scheduleid||scheduleid.equals("")){
	//调用增加
     Date date=new Date();
	 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	 scheduleid  =	sdf1.format(date);
}else {
	//调用删除
}



//保存时间安排表
JSONArray sureTime = (JSONArray) form.get("sureTime");
for (int i = 0; i < sureTime.length(); i++) {
	JSONObject  object3 = (JSONObject) sureTime.get(i);
	String fromtime=(String) object3.get("start");
	String totime=(String) object3.get("end");
	JSONArray DateValue = (JSONArray) form.get("DateValue");
	for (int j = 0; j < DateValue.length(); j++) {
		String  weekid = (String) DateValue.get(j);
		String scheduletimeid=scheduleid+weekid+i;
		
		
	}
}
//保存时间关联点表
JSONArray light = (JSONArray) form.get("light");
for (int i = 0; i < light.length(); i++) {
	JSONObject  object3 = (JSONObject) light.get(i);
	String OutSport=(String) object3.get("OutSport");
	//解析点
	String[] s=OutSport.split("\\.");
	
}

}
}