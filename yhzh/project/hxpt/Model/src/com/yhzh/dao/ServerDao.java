package com.yhzh.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.yhzh.pojo.S0000002;
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
		String sql ="select F1,F2,F3,F4 from S0000001";
		scList = baseDao.queryForList(sql, null);
		return scList;
	}
	
	/**
	 * 初始化页面
	 * 查询S0000002表格中的所有数据
	 * @return
	 */
	public List<Map<String, Object>> getS0000002(){
		List<Map<String, Object>> scList = null;
		String sql ="select F1,F2,F3,F4,F5,F6 from S0000002";
		scList = baseDao.queryForList(sql, null);
		return scList;
	}
	
	public List<Map<String, Object>> getF1InS0000002(){
		List<Map<String, Object>> scList = null;
		String sql ="select distinct F1 from S0000002";
		scList = baseDao.queryForList(sql, null);
		return scList;
	}

	/**
	 * 根据父节点在s0000003表中查找可创建的子节点
	 * @param parentId
	 * @return
	 */
	public List<Map<String, Object>> FindChildNodeFromS0000003(String parentId,String findTheFirst0){
		List<Map<String, Object>> scList = null;
		String sql ="select F1,F2,F3,F4 from S0000001 WHERE f1 LIKE '"+findTheFirst0+"' AND F3 = '1' AND F1 <> '"+parentId+"'";
		scList = baseDao.queryForList(sql, null);
		return scList;
	}
	/**
	 * 只查询该1110000父节点下的子节点
	 * @param parentId
	 * @param findTheFirst0
	 * @return
	 */
	public List<Map<String, Object>> FindChildNode(String parentId,String findTheFirst0){
		List<Map<String, Object>> scList = null;
		String sql ="select F1,F2,F3,F4 from S0000001 WHERE F1 LIKE '"+findTheFirst0+"' AND F1 <> '"+parentId+"'";
		scList = baseDao.queryForList(sql, null);
		return scList;
	}
	
	/**
	 * 查询S0000002表格中的所有数据
	 * @return
	 */
	public List<Map<String, Object>> getDatas(String serviceCode){
		List<Map<String, Object>> scList = null;
		String sql ="select F1,F2,F3,F4,F5,F6 from S0000002 where F1= '"+serviceCode+"' and F6 ='000000000000'";
		scList = baseDao.queryForList(sql, null);
		return scList;
	}
	/**
	 * 查询子节点
	 * @param parentCode
	 * @return
	 */
	public List<Map<String, Object>> getTreeChildren(String parentCode){
		List<Map<String, Object>> scList = null;
		String sql ="select F1,F2,F3,F4,F5,F6 from S0000002 where F6 ='"+parentCode+"'";
		scList = baseDao.queryForList(sql, null);
		return scList;
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
	public List<Map<String, Object>> OpenServer(String ServerCode){
		String sql = "select F1,F2,F3,F4,F5,F6 from S0000002 where F1 = '"+ServerCode+"' and F6 ='000000000000'";
		List<Map<String, Object>> list = baseDao.queryForList(sql, null);
		return list;
	}
	/**
	 * 根据服务编码从S1000000表中查出相关的服务信息
	 * @param ServerCode
	 * @return
	 */
	public List<Map<String, Object>> EditServer(String ServerCode) {
		String sql = "select F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11 from S1000000 where F1 = "+ServerCode;
		List<Map<String, Object>> list = baseDao.queryForList(sql, null);
		return list;
	}
	
	/**
	 * 将数据更新到S1000000表中
	 * @param s1000000
	 * @return
	 */
	public int updateS1000000(S1000000 s1000000) {
		String sql = "update S1000000 set F2='"+s1000000.getF2()+"',F3='"+s1000000.getF3()+"',F4='"+s1000000.getF4()+"',F5='"+s1000000.getF5()+"',F6='"+s1000000.getF6()+"',F7='"+s1000000.getF7()+"',F8='"+s1000000.getF8()+"',F9='"+s1000000.getF9()+"',F10='"+s1000000.getF10()+"',F11='"+s1000000.getF11()+"' where F1='"+s1000000.getF1()+"'";
		int updateS1000000 = baseDao.execSql(sql, null);
		return updateS1000000;
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
