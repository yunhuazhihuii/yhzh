package com.yhzh.hxpt.wsocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yhzh.hxpt.dao.BaseDao;
import com.yhzh.hxpt.spring.SpringBeanManager;

/**WebSocket相关数据库操作
 * @author chenms 2018-7-6
 *
 */
public class WebSocketDao {

private BaseDao baseDao;
	
	public WebSocketDao(){
		baseDao = (BaseDao)SpringBeanManager.getInstance().getSpringBean("baseDao");
	}
	
	/**根据服务单元编码从数据库获取WebSocket客户单列表
	 * @param serUnitNo
	 * @return
	 */
	public List<Map<String,Object>> getWSocketClientList(String serUnitNo){
		List<Map<String,Object>> listRst = null;	
		/*  服务编码（应该为服务单元编码）		F1
			websocket单元编码		F2
			websocket单元名称		F3
			websocket单元描述		F4
			是否有效		F5
			事件名	socket.io的监听事件名	F6
			服务端url地址	websocket服务端url地址	F7
		 */
		String sql = "select t.F2,t.F3,t.F6,t.F7 from S1150000 t wheret.F1=:serUnitNo and t.F5='1'";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("serUnitNo", serUnitNo);
		listRst = baseDao.queryForList(sql, paramMap);
		return listRst;
	}
}
