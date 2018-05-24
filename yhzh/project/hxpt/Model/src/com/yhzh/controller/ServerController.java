package com.yhzh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhzh.biz.ServerBizImpl;

import net.sf.json.JSONObject;

//解决跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/Server")
public class ServerController {

	@Resource
	ServerBizImpl serviceBizImpl;

	/**
	 * 初始化页面，发送S0000001表中的数据
	 * @return
	 */
	@RequestMapping(value = "/InitPage", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String InitPage() {
		//发送S0000001的数据给前端页面
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
		String serviceCode = null;
		String serviceUnitCode = null;
		Map<String, String> mapString = serviceBizImpl.getServiceCodeAndUnitCode();
		serviceCode = mapString.get("serviceCode");
		serviceUnitCode = mapString.get("serviceUnitCode");
//		System.out.println("获得数据为：" +serviceCode + serviceUnitCode);
// 将得到的服务编码和服务单元编码更新到数据字典表中
		// 更新服务编码
		int updateServiceCode = serviceBizImpl.updateServiceCode(serviceCode);
		System.out.println("更新服务编码返回值：" + updateServiceCode);
		// 更新服务单元编码
		int updateServiceUnitCode = serviceBizImpl.updateServiceUnitCode(serviceUnitCode);
		System.out.println("更新服务单元编码返回值：" + updateServiceUnitCode);		
// 插入1级和2级菜单到S0000002表中		
		int insertS0000002 = serviceBizImpl.insertS0000002(serviceCode);
// S1000000和S1100000各插入一条记录
//向S1000000表中 插入一条记录
		int insertS1000000 = serviceBizImpl.insertS1000000(serviceCode);
		System.out.println("向s1000000表中插入数据成功的返回值："+insertS1000000);
//向S1100000表中 插入一条记录
		int insertS1100000 = serviceBizImpl.insertS1100000(serviceCode, serviceUnitCode);
		System.out.println("向s1100000表中插入数据成功的返回值："+insertS1100000);
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
	 * @return
	 */
	@RequestMapping(value = "/OpenServer/{ServerCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String OpenServer(@PathVariable("ServerCode") String ServerCode){
		System.out.println("得到的服务编码参数:"+ServerCode);
		//根据服务编码从数据库中的S0000002表中查询数据
		List<Object> list = serviceBizImpl.getDatasByServiceCode(ServerCode);
		System.out.println("数据库查询出的数据：" + list);
		String jsonString = "{'isSuccess':1,'resultMessage':'" + list + "'}";
		// 转换成json字符串格格式
		JSONObject jsonObject = new JSONObject().fromObject(jsonString);
		return jsonObject.toString();
	}
}
