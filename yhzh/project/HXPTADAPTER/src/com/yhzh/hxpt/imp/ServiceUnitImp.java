package com.yhzh.hxpt.imp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yhzh.hxpt.abst.PServiceUnit;

/**服务单元实现类
 * @author chenms 2018-7-5
 *
 */
public class ServiceUnitImp extends PServiceUnit {
	
	//日志
	private Log log = LogFactory.getLog(ServiceUnitImp.class); 

	/**启动方法
	 * @return 1启动成功; 非1为启动失败原因
	 */
	public String execute(){
		log.debug("----------服务单元(" + this.getSerUnitName() + ")开始启动------------");
		String result = "1";
		//1.启动websocket管理
		result = this.getwSocketManager().execute();
		if(!"1".equals(result)){
			//启动websocket管理失败，退出该服务单元启动
			result = "websocket管理启动失败: " + result;
			//退出启动，不再启动后面的内容
			return result;
		}
		
		//2.设置服务单元当前状态
		if("1".equals(result)){
			this.setState("1");//启动成功
		}
		log.debug("----------服务单元(" + this.getSerUnitName() + ")启动结束result：" + result + "------------");
		return result;
	}
}
