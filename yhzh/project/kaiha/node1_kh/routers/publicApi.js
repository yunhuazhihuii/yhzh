  var express = require('express'); 
  var nodeApi = require('../module/NodeApi');
  var bodyParser = require('body-parser');
  var router = express.Router();
  var async = require("async");
  var common = require('../module/common');

  // 获取设备属性列表
  router.post('/get_dev_attr', function (req, res) {
    //console.log(req.body.devtypeid); ifnull(t.attrWidth,'80')
    nodeApi.query("select t.devAtrrId,t.attrClass,t.attrEnName,t.attrCnName,t.attrUnit,t.attrDict," +
        "t.attrWidth, t.tabOrder " +
        " from YH_DEV_ATTR t where  t.devtypeid=? order by t.tabOrder",
      [req.body.devtypeid],
      function (devAttrData) {
        var i = 0;
        var attrLength = devAttrData.length;
        async.each(devAttrData,function(item,callback){
          if(common.isNull(item.attrDict)){ //属性数据字典为空
            i++;
            if(i == attrLength){
              res.send({code: 1,data: devAttrData});
            }
          }else{
            //属性数据字典不为空
            nodeApi.query("select t.mykey,t.val from YH_DICT t where t.dictname=?",[item.attrDict],
              function(dictData){
                var kvData = {};
                for(var k = 0;k < dictData.length;k++){
                  kvData[dictData[k].mykey] = dictData[k].val;
                }
                item.dictData = kvData;
                i++;
                if(i == attrLength){
                  res.send({code: 1,data: devAttrData});
                }
            });
          }
        });

        
    });
  });

  // 获取设备及点映射信息
  router.post('/get_dev_table', function (req, res) {
    //查询设备信息
    /*"select t.devId,t.devEnName,t.devCnName,floor,location from YH_DEV_INFO t " +
        " where t.devtypeid=? and t.floor>=? and t.floor<=? order by t.floor;" +
        "select concat_ws('.',t.adapterId,t.controllerId,t.pointId) pointId,t.devId,t.devAtrrId " +
        " from YH_DEV_POINT_REF t left join YH_DEV_INFO t2 on t.devId=t2.devId " +
        " where t2.devtypeid=? and t2.floor>=? and t2.floor<=?"*/
    nodeApi.query("select t.devId,t.devEnName,t.devCnName,floor,location from YH_DEV_INFO t " +
        " where t.devtypeid=? and t.floor>=? and t.floor<=? order by t.floor",
      [req.body.devtypeid,req.body.floor1,req.body.floor2],
      function (devInfoData) {

        /*for(var i = 0;i < devInfoData.length;i++){
          nodeApi.query("select concat_ws('.',t.adapterId,t.controllerId,t.pointId) pointId,t.devId,t.devAtrrId " +
            " from YH_DEV_POINT_REF t where t.devId=?",
            [devInfoData[i].devId],
            function (pointData){
              for(point in pointData){
                devInfoData[i][point.devAtrrId] = {pointId: point.pointId,value:"测试" + point.pointId};
              }
            });
        }
        res.send({code: 1,data: devInfoData});*/
        var i = 0;
        var devLength = 0;
        if(devInfoData){
          devLength = devInfoData.length;
        }
        if(devLength == 0){
          //查询到设备信息为空，直接返回
          res.send({code: 1,data: devInfoData});
        }else{
          async.each(devInfoData,function(item,callback){
                nodeApi.query("select concat_ws('.',t.adapterId,t.controllerId,t.pointId) pointId,t.devId,t.devAtrrId " +
                  " from YH_DEV_POINT_REF t where t.devId=?",
                  [item.devId],
                  function (pointData){
                    i++;
                    for(var j = 0;j < pointData.length;j++){
                      var point = pointData[j];
                      item[point.devAtrrId] = {pointId: point.pointId,value:"0"};//value:"测试" + point.pointId
                    }
                    //callback(null);
                    if(i == devLength){
                      res.send({code: 1,data: devInfoData});
                    }
                });
              },function(err){
                console.log(err);
          });
        }
        //res.send({code: 1,data: devInfoData});
    });
  });

  //查询点历史数据
  router.post('/get_his_data', function (req, res) {
    var devtypeid = req.body.devtypeid;
    var floor1 = req.body.floor1;
    var floor2 = req.body.floor2;
    var recDate = req.body.recDate;
    var recTime = req.body.recTime;
    //查询设备列表信息
    nodeApi.query("select t.devId,t.devEnName,t.devCnName,floor,location from YH_DEV_INFO t " +
        " where t.devtypeid=? and t.floor>=? and t.floor<=? order by t.floor",
      [devtypeid,floor1,floor2],
      function (devInfoData) {
        var i = 0;
        var devLength = 0;
        if(devInfoData){
          devLength = devInfoData.length;
        }
        if(devLength == 0){
          //查询到设备信息为空，直接返回
          res.send({code: 1,data: devInfoData});
        }else{
          //查询设备类型表 判断其是否有父设备编号，以确定是查哪个历史数据表
          nodeApi.query("select t.pareDevTypeId from YH_DEV_TYPE t where t.devtypeid=?",
            [devtypeid],
            function (devTypeData) {
              var hisIndex = devTypeData[0].pareDevTypeId;
              if(common.isNull(hisIndex)){
                hisIndex = devtypeid;
              }
              //每个设备循环去查历史表
              async.each(devInfoData,function(item,callback){
                //查询历史表
                nodeApi.query("select t2.devId,t2.devAtrrId,t.pointvalue from yh_point_val_his" + hisIndex + " t " +
                  " left join YH_DEV_POINT_REF t2 on t.pointId=t2.pointId where t2.devId=? " +
                  " and t.recReason='1' and t.recDate=? and recTime=?",
                  [item.devId,recDate,recTime],
                  function (pHisData) {
                    i++;
                    for(var j = 0;j < pHisData.length;j++){
                      var pointVal = pHisData[j];
                      item[pointVal.devAtrrId] = pointVal.pointvalue;
                    }
                    if(i == devLength){
                      res.send({code: 1,data: devInfoData});
                    }
                });
              },function(err){
                console.log(err);
              });

          });
        }
    });
  });

  //查询某种类型设备历史数据在某一天有记录的时间清单[只查正常间隔记录的时间点]
  router.post('/get_recTime_list', function (req, res) {
    var devtypeid = req.body.devtypeid;
    var recDate = req.body.recDate;
    //查询设备类型表 判断其是否有父设备编号，以确定是查哪个历史数据表
    nodeApi.query("select t.pareDevTypeId from YH_DEV_TYPE t where t.devtypeid=?",
      [devtypeid],
      function (devTypeData){
        var hisIndex = devTypeData[0].pareDevTypeId;
        if(common.isNull(hisIndex)){
          hisIndex = devtypeid;
          nodeApi.query("select distinct t.recTime from yh_point_val_his" + hisIndex + " t " +
            " left join YH_DEV_POINT_REF t2 on t.pointId=t2.pointId " +
            " left join YH_DEV_INFO t3 on t2.devId=t3.devId " +
            " where t.recReason='1' and t.recDate=? and t3.devtypeid=?",
            [recDate,devtypeid],
            function (recTimeData) {
              res.send({code: 1,data: recTimeData});
          });
        }

    });
  });


  module.exports = router;