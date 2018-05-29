package com.yhzh.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class BaseDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource  
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	 * 查询操作
	 */
	public synchronized List<Map<String,Object>> queryForList(String sql,Map<String,Object> paramMap){
		List<Map<String,Object>> listRst = null;
		listRst = namedParameterJdbcTemplate.queryForList(sql, paramMap);
		return listRst;
	}
	
	/**
	 * 增删改操作
	 */
	public synchronized int execSql(String sql,Map<String,Object> paramMap){
		int affectRow = -1;
		affectRow = namedParameterJdbcTemplate.update(sql, paramMap);
		return affectRow;
	}
	
}
