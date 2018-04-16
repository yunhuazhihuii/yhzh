package com.yhzh.schedule.dao;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yhzh.schedule.ListToJson;
import com.yhzh.util.YhzhUtil;
import com.yhzh.zhyq.dao.BaseDao;
public class ScheduleDao {

	@Resource
	// 非单例不能用注入
	BaseDao baseDao;

	/**
	 * 获取所有日程表列表
	 * 
	 * @return
	 */
	//interval
	public List<Map<String, Object>> getScheduleList() {
		List<Map<String, Object>> scList = null;
		String sql = "select scheduleid,schedulename,enabled,reverse,controlmode,intervals from yhzh_schedule";
		scList = baseDao.queryForList(sql, null);
		return scList;
	}

	/**
	 * 获取所有日程表起始结束列表
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getScheduleList(String scheduleid) {
		List<Map<String, Object>> scList = null;
		String sql = "select scheduletimeid,weekid,fromtime,totime from yhzh_schedule_times where scheduleid=:scheduleid";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("scheduleid", scheduleid);
		scList = baseDao.queryForList(sql, paramMap1);
		return scList;
	}

	
	
	/**
	 * 判断是否节假日 date格式String
	 * 
	 * @return
	 */
	public Boolean isHoliday(Date date) {
		boolean holiday = false;
		List<Map<String, Object>> scList = null;
		String sql = "select holidate from yhzh_pa_holiday where holidate=:holidate";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("holidate", date);
		scList = baseDao.queryForList(sql, paramMap1);
		if (scList.size() > 0) {
			holiday = true;
		}
		return holiday;
	}

	/**
	 * 根据输出点名获取输入点名
	 * 
	 * @return
	 */
	public String getpointInbypointOut(String pointOut) {
		String pointIn = "";
		String sql = "select (case t.pointtype when '4' then :pointid1 else "
				+ "(select  a.pointid from yhzh_point a where a.phisid=t.phisid and a.ioproperty='1') end) as inpointid from yhzh_point t "
				+ "where t.pointid=:pointid2 and t.ioproperty='2';";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("pointid1", pointOut);
		paramMap1.put("pointid2", pointOut);
		List<Map<String, Object>> scList = baseDao.queryForList(sql, paramMap1);
		if (null != scList && scList.size() > 0) {
			Map<String, Object> map = scList.get(0);
			pointIn = (String) map.get("pointid");
		}
		return pointIn;

	}

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
	 * 根据日志编号获取日志关联点
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getpointbyscheduleid(String scheduleid) {
		String sql = "select pointid,controlmode,delaytimes from yhzh_schedule_rel where scheduleid=:scheduleid";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("scheduleid", scheduleid);
		List<Map<String, Object>> scList = baseDao.queryForList(sql, paramMap1);
		return scList;

	}

	/**
	 * 写入日程执行日志
	 * 
	 * @return
	 */
	public int insYhzhScheduleLog(String logtime, String scheduleid,
			String scheduletimeid, String outpointid, String outpointvalue,
			String inpointid, String inpointvalue, String remark) {

		String sql = "INSERT INTO yhzh_schedule_log VALUES ('" + logtime
				+ "', '" + scheduleid + "', '" + scheduletimeid + "'," + " '"
				+ outpointid + "', '" + outpointvalue + "', '" + inpointid
				+ "', '" + inpointvalue + "', '" + remark + "')";
		return baseDao.execSql(sql, null);
	}

	/**
	 * 判断属于开启时间段 date格式Date
	 * 
	 * @return
	 */
	public String isopen(Date date, String scheduleid) {
		YhzhUtil yhu = new YhzhUtil();
		//Between属性为：1是开，0是关，2是不开不关
		String Between = "0";
		List<Map<String, Object>> scList = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		// 这个week调用工具类，得到星期几
		String weekid = "0";
		if (isHoliday(date)) {
			weekid = "8";
		} else {
				weekid = String.valueOf(yhu.dayForWeek(date));
		} 
		String sql0=" select weekid from yhzh_schedule_times where weekid=:weekid and scheduleid=:scheduleid";
		
		
		String sql = "select weekid from yhzh_schedule_times where weekid=:weekid and scheduleid=:scheduleid and totime>=:date and fromtime<=:date";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("weekid", weekid);
		paramMap1.put("scheduleid", scheduleid);
		paramMap1.put("date", sdf1.format(date));
		scList = baseDao.queryForList(sql0, paramMap1);
		if (scList.size() > 0) {
			scList = baseDao.queryForList(sql, paramMap1);
			if (scList.size() > 0) {
				Between = "1";}
		}else{
			
				Between = "2";
			}
			
		
		
	
		return Between;
	}

