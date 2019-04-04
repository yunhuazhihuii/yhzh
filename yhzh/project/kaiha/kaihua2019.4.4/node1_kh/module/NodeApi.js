
var express = require('express');
var app=express();
var mysql = require('mysql');
var databaseConfig = require('./database.config.js');  //引入数据库配置模块中的数据
var async = require("async");
var common = require('./common');

//数据库连接池 chenms 2018-9-17
var pool = mysql.createPool(databaseConfig);  


module.exports ={
	// 获取登录数据
	query:function(sql,params,callback){
	
    //每次使用的时候需要创建链接，数据操作完成之后要关闭连接
    var connection = mysql.createConnection(databaseConfig);
		connection.connect(function(err){
	      if(err){
	        console.log('数据库连接失败');
	        throw err;
	      }

	      console.log("数据库链接成功");
    	  //开始数据操作
        console.log("准备执行sql:" + sql + params);

    		connection.query(sql,params, function(err2,data){
            if(err2){
              console.log('数据操作失败');
              throw err2;
            }
            //将查询出来的数据返回给回调函数
            callback && callback(data);
        });

		    //停止链接数据库，必须再查询语句后，要不然一调用这个方法，就直接停止链接，数据操作就会失败
        connection.end(function(err3){
            if(err3){
              console.log('关闭数据库连接失败！');
              throw err3;
            }
        });
	  });

  },


  /**
  *执行事务 chenms 2018-9-18
  */
  execTrans: execTrans,

  /**
  * 返回一个要执行的sql对象 chenms 2018-9-18
  */
  _getNewSqlParamEntity: function(sql, params, callback) {
    if (callback) {
      return callback(null, {
        sql: sql,
        params: params
      });
    }
    return {
      sql: sql,
      params: params
    };
  },

  /**
  *获取下一编号 chenms 2018-9-24
  */
  getNextSerialNo: getNextSerialNo
}

/**执行事务,一般要执行多个update或insert且属于同一事务则用到该方法 chenms 2018-9-18
* @param sqlparamsEntities: 要执行的sql对象数组
*/
function execTrans(sqlparamsEntities, callback) {
  pool.getConnection(function (err, connection) {
    if (err) {
      return callback(err, null);
    }
    connection.beginTransaction(function (err) {
      if (err) {
        return callback(err, null);
      }
      console.log("开始执行transaction，共执行" + sqlparamsEntities.length + "条sql语句");
      var funcAry = [];
      sqlparamsEntities.forEach(function (sql_param) {
        var temp = function (cb) {
          var sql = sql_param.sql;
          var param = sql_param.params;
          connection.query(sql, param, function (tErr, rows, fields) {
            if (tErr) {
              connection.rollback(function () {
                console.log("事务失败，" + sql_param + "，ERROR：" + tErr);
                throw tErr;
              });
            } else {
              return cb(null, 'ok');
            }
          })
        };
        funcAry.push(temp);
      });
 
      async.series(funcAry, function (err, result) {
        console.log("transaction error: " + err);
        if (err) {
          connection.rollback(function (err) {
            console.log("transaction error: " + err);
            connection.release();
            return callback(err, null);
          });
        } else {
          connection.commit(function (err, info) {
            console.log("transaction info: " + JSON.stringify(info));
            if (err) {
              console.log("执行事务失败，" + err);
              connection.rollback(function (err) {
                console.log("transaction error: " + err);
                connection.release();
                return callback(err, null);
              });
            } else {
              connection.release();
              //return callback(null, info);
              return callback(info);
            }
          });
        }
      })
    });
  });
}


