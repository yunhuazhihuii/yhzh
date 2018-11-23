<template>
    <!-- 所有的内容要被根节点包含起来 -->
    
      
        <div class="user">  

            
      
            <div class="left">  
                <el-row class="tac" >
  
              <el-col :span="24" >
                <h1 class="title"  >凯华国际中心 </h1>  
                <h2>主目录</h2>
                <el-menu
                    default-active="2"
                    class="el-menu-vertical-demo"
                    @open="handleOpen"
                    @close="handleClose"
                    background-color="#9900FF"
                    text-color="#fff"
                    active-text-color="#ffd04b">

                    <el-submenu index="1">
                        <template slot="title">
                          <span>空调系统</span>
                        </template>

                        <el-menu-item-group>
                          <template slot="title"><router-link to="/home/acDatatable">空调机组参数</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/aclist">风机盘管</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/lobby">大堂</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/podiumbuilding">裙楼层</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/aclist">设备层</router-link></template>
                        </el-menu-item-group>

                    </el-submenu>

                    <el-submenu index="2">
                    
                        <template slot="title">
                          <span>送排风系统</span>
                        </template>

                        <el-menu-item-group>
                            <template slot="title"><router-link to="/home/sendfan">排风机</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/floor">地下层</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/byl">排油烟风机状态</router-link></template>
                        </el-menu-item-group>
                      
                    </el-submenu>

                    <el-submenu index="3">
                    
                        <template slot="title">
                          <span>给排水系统</span>
                        </template>

                        <el-menu-item-group>
                            <template slot="title"><router-link to="/home/floorwater">地下层</router-link></template>
                        </el-menu-item-group>

                        
                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/equipment">设备层</router-link></template>
                        </el-menu-item-group>

                        
                        
                    </el-submenu>

                    <el-submenu index="4">
                    
                        <template slot="title">
                          <span>照明系统</span>
                        </template>

                        <el-menu-item-group>
                              <template slot="title"><router-link to="/home/Genlight"> 普通照明</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                                <template slot="title"><router-link to="/home/emergencylight"> 应急照明</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/Toppanel"> 天面</router-link></template>
                        </el-menu-item-group>
                        
                    </el-submenu>


                    <el-submenu index="5">
                    
                        <template slot="title">
                          <span>负1层供冷系统</span>
                        </template>

                        <el-menu-item-group>
                            <template slot="title"><router-link to="/home/cold"> 冷冻泵</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/butterflybamper"> 板换蝶阀</router-link></template>
                        </el-menu-item-group>

                        <!-- <el-menu-item-group >
                            <template slot="title"><router-link to="/home/cold"> 负1层供冷系统</router-link></template>
                        </el-menu-item-group> -->
                        
                    </el-submenu>


                    <el-submenu index="6">
                    
                        <template slot="title">
                          <span>37层板换系统</span>
                        </template>

                        <el-menu-item-group>
                            <template slot="title"><router-link to="/home/butterflybamp"> 冷冻泵</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/coldpate"> 板换蝶阀</router-link></template>
                        </el-menu-item-group>

                        
                        
                    </el-submenu>


                    <el-submenu index="7">
                    
                        <template slot="title">
                          <span>其它</span>
                        </template>

                        <el-menu-item-group>
                            <template slot="title"><router-link to="/home/flood">溢水报警</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/othermain"> 热水、盘管</router-link></template>
                        </el-menu-item-group>

                        <el-menu-item-group >
                            <template slot="title"><router-link to="/home/othermain"> </router-link></template>
                        </el-menu-item-group>
                        
                    </el-submenu>

                    </el-menu>
                    <h2>主界面</h2>

                    <div class="inner"><img src="../assets/mainlogo.jpg"></div>
                </el-col>
            </el-row>  

                

            </div> 

            <div class="right">   

                <v-userheader></v-userheader>

                <router-view></router-view>

            </div>
  
        </div>
       
  
</template>


<script>
import Userheader from './Userheader.vue';

export default{
    data() {
        return {
            
        }
    },

    components:{
            'v-userheader':Userheader
    },

    methods: {
      onSubmit() {
        console.log('submit!');
      },
       handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    },

    mounted(){

            
    
    }
}
   
       

</script>



<style >
.user{
    display:flex;
    background:url(../assets/bgimg.jpg) no-repeat;
    background-size:100% 100%;  
}


.left{

    width:250px;

    min-height:740px;

    text-align:center;

    line-height:1.2;

    background-color: #9900FF;

   

}

.el-menu {
    border-right: solid 0px #e6e6e6;
}


.el-submenu__title,.el-menu-item {
    font-size: 20px;}

.el-menu-item-group__title {
    padding: 7px 0 7px 20px;
    line-height: normal;
    font-size: 18px;
    color: #909399;
}

.right{
     flex:1;
     width:82%;

}


ul{
   width:100%;
}

li{
    width:100%;

    line-height:2.2;

    font-size:20px;


}

.inner{
    width:85%;
    margin: 0 auto;
}

img{
    width:100%;
   

}



</style>
