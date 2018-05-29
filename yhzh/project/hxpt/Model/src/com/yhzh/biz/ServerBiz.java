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
	 * 插入数据到S0000002数据库表中
	 * @param s2
	 */
	public int insertS0000002(String serviceCode,String serviceUnitCode);
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
	/**
	 * 根据服务编码从S1000000表中查询数据
	 * @param ServerCode
	 * @return
	 */
	public List<Object> findS1000000ByServerCode(String ServerCode);
	
	/**
	 * 更新数据到s1000000表中
	 * @param serverString
	 * @return
	 */
	public int updateS1000000(String serverString);
	/**
	 * 更新服务数据到s0000002表中
	 * @param serverString
	 * @return
	 */
	public int updateServerS0000002(String serverString);
	
	/**
	 * 根据服务单元编码向S1100000数据库中查询数据
	 * @param ServerUnitCodeString
	 * @return
	 */
	public List<Object> findS1100000ByServerUnitCode(String ServerUnitCodeString);
	/**
	 * 更新数据到S1100000表中
	 * @param ServerUnitCodeString
	 * @return
	 */
	public int updateS1100000(String ServerUnitCodeString);
	/**
	 * 更新服务单元数据到s0000002表中
	 * @param serverUnitString
	 * @return
	 */
	public int updateServerUnitS0000002(String serverUnitString);

	/**
	 * 创建实时对象
	 * @param createRtObjectString
	 * @return
	 */
	public int CreateRTObject(String createRtObjectString);
	
	/**
	 * 自动获得编码
	 * @param DicCode
	 * @return
	 */
	public String AutoGetCode(String DicCode);
	
}
