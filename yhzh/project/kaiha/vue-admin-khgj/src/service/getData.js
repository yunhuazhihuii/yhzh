import Vue from 'vue'
import axios from 'axios'
import {viewConfig} from '../../config/yhzh_view_cfg.js'
// 全局配置post请求
axios.defaults.headers['Content-Type'] = 'application/json'
axios.defaults.baseURL=viewConfig.node1Url

// 获取设备属性信息 chenms 2018-12-1
export const getDevAttr = (devtypeid,res) => {
    axios.post('/get_dev_attr',{
      devtypeid:devtypeid //设备类型编号
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};

// 获取设备及点映射信息 chenms 2018-12-1
export const getDevTable = (devtypeid,floor1,floor2,res) => {
    axios.post('/get_dev_table',{
      devtypeid:devtypeid, //设备类型编号
      floor1:floor1, //楼层范围1
      floor2:floor2 //楼层范围2
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};

// 获取历史数据 chenms 2018-12-10
export const getHisData = (devtypeid,floor1,floor2,recDate,recTime,res) => {
    axios.post('/get_his_data',{
      devtypeid:devtypeid, //设备类型编号
      floor1:floor1, //楼层范围1
      floor2:floor2, //楼层范围2
      recDate:recDate, //日期 yyyy-mm-dd格式
      recTime:recTime //时间 hh24:min格式，如18:30
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};


// 获取某种类型设备历史数据在某一天有记录的时间清单[只查正常间隔记录的时间点] chenms 2018-12-10
export const getRecTimeList = (devtypeid,recDate,res) => {
    axios.post('/get_recTime_list',{
      devtypeid:devtypeid, //设备类型编号
      recDate:recDate //日期 yyyy-mm-dd格式
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};



