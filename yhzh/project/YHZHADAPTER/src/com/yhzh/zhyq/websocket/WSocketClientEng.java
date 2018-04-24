package com.yhzh.zhyq.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import com.yhzh.bacnet.BacnetManager;
import com.yhzh.pub.bean.PointBean;
import com.yhzh.util.AdapterConstants;
import com.yhzh.util.JsonUtil;
import com.yhzh.zhyq.dao.YhzhDao;
import com.yhzh.zhyq.socket.Nsocket;
//import com.yhzh.zhyq.socket.ServerSocketThread;
//import com.yhzh.zhyq.socket.SocketClient;

public class WSocketClientEng extends WebSocketClient {

	public static Log LOG = LogFactory.getLog(WSocketClientEng.class);
	// 如下配置需写在配置文件中
	private String eventName; // 发送事件名，即socket.io的监听事件名
	private String clientId;
	@Resource
	// 非单例不能用注入
	YhzhDao yhzhDao;
	private String clientName;
	@Resource
	Nsocket nsocket;
	@Resource
	// 注解注入
	private JsonUtil jsonUtil; // json解析

//	@Resource
//	// 注解注入
//	private ServerSocketThread serverSocketThread;

	@Resource
	// 注解注入
	private BacnetManager bacnetManager;

	public WSocketClientEng(String uri, String clientId, String clientName,
			String eventName) throws URISyntaxException, InterruptedException {
		super(new URI(uri), new Draft_17());
		// 如下配置需写在配置文件中
		this.eventName = eventName;
		this.clientId = clientId;
		this.clientName = clientName;
		// 本地测试暂时注释
		if (this.connectBlocking()) {
			LOG.info("-------与websocketserver连接成功--------");
		} else {
			LOG.info("-------与websocketserver连接失败--------");
		}
		this.registerAdapter(clientId, clientName,
				AdapterConstants.WEBSOCKT_REGISTER);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		System.out.println("开流--opened connection");
	}

	@Override
	public void onMessage(String message) {
		LOG.debug("接收到websocketserver信息：" + message);
		if (message.length() > 10) { // 剔除一些握手心跳等无用信息
		// 接收内容为：42["A000000001",{"callback":"45","data":"true","message":"OK"}]
		// 授权信息：:
		// 0{"sid":"npZfRe1hcBnviupvAAAB","upgrades":[],"pingInterval":25000,"pingTimeout":60000}
			String preNum = message.substring(0, message.indexOf("{"));
			if ("0".equals(preNum)) {
				LOG.debug("接收到的为websocketserver授权信息");
				// 启动心跳定时器保持长连接
				System.out.println("开始心跳。。。。。。。。。。。" + message);
				heartbeatTimer(message);
			} else {
				String eName = message.substring(message.indexOf("\"") + 1,
						message.indexOf(",") - 1);
				LOG.debug("接收到websocketserver事件名：" + eName);
				if (clientId.equals(eName)) {
					try {
						String strCon = message.substring(
								message.indexOf(",") + 1,
								message.lastIndexOf("]"));
						LOG.debug("准备解析的json字符串为：" + strCon);
						jsonUtil = new JsonUtil(); // 依赖注入怎么还是失败？？？，初始化顺序问题
						Map<String, Object> map = jsonUtil.jsonStrToMap(strCon);
						String callback = (String) map.get("callback");
						String type = (String) map.get("type");
						// 注册后websocketserver返回的信息
						if (AdapterConstants.WEBSOCKT_REGISTER.equals(callback)) {
							if ("true".equals((String) map.get("data"))) {
								LOG.info("向websocketserver注册适配器成功！");

							} else {
								LOG.info("向websocketserver注册适配器失败："
										+ (String) map.get("message"));
							}
						}
						// 向websocketserver推送数据后返回的信息
						else if (AdapterConstants.WEBSOCKT_CLIENTSEND
								.equals(callback)) {
							if ("true".equals((String) map.get("data"))) {
								LOG.debug("向websocketserver推送数据成功！");
							} else {
								LOG.debug("向websocketserver推送数据失败："
										+ (String) map.get("message"));
							}
						}
						// websocketserver订阅接口
						if ("rtsub".equals(type)) {
							this.reciRtsub(map);
						}
						// websocketserver控制接口
						else if ("serverSend".equals(type)) {
							this.reciWServerCont(map);
						}
					} catch (Exception e) {
						LOG.error("接收websocketserver异常：" + e.getMessage());
						e.printStackTrace();
					}
				} else {
					LOG.info("接收到websocketserver非法事件");
				}
			}
		}
	}

