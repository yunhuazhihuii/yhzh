package com.yhzh.biz;

import java.util.List;
import java.util.Map;

import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S1000000;
import com.yhzh.pojo.S1100000;

public interface ServerBiz {
	/**
	 * 初始化页面
	 * 查询S0000001表格中的所有数据
	 */
	public List<Object> getS0000001();
	/**
	 * 查询S0000002表格中的所有数据
	 */
	public List<Object> getS0000002();
	/**
	 * 从数据字典表中获得服务编码和服务单元编码
	 */
	public Map<String, String> getServiceCodeAndUnitCode();
	/**
	 * 更新服务编码到数据字典表中
	 * @param id
	 */
	public int updateServiceCode(String serviceCode);
	/**
	 * 更新服务单元编码到数据字典表中
	 * @param id
	 */
	public int updateServiceUnitCode(String serviceUnitCode);
	/**
	 * 插入数据到S0000002数据库表中
	 * @param s2
	 */
	public int insertS0000002(String serviceCode);
	/**
	 * 插入数据到S1000000数据库表中
	 * @param s1000000
	 */
	public int insertS1000000(String serviceCode);
	/**
	 * 插入数据到S1100000数据库表中
	 * @param s1100000
	 */
	public int insertS1100000(String serviceCode,String serviceUnitCode);
	/**
	 * 根据服务编码，从数据库中查询 出数据给页面
	 * @param ServerCode
	 * @return
	 */
	public List<Object> getDatasByServiceCode(String ServerCode);
}
