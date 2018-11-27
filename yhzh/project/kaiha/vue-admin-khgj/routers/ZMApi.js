   var express = require('express'); 
   var nodeApi = require('../module/NodeApi');
   var bodyParser = require('body-parser');
   var router = express.Router();
    // 照明楼层获取数据
   router.post('/get_zm', function (req, res) {
    //关联表查询
    //select * from pagemessage inner join PageReflect on 
    //pagemessage.PageCode = PageReflect.PageCode where pagemessage.PageCode="HLT0100011" AND 
    //PageReflect.VarType=1
    nodeApi.query('select * from pagemessage where PageCode = ?',[req.body.pageCode],
    function(data){
        res.send({code: 1,data: data})
      });
  })
   // 照明获取映射表数据
   router.post('/get_reflect', function (req, res) {
    console.log('数据'+req.body.pageCode)
    nodeApi.query("select * from pagereflect  where  VarType!=1 and PageCode=? ORDER BY LocalVar",[req.body.pageCode],
    function (data) {
       res.send({code: 1,data: data})
    })
  })
      // 更新设备名称数据
   router.post('/updataValue', function (req, res) {
    nodeApi.query("UPDATE pagemessage SET PLDefine = ? WHERE AddMess = ?", [req.body.tableData,req.body.floor],
      function (data) {
       res.send({code: 1,data: data})
    })
  })
    // 获取所有数据
   router.post('/get_All', function (req, res) {
    nodeApi.query('select * from  genlight ',[],
    function(data){
        res.send({code: 1,data: data})
      });
  })

   router.post('/get_All_fan', function (req, res) {
    nodeApi.query('select * from  fan ',[],
    function(data){
        res.send({code: 1,data: data})
      });
  })
    module.exports = router