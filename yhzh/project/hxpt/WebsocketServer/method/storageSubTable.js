/**
 * Created by Administrator on 2018/2/28.
 */
var writelog = require('./writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';

//将订阅信息存入订阅表中,string1是要存入到订阅表中的信息，string2是i_callbackdata
//{"clientId":"' + s_clientId + '","subscribeSubitem":"' + s_subscribeSubitem + '","subscribeTopic":"' + s_subscribeTopic + '","socketId":"' + s_socketId + '"}
function storageSubTable(string1,string2,string3,string4,redisClient,io) {
    var promise =  new Promise(function (resolve,reject) {
        var o_obj = JSON.parse(string1);
        var s_socketId3 = o_obj.socketId;
        var s_clientId = o_obj.clientId;
        var s_subscribeSubitem = o_obj.subscribeSubitem;
        //将页面订阅信息存入redis中
        redisClient.hmset("subscribleTable"+":"+s_socketId3+":"+s_subscribeSubitem,"field1", string1, function (err, res) {
            if (err) {
                writelog(s_order,"前端订阅数据存入失败！" + err);
                var s_str4 = '{"callback": "' + string2 + '","data": false,"message":"' + err + '"}';
                var o_obj4 = JSON.parse(s_str4);
                io.to(s_socketId3).emit(s_socketId3, o_obj4);
            } else {
                writelog(s_order,"前端订阅数据存入成功！");
                var s_str4 = '{"callback": "' + string2 + '","data": true,"message":"' + res + '"}';
                var o_obj4 = JSON.parse(s_str4);
                //console.log(s_subscribeSubitem);
                //存入成功，发送数据给html5
                if(string3 == string4){
                    io.to(s_socketId3).emit("message", o_obj4);
                }
            }
        });
    })
    //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"存入订阅表时，异步操作出现异常："+error);
        });
};
module.exports = storageSubTable;