	/**
	 * 新增日程表
	 * 
	 * @return
	 */
	public void addsche(String scheduleid,String schedulename, String enabled, String reverse,String controlmode, String intervals, String floor, String system) {

		String sql = "insert into yhzh_schedule(scheduleid,schedulename,enabled,reverse,controlmode,intervals,floor,system) "
				+ "VALUES (:scheduleid,:schedulename,:enabled, :reverse,:controlmode, :intervals,:floor, :system)";
		
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("scheduleid", scheduleid);
		paramMap1.put("schedulename", schedulename);
		paramMap1.put("enabled", enabled);
		paramMap1.put("reverse", reverse);
		paramMap1.put("controlmode", controlmode);
		paramMap1.put("intervals", intervals);
		paramMap1.put("floor", floor);
		paramMap1.put("system", system);
		//return baseDao.backId(sql, paramMap1);
		 baseDao.execSql(sql, paramMap1);
	}
	/**
	 * 更改日程表
	 * 
	 * @return
	 */
	public long updatesche( String scheduleid, String schedulename, String enabled, String reverse, String controlmode, String intervals, String floor, String system) {
	String sql = "UPDATE yhzh_schedule SET schedulename =:schedulename,enabled =:enabled ,reverse =:reverse,controlmode =:controlmode,intervals =:intervals WHERE scheduleid=:scheduleid  ";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("schedulename", schedulename);
		paramMap1.put("enabled", enabled);
		paramMap1.put("reverse", reverse);
		paramMap1.put("controlmode", controlmode);
		paramMap1.put("intervals", intervals);
		paramMap1.put("scheduleid", scheduleid);
		paramMap1.put("floor", floor);
		paramMap1.put("system", system);
		return baseDao.execSql(sql, paramMap1);
	}
	/**
	 * 新增日程时间安排表
	 */
	public void addscheduletimes(String scheduletimeid, String scheduleid, String weekid,String fromtime,String totime) {

		String sql = "insert into yhzh_schedule_times(scheduletimeid,scheduleid,weekid,fromtime,totime) VALUES (:scheduletimeid,:scheduleid,:weekid,:fromtime,:totime)";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("scheduletimeid", scheduletimeid);
		paramMap1.put("scheduleid", scheduleid);
		paramMap1.put("weekid", weekid);
		paramMap1.put("fromtime", fromtime);
		paramMap1.put("totime", totime);
		baseDao.execSql(sql, paramMap1);
	}
	/**
	 * 更改日程时间安排表
	 */
	public void updatescheduletimes(String scheduletimeid, String scheduleid, String weekid,String fromtime,String totime) {

		String sql = "update yhzh_schedule_times set scheduletimeid=:scheduletimeid,weekid=:weekid,fromtime=:fromtime,totime=:totime where scheduleid=:scheduleid ";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("scheduletimeid", scheduletimeid);
		paramMap1.put("weekid", weekid);
		paramMap1.put("fromtime", fromtime);
		paramMap1.put("totime", totime);
		paramMap1.put("scheduleid", scheduleid);
		baseDao.execSql(sql, paramMap1);
	}
	
	/**
	 * 新增日程和点关联表
	 */
	public void addschedulerel(String scheduleid, String pointid,String controlmode,String delaytimes,String pointname) {
		String sql = "insert into yhzh_schedule_rel(scheduleid,pointid,controlmode,delaytimes,pointname) VALUES (:scheduleid,:pointid,:controlmode,:delaytimes,:pointname)";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("scheduleid", scheduleid);
		paramMap1.put("pointid", pointid);
		paramMap1.put("controlmode", controlmode);
		paramMap1.put("delaytimes", delaytimes);
		paramMap1.put("pointname", pointname);
		baseDao.execSql(sql, paramMap1);
	}


