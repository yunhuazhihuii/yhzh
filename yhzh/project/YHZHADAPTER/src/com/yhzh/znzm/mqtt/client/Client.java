package com.yhzh.znzm.mqtt.client;

import java.util.concurrent.Executors;  
import java.util.concurrent.ScheduledExecutorService;  
import java.util.concurrent.TimeUnit;  

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;  
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;  
import org.eclipse.paho.client.mqttv3.MqttException;  
import org.eclipse.paho.client.mqttv3.MqttSecurityException;  
import org.eclipse.paho.client.mqttv3.MqttTopic;  
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.yhzh.zhyq.socket.Nsocket;
  
public class Client  extends Thread{  
	public static Log LOG = LogFactory.getLog(Client.class); 
  
	public static final String HOST = "tcp://127.0.0.1:1883";  
	public static final String TOPIC = "DYCTRLUP";  
    private static final String clientid = "ZMclient";  
    private String client_id= "ZMclient";
    private MqttClient client;  
    private MqttConnectOptions options;   
    private String userName = "admin";
    private String passWord = "admin";
    private ScheduledExecutorService scheduler;  
    
    
    private  Nsocket nsocket; 
    public Client(Nsocket nsocket){
	this.nsocket=nsocket;
}
    
    public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	//重新链接  
    public void startReconnect() {  
        scheduler = Executors.newSingleThreadScheduledExecutor();  
        scheduler.scheduleAtFixedRate(new Runnable() {  
            public void run() {  
                if (!client.isConnected()) {  
                    try {  
                    	LOG.debug("#########################options进行zmclient重新连接########################");
                        client.connect(options);  
                    } catch (MqttSecurityException e) {  
                        e.printStackTrace();  
                    } catch (MqttException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);  
    }  
  
    public void run(){  
    	//while(true){}

	     try {  
	            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存  
	            client = new MqttClient(HOST, clientid, new MemoryPersistence());  
	            // MQTT的连接设置  
	            options = new MqttConnectOptions();  
	            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接  
	            options.setCleanSession(true);  
	            // 设置连接的用户名  
	            options.setUserName(userName);  
	            // 设置连接的密码  
	            options.setPassword(passWord.toCharArray());  
	            // 设置超时时间 单位为秒  
	            options.setConnectionTimeout(10);  
	            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制  
	            options.setKeepAliveInterval(20);  
	            // 设置回调  
	            client.setCallback(new PushCallback(nsocket));  
	            MqttTopic topic = client.getTopic(TOPIC);  
	            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息    
	            options.setWill(topic, "close".getBytes(), 2, true);  
	              
	            client.connect(options);  
	            //订阅消息  
	            int[] Qos  = {1};  
	            String[] topic1 = {TOPIC};  
	            client.subscribe(topic1, Qos);  

	            
	        } catch (Exception e) {  
	        	e.printStackTrace();
	        	System.out.println("znzmClient异常："+e);
	             
	        } 
	
    
    } 
    public void disconnect() {  
        try {  
        	LOG.debug("#########################进行zmclient重新连接########################");
           client.disconnect();  
       } catch (MqttException e) {  
           e.printStackTrace();  
       }  
   }  
    
   
  
	public static void main(String[] args) throws MqttException {     

        LOG.debug("#########################运行智能照明接收程序成功！########################");
    }  
}