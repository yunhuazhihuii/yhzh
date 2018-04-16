package com.yhzh.znzm.mqtt.client;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
public class LogObb {
public static void main(String[] args) throws Exception {
	controllog("今天是第几");
}
//反馈信息
public static void savelog(String log) throws Exception {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");  
	 Date date = new Date(System.currentTimeMillis());  
     String fileName = dateFormat.format(date) + ".txt";
     
	try {  
        FileWriter fw = new FileWriter("E:/obb/"+fileName+"", true); 
        BufferedWriter bw = new BufferedWriter(fw);  
        
        bw.append("网关上传信息："+log+"\r");  
        bw.append(df.format(new Date())+"\r\n"); 
//        bw.write("2abc\r\n");// 往已有的文件上添加字符串  

        bw.close();
        fw.close(); 
    } catch (Exception e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    }  
}
//控制信息
public static void controllog(String log) throws Exception {SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");  
 Date date = new Date(System.currentTimeMillis());  
 String fileName = dateFormat.format(date) + ".txt";
 
try {  
    FileWriter fw = new FileWriter("E:/obb/"+fileName+"", true); 
    BufferedWriter bw = new BufferedWriter(fw);  
    
    bw.append("前台发送信息："+log+"\r");  
    bw.append(df.format(new Date())+"\r\n"); 
//    bw.write("2abc\r\n");// 往已有的文件上添加字符串  

    bw.close();
    fw.close(); 
} catch (Exception e) {  
    // TODO Auto-generated catch block  
    e.printStackTrace();  
}  }


}

