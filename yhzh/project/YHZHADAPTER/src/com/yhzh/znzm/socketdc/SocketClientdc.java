package com.yhzh.znzm.socketdc;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.security.ntlm.Client;
import com.yhzh.zhyq.websocket.WSocketClientEng;
import com.yhzh.znzm.mqtt.server.Json;

/**socket客户端线程测试 陈明帅 2017-11-16
 */
public class SocketClientdc extends Thread{

	Socket sk = null;  
    BufferedReader reader = null;  
    static PrintWriter wtr = null;  
    BufferedReader keyin = null;  
    String get = "";  
	public static Log LOG = LogFactory.getLog(WSocketClientEng.class); 
   
    public SocketClientdc()  
    {  
//        keyin = new BufferedReader(new InputStreamReader(System.in));  
        try  
        {  
          //  sk = new Socket("192.168.1.101", 16888);  
        	sk = new Socket("172.16.8.2", 16888);
            //注册
            get = "{\"act\":\"register\",\"content\":{\"clientId\":\"000001\",\"name\":\"znzm\"}}";
            wtr = new PrintWriter(sk.getOutputStream());
            this.sentMsgToSserver(get);
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
  
    }  
  
    public void run()  
    {  
        try  
        {  
            reader = new BufferedReader(new InputStreamReader(sk.getInputStream(),"UTF-8"));  
            int i = 0;
            while (true)  
            {  
                if (reader != null)  
                {  
                    String line = reader.readLine();  
                    LOG.debug("从适配器来的信息：" + line);
                 
                    Json.setvalue(line);
                }  

                sleep(3000);  
            }  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
    
    /**发送数据给socketserver
     * @param str
     * @return
     */
    public static boolean sentMsgToSserver(String str){
    	boolean bRst = false;
        System.out.println("开始发送数据给适配器：" + str);
    	wtr.println(str);   
        wtr.flush(); 
      //  System.out.println("发送完成");
    	return bRst;
    }
    
  
    public static void main(String [] args)  
    {  
        new SocketClientdc().start();
        
        
    }  

}
