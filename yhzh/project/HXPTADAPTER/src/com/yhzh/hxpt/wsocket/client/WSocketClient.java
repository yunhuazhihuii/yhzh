package com.yhzh.hxpt.wsocket.client;

import java.util.Map;
import java.util.Timer;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

import com.yhzh.hxpt.iface.IHxptSuper;
import com.yhzh.hxpt.util.JsonUtil;
import com.yhzh.hxpt.wsocket.HeadbeadTimerTask;

/**websocket客户端，注意是挂在服务单元下面的
 * @author chenms 2018-6-27
 *
 */
public class WSocketClient extends WebSocketClient implements IHxptSuper{
	

	//状态 
	private String state = "0";
	
	//日志
	private Log log = LogFactory.getLog(WSocketClient.class); 
	
	//websocket客户端编码
	private String wSocketClientNo;
	
	//websocket客户端名称
	private String wSocketClientName;
	
	//websocket socket.io 事件名
	private String eventName;
	
	//websocket服务端连接地址
	private String url;

	/**启动方法
	 * @return 1启动成功; 非1为启动失败原因
	 */
	@Override
	public String execute(){
		log.debug("----------websocket客户端(" + this.getwSocketClientName() + ")开始启动------------");
		String result = "1";
		
		//2.设置WSocketClient当前状态
		if("1".equals(result)){
			this.setState("1");//启动成功
		}
		log.debug("----------websocket客户端(" + this.getwSocketClientName() + ")启动完成result：" + result + "------------");
		return result;
	}
	
	@Override
	public void onOpen(ServerHandshake handshakedata) {
		log.debug("websocket客户端(" + this.getwSocketClientName() + ")开流,连接服务端成功！");
	}

	@Override
	public void onMessage(String message) {
		log.debug("websocket客户端(" + this.getwSocketClientName() + ")接收到websocketserver信息：" + message);
		if (message.length() > 10) { // 剔除一些握手心跳等无用信息
		// 接收内容为：42["A000000001",{"callback":"45","data":"true","message":"OK"}]
		// 授权信息：:
		// 0{"sid":"npZfRe1hcBnviupvAAAB","upgrades":[],"pingInterval":25000,"pingTimeout":60000}
			String preNum = message.substring(0, message.indexOf("{"));
			if ("0".equals(preNum)) {
				log.debug("websocket客户端(" + this.getwSocketClientName() + ")接收到的为websocketserver授权信息");
				// 启动心跳定时器保持长连接
				System.out.println("开始心跳。。。。。。。。。。。" + message);
				heartbeatTimer(message);
			} else {
				String eName = message.substring(message.indexOf("\"") + 1,
						message.indexOf(",") - 1);
				log.debug("接收到websocketserver事件名：" + eName);
				if (clientId.equals(eName)) {
					try {
						String strCon = message.substring(
								message.indexOf(",") + 1,
								message.lastIndexOf("]"));
						log.debug("准备解析的json字符串为：" + strCon);
						jsonUtil = new JsonUtil(); // 依赖注入怎么还是失败？？？，初始化顺序问题
						Map<String, Object> map = jsonUtil.jsonStrToMap(strCon);
						String callback = (String) map.get("callback");
						String type = (String) map.get("type");
						// 注册后websocketserver返回的信息
						if (AdapterConstants.WEBSOCKT_REGISTER.equals(callback)) {
							if ("true".equals((String) map.get("data"))) {
								log.info("向websocketserver注册适配器成功！");

							} else {
								log.info("向websocketserver注册适配器失败："
										+ (String) map.get("message"));
							}
						}
						// 向websocketserver推送数据后返回的信息
						else if (AdapterConstants.WEBSOCKT_CLIENTSEND
								.equals(callback)) {
							if ("true".equals((String) map.get("data"))) {
								log.debug("向websocketserver推送数据成功！");
							} else {
								log.debug("向websocketserver推送数据失败："
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
						log.error("接收websocketserver异常：" + e.getMessage());
						e.printStackTrace();
					}
				} else {
					log.info("接收到websocketserver非法事件");
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
		log.debug("关流--Connection closed by " + (remote ? "remote peer" : "us")
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
	 * 心跳握手保持websocket长连接
	 */
	public void heartbeatTimer(String authorize) {
		// 0{"sid":"npZfRe1hcBnviupvAAAB","upgrades":[],"pingInterval":25000,"pingTimeout":60000}
		String data = authorize.substring(1);
//		JSONObject obj = new JSONObject(data);
//		int pingInterval = (int) obj.get("pingInterval");
		 Map<String,Object> mapAuth =
		 jsonUtil.jsonStrToMap(authorize.substring(1));
		 int pingInterval = (Integer)mapAuth.get("pingInterval"); //心跳发送时间间隔
		HeadbeadTimerTask task = new HeadbeadTimerTask(this);
		Timer timer = new Timer();
		long delay = 0;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, pingInterval);
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(String state) {
		this.state = state;
	}

	public String getwSocketClientNo() {
		return wSocketClientNo;
	}

	public void setwSocketClientNo(String wSocketClientNo) {
		this.wSocketClientNo = wSocketClientNo;
	}

	public String getwSocketClientName() {
		return wSocketClientName;
	}

	public void setwSocketClientName(String wSocketClientName) {
		this.wSocketClientName = wSocketClientName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
