package com.yhzh.zhyq.socket;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yhzh.util.JsonUtil;

/**连接到socket server的socket客户端线程
 * @author chenmingshuai
 * 2017-11-9
 */
public class SocketSerClientThread extends Thread{

	private SocketClient socketClient = null;

//	@Resource  //注解注入，这个可以注入，而Autowire不行,非单例，不能用注入
	private JsonUtil jsonUtil = new JsonUtil();
	
	public static Log LOG = LogFactory.getLog(SocketSerClientThread.class); 
	
	public SocketSerClientThread(SocketClient sc){
		this.socketClient = sc;
	}
	
	
	public void run() {
		LOG.debug("---启动socket客户端监听线程---");
		while (socketClient.isbConnected()) {
			//监听socket客户端发送过来的内容
			String strRec = socketClient.read();
			LOG.debug("接收到socket客户端发送过来的内容:" + strRec);
			if("{".equals(strRec.substring(0, 1))){
				Map<String,Object> mapRes = jsonUtil.jsonStrToMap(strRec);
				//智能照明驱动向主适配器发送数据
				if("update".equals((String)mapRes.get("act"))){
					//现在先默认都是回路数据
					/*{"act":"update","content":{"type":"cellDimmer","mac":"000001","data":[
						{"ModuleMac":"1","cellIndex":"1","value":"1"},
						{"ModuleMac":"1","cellIndex":"2","value":"0"},
						{"ModuleMac":"2","cellIndex":"1","value":"1"},
						…
						{"ModuleMac":"5","cellIndex":"5","value":"1"}
						]}}
					服务端返回：{'act':'update','succcess':'0','message':''}
					*/
					//转发数据给websocket服务器
					LOG.debug("接收到内容为智能照明驱动发过来的数据");
					boolean bRst = socketClient.reciLightData(mapRes);
				}else if("control".equals((String)mapRes.get("act"))){
					LOG.debug("接收到内容为智能照明驱动发过来的控制命令回馈");
					boolean bRst = socketClient.reciLightConBak(mapRes);
				}
			}
			
		}
	} 
}
