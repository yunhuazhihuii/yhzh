/**
 * Created by Administrator on 2018/3/29.
 */
var writelog = require('../method/writelog.js');
var sendToHtml = require('./sendToHtml.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';
//从redis中获取该适配器.控制器下点的个数
//function getCounts(datas,count,array3,s_str){
function getCounts(s_adapterAndControll,redisClient,children2,jsonStr2,children1,restFulJson,jsonStr1,controllersDub,dotArray,dotName,adaptersDub, res1){
    var promise =  new Promise(function (resolve,reject) {
        redisClient.KEYS("dotTable"+":"+s_adapterAndControll +"*",function(err,reply){
            if(err){
                writelog(s_order,"查询点表时出现错误：" + err);
            }else {
                writelog(s_order, "查询点表成功：" + s_adapterAndControll + ":" + reply.length);
                //获得该适配器.控制器下的点的个数
                //sendToHtml(dotArray,dotName,children2,children1,restFulJson,jsonStr1,adaptersDub,res1,jsonStr2,redisClient,s_adapterAndControll,controllersDub,reply);

                //console.log(reply.length);
              /*  //判断该适配器.控制器下的点有多少个
                if (children2.length == reply.length) {
                    //console.log("输出数据");
                    //console.log(children2);
                    jsonStr2.children = children2;
                    //console.log(children2);
                    children1.push(jsonStr2);
                    //判断该适配器下有多少个控制器
                    if(children1.length == controllersDub.length){
                        //console.log("什么情况？");
                        //console.log(children1);
                        restFulJson.push(jsonStr1);
                        //判断有多少个适配器
                        //console.log(restFulJson);
                    }
                }*/
            }
            resolve(reply)
        });
    })
    return promise.then(function(value){
        console.log("输出的结果");
        //console.log(value);
        sendToHtml(dotArray,dotName,children2,children1,restFulJson,jsonStr1,adaptersDub,res1,jsonStr2,redisClient,s_adapterAndControll,controllersDub,value);
    })
    //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"查询点表时，同步操作出现异常："+error);
        });
}
module.exports = getCounts;
