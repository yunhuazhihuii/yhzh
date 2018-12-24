  var express = require('express'); 
  var nodeApi = require('../module/NodeApi');
  var bodyParser = require('body-parser');
  var router = express.Router();
  var async = require("async");
  var common = require('../module/common');

  // 获取设备属性列表
  router.post('/get_dev_attr', function (req, res) {
    //console.log(req.body.devtypeid); ifnull(t.attrWidth,'80')
    nodeApi.query("select t.devAttrId,t.attrClass,t.attrEnName,t.attrCnName,t.attrUnit,t.attrDict," +
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

  // 获取设备及点映射信息,实时数据单个表格
  router.post('/get_dev_table', function (req, res) {
    //查询设备信息
    /*"select t.devId,t.devEnName,t.devCnName,floor,location from YH_DEV_INFO t " +
        " where t.devtypeid=? and t.floor>=? and t.floor<=? order by t.floor;" +
        "select concat_ws('.',t.adapterId,t.controllerId,t.pointId) pointId,t.devId,t.devAttrId " +
        " from YH_DEV_POINT_REF t left join YH_DEV_INFO t2 on t.devId=t2.devId " +
        " where t2.devtypeid=? and t2.floor>=? and t2.floor<=?"*/
    //floor+0的目的是为了转成数字去比较，要不字符比较会有问题的
    nodeApi.query("select t.devId,t.devEnName,t.devCnName,floor,location from YH_DEV_INFO t " +
        " where t.devtypeid=? and t.floor+0>=? and t.floor+0<=? order by t.floor", 
      [req.body.devtypeid,req.body.floor1,req.body.floor2],
      function (devInfoData) {

        /*for(var i = 0;i < devInfoData.length;i++){
          nodeApi.query("select concat_ws('.',t.adapterId,t.controllerId,t.pointId) pointId,t.devId,t.devAttrId " +
            " from YH_DEV_POINT_REF t where t.devId=?",
            [devInfoData[i].devId],
            function (pointData){
              for(point in pointData){
                devInfoData[i][point.devAttrId] = {pointId: point.pointId,value:"测试" + point.pointId};
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
                nodeApi.query("select concat_ws('.',t.adapterId,t.controllerId,t.pointId) pointId,t.devId,t.devAttrId " +
                  " from YH_DEV_POINT_REF t where t.devId=?",
                  [item.devId],
                  function (pointData){
                    i++;
                    for(var j = 0;j < pointData.length;j++){
                      var point = pointData[j];
                      item[point.devAttrId] = {pointId: point.pointId,value:"0"};//value:"测试" + point.pointId
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

  // 获取设备及点映射信息,实时数据两个表格
  router.post('/get_dev_dtab', function (req, res) {
    var devtypeid = req.body.devtypeid; //设备类型编号
    var floor1 = req.body.floor1;
    var floor2 = req.body.floor2;
    var pageCount = req.body.pageCount; //表格显示条数

    var devPointData1;
    var devPointData2;
    var finish1 = false;//表格1数据已经完全构造完了
    var finish2 = false;//表格2数据已经完全构造完了
    //查询设备信息表，表格1数据
    nodeApi.query("select t.devId,t.devEnName,t.devCnName,floor,location from YH_DEV_INFO t " +
        " where t.devtypeid=? and t.floor+0>=? and t.floor+0<=? order by t.floor,t.devEnName limit 0, ?",
      [devtypeid,floor1,floor2,pageCount],
      function (devInfoData1) {
        devPointData1 = devInfoData1;
        var i = 0;
        var devLength = 0;
        if(devPointData1){
          devLength = devPointData1.length;
        }
        if(devLength == 0){
          //查询到设备信息为空，直接返回
          res.send({code: 1,data: devPointData1});
        }else{
          async.each(devPointData1,function(item,callback){
                nodeApi.query("select concat_ws('.',t.adapterId,t.controllerId,t.pointId) pointId,t.devId,t.devAttrId " +
                  " from YH_DEV_POINT_REF t where t.devId=?",
                  [item.devId],
                  function (pointData){
                    for(var j = 0;j < pointData.length;j++){
                      var point = pointData[j];
                      item[point.devAttrId] = {pointId: point.pointId,value:"0"};//value:"测试" + point.pointId
                    }
                    i++;
                    if(i == devLength){//说明设备列表循环结束了
                      finish1 = true;
                      if(finish2){
                        res.send({code: 1,data: {devPointData1:devPointData1,devPointData2:devPointData2}});
                      }
                    }
                });
              },function(err){
                console.log(err);
          });
          //查询设备信息表，表格2数据
          nodeApi.query("select t.devId,t.devEnName,t.devCnName,floor,location from YH_DEV_INFO t " +
              " where t.devtypeid=? and t.floor+0>=? and t.floor+0<=? order by t.floor,t.devEnName limit ?,?",
            [devtypeid,floor1,floor2,pageCount,pageCount],
            function (devInfoData2) {
              devPointData2 = devInfoData2;
              var i2 = 0;
              var devLength2 = 0;
              if(devPointData2){
                devLength2 = devPointData2.length;
              }
              if(devLength2 == 0){
                //查询到设备信息为空
                finish2 = true;
                if(finish1){
                  res.send({code: 1,data: {devPointData1:devPointData1,devPointData2:devPointData2}});
                }
              }else{
                async.each(devPointData2,function(item,callback){
                      nodeApi.query("select concat_ws('.',t.adapterId,t.controllerId,t.pointId) pointId,t.devId,t.devAttrId " +
                        " from YH_DEV_POINT_REF t where t.devId=?",
                        [item.devId],
                        function (pointData2){
                          for(var j = 0;j < pointData2.length;j++){
                            var point = pointData2[j];
                            item[point.devAttrId] = {pointId: point.pointId,value:"0"};//value:"测试" + point.pointId
                          }
                          i2++;
                          if(i2 == devLength2){//说明设备列表循环结束了
                            finish2 = true;
                            if(finish1){
                              res.send({code: 1,data: {devPointData1:devPointData1,devPointData2:devPointData2}});
                            }
                          }
                      });
                    },function(err){
                      console.log(err);
                });
              }
          });
        }
    });
  });

  //查询点历史数据 单个表格
  router.post('/get_his_data', function (req, res) {
    var devtypeid = req.body.devtypeid;
    var floor1 = req.body.floor1;
    var floor2 = req.body.floor2;
    var recDate = req.body.recDate;
    var recTime = req.body.recTime;
    //查询设备列表信息
    nodeApi.query("select t.devId,t.devEnName,t.devCnName,floor,location from YH_DEV_INFO t " +
        " where t.devtypeid=? and t.floor+0>=? and t.floor+0<=? order by t.floor",
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
                nodeApi.query("select t2.devId,t2.devAttrId,t.pointvalue from yh_point_val_his" + hisIndex + " t " +
                  " left join YH_DEV_POINT_REF t2 on t.pointId=t2.pointId where t2.devId=? " +
                  " and t.recReason='1' and t.recDate=? and t.recTime=?",
                  [item.devId,recDate,recTime],
                  function (pHisData) {
                    i++;
                    for(var j = 0;j < pHisData.length;j++){
                      var pointVal = pHisData[j];
                      item[pointVal.devAttrId] = pointVal.pointvalue;
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

  //查询点历史数据 双表格
  router.post('/get_his_data2', function (req, res) {
    var devtypeid = req.body.devtypeid;
    var floor1 = req.body.floor1;
    var floor2 = req.body.floor2;
    var recDate = req.body.recDate;
    var recTime = req.body.recTime;
    //查询设备列表信息
    nodeApi.query("select t.devId,t.devEnName,t.devCnName,floor,location from YH_DEV_INFO t " +
        " where t.devtypeid=? and t.floor+0>=? and t.floor+0<=? order by t.floor,t.devEnName limit 0, ?",
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
                nodeApi.query("select t2.devId,t2.devAttrId,t.pointvalue from yh_point_val_his" + hisIndex + " t " +
                  " left join YH_DEV_POINT_REF t2 on t.pointId=t2.pointId where t2.devId=? " +
                  " and t.recReason='1' and t.recDate=? and t.recTime=?",
                  [item.devId,recDate,recTime],
                  function (pHisData) {
                    i++;
                    for(var j = 0;j < pHisData.length;j++){
                      var pointVal = pHisData[j];
                      item[pointVal.devAttrId] = pointVal.pointvalue;
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