package com.yhzh.znzm.mqtt.server;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.yhzh.zhyq.socket.Nsocket;
//import com.yhzh.zhyq.socket.SocketClient;
import com.yhzh.znzm.mqtt.client.LogObb;
import com.yhzh.znzm.mqtt.client.PushCallback;

/**
 * 
 * Title:Server Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 * 
 * @author chenrl 2016年1月6日下午3:29:28
 */
public class Server {
	static LogObb logbb;
	public static final String HOST = "tcp://127.0.0.1:1883";
	public static final String TOPICzm = "DYCTRLUP";
	
	public static final String TOPICone = "DYCTRLDOWN/0011c1c8016c";
	public static final String TOPICtwo = "DYCTRLDOWN/0011c1c8016e";
	public static final String TOPICthree ="DYCTRLDOWN/0011c1c8016f";
	public static final String TOPICFOUR = "DYCTRLDOWN/0011c1d00171";
	public static final String TOPICFIVE = "DYCTRLDOWN/0011c1c8016b";
	public static final String TOPICsix = "DYCTRLDOWN/4011c1d0021a";
	private static final String clientid = "server";
    private  Nsocket nsocket; 

	private MqttClient client;
	private MqttTopic topiczm;
	private MqttTopic topic1;
	private MqttTopic topic2;
	private MqttTopic topic3;
	private MqttTopic topic4;
	private MqttTopic topic5;
	private MqttTopic topic6;
	private String userName = "admin";
	private String passWord = "admin";

	private MqttMessage message;

	public Server() throws MqttException {
		// MemoryPersistence设置clientid的保存形式，默认为以内存保存
		client = new MqttClient(HOST, clientid, new MemoryPersistence());
		connect();
	}

	private void connect() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(false);
		options.setUserName(userName);
		options.setPassword(passWord.toCharArray());
		// 设置超时时间
		options.setConnectionTimeout(30);
		// 设置会话心跳时间
		options.setKeepAliveInterval(20);
		try {
			client.setCallback(new PushCallback(nsocket));
			client.connect(options);
			topiczm = client.getTopic(TOPICzm);
			topic1 = client.getTopic(TOPICone);
			topic2 = client.getTopic(TOPICtwo);
			topic3 = client.getTopic(TOPICthree);
			topic4 = client.getTopic(TOPICFOUR);
			topic5 = client.getTopic(TOPICFIVE);
			topic6 = client.getTopic(TOPICsix);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void publish(MqttTopic topic, MqttMessage message)
			throws MqttPersistenceException, MqttException {
		MqttDeliveryToken token = topic.publish(message);
		token.waitForCompletion();
		System.out.println("message is published completely! "
				+ token.isComplete());
	}

	public static void main(String[] args) throws MqttException {

	}

	public static void open(String str1) throws MqttException {
		Server server = new Server();
		server.message = new MqttMessage();
		server.message.setQos(2);
		server.message.setRetained(true);
		server.message.setPayload(str1.getBytes());
		server.publish(server.topic1, server.message);
		try {
			logbb.controllog("OPEN"+str1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void open2(String str1) throws MqttException {
		Server server = new Server();
		server.message = new MqttMessage();
		server.message.setQos(2);
		server.message.setRetained(true);
		server.message.setPayload(str1.getBytes());
		server.publish(server.topic2, server.message);
		try {
			logbb.controllog("OPEN2"+str1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void open3(String str1) throws MqttException {
		Server server = new Server();
		server.message = new MqttMessage();
		server.message.setQos(2);
		server.message.setRetained(true);
		server.message.setPayload(str1.getBytes());
		server.publish(server.topic3, server.message);
		try {
			logbb.controllog("OPEN3"+str1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void open4(String str1) throws MqttException {
		Server server = new Server();
		server.message = new MqttMessage();
		server.message.setQos(2);
		server.message.setRetained(true);
		server.message.setPayload(str1.getBytes());
		server.publish(server.topic4, server.message);	
		try {
			logbb.controllog("OPEN4"+str1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void open5(String str1) throws MqttException {
		Server server = new Server();
		server.message = new MqttMessage();
		server.message.setQos(2);
		server.message.setRetained(true);
		server.message.setPayload(str1.getBytes());
		server.publish(server.topic5, server.message);
		try {
			logbb.controllog("OPEN5"+str1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void open6(String str1) throws MqttException {
		Server server = new Server();
		server.message = new MqttMessage();
		server.message.setQos(2);
		server.message.setRetained(true);
		server.message.setPayload(str1.getBytes());
		server.publish(server.topic6, server.message);
		try {
			logbb.controllog("OPEN6"+str1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}