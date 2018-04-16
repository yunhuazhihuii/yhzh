package com.yhzh.zhyq.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhzh.bacnet.BacnetManager;
import com.yhzh.util.AdapterConstants;
import com.yhzh.zhyq.dao.BaseDao;
import com.yhzh.znzm.socketdc.Init;


@Controller
public class Helloworld {

	@Resource
	private BaseDao baseDao;

	public static Log LOG = LogFactory.getLog(Helloworld.class); 
	public Helloworld() throws MqttException{
		System.out.println("进入Helloworld构造函数");
		//Init.start();
		//BacnetManager bac =new BacnetManager();
	//	bac.BacnetManager();
//		LOG.debug("我是debug");
//		LOG.info("我是info");
//		LOG.error("我是error");
//		LOG.debug("路径path：" + AdapterConstants.getRootPath());
	}
	
    @RequestMapping("/helloworld")  //此处控制浏览器里访问路径 具体为：/helloworld，即http://localhost:8080/YHZHADAPTER/helloworld/
    public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //输出字符串
        response.getWriter().append("hello world");

    }
    
    public void initTest(){
    	/*String sql = "insert into yhzh_user(id,name) values (:id,:name)";
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("id", "1000000002");
    	paramMap.put("name", "chenmingshuai1");
    	baseDao.execSql(sql, paramMap);*/

    	String sql1 = "insert into yhzh_user(id,name) values (:id,:name)";
    	Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	paramMap1.put("id", "1000000003");
    	paramMap1.put("name", "chenmingshuai2");
    	baseDao.execSql(sql1, paramMap1);
    	System.out.println("----成功插入数据库---");
//    	baseDao.initTest();
    }
    
}