/**
*获取下一编号(要做在事务中避免编号重复情况) chenms 2018-9-24
*@param tablekey 序列名
*/
function getNextSerialNo(tablekey,callback){
  var rst = null;
  // 从链接池里得到connection
  pool.getConnection(function(err, connection) {
    if(err) {
        console.error('getNextSerialNo: mysql 从连接池中获取连接失败');
        throw err;
    }
    connection.beginTransaction(function(errTr) {
      if(errTr) {
        console.error('getNextSerialNo: 开始事务失败');
        connection.release();
        throw errTr;
      }
      console.error('getNextSerialNo: 开始事务成功，tablekey：' + tablekey);
      var paraIn = [];
      paraIn.push(tablekey);
      var sql1 = "update yh_serial t set t.format=t.format where t.tablekey=?";
      //锁住该行记录
      connection.query(sql1,paraIn, function(errLock,data1){
        if(errLock){
          console.log('getNextSerialNo锁表失败');
          connection.rollback(function() {
            connection.release();
            throw errLock;
          });
          return;
        }
      });
      //查询当前编号,生成新的编号
      var sql2 = "select t.currentNo,t.format,currdate from yh_serial t where t.tablekey=?";
      var currentNo = "";
      var format = "";
      var currdate = "";
      connection.query(sql2,paraIn, function(err2,data2){
        if(err2){
          console.log('getNextSerialNo查询当前编号失败');
          connection.rollback(function() {
            connection.release();
            throw err2;
          });
          return;
        }
        console.log("getNextSerialNo查询到的编号表长度：",data2.length);
        if(data2.length > 0){
          currentNo = data2[0].currentNo;
          format = data2[0].format;
          currdate = data2[0].currdate;
          console.log('getNextSerialNo查询当前编号成功，currentNo：' + currentNo + ";format:" + format + ";currdate：" + currdate);
        
          console.log('外面的话，currentNo：' + currentNo + ";format:" + format + ";currdate：" + currdate);
          //format格式如：am{yyyymmdd}dddddd，第一部分为固定字母，中间为日期时间，后面为数字
          //1.若currdate为空或currdate不为当天，则从当天第一个生成；2.若currdate为当天，则取currentNo + 1；
          var index1 = format.indexOf("{");
          var index2 = format.indexOf("}");
          //字符串索引号从0开始，"rcinn.cn".substring(1,5) 返回"cinn"
          var format1 = format.substring(0,index1); //固定字符串头
          var format2 = format.substring(index1 + 1,index2);
          var format3 = format.substring(index2 + 1);
          var numLength = format3.length; //数字部分长度
          var nowDateTime = common.formatDateTime(new Date(),"yyyyMMddhhmmss"); //获取服务器当前日期时间
          var nowDate = nowDateTime.substring(0,8);
          var nowTime = nowDateTime.substring(8);
          format2 = format2.replace("yyyyMMdd",nowDate);
          format2 = format2.replace("hhmmss",nowTime);
          if(common.isNull(currdate) || currdate != nowDate){
            rst = format1 + format2 + getNextStr(numLength,"0");
          }else{
            rst = format1 + format2 + getNextStr(numLength,currentNo.substring(format1.length + format2.length));
          }
          //更新新的编号
          var sql3 = "update yh_serial t set t.currentNo=?,t.currdate=? where t.tablekey=?";
          var paraIn3 = [];
          paraIn3.push(rst);
          paraIn3.push(nowDate);
          paraIn3.push(tablekey);
          console.log('getNextSerialNo准备执行update,新编号为：',rst);
          connection.query(sql3,paraIn3, function(err3,data3){
            if(err3){
              console.log('getNextSerialNo更新新的编号失败');
              connection.rollback(function() {
                connection.release();
                throw err3;
              });
              //return;
            }
            console.log('getNextSerialNo准备提交事务');
            //提交事务
            connection.commit(function (err4, info4) {
              //console.log("transaction info4: " + JSON.stringify(info4));
              if (err4) {
                console.log("getNextSerialNo提交事务失败，" + err4);
                connection.rollback(function (err4) {
                  //console.log("transaction error: " + err4);
                  connection.release();
                  //return;
                });
              } else {
                console.log("getNextSerialNo提交事务成功！");
                connection.release();
                //返回新的编号
                //return rst;
                callback(rst);
              }
            });
          });
        }else{
          connection.rollback(function() {
          });
          connection.release();
          throw new Error('根据当前序列号找不到记录！');
          //return;
        }
      });
    });
  });
}

/**根据当前值+1后按指定长度返回（长度不足前面补0）chenms 2018-9-25
* @param strLength: 长度，整型
* @param currValue: 
*/
function getNextStr(strLength,currValue){
  var nextInt = parseInt(currValue) + 1;
  var nextStr = nextInt + "";
  while(nextStr.length < strLength){
    nextStr = "0" + nextStr;
  }
  return nextStr;
}