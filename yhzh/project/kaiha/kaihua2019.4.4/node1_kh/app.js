var express = require('express');
var bodyParser = require('body-parser');
var app=express();
var server = require('http').createServer(app).listen(9000);

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

// 配置允许跨域请求；
app.all('*', function (req, res, next) {
  res.header('Access-Control-Allow-Origin', '*')
  res.header('Access-Control-Allow-Headers', 'Content-Type,Content-Length, Authorization, Accept,X-Requested-With')
  res.header('Access-Control-Allow-Methods', 'PUT,POST,GET,DELETE,OPTIONS')
  res.header('X-Powered-By', ' 3.2.1')
  next()
});

//用户login数据库操作
var users = require('./routers/userApi');
app.use('/', users);

//设备信息
var publicApi = require('./routers/publicApi');
app.use('/', publicApi);
 
var usocket = {},user = [];

  