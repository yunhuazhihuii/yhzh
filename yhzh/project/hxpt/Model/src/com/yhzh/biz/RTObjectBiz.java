package com.yhzh.biz;

public interface RTObjectBiz {

	/**
	 * 创建实时对象
	 * @param createRtObjectString
	 * @return
	 */
	public String CreateRTObject(String createRtObjectString);
	
	/**
	 * 根据实时对象编码查询实时对象
	 * @param RtObjectCode
	 * @return
	 */
	public String findS1110000ByRtObjectCode(String RtObjectCode);
	
	/**
	 * 更新实时对象
	 * @param RtObjectString
	 * @return
	 */
	public String UpdateRtObject(String RtObjectString);
	/**
	 * 删除实时对象
	 * @param RtObjectCode
	 * @return
	 */
	public String DeleteRtObject(String RtObjectCode);
}
