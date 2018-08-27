/**
 * Created by Administrator on 2018/2/28.
 */
var getcallbackid1 = require('./getcallbackid1.js');
var writelog = require('./writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';

//从注册表中查询该适配器是否注册了,string1为s_adapterId，string2为将要发给适配的数据str2
function findRegisterTable(string1,string2,redisClient,io) {
    var promise =  new Promise(function (resolve,reject) {
        redisClient.KEYS("registerTable"+":"+string1+":"+"*",function(err,reply){
            if(err){
                writelog(s_order,"查询注册表时出现错误：" + err);
            }else{
                writelog(s_order,"查询注册表成功：" + reply);
            }
            resolve(reply);
        });
    })
    return promise.then(function(value){
            if(value.length == 0){
                console.log("该适配器还没有注册，请注册！");
            }else {
                writelog(s_order,"查询注册表中所有socketID的值！");
                writelog(s_order,value);
                value.forEach(function (registerName) {
                    var s_string = registerName.split(":");
                    var s_socketId4 = s_string[2];
                    getcallbackid1(string2, string1, s_socketId4,redisClient,io);
                });
            }
        })
        //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"查询订阅表时，异步操作出现异常："+error);
        });
};
module.exports = findRegisterTable;