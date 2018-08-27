/**
 * Created by Administrator on 2018/3/1.
 */
var writelog = require('./writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';

//创建getcallbackid的函数，进行同步获取callbackId(适配器->server->html5)
function getcallbackid2(string,redisClient,io) {
    var promise =  new Promise(function (resolve,reject) {
        redisClient.incr("callbackid", function (error, reply) {
            if (error) {
                writelog(s_order, "error:redis获取callbackid失败" + error );
            } else {
                writelog(s_order, "redis获取callbackid获取成功:" + reply );
            }
            resolve(reply);
        });
    });
    return promise.then(function(value){
            //writelog(s_order,"订阅表中的数据");
            //writelog(s_order,value);
            var s_str1 = string.replace('{','{"callbackid":"'+value+'",');//将callbackid加入将要存入堆栈表中的字符串中
            var o_obj1 = JSON.parse(string);
            var o_content = o_obj1.content;
            //得到socketId和clientId
            var s_socketId2 = o_obj1.socketId;
            //测试socketID
            //writelog(s_order,"得到的socketId");
            //writelog(s_order,s_socketId2);
            //var s_clientId = o_obj1.clientId;
            var s_content = JSON.stringify(o_content);
            var s_str2 = s_content.replace('{','{"callbackid":"'+value+'",');
            //将存入堆栈表中的content更新
            var o_obj2 = JSON.parse(s_str2);//发送给html5的数据
            var i_callback = o_content.callback;
            //writelog(s_order,"存入到堆栈表中的数据2："+s_str2);
            //writelog(s_order,"存入到堆栈表中的数据1："+s_str1);
            //建立与适配器的通信，通信事件为adapterId,将数据发送给
            writelog(s_order,"htmlSocketID");
            writelog(s_order,s_socketId2);
        console.log("准备发送数据给前段");
            io.to(s_socketId2).emit(s_socketId2, o_obj1);
            //io.to(s_socketId2).emit("H000000003", o_obj1);
          /*  redisClient.hmset("stackTable"+":"+i_callback+":"+value,'field2',s_str1,function(error,res){
                if (error) {
                    writelog(s_order,"存入数据失败："+error);
                } else {
                    writelog(s_order,"存入数据成功："+res);
                    //建立与适配器的通信，通信事件为adapterId,将数据发送给
                    writelog(s_order,"发送给前端的数据!");
                    writelog(s_order,o_obj1);
                    //io.to(s_socketId2).emit("message", o_obj1);
                    io.to(s_socketId2).emit(s_socketId2, o_obj1);
                }
            });*/
        })
        //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"获取callbackid2时，异步操作出现异常："+error);
        });
};

module.exports = getcallbackid2;