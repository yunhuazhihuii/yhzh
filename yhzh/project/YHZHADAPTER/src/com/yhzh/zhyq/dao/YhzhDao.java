package com.yhzh.zhyq.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

public class YhzhDao {
	@Resource
	// 非单例不能用注入
	BaseDao baseDao;

	/**
	 * 根据点名获取点值
	 * 
	 * @return
	 */
	public String getpointValuebypointid(String pointid) {
		String pointvalue = "";
		String sql = "select pointvalue from yhzh_point where pointid=:pointid";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("pointid", pointid);
		List<Map<String, Object>> scList = baseDao.queryForList(sql, paramMap1);
		if (null != scList && scList.size() > 0) {
			Map<String, Object> map = scList.get(0);
			pointvalue = (String) map.get("pointvalue");
		}
		return pointvalue;

	}

	/**
	 * 根据点名保存点值
	 * 
	 * @return
	 */
	public int setpointValuebypointid(String pointid, String pointvalue) {

		String sql = " update yhzh_point SET pointvalue = :pointvalue WHERE pointid = :pointid ";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("pointid", pointid);
		paramMap1.put("pointvalue", pointvalue);
		return baseDao.execSql(sql, paramMap1);
	}
	/**根据点编号获取点信息
	 * @return
	 */
	public Map<String,Object> getPointInfoByid(String pointid){
		Map<String,Object> mapRst = null;
		String sql = "select phisid,ioproperty,pointtype,controllerid from yhzh_point where pointid=:pointid";
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	paramMap1.put("pointid", pointid);
    	 List<Map<String,Object>> scList = baseDao.queryForList(sql, paramMap1);
		if(null != scList && scList.size() > 0){
    		mapRst = scList.get(0);
    	}
		return mapRst;
	}
	
	/**获取点信息
	 * @return
	 */
	public  List<Map<String,Object>> getPointInfo(){
		Map<String,Object> mapRst = null;
		String sql = "select pointid,pointvalue, pointname,ioproperty,controllerid from yhzh_point";
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
    
    	 List<Map<String,Object>> scList = baseDao.queryForList(sql, paramMap1);
	
		return scList;
	}

}
