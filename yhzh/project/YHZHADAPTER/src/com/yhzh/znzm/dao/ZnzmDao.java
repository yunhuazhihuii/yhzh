package com.yhzh.znzm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yhzh.zhyq.dao.BaseDao;

public class ZnzmDao {

	@Resource //非单例不能用注入
	BaseDao baseDao;
	
	/**根据点编号获取控制器地址（网 关）mac值
	 * @param pointId
	 * @return
	 */
	public String getMacBypointId(String pointId){
		String mac = null;
		
		String sql = "select max(mac) mac from yhzh_controller where controllerid = " +
				"(select max(a.controllerid) from yhzh_znzm_area a where a.areaid=" +
				"(select max(b.areaid) from yhzh_znzm_cell b where b.celllid=" +
				"(select max(c.phisid) from yhzh_point c where c.pointid=:pointid)))";
    	Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	paramMap1.put("pointid", pointId);
    	List<Map<String,Object>> list = baseDao.queryForList(sql, paramMap1);
    	if(null != list && list.size() > 0){
    		Map<String,Object> map  = list.get(0);
    		mac = (String)map.get("mac");
    	}

    	
    	return mac;
    	
    			
	}
	
	/**根据点编号获取区域号
	 * @param pointId
	 * @return
	 */
	public String getAreaCodeBypointId(String pointId){
		String areaCode = null;
		String sql = "select max(a.areacode) areacode from yhzh_znzm_area a where a.areaid=" +
				"(select max(b.areaid) from yhzh_znzm_cell b where b.celllid=" +
				"(select max(c.phisid) from yhzh_point c where c.pointid=:pointid))";
    	Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	paramMap1.put("pointid", pointId);
    	List<Map<String,Object>> list = baseDao.queryForList(sql, paramMap1);
    	if(null != list && list.size() > 0){
    		Map<String,Object> map  = list.get(0);
    		areaCode = (String)map.get("areacode");
    	}
		return areaCode;
	}
	
	/**根据点编号获取区域回路号
	 * @param pointId
	 * @return
	 */
	public String getCellCodeBypointId(String pointId){
		String cellCode = null;
		String sql = "select max(b.cellcode) cellcode from yhzh_znzm_cell b where b.celllid=" +
				"(select max(c.phisid) from yhzh_point c where c.pointid=:pointid)";
    	Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	paramMap1.put("pointid", pointId);
    	List<Map<String,Object>> list = baseDao.queryForList(sql, paramMap1);
    	if(null != list && list.size() > 0){
    		Map<String,Object> map  = list.get(0);
    		cellCode = (String)map.get("cellcode");
    	}
		return cellCode;
	}
	
	/**根据 区域编号/区域回路号/点输入输出属性 找到点编号
	 * @param modulemac 区域编号
	 * @param cellindex 区域回路号
	 * @param ioproperty 点输入输出属性（1数据量输入2数据量输出3模拟量）
	 * @return
	 */
	public String getPointId(String modulemac,String cellindex,String ioproperty){
		String pointid = "";
		String sql = "select pointid from yhzh_point where ioproperty=:ioproperty and phisid = " + 
				"(select max(celllid) from yhzh_znzm_cell where modulemac=:modulemac and cellindex=:cellindex)";
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	paramMap1.put("ioproperty", ioproperty);
    	paramMap1.put("modulemac", modulemac);
    	paramMap1.put("cellindex", cellindex);
    	List<Map<String,Object>> list = baseDao.queryForList(sql, paramMap1);
    	if(null != list && list.size() > 0){
    		Map<String,Object> map  = list.get(0);
    		pointid = (String)map.get("pointid");
    	}
		return pointid;
	}
	/**根据 区域编号/区域回路号/点输入输出属性 找到点编号
	 * @param modulemac 区域编号
	 * @param cellindex 区域回路号
	 * @param ioproperty 点输入输出属性（1数据量输入2数据量输出3模拟量）
	 * @return
	 */
	public List<Map<String, Object>> getallschedule(){
		String sql = "select * from yhzh_schedule";
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	List<Map<String,Object>> list = baseDao.queryForList(sql, paramMap1);
    	if(null != list && list.size() > 0){
    		
    	}
		return list;
	}
	/**找到点编号
	 * @return
	 */
	public List<Map<String, Object>> getallpointid(){
		String sql = "select pointid from yhzh_point where pointname='回路输入'";
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	List<Map<String,Object>> list = baseDao.queryForList(sql, paramMap1);
    	if(null != list && list.size() > 0){
    		
    	}
		return list;
	}
	/**找到点编号
	 * @return
	 */
	public List<Map<String, Object>> getallzmpointid(){
		String sql = "select pointid from yhzh_point where pointname='回路输入'";
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	List<Map<String,Object>> list = baseDao.queryForList(sql, paramMap1);
    	if(null != list && list.size() > 0){
    		
    	}
		return list;
	}
	/**找到点编号
	 * @return
	 */
	public List<Map<String, Object>> getallzmMac(){
		String sql = "select pointid from yhzh_controller where controller='智能照明'";
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	List<Map<String,Object>> list = baseDao.queryForList(sql, paramMap1);
    	if(null != list && list.size() > 0){
    		
    	}
		return list;
	}
}
