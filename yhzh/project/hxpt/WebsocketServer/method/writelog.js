/**
 * Created by Administrator on 2018/3/1.
 */
var fs = require('fs');

//日志的公用函数
writelog = function(s_order,s_str){
    if(s_order=='console'){
        console.log(s_str);
    }else if(s_order=='log'){
        //获得年月日
        var t_date = new Date();
        var t_year = t_date.getFullYear();
        t_year = t_year < 10 ? ('0' + t_year) : t_year;
        var t_month = t_date.getMonth()+1;
        t_month = t_month < 10 ? ('0' + t_month) : t_month;
        var t_day = t_date.getDate();
        t_day = t_day < 10 ? ('0' + t_day) : t_day;
        var t_hour = t_date.getHours();
        t_hour = t_hour < 10 ? ('0' + t_hour) : t_hour;
        var t_minute = t_date.getMinutes();
        t_minute = t_minute < 10 ? ('0' + t_minute) : t_minute;
        var t_seconds = t_date.getSeconds();
        t_seconds = t_seconds < 10 ? ('0' + t_seconds) : t_seconds;
        var s_path = "./log/";
        //创建一个文件名
        var s_fileName = "log"+ t_year+t_month+t_day + ".txt";
        //创建一个可读流
        var writerStream = fs.createWriteStream(s_path + s_fileName,{'flags': 'a'});
        // 使用 utf8 编码写入数据
        writerStream.write(t_year+"-"+t_month + "-"+t_day+" "+t_hour+":"+t_minute+":"+t_seconds +" "+s_str + "。\n",'UTF8');
    }else if(s_order=='none'){
        console.log();
    }else{
        console.log("更新中。。。。。。");
    }
};
module.exports = writelog;