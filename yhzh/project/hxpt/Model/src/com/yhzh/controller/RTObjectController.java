package com.yhzh.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhzh.biz.RTObjectBiz;


//解决跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
// @RestController //相当于@Controller + @ResponseBody
@RequestMapping(value = "/Server")
public class RTObjectController {

	@Resource
	RTObjectBiz rTObjectBiz;


	/**
	 * 创建实时对象
	 * 
	 * @param createRtObjectString
	 * @return
	 */
	@RequestMapping(value = "/CreateRTObject", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String CreateRTObject(@RequestBody String createRtObjectString) {
		String createRTObject = rTObjectBiz.CreateRTObject(createRtObjectString);
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
		String editRtObject = rTObjectBiz.findS1110000ByRtObjectCode(RtObjectCode);
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
		String updateRtObject = rTObjectBiz.UpdateRtObject(RtObjectString);
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
		String deleteRtObject = rTObjectBiz.DeleteRtObject(RtObjectCode);
		return deleteRtObject;
	}
}
