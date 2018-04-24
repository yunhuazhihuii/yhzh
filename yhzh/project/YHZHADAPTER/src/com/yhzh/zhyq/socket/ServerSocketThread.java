//package com.yhzh.zhyq.socket;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.yhzh.util.JsonUtil;
//import com.yhzh.util.SpringContextHelper;
//import com.yhzh.zhyq.websocket.WSocketClientEng;
//import com.yhzh.znzm.dao.ZnzmDao;
//
///**
// * socket服务线程
// * 
// * @author chenmingshuai 2017-11-9
// */
//public class ServerSocketThread extends Thread {
//
//	public static Log LOG = LogFactory.getLog(ServerSocketThread.class);
//
//	private List<SocketClient> sClientList = new ArrayList<SocketClient>();
//
//	private ServerSocket serverSocket = null;
//
//	private ZnzmDao znzmDao;
//
//	// @Value("#{socketserver.port}") //注解引入properties中文件
//	private Integer port;// 监听的端口号
//
//	@Resource
//	// 注解注入，这个可以注入，而Autowire不行
//	private JsonUtil jsonUtil;
//	@Resource
//	private WSocketClientEng wSocketClientEng;
//	@Resource
//	private SpringContextHelper springContextHelper;
//
//	/**
//	 * 构造方法，启动时就执行
//	 */
//	public ServerSocketThread(int port) {
//		try {
//			serverSocket = new ServerSocket(port);
//			LOG.info("--------开始启动socket服务端接口--------------");
//			this.start();// 启动线程
//			LOG.info("--------启动socket服务端接口成功--------------");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 重写run()方法
//	 */
//	public void run() {
//		while (true) {
//			try {
//				// 每个请求交给一个线程去处理
//				Socket sk = serverSocket.accept();
//				LOG.info("--------有socket客户端连接成功："
//						+ sk.getRemoteSocketAddress());
//				// 获取标志
//				String id = "33";
//				SocketClient sc = new SocketClient(id, sk, springContextHelper);
//
//				String strRec = sc.read();
//				LOG.info("-----开始注册socket客户端-----");
//				if (sc.acceptRegister(strRec)) {
//					Map<String, String> mapRst = this.addSClient(sc);
//					String success = (String) mapRst.get("success");
//					String message = (String) mapRst.get("message");
//					if (!"0".equals(success)) {
//						LOG.info("-----注册socket客户端失败-----");
//					} else {
//						LOG.info("-----注册socket客户端成功-----");
//
//				
//
//					}
//					sc.send("{\"act\":\"register\",\"succcess\":\"" + success
//							+ "\",\"message\":\"" + message + "\"}");
//				}
//				sleep(1000);
//			} catch (Exception e) {
//				LOG.error("socket客户端连接异常：" + e.getMessage());
//			}
//
//		}
//	}
//
//	public List<SocketClient> getsClientList() {
//		return sClientList;
//	}
//
//	public Map<String, String> addSClient(SocketClient sClient) {
//		Map<String, String> mapRst = new HashMap<String, String>();
//		SocketClient sClient1 = this.getSocketClient(sClient.getId());
//		if (null != sClient1) {
//			mapRst.put("success", "-1");
//			mapRst.put("message", "连接失败，socket客户端编号[" + sClient.getId()
//					+ "]已存在！");
//		} else {
//			sClientList.add(sClient);
//			// 启动socket客户端连接过来的线程
//			SocketSerClientThread scThread = new SocketSerClientThread(sClient);
//			scThread.start();
//			mapRst.put("success", "0");
//			mapRst.put("message", "连接成功");
//		}
//		return mapRst;
//	}
//
//	public void delSClient(String id) {
//		for (SocketClient sClient : sClientList) {
//			if (id.equals(sClient.getId())) {
//				sClientList.remove(sClient);
//				break;
//			}
//		}
//	}
//
//	public SocketClient getSocketClient(String id) {
//		SocketClient sClient = null;
//		for (SocketClient sClient1 : sClientList) {
//			if (id.equals(sClient1.getId())) {
//				sClient = sClient1;
//				break;
//			}
//		}
//		return sClient;
//	}
//
//
//
//}
