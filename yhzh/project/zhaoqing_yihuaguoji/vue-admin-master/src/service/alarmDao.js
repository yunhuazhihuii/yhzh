import Vue from 'vue'
import axios from 'axios'

// 全局配置post请求
axios.defaults.headers['Content-Type'] = 'application/json';
axios.defaults.baseURL='http://localhost:9000/';

/**
*查询报警数据 chenms 2018-9-15
*@param begindate： 报警日期从
*@param enddate: 报警日期 到
*/
export const getAlarm = (begindate,enddate,res) => {
    axios.post('/get_alarm',{
      begindate: begindate,
      enddate: enddate
    })
    .then(function (response) {
      res(response.data);
    })
    .catch(function (error) {
      console.log("查询报警数据报错：" + error);
    });
};

/**
*确认报警 chenms 2018-9-19
*@param alarmId 报警id
*@param sureUser: 确认用户id
*@param remark: 备注
*/
export const sureAlarm = (alarmId,sureUser,remark,res) => {
    axios.post('/sure_alarm',{
      alarmId: alarmId,
      sureUser: sureUser,
      remark: remark
    })
    .then(function (response) {
      res(response.data);
    })
    .catch(function (error) {
      console.log("查询报警数据报错：" + error);
    });
};

/**
*新增报警 chenms 2018-10-21
*@param date 报警时间
*@param reflectvar: 报警点编号
*@param system: 子系统
*@param AlarmLevel: 报警级别
*@param problem: 故障原因
*/
export const insertAlarm = (date,reflectvar,system,AlarmLevel,problem,res) => {
    axios.post('/insert_alarm',{
      date: date,
      reflectvar: reflectvar,
      system: system,
      AlarmLevel: AlarmLevel,
      problem: problem
    })
    .then(function (response) {
      res(response.data);
    })
    .catch(function (error) {
      console.log("新增报警数据报错：" + error);
    });
};
