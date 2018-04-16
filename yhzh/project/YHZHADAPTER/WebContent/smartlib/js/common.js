/*
* 公共js
* Create By chenmingshuai 2017-9-16
* Copyright (c) 2017-2057 yunhuazhihui
*/
$.namespace = function() {  
	var a = arguments, o = null, i, j, d;    
	for (i = 0; i < a.length; i++) {        
		d = a[i].split("."); o = window;        
		for (j = 0;j < d.length; j++) {            
			o[d[j]] = o[d[j]] || {};            
			o = o[d[j]];        
        }    
    }    
    return o;
};

var common = {};

//新产品名称空间
//var $cms = $.namespace('sun');

//公共变量
common.vars = {
//           	rootPath: null
			varSep: "&#"
		   };
/**
 * 封装ajax
 * 注意必须要用到上面的namespace，直接写$cms.ajax=function没有用
 */
common.ajax=function(opt,retype,fmtfun,cbfun,erfun){
	opt = opt || {};
	var rs ;
	opt.async = opt.async === true ? true : false;
	
	if(opt.url){
		$.ajax({
			type     : opt.ajaxType === 'get'  || opt.ajaxType === 'GET' ? 'get' : 'post',
			url      : opt.url,
			data     : opt.data || null,
			dataType : opt.dataType || "json",	
			//async    : opt.async === true ? true : false,
			async    : opt.async,
			//不允许使用缓存
			cache : false,
//			success  : $.isFunction(opt.success) ? opt.success : function(result) {
			success  : function(result) {
				if(result == null)return;
				if($.isFunction(opt.success)){
//					alert(result[0].cataname);//json是中文的
					//统一转化在调用回调函数
//					var str = JSON.stringify(result); stringify会把中文转Unicode
//					//Unicode转中文
//				    str = unescape(str.replace(/\\/g,'%')); //成功，只要这一句
					opt.success(result);
				}
//				if(result.success  == "1"){
//					if(erfun != null && $.isFunction(erfun)){
//						erfun(result,opt);
//					}
//					$xcp.dispAjaxError(result); //显示异常错误
//				}else{
//					rs = result.outEntity;
//					if(retype != null  && retype.toLowerCase() === 'array'){
//						if(rs && !$.isArray(rs)){
//							rs = [rs];
//						}						
//					}
//					
//					if(fmtfun != null && $.isFunction(fmtfun)){
//						fmtfun(rs);
//					}
//					
//					if(cbfun != null && $.isFunction(cbfun)){
//						//对于异步处理，需要请求端传递一个回调函数，
//						cbfun(rs,opt,result);
//					}					
//				}
			},
			beforeSend : function(XMLHttpRequest, textStatus){
				
			},
			error : $.isFunction(opt.error) ? opt.error : function(XMLHttpRequest, textStatus, errorThrown){
			}
		});
	}
	
	//如果是同步请求，则返回结果
//	if(opt.async === false){
//		return rs;
//	}else{
//		return null;
//	}
};

/**
 * 设置session变量
 */
common.setSession=function(key,value){
	$cms.ajax({  
		type:'POST',  
		url:'mylib/action/controller.php',  
		data:{
			key: key,
			value: value,
			phpfile:'pubManager',
			manager:'PubManager',
			todo:'setSession'},  
		dataType:'json',  
		timeout:3000,  
		cache:false,  
		async:false,
		 success:function(data){
//			 alert("设置session成功！");
		 },  
		 error:function(msg){  
			 alert("设置session失败:" + JSON.stringify(msg));
		 }  
	});
};
/**
 * 获取session值
 */
common.getSession=function(key,defValue){
	var sValue;
	$cms.ajax({  
		type:'POST',  
		url:'mylib/action/controller.php',  
		data:{
			key: key,
			defValue: defValue,
			phpfile:'pubManager',
			manager:'PubManager',
			todo:'getSession'},  
		dataType:'json',  
		timeout:3000,  
		cache:false,  
		async:false,
		 success:function(data){
			 sValue = data;
		 },  
		 error:function(msg){  
			 alert("获取session error:" + JSON.stringify(msg));
		 }  
	});
	return sValue;
};

/**
 * 字符串加密
 * 注意str才是密码，pwd是标志，要与解密函数中的pwd一致
 */
