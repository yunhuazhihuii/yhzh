package com.yhzh.zhyq.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**socket客户端线程测试 陈明帅 2017-11-16
 */
public class SocketClientThTest extends Thread{

	Socket sk = null;  
    BufferedReader reader = null;  
    PrintWriter wtr = null;  
    BufferedReader keyin = null;  
    String get = "";  
  
    public SocketClientThTest()  
    {  
//        keyin = new BufferedReader(new InputStreamReader(System.in));  
        try  
        {  
            sk = new Socket("127.0.0.1", 16888);  
            get = "{'type':'register','content':{'clientId':'000001','name':'znzm'}}";
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
            reader = new BufferedReader(new InputStreamReader(sk  
                    .getInputStream(),"UTF-8"));  
            wtr = new PrintWriter(sk.getOutputStream());
            int i = 0;
            while (true)  
            {  
                if (get != null & get.length() > 0)  
                {  
                    System.out.println("准备发送数据给服务端："+get);
                    wtr.println(get);  
                    wtr.flush();  
                    System.out.println("发送完成");
//                  wtr.close();  
//                  System.out.println(get + "发送完毕");  
                }  
                if (reader != null)  
                {  
                    String line = reader.readLine();  
                    System.out.println("从服务器来的信息：" + line);  
                      
                }  
                i++;
                get = "send msg to server " + i;
                sleep(300000);  
            }  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
  
    public static void main(String [] args)  
    {  
        new SocketClientThTest().start();  
    }  

}
