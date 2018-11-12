package com.yhzh.hxpt.wsocket;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yhzh.hxpt.iface.IHxptSuper;
import com.yhzh.hxpt.wsocket.client.WSocketClient;

/** websocket管理类
 * @author chenms 2018-6-27
 *
 */
public class WSocketManager implements IHxptSuper{
	
	//状态 
	private String state = "0";
	
	//日志
	private Log log = LogFactory.getLog(WSocketManager.class); 
	
	//websocket客户端集合
	private List<WSocketClient> wSocketClientList;
	
	/**启动方法
	 * @return 1启动成功; 非1为启动失败原因
	 */
	public String execute(){
		log.debug("----------开始启动websocket管理器------------");
		String result = "1";
		
		//2.设置websocket管理器当前状态
		if("1".equals(result)){
			this.setState("1");//启动成功
		}

		log.debug("----------启动websocket管理器完成result：" + result + "------------");
		return result;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<WSocketClient> getwSocketClientList() {
		return wSocketClientList;
	}
	public void setwSocketClientList(List<WSocketClient> wSocketClientList) {
		this.wSocketClientList = wSocketClientList;
	}
	
}
