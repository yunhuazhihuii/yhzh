package com.yhzh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yhzh.biz.ServerBiz;
import com.yhzh.biz.ServerUnitBiz;
import com.yhzh.biz.impl.ServerBizImpl;
import com.yhzh.pojo.S0000003;
import com.yhzh.pojo.S1000000;

import net.sf.json.JSONObject;

//解决跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
// @RestController //相当于@Controller + @ResponseBody
@RequestMapping(value = "/Server")
public class ServerUnitController {

	@Resource
	ServerUnitBiz serviceUnitBiz;
	
	/**
	 * 创建服务单元
	 * @param ServerCode
	 * @return
	 */
	@RequestMapping(value = "/CreateServerUnit", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String CreateServerUnit(@RequestBody String CreateServerUnitString) {
		// 从S1000000得到服务的相关信息
		String editUnitServer = serviceUnitBiz.CreateServerUnit(CreateServerUnitString);
		return editUnitServer;
	}
	/**
	 * 编辑服务单元,将S1100000表中相对应服务单元的数据发送给页面
	 * @param ServerCode
	 * @return
	 */
	@RequestMapping(value = "/EditServerUnit/{ServerUnitCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String EditServerUnit(@PathVariable("ServerUnitCode") String ServerUnitCode) {
		// 从S1000000得到服务的相关信息
		String editUnitServer = serviceUnitBiz.findS1100000ByServerUnitCode(ServerUnitCode);
		return editUnitServer;
	}

	/**
	 * 更新服务单元的信息到S1100000数据库表中，要是服务单元名称被更改，需要更改S0000002表中的数据
	 */
	@RequestMapping(value = "/UpdateServerUnit", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String UpdateServerUnit(@RequestBody String serverUnitString) {
		// 将数据更新到S1000000和S0000002(服务名称)表格中
		String updateS1100000 = serviceUnitBiz.updateS1100000(serverUnitString);
		return updateS1100000;
	}
	
}
