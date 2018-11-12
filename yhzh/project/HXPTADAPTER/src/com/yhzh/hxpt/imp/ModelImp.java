package com.yhzh.hxpt.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yhzh.hxpt.abst.PModel;
import com.yhzh.hxpt.imp.util.ModelUtil;

/**model服务实现类
 * @author chenms 2018-7-5
 *
 */
public class ModelImp extends PModel{

	public Log log = LogFactory.getLog(ModelImp.class); 
	
	private ModelUtil modelUtil;

	/**启动方法
	 * @return 1启动成功; 非1为启动失败原因
	 */
	public String execute(){
		log.debug("----------Model(" + this.getModelName() + ")开始启动------------");
		String result = "1";
		modelUtil = new ModelUtil();
		List<ServiceUnitImp> serviceUnitList = modelUtil.getSerUnitListFromDb(this.getModelNo());
		//1.循环启动该model下所有服务单元
		for(ServiceUnitImp serviceUnit: serviceUnitList){
			result = serviceUnit.execute();
			if(!"1".equals(result)){
				//待补充
				result = "服务单元(" + serviceUnit.getSerUnitName() + ")启动失败：" + result;
				//启动失败直接退出
				break;
			}
		}
		//2.设置model当前状态
		if("1".equals(result)){
			this.setState("1");//启动成功
		}
		log.debug("----------Model(" + this.getModelName() + ")启动结束result：" + result + "------------");
		return result;
	}
}
