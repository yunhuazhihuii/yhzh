package com.yhzh.test;
//package com.yhzh.znzm.socketdc;
//
//import org.eclipse.paho.client.mqttv3.MqttException;
//
//import com.yhzh.znzm.mqtt.server.Server;
//
//public class Test {
//	static String close0 = "{\"act\":\"control\",\"data\":{\"type\":\"mode\",\"areaCode\":1,\"modeCode\":1}}";
//	static String close1 = "{\"act\":\"control\",\"data\":{\"type\":\"mode\",\"areaCode\":2,\"modeCode\":1}}";
//	static String close2 = "{\"act\":\"control\",\"data\":{\"type\":\"mode\",\"areaCode\":3,\"modeCode\":1}}";
//	static String close3 = "{\"act\":\"control\",\"data\":{\"type\":\"mode\",\"areaCode\":4,\"modeCode\":1}}";
//	static String open0 = "{\"act\":\"control\",\"data\":{\"type\":\"mode\",\"areaCode\":1,\"modeCode\":2}}";
//	static String open1 = "{\"act\":\"control\",\"data\":{\"type\":\"mode\",\"areaCode\":2,\"modeCode\":2}}";
//	static String open2 = "{\"act\":\"control\",\"data\":{\"type\":\"mode\",\"areaCode\":3,\"modeCode\":2}}";
//	static String open3 = "{\"act\":\"control\",\"data\":{\"type\":\"mode\",\"areaCode\":4,\"modeCode\":2}}";
//
//	public static void main(String[] args) throws MqttException {
//		Server.open(close0);
//		Server.open2(close0);
//		Server.open3(close0);
//		Server.open4(close0);
//		Server.open4(close1);
//		Server.open5(close0);
//		Server.open6(close0);
//		Server.open5(close1);
//		Server.open6(close1);
//		Server.open5(close2);
//		Server.open6(close2);
//		Server.open5(close3);
//		Server.open6(close3);
//
//		Server.open(open0);
//		Server.open2(open0);
//		Server.open3(open0);
//		Server.open4(open0);
//		Server.open4(open1);
//		Server.open5(open0);
//		Server.open6(open0);
//		Server.open5(open1);
//		Server.open6(open1);
//		Server.open5(open2);
//		Server.open6(open2);
//		Server.open5(open3);
//		Server.open6(open3);
//
//	}
//
//	public static void openb1L() throws MqttException {
//		Server.open(open0);
//		Server.open2(open0);
//		Server.open3(open0);
//
//	}
//
//	public static void open1L() throws MqttException {
//		Server.open4(open0);
//		Server.open5(open0);
//		Server.open6(open0);
//
//	}
//
//	public static void open2L() throws MqttException {
//		Server.open4(open1);
//		Server.open5(open1);
//		Server.open6(open1);
//	}
//
//	public static void open3L() throws MqttException {
//		Server.open5(open2);
//		Server.open6(open2);
//	}
//
//	public static void open4L() throws MqttException {
//		Server.open5(open3);
//		Server.open6(open3);
//
//	}
//	public static void close4L() throws MqttException {
//		Server.open5(close3);
//		Server.open6(close3);
//
//	}
//
//	public static void close3L() throws MqttException {
//		Server.open5(close2);
//		Server.open6(close2);
//	}
//
//	public static void close2L() throws MqttException {
//		Server.open4(close1);
//		Server.open5(close1);
//		Server.open6(close1);
//	}
//
//	public static void close1L() throws MqttException {
//		Server.open4(close0);
//		Server.open5(close0);
//		Server.open6(close0);
//	}
//
//	public static void closeb1L() throws MqttException {
//		Server.open(close0);
//		Server.open2(close0);
//		Server.open3(close0);
//	}
//
//}