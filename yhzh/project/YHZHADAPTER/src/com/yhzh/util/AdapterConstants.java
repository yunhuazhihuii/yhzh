package com.yhzh.util;
/**
 * 适配器常量
 * @author chenms 2017-11-18
 *
 */
public class AdapterConstants {

	/** websocket 接口回调常量 **/
	public final static String WEBSOCKT_REGISTER = "01"; //向websocketserver注册接口
	public final static String WEBSOCKT_RTSUB = "02"; //websocketserver请求数据接口（订阅）
	public final static String WEBSOCKT_CLIENTSEND = "03"; //向websocketserver推送数据（包括请求数据后的返回以及状态变化后主动推送数据）
	public final static String WEBSOCKT_SERVERSEND = "04"; //websocketserver控制接口
	
	private static String rootPath = null;
	
	public static String getRootPath(){
		if(null == rootPath){
			String path = AdapterConstants.class.getResource("/").getPath();
			rootPath = path.substring(0,path.indexOf("WebContent"));
		}
		return rootPath;
	}
}
