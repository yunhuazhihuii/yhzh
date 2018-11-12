package com.yhzh.hxpt.iface;

/**核心平台最顶层接口
 * @author chenms 2018-7-1
 *
 */
public interface IHxptSuper {

	
	/**启动方法
	 * @return 1启动成功; 非1为启动失败原因
	 */
	public String execute();
	
	/**获取状态  : 0未启动；1已启动完成；
	 * @return
	 */
	public String getState();

	/**设置状态
	 * @param state
	 */
	public void setState(String state);
}
