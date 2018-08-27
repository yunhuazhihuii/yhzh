/**
 * Created by Administrator on 2018/3/13.
 */
var writelog = require('./writelog.js');
var restfulDatas = require('./restfulDatas.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';
//string1是控制器的id，string2是适配器的对象，string3是控制器的对象
function restfulFindDot(redisClient,string1,restfulJson,res,string2,string3){
    var promise =  new Promise(function (resolve,reject) {
        var children1 = [];
        var children2 = [];
        //var children3 = [];
        var dot = [];
        redisClient.KEYS("dotTable"+":"+string1+"*",function(err,reply){
            if(err){
                writelog(s_order,"查询注册表时出现错误：" + err);
            }else{
                writelog(s_order,"查询注册表成功：" + reply);
                console.log("查询出来该适配器下该控制器下的所有的点：");
                console.log(reply);
                //根据查询出的适配器，控制器和点从点表中查询该值
                for(var i = 0;i < reply.length;i++ ){
                    /*var dotId = reply[i].split(':');
                    //dot.push(dotId[2]);
                    dot.push(dotId[1]);
                    console.log("输出点的值：");
                    console.log(dot);*/
                    restfulDatas(redisClient,reply[i],restfulJson,res,dot,string2,string3,reply.length,children1,children2,i);
                    //函数名为：restFulDatas(reply[i],restfulJson,res,dot,string2,string3,reply.length)
                   /* //根据该点得到的值
                    redisClient.hget(reply[i],'field1',function(error,reply1){
                        var dotId = reply[i].split(':');
                        //dot.push(dotId[2]);
                        dot.push(dotId[1]);
                        console.log("输出点的值：");
                        console.log(dot);
                        if(error){
                            writelog(s_order,"查找点表时出现错误："+error);
                        }else{
                            console.log(reply1);
                            var obj = JSON.parse(reply1);
                            //将点的值存入string2里面
                            var s_dot = {"id":i*110+1+i, "text":"点1", "iconCls":"icon-rss","name":dotId[1],"value":obj.dotValue, "attributes":{"url":"http://localhost:8090/datas"}};
                            children1.push(s_dot);
                            string3.children = children1;
                            console.log("数据库的数据......");
                            console.log(string3);
                            children2.push(string3);
                            string2.children = children2;
                            //children3.push(string2);
                            restfulJson.push(string2);
                            //res.end(JSON.stringify(dotId[1]+":"+ obj.dotValue));
                            if(dot.length == reply.length){
                                writelog(s_order,"发送数据给前端页面：");
                                writelog(s_order,restfulJson);
                                console.dir(restfulJson);
                                res.end(JSON.stringify(restfulJson));
                            }
                        }
                    })*/
                }
            }
    });
    })
        //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"查询订阅表时，异步操作出现异常："+error);
        });
}
module.exports = restfulFindDot;
