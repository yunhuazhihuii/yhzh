/**
 * Created by Administrator on 2018/3/23.
 */
var writelog = require('../method/writelog.js');
var controlsAndDots = require('./controlsAndDots.js');
var getCounts = require('./getCounts.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';
//从dotArray数组中查询表的值及其他属性
 function sendToHtml(dotArray,dotName,children2,children1,restFulJson,jsonStr1,adaptersDub,res1,jsonStr2,redisClient,s_adapterAndControll,controllersDub,reply){
    var promise =  new Promise(function (resolve,reject) {
        dotArray.forEach(function (item4) {
            //console.log("222222222222222222222222");
            //console.log(item4.name );
            //console.log(dotName);
            if (item4.name === dotName) {
                var dotValue = item4.value;
                var dotType = item4.ioproperty;
                //console.log(dotValue);
                //console.log(dotType);
                var jsonStr3 = {"id": 111, "text": "点", "name": dotName, "value": dotValue, "type": dotType};
                //console.log("输出数据");
                //console.log(jsonStr3);
                children2.push(jsonStr3);
                console.log(children2)
                //判断，该控制下有多少个点,数据是为counts
                //if(children2.length == reply.length){
                //    jsonStr2.children = children2;
                //    console.log("出现的数据");
                //    console.log(jsonStr2);
                //}
                //getCounts(s_adapterAndControll,redisClient,children2,jsonStr2,children1,restFulJson,jsonStr1,controllersDub);

              /*  if(children2.length == counts){
                    console.log("输出数据");
                    console.log(children2);
                }*/
               /* jsonStr2.children = children2;
                //console.log(children2);

                children1.push(jsonStr2);
                //判断该适配器下有多少个控制器

                //console.log(children1);
                restFulJson.push(jsonStr1);*/
                //console.log(restFulJson);
               /* console.log("每次输出的数据：")
                console.log(children2)
                console.log(restFulJson)*/
             /*   if(children2.length==dotArray.length){
                    console.log("一次输出的数据：")
                    console.log(children2)
                    console.log(restFulJson)
                }*/

                //console.log(restFulJson)
               /* if (restFulJson.length == adaptersDub.length) {
                    console.log("每次输出的数据：")
                    console.log(children2)
                    console.log("输出发送给页面的数据");
                    console.log(restFulJson);
                    console.log(jsonStr1);
                    res1.end(JSON.stringify(restFulJson));
                }*/
            }
        })
    })
    //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"发送数据给server页面时，同步操作出现异常："+error);
        });
}
module.exports = sendToHtml;
