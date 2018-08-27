/**
 * Created by Administrator on 2018/2/28.
 */
var writelog = require('./writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';

//将数据存入redis的点表中,string1是订阅的子项名，string2是存入点表中的数据
function storageDotTable(string1,string2,redisClient) {
    var promise =  new Promise(function (resolve,reject) {
        redisClient.hmset("dotTable"+":"+string1,"field1",string2,function(err,res){
            if (err) {
                writelog(s_order,"存入数据到点表中失败："+err);
            } else {
                //console.log("存入数据到点表中成功："+res);
                writelog(s_order,"存入数据到点表中成功："+res);
            }
        });
    })
    //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"存入点表时，异步操作出现异常："+error);
        });
};

module.exports = storageDotTable;