package com.yhzh.biz;
public interface ServerBiz {
	/**
	 * 初始化页面
	 * 查询S0000001表格中的所有数据
	 */
	public String InitPage();
	
	/**
	 * 获得子节点
	 * @param parentCode
	 * @return
	 */
	public String ChildrenNode(String parentCode);
	
	/**
	 * 创建服务
	 * @return
	 */
	public String CreateServer();

	/**
	 * 根据服务编码，从数据库中查询 出数据给页面
	 * @param ServerCode
	 * @return
	 */
	public String OpenServer(String ServerCode);
	/**
	 * 根据服务编码从S1000000表中查询数据
	 * @param ServerCode
	 * @return
	 */
	public String EditServer(String ServerCode);
	
	/**
	 * 更新服务数据到s1000000表中
	 * @param serverString
	 * @return
	 */
	public String UpdateServer(String serverString);
}
