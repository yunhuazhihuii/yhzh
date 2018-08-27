/**
 * Created by Administrator on 2018/3/24.
 */
var writelog = require('../method/writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';
var sendToHtml = require('./sendToHtml.js');
var getCounts = require('./getCounts.js');
function dotsSearch (dotsDub,item1,item2,children2,children1,dotArray,restFulJson,jsonStr1,adaptersDub,res1,jsonStr2,redisClient,s_adapterAndControll,controllersDub){
    var promise =  new Promise(function (resolve,reject) {
        dotsDub.forEach(function (item3) {
            var dotName = item1 + "." + item2 + "." + item3;
            //console.log("111111111111111111111111");
            //console.log(dotName);
            //前面没问题
            sendToHtml(dotArray, dotName, children2,children1,restFulJson, jsonStr1, adaptersDub, res1,jsonStr2,redisClient,s_adapterAndControll,controllersDub);
            //getCounts(s_adapterAndControll,redisClient,children2,jsonStr2,children1,restFulJson,jsonStr1,controllersDub,dotArray,dotName,adaptersDub, res1);
        })
    })
    //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"发送数据给server页面时，同步操作出现异常："+error);
        });
}
module.exports = dotsSearch;
