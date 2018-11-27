var express = require('express');
var app=express();
var mysql = require('mysql');
var databaseConfig = require('./database.config.js');  //引入数据库配置模块中的数据

module.exports ={
	// 获取登录数据
	query:function(sql,params,callback){
	//每次使用的时候需要创建链接，数据操作完成之后要关闭连接
    var connection = mysql.createConnection(databaseConfig);
		 connection.connect(function(err){
	        if(err){
	        console.log('数据库链接失败');
	        throw err;
	       }
	    console.log("数据库链接成功")
	    //开始数据操作
		connection.query(sql,params, function(err,data){
               if(err){
                console.log('数据操作失败');
                throw err;
            }
             //将查询出来的数据返回给回调函数
            callback && callback(data);
          })
		 //停止链接数据库，必须再查询语句后，要不然一调用这个方法，就直接停止链接，数据操作就会失败
        connection.end(function(err){
              if(err){
                  console.log('关闭数据库连接失败！');
                  throw err;
              }
          });
	   })
  }
}