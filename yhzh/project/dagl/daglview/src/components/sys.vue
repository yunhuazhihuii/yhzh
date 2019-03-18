<template>
  <div>  
    <!-- 导航栏 -->
    <!-- <div class="head">
      <div class="nav">
        <ul>
          <router-link to="/home">
            <li><a href="">首页</a></li>
          </router-link>
          <router-link to="/order/allorders">
            <li><a href="">交易管理</a></li>
          </router-link>
          <router-link to="/storehouse/tocollected">
            <li><a href="">仓库管理</a></li>
          </router-link>
          <router-link to="/sys/shopping">
            <li><a href="">系统管理</a></li>
          </router-link>
        </ul>
      </div>
      <div class="user">  
      </div>
    </div> -->

    <v-header></v-header>

    <div class="content">
      <div class="aside bj">
        <ul id="aside">
          <li class="other"><p>系统管理</p></li>
          <li><a href=""><router-link to="/sys/shopping"><p>店铺列表管理</p></router-link></a>
          </li>
          <li><a href=""><router-link to="/sys/Purchase"><p>采购账号管理</p></router-link></a>
          </li>
          <li><a href=""><router-link to="/sys/CompanyInformation"><p>公司信息管理</p></router-link></a>
          </li>
          <li class="other"><p>子账号管理</p></li>
          <li><a href=""><router-link to="/sys/role"><p>角色管理</p></router-link></a></li>
          <li><a href=""><router-link to="/sys/staff"><p>员工管理</p></router-link></a></li>
          <li class="other"><p>主页管理</p></li>
          <li><a href=""><router-link to="/sys/notice"><p>发布公告管理</p></router-link></a></li>
          <li><a href=""><router-link to="/sys/tool"><p>常用工具管理</p></router-link></a></li>
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

export default { 

  components:{
    'v-header':Header 
  }, 

  data () {  /*业务逻辑里面定义的数据*/
    return {
      msg: '你好vue',

    }
  }
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
    height: 450px;
    width: 180px;
    background-color: #fff;
    position:absolute;
    border: 1px solid #CCCCCC;
    top: 15px;
    left: 25px;
  }
  #aside {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
  }
  #aside li {
    flex-wrap: nowrap;
    list-style: none;
    width: 100%;
    height: 60px;
    border-bottom: 1px solid #CCCCCC;
  }
  #aside li a {
    text-decoration: none;
    color: #333333;
    display: block;
    width: 100%;
    height: 43.57px;
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