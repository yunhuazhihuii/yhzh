var express = require('express');
var bodyParser = require('body-parser');
var app=express();
var server = require('http').createServer(app).listen(9000);


var io = require('socket.io').listen(server);
// var redis = require("redis")
// // 用来缓存的客户端  存储一对一消息
// var sigle = redis.createClient()
// // 用来缓存的客户端  存储群组消息
// var store = redis.createClient()

//通过bodyParser中间件分析 application/x-www-form-urlencoded和application/json请求，并把变量存入req.body，这种我们才能够获取到！
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
  // 配置允许跨域请求；
  app.all('*', function (req, res, next) {
    res.header('Access-Control-Allow-Origin', '*')
    res.header('Access-Control-Allow-Headers', 'Content-Type,Content-Length, Authorization, Accept,X-Requested-With')
    res.header('Access-Control-Allow-Methods', 'PUT,POST,GET,DELETE,OPTIONS')
    res.header('X-Powered-By', ' 3.2.1')
    next()
  })

 //  // 链接错误
 // redis.createClient().on("error", function (err) {
 //    console.log("Error " + err);
 //  });
 // // 链接成功
 // redis.createClient().on('connect', function(){  
 //   console.log('Redis连接成功.');  
 //  })  
  //用户login数据库操作
  var users = require('./routers/userApi');
  app.use('/', users);
  
   //智能照明接口
  var illumination = require('./routers/ZMApi');
  app.use('/', illumination);

  //空调系统接口
  var airConditioner = require('./routers/airConditionerApi');
  app.use('/', airConditioner);

  //报警系统接口 chenms 2018-9-15
  var alarm = require('./routers/alarmApi.js');
  app.use('/', alarm);

 
  /**
    *聊天系统  私聊和群聊
   * */
  var usocket = {},user = [];

   //socket
  // io.on('connection', function (socket) {
  //    console.log('websecket连接成功了')
  //    console.log(socket.id)
  //    //成员对象数组
  //   socket.on('new user', (username) => {
  //     if(!(username in usocket)) {
  //       socket.username = username;
  //       usocket[username] = socket;
  //       user.push(username);
  //       // 获取离线信息到客户端
  //       send_store_msg(socket, username)
  //      socket.broadcast.emit('user joined',username);
  //     }
  //       console.log("添加用户");
  //       console.log(username);
  //       console.log(user);
  //   })
  //  // 添加群组
  //  socket.on('join', function (group) {
  //     var roomid=group.group.username
  //      var length=group.group.chatName
  //       for (var i = 0; i <= length.length - 1; i++) {
  //         if(length[i] in usocket) {
  //           usocket[length[i]].join(roomid); //加入房间 
  //           usocket[length[i]].emit('addGroup',group.group)//添加分组到其他成员
  //         }else{
  //           var groups=JSON.stringify(group.group)
  //           console.log("离线用户")
  //           console.log(length[i])
  //           // 保存离线信息到客户端
  //           store.hset("chat_history", length[i], groups, function (e, r) {})
  //         }
  //       } 
  //     })
  //   // 获取离线信息
  //   function send_store_msg(socket, username) {
  //     // 群聊的离线消息
  //     store.hget("chat_history", username, function (e, msg) {
  //       if (msg) {
  //           // 发送添加创建群组离线信息
  //           socket.emit('login',msg);
  //           console.log("加入的聊天室")
  //           console.log(JSON.parse(msg).username)
  //           // 离线用户进入加入聊天室
  //           socket.join(JSON.parse(msg).username); //加入房间 
  //           // 删除离线信息
  //           store.hdel("chat_history", username, function (e, r) {
  //           })
  //       }
  //     })
  //     // 来自聊天信息
  //     sigle.hget("sigle_history", username, function (e, msg) {
  //       if (msg) {
  //            // 离线用户的信息
  //           socket.emit('sigle_login',eval('(' + msg + ')'));
  //            console.log("用户离线信息")
  //            console.log(msg)
  //           // 删除离线信息
  //           store.hdel("sigle_history", username, function (e, r) {
  //           })
  //       }
  //     })
  //   };
  //   //发信息
  //  socket.on('send private message', function(res){
  //     //一对一私发信息 
  //     if(res.recipient in usocket) {
  //       // 如果用户在线就直接发送
  //       usocket[res.recipient].emit('receive private message', res);
  //     }else{
  //       // 消息来自谁的用户名
  //       var addresser=res.addresser;
  //        // 离线用户名
  //       var recipient=res.recipient;
  //       console.log("追加离线信息")
  //       console.log(recipient)
  //       // 先查找数据库 追加离线信息
  //        sigle.hget("sigle_history",recipient, function (e, msg) {
  //          if(msg){
  //           // 转成数组
  //           let newList=eval('(' + msg + ')');
  //           // 追加离线信息
  //           newList.push({addresser:res});
  //           // 用户如果不在线就保存数据到redis
  //           sigle.hset("sigle_history", res.recipient ,  JSON.stringify(newList), function (e, r) {})
  //          }else{
  //           let newList=[]
  //           // 追加离线信息
  //           newList.push({addresser:res});
  //           // 用户如果不在线就保存数据到redis
  //           sigle.hset("sigle_history", res.recipient , JSON.stringify(newList), function (e, r) {})  
  //          }
  //        })
  //     }
  //    //往对应房间广播消息
  //    io.to(res.recipient).emit('message',res);
  //   });

  //  //用户退出
  // socket.on('disconnect', function(){
  //   console.log('用户退出')
  //   console.log(socket.username)
  //    //移除
  //     if(socket.username in usocket){
  //       delete(usocket[socket.username]);
  //       user.splice(user.indexOf(socket.username), 1);
  //     }
  //     console.log("移除");
  //     console.log(user);
  //     socket.broadcast.emit('user left',socket.username)
  //   })
  // })