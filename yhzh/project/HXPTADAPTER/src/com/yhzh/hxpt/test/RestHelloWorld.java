package com.yhzh.hxpt.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestHelloWorld {

	
	/** RestController 测试1，非常成功，无中文乱码
	 * @param listIn
	 * @return
	 */
	@RequestMapping({"/helloAjax6"})
	public List<Map<String,Object>> helloAjax6(@RequestBody List<Map<String,Object>> listIn){
		System.out.println("进入这里了111111----");
		System.out.println("ajax传递接送数据方式5拿到的入参listIn：" + listIn);

		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map1.put("out1", "myout1valuerest1");
		map1.put("out2", "myout2valuerest1");
		map2.put("out1", "myout1valuerest2");
		map2.put("out2", "myout2valuerest2");
		list.add(map1);
		list.add(map2);
		return list;
		//以下是前台调用方式：
		/*var list =[
			{username:"zhangsan61",mypara2:"哈哈入参mypara61"},
			{username:"zhangsan62",mypara2:"哈哈入参mypara62"},
			{username:"zhangsan63",mypara2:"哈哈入参mypara63"},
			{username:"zhangsan64",mypara2:"哈哈入参mypara66"},
			{username:"zhangsan68",mypara2:"哈哈入参mypara68"}
		];
		$.ajax({
			   url:"http://localhost:8080/cmstest/helloAjax6",
			   type:"post",
			   dataType:"json", //这样返回的就是一个json对象
			   contentType:'application/json;charset=UTF-8',//关键是要加上这行
		       traditional:true,//这使json格式的字符不会被转码
		       data:JSON.stringify(list),
			   success:function(responseText){
			     alert(responseText);
			     alert(responseText[0].out1);
			   },
			   error:function(){
			     alert("system error");
			   }
		});
				});*/
	}
	
	/** RestController 测试2，失败
	 * @param listIn
	 * @return
	 */
	@RequestMapping({"/helloAjax6_2"})
	public List<Map<String,Object>> helloAjax6_2(@RequestBody List<Map<String,Object>> listIn){
		System.out.println("进入这里了111111----");
		System.out.println("ajax传递接送数据方式5拿到的入参listIn：" + listIn);

		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map1.put("out1", "myout1valuerest1");
		map1.put("out2", "myout2valuerest1");
		map2.put("out1", "myout1valuerest2");
		map2.put("out2", "myout2valuerest2");
		list.add(map1);
		list.add(map2);
		return list;
		//以下是前台调用方式：
		/*var list =[
			{username:"zhangsan61",mypara2:"哈哈入参mypara61"},
			{username:"zhangsan62",mypara2:"哈哈入参mypara62"},
			{username:"zhangsan63",mypara2:"哈哈入参mypara63"},
			{username:"zhangsan64",mypara2:"哈哈入参mypara66"},
			{username:"zhangsan68",mypara2:"哈哈入参mypara68"}
		];
		$.ajax({
			   url:"http://localhost:8080/HXPTADAPTER/helloAjax6_2",
			   type:"post",
			   dataType:"json", //这样返回的就是一个json对象
			   //contentType:'application/json;charset=UTF-8',//关键是要加上这行
		       //traditional:true,//这使json格式的字符不会被转码
		       data:list,
			   success:function(responseText){
			     alert(responseText);
			     alert(responseText[0].out1);
			   },
			   error:function(){
			     alert("system error");
			   }
		});*/
	}
	
	/** RestController 测试3，失败
	 * @param listIn
	 * @return
	 */
	@RequestMapping({"/helloAjax6_3"})
	public List<Map<String,Object>> helloAjax6_3(@RequestBody Map<String,Object> mapIn){
		System.out.println("进入这里了111111----");
		System.out.println("ajax传递接送数据拿到的入参mapIn：" + mapIn);

		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map1.put("out1", "myout1valuerest3");
		map1.put("out2", "myout2valuerest3");
		map2.put("out1", "myout1valuerest33");
		map2.put("out2", "myout2valuerest33");
		list.add(map1);
		list.add(map2);
		return list;
		//以下是前台调用方式：
		/*$.ajax({
			   url:"http://localhost:8080/HXPTADAPTER/helloAjax6_3",
			   type:"post",
			   //dataType:"text", //这样返回的就是一个json字符串
			   dataType:"json", //这样返回的就是一个json对象
			   data:{
				   username:"RestController33",
				   mypara2:"哈哈入参RestController3"
			   },
			   success:function(responseText){
			     alert(responseText);
			     alert(responseText[0].out1);
			   },
			   error:function(){
			     alert("system error");
			   }
		});*/
	}
}
