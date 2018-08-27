package com.yhzh.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S1100000;

public class ServerUnitDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource  
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Resource
	BaseDao baseDao;
	
	/**
	 * 根据服务单元编码从S1100000表中查出相关的服务信息
	 * @param ServerUnitCode
	 * @return
	 */
	public List<Map<String, Object>> findS1100000ByServerUnitCode(String ServerUnitCode) {
		String sql = "select F1,F2,F3,F4,F5,F6,F7,F8,F9,F10 from S1100000 where F2 = "+ServerUnitCode;
		List<Map<String, Object>> list = baseDao.queryForList(sql, null);
		return list;
	}
	/**
	 * 将数据更新到S1100000表中
	 * @param s1100000
	 * @return
	 */
	public int updateS1100000(S1100000 s1100000) {
		String sql = "update S1100000 set F3='"+s1100000.getF3()+"',F4='"+s1100000.getF4()+"',F5='"+s1100000.getF5()+"',F6='"+s1100000.getF6()+"',F7='"+s1100000.getF7()+"',F8='"+s1100000.getF8()+"',F9='"+s1100000.getF9()+"',F10='"+s1100000.getF10()+"' where F1='"+s1100000.getF1()+"'";
		int updateS1100000 = baseDao.execSql(sql, null);
		return updateS1100000;
	}
	/**
	 * 将数据更新到S0000002表中
	 * @param s0000002
	 * @return
	 */
	public int updateS0000002(S0000002 s0000002) {
		System.out.println("将数据更新到S0000002表中的格式："+s0000002.getF3()+ s0000002.getF2());
		String sql = "update S0000002 set F3='"+s0000002.getF3()+"' where F2='"+s0000002.getF2()+"'";
		int updateS0000002 = baseDao.execSql(sql, null);
		return updateS0000002;
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
	 * 插入数据到S0000002数据库表中
	 * @param S0000002
	 * @return
	 */
	public int insertS0000002(S0000002 s0000002) {
		String sql = "insert into S0000002(F1,F2,F3,F4,F5,F6) values('"+s0000002.getF1()+"','"+s0000002.getF2()+"','"+s0000002.getF3()+"','"+s0000002.getF4()+"','"+s0000002.getF5()+"','"+s0000002.getF6()+"')";
		int insertS0000002 = baseDao.execSql(sql, null);
		return insertS0000002;
	}
	
	/**
	 * 自动获得编码
	 * @param DicCode
	 * @return
	 */
	public String AutoGetCode(String DicCode) {
		String sql = "select GetAutoCode('"+DicCode+"')";
		String getCode = null;
		List<Map<String, Object>> autoGetCode = baseDao.queryForList(sql, null);
		for (Map<String, Object> map : autoGetCode) {
			getCode = (String) map.get("GetAutoCode('"+DicCode+"')");
			System.out.println("得到自动编码2：" + getCode);
		}
		return getCode;
	}
}
