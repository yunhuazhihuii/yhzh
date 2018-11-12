package com.yhzh.hxpt.abst;

import com.yhzh.hxpt.driver.IDriverEngine;
import com.yhzh.hxpt.iface.IServiceUnit;
import com.yhzh.hxpt.wsocket.WSocketManager;

/**服务单元抽象类
 * @author chenms 2018-6-26
 *
 */
public abstract class PServiceUnit implements IServiceUnit {
	
	//服务单元编号
	private String serUnitNo;
	//服务单元名称
	private String serUnitName;
	
	//状态 :0未启动；1已启动完成；
	private String state = "0";

	//驱动引擎
	private IDriverEngine driverEngine;
	
	//websocket管理
	private WSocketManager wSocketManager;
	
	//Restful管理
	
	//日程表
	
	//批量跑批

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public IDriverEngine getDriverEngine() {
		return driverEngine;
	}

	public void setDriverEngine(IDriverEngine driverEngine) {
		this.driverEngine = driverEngine;
	}

	public WSocketManager getwSocketManager() {
		return wSocketManager;
	}

	public void setwSocketManager(WSocketManager wSocketManager) {
		this.wSocketManager = wSocketManager;
	}

	public String getSerUnitNo() {
		return serUnitNo;
	}

	public void setSerUnitNo(String serUnitNo) {
		this.serUnitNo = serUnitNo;
	}

	public String getSerUnitName() {
		return serUnitName;
	}

	public void setSerUnitName(String serUnitName) {
		this.serUnitName = serUnitName;
	}
	
}
