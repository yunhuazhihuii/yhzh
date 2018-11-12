package com.yhzh.hxpt.wsocket;

import java.util.TimerTask;

import com.yhzh.hxpt.wsocket.client.WSocketClient;

/** 心跳握手保持websocket长连接 任务
 * @author chenms 2018-1-7
 *
 */
public class HeadbeadTimerTask extends TimerTask {
	
	private WSocketClient wc;
	
	public HeadbeadTimerTask(WSocketClient wc){
		this.wc = wc;
	}
	
	@Override
	public void run() {
		String headbeatMsg = "5[\"simple\",{\"name\":\"simple\"}]";
		wc.send(headbeatMsg);
//		 Date date = new Date(System.currentTimeMillis());
//			System.out.println("发送心跳成功"+"时间是:"+date);
	}

}
