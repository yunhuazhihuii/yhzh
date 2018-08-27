package com.yhzh.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhzh.biz.RTObjectAttrBiz;


//解决跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
// @RestController //相当于@Controller + @ResponseBody
@RequestMapping(value = "/Server")
public class RTObjectAttrController {

	@Resource
	RTObjectAttrBiz rTObjectAttrBiz;

	/**
	 * 创建实时对象属性目录
	 * @param RTOAttrCatalogString
	 * @return
	 */
	@RequestMapping(value = "/CreateRTOAttrCatalog", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String CreateRTOAttrCatalog(@RequestBody String RTOAttrCatalogString) {
		String rTOAttrCatalog = rTObjectAttrBiz.RTOAttrCatalog(RTOAttrCatalogString);
		return rTOAttrCatalog;
	}
	/**
	 * 删除实时对象属性目录
	 * @param RTOAttrCatalogCode
	 * @return
	 */
	@RequestMapping(value = "/DeleteRTOAttrCatalog/{RTOAttrCatalogCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String DeleteRTOAttrCatalog(@PathVariable("RTOAttrCatalogCode") String RTOAttrCatalogCode) {
		String deleteRTOAttrCatalog = rTObjectAttrBiz.DeleteRTOAttrCatalog(RTOAttrCatalogCode);
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
		String createRTOAttr = rTObjectAttrBiz.CreateRTOAttr(RTOAttrString);
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
		String editRTOAttr = rTObjectAttrBiz.findS1111000ByRTOAttrCode(RTOAttrCode);
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
		String UpdateRTOAttr = rTObjectAttrBiz.UpdateRTOAttr(RTOAttrString);
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
		String DeleteRTOAttr = rTObjectAttrBiz.DeleteRTOAttr(RTOAttrCode);
		return DeleteRTOAttr;
	}
}
