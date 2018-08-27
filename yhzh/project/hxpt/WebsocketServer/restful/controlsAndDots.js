/**
 * Created by Administrator on 2018/3/24.
 */
var writelog = require('../method/writelog.js');
var getCounts = require('./getCounts.js');
//日志文件写入到屏幕或者日志文件中,console是输出到屏幕，log是写日志，none时啥都不输出
//var s_order = 'log';
var s_order = 'console';
//string是适配器.控制器，array3是所有点的集合
//array1是适配器的集合,array2是控制器的集合，array3是所有点的集合
//function controlsAndDots(string,array){
function controlsAndDots(array1,array2,array3){
    var count = 0;
    var promise =  new Promise(function (resolve,reject) {
        //得到dotarray的数据
        console.log("输出点数组");
        console.log(array2);
        array1.forEach(function(item1){
            //console.log("00000000000000000000000");
            //console.log(item1);
         /*   array2.forEach(function(item2){
                var s_str = item1+'.'+item2;
                console.log("11111111111111111111111111111111111111");
                console.log(s_str);
                   array3.forEach(function(item3){
                       var itemName = item3.name;
                       var item = itemName.split(".");
                       var adapterAndControllers = item[0] + item[1];
                       console.log("2222222222222222222222222");
                       console.log(adapterAndControllers);
                       if(adapterAndControllers === s_str){
                            count ++;
                           console.log("3333333333333333");
                           console.log(count);
                       }
                   });
            });*/
            resolve(item1);
        });
    })
    return promise.then(function(value){
        array2.forEach(function(item2) {
            var s_str = value + '.' + item2;
            //console.log("11111111111111111111111111111111111111");
            //console.log(s_str);
            getCounts(count,array3,s_str);
            /*array3.forEach(function(item3){
                var itemName = item3.name;
                var item = itemName.split(".");
                var adapterAndControllers = item[0] +"."+ item[1];
                console.log("2222222222222222222222222");
                console.log(adapterAndControllers);
                if(adapterAndControllers === s_str){
                    count ++;
                    console.log("3333333333333333");
                    console.log(count);
                }
            });*/
        })
    })
    //捕捉异步时的错误
        .catch(function(error) {
            writelog(s_order,"发送数据给server页面时，同步操作出现异常："+error);
        });

}
module.exports = controlsAndDots;
