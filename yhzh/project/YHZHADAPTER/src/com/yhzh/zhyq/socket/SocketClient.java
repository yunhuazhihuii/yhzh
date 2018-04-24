//package com.yhzh.zhyq.socket;
//
//import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import com.yhzh.util.JsonUtil;
//import com.yhzh.util.SpringContextHelper;
//import com.yhzh.zhyq.dao.YhzhDao;
//import com.yhzh.zhyq.websocket.WSocketClientEng;
//import com.yhzh.znzm.dao.ZnzmDao;
//import com.yhzh.znzm.mqtt.server.Json;
//
///**连接到socket server的socket客户端
// * @author chenmingshuai
// * 2017-11-9
// */
//
////@Scope("prototype")
//public class SocketClient {
//
//	public static Log LOG = LogFactory.getLog(SocketClient.class); 
//	
//	private String id; //客户端标识
//	private Socket socket; //客户端连接过来的socket
//	private String name;// 客户端名称
//	
//	private boolean bConnected = false; //是否与客户端保持连接
//	private DataInputStream dis = null;
//	private DataOutputStream dos = null;
//	private BufferedReader br;
//	private PrintWriter pw;
//	
//    @Resource //非单例不能用注入
//	YhzhDao yhzhDao;
//    //去socket其六   改为了static
////	@Resource  //注解注入，这个可以注入，而Autowire不行,不知怎得又不行了
//	private  JsonUtil jsonUtil = new JsonUtil();
//	@Resource
//	private ZnzmDao znzmDao;
////	@Resource
//	private WSocketClientEng wSocketClientEng;
//	private SpringContextHelper springContextHelper;
///*	public static SocketClient socketClient;
//	@PostConstruct
//	public void init() {
//		socketClient = this;
//	}
//	*/
//	/**构造方法
//	 * @param id
//	 * @param socket
//	 */
//	/**
//	 * @param id
//	 * @param socket
//	 * @param wSocketClientEng
//	 */
//	public SocketClient(String id,Socket socket,SpringContextHelper springContextHelper){
//		this.id= id;
//		this.socket = socket;
//		this.springContextHelper = springContextHelper;
//		try {
////			dis = new DataInputStream(socket.getInputStream());
////			dos = new DataOutputStream(socket.getOutputStream());
//			InputStreamReader isr = new InputStreamReader(socket.getInputStream(),"UTF-8");
//			br = new BufferedReader(isr);
//			OutputStream os = socket.getOutputStream(); // 字节输出流
//			pw = new PrintWriter(os);
//			bConnected = true;
//			//这个方法不可行，导致类被再次实例化
////			AbstractApplicationContext ac = new FileSystemXmlApplicationContext(AdapterConstants.getRootPath() + "/WebContent/WEB-INF/springMVC-servlet.xml"); 
////			this.znzmDao = (ZnzmDao)ac.getBean("znzmDao");
////			ac.close();
//			
////			ServletContext sc;
////			ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
//			wSocketClientEng = (WSocketClientEng)springContextHelper.getBean("wSocketClientEng");
//			znzmDao = (ZnzmDao)springContextHelper.getBean("znzmDao");
//			yhzhDao = (YhzhDao)springContextHelper.getBean("yhzhDao");
//		} catch (IOException e) {
//			//记录日志
//			e.printStackTrace();
//		}
//	}
////	去socket，其三
//	public SocketClient() {
//		// TODO Auto-generated constructor stub
//	}
//
//	/**读取客户端的数据
//	 * @return
//	 */
//	public String read(){
//		String str = "";
//		try {
////			int BUFFER_SIZE=1024*1024; 
////			byte[] data = new byte[BUFFER_SIZE]; 
////			int len = dis.read(data);
////			str = dis.readUTF();
////			str = String.valueOf(data);
//			str = br.readLine();
//			LOG.debug("socket服务端收到消息：" + str);
////			OutputStream os1 = socket.getOutputStream(); // 字节输出流
////			PrintWriter pw1 = new PrintWriter(os1);
////			pw1.println("你好，服务器已经收到您的信息！");
////			pw1.flush();
//			//记录日志
//		} catch (IOException e) {
//			System.out.println("Client closed!");
//			System.out.println(e);
//		}
//		return str;
//	}
//	
//	/**发送数据给客户端
//	 * @param str
//	 */
//	public void send(String str) {
//		LOG.debug("nsocket准备发送数据到驱动：" + str);
//		//去socket，其一
//		try {
//			Json.setvalue(str);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		try {
////
////			pw.println(str);
////			pw.flush();
////			//	LOG.debug("socketserver发送数据成功!");
////
////		} catch (Exception e) {
////			//记录日志
////			System.out.println("对方退出了！我从List里面去掉了！");
////		}
//	}
//	
//	/**
//	 * 连接断开， 释放资源
//	 */
//	public void close(){
//		try {
//			if (dis != null)
//				dis.close();
//			if (dos != null)
//				dos.close();
//			if (socket != null) {
//				socket.close();
//			}
//			//记录日志
//		} catch (IOException e) {
//			//记录日志
//			e.printStackTrace();
//		}
//	}
//	
//	/**检查是否与客户端保持连接
//	 * @param str
//	 */
//	
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public Socket getSocket() {
//		return socket;
//	}
//	public void setSocket(Socket socket) {
//		this.socket = socket;
//	}
//
//	public boolean isbConnected() {
//		return bConnected;
//	}
//
//	public void setbConnected(boolean bConnected) {
//		this.bConnected = bConnected;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//
//	/**socket客户端注册接口
//	 * @param strRec
//	 * @return
//	 */
//	public boolean acceptRegister(String strRec){
//		boolean bRst = false;
//		Map<String,Object> mapRes = jsonUtil.jsonStrToMap(strRec);
//        //{"act":"register",content:{"clientId":"000001","name":"智能照明"}}
//        if("register".equals((String)mapRes.get("act"))){//是注册
//    		  @SuppressWarnings("unchecked")
//			Map<String,Object> mapCon = (Map<String,Object>)mapRes.get("content");
//    		  String clientId = (String)mapCon.get("clientId");
//    		  String name = (String)mapCon.get("name");
//    		  this.setId(clientId);
//    		  this.setName(name);
//    	      bRst = true;
//        }else{
//        	LOG.debug("-----不是socket客户端注册数据-----");
//        }
//        return bRst;
//	}
//	
//	/**适配器向驱动（socketclient）请求数据接口
//	 * @param pointId 点编号
//	 * @return
//	 */
//	public boolean loopStatusRequ(String pointId){
//		boolean bRst = false;
//		String mac = znzmDao.getMacBypointId(pointId);
//		String areaCode = znzmDao.getAreaCodeBypointId(pointId);
//		String cellCode = znzmDao.getCellCodeBypointId(pointId);
//		LOG.debug("-----适配器开始向驱动（socketclient）请求数据----");
//		String str_loopStatusRequ = "{\"act\":\"loopStatusRequ\",\"content\":{\"areaCode\":\"" + areaCode + "\"," +
//				"\"cellCode\":\"" + cellCode + "\",\"mac\":\"" + mac + "\"}}";
//		this.send(str_loopStatusRequ);
//		return bRst;
//	}
////	去socket，其二
//public  void	nosocket(String strRec){
//
//
//	LOG.debug("接收到nosocket发送过来的内容:" + strRec);
//	if("{".equals(strRec.substring(0, 1))){
//		Map<String,Object> mapRes = jsonUtil.jsonStrToMap(strRec);
//		//智能照明驱动向主适配器发送数据
//		if("update".equals((String)mapRes.get("act"))){
//			
//			//转发数据给websocket服务器
//			//去socket,其五
//			LOG.debug("接收到内容为智能照明驱动发过来的数据"+mapRes);
//			reciLightData(mapRes);
//		}else if("control".equals((String)mapRes.get("act"))){
//			LOG.debug("接收到内容为智能照明驱动发过来的控制命令回馈");
//		    reciLightConBak(mapRes);
//		}
//	}
//	
//
//	
//}
//	/**接收照明驱动发送过来的数据
//	 * @param mapRes
//	 */
//	public boolean reciLightData(Map<String,Object> mapRes){
//		boolean bRst = false;
//		//现在先默认都是回路数据
//		/*{"act":"update","content":{"type":"cellDimmer","mac":"000001","data":[
//			{"ModuleMac":"1","cellIndex":"1","value":"1"},
//			{"ModuleMac":"1","cellIndex":"2","value":"0"},
//			{"ModuleMac":"2","cellIndex":"1","value":"1"},
//			…
//			{"ModuleMac":"5","cellIndex":"5","value":"1"}
//			]}}
//		服务端返回：{'act':'update','succcess':'0','message':''}
//		*/
//		@SuppressWarnings("unchecked")
//		Map<String,Object> mapCon = (Map<String,Object>)mapRes.get("content");
//		@SuppressWarnings("unused")
//		String mac = (String)mapCon.get("mac");
//		@SuppressWarnings("unchecked")
//		List<Map<String, Object>> listData = (List<Map<String, Object>>)mapCon.get("data");
//		//[{"name":点名1,"value":值},{"name":点名1,"value":值},...]
//		String sData = "[";
//		for(Map<String, Object> mapData: listData){
//			
//			String ModuleMac = (String)mapData.get("ModuleMac");
//			String cellIndex = (String)mapData.get("cellIndex");
//			String value = (String)mapData.get("value");
//			System.out.println("测试用：ModuleMac："+ModuleMac+",cellIndex:"+cellIndex+",2");
//			
//			String pointId = znzmDao.getPointId(ModuleMac,cellIndex,"2");//2是输出点
//			pointId = wSocketClientEng.getClientId() + "." + id + "." + pointId;
//			sData += "{\"name\":\"" + pointId + "\",\"value\":\"" + value + "\",\"ioproperty\":\"" + "DI"+ "\"},";
//			System.out.print("开始发送数据到webserver"+znzmDao.getPointId(ModuleMac,cellIndex,"2"));
//    		//将数据保存到数据库
//    		yhzhDao.setpointValuebypointid(znzmDao.getPointId(ModuleMac,cellIndex,"2"),value);
//		}
//		sData  = sData.substring(0,sData.length() - 1);
//		sData += "]";
//		//将数据转发给websocketserver
//    	LOG.debug("---开始向websocketserver发送灯光驱动发过来的数据---");
//		bRst = wSocketClientEng.sendDataToWServer(sData);
//		if(bRst){
//			LOG.debug("向websocketserver发送灯光驱动发过来的数据成功！");
//		}else{
//			LOG.debug("向websocketserver发送灯光驱动发过来的数据失败，请检查。");
//		}
//		//返回回馈信息给照明驱动
//		//{"act":"update","succcess":"0","message":""}
//		String str = "{\"act\":\"update\",\"succcess\":\"0\",\"message\":\"\"}";
//		this.send(str);
//		bRst = true;
//		return bRst;
//	}
//	
//	/**发送控制命名给照明驱动
//	 * @param listData
//	 * @return
//	 */
//	public boolean sendConToLigthEng(List<Map<String,Object>> listData){
//		boolean bRst = false;
//		/*[
//         {
//             "name": "点名1",
//             "value": "值"
//         },
//         {
//             "name": "点名1",
//             "value": "值"
//         }
//         ]*/
//		
//		/*{"act":"control","content":{"type":"cellDimmer","data":[
//		{"mac":"0000a","areaCode":"1","cellCode":"1","value":"1"},
//		{"mac":"0000a","areaCode":"1","cellCode":"2","value":"1"},
//		…
//		{"mac":"0000a","areaCode":"5","cellCode":"1","value":"1"}
//		]}}
//		客户端返回:
//		 {"type":"control","succcess":"0","message":""}*/
//		String strSend = "{\"act\":\"control\",\"content\":{\"type\":\"cellDimmer\",\"data\":[";
//		for(Map<String,Object> mapData : listData){
//			String pointNames = (String)mapData.get("name");
//			String value = (String)mapData.get("value");
//			String [] pointArr = pointNames.split("\\."); //适配器id.驱动id.点编号
//		
//			String pointId = pointArr[2]; //点编号
//			//获取网关地址
//			String mac = znzmDao.getMacBypointId(pointId);
//			//区域号
//			String areaCode = znzmDao.getAreaCodeBypointId(pointId);
//			//区域回路号
//			String cellCode = znzmDao.getCellCodeBypointId(pointId);
//			strSend += "{\"mac\":\"" + mac + "\",\"areaCode\":\"" + areaCode + "\",\"cellCode\":\"" + cellCode + "\",\"value\":\"" + value + "\"}";
//		}
//		strSend += "]}}";
//		this.send(strSend);
//		bRst = true;
//		return bRst;
//	}
//		/**写点  2018-1-27
//	 * @param listData
//	 * @return
//	 */
//	public boolean writePoint(String pointId,String value){
//		boolean bRst = false;
//		String strSend = "{\"act\":\"control\",\"content\":{\"type\":\"cellDimmer\",\"data\":[";
//		//获取网关地址
//		String mac = znzmDao.getMacBypointId(pointId);
//		//区域号
//		String areaCode = znzmDao.getAreaCodeBypointId(pointId);
//		//区域回路号
//		String cellCode = znzmDao.getCellCodeBypointId(pointId);
//		strSend += "{\"mac\":\"" + mac + "\",\"areaCode\":\"" + areaCode + "\",\"cellCode\":\"" + cellCode + "\",\"value\":\"" + value + "\"}";
//		strSend += "]}}";
//		
//		this.send(strSend);
//		bRst = true;
//		return bRst;
//	}
//
//	/**发送控制命名给照明驱动后接收回馈
//	 * @param mapRes
//	 */
//	public boolean reciLightConBak(Map<String,Object> mapRes){
//		boolean bRst = false;
//		/*驱动返回:
//		 {"type":"control","succcess":"0","message":""}*/
//		String succcess = (String)mapRes.get("succcess");
//		if("0".equals(succcess)){
//			LOG.debug("---照明驱动控制灯光成功！---");
//		}else{
//			LOG.info("照明驱动控制灯光失败：" + mapRes.get("message"));
//		}
//		return bRst;
//	}
//}
