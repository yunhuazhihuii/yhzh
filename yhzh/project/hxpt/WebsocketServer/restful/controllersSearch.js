/**
 * Created by Administrator on 2018/3/24.
 */
var writelog = require('../method/writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';
var dotsSearch = require('./dotsSearch.js');
var controlsAndDots = require('./controlsAndDots.js');
var getCounts = require('./getCounts.js');
function controllersSearch(controllersDub,children1,dotArray, restFulJson, jsonStr1, adaptersDub, res1,dotsDub,item1,redisClient){
    var count = 0;//点的个数
    var datas = [];//用来收集该适配器下点的个数
    var promise =  new Promise(function (resolve,reject) {
    controllersDub.forEach(function(item2) {
        var children2 = [];//点的数组
        var jsonStr2 = {"id": 11, "text": "控制器", "name": item2, "children": ""};
        //根据适配器.控制器查找有多少个点
        var s_adapterAndControll = item1+"."+item2;
        //console.log("输出适配器数据和控制器的数据")
        //getCounts(datas,count,dotArray,s_adapterAndControll,redisClient);
/*
       var counts = getCounts(s_adapterAndControll,redisClient);
        console.log(counts);
*/

        //console.log(item1+"."+item2);


        //console.log("输出适配器数据和控制器的数据")
        //console.log(item1+"."+item2);
        ////console.log(jsonStr2);
        //children1.push(jsonStr2);
        //jsonStr1.children = children1;

        /*if(children1.length == controllersDub.length){
            console.log("输出一次")
            console.log(children1)
            console.log(jsonStr1)
        }*/
      /*  console.log(children1)
        console.log(jsonStr1)*/
        dotsSearch(dotsDub, item1, item2, children2,children1, dotArray, restFulJson, jsonStr1, adaptersDub, res1,jsonStr2,redisClient,s_adapterAndControll,controllersDub);
        //dotsSearch(dotsDub, item1, item2, children2,children1, dotArray, restFulJson, jsonStr1, adaptersDub, res1,jsonStr2,counts);
    })
    })
    //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"发送数据给server页面时，同步操作出现异常："+error);
        });
}
module.exports = controllersSearch;