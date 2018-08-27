/**
 * Created by Administrator on 2018/3/12.
 */
var writelog = require('./writelog.js');
var arrayRemoveDup = require('./arrayRemoveDub.js');
var restfulFindDot = require('./restfulFindDot.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';

//根据这个适配器的名称去查询点表中的数据(restfulFindController())
//redisClient,string1是适配器id,string2是发送给前端页面的JSON数据数组,res是发送给前端页面的数据
function restfulFindController(redisClient,string1,string2,res,string3){
    var promise =  new Promise(function (resolve,reject) {
        var children = [];
        var controllerId =[];//用来存放控制器的id
        redisClient.KEYS('dotTable:'+string1+'*',function(err,reply){
            if(err){
                writelog(s_order,"查询点表时出现错误：" + err);
            }else {
                writelog(s_order, "查询点表成功：" + reply);
                writelog(s_order,reply);//reply为一数组[dotTable:A000000001.100000001.1000023,dotTable:A000000001.100000001.1000025]
                //获得控制器Id
                for(var i =0;i<reply.length;i++){
                    var s_replyItem = reply[i].split(".");
                    var s_controllerId = s_replyItem[1];
                    writelog(s_order,"控制器的id");
                    writelog(s_order,s_controllerId);
                    controllerId.push(string1+"."+s_controllerId);
                    writelog(s_order,"没有去重：");
                    writelog(s_order,controllerId);
                    if(reply.length == controllerId.length){
                        writelog(s_order,"去重：");
                        var controllerIdDub = arrayRemoveDup(controllerId);
                        writelog(s_order,controllerIdDub);
                        //遍历controllerIdDub
                        for(var i = 0;i < controllerIdDub.length; i++){
                            writelog(s_order,"遍历适配器控制器的点");
                            writelog(s_order,controllerIdDub[i]);
                            //得到控制器的点，配置到数据库
                            var s_controller = {"id":i*10+1+i,"text":"控制器","name":controllerIdDub[i],"iconCls":"icon-feed","children":null};
                            //children.push(s_controller);
                            //设计发送给页面的数据
                            var s_adapter = {"id":string3, "text":"适配器","name":string1, "iconCls":"icon-channels", "children":null};
                            //console.log("输出的点的值");
                            //console.log(children);
                            console.log(s_adapter);
                            //根据这个适配器的名称去查询点表中的数据(restfulFindDot())，string1是控制器的id，string2是适配器的对象，string3是控制器的对象
                            restfulFindDot(redisClient,controllerIdDub[i],string2,res,s_adapter,s_controller);

                        }
                    }
                }
            }
        })
    })
    //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"存入点表时，异步操作出现异常："+error);
        });
}
module.exports = restfulFindController;


