package com.yhzh.zhyq.websocket;


import java.util.Date;
import java.util.TimerTask;

/** 心跳握手保持websocket长连接 任务
 * @author samsun 2018-1-7
 *
 */
public class HeadbeadTimerTask extends TimerTask {
	private WSocketClientEng wc;
	public HeadbeadTimerTask(WSocketClientEng wc){
		this.wc = wc;
	}
	
	/*private WebsocketTest wc;
	public HeadbeadTimerTask(WebsocketTest wc){
		this.wc = wc;
	}*/
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String headbeatMsg = "5[\"simple\",{\"name\":\"simple\"}]";
		wc.send(headbeatMsg);
		 Date date = new Date(System.currentTimeMillis());
//			System.out.println("发送心跳成功"+"时间是:"+date);
	}

}
