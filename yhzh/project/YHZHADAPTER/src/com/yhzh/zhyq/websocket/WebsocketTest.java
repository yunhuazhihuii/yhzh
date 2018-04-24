package com.yhzh.zhyq.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

/**socket.io客户端测试
 * @author samsun
 *
 */
public class WebsocketTest extends WebSocketClient{

	public WebsocketTest( URI serverUri , Draft draft ) {  
        super( serverUri, draft );  
	} 
	
	@Override  
    public void onOpen( ServerHandshake handshakedata ) {  
       System.out.println( getDateTime() + "开流--opened connection，handshakedata：" + handshakedata);  
    }  

    @Override  
    public void onMessage( String message ) {  
       System.out.println( getDateTime() + "接收--received: " + message );  
//		   wu.send("测试--handshake");
    }  

    @Override  
    public void onFragment( Framedata fragment ) {  
       System.out.println( getDateTime() + "片段--received fragment: " + new String( fragment.getPayloadData().array() ) );  
    }  

    @Override  
    public void onClose( int code, String reason, boolean remote ) {  
       System.out.println( getDateTime() + " 关流--Connection closed by " + ( remote ? "remote peer" : "us" ) + ";reason:" + reason + ";code:" + code );  
    }  

    @Override  
    public void onError( Exception ex ) {  
       ex.printStackTrace();  
       
    }  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//       	WebSocketUtils c = new WebSocketUtils( new URI( "ws://tourace-api.iriding.cc/socket.io/?transport=websocket" ), new Draft_17() );  
//       	WebSocketUtils c = new WebSocketUtils( new URI( "ws://192.168.1.103:8000/socket.io/?transport=websocket" ), new Draft_17() );    
//       	WebSocketUtils c = new WebSocketUtils( new URI( "ws://192.168.1.123:8000/socket.io/?transport=websocket" ), new Draft_17() );     
			WebsocketTest c = new WebsocketTest( new URI( "ws://127.0.0.1:8000/socket.io/?transport=websocket" ), new Draft_17() );   
//       	WebSocketUtils c = new WebSocketUtils( new URI( "https://192.168.1.101:8000" ), new Draft_17() );  
       	
//       	c.connect();
//			System.out.println(c.connectBlocking());
			c.connect();
			System.out.println(c.getDateTime()+" 开始发送数据给客户端：42[\"my other event\",{\"server\":\"hello???\"}]");
			c.send("42[\"my other event\",{\"server\":\"hello???\"}]");
			
			//这一段陈明帅没有注释掉，。。。。。。
			/*HeadbeadTimerTask task = new HeadbeadTimerTask(c); 
	        Timer timer = new Timer();  
	        long delay = 0;   
	        // schedules the task to be run in an interval  
	        timer.scheduleAtFixedRate(task, delay, 25000);*/
			
//			String headbeatMsg = "5[\"simple\",{\"name\":\"simple\"}]";
//			c.send(headbeatMsg);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDateTime(){
	  Date date=new Date();
	  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String time=format.format(date);
	  return time;
	}

}

 class HeadbeadTimerTask1 extends TimerTask {
	private WebsocketTest wc;
	public HeadbeadTimerTask1(WebsocketTest wc){
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
		System.out.println(wc.getDateTime()+"发送心跳成功");
	}

}
