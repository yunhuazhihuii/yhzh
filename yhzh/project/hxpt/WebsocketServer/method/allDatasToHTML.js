/**
 * Created by Administrator on 2018/3/1.
 */
var writelog = require('./writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';

//string1是所有点的数组，string2是点的数组的长度,string3是用来装数据发给前端的数组
function allDatasToHTML(string1,string2,string3,redisClient,io) {
    var promise =  new Promise(function (resolve,reject) {
        string1.forEach(function(perkeys){
            //根据键查询值，存入data数组中
            // 存入点表中的数据格式为："adapterId":"' + s_adapterId + '","ncId":"' + s_ncId + '","dotId":"' + s_dotId + '","dotValue":"' + s_dotValue + '",
            redisClient.hget(perkeys,"field1",function(error,reply2){
                var o_obj = JSON.parse(reply2);
                var s_adapterId = o_obj.adapterId;
                var s_ncId = o_obj.ncId;
                var s_dotId = o_obj.dotId;
                var s_dotValue = o_obj.dotValue;
                var s_string1 = s_adapterId+"." + s_ncId+"." +s_dotId;
                var s_string = {"name":s_string1,"value":s_dotValue};
                string3.push(s_string);
                if(string2 == string3.length){
                    writelog(s_order,"发送给前段页面的数据1.................");
                    writelog(s_order,string3);
                    io.emit("AllDatas",string3);
                }
            });
        });
    })
    //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"发送所有数据给前端时，出现异常："+error);
        });
};
module.exports = allDatasToHTML;