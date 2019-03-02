<template>
  
<el-aside width="180px" style="margin-left:10px;margin-top:20px;border:1px solid #aaa;float:left;">
  <div>
    <div class="parent">

      <h2 style="background-color:#aaa"> 收货管理</h2>
      
      <ul>
        <li><router-link to="/storehouse/tocollected">待收货订单( 10 )</router-link></li>
        <li><router-link to="/storehouse/check">拆件核对包裹 ( 15) </router-link></li>
        <li><router-link to="/storehouse/Alreadyprinted">已打印订单( 16) </router-link></li> 
        <li><router-link to="/storehouse/Intransportation">运输中订单(14 ) </router-link></li> 
        <li><router-link to="/storehouse/Returngoods">采购退货订单(12 ) </router-link></li> 
      </ul>
    </div>

  </div>
        
  </el-aside>


</template>

<script>
  export default{
    data(){
      return{
        
      }
    }
  }

</script>


<style scoped>


.router-link-active {
    background-color: #76A3A3;
}


.parent li a {
    text-decoration: none;
    color: #333333;
    display: block;
    width: 100%;
    height: 30px;
    text-align: center;
  }



.parent li{
  border-bottom: 1px solid #aaa;
}


</style>
