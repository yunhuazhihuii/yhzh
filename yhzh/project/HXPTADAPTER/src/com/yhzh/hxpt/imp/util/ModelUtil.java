package com.yhzh.hxpt.imp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.yhzh.hxpt.imp.ServiceUnitImp;
import com.yhzh.hxpt.imp.dao.ModelDao;

/**model工具类
 * @author chenms 2018-7-7
 *
 */
public class ModelUtil {

	private ModelDao modelDao;

	//日志
	public Log log = LogFactory.getLog(ModelUtil.class); 
	
	public ModelUtil(){
		modelDao = new ModelDao();
	}
	
	/**将数据库的服务单元集合转成服务单元对象集合返回
	 * @param modelNo
	 * @return
	 */
	public List<ServiceUnitImp> getSerUnitListFromDb(String modelNo){
		List<ServiceUnitImp> listRst = new ArrayList<ServiceUnitImp>();
		List<Map<String,Object>> listDb = modelDao.getDBSerUnitList(modelNo);
		for(Map<String,Object> map: listDb){
			ServiceUnitImp serviceUnit = new ServiceUnitImp();
			serviceUnit.setSerUnitNo((String)map.get("F2")); 
			serviceUnit.setSerUnitName((String)map.get("F3")); 
			listRst.add(serviceUnit);
		}
		return listRst;
	}
	
}
