package com.yhzh.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S0000003;
import com.yhzh.pojo.S1000000;
import com.yhzh.pojo.S1100000;

public class ServerDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource  
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Resource
	BaseDao baseDao;
	
	/**
	 * 初始化页面
	 * 查询S0000001表格中的所有数据
	 * @return
	 */
	public List<Map<String, Object>> getS0000001(){
		List<Map<String, Object>> scList = null;
		String sql ="select * from S0000001";
		scList = baseDao.queryForList(sql, null);
		return scList;
	}
	
	/**
	 * 查询S0000002表格中的所有数据
	 * @return
	 */
	public List<Map<String, Object>> getDatas(){
		List<Map<String, Object>> scList = null;
		String sql ="select * from S0000002";
//		scList = namedParameterJdbcTemplate.queryForList(sql, paramMap);
		scList = baseDao.queryForList(sql, null);
		return scList;
	}
	
	/**
	 * 从数据字典表中获得服务编码和服务单元编码
	 * @return
	 */
	public List<Map<String, Object>> getServiceCode(){
		String sql = "select * from S0000003 where F5='服务编码' or F5='服务单元编码'";
		List<Map<String, Object>> list = baseDao.queryForList(sql, null);
		return list;
	}
	
	/**
	 * 更新服务编码到数据字典表中
	 * @param id
	 * @return
	 */
	public int updateServiceCode(String serviceCode) {
		String sql = "update S0000003 set F4='"+serviceCode+"'where F5='服务编码'";
		int updateServiceCode = baseDao.execSql(sql, null);
		return updateServiceCode;
	}
	
	/**
	 * 更新服务单元编码到数据字典表中
	 * @param id
	 * @return
	 */
	public int updateServiceUnitCode(String serviceUnitCode) {
		String sql = "update S0000003 set F4='"+serviceUnitCode+"'where F5='服务单元编码'";
		int updateServiceUnitCode = baseDao.execSql(sql, null);
		return updateServiceUnitCode;
	}
	
	/**
	 * 插入数据到S0000002数据库表中
	 * @param s2
	 * @return
	 */
	public int insertS0000002(S0000002 s2) {
		System.out.println("获得传输的数据："+ s2.getF1()+ s2.getF2()+ s2.getF3()+ s2.getF4()+ s2.getF5()+ s2.getF6());
		String sql = "insert into S0000002(F1,F2,F3,F4,F5,F6) values('"+s2.getF1()+"','"+s2.getF2()+"','"+s2.getF3()+"','"+s2.getF4()+"','"+s2.getF5()+"','"+s2.getF6()+"')";
		int insertS0000002 = baseDao.execSql(sql, null);
		return insertS0000002;
	}
	
	/**
	 * 插入数据到S1000000数据库表中
	 * @param s2
	 * @return
	 */
	public int insertS1000000(S1000000 s1000000) {
		System.out.println("获得传输的数据："+ s1000000.getF1()+ s1000000.getF2()+ s1000000.getF4()+ s1000000.getF10()+ s1000000.getF11());
		String sql = "insert into S1000000(F1,F2,F4,F10,F11) values('"+s1000000.getF1()+"','"+s1000000.getF2()+"','"+s1000000.getF4()+"','"+s1000000.getF10()+"','"+s1000000.getF11()+"')";
		int insertS1000000 = baseDao.execSql(sql, null);
		return insertS1000000;
	}

	/**
	 * 插入数据到S1100000数据库表中
	 * @param s1100000
	 * @return
	 */
	public int insertS1100000(S1100000 s1100000) {
		System.out.println("获得传输的数据："+ s1100000.getF1()+ s1100000.getF2()+ s1100000.getF3()+ s1100000.getF9()+ s1100000.getF10());
		String sql = "insert into S1100000(F1,F2,F3,F9,F10) values('"+s1100000.getF1()+"','"+s1100000.getF2()+"','"+s1100000.getF3()+"','"+s1100000.getF9()+"','"+s1100000.getF10()+"')";
		int insertS1100000 = baseDao.execSql(sql, null);
		return insertS1100000;
	}
	
	/**
	 * 根据服务编码，从数据库中查询 出数据给页面
	 * @param ServerCode
	 * @return
	 */
	public List<Map<String, Object>> getDatasByServiceCode(String ServerCode){
		String sql = "select * from S0000002 where F6 = "+ServerCode;
		List<Map<String, Object>> list = baseDao.queryForList(sql, null);
		return list;
	}
	
	
}
