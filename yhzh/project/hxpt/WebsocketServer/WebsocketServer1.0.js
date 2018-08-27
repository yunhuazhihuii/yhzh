/**
 * Created by Administrator on 2017/10/30.
 *
 */
var fs = require('fs');
var path = require('path');
//js的调用
var getDate = require('./method/date.js');
var storageSubTable = require('./method/storageSubTable.js');
var findDotTable = require('./method/findDotTable.js');
var storageDotTable = require('./method/storageDotTable.js');
var findRegisterTable = require('./method/findRegisterTable.js');
var writelog = require('./method/writelog.js');
var findSubscribe = require('./method/findSubscribe.js');
var allDatasToHTML = require('./method/allDatasToHTML.js');
var getIPAdress = require('./method/getIPAdress.js');

//restfulApi的设计
var restfulFindController = require('./method/restfulFindController.js');
var arrayRemoveDup = require('./method/arrayRemoveDub.js');
//var getDatas = require('./method/getDatas.js');
//var sendToHtml = require('./restful/sendToHtml.js');
//var dotsSearch = require('./restful/dotsSearch.js');
//var controllersSearch = require('./restful/controllersSearch.js');
var adaptersSearch = require('./restful/adaptersSearch.js');
var controlsAndDots = require('./restful/controlsAndDots.js');


//module的调用
var express = require('express');
var bodyParser = require('body-parser');
//端口和ip
var s_HOST = getIPAdress();
var s_PORT = '8090';
var app = express();

//通过bodyParser中间件分析 application/x-www-form-urlencoded和application/json请求，并把变量存入req.body，这种我们才能够获取到！
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

var http = require('http').createServer(app);
//var http = require('http');
//var server = http.createServer(app);
var io = require('socket.io')(http);
//监听端口
/*app.set('port', process.env.PORT || s_PORT);
var server = server.listen(app.get('port'), function() {
    console.log('start at ip:'+s_HOST+':' + server.address().port);
});*/
app.set('port', process.env.PORT || s_PORT);
var server = http.listen(app.get('port'), function() {
    console.log('start at ip:'+s_HOST+':' + server.address().port);
});
// 配置允许跨域请求；
app.all('*', function (req, res, next) {
    res.header('Access-Control-Allow-Origin', '*')
    res.header('Access-Control-Allow-Headers', 'Content-Type,Content-Length, Authorization, Accept,X-Requested-With')
    res.header('Access-Control-Allow-Methods', 'PUT,POST,GET,DELETE,OPTIONS')
    res.header('X-Powered-By', ' 3.2.1')
    next()
})

//日志文件写入到屏幕或者日志文件中，console是输出到屏幕，log是写日志，none是不输出
//var s_order = 'log';
var s_order = 'console';

/*//连接sqlite3数据库
var sqlite = require('sqlite3');
//db数据只连接一次
var db = new sqlite.Database('./database/test.db');*/
//连接redis数据库
var redis = require('redis'),
    RDS_POST = 6379,
    RDS_HOST = '127.0.0.1',
    RDS_PASS = '654321',//设置密码的redis
    RDS_OPTS = {},
    redisClient = redis.createClient(RDS_POST,RDS_HOST,RDS_OPTS);
redisClient.auth(RDS_PASS,function(){
     writelog(s_order,'redis通过认证');
});

//建立与适配器的事件，获取所有点的属性，事件名为：getAllDotsAttribute,获取数据后存入redis中，存入的名称为setAllDotsAttribute
//刷新---向适配器获取数据的接口为http://172.16.8.2:8080/YHZHADAPTER/rest/holiday/getpoint，页面获取数据的接口为/refleshDatas
//获取该适配器下的点的属性接口为/getDatas
app.get("/getDatas",function(req,res){
    console.log("输出的结果");
    console.log(req.query.id);
});


var JSONTOHTML = [];//用来存放返回给前端页面的数据
app.get("/refleshDatas",function(req1,res1){
    //var JSONTOHTML = [];//用来存放返回给前端页面的数据
    //读取json文件发送给前端
   /* fs.readFile(path.join('./json/', 'serverToHTML.json'),'UTF8', function (err,bytesRead) {
        if (err) throw err;
        console.log("读取文件成功！");
        //res1.end(JSON.stringify(bytesRead));
        console.log(bytesRead);
        res1.end(bytesRead);
    });*/

    //get 请求获得适配器相关数据
    http.get('http://172.16.8.2:8080/YHZHADAPTER/rest/holiday/getpoint',function(req,res){
        var html='';
        req.on('data',function(data){
            html+=data;
        });
        req.on('end',function(){
            //console.log("获得适配器点的属性：");
            var jsonString = JSON.parse(html);
            //console.log(jsonString.data);
            var dotAttr = jsonString.data;
            var adapters = [];//用来存放适配ID
            //遍历发过来的点值，并存入redis数据库中的点表中，新增两个属性，点名称dotname和点类型dottype
            //'{"adapterId":"' + s_adapterId + '","ncId":"' + s_ncId + '","dotId":"' + s_dotId + '","dotValue":"' + s_dotValue + '","dotName":"' + dotName + '","dotType":"' + dotType + '","valueUpdateTime":"' + t_datetime + '"}';
            dotAttr.forEach(function(item) {
                //console.log(item);
                //获得时间
                var t_datetime = getDate();
                var s_name = item.name;
                var s_item = s_name.split('.');
                var s_adapterId = s_item[0];
                var s_ncId = s_item[1];
                var s_dotId = s_item[2];
                var s_dotValue = item.value;
                var s_dotType = item.ioproperty;
                //对象编号，对象名称，网络控制器名称,适配器名称
                 var s_str = '{"adapterId":"' + s_adapterId + '","ncId":"' + s_ncId + '","dotId":"' + s_dotId + '","dotValue":"' + s_dotValue + '","dotName":"' + s_name + '","dotType":"' + s_dotType + '","valueUpdateTime":"' + t_datetime + '"}';
                //console.log(s_str)
                //将数据存入点表中
                //storageDotTable(s_name,s_str,redisClient);
                //将一级目录返回给前端
                if(s_ncId == 1100000000000005){
                    adapters.push('A002');
                }
                adapters.push(s_adapterId);
                if(adapters.length == dotAttr.length){
                    var adaptersDub = arrayRemoveDup(adapters);
                    console.log(adaptersDub);
                    var adaptersChildren = [];
                    var s_string1 = {id:1, text:"适配器", iconCls:"icon-channels", children:null};
                    //遍历adaptersDub，组装json数组
                    for(var i = 0;i<adaptersDub.length;i++){
                        //获得id的值
                        var i_id = s_string1.id;
                        var s_string2 = {id:i_id*10 +i+1, text:adaptersDub[i], iconCls:"icon-feed",attributes:{"url":"http://"+s_HOST+":"+s_PORT+"/getDatas?id="+adaptersDub[i]}};
                        //var s_string2 = {id:i_id*10 +i+1, text:adaptersDub[i], iconCls:"icon-feed",attributes:{"url":"http://"+s_HOST+":"+s_PORT+"/getDatas"}};
                        //var s_string2 = {id:i_id*10 +i+1, text:adaptersDub[i], iconCls:"icon-feed",attributes:{"url":"http://localhost:8090/getDatas"}};
                        console.log("输出的格式");
                        //console.log(s_string2);
                        adaptersChildren.push(s_string2);
                        if(adaptersChildren.length == adaptersDub.length){
                            s_string1.children = adaptersChildren;
                            JSONTOHTML.push(s_string1);
                            //res1.end(JSON.stringify(JSONTOHTML));
                            //将JSONTOHTML写入一个文档
                            var s_path = './json/';
                            var s_fileName = 'serverToHTML.json';
                            //创建一个可读流
                           /*
                           //第一种方式
                            var writerStream = fs.createWriteStream(s_path + s_fileName,{'flags': 'a'});
                            // 使用 utf8 编码写入数据
                            writerStream.write(JSON.stringify(JSONTOHTML),'UTF8');*/
                            //第二种方式
                            fs.writeFile(path.join(s_path, s_fileName), JSON.stringify(JSONTOHTML),'UTF8', function (err) {
                                if (err) throw err;
                                console.log("写入json文件成功");
                            });
                        }
                    }
                }
            })
        })
    })

    //获得相关的用户数据
    var s_string2 ={id:2, text:"连接用户", iconCls:"icon-channels",children: [{id:21, text:"用户1", iconCls:"icon-feed"},{id:22, text:"用户2",iconCls:"icon-feed"}]};
    JSONTOHTML.push(s_string2);

    //获得订阅主题
    var s_string3 ={id:3, text:"订阅主题",iconCls:"icon-channels", children: [{id:31,text:"订阅主题1", iconCls:"icon-feed"},{ id:32,text:"订阅主题2",iconCls:"icon-feed"}]};
    JSONTOHTML.push(s_string3);

    //获得日志
    var s_string4 ={id:4, text:"日志",iconCls:"icon-channels", children: [{id:41,text:"日志1", iconCls:"icon-feed"},{ id:42,text:"日志2",iconCls:"icon-feed"}]};
    JSONTOHTML.push(s_string4);
})


/*app.get("/refleshDatas",function(req1,res1){
    //get 请求外网
    http.get('http://172.16.8.2:8080/YHZHADAPTER/rest/holiday/getpoint',function(req,res){
        var html='';
        req.on('data',function(data){
            html+=data;
        });
        req.on('end',function(){
            //console.log("获得适配器点的属性：");
            //console.log(html);
            var jsonString = JSON.parse(html);
            //console.log(jsonString.data);
            var dotAttr = jsonString.data;
            //遍历发过来的点值，并存入redis数据库中的点表中，新增两个属性，点名称dotname和点类型dottype
            //'{"adapterId":"' + s_adapterId + '","ncId":"' + s_ncId + '","dotId":"' + s_dotId + '","dotValue":"' + s_dotValue + '","dotName":"' + dotName + '","dotType":"' + dotType + '","valueUpdateTime":"' + t_datetime + '"}';
           var dotArray = [];
           var adapters = [];//用来存放适配ID
           var controllers = [];//用来存放适配ID
           var dots = [];//用来存放适配ID
            // 各个适配器.控制器下的点的个数{"name":"适配器.控制器","dotCounts":"value"}
            //var controlsAndDots = [];
            dotAttr.forEach(function(item){
                //console.log(item);
                //获得时间
                var t_datetime = getDate();
                var s_name = item.name;
                var s_item = s_name.split('.');
                var s_adapterId = s_item[0];
                var s_ncId = s_item[1];
                var s_dotId = s_item[2];
                var s_dotValue = item.value;
                var s_dotType = item.ioproperty;
                //对象编号，对象名称，网络控制器名称,适配器名称
                //var s_adaptername = "";
                //var s_objCode = "";
                //var s_objName = "";
                //var s_netControlName = "";
                var s_str = '{"adapterId":"' + s_adapterId + '","ncId":"' + s_ncId + '","dotId":"' + s_dotId + '","dotValue":"' + s_dotValue + '","dotName":"' + s_name + '","dotType":"' + s_dotType + '","valueUpdateTime":"' + t_datetime + '"}';
                //console.log(s_str)
                //将数据存入点表中
                //storageDotTable(s_name,s_str,redisClient);
                //组装树形json数据发送给页面
                //{ name: 'A001.000001.1000000000000001',value: '1',ioproperty: 'DI' }
                dotArray.push(item);
                adapters.push(s_adapterId);
                controllers.push(s_ncId);
                dots.push(s_dotId);
                var restFulJson=[];//用来传送数据给前端
                if(dotArray.length ==dotAttr.length ){
                    var adaptersDub = arrayRemoveDup(adapters);
                    var controllersDub = arrayRemoveDup(controllers);
                    var dotsDub = arrayRemoveDup(dots);

                    adaptersSearch(controllersDub,dotArray, restFulJson, adaptersDub, res1,dotsDub,redisClient);
                }
            });
        });
    });
});*/


//接口的设计，根据适配器的id查询控制器，根据控制器的id查询点id
//var alldatas = require('./routes/alldatas.js');
//app.get('/datas',alldatas);
/*app.get('/datas',function(req,res){
    //查找所有适配器
    redisClient.KEYS('registerTable:'+'A*',function(err,reply){
        if(err){
            writelog(s_order,"查询注册表时出现错误：" + err);
        }else{
            writelog(s_order,"查询注册表成功：" + reply);
            //res.end(JSON.stringify(reply));
            var restfulJSon = [];//发送给前端页面的数据
            var adapters = [];//只是用来装adapterID
            reply.forEach(function(registerAdapterId){
                var s_registerArr = registerAdapterId.split(':');
                var s_adaptId = s_registerArr[1];
                adapters.push(s_adaptId);
                writelog(s_order,"得到适配id的数组：");
                writelog(s_order,adapters);
                if(reply.length == adapters.length){
                    writelog(s_order,"得到适配id的去重数组：");
                    var adaptersDub = arrayRemoveDup(adapters);
                    writelog(s_order,adaptersDub);
                    for(var i= 0;i<adaptersDub.length;i++){
                        writelog(s_order,"循环便利");
                        writelog(s_order,adaptersDub[i]);
                        //根据适配器的id从点表中查询控制器的id
                        restfulFindController(redisClient,adaptersDub[i],restfulJSon,res,i+1);
                    }
                }
            })
        }
    })
});*/

//编写server页面的接口
//返回一级目录以及所有注册的适配器










//监听连接事件
io.sockets.on('connection',function(client){
    /*    //自增连接数
    redisClient.incr("countConnecting", function (error, reply) {
        if (error) {
            //reject();
            writelog(s_order, "自增连接数据失败" + error);
        } else {
            writelog(s_order, "自增连接数据成功" + reply);
        }
    });*/

    //获取客户端的ip和port
    var s_ipString = client.request.connection.remoteAddress;
    var s_clientIpAddress = s_ipString.substring(7);
    var s_clientPort = client.request.connection.remotePort;
    var s_socketId = client.id;
    writelog(s_order,s_clientIpAddress+":"+s_clientPort +"连接成功！");
    //客户端一连接，就将所有的信息发给前端用户，事件名：AllDatas，从点表中查询数据，发送给前端页面，首先得到所有的key值
    var datas = [];
  /*  redisClient.KEYS("dotTable"+":A"+"*",function(err,reply1) {
        //console.log(reply1);
        //根据键值找到所有的值，然后进行集合
        var i_length = reply1.length;
        //console.log(i_length);
        allDatasToHTML(reply1,i_length,datas,redisClient,io);
        });*/
    //监听注册事件，获取注册信息
    client.on('clientToS',function(data){
        //判别客户端连接类型
        var ctype = data.type;
        console.log("前端页面发送过来的数据的类型。。。。。。。。。。。。。。。。");
        writelog(s_order,ctype);
            switch(ctype) {
                //客户端注册
                case "register":
                    console.log("前端页面register数据·····");
                    console.log(data);
                    var s_clientId = data.content.data.clientId;
                    var i_callbackdata = data.content.callback;
                    console.log("注册信息接收：" +data);
                    //获得时间
                    var t_datetime = getDate();
                    var o_content = data.content.data;
                    var s_str1 = JSON.stringify(o_content);
                    var s_str2 = s_str1.replace('{', '{"clientIpAddress":"' + s_clientIpAddress + '","clientPort":"' + s_clientPort + '","clientRegisterTime":"' + t_datetime + '","socketId":"' + s_socketId + '",');
                    //将注册信息存入redis中(registerTable),键的值为socketId
                      redisClient.hmset("registerTable"+":"+s_clientId+":"+s_socketId,"field1", s_str2, function (err, res) {
                    //使用客户端的id属性作为通信的唯一标识，与客户端进行通信var i_callbackdata = data.callback;//该callback码需要从redis中取出唯一的
                    if (err) {
                      writelog(s_order, s_clientIpAddress + ":" + s_clientPort + " 注册失败！" + err);
                        var s_str4 = '{"callback": "' + i_callbackdata + '","data": false,"message":"' + err + '"}';
                        var o_str4 = JSON.parse(s_str4);
                        io.to(s_socketId).emit(s_clientId, o_str4);
                    } else {
                        writelog(s_order, s_clientIpAddress + ":" + s_clientPort + "注册成功！");
                        var s_str4 = '{"callback": "' + i_callbackdata + '","data": "true","message":"' + res + '"}';
                        var o_obj4 = JSON.parse(s_str4);
                        //io.to(s_socketId).emit(s_clientId, o_obj4);
                    }
            });
                    break;
                //客户端订阅 type:实时数据为rtsub,非实时数据为nrtsub
                //实时数据订阅
                case 'rtsub':
                    console.log("前端页面rtsub数据·····");
                    console.log(data);
                    var s_clientId = data.content.data.clientId;
                    var i_callbackdata = data.content.callback;
                    var s_subscribeTopic = data.content.data.subscribeTopic;
                    var s_subscribeSubitemall = data.content.data.subscribeSubitem;
                    console.log(s_subscribeSubitemall);
                    var i_length = s_subscribeSubitemall.length;
                    //建立数组用于存储数据
                    var data = [];
                    var i_count = 0;
                    s_subscribeSubitemall.forEach(function(s_subscribeSubitem){
                        console.log(" rtsub + s_subscribeSubitem");
                        console.log(s_subscribeSubitem);
                        i_count ++;
                        console.log("前端页面clientId数据·····");
                        console.log(s_socketId);
                        //将订阅信息存入redis中的订阅表中(subscribleTable),key值为socketId+":"+s_subscribeSubitem
                        var s_str2 = '{"clientId":"' + s_clientId + '","subscribeSubitem":"' + s_subscribeSubitem + '","subscribeTopic":"' + s_subscribeTopic + '","socketId":"' + s_socketId + '"}';
                        //将数据存入订阅表中
                        storageSubTable(s_str2,i_callbackdata,i_count,i_length,redisClient,io);
                        //根据html所订阅的项，从redis的点表中查询数据
                        findDotTable(s_subscribeSubitem,data,i_length,redisClient,io,s_socketId);
                    });
                break;
                // 客户端向服务端发送数据
                case "clientSend":
                    console.log("clientSend数据");
                    console.log(data);
                    //集群转发
                       var s_sourcetype = data.content.sourcetype;
                       var i_callback = data.content.callback;
                        console.log("输出的callback：" + i_callback);
                    switch(s_sourcetype){
                        case "html5":
                            console.log("前端页面html5发送过来的数据显示。。。。。。。。。。。。。。。。");
                            console.log(data);
                            var s_clientId = data.content.clientId;
                            var s_contentdataAll = data.content.data;
                            for(var i = 0;i < s_contentdataAll.length;i++){
                                //获得时间
                                var t_datetime = getDate();
                                //格式[{"name":点名1,”value”:值},{"name":点名1,”value”:值},...]
                                var s_contentdata = s_contentdataAll[i];
            //异常处理
                                try{
                                    var s_name = s_contentdata.name;//adapter1.controller1.dot1
                                    var s_content = s_name.split(".");
                                }catch(e) {
                                    var s_name ='..';
                                    var s_content = s_name.split(".");
                                    console.log("前端页面发送过来的数据没有name,只有value,请查看发送过来的数据：");
                                    console.log(s_contentdata);
                                }

                                //var s_name = s_contentdata.name;//adapter1.controller1.dot1
                                //var s_content = s_name.split(".");
                                var s_adapterId = s_content[0];
                                var s_ncId = s_content[1];
                                var s_dotId = s_content[2];
                                var s_dotValue = s_contentdata.value;
                                var s_str = '{"adapterId":"'+s_adapterId+'","ncId":"'+s_ncId+'","dotId":"'+s_dotId+'","dotValue":"'+s_dotValue+'","valueUpdateTime":"'+t_datetime+'"}';
                                //写入redis的点表中(dotTable)，键值为s_adapterId+s_ncId+s_dotId, 如何用hash表结构如何存
                                writelog(s_order,"存入点表中的数据："+s_str);
                               //将数据存入到点表中
                                storageDotTable(s_name,s_str,redisClient);
                                var s_str1 = '[{"name":"'+s_name+'","value":"'+s_dotValue+'"}]';
                                var s_str2 = '{"clientId":"'+s_clientId+'","socketId":"'+s_socketId+'","content":{"desttype":"adapter","data":'+s_str1+',"callback":"'+i_callback+'"},"sentTime":"'+t_datetime+'","lastSendTime":"'+t_datetime+'","sendCount":"'+0+'"}';
                                //从注册表中查询该适配器是否注册了,
                                findRegisterTable(s_adapterId,s_str2,redisClient,io);
                            }
                            break;
                        case "adapter":
                            console.log("adapter数据");
                            console.log(data);
                            var s_contentdataAll = data.content.data;
                            for(var i = 0;i < s_contentdataAll.length;i++) {
                                //获得时间
                                var t_datetime = getDate();
                                //格式[{"name":点名1,”value”:值},{"name":点名1,”value”:值},...]
                                var s_contentdata = s_contentdataAll[i];
                                console.log("adapter +　s_subscribeSubitem");
                                console.log(s_contentdata);
                                //异常处理
                                try{
                                    var s_name = s_contentdata.name;//adapter1.controller1.dot1
                                    var s_content = s_name.split(".");
                                }catch(e) {
                                    var s_name ='..';
                                    var s_content = s_name.split(".");
                                    console.log("适配器发送过来的数据没有name,只有value,请查看发送过来的数据：");
                                    console.log(s_contentdata);
                                }
                                //var s_name = s_contentdata.name;//adapter1.controller1.dot1
                                //var s_content = s_name.split(".");
                                var s_adapterId = s_content[0];
                                var s_ncId = s_content[1];
                                var s_dotId = s_content[2];
                                var s_dotValue = s_contentdata.value;
                                var s_str = '{"adapterId":"' + s_adapterId + '","ncId":"' + s_ncId + '","dotId":"' + s_dotId + '","dotValue":"' + s_dotValue + '","valueUpdateTime":"' + t_datetime + '"}';
                                //写入redis的点表中(dotTable)，键值为s_adapterId+s_ncId+s_dotId, 如何用hash表结构如何存
                                writelog(s_order,"存入点表中的数据："+s_str);
                                //将数据存入点表中
                                storageDotTable(s_name,s_str,redisClient);
                                var s_str1 = '{"name":"'+s_name+'","value":"'+s_dotValue+'"}';
                                var s_str2 = '{"content":{"desttype":"html5","data":['+s_str1+'],"callback":"'+i_callback+'"},"sentTime":"'+t_datetime+'","lastSendTime":"'+t_datetime+'","sendCount":"'+0+'"}';
                                //根据适配器id，查询订阅该适配器的所有客户端，并发送数据给相对应的客户端
                                findSubscribe(s_adapterId,s_str2,redisClient,io);
                                //根据适配器和控制器的id，查询订阅该控制器的所有客户端，并发送数据给相对应的客户端
                                findSubscribe(s_adapterId+"."+s_ncId,s_str2,redisClient,io);
                                //根据适配器,控制器和点的id，查询订阅该控制器的所有客户端，并发送数据给相对应的客户端
                                findSubscribe(s_name,s_str2,redisClient,io);
                            }
                            break;
                    }
                break;

                //服务端发送数据给客户端的，客户端对服务端作出的应答
                case 'callback':
                    var callbackid = data.callbackid;
                    var callback = data.callback;
                     //获得redis中的数据,看是否存入值栈表中
                     redisClient.del("stackTable"+":"+callback+":"+callbackid,"field1",function(error,reply){
                     if(error){
                     writelog(s_order,"堆栈表中删除数据失败:"+ error);
                     }else{
                     writelog(s_order,"堆栈表中删除数据成功："+reply );
                     }
                     });
                    writelog(s_order,"发 送数据成功！"+callbackid + callback);
                break;

                //报警接口
                case 'Alarm':
                    console.log("接收到的Alarm报警数据")
                    console.log(data)
                    //var s_str = JSON.stringify(data);
                    //var o_obj = JSON.parse(s_str);
                    console.log(data.content.data)
                    io.emit("Alarm",data.content.data);
                    break;
            }
    });
    //监听断开事件
    client.on('disconnect',function(){
        writelog(s_order,s_clientIpAddress+":"+s_clientPort +"已退出");
       /* redisClient.decr("countConnecting", function (error, reply) {
            if (error) {
                //reject();
                writelog(s_order, "自减连接数据失败" + error);
            } else {
                writelog(s_order, "自减连接数据成功" + reply);
            }
        });*/
        //删除相关的注册信息
        redisClient.KEYS("registerTable"+":"+"*"+":"+s_socketId,function(error,reply1){
            if(error){
                writelog(s_order,"删除注册数据失败："+error);
            }else{
                writelog(s_order,"注册信息删除。。。。。。"+reply1);
                for(var i = 0;i<reply1.length;i++){
                    redisClient.del(reply1[i],function(error,reply){
                        if(error){
                            writelog(s_order,"删除数据失败："+error);
                        }else{
                            writelog(s_order,"删除数据成功"+reply);
                        }
                    });
                }
            }
        });
        //删除订阅信息
        redisClient.KEYS("subscribleTable"+":"+s_socketId+"*",function(error,reply1){
            if(error){
                writelog(s_order,"删除数据失败："+error);
            }else{
                writelog(s_order,"订阅信息删除。。。。。。"+reply1);
                for(var i = 0;i<reply1.length;i++){
                   /* var s_adapterIdArr = reply1.split(":");
                    var s_adapterId = s_adapterIdArr[1];*/
                    redisClient.del(reply1[i],function(error,reply){
                        if(error){
                            writelog(s_order,"删除数据失败："+error);
                        }else{
                            writelog(s_order,"删除数据成功"+reply);
                        }
                    });
                }
            }
        });
    });
});