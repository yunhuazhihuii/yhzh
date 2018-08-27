function getDate(){
    var t_date = new Date();
    var t_year = t_date.getFullYear();
    t_year = t_year < 10 ? ('0' + t_year) : t_year;
    var t_month = t_date.getMonth() + 1;
    t_month = t_month < 10 ? ('0' + t_month) : t_month;
    var t_day = t_date.getDate();
    t_day = t_day < 10 ? ('0' + t_day) : t_day;
    var t_hour = t_date.getHours();
    t_hour = t_hour < 10 ? ('0' + t_hour) : t_hour;
    var t_minute = t_date.getMinutes();
    t_minute = t_minute < 10 ? ('0' + t_minute) : t_minute;
    var t_seconds = t_date.getSeconds();
    t_seconds = t_seconds < 10 ? ('0' + t_seconds) : t_seconds;
    //var t_week = "日一二三四五六".charAt(t_date.getDay());
    var t_datetime = t_year + "-" + t_month + "-" + t_day + " " + t_hour + ":" + t_minute + ":" + t_seconds;
    //var t_datetime = t_year + "年" + t_month + "月" + t_day + "日 " + "星期" + t_week + " " + t_hour + ":" + t_minute + ":" + t_seconds;
    return t_datetime;
}
module.exports = getDate;