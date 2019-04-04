<template>
  <div>  

    <v-header></v-header>

    <div class="content">
      <div class="aside bj">
        <ul id="aside">
          <li class="other"><p>系统管理</p></li>
          <template v-if="sys_role.includes('店铺管理')">
          <li><a href=""><router-link to="/sys/shopping"><p>店铺列表管理</p></router-link></a>
          </li>
        </template>
        <template v-if="sys_role.includes('采购账号管理')">
          <li><a href=""><router-link to="/sys/Purchase"><p>采购账号管理</p></router-link></a>
          </li>
        </template>
        <template v-if="sys_role.includes('公司信息管理')">
          <li><a href=""><router-link to="/sys/CompanyInformation"><p>公司信息管理</p></router-link></a>
          </li>
        </template>
          <li class="other"><p>子账号管理</p></li>
           <template v-if="sys_role.includes('角色管理')">
          <li><a href=""><router-link to="/sys/role"><p>角色管理</p></router-link></a></li>
        </template>
        <template v-if="sys_role.includes('员工管理')">
          <li><a href=""><router-link to="/sys/staff"><p>员工管理</p></router-link></a></li>
        </template>
          <li class="other"><p>主页管理</p></li>
          <template v-if="sys_role.includes('公告管理')">
          <li><a href=""><router-link to="/sys/notice"><p>发布公告管理</p></router-link></a></li>
        </template>
        <template v-if="sys_role.includes('工具管理')">
          <li><a href=""><router-link to="/sys/tool"><p>常用工具管理</p></router-link></a></li>
        </template>
          <li><a href=""><router-link to="/sys/kpi"><p>十五天业绩预览</p></router-link></a></li>
        </ul>
      </div>
      <div class="order-right">
        <router-view></router-view>
      </div>
      
    </div>
  </div>
</template>

<script>

import Header from './Header.vue';
import {getSession} from '../common/js/util.js'
export default { 

  components:{
    'v-header':Header 
  }, 

  data () {  /*业务逻辑里面定义的数据*/
    return {
      msg: '你好vue',
      sys_role:'',

    }
  },
     created(){
    
        this.sys_role = getSession('sys_role')
        
        console.log(this.sys_role+'系统的权限')
        

      },
}
</script>

<style scoped>
  .head {
    width: 100%;
    height: 60px;
    background-color: #76A3A3;
  }
  .nav {
    float: left;
    margin-left: 200px;
    /* margin-top: 30px; */
  }
  .nav ul {
    overflow: hidden;
  }
  .nav li {
    float: left;
    /* margin: 0 20px; */
    list-style: none;
  }
  .nav li a {
    height: 100%;
    color: #fff;
    text-decoration: none;
    font-weight: bold;
  }
  .nav li a:hover {
    color: red;
    background-color: #669999
  }
  .user {
    float: right;
  }
  
  .content {
    position:absolute;
    width: 100%;
    height: 100%;
  }
  .aside {
    /* height: 450px; */
    width: 180px;
    background-color: #fff;
    position:absolute;
    border: 1px solid #CCCCCC;
    top: 15px;
    left: 25px;
  }
  #aside {
    width: 100%;
    /* height: 100%; */
    display: flex;
    flex-direction: column;
  }
  #aside li {
    flex-wrap: nowrap;
    list-style: none;
    width: 100%;
    /* height: 30px; */
    border-bottom: 1px solid #CCCCCC;
  }
  #aside li a {
    text-decoration: none;
    color: #333333;
    display: block;
    width: 100%;
    height: 40px;
    /* height: 100%; */
    text-align: center;
    /* margin-top: 8px; */
  }
  #aside li a p {
    padding-top: 10px;
  }
  .other {
    font-weight: bold;
    background-color: #F2F2F2;
    height: 40px;
    /* padding-top: 5px; */
  }

.other p {
    margin-top: 10px;
  }

  .router-link-active {
    background-color: #76A3A3;
  }
  #aside li a :hover {

  }
  .order-right {
    width: 1710px;
    height: 100%;
    position: absolute;
    top: 15px;
    left: 220px;
    background-color: ;
  }

</style>
