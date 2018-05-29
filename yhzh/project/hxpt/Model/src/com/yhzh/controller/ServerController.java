package com.yhzh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yhzh.biz.ServerBizImpl;
import com.yhzh.pojo.S0000003;
import com.yhzh.pojo.S1000000;

import net.sf.json.JSONObject;

//解决跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
//@RestController //相当于@Controller + @ResponseBody
@RequestMapping(value = "/Server")
public class ServerController {

	@Resource
	ServerBizImpl serviceBizImpl;

	/**
	 * 初始化页面，发送S0000001表中的数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/InitPage", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String InitPage() {
		// 发送S0000001的数据给前端页面
		List<Object> list = serviceBizImpl.getS0000001();
		System.out.println("数据库查询出的数据：" + list);
		String jsonString = "{'isSuccess':1,'resultMessage':'" + list + "'}";
		// 转换成json字符串格式
		JSONObject jsonObject = new JSONObject().fromObject(jsonString);
		return jsonObject.toString();
	}

	/**
	 * 创建服务 json字符串出现转义符使用"text/plain;charset=UTF-8"
	 * 
	 * @return
	 */
	@RequestMapping(value = "/CreateServer", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String CreateServer() {
		String serviceCode = serviceBizImpl.AutoGetCode("01");
		String serviceUnitCode = serviceBizImpl.AutoGetCode("02");
		System.out.println("获得的服务编码："+ serviceCode);
		System.out.println("获得的服务单元编码："+ serviceUnitCode);	
		
		// 插入1级和2级菜单到S0000002表中
		int insertS0000002 = serviceBizImpl.insertS0000002(serviceCode,serviceUnitCode);
		// S1000000和S1100000各插入一条记录
		// 向S1000000表中 插入一条记录
		int insertS1000000 = serviceBizImpl.insertS1000000(serviceCode);
		System.out.println("向s1000000表中插入数据成功的返回值：" + insertS1000000);
		// 向S1100000表中 插入一条记录
		int insertS1100000 = serviceBizImpl.insertS1100000(serviceCode, serviceUnitCode);
		System.out.println("向s1100000表中插入数据成功的返回值：" + insertS1100000);
		// 将S0000002表中的数据发送给前端
		List<Object> list = serviceBizImpl.getS0000002();
		System.out.println("数据库查询出的数据：" + list);
		String jsonString = "{'isSuccess':1,'resultMessage':'" + list + "'}";
		// 转换成json字符串格式
		JSONObject jsonObject = new JSONObject().fromObject(jsonString);
		return jsonObject.toString();
	}

	/**
	 * 打开服务，前端传递服务编码参数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/OpenServer/{ServerCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String OpenServer(@PathVariable("ServerCode") String ServerCode) {
		System.out.println("得到的服务编码参数:" + ServerCode);
		// 根据服务编码从数据库中的S0000002表中查询数据
		List<Object> list = serviceBizImpl.getDatasByServiceCode(ServerCode);
		System.out.println("数据库查询出的数据：" + list);
		String jsonString = "{'isSuccess':1,'resultMessage':'" + list + "'}";
		// 转换成json字符串格格式
		JSONObject jsonObject = new JSONObject().fromObject(jsonString);
		return jsonObject.toString();
	}

	/**
	 * 编辑服务,将S1000000表中相对应服务的数据发送给页面
	 * @param ServerCode
	 * @return
	 */
	@RequestMapping(value = "/EditServer/{ServerCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String EditServer(@PathVariable("ServerCode") String ServerCode) {
		// 从S1000000得到服务的相关信息
		List<Object> list = serviceBizImpl.findS1000000ByServerCode(ServerCode);
		System.out.println("数据库查询出的数据：" + list);
		String jsonString = "{'isSuccess':1,'resultMessage':'" + list + "'}";
		// 转换成json字符串格格式
		JSONObject jsonObject = new JSONObject().fromObject(jsonString);
		return jsonObject.toString();
	}

	/**
	 * 更新服务的信息到S1000000数据库表中，要是服务名称被更改，需要更改S0000002表中的数据
	 */
	@RequestMapping(value = "/UpdateServer", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String UpdateServer(@RequestBody String server) {
		//接收json格式数据
		System.out.println("接收到的参数" + server);	
		// 将数据更新到S1000000和S0000002(服务名称)表格中
		//将数据更新到S1000000表中
		int updateS1000000 = serviceBizImpl.updateS1000000(server);
		System.out.println("更新到S1000000表中成功："+updateS1000000);
		//将数据更新到S0000002表中
		int updateS0000002 = serviceBizImpl.updateServerS0000002(server);
		System.out.println("更新到S0000002表中成功："+updateS0000002);
		//修改完成返回的数据
		return "OK";
	}
	
	/**
	 * 编辑服务单元,将S1100000表中相对应服务单元的数据发送给页面
	 * @param ServerCode
	 * @return
	 */
	@RequestMapping(value = "/EditUnitServer/{ServerUnitCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String EditUnitServer(@PathVariable("ServerUnitCode") String ServerUnitCode) {
		// 从S1000000得到服务的相关信息
		List<Object> list = serviceBizImpl.findS1100000ByServerUnitCode(ServerUnitCode);
		System.out.println("数据库查询出的数据：" + list);
		String jsonString = "{'isSuccess':1,'resultMessage':'" + list + "'}";
		// 转换成json字符串格格式
		JSONObject jsonObject = new JSONObject().fromObject(jsonString);
		return jsonObject.toString();
	}	
	
	/**
	 * 更新服务单元的信息到S1100000数据库表中，要是服务单元名称被更改，需要更改S0000002表中的数据
	 */
	@RequestMapping(value = "/UpdateServerUnit", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String UpdateServerUnit(@RequestBody String serverUnitString) {
		//接收json格式数据
		System.out.println("接收到的参数" + serverUnitString);	
		// 将数据更新到S1000000和S0000002(服务名称)表格中
		//将数据更新到S1000000表中
		int updateS1100000 = serviceBizImpl.updateS1100000(serverUnitString);
		System.out.println("更新到S1100000表中成功："+updateS1100000);
		//将数据更新到S0000002表中
		int updateS0000002 = serviceBizImpl.updateServerUnitS0000002(serverUnitString);
		System.out.println("更新到S0000002表中成功："+updateS0000002);
		//修改完成返回的数据
		return "OK";
	}
	/**
	 * 创建实时对象
	 * @param createRtObjectString
	 * @return
	 */
	@RequestMapping(value = "/CreateRTObject", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String CreateRTObject(@RequestBody String createRtObjectString) {
		int createRTObject = serviceBizImpl.CreateRTObject(createRtObjectString);
		return "OK";
	}
	
	
}
