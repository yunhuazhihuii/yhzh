package com.yhzh.znzm.socketdc;

import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yhzh.zhyq.socket.Nsocket;
import com.yhzh.znzm.mqtt.client.CheckSome;


	public class Zmonline implements Runnable {
		public static Log LOG = LogFactory.getLog(Zmonline.class);
		   private static int count;
		   private  Nsocket nsocket; 
		    public Zmonline(Nsocket nsocket){
			this.nsocket=nsocket;
		}
		   public Zmonline() {
		      count = 600;
		   }
	
		  public void set(){
			  count=600;
		  }
		   public  void run() {
		      synchronized(this) {
	
		    	  
		    	 if(Thread.currentThread().getName().equals("one")){
	
		    			Scanner sc=new Scanner(System.in);
		    			
		    			 while (true) { 
		    				 break;
		    			 }
		    		 
		    	 }else{
		    		
		    		 while(true){
		    				try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		    				count=count-1;
		    	             if(count<1){
		    	            	 LOG.debug("mqttclient服务停止。。。。。。");
		    					CheckSome rt=new CheckSome(null, nsocket);
		    					rt.back();
		    				}
		    			}
		    	 }
		    	  
		    	  
		    	  
		    	  
		      }
			 
		   }
	
		   public int getCount() {
		      return count;
		   }
		}
	


