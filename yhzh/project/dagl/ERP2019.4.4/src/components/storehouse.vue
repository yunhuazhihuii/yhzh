<template>
  <div>  
    <!--头部 导航栏 -->
    <v-header></v-header>

    <div class="content">

      <!-- 左侧边栏目录 -->
      <el-aside width="180px" class="bj" style="margin-left:30px;margin-top:20px;border:1px solid #aaa;float:left;">
      <div>
        <div class="parent">

          <p>收货管理</p>
          
          <ul>
            <li><router-link to="/storehouse/tocollected">待收货订单</router-link></li>
            <li><router-link to="/storehouse/check">拆件核对包裹  </router-link></li>
            <li><router-link to="/storehouse/Alreadyprinted">已打印订单 </router-link></li> 
            <li><router-link to="/storehouse/Intransportation">运输中订单 </router-link></li> 
            <li><router-link to="/storehouse/Returngoods">采购退货订单 </router-link></li> 
          </ul>
        </div>

      </div>
            
      </el-aside>

      <!-- 右边内容 -->
      <div style="float:left;margin-left:10px;margin-top:13px;margin-bottom:30px;">
       
        <router-view></router-view>

      </div>

    </div>
   

  </div>
</template>

<script>
 // import '../assets/css/storehouse.css';
 import Header from './Header.vue';
   export default {   
      components:{
        'v-header':Header,
      },

      data () {  /*业务逻辑里面定义的数据*/
        return {
          
          select: '',
          search: '',
          currentPage1: 10,

          tableData3: [
              {
                
              }],
              multipleSelection: []

        }
      },

      methods: {

      }

    }
</script>


<style scoped>
.content{
  
}

.router-link-active {
    background-color: #76A3A3;
}
.parent p {
  height: 35px;
  background-color: #F2F2F2;
  font-size: 18px;
  font-weight: bold;
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
