package com.yhzh.bacnet.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.serotonin.bacnet4j.type.primitive.Date;
import com.yhzh.zhyq.dao.BaseDao;

/**
 * 智能空调 数据库dao
 * 
 * @author samsun 2018-1-7
 *
 */
public class AirCondiDao {

	@Resource
	// 非单例不能用注入
	 BaseDao baseDao;

	/**
	 * 获取bacnet控制器列表
	 * 
	 * @return baList
	 */
	public List<Map<String,Object>> getBaControllerList(){
		List<Map<String,Object>> baList =  new ArrayList();
		String sql = "select controllerid,controllername,ip,mac,deviceid from yhzh_controller where contype='2'";
    	baList = baseDao.queryForList(sql, null);
		return baList;
	}

	/**
	 * 根据控制器编号获取点的集合
	 * 
	 * @param controllerid
	 * @return
	 */
	public List<Map<String,Object>> getPointListByConId(String controllerid){
		List<Map<String,Object>> pointList = null;
		String sql = "select pointid,phisid,pointname,ioproperty,pointtype,pointvalue,paratype,controllerid,instanceNumber,issubs,increment" +
				" from yhzh_point where controllerid=:controllerid";
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	paramMap1.put("controllerid", controllerid);
		pointList = baseDao.queryForList(sql, paramMap1);
		return pointList;
	}
	/**
	 * 保存温度历史数据
	 * 
	 * @param controllerid
	 * @return
	 */
	public int setPointhistoryy(String name,String pointname,String time,String value) {
		Date data=new Date();
		String sql = "INSERT INTO yhzh_air_history VALUES ('"+name+"', '"+pointname+"', '"+data+"', '"+value+"')";
		return	baseDao.execSql(sql, null);
		
	}
}
