
<template>
	<el-aside width="200px" style="margin-left:30px;margin-top:20px;border:1px solid #aaa;height: 400px;" >
        <div>
          <div class="parent">
            <h2 style="background-color:#aaa"> 系统管理</h2>
            <ul>
              <li><router-link to="/sys/shopping">店铺列表管理</router-link></li>
              <li><router-link to="/sys/Purchase">采购账号管理</router-link></li>
              <li><router-link to="/sys/CompanyInformation">公司信息管理</router-link></li> 
            </ul>
          </div>

          <div class="child">
            <h2 style="background-color:#aaa"> 子账号管理</h2>
            <ul>
              <li><router-link to="/sys/role">角色管理</router-link></li>
              <li><router-link to="/sys/staff">员工管理</router-link></li>
            </ul>
          </div>

          <div class="main">
            <router-link to="/sys/mainsys"><h2 style="background-color:#aaa">主页管理</h2></router-link>
            
          </div>
          
        </div>
        
      </el-aside>
</template>

<style type="text/css" scoped>

  .parent ul li,.child ul li,.main ul li{
    
    margin-top:10px;
  }

  .parent li a,.child li a,.main a{
    text-decoration: none;
    color: #333333;
    display: block;
    width: 100%;
    height: 30px;
    text-align: center;
    margin-top: 8px;
  }

.main h2{
  text-align: left;
}

	
</style>