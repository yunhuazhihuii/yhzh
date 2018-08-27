/**
 * Created by Administrator on 2018/3/24.
 */
var writelog = require('../method/writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';
var controllersSearch = require('./controllersSearch.js');
function adaptersSearch(controllersDub,dotArray, restFulJson, adaptersDub, res1,dotsDub,redisClient){
    var promise =  new Promise(function (resolve,reject) {
        adaptersDub.forEach(function (item1) {
            var children1 = [];//控制器的数组
            var jsonStr1 = {"id": 1, "text": "适配器", "name": item1, "children": ""};
            //console.log("输出适配器数据");
            //console.log("jsonStr1");
            controllersSearch(controllersDub, children1, dotArray, restFulJson, jsonStr1, adaptersDub, res1, dotsDub, item1,redisClient);
        })
    })
    //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"发送数据给server页面时，同步操作出现异常："+error);
        });
}
module.exports = adaptersSearch;