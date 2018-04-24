package com.yhzh.znzm.mqtt.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;  
import org.eclipse.paho.client.mqttv3.MqttMessage;  
import com.yhzh.zhyq.socket.Nsocket;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;  
/**  
 * 发布消息的回调类  
 *   
 * 必须实现MqttCallback的接口并实现对应的相关接口方法CallBack 类将实现 MqttCallBack。  
 * 每个客户机标识都需要一个回调实例。在此示例中，构造函数传递客户机标识以另存为实例数据。
 * 在回调中，将它用来标识已经启动了该回调的哪个实例。  
 * 必须在回调类中实现三个方法：  
 *   
 *  public void messageArrived(MqttTopic topic, MqttMessage message)接收已经预订的发布。  
 *   
 *  public void connectionLost(Throwable cause)在断开连接时调用。  
 *   
 *  public void deliveryComplete(MqttDeliveryToken token))  
 *  接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。  
 *  由 MqttClient.connect 激活此回调。  
 *   
 */    
public class PushCallback implements MqttCallback {  
	//日程线程列表
	private List<Client> ClientThList;
	
    private  Nsocket nsocket; 
    public PushCallback(Nsocket nsocket){
	this.nsocket=nsocket;
}
	public static Log LOG = LogFactory.getLog(PushCallback.class); 
	  Client client;
    public void connectionLost(Throwable cause) {  
        // 连接丢失后，一般在这里面进行重连   
    	 LOG.debug("-nomore-");
    	 Client client1=this.getClientById("ZMclient");
    	 if(client1==null){
    		 LOG.debug("#########################ZMclient线程失效，尝试重新启动########################");
    		 client1.setClient_id("ZMclient");
    		 ClientThList.add(client1);
    		 client1.start();
    		 
    	 }
    }  
    
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());  
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
//    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
       
        // subscribe后得到的消息会执行到这里面   
        String obb = new String(message.getPayload());
        //System.out.println("接收消息主题 : " + topic+"接收消息Qos : " + message.getQos()+"接收消息内容 : " + obb+"时间:"+df.format(new Date())); 
        LogObb.savelog(obb);

        CheckSome checksome=new CheckSome(obb,nsocket);
        checksome.start();
    }  

	/**根据scheduleid 获取日程线程对象
	 * @param scheduleid
	 * @return
	 */
	public Client getClientById(String clientid){
		Client client1 = null;
		for(Client ClientThList : ClientThList){
			if(clientid.equals(ClientThList.getClient_id())){
				client1 = ClientThList;
				break;
			}
		}
		return client1;
	}
 
    public static void NowString() { 
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    }
    
}