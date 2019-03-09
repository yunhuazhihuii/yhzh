<template>
	<el-header>
      <div class="nav">
        <ul>
          <router-link to="/home">
            <li><a href="">首页</a></li>
          </router-link>
          <router-link to="/order/pendingorder">
            <li><a href="">交易管理</a></li>
          </router-link>
          <router-link to="/storehouse/check">
            <li><a href="">仓库管理</a></li>
          </router-link>
          <router-link to="/sys/shopping">
            <li><a href="">系统管理</a></li>
          </router-link>
          
        </ul>
      </div>

      <div class="user">
        <el-col class="userinfo" style="width: 850px">
            <el-dropdown trigger="hover">
              <span class="el-dropdown-link userinfo-inner"><img src="../assets/images/user1.png" /> {{name}}
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
          name:''
      }
    },
    created(){
        // var that = this;
        this.name = getSession('name')
        console.log(this.name+'--------88888')

      },
    methods: {
        logout: function () {
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