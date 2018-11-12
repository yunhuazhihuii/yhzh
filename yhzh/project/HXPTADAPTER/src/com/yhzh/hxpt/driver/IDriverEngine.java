package com.yhzh.hxpt.driver;

/**驱动引擎接口，所有驱动都归它管理，包括ba、mqtt等
 * 做成线程是为了可以独立启动，避免点过多时启动过慢
 * @author chenms 2018-6-26
 *接口可以继承多个接口
 */
public interface IDriverEngine extends Runnable {
	
}
