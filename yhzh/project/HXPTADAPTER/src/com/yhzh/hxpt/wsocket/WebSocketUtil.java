package com.yhzh.hxpt.wsocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yhzh.hxpt.wsocket.client.WSocketClient;

/**websocket相关工具类
 * @author chenms 2018-7-7
 *
 */
public class WebSocketUtil {

	private WebSocketDao webSocketDao;
	
	public WebSocketUtil(){
		webSocketDao = new WebSocketDao();
	}
	
	/**根据服务单元编号获取数据库的websocket客户端并转成websocket客户端对象集合返回
	 * @param serUnitNo
	 * @return
	 */
	public List<WSocketClient> getWSocketClientList(String serUnitNo){
		List<WSocketClient> wSocketClientList = new ArrayList<WSocketClient>();
		List<Map<String,Object>> list = webSocketDao.getWSocketClientList(serUnitNo);
		for(Map<String,Object> map : list){
			WSocketClient wSocketClient = new WSocketClient();
			wSocketClient.
		}
		return wSocketClientList;
	}
}
