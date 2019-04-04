<template>
  <div id="order">  
    <!-- 导航栏 -->
    <v-header></v-header>

    <div class="content">
      <div class="aside bj">
        <ul id="aside">
          <li class="other">店铺管理</li>
          <template v-if="order_role.includes('店铺维护')">
          <li>
            <a href="">
               
                <router-link to="/order/maintain">
                  <p>店铺维护</p>
                </router-link>
              
            </a>
          </li>
          </template>
          <li class="other">订单管理</li>
          <template v-if="order_role.includes('全部订单')"> 
          <li>
            <a href="">
              
                <router-link to="/order/allorders">
                  <p>全部订单</p>
                </router-link>
              
            </a>
          </li>
          </template>
          <template v-if="order_role.includes('24小时到期订单')"> 
            <li>
              <a href="">
                
                <router-link to="/order/tforder">
                  <p>24小时到期订单</p>
                </router-link>
                
              </a>
            </li>
          </template>
          <template v-if="order_role.includes('待处理订单')">
          <li>
            <a href="">
               
              <router-link to="/order/pendingorder">
                <p>待处理订单</p>
              </router-link>
              
            </a>
          </li>
          </template>
          <template v-if="order_role.includes('待收货订单')"> 
          <li>
            <a href="">
              
              <router-link to="/order/tobereceorder">
                <p>待收货订单</p>
              </router-link>
              
            </a>
          </li>
          </template>
          <template v-if="order_role.includes('运输中订单')"> 
          <li>
            <a href="">
              
              <router-link to="/order/tranorder">
                <p>运输中订单</p>
              </router-link>
              
            </a>
          </li>
          </template>
          <template v-if="order_role.includes('申请取消订单')">
          <li>
            <a href="">
               
              <router-link to="/order/cancelorder">
                <p>申请取消订单</p>
              </router-link>
              
            </a>
          </li>
          </template>
           <template v-if="order_role.includes('申请退款订单')"> 
          <li>
            <a href="">
             
              <router-link to="/order/refundorder">
                <p>申请退款订单</p>
              </router-link>
              
            </a>
          </li>
          </template>
          <template v-if="order_role.includes('耗损订单')"> 
          <li>
            <a href="">
              
              <router-link to="/order/wastageorder">
                <p>耗损订单</p>
              </router-link>
              
            </a>
          </li>
          </template>
          <template v-if="order_role.includes('已完成订单')"> 
          <li>
            <a href="">
              
              <router-link to="/order/completedorder">
                <p>已完成订单</p>
              </router-link>
              
            </a>
          </li>
          </template>
          <template v-if="order_role.includes('已取消订单')"> 
          <li>
            <a href="">
              
              <router-link to="/order/cancorder">
                <p>已取消订单</p>
              </router-link>
              
            </a>
          </li>
          </template>
          <template v-if="order_role.includes('采购退货订单')"> 
          <li>
            <a href="">
              
              <router-link to="/order/returngoodsorder">
                <p>采购退货订单</p>
              </router-link>
              
            </a>
          </li>
          </template>
          <li class="other">标签管理</li>
          <template v-if="order_role.includes('标志标签管理')"> 
          <li>
            <a href="">
              
              <router-link to="/order/label">
                <p>标志标签管理</p>
              </router-link>
              
            </a>
          </li>
          </template>
           <template v-if="order_role.includes('退货原因管理')">
          <li>
            <a href="">
              
              <router-link to="/order/returngoodsreson">
                <p>退货原因管理</p>
              </router-link>
              
            </a>
          </li>
          </template>
        </ul>
      </div>
      <div class="order-right">
        <router-view></router-view>
      </div>
      
    </div>
  </div>
</template>

<script>
import {getSession} from '../common/js/util.js'
import Header from './Header.vue';
    export default {     
      components:{
        'v-header':Header  
      },

      data () {  /*业务逻辑里面定义的数据*/
        return {
          userid:'',
          user_name:'',
          shopid:[],
          home_role:'',
          order_role:'',
          ck_role:'',
          sys_role:'',
        }
      },
      created(){
        // var that = this;
        this.userid = getSession('user_id')
        this.user_name = getSession('user_name')
        this.shopid = getSession('shop_id')

        this.home_role = getSession('home_role')
        this.order_role = getSession('order_role')
        this.ck_role = getSession('ck_role')
        this.sys_role = getSession('sys_role')
        console.log(this.userid+'--------userid')
        console.log(this.shopid+"-------shopid")
        console.log(this.home_role+'home_role主页的权限')
        console.log(this.order_role+'order_role订单的权限')
        console.log(this.ck_role+'ck_role仓库的权限')
        console.log(this.sys_role+'sys_role系统的权限')
        

      },

      methods: {
        

      },

      mounted(){
          
      } 

    }
</script>

<style scoped>

#order{
  height:100%;
}

  .head {
    width: 100%;
    height: 60px;
    background-color: #76A3A3;
  }
  .nav {
    float: left;
    margin-left: 200px;
    margin-top: 30px;
  }
  .nav ul {
    overflow: hidden;
  }
  .nav li {
    float: left;
    margin: 0 20px;
    list-style: none;
  }
  .nav li a {
    color: #fff;
    text-decoration: none;
    font-weight: bold;
  }
  .nav li a:hover {
    color: red;
  }
  .user {
    float: right;
  }
  
  .content {
    position:relative;
    width: 100%;
    height: 1000px;
  }
  .aside {
    /* height: 680px; */
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
    /* height: 60px; */
   
    border-bottom: 1px solid #CCCCCC;
  }
  #aside li a {
    text-decoration: none;
    color: #333333;
    display: block;
    width: 100%;
    height: 39px;
   
    text-align: center;
   
  }
  #aside li p {
    padding-top: 10px;
  }
  .other {
    height: 39px;
    font-weight: bold;
    background-color: #F2F2F2;
  }
  .router-link-active {
    background-color: #76A3A3;
  }
  #aside li a :hover {

  }
  .order-right {
    width: 1710px;
    height: inherit;
    position: relative;
    top: 15px;
    left: 220px; 
  }

</style>