	@Override
	public void onFragment(Framedata fragment) {
		System.out.println("片段--received fragment: "
				+ new String(fragment.getPayloadData().array()));
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		LOG.debug("关流--Connection closed by " + (remote ? "remote peer" : "us")
				+ "。code:" + code + ";reason:" + reason + ";remote:" + remote);
		System.out.println("关流--Connection closed by "
				+ (remote ? "remote peer" : "us") + "。code:" + code
				+ ";reason:" + reason + ";remote:" + remote);
	}

	@Override
	public void onError(Exception ex) {
		ex.printStackTrace();

	}

	/**
	 * 向websocketserver注册适配器
	 * 
	 * @param adapterId
	 * @param adapterName
	 */
	public void registerAdapter(String clientId, String clientName,
			String callback) {
		String regStr = "42[\"" + eventName
				+ "\",{\"type\":\"register\",\"content\":"
				+ "{\"data\":{\"clientId\":\"" + clientId
				+ "\",\"adapterName\":\"" + clientName + "\"},\"callback\":\""
				+ callback + "\"}}]";
		LOG.info("---开始向websocketserver注册适配器---");
		LOG.debug("向websocketserver发送：" + regStr);
		this.send(regStr);

	}

	/**
	 * 接收websocketserver订阅接口
	 * 
	 * @param map
	 */
	public void reciRtsub(Map<String, Object> map) {
		// String str = "{\"type\":\"rtsub\", \"content\":{" +
		// "\"subscribeSubitem\":[\"adapter1.controller1\",\"adapter1.controller2\",\"adapter2.controller1\",\"adapter2.controller2\"]},"
		// +
		// "\"callback\": 21}";
		// 返回接收订阅成功
		this.sendRtsubBak("true", "");
		// 向驱动请求数据发送给websocketserver
		Map<String, Object> mapCon = (Map<String, Object>) map.get("content");
		List<String> list = (List<String>) mapCon.get("subscribeSubitem");
		// 先不做组优化，一个个回路发
		for (int i = 0; i < list.size(); i++) {
			String points = (String) list.get(i);
			String[] pointArr = points.split("."); // 适配器id.驱动id.点编号
			// 获取驱动对应的socket客户端连接
			String sClientId = pointArr[1];
			
//			SocketClient sc = serverSocketThread.getSocketClient(sClientId);
			
			
		}
	}

	/**
	 * 接收到订阅接口后，返回信息给websocketserver
	 * 
	 * @param data
	 * @param message
	 * @return
	 */
	public boolean sendRtsubBak(String data, String message) {
		boolean bRst = false;
		String str = "42[\"" + eventName + "\",{\"callback\":\""
				+ AdapterConstants.WEBSOCKT_RTSUB + "\",\"data\":\"" + data
				+ "\"," + "\"message\":\"" + message + "\"}]";
		LOG.info("---接收到订阅接口后，回馈信息给websocketserver---");
		LOG.debug("向websocketserver发送接收到订阅接口后的回馈：" + str);
		this.send(str);
		bRst = true;
		return bRst;
	}

	/**
	 * 发送数据给websocketserver
	 * 
	 * @param pointList
	 *            点的集合
	 * @return
	 */
	public boolean sendDataToWServer(List<PointBean> pointList) {
		boolean bRst = false;
		String sData = "[";
		for (PointBean point : pointList) {
			String pointId = point.getPointId();
			// 将 BA数据保存到数据库
			yhzhDao.setpointValuebypointid(pointId, point.getPointValue());
			String ioproperty = "";
			switch (point.getIoProperty()) {
			case "1":
				ioproperty = "DI";
				break;
			case "2":
				ioproperty = "DO";
				break;
			case "3":
				ioproperty = "AV";
				break;
			case "4":
				ioproperty = "AI";
				break;
			case "5":
				ioproperty = "AO";
				break;
			case "6":
				ioproperty = "BV";
				break;

			default:
				break;
			}
			pointId = this.getClientId() + "." + point.getControllerid() + "."
					+ pointId; // 适配器编号.控制器编号.点编号
			sData += "{\"name\":\"" + pointId + "\",\"value\":\""
					+ point.getPointValue() + "\",\"ioproperty\":\""
					+ ioproperty + "\"},";
		}
		sData = sData.substring(0, sData.length() - 1);
		sData += "]";
		bRst = sendDataToWServer(sData);
		return bRst;
	}

	/**
	 * 发送数据给websocketserver
	 * 
	 * @param data
	 * @return
	 */
	public boolean sendDataToWServer(String data) {
		boolean bRst = false;
		/*
		 * "type":"clientSend", "content":{ "clientId":"H000000001",
		 * "sourcetype":"adapter",
		 * "data":[{"name":点名1,"value":值},{"name":点名1,"value":值},...],
		 * "callback": "21" }' S -> C: { "callback":"21", "data":"true",
		 * "message":"" }
		 */
		String str = "42[\"" + eventName + "\",{" + "\"type\":\"clientSend\","
				+ "\"content\":{\"clientId\":\"" + this.clientId
				+ "\",\"sourcetype\":\"adapter\",\"data\":" + data + "}" + "}]";
		// LOG.debug("开始向websocketserver发送数据：" + str);
		this.send(str);
		LOG.debug("向websocketserver发送数据完成。");
		bRst = true;
		return bRst;
	}

	/**
	 * 获取适配器编号
	 * 
	 * @return
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * 接收websocketserver控制接口
	 * 
	 * @param map
	 * @return
	 */
	public boolean reciWServerCont(Map<String, Object> map) {
		boolean bRst = false;
		/*
		 * S-> C: { "type": "serverSend", "content": { "desttype": "adapter",
		 * "data": [ { "name": "点名1", "value": "值" }, { "name": "点名1", "value":
		 * "值" } ], "callback": "21" }, "callbackid": "22" } C -> S: {
		 * "callback": "21", "data": "true", "message": "Text" }
		 */
		Map<String, Object> mapCon = (Map<String, Object>) map.get("content");
		String callback = (String) mapCon.get("callback");
		List<Map<String, Object>> listData = (List<Map<String, Object>>) mapCon
				.get("data");
		// 这里默认所有数据点名中驱动号一致
		Map<String, Object> mapData0 = listData.get(0);
		String points = (String) mapData0.get("name");
		String[] pointArr = points.split("\\."); // 适配器id.驱动id.点编号
		// 获取驱动对应的socket客户端连接
		String sClientId = pointArr[1];
//	SocketClient sc = serverSocketThread.getSocketClient(sClientId);
		boolean bSend = false;
		//if (null != sc) {
		if (sClientId.equals("000001")){	
		//bSend = sc.sendConToLigthEng(listData);
			nsocket.sendConToLigthEng(listData);
		} else {
			// 说明是ba的
			for (Map<String, Object> mapP : listData) {
				String pointNames = (String) mapP.get("name");
				String value = (String) mapP.get("value");
				String[] point_arr = pointNames.split("\\."); // 适配器id.驱动id.点编号
				String controllerId = point_arr[1];
				String pointId = point_arr[2]; // 点编号
				bSend = bacnetManager.writePoint(controllerId, pointId, value);
			}
		}
		// 回馈信息给websocketserver
		String str = "42[\"" + eventName + "\",{\"callback\":\"" + callback
				+ "\",\"data\":\"" + bSend + "\"," + "\"message\":\"\"}]";
		LOG.info("---接收到控制接口后，回馈信息给websocketserver---");
		LOG.debug("向websocketserver发送接收到控制接口后的回馈：" + str);
		this.send(str);
		return bRst;
	}

	/**
	 * 心跳握手保持websocket长连接
	 */
	public void heartbeatTimer(String authorize) {
		// 0{"sid":"npZfRe1hcBnviupvAAAB","upgrades":[],"pingInterval":25000,"pingTimeout":60000}
		String data = authorize.substring(1);
		JSONObject obj = new JSONObject(data);
		int pingInterval = (int) obj.get("pingInterval");
		// Map<String,Object> mapAuth =
		// jsonUtil.jsonStrToMap(authorize.substring(1));
		// int pingInterval = (Integer)mapAuth.get("pingInterval"); //心跳发送时间间隔
		HeadbeadTimerTask task = new HeadbeadTimerTask(this);
		Timer timer = new Timer();
		long delay = 0;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, pingInterval);
	}
}