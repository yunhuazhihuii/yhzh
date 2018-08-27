/**
 * Created by Administrator on 2018/3/1.
 */
var getcallbackid2 = require('./getcallbackid2.js');
var writelog = require('./writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';

//查询订阅适配器的所有客户端string1是适配器的键值，string2是要发送给页面的数据（但是不包含socketId和clientId，socketId和clientId只能根据适配器的值得到）
//存入订阅表中的键值为"subscribleTable"+":"+s_socketId+":"+s_subscribeSubitem
function findSubscribe(string1,string2,redisClient,io) {
    var promise =  new Promise(function (resolve,reject) {
        //获得订阅表中的所有键的值
        //redisClient.KEYS('subscribleTable'+':'+'*'+':'+ string1,function(err,reply1){
        redisClient.KEYS('subscribleTable'+'*'+ string1,function(err,reply1){
            if(err){
                writelog(s_order, "出现错误"+err);
            }else{
                writelog(s_order, "取出成功的订阅表中键的值:"+reply1);
                var i_adaptercount = reply1.length;
                writelog(s_order, "有"+i_adaptercount+"个客户端订阅了该点!");
                for(var i = 0;i<i_adaptercount;i++){
                    //循环键，根据键找到相对应的值
                    var s_adapterkey = reply1[i];
                    writelog(s_order, "订阅表中查询到的键的值:"+s_adapterkey);
                    //根据键值从redis中的订阅表中读取数据
                    redisClient.hget(s_adapterkey,"field1",function(error,reply3){
                        if(error){
                            writelog(s_order,"error:"+ error);
                        }else{
                            //订阅数据： '{"clientId":"' + s_clientId + '","subscribeSubitem":"' + s_subscribeSubitem + '","subscribeTopic":"' + s_subscribeTopic + '","socketId":"' + s_socketId + '"}';
                            writelog(s_order, "根据键值从订阅表中获取的数据:" + reply3);
//修改
                            if(reply3==null){
                                writelog(s_order, "还没有客户端订阅该点！");
                            }else{
                                //得到值，解析出socketId和clientId
                                var obj = JSON.parse(reply3);
                                //writelog(s_order, reply3);
                                var s_socketId1 = obj.socketId;
                                //writelog(s_order, "从订阅表中得到的socketid");
                                //writelog(s_order, s_socketId1);
                                var s_clientId = obj.clientId;
                                //整理发送给html的数据
                                var s_str2 = string2.replace('{','{"clientId":"'+s_clientId+'","socketId":"'+s_socketId1+'",');
                                writelog(s_order, "存入堆栈表中的数据:" + s_str2);
                                console.log("html的clientID");
                                console.log(s_clientId);
                                //创建发送事件
                                getcallbackid2(s_str2,redisClient,io);
                            }
                        }
                        //resolve(reply3);
                    });
                }
            }
        });
    })
    //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"查询订阅表时，异步操作出现异常："+error);
        });
};
module.exports = findSubscribe;