package com.yhzh.znzm.socketdc;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.yhzh.znzm.mqtt.client.Client;
import com.yhzh.znzm.mqtt.server.Server;

public class Init {

	public static void start() throws MqttException {
		new SocketClientdc().start();
		Server server = new Server();
		System.out.println("############启动znzm服务############");
		new Client().start();
	}

}
