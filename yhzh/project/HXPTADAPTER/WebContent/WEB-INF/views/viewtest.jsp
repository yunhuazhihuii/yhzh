<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <script type="text/javascript" src="../../otherlib/easyui/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="E:\spaceall\spacegit\git\yhzh\project\cmstest\WebContent\otherlib\easyui\jquery.min.js"></script> -->
<script type="text/javascript" src="http://localhost:8080/HXPTADAPTER/otherlib/easyui/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function ajaxTest(){
		//alert(11);
		
		$.ajax({
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
		});
	}
	
	function ajaxTest2(){
		$.ajax({
			   url:"http://localhost:8080/cmstest/helloAjax2",
			   type:"post",
			   //dataType:"text", //这样返回的就是一个json字符串
			   dataType:"json", //这样返回的就是一个json对象
			   data:{
				   homenameIn:"zhangsan22",
				   mypara2:"哈哈入参2mypara2"
			   },
			   success:function(responseText){
			     alert(responseText);
			     alert(responseText.age);
			   },
			   error:function(){
			     alert("system error");
			   }
		});
	}
	
	function ajaxTest3(){
		$.ajax({
			   url:"http://localhost:8080/cmstest/helloAjax3",
			   type:"post",
			   //dataType:"text", //这样返回的就是一个json字符串
			   dataType:"json", //这样返回的就是一个json对象
			   data:{
				   homenameIn:"zhangsan33",
				   mypara2:"哈哈入参23mypara3"
			   },
			   success:function(responseText){
			     alert(responseText);
			     alert(responseText.age);
			   },
			   error:function(){
			     alert("system error");
			   }
		});
	}
	
	function ajaxTest3_2(){
		$.ajax({
			   url:"http://localhost:8080/cmstest/helloAjax3_2",
			   type:"post",
			   //dataType:"text", //这样返回的就是一个json字符串
			   dataType:"json", //这样返回的就是一个json对象
			   data:{
				   name:"zhangsan33",
				   text:"哈哈入参23mypara3"
			   },
			   success:function(responseText){
			     alert(responseText);
			     alert(responseText.age);
			   },
			   error:function(){
			     alert("system error");
			   }
		});
	}
	
	function ajaxTest4(){
		$.ajax({
			   url:"http://localhost:8080/cmstest/helloAjax4",
			   type:"post",
			   //dataType:"text", //这样返回的就是一个json字符串
			   dataType:"json", //这样返回的就是一个json对象
			   data:{
				   username:"zhangsan44",
				   mypara2:"哈哈入参23mypara3"
			   },
			   success:function(responseText){
			     alert(responseText);
			     alert(responseText[0].out1);
			   },
			   error:function(){
			     alert("system error");
			   }
		});
	}
	
	function ajaxTest5(){
		var map = [];
		map.username = "zhangsan55";
		map.mypara2 = "哈哈入参mypara55";
		var para = 
		{
				 username:"zhangsan44",
				 mypara2:"哈哈入参23mypara3"	
		};
		/* {
			map: map
		} */
		
		$.ajax({
			   url:"http://localhost:8080/cmstest/helloAjax5",
			   type:"post",
			   //dataType:"text", //这样返回的就是一个json字符串
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
		});
	}
	
	function ajaxTest5_2(){
		var list =[
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
		});
	}
	
	/**
	RestController测试1，非常成功
	*/
	function ajaxTest6(){
		var list =[
			{username:"zhangsan61",mypara2:"哈哈入参mypara61"},
			{username:"zhangsan62",mypara2:"哈哈入参mypara62"},
			{username:"zhangsan63",mypara2:"哈哈入参mypara63"},
			{username:"zhangsan64",mypara2:"哈哈入参mypara66"},
			{username:"zhangsan68",mypara2:"哈哈入参mypara68"}
		];
		$.ajax({
			   url:"http://localhost:8080/HXPTADAPTER/helloAjax6",
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
	}
	
	/**
	RestController测试2,失败
	*/
	function ajaxTest6_2(){
		var list =[
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
		});
	}
	
	/**
	RestController测试3
	*/
	function ajaxTest6_3(){
		$.ajax({
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
		});
	}
	
	//--------------以下是数据库操作测试---------------------------------------------------
	function hellodao1(){
		$.ajax({
			   url:"http://localhost:8080/cmstest/hellodao1",
			   type:"post",
			   //dataType:"text", //这样返回的就是一个json字符串
			   dataType:"json", //这样返回的就是一个json对象
			   data:{
				   username:"到dao1",
				   mypara2:"哈哈入参23mypara3"
			   },
			   success:function(responseText){
			     alert(responseText);
			     alert(responseText[0].out1);
			   },
			   error:function(){
			     alert("system error");
			   }
		});
	}
	//随便为了触发后台测试
	function hellodao1(){
		$.ajax({
			   url:"http://localhost:8080/cmstest/helloProper",
			   type:"post",
			   //dataType:"text", //这样返回的就是一个json字符串
			   dataType:"json", //这样返回的就是一个json对象
			   data:{
				   username:"到dao1",
				   mypara2:"哈哈入参23mypara3"
			   },
			   success:function(responseText){
			     alert(responseText);
			   },
			   error:function(){
			     alert("system error");
			   }
		});
	}
	
</script>
</head>
<body>
	just test 随便测试个东西	viewtest11
	
	<%-- <p>hello!!，我的出参1： ${stringOut}</p> --%>
	<p>hello!!，我的出参2： ${homeMsg}</p>
	<div>
		<input type="button" id="mybutton" value="按我ajax" onclick="ajaxTest6_3()"></input>
	</div>
</body>
</html>