common.encrypt=function(str, pwd) {
	  if(pwd == null || pwd.length <= 0) {
	    alert("Please enter a password with which to encrypt the message.");
	    return null;
	  }
	  var prand = "";
	  for(var i=0; i<pwd.length; i++) {
	    prand += pwd.charCodeAt(i).toString();
	  }
	  var sPos = Math.floor(prand.length / 5);
	  var mult = parseInt(prand.charAt(sPos) + prand.charAt(sPos*2) + prand.charAt(sPos*3) + prand.charAt(sPos*4) + prand.charAt(sPos*5));
	  var incr = Math.ceil(pwd.length / 2);
	  var modu = Math.pow(2, 31) - 1;
	  if(mult < 2) {
	    alert("Algorithm cannot find a suitable hash. Please choose a different password. \nPossible considerations are to choose a more complex or longer password.");
	    return null;
	  }
	  var salt = Math.round(Math.random() * 1000000000) % 100000000;
	  prand += salt;
	  while(prand.length > 10) {
	    prand = (parseInt(prand.substring(0, 10)) + parseInt(prand.substring(10, prand.length))).toString();
	  }
	  prand = (mult * prand + incr) % modu;
	  var enc_chr = "";
	  var enc_str = "";
	  for(var i=0; i<str.length; i++) {
	    enc_chr = parseInt(str.charCodeAt(i) ^ Math.floor((prand / modu) * 255));
	    if(enc_chr < 16) {
	      enc_str += "0" + enc_chr.toString(16);
	    } else enc_str += enc_chr.toString(16);
	    prand = (mult * prand + incr) % modu;
	  }
	  salt = salt.toString(16);
	  while(salt.length < 8)salt = "0" + salt;
	  enc_str += salt;
	  return enc_str;
	}
/**
 * 字符串解密
 * 注意str才是密码，pwd是标志，要与加密函数中的pwd一致
 */
common.decrypt=function(str, pwd) {
	  if(str == null || str.length < 8) {
	    alert("A salt value could not be extracted from the encrypted message because it's length is too short. The message cannot be decrypted.");
	    return;
	  }
	  if(pwd == null || pwd.length <= 0) {
	    alert("Please enter a password with which to decrypt the message.");
	    return;
	  }
	  var prand = "";
	  for(var i=0; i<pwd.length; i++) {
	    prand += pwd.charCodeAt(i).toString();
	  }
//	  alert("该步骤："+prand);
	  var sPos = Math.floor(prand.length / 5);
//	  alert("该步骤："+sPos);
	  ////js 字符串下标也是从0开始的，但超出下标返回空，而不会报错
	  var mult = parseInt(prand.charAt(sPos) + prand.charAt(sPos*2) + prand.charAt(sPos*3) + prand.charAt(sPos*4) + prand.charAt(sPos*5)); 
//	  alert("该步骤："+mult);
	  var incr = Math.round(pwd.length / 2);
	  var modu = Math.pow(2, 31) - 1;
//	  alert("该步骤："+modu);
	  var salt = parseInt(str.substring(str.length - 8, str.length), 16);
//	  alert("该步骤："+salt);
	  str = str.substring(0, str.length - 8);
	  prand += salt;
//	  alert("该步骤："+prand);
	  while(prand.length > 10) {
//		alert(prand.substring(0, 10));
//		alert(prand.substring(10, prand.length));
	    prand = (parseInt(prand.substring(0, 10)) + parseInt(prand.substring(10, prand.length))).toString();
//	    alert(prand);
	  }
//	  alert("该步骤："+prand);
//	  alert("该步骤prand："+prand);
//	  alert("mult："+mult +";prand："+prand+";incr："+incr+";modu："+modu);
	  prand = (mult * prand + incr) % modu;
//	  alert("该步骤："+prand);
	  var enc_chr = "";
	  var enc_str = "";
	  for(var i=0; i<str.length; i+=2) {
//		alert("循环中substring："+str.substring(i, i+2));
	    enc_chr = parseInt(parseInt(str.substring(i, i+2), 16) ^ Math.floor((prand / modu) * 255));
//		alert("循环中enc_chr："+enc_chr);
	    enc_str += String.fromCharCode(enc_chr);
//		alert("循环中mult："+mult +";循环中prand："+prand+";循环中incr："+incr+";循环中modu："+modu);
	    prand = (mult * prand + incr) % modu;
//		alert("循环中prand："+prand);
	  }
	  return enc_str;
	}

common.strIsNull=function(str1){
	var result = false;
	if(null == str1 || undefined == str1 || "" == $.trim(str1) || "null" == $.trim(str1)){
		result = true;
	}
	return result;
}

common.setCookie=function(name,value) {//两个参数，一个是cookie的名子，一个是值
    var Days = 30; //此 cookie 将被保存 30 天
    var exp = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

common.getCookie=function(name) {//取cookies函数 
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
//    alert(unescape(arr[2]));
     if(arr != null) return unescape(arr[2]); return null;
}


/**获取国际化方法，一般用在获取combobox下拉数据
 * @param sql 查询sql语句，如果为空，则查询palan表
 * @param tabName 国际化类型
 * @param url
 * @return json数组，如data: [{label: '0-屏蔽',value: '0'},{label: '1-普通',value: '1'}]
 */
common.getPalan = function (sql,tabName,url){
	
}



