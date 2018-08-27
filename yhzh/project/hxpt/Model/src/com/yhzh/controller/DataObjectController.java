package com.yhzh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhzh.biz.DataObjectBiz;

//解决跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
// @RestController //相当于@Controller + @ResponseBody
@RequestMapping(value = "/Server")
public class DataObjectController {

	@Resource
	DataObjectBiz dataObjectBiz;

	/**
	 * 创建数据对象
	 * @return
	 */
	@RequestMapping(value = "/CreateDataObject", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String CreateDataObject(@RequestBody String DataObjectString) {
		String createDataObject = dataObjectBiz.CreateDataObject(DataObjectString);
		return createDataObject;
	}
	/**
	 * 编辑数据对象
	 * @param DataObjectCode
	 * @return
	 */
	@RequestMapping(value = "/EditDataObject/{DataObjectCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String EditDataObject(@PathVariable("DataObjectCode") String DataObjectCode) {
		String dataObject = dataObjectBiz.findS1120000ByDataObjectCode(DataObjectCode);
		return dataObject;
	}
	/**
	 * 更新数据对象
	 * @param DataObjectString
	 * @return
	 */
	@RequestMapping(value = "/UpdateDataObject", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String UpdateDataObject(@RequestBody String DataObjectString) {
		String updateDataObject = dataObjectBiz.UpdateDataObject(DataObjectString);
		return updateDataObject;
	}
	/**
	 * 删除数据对象
	 * @param DataObjectString
	 * @return
	 */
	@RequestMapping(value = "/DeleteDataObject/{DataObjectCode}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String DeleteDataObject(@PathVariable("DataObjectCode") String DataObjectCode) {
		String updateDataObject = dataObjectBiz.DeleteDataObject(DataObjectCode);
		return updateDataObject;
	}
	/**
	 * 创建数据库表类
	 * @param DataObjectString
	 * @return
	 */
	@RequestMapping(value = "/CreateDataTable", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String CreateDataTable(@RequestBody String DataObjectCode) {
		String updateDataObject = dataObjectBiz.DeleteDataObject(DataObjectCode);
		return updateDataObject;
	}
	
	
	
	
}
