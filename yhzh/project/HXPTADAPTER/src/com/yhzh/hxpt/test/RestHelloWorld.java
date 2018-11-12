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

	
	/** RestController ����1���ǳ��ɹ�������������
	 * @param listIn
	 * @return
	 */
	@RequestMapping({"/helloAjax6"})
	public List<Map<String,Object>> helloAjax6(@RequestBody List<Map<String,Object>> listIn){
		System.out.println("����������111111----");
		System.out.println("ajax���ݽ������ݷ�ʽ5�õ������listIn��" + listIn);

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
		//������ǰ̨���÷�ʽ��
		/*var list =[
			{username:"zhangsan61",mypara2:"�������mypara61"},
			{username:"zhangsan62",mypara2:"�������mypara62"},
			{username:"zhangsan63",mypara2:"�������mypara63"},
			{username:"zhangsan64",mypara2:"�������mypara66"},
			{username:"zhangsan68",mypara2:"�������mypara68"}
		];
		$.ajax({
			   url:"http://localhost:8080/cmstest/helloAjax6",
			   type:"post",
			   dataType:"json", //�������صľ���һ��json����
			   contentType:'application/json;charset=UTF-8',//�ؼ���Ҫ��������
		       traditional:true,//��ʹjson��ʽ���ַ����ᱻת��
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
	
	/** RestController ����2��ʧ��
	 * @param listIn
	 * @return
	 */
	@RequestMapping({"/helloAjax6_2"})
	public List<Map<String,Object>> helloAjax6_2(@RequestBody List<Map<String,Object>> listIn){
		System.out.println("����������111111----");
		System.out.println("ajax���ݽ������ݷ�ʽ5�õ������listIn��" + listIn);

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
		//������ǰ̨���÷�ʽ��
		/*var list =[
			{username:"zhangsan61",mypara2:"�������mypara61"},
			{username:"zhangsan62",mypara2:"�������mypara62"},
			{username:"zhangsan63",mypara2:"�������mypara63"},
			{username:"zhangsan64",mypara2:"�������mypara66"},
			{username:"zhangsan68",mypara2:"�������mypara68"}
		];
		$.ajax({
			   url:"http://localhost:8080/HXPTADAPTER/helloAjax6_2",
			   type:"post",
			   dataType:"json", //�������صľ���һ��json����
			   //contentType:'application/json;charset=UTF-8',//�ؼ���Ҫ��������
		       //traditional:true,//��ʹjson��ʽ���ַ����ᱻת��
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
	
	/** RestController ����3��ʧ��
	 * @param listIn
	 * @return
	 */
	@RequestMapping({"/helloAjax6_3"})
	public List<Map<String,Object>> helloAjax6_3(@RequestBody Map<String,Object> mapIn){
		System.out.println("����������111111----");
		System.out.println("ajax���ݽ��������õ������mapIn��" + mapIn);

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
		//������ǰ̨���÷�ʽ��
		/*$.ajax({
			   url:"http://localhost:8080/HXPTADAPTER/helloAjax6_3",
			   type:"post",
			   //dataType:"text", //�������صľ���һ��json�ַ���
			   dataType:"json", //�������صľ���һ��json����
			   data:{
				   username:"RestController33",
				   mypara2:"�������RestController3"
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
