package com.yhzh.hxpt.abst;

import java.util.List;

import com.yhzh.hxpt.iface.IModel;
import com.yhzh.hxpt.imp.ServiceUnitImp;

/**模型服务抽象类
 * 待后面扩展：集群
 * @author chenms 2018-6-2
 *
 */
public abstract class PModel implements IModel{
	
	//服务编号
	private String modelNo;
	//模型服务名称
	private String modelName;
	//IPV4
	private String IPV4;
	//端口
	private String port;
	//URL地址,
	/*目前与适配器直连的有：1.驱动，通过方法直连直接调用；
	 * 					   2.websocket服务，通过特定url，但是websocket服务的url
	 * 					   3.界面，通过rest接口通信，要本模型服务的url*/
	private String URL;
	//注册码 ，暂时用不到-------
	private String registCode;
	
	//服务单元列表
	private List<ServiceUnitImp> serUnitList;
	
	//状态 
	private String state = "0";

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getIPV4() {
		return IPV4;
	}

	public void setIPV4(String iPV4) {
		IPV4 = iPV4;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getRegistCode() {
		return registCode;
	}

	public void setRegistCode(String registCode) {
		this.registCode = registCode;
	}

	public List<ServiceUnitImp> getSerUnitList() {
		return serUnitList;
	}

	public void setSerUnitList(List<ServiceUnitImp> serUnitList) {
		this.serUnitList = serUnitList;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
