package com.yhzh.znzm.mqtt.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONObject;

import com.yhzh.zhyq.socket.Nsocket;
import com.yhzh.znzm.mqtt.server.Server;
import com.yhzh.znzm.socketdc.Zmonline;

public class CheckSome extends Thread {
	    public static Log LOG = LogFactory.getLog(CheckSome.class);
	    String a = null;
	    private  Nsocket nsocket; 
	    private static int time=600;
	    
	    
	    
	    
	    
	    
	public CheckSome(String a,Nsocket nsocket){
		this.nsocket=nsocket;
		this.a=a;
	}
  
	// @Override
	public void run() {
		String back = "{\"act\":\"postReply\",\"flag\":\"success\"}";
		try {
			Server server = new Server();

            if (a.equals("close")) {
            	 LOG.debug("#########################ZMclient断开连接，close，等待重连########################");
				//System.err.print("断开连接，close，等待重连");
			} else {
				JSONObject obj = new JSONObject(a);
				// LOG.debug("信息种类" + obj.get("act"));
				String act = (String) obj.get("act");
				
				switch (act) {
				
				case "loopFeedback":
					// 回路反馈，发送到适配器
					String mac = (String) obj.get("mac");
					Object object = obj.get("data");

					Iterator it = ((JSONObject) object).keys();
					List<String> keyListstr = new ArrayList<String>();

					String c = null;
					while (it.hasNext()) {
						String modulemac = (String) it.next();
						Object value = ((JSONObject) object).get(modulemac);

						Iterator it1 = ((JSONObject) value).keys();
						List<String> keyListstr1 = new ArrayList<String>();

						while (it1.hasNext()) {
							String INDEX = (String) it1.next();
							if(INDEX.equals("0")||INDEX.equals("1")||INDEX.equals("2")||INDEX.equals("3")){

								Object value1 = ((JSONObject) value).get(INDEX);
								// int cell1le1 = (int) ((JSONObject) value1).get("le");

								int cell1le1 = (int) ((JSONObject) value1).get("le");
								if(cell1le1==100){
									cell1le1=1;
								}
								String c1 = "{\"ModuleMac\":\"" + modulemac
										+ "\",\"cellIndex\":\"" + INDEX + "\",\"value\":\""
										+ cell1le1 + "\"}";
								if (c == null) {
									c = c1;
								} else {
									c = c + "," + c1;
								}
							}
							
						}
					}
					String b = "{\"act\":\"update\",\"content\":{\"type\":\"cellDimmer\",\"mac\":\""
							+ mac + "\",\"data\":[" + c + "]}}";

					// LOG.debug("发送到适配器，封装："+b);
					senttosocket(b);
					break;
				case "deviceOnline":
			       /*需要详细设计
			        * 会返回网关在线数据，定义时间间隔加以判断是否离线
			        * 
*/			

					Zmonline ssc = new Zmonline(nsocket);
					ssc.set();
					if (time < 1) {
						 LOG.debug("连接超时，即将启动重连,time:"+time);
	    //启动，待完善
						new Client(nsocket).start();
    }
					
					

					
					break;
				case "areaFeedback":
					/*区域反馈，区域处于何种模式
					 *是否增加接口判断区域模式是否一致 
					 * 
					 */
					break;
				case "postModule":
					/*增加，模块数据
					 * 
					 *
					 *
					 */
					senttomac((String) obj.get("gatewayPlaintext"),back);
					break;
				case "postCell":
					/*//修改数据库，查询数据
					 * JSONObject bs= (JSONObject) obj.get("data");
					String a="网关："+(String) obj.get("gatewayPlaintext")+
					"模块："+(String) obj.get("moduleMac")+
					"模块回路："+(int) obj.get("cellIndex")+
					"区域："+(int) ((JSONObject) bs).get("areaCode")+
					"区域回路："+(int) bs.get("cellCode");
					Log.savelog(a);*/
					senttomac((String) obj.get("gatewayPlaintext"),back);
					break;
				case "postMenu":
					//未使用
				
					senttomac((String) obj.get("gatewayPlaintext"),back);
					break;
				case "postArea":
					 LOG.debug("发送"+back);
					senttomac((String) obj.get("gatewayPlaintext"),back);
					break;
				case "postGroup":
					 LOG.debug("发送"+back);
					senttomac((String) obj.get("gatewayPlaintext"),back);
				case "postGateway":
					 LOG.debug((String) obj.get("gatewayPlaintext"));
					 LOG.debug("发送"+back);
					senttomac((String) obj.get("gatewayPlaintext"),back);
					break;
				case "setReply":
					 LOG.debug("发送"+back);
					 LOG.debug("设置成功");
					break;
				default:
					break;
				}
			}
		

		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	// 上传回路信息到适配器
	public void senttosocket(String back) {
		
//		去socket，其四
		nsocket.nosocket(back);
		/*boolean bRst = SocketClientdc.sentMsgToSserver(back);*/
	}
	//根据mac地址分别发送到不同网关
	public static void senttomac(String mac,String data) throws MqttException {
		switch (mac) {
		case "0011c1c8016b":
			Server.open5(data);
			break;
		case "0011c1c8016e":
			Server.open2(data);
			break;
		case "0011c1d00171":
			Server.open4(data);
			break;
		case "0011c1c8016f":
			Server.open3(data);
			break;
		case "0011c1c8016c":
			Server.open(data);
			break;
		case "4011c1d0021a":
			Server.open6(data);
			break;

		default:
			
			break;
		}
		
		
	}
	
	public void back(){
		time=0;
	}
	
}
