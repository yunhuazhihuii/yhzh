   var express = require('express'); 
   var nodeApi = require('../module/NodeApi');
   var bodyParser = require('body-parser');
   var router = express.Router();
       // 获取空调数据
   router.post('/get_ktFl', function (req, res) {
    //空调数据
    console.log(req.body.floorNum)
    console.log(req.body.subsystem)
    nodeApi.query("select * from devmessage where  floorNum=? and subsystem=?",[req.body.floorNum,req.body.subsystem],
      function (data) {
       res.send({code: 1,data: data})
    })
  })
    // 获取空调映射数据
   router.post('/get_kt', function (req, res) {
    //空调数据
    nodeApi.query("select * from devmessage inner join viewreflect on devmessage.devcode = viewreflect.devcode where  devmessage.devcode=?",[req.body.devcode],
      function (data) {
      res.send({code: 1,data: data})
    })
  })
     // 获取所有空调映射数据
   router.post('/get_allkt', function (req, res) {
    console.log(req.body.subsystem)
    //空调数据
    nodeApi.query("select * from devmessage where subsystem=?;select * from viewreflect where varType=?",[req.body.subsystem,req.body.varType],
      function (data) {
       res.send({code: 1,data: data})
    })
  })
  module.exports = router