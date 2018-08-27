package com.yhzh.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhzh.biz.ServerBiz;
//解决跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
// @RestController //相当于@Controller + @ResponseBody
@RequestMapping(value = "/Server")
public class ServerController {

	@Resource
	ServerBiz serviceBiz;

	/**
	 * 初始化页面，发送S0000001表中的数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/InitPage", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String InitPage() {
		// 发送S0000002的数据给前端页面
		String initPage = serviceBiz.InitPage();
		return initPage;
	}
	/**
	 * 右击创建的子节点
	 * @return
	 */
	@RequestMapping(value = "/ChildrenNode/{parentCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String ChildrenNode(@PathVariable("parentCode") String parentCode) {
		String childrenNode = serviceBiz.ChildrenNode(parentCode);
		return childrenNode;
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
		String createServer = serviceBiz.CreateServer();
		return createServer;
	}

	/**
	 * 打开服务，前端传递服务编码参数
	 * @return
	 */
	@RequestMapping(value = "/OpenServer/{ServerCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String OpenServer(@PathVariable("ServerCode") String ServerCode) {
		// 根据服务编码从数据库中的S0000002表中查询数据
		String openServer = serviceBiz.OpenServer(ServerCode);
		return openServer;
	}

	/**
	 * 编辑服务,将S1000000表中相对应服务的数据发送给页面
	 * 
	 * @param ServerCode
	 * @return
	 */
	@RequestMapping(value = "/EditServer/{ServerCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String EditServer(@PathVariable("ServerCode") String ServerCode) {
		// 从S1000000得到服务的相关信息
		String editServer = serviceBiz.EditServer(ServerCode);
		System.out.println("编辑服务发送给也页面的数据：" + editServer);
		return editServer;
	}

	/**
	 * 更新服务的信息到S1000000数据库表中，要是服务名称被更改，需要更改S0000002表中的数据
	 */
	@RequestMapping(value = "/UpdateServer", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String UpdateServer(@RequestBody String serverString) {
		// 将数据更新到S1000000和S0000002(服务名称)表格中
		String updateS1000000 = serviceBiz.UpdateServer(serverString);
		System.out.println("1111111");
		return updateS1000000;
	}
}
