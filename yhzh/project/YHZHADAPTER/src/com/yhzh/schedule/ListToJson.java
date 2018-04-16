package com.yhzh.schedule;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ListToJson {

	
	public JSONArray change(List<Map<String, Object>> e){
		JSONArray a=JSONArray.fromObject(e.toArray());
		return a;
	}
	public String changes(List<Map<String, Object>> e){
		String a=JSONArray.fromObject(e.toArray()).toString();
		return a;
	}
	public String maptojson(Map<String, Object> e){
		JSONObject s=JSONObject.fromObject(e);
		String a=s.toString();
		return a;
	}
	
}
