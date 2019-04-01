
// 引入vue  引入vuex 并且use vuex
import Vue from 'vue'
import Vuex from 'vuex'

import * as actions from './actions'
import {setSession} from '../common/js/util'
Vue.prototype.random = n => Math.floor(n * Math.random())

Vue.use(Vuex)

// 定义数据:应用初始状态
const state = {
    // 聊天的socket
    socket:'',
    name: '', //姓名
    // 头像随机生成
    avatarUrl: `http://omratag7g.bkt.clouddn.com/icon-avatar${Vue.prototype.random(21)}.svg`,
    //照明楼层信息
    floorInfon: [],
    //空调楼层信息
    saveKtData:[],
    //控制导航切换样式
    classStyle:false,
    //聊天好友名字
    friendName:[],
    // 日程控制或者手动控制状态 
    RcState:'日程控制',
    NewFanVal:'日程控制',
    // 消息提示小图标
    showBadge:false,
    // admin用户权限
    admin:'',
    //报警数据 chenms 2018-10-17
    alarmData: []


}

// 定义方法 :定义所需的 mutations
const mutations = {
   //本地保存socket方法
    savaSocket(state, socket) {
      state.socket = socket;
    },
      //消息图标显示
    showBadges(state, showBadge) {
      state.showBadge = showBadge;
    },
     //保存消息用户名字
    friendNames(state, friendName) {
      state.friendName = friendName;
    },
     //日程控制或者手动控制状态  空调
    RcStateVal(state, RcState) {
      state.RcState = RcState;
    },
    //日程控制或者手动控制状态 新风机
    fanStateVal(state, NewFanVal) {
      state.NewFanVal = NewFanVal;
    },
   //本地保存用户名方法
    sessionName(state, name) {
      state.name = name;
      setSession('name', name);
    },
    //存储照明楼层信息
    setfloorInfon(state, floorInfon) {
      state.floorInfon = floorInfon;
    },
     //存储空调楼层信息
    saveKTData(state, saveKtData) {
      state.saveKtData = saveKtData;
    },
    //主导航样式
    classStyle(state,name) {
      state.classStyle = name;
    },
     //主导航样式
    userAdmin(state,admin) {
      state.admin = admin;
    },
    //设置报警数据 chenms 2018-10-17
    setAlarmData(state,alarmData) {
      state.alarmData = alarmData;
    }

}

// 暴露:创建 store 实例
export default new Vuex.Store({
    actions,
    state,
    mutations
})