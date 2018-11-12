package com.yhzh.hxpt.imp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yhzh.hxpt.dao.BaseDao;
import com.yhzh.hxpt.spring.SpringBeanManager;

/**模型服务数据库操作dao
 * @author chenms 2018-7-6
 *
 */
public class ModelDao {

	private BaseDao baseDao;

	public Log log = LogFactory.getLog(ModelDao.class); 
	
	public ModelDao(){
		baseDao = (BaseDao)SpringBeanManager.getInstance().getSpringBean("baseDao");
	}
	
	/**根据模型服务编号获取其下所有服务单元
	 * @param modelNo
	 * @return
	 */
	public List<Map<String,Object>> getDBSerUnitList(String modelNo){
		List<Map<String,Object>> listRst = null;	
		/*服务编号	F1	varchar(12)
		服务单元编码	F2	varchar(12)
		服务单元名称	F3	varchar(30)
		服务单元说明	F4	varchar(120)
		报警主题编码	F5	varchar(3)
		报警主题名称	F6	varchar(30)
		驱动jar包文件名	F7	varchar(30)
		接口方法名	F8	varchar(20)
		创建时间	F9	datetime
		是否有效	F10	varchar(1)
		 */
		String sql = "select t.F2,t.F3 from S1100000 t where t.F1=:modelNo and t.F10='1'";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("modelNo", modelNo);
		listRst = baseDao.queryForList(sql, paramMap);
		return listRst;
	}
	
}
