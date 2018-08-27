package com.yhzh.biz;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S1000000;
import com.yhzh.pojo.S1100000;

public interface ServerUnitBiz {
	/**
	 * 创建服务单元
	 * @param CreateServerUnitString
	 * @return
	 */
	public String CreateServerUnit(String CreateServerUnitString);
	/**
	 * 根据服务单元编码向S1100000数据库中查询数据
	 * @param ServerUnitCodeString
	 * @return
	 */
	public String findS1100000ByServerUnitCode(String ServerUnitCode);
	/**
	 * 更新数据到S1100000表中
	 * @param ServerUnitCodeString
	 * @return
	 */
	public String updateS1100000(String ServerUnitCodeString);

}
