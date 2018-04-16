package com.yhzh.zhyq.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/**数据库操作
 * @author chenmingshuai
 * 2017-9-16
 */
public class  BaseDao{
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource  
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate; // 支持具名参数，提高代码的可读性。
	
	/**查询
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public synchronized List<Map<String,Object>> queryForList(String sql,Map<String,Object> paramMap){
		List<Map<String,Object>> listRst = null;
		listRst = namedParameterJdbcTemplate.queryForList(sql, paramMap);
		return listRst;
	}
	
	/**增、删、改
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public synchronized int execSql(String sql,Map<String,Object> paramMap){
		int affectRow = -1;
		affectRow = namedParameterJdbcTemplate.update(sql, paramMap);
		return affectRow;
	}
	
	@Transactional
    public void initTest(){
    	String sql = "insert into yhzh_user(id,name) values (:id,:name)";
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("id", "1000000004");
    	paramMap.put("name", "chenmingshuai4");
    	this.execSql(sql, paramMap);

    	String sql1 = "insert into yhzh_user1(id,name) values (:id,:name)";
    	Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	paramMap1.put("id", "1000000005");
    	paramMap1.put("name", "chenmingshuai5");
    	this.execSql(sql1, paramMap1);
    }
	
}
