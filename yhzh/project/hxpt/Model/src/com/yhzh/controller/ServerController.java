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
// @RestController //相当于@Controller + @ResponseBody
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
		String initPage = serviceBizImpl.InitPage();
		return initPage;
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
		String createServer = serviceBizImpl.CreateServer();
		return createServer;
	}

	/**
	 * 打开服务，前端传递服务编码参数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/OpenServer/{ServerCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String OpenServer(@PathVariable("ServerCode") String ServerCode) {
		// 根据服务编码从数据库中的S0000002表中查询数据
		String openServer = serviceBizImpl.getDatasByServiceCode(ServerCode);
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
		String editServer = serviceBizImpl.findS1000000ByServerCode(ServerCode);
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
		String updateS1000000 = serviceBizImpl.updateS1000000(serverString);
		System.out.println("1111111");
		return updateS1000000;
	}

	/**
	 * 编辑服务单元,将S1100000表中相对应服务单元的数据发送给页面
	 * 
	 * @param ServerCode
	 * @return
	 */
	@RequestMapping(value = "/EditServerUnit/{ServerUnitCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String EditUnitServer(@PathVariable("ServerUnitCode") String ServerUnitCode) {
		// 从S1000000得到服务的相关信息
		String editUnitServer = serviceBizImpl.findS1100000ByServerUnitCode(ServerUnitCode);
		return editUnitServer;
	}

	/**
	 * 更新服务单元的信息到S1100000数据库表中，要是服务单元名称被更改，需要更改S0000002表中的数据
	 */
	@RequestMapping(value = "/UpdateServerUnit", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String UpdateServerUnit(@RequestBody String serverUnitString) {
		// 将数据更新到S1000000和S0000002(服务名称)表格中
		String updateS1100000 = serviceBizImpl.updateS1100000(serverUnitString);
		return updateS1100000;
	}

	/**
	 * 创建实时对象
	 * 
	 * @param createRtObjectString
	 * @return
	 */
	@RequestMapping(value = "/CreateRTObject", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String CreateRTObject(@RequestBody String createRtObjectString) {
		String createRTObject = serviceBizImpl.CreateRTObject(createRtObjectString);
		return createRTObject;
	}

	/**
	 * 编辑实时对象
	 * 
	 * @param RtObjectCode
	 * @return
	 */
	@RequestMapping(value = "/EditRtObject/{RtObjectCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String EditRtObject(@PathVariable("RtObjectCode") String RtObjectCode) {
		// 根据实时对象编码在S1110000表中查询
		String editRtObject = serviceBizImpl.findS1110000ByRtObjectCode(RtObjectCode);
		return editRtObject;
	}

	/**
	 * 更新实时对象
	 * 
	 * @param RtObjectString
	 * @return
	 */
	@RequestMapping(value = "/UpdateRtObject", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String UpdateRtObject(@RequestBody String RtObjectString) {
		// 将修改的实时对象数据更新到表S1110000中
		String updateRtObject = serviceBizImpl.UpdateRtObject(RtObjectString);
		return updateRtObject;
	}
	
	/**
	 * 删除实时对象
	 * @param RtObjectCode
	 * @return
	 */
	@RequestMapping(value = "/DeleteRtObject/{RtObjectCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String DeleteRtObject(@PathVariable("RtObjectCode") String RtObjectCode) {
		String deleteRtObject = serviceBizImpl.DeleteRtObject(RtObjectCode);
		return deleteRtObject;
	}
	/**
	 * 创建实时对象属性目录
	 * @param RTOAttrCatalogString
	 * @return
	 */
	@RequestMapping(value = "/CreateRTOAttrCatalog", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String CreateRTOAttrCatalog(@RequestBody String RTOAttrCatalogString) {
		String rTOAttrCatalog = serviceBizImpl.RTOAttrCatalog(RTOAttrCatalogString);
		return rTOAttrCatalog;
	}
	
	@RequestMapping(value = "/DeleteRTOAttrCatalog/{RTOAttrCatalogCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String DeleteRTOAttrCatalog(@PathVariable("RTOAttrCatalogCode") String RTOAttrCatalogCode) {
		String deleteRTOAttrCatalog = serviceBizImpl.DeleteRTOAttrCatalog(RTOAttrCatalogCode);
		return deleteRTOAttrCatalog;
	}
	/**
	 * 创建实时对象属性
	 * @param RTOAttrString
	 * @return
	 */
	@RequestMapping(value = "/CreateRTOAttr", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String CreateRTOAttr(@RequestBody String RTOAttrString) {
		String createRTOAttr = serviceBizImpl.CreateRTOAttr(RTOAttrString);
		return createRTOAttr;
	}
	/**
	 * 编辑实时对象属性
	 * @param RTOAttrCode
	 * @return
	 */
	@RequestMapping(value = "/EditRTOAttr/{RTOAttrCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String EditRTOAttr(@PathVariable("RTOAttrCode") String RTOAttrCode) {
		String editRTOAttr = serviceBizImpl.findS1111000ByRTOAttrCode(RTOAttrCode);
		return editRTOAttr;
	}
	/**
	 * 更新实时对象属性
	 * @param RTOAttrString
	 * @return
	 */
	@RequestMapping(value = "/UpdateRTOAttr", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String UpdateRTOAttr(@RequestBody String RTOAttrString) {
		String UpdateRTOAttr = serviceBizImpl.UpdateRTOAttr(RTOAttrString);
		return UpdateRTOAttr;
	}
	/**
	 * 删除实时对象属性
	 * @param RTOAttrString
	 * @return
	 */
	@RequestMapping(value = "/DeleteRTOAttr/{RTOAttrCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String DeleteRTOAttr(@PathVariable("RTOAttrCode") String RTOAttrCode) {
		String DeleteRTOAttr = serviceBizImpl.DeleteRTOAttr(RTOAttrCode);
		return DeleteRTOAttr;
	}
	
}
