<template>
	<el-header>
      <div class="nav">
        <ul>

           <template v-if="home_roles.length == 4">  
             <router-link to="/home">
              <li><a href="">首页</a></li>
            </router-link>
          </template>
          <template v-if="order_roles.length == 14">  
            <router-link to="/order/pendingorder">
              <li><a href="">交易管理</a></li>
            </router-link>
           </template>
          <template v-if="ck_roles.length == 5">  
            <router-link to="/storehouse/check">
              <li><a href="">仓库管理</a></li>
            </router-link>
           </template>
          <template v-if="sys_roles.length == 7">  
            <router-link to="/sys/shopping">
              <li><a href="">系统管理</a></li>
            </router-link>
           </template>
          
        </ul>
      </div>

      <div class="user">
        <el-col class="userinfo" style="width: 850px">
            <el-dropdown trigger="hover">
              <span class="el-dropdown-link userinfo-inner"><img src="../assets/images/user1.png" /> {{user_name}}
              </span>
              <el-dropdown-menu slot="dropdown">
                <!-- <el-dropdown-item>我的消息</el-dropdown-item>
                <el-dropdown-item>设置</el-dropdown-item> -->
                <el-dropdown-item  @click.native="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>

      </div>
      
    </el-header>
</template>


<script>
  import {getSession} from '../common/js/util.js'
  export default{
    data(){
      return {
          userid:'',
          user_name:'',
          shopid:[],
          home_role:'',
          home_roles:[],
          order_role:'',
          order_roles:[],
          ck_role:'',
          ck_roles:[],
          sys_role:'',
          sys_roles:[],


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
        console.log(this.userid+'--------88888')
        console.log(this.shopid+"-------99999")
        console.log(this.home_role+'主页的权限')
        console.log(this.home_role.length)
        console.log(this.order_role+'订单的权限')
        console.log(this.ck_role+'仓库的权限')
        console.log(this.sys_role+'系统的权限')
        this.quanxian()

      },
    methods: {

      logout() {
        console.log("1111111111111111")
        var _this = this;
        this.$confirm('确认退出吗?', '提示', {
          type: 'warning'
        }).then(() => {
          sessionStorage.removeItem('name');
           _this.$router.push('/login');
           
        }).catch(() => {

        });
      },
      quanxian(){
       this.home_roles = this.home_role.split(',')
       this.order_roles = this.order_role.split(',')
       this.ck_roles = this.ck_role.split(',')
       this.sys_roles = this.sys_role.split(',')
        console.log(this.home_roles instanceof Array);
        console.log(this.home_roles.length)

      }

    }

  }

</script>



<style type="text/css">
	

.el-header{
   background-color: #76A3A3;
    color: #333;
    text-align: center;
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

.user .userinfo {
    float: right;
}

.user .userinfo .userinfo-inner{
    cursor: pointer;
    color: #fff;
}

.user .userinfo img{
    width: 40px;
    height: 40px;
    border-radius: 20px;
    margin: 10px 0px 10px 10px;
    float: right;
}

</style>
