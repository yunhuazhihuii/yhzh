package com.yhzh.znzm.socketdc;

import javax.annotation.Resource;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.yhzh.zhyq.socket.Nsocket;
import com.yhzh.znzm.mqtt.client.Client;
//import com.yhzh.znzm.mqtt.server.Server;

public class Init {

    @Resource 
	private  Nsocket nsocket;
	public  void start() throws MqttException {
   //未使用
//	new SocketClientdc().start();
	//启动znzm发送服务
	//Server server = new Server();
	
	System.out.println("############启动znzm接收服务############");
	new Client(nsocket).start();
	
	System.out.println("准备查询znzm数据");
	nsocket.loopStatusRequ();
	
	//启动接收检测机制
	Thread thread1 = new Thread(new Zmonline(nsocket), "two");
	thread1.start();
	
	 }
	}


