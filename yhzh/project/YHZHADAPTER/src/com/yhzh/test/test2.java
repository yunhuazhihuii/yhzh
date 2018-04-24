package com.yhzh.test;
//package com.yhzh.bacnet;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import com.yhzh.bacnet.dao.AirCondiDao;
//import com.yhzh.znzm.dao.ZnzmDao;
//
//public class test2 {
//	@Resource
//	// 非单例不能用注入
//	AirCondiDao airDao;
//	
//	@Resource(name="znzmDao")
//	private ZnzmDao     anamDao;
//
//	public static void main(String[] args) {
//
//		test2 io = new test2();
//		io.init();
//	}
//
//	public void init() {
//		List<Map<String, Object>> baList = new ArrayList();
//		Map map = new HashMap();
//		String b = "hahah";
//		map.put("a", b);
//		baList.add(map);
//
//		// List<Map<String, Object>> baList = airDao.getBaControllerList();
//
//		List<Map<String, Object>> c = getBaControllerList();
//		System.out.println(c);
//		
//		List<Map<String, Object>> a = airDao.getBaControllerList();
//		System.out.println(a);
//		
//		
//		String pointid="1000000000000425";
//		String mac=anamDao.getMacBypointId(pointid);
//		System.out.println(mac);
//		
//		
//
//	}
//
//	/**
//	 * 获取bacnet控制器列表
//	 * 
//	 * @return
//	 */
//	public static List<Map<String, Object>> getBaControllerList() {
//		// List<Map<String,Object>> baList = null;
//		List<Map<String, Object>> baList = new ArrayList();
//		Map map = new HashMap();
//		String b = "hahah";
//		map.put("a", b);
//		baList.add(map);
//
//		return baList;
//	}
//}
