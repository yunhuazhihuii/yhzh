/**
 * Created by Administrator on 2018/3/20.
 */
var post_data={"name":"test001","pass":"xxxx"};
function getDatas(){
    $.ajax({
        url: "http://172.16.8.2:8080/YHZHADAPTER/rest/holiday/sche/:1.1.1",
        type: 'post',
        contentType: "application/json; charset=utf-8",
        data:JSON.stringify(post_data),
        success:function (data) {
            //调用成功
            console.log(data);
        },
        error: function(data, textStatus, errorThrown){
            //调用失败
        }
    });
}
module.exports = getDatas;
