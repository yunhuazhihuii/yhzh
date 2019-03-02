<template>
	<el-header>
      <div class="nav">
        <ul>
          <router-link to="/home">
            <li><a href="">首页</a></li>
          </router-link>
          <router-link to="/order/allorders">
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

      </div>
      
    </el-header>
</template>


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
  .user {
    float: right;
  }


</style>