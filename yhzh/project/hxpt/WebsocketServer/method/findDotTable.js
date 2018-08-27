/**
 * Created by Administrator on 2018/2/28.
 */
var writelog = require('./writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';
//根据html所订阅的子项，从redis的点表中查询数据findDotTable,string1是订阅的子项，string2是空数组
function findDotTable(string1,string2,string3,redisClient,io,s_socketId) {
    var promise =  new Promise(function (resolve,reject) {
        redisClient.hget("dotTable"+":"+string1,'field1',function(error,reply){
            if(error){
                writelog(s_order,"查找点表时出现错误："+error);
            }else{
                writelog(s_order,reply);
                //判断reply是否为null
                if(reply==null){
                    var s_string = '{"name":"'+string1+'","value":"null"}';
                    writelog(s_order,"还没有该数据存入redis中的点表中！");
                }else{
                    var o_obj = JSON.parse(reply);
                    var i_value = o_obj.dotValue;
                    var s_string = '{"name":"'+string1+'","value":"'+i_value+'"}';
                }
            }
            resolve(s_string);
        });
    })
    return promise.then(function(value){
            string2.push(value);
            //writelog(s_order,string2);
            // writelog(s_order,string2.length);
            if(string2.length==string3){
                // writelog(s_order,string2);
                var MESSAAGE = '{"type": "serverSend","content":{"desttype":"html5","data":['+string2+']}}';
                var o_obj2 = JSON.parse(MESSAAGE);
                //发送数据给html5，创建事件
                writelog(s_order,"前端订阅时，发送给前端的数据！");
                writelog(s_order,o_obj2);
                //io.to(s_socketId).emit("message", o_obj2);
                writelog(s_order,"前端订阅时，！s_socketId");
                writelog(s_order,s_socketId);
                io.to(s_socketId).emit(s_socketId, o_obj2);
                //io.to(s_socketId).emit("message1", o_obj2);
            }
        })
        //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"查询点表时，异步操作出现异常："+error);
        });
};
module.exports = findDotTable;