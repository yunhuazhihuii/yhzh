
// 引入vue  引入vuex 并且use vuex
import Vue from 'vue'
import Vuex from 'vuex'

import * as actions from './actions'

Vue.prototype.random = n => Math.floor(n * Math.random())

Vue.use(Vuex)

// 定义数据:应用初始状态
const state = {
    // 聊天的socket
    socket:'',
    name: '', //姓名
    // 头像随机生成
    avatarUrl: `http://omratag7g.bkt.clouddn.com/icon-avatar${Vue.prototype.random(21)}.svg`,
    


}

// 定义方法 :定义所需的 mutations
const mutations = {
   //本地保存socket方法
    savaSocket(state, socket) {
      state.socket = socket;
    },
    
}

// 暴露:创建 store 实例
export default new Vuex.Store({
    actions,
    state,
    mutations
})