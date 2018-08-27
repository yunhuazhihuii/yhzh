/**
 * Created by Administrator on 2018/2/28.
 */
var writelog = require('./writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';
//创建getcallbackid的函数，进行同步获取callbackId(html5页面->server->适配器)
//string1是发送给adapter的数据，string2是适配器的id，string3是适配器的socketId
function getcallbackid1(string1,string2,string3,redisClient,io) {
    var promise = new Promise(function (resolve, reject) {
        redisClient.incr("callbackid", function (error, reply) {
            if (error) {
                writelog(s_order,"error:redis获取callbackid失败" + error);
            } else {
                writelog(s_order,"redis获取callbackid获取成功:" + reply);
            }
            resolve(reply);
        });
    });
    return promise.then(function (value) {
            var s_str1 = string1.replace('{', '{"callbackid":"' + value + '",');
            // 将callbackid加入将要存入堆栈表中的字符串中
            var o_obj1 = JSON.parse(string1);
            var o_content = o_obj1.content;
            var s_content = JSON.stringify(o_content);
            var s_str2 = '{"callbackid":"' + value + '","type":"serverSend","content":'+s_content+'}';
            //将存入堆栈表中的content更新
            var o_obj2 = JSON.parse(s_str2);//发送给适配器的数据
            var i_callback = o_content.callback;
            //建立与适配器的通信，通信事件为adapterId,将数据发送给
            io.to(string3).emit(string2,o_obj2);
          /*  redisClient.hmset("stackTable" + ":" + i_callback + ":" + value, 'field1', s_str1, function (error, res) {
                if (error) {
                    writelog(s_order,"存入数据失败：" + error);
                } else {
                    writelog(s_order,"存入数据成功：" + res);
                    //建立与适配器的通信，通信事件为adapterId,将数据发送给
                    io.to(string3).emit(string2,o_obj2);
                }
            });*/
        })
        //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"获取callbackid1时，异步操作出现异常："+error);
        });
};
module.exports = getcallbackid1;