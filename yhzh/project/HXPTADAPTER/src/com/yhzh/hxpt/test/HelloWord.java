package com.yhzh.hxpt.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhzh.hxpt.dao.BaseDao;
import com.yhzh.hxpt.spring.SpringBeanManager;

@Controller
public class HelloWord {
	
	@Value("${websocket.clientName}")
	public static String websName; //static变量没办法注入
	@Value("${websocket.url}")
	public static String websUrl;
	

	@Value("${websocket.clientName}")
	public String websName2; //普通变量可以注入，注意用utf-8加载就不会中文乱码
	@Value("${websocket.url}")
	public String websUrl2;

	@Resource  // 非单例不能用注入
	BaseDao baseDao;

	/* 这种方式应该可以从request中拿到入参，response中设置出参，甚至在response的Writer直接返回页面的html内容
	 * @RequestMapping("/view1")
     public String testView(){
    	 return "viewtest";
     }*/
	 @RequestMapping("/helloworld")  //此处控制浏览器里访问路径 具体为：/工程名/helloworld
     public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
         //输出字符串
         response.getWriter().append("hello world222");

     }
	 @RequestMapping("/viewtest")
	 public String myViewTest(){
		 return "viewtest";
	 }
	 
	 /**入参方式1：
	  * 这种方式应该要求前台的参数名称与这里方法入参名称一致
	 * @param stringIn
	 * @return
	 */
	@RequestMapping({"/hello11"})  
	 public String hello(String stringIn){   //此时在浏览器中输入 {url}/hello11?stringIn=AAA  
	 System.out.println("前台传入参数stringIn：" + stringIn);  
	 return "viewtest";   
	 }
	 
	 /** 入参方式2：
	  * 用RequestParam这种方式传参与上面的区别是：这种方式要求必须传入参数，不传的话404
	 * @param reString
	 * @return
	 */
	@RequestMapping({"/hello12"})   // ?inString=AAA,通过这种方式传值、请求  
	 public String view2controller(@RequestParam("inString") String reString){  
	                //(@RequestParam("inString") String reString)  
	     System.out.println("方式2传参inString：" + reString);  
	     return "viewtest";  //mapback to home.jsp  
	 } 
	
	/**出参方式1： context
	 * @param stringIn
	 * @param context
	 * @return
	 */
	@RequestMapping({"/hello13"})  
	public String hello13(String stringIn,Map<String,Object> context){   //Map String Obj. context  
	context.put("stringOut", "myout: 中文"); //add context.put<Stringkey, ObjectValue>  
	System.out.println("出参方式1拿到的入参：" + stringIn);  
	return "viewtest"; 
	//出参在jsp中输出：<p>hello!!，我的出参： ${stringOut}</p>
	}
	
	/**出参方式2：model
	 * @param homenameIn
	 * @param model
	 * @return
	 */
	@RequestMapping({"/home"})  
	public String home(String homenameIn,Model model){   //我们习惯使用Model而不是Map  
		System.out.println("出参方式2拿到的入参：" + homenameIn);  
		model.addAttribute("homeMsg","随便吧出参2haha");//model.addAttribute加入一个分配  
		return "viewtest";    
	}  
	
	/**最原始方式ajax传递接送数据方式1：
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/helloAjax1")
    public void helloAjax1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("入参mypara1：" + request.getParameter("mypara1"));
		System.out.println("入参mypara2：" + request.getParameter("mypara2"));
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String data = "{\"userName\":\"哈哈你好myname\",\"age\":\"139\"}"; //前台弹出的有中文乱码，后面解决
//        response.getWriter().append("hello world222");
		out.print(data);

		/*对应的前台请求：
		 * $.ajax({
			   url:"http://localhost:8080/cmstest/helloAjax1",
			   //url:"/helloAjax1",
			   type:"post",
			   //dataType:"text", //这样返回的就是一个json字符串
			   dataType:"json", //这样返回的就是一个json对象
			   data:{
				   mypara1:"zhangsan",
				   mypara2:"哈哈入参2mypara2"
			   },
			   success:function(responseText){
			     alert(responseText);
			     alert(responseText.age);
			   },
			   error:function(){
			     alert("system error");
			   }
		});*/
    }
	

	/**ajax传递接送数据方式2：PrintWriter
	 * 这种方式缺点：入参不灵活，要一个个写
	 * @param homenameIn
	 * @param out
	 * @return
	 */
	@RequestMapping({"/helloAjax2"})  
	public void helloAjax2(String homenameIn,PrintWriter out){
		System.out.println("ajax传递接送数据方式2拿到的入参homenameIn：" + homenameIn);  
		String data = "{\"userName\":\"哈哈你好myname22\",\"age\":\"13922\"}"; 
		out.print(data);
	}
	
	/**ajax传递接送数据方式3：PrintWriter
	 * 这种方式缺点：事实证明这种方式失败，前台入参没办法自动转为map对象
	 * @param map
	 * @param out
	 * @return
	 */
	@RequestMapping({"/helloAjax3"})  
	public void helloAjax3(/*Map<String,Object> map,*/Map map,PrintWriter out){
		System.out.println(map);
		System.out.println("ajax传递接送数据方式3拿到的入参homenameIn：" + map.get("homenameIn"));  
		String data = "{\"userName\":\"哈哈你好myname33\",\"age\":\"13933\"}"; 
		out.print(data);
	} 
	
	/**ajax传递接送数据方式3-2：PrintWriter
	 * 这种方式可以实现前后台传参皆是json格式
	 * 缺点：入参要定义对应的bean对象(貌似这个bean对象不能自定义构造方法及要实现tostring)
	 * @param homenameIn
	 * @param map
	 * @return
	 */
	@RequestMapping({"/helloAjax3_2"})  
	public void helloAjax3_2(Message message,PrintWriter out){
		System.out.println(message);
		System.out.println("ajax传递接送数据方式3拿到的入参：" + message.getName());  
		String data = "{\"userName\":\"哈哈你好myname33\",\"age\":\"13933_2\"}"; 
		out.print(data);
	}
	
	/**ajax传递接送数据方式4：ResponseBody
	 * 经过测试非常成功，估计出参返回Map也是可以的（另外返回list<MyBean>也是绝对可以的），
	 * 这种方式的好处是返回的json不用自己构造字符串了，可以直接返回list或map对象，内部会自动转换
	 * @param username
	 */
	@RequestMapping({"/helloAjax4"})  
	@ResponseBody
	public List<Map<String,Object>> helloAjax4(String username){
		System.out.println("ajax传递接送数据方式4拿到的入参username：" + username);
		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map1.put("out1", "myout1value");
		map1.put("out2", "myout2value");
		map2.put("out1", "myout1value");
		map2.put("out2", "myout2value");
		list.add(map1);
		list.add(map2);
		return list;
	}
	
	/**ajax传递接送数据方式5: @RequestBody 与  @ResponseBody
	 * 非常成功的一次测试，前台传入json字符串，后台通过@RequestBody转成
	 * 		map或list对象，之后构造list或map返回值，通过@ResponseBody转成json对象返回
	 * 注：这种方式对前台的ajax写法有特殊要求，见下面
	 * @param mapIn
	 * @return
	 */
	@RequestMapping({"/helloAjax5"})  
	@ResponseBody
	public List<Map<String,Object>> helloAjax5(@RequestBody Map<String,Object> mapIn){
		System.out.println("进入这里了111111----");
		System.out.println("ajax传递接送数据方式5拿到的入参mapIn：" + mapIn);
		System.out.println(mapIn.get("mypara2"));
		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map1.put("out1", "myout1value");
		map1.put("out2", "myout2value");
		map2.put("out1", "myout1value");
		map2.put("out2", "myout2value");
		list.add(map1);
		list.add(map2);
		return list;
		
		/*  var para = 
			{
					 username:"zhangsan44",
					 mypara2:"哈哈入参23mypara3"	
			};
			
			$.ajax({
				   url:"http://localhost:8080/cmstest/helloAjax5",
				   type:"post",
				   dataType:"json", //这样返回的就是一个json对象
				   contentType:'application/json;charset=UTF-8',//关键是要加上这行
			       traditional:true,//这使json格式的字符不会被转码
			       data:JSON.stringify(para),
				   success:function(responseText){
				     alert(responseText);
				     alert(responseText[0].out1);
				   },
				   error:function(){
				     alert("system error");
				   }
			});*/
	}
	
	/**ajax传递接送数据方式5_2: @RequestBody 与  @ResponseBody，入参是list
	 * 非常成功的一次测试，前台传入json字符串，后台通过@RequestBody转成
	 * 		map或list对象，之后构造list或map返回值，通过@ResponseBody转成json对象返回
	 * 注：这种方式对前台的ajax写法有特殊要求，见下面
	 * @param mapIn
	 * @return
	 */
	@RequestMapping({"/helloAjax5_2"})  
	@ResponseBody
	public List<Map<String,Object>> helloAjax5_2(@RequestBody List<Map<String,Object>> listIn){
		System.out.println("进入这里了111111----");
		System.out.println("ajax传递接送数据方式5拿到的入参listIn：" + listIn);

		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map1.put("out1", "myout1value");
		map1.put("out2", "myout2value");
		map2.put("out1", "myout1value");
		map2.put("out2", "myout2value");
		list.add(map1);
		list.add(map2);
		return list;
		
		/*		var list =[
					{username:"zhangsan51",mypara2:"哈哈入参mypara51"},
					{username:"zhangsan52",mypara2:"哈哈入参mypara52"},
					{username:"zhangsan53",mypara2:"哈哈入参mypara53"},
					{username:"zhangsan54",mypara2:"哈哈入参mypara56"},
					{username:"zhangsan58",mypara2:"哈哈入参mypara58"}
				];
				$.ajax({
					   url:"http://localhost:8080/cmstest/helloAjax5_2",
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
				});*/
	}
	
	
	//--------------以下是数据库操作测试---------------------------------------------------
	@RequestMapping({"/hellodao1"})  
	@ResponseBody
	public List<Map<String,Object>> helloDao1(String username)/*throws Exception*/{
		System.out.println("dao测试拿到的入参username：" + username);
		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();

//		BaseDao bDao = new BaseDao();
		/*BaseDao bDao = BaseDao.getInstance();
		System.out.println("检查JdbcTemplate是否为空：" + bDao.getJdbcTemplate());  //不为null
		System.out.println("检查用注入方式得到的JdbcTemplate是否为空：" + baseDao.getJdbcTemplate());//不为null
		if(bDao == baseDao){
			System.out.println("两个对象相同");
		}else{
			System.out.println("两个对象不一样？？？");
		}
//		bDao.testTrans(); //这个没有效果
//		baseDao.testTrans(); //这个有效果
		bDao.testTrans2(); //这个有效果
*/		Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map1.put("out1", "myout1value");
		map1.put("out2", "myout2value");
		map2.put("out1", "myout1value");
		map2.put("out2", "myout2value");
		list.add(map1);
		list.add(map2);
		return list;
	} 
	
	/**测试读取properties配置文件的变量
	 * @return
	 */
	@RequestMapping({"/helloProper"})  
	@ResponseBody
	public List<Map<String,Object>> helloProper(){
		
		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
		
		//1.测试读取properties配置文件的变量
		/*System.out.println("websName:" + websName);
		System.out.println("websUrl:" + websUrl);
		System.out.println("websName2:" + websName2);
		System.out.println("websUrl2:" + websUrl2);*/
		
		//2.测试获取spring对象bean实例 ，不能用这种方式，应该是web.xml中没有配置ContextLoaderListener
		Object o1 = SpringBeanManager.getInstance().getSpringBean("jdbcTemplate");
		System.out.println("获取到的spring对象为：" + o1);
		
		return list;
	}
	 
}
