/**
 * Created by Administrator on 2018/3/14.
 */
    //根据该点得到的值
var writelog = require('./writelog.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';
function restfulDatas(redisClient,string1,restfulJson,res,dot,string2,string3,string4,children1,children2,i) {
    redisClient.hget(string1, 'field1', function (error, reply1) {
        var dotId = string1.split(':');
        //dot.push(dotId[2]);
        dot.push(dotId[1]);
        console.log("输出点的值：");
        console.log(dot);
        if (error) {
            writelog(s_order, "查找点表时出现错误：" + error);
        } else {
            console.log(reply1);
            var obj = JSON.parse(reply1);
            //将点的值存入string2里面
            var s_dot = {
                "id": i * 110 + 1 + i,
                "text": "点1",
                "iconCls": "icon-rss",
                "name": dotId[1],
                "value": obj.dotValue,
                "attributes": {"url": "http://localhost:8090/datas"}
            };
            children1.push(s_dot);
            string3.children = children1;
            console.log("数据库的数据......");
            console.log(string3);
            children2.push(string3);
            string2.children = children2;
            //children3.push(string2);
            restfulJson.push(string2);
            //res.end(JSON.stringify(dotId[1]+":"+ obj.dotValue));
            if (dot.length == string4) {
                writelog(s_order, "发送数据给前端页面：");
                writelog(s_order, restfulJson);
                console.dir(restfulJson);
                res.end(JSON.stringify(restfulJson));
            }
        }
    })
}
module.exports = restfulDatas;