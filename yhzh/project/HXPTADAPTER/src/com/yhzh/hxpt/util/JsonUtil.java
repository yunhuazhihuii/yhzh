package com.yhzh.hxpt.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

/**json字符串与 map、list转化
 * @author chenms 2018-7-7
 *
 */
public class JsonUtil {
	
	public List<Map<String,Object>> josnStrToList(String jsonStr){
		List<Map<String,Object>> list = null;
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		list = new ArrayList<Map<String,Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while(it.hasNext()){
		JSONObject json2 = it.next();
		list.add(jsonStrToMap(json2.toString()));
		}
		return list;
	}
	
	public String listToJsonStr(List<Map<String,Object>> list){
		String jsonStr = null;
		JSONArray ja = JSONArray.fromObject(list);
		jsonStr = ja.toString();
		return jsonStr;
	} 
	
	public Map<String,Object> jsonStrToMap(String jsonStr){
		ListOrderedMap map = new ListOrderedMap();
		//最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for(Object k : json.keySet()){
			Object v = json.get(k); 
			//如果内层还是数组的话，继续解析
			if(v instanceof JSONArray){
				JSONArray ja = (JSONArray)v;
				if(ja.get(0) instanceof String){
					List<String> listStr = new ArrayList<String>();
					for(int i = 0;i<ja.size();i++){
						listStr.add((String)ja.get(i));
					}
					map.put(k.toString(), listStr);
				}else{
					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
					Iterator<JSONObject> it = ((JSONArray)v).iterator();
					while(it.hasNext()){
						JSONObject json2 = it.next();
						list.add(this.jsonStrToMap(json2.toString()));
					}
					map.put(k.toString(), list);
				}
			}else if(v instanceof JSONObject){
				Map<String,Object> map1 = jsonStrToMap(v.toString());
				map.put(k.toString(), map1);
			}
			else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}
	
	public String mapToJsonStr(Map<String,Object> map){
		String jsonStr = null;
		JSONObject jo = JSONObject.fromObject(map);
		jsonStr = jo.toString();
		return jsonStr;
	}
	
}