	/**
	 * 删除日程表
	 * 
	 * @return
	 */
	public void deletesche(String scheduleid) {
		String sql0 = "DELETE FROM yhzh_schedule WHERE scheduleid=:scheduleid";
		String sql1 = "DELETE FROM yhzh_schedule_times WHERE scheduleid=:scheduleid";
		String sql2 = "DELETE FROM yhzh_schedule_rel WHERE scheduleid=:scheduleid";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("scheduleid", scheduleid);
		baseDao.execSql(sql0, paramMap1);
		baseDao.execSql(sql1, paramMap1);
		baseDao.execSql(sql2, paramMap1);
		
	}
	/**
	 * 新增节假日
	 * 
	 * @return
	 */
	public int saveHoliday(String holidate, String holidesc) {

		String sql = "INSERT INTO yhzh_pa_holiday VALUES ('" + holidate + "', '" + holidesc + "')";

		return baseDao.execSql(sql, null);
	}
	
	/**
	 * 删除节假日
	 * 
	 * @return
	 */
	public int deleteHoliday(String holidate) {

		String sql = "DELETE FROM yhzh_pa_holiday WHERE holidate=:holidate";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("holidate", holidate);
		return baseDao.execSql(sql, paramMap1);
	}
	/**
	 * 更改节假日
	 * 
	 * @return
	 */
	public int updateHoliday(String holidate, String holidesc) {

		String sql = "UPDATE yhzh_pa_holiday SET holidesc =:holidesc WHERE holidate=:holidate";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("holidesc", holidesc);
		paramMap1.put("holidate", holidate);
		return baseDao.execSql(sql, paramMap1);
	}
	/**
	 * 查询节假日
	 * 
	 * @return
	 */
	public List<Map<String, Object>> selectHoliday() {
		String sql = "select * from yhzh_pa_holiday";
		List<Map<String, Object>> scList = baseDao.queryForList(sql, null);
		return scList;
	}	
	
	
	/**
	 * 查询日程表
	 * 
	 * @return
	 */
	public String selectSche(String system,String floor) {
		ListToJson ltj=new ListToJson();
		String backSche="";
		String sql1 = "select * from yhzh_schedule where floor=:floor and system=:system";
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("floor", floor);
		paramMap1.put("system", system);
		
		
		List<Map<String, Object>> scList1 = baseDao.queryForList(sql1, paramMap1);
		for(Map<String,Object> map : scList1){
			String scheduleid = (String)map.get("scheduleid");
			String sql2="select distinct fromtime,totime from yhzh_schedule_times where scheduleid=:scheduleid";
			Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("scheduleid", scheduleid);
			List<Map<String, Object>> scList2 = baseDao.queryForList(sql2, paramMap2);
		
			String sql3="select distinct weekid from yhzh_schedule_times where  scheduleid=:scheduleid";  
			Map<String, Object> paramMap3 = new HashMap<String, Object>();
			paramMap3.put("scheduleid", scheduleid);
			List<Map<String, Object>> scList3 = baseDao.queryForList(sql3, paramMap3);
			
			String sql4="select pointname from yhzh_schedule_rel where  scheduleid=:scheduleid";  
			Map<String, Object> paramMap4 = new HashMap<String, Object>();
			paramMap4.put("scheduleid", scheduleid);
			List<Map<String, Object>> scList4 = baseDao.queryForList(sql4, paramMap4);
		
			
			if(backSche.equals("")){
				backSche=backSche+"{"+"\"schedule\":"+ltj.maptojson(map)+",\"sureTime\":"+ltj.change(scList2).toString()+",\"DateValue\":"+ltj.change(scList3).toString()+",\"Light\":"+ltj.change(scList4).toString()+"}";
			}else{
				backSche=backSche+",{"+"\"schedule\":"+ltj.maptojson(map)+",\"sureTime\":"+ltj.change(scList2).toString()+",\"DateValue\":"+ltj.change(scList3).toString()+",\"Light\":"+ltj.change(scList4).toString()+"}";
			}
			
			
		}	
		backSche="{\"all\":["+backSche+"]}";
		return backSche;
	}

}
