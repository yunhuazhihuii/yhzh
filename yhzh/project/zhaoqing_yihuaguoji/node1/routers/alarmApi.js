   /******************************************
   *报警模块数据操作
   *@author： chenms 2018-9-15
   *
   *************************************/
   var express = require('express'); 
   var nodeApi = require('../module/NodeApi');
   var common = require('../module/common');
   var bodyParser = require('body-parser');
   var router = express.Router();

   /**查询报警表yh_alarm数据 chenms 2018-9-15
   *
   */
   router.post('/get_alarm', function (req, res) {
   	var begindate = req.body.begindate; //报警日期从
   	var enddate = req.body.enddate; //报警日期 到
   	var paraIn = [];
    console.log('报警查询条件：' + begindate + ";" + enddate);
    var selectSql = "select alarmId,DATE_FORMAT(t.date,'%Y-%m-%d %H:%i:%s') date,(case t.system when 'BA' then '空调系统' else '其它' end) system," + 
    	"t3.description,t.problem,(case t.ACK when '0' then '未确认' when '1' then '已确认' else '未确认' end) ACK," + 
    	"DATE_FORMAT(t.sureTime,'%Y-%m-%d %H:%i:%s') sureTime,t4.name sureUser,t.remark from yh_alarm t " + 
    	"left join viewreflect t2 on t.reflectvar=t2.reflectvar " + 
    	"left join devmessage t3 on t2.devcode=t3.devcode " +
    	"left join login t4 on t.sureUser=t4.id " +
    	"where 1=1 ";
    if(!common.isNull(begindate)){
    	selectSql += " and t.date >= ?";
    	paraIn.push(begindate);
    }
    if(!common.isNull(enddate)){
    	selectSql += " and t.date <= ?";
    	paraIn.push(enddate);
    }
    selectSql += " ORDER BY t.date desc";
    nodeApi.query(selectSql,paraIn,
	    function (data) {
	       res.send({code: 1,data: data});
    	}
    );
   });

   /**报警确认,就是更新报警表 chenms 2018-9-17
   *
   */
   router.post('/sure_alarm', function (req, res) {
   	 var alarmId = req.body.alarmId;
   	 var sureUser = req.body.sureUser;
   	 var remark = req.body.remark;
   	 var sql = "update yh_alarm t set t.ACK='1',t.sureUser=?,t.remark=?,sureTime=sysdate() where t.alarmId=?";
   	 var paraIn = [];
   	 paraIn.push(sureUser);
   	 paraIn.push(remark);
   	 paraIn.push(alarmId);
   	 nodeApi.query(sql,paraIn,
	    function (data) {
	       //获取“确认时间”返回
	       var sql2 = "select DATE_FORMAT(t.sureTime,'%Y-%m-%d %H:%i:%s') sureTime from yh_alarm t where t.alarmId=?";
	       var paraIn2 = [];
	       paraIn2.push(alarmId);
	       nodeApi.query(sql2,paraIn2,function (data2){
	       	 res.send({code: 1,data: data2});	
	       });
    	}
     );
   });

   /**插入数据进报警表 chenms 2018-10-21
   * 并返回插入的数据
   */
   router.post('/insert_alarm', function (req, res) {
     console.log("进入插入报警数据方法");
     nodeApi.getNextSerialNo("yh_alarm.alarmId",function(data1){
       var alarmId = data1;
       console.log("获取到新的alarmId：",data1);
       var date = req.body.date;
       var reflectvar = req.body.reflectvar;
       var system = req.body.system;
       var AlarmLevel = req.body.AlarmLevel;
       var problem = req.body.problem;

       var sql = "insert into yh_alarm(alarmId,date,reflectvar,system,AlarmLevel,problem,ACK) values (?,?,?,?,?,?,'0')";
       var params = [];
       params.push(alarmId);
       params.push(date);
       params.push(reflectvar);
       params.push(system);
       params.push(AlarmLevel);
       params.push(problem);

       var sqlparamsEntities = [];
       sqlparamsEntities.push({sql: sql,params: params});
       nodeApi.execTrans(sqlparamsEntities,function(data){
         //插入数据成功，同时将插入的数据返回
         var selectSql = "select alarmId,DATE_FORMAT(t.date,'%Y-%m-%d %H:%i:%s') date,(case t.system when 'BA' then '空调系统' else '其它' end) system," + 
          "t3.description,t.problem,(case t.ACK when '0' then '未确认' when '1' then '已确认' else '未确认' end) ACK," + 
          "DATE_FORMAT(t.sureTime,'%Y-%m-%d %H:%i:%s') sureTime,t4.name sureUser,t.remark from yh_alarm t " + 
          "left join viewreflect t2 on t.reflectvar=t2.reflectvar " + 
          "left join devmessage t3 on t2.devcode=t3.devcode " +
          "left join login t4 on t.sureUser=t4.id " +
          "where t.alarmId=?";
         var paraIn = [];
         paraIn.push(alarmId);
         nodeApi.query(selectSql,paraIn,
           function (data2) {
             res.send({code: 1,data: data2});
           }
         );
       });
     });
     

   });


  module.exports = router;