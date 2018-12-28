<template>

    <div class="user" >  

        <div class="left" style="height: 500px; border: 1px solid #eee">  

            <el-row class="tac" >
  
                <el-col :span="24" >

                    <h1 class="title"  >凯华国际中心 </h1>  
                    <h2>主目录</h2>

                    <el-menu
                        default-active="2"
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose"
                        background-color="#00BFFF"
                        :default-openeds="openeds"
                        :unique-opened="uniqueOpened"
                        text-color="#fff"
                        active-text-color="#ffd04b">

                        <el-submenu index="1" class="group">
                            <template slot="title">
                              <span class="group">空调系统</span>
                            </template>

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/ACAirDatatable">空调机组</router-link></template>
                                </el-menu-item-group>

                                <el-menu-item-group class="group">
                                  <template slot="title"><router-link to="/home/acDatatable"> 新风机组</router-link></template>
                                </el-menu-item-group>


                                <!-- <el-menu-item-group>
                                    <template slot="title">裙楼层</template>
                                    <el-menu-item index="1-1">空调机组</el-menu-item>
                                    <el-menu-item index="1-2">新风机组</el-menu-item>
                                    <el-menu-item index="1-2">商铺水阀</el-menu-item>
                                </el-menu-item-group> -->

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/aclist">商铺水阀  </router-link></template>
                                </el-menu-item-group>


                                <!-- <el-menu-item-group class="group" >
                                    <template slot="title"><router-link to="/home/acfloor">地下室</router-link></template>
                                </el-menu-item-group>
 -->
                        </el-submenu>


                        <el-submenu index="2" class="group">
                        
                            <template slot="title">
                              <span>送排风系统</span>
                            </template>

                                <el-menu-item-group  class="group">
                                    <template slot="title"><router-link to="/home/sendfan">男女卫排风机</router-link></template>
                                </el-menu-item-group>

                                <el-menu-item-group  class="group">
                                    <template slot="title"><router-link to="/home/Csendfan">茶水间排风机</router-link></template>
                                </el-menu-item-group>

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/byl">排油烟风机状态</router-link></template>
                                </el-menu-item-group>
                          
                        </el-submenu>


                        <el-submenu index="3" class="group">
                        
                            <template slot="title">
                              <span>给排水系统</span>
                            </template>

                            <el-menu-item-group class="group">
                                <template slot="title"><router-link to="/home/floorwater">地下层</router-link></template>
                            </el-menu-item-group>

                            <el-menu-item-group class="group">
                                <template slot="title"><router-link to="/home/equipment">设备层</router-link></template>
                            </el-menu-item-group>

                        </el-submenu>


                        <el-submenu index="4" class="group">
                        
                            <template slot="title">
                                <span>照明系统</span>
                            </template>

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/Genlight"> 普通照明</router-link></template>
                                </el-menu-item-group>

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/emergencylight"> 应急照明</router-link></template>
                                </el-menu-item-group>

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/Toppanel"> 天面/首层商铺</router-link></template>
                                </el-menu-item-group>
                            
                        </el-submenu>


                        <el-submenu index="5" class="group">
                        
                            <template slot="title" class="group">
                              <span>负1层供冷系统</span>
                            </template>

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/cold"> 冷冻泵</router-link></template>
                                </el-menu-item-group>

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/butterflybamper"> 板换蝶阀</router-link></template>
                                </el-menu-item-group>

                        </el-submenu>


                        <el-submenu index="6" class="group">
                        
                            <template slot="title">
                              <span>37层板换系统</span>
                            </template>

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/coldpate"> 冷冻泵</router-link></template>
                                </el-menu-item-group>

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/butterflybamp"> 板换蝶阀</router-link></template>
                                </el-menu-item-group>

                        </el-submenu>


                        <el-submenu index="7" class="group">
                        
                            <template slot="title">
                              <span>其它</span>
                            </template>

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/flood">溢水报警</router-link></template>
                                </el-menu-item-group>

                                <el-menu-item-group class="group">
                                    <template slot="title"><router-link to="/home/othermain"> 热水、盘管</router-link></template>
                                </el-menu-item-group>

                        </el-submenu>

                    </el-menu>

                    <h2 class="mainjiemian">主界面</h2>

                    <div class="inner">
                            <img src="../assets/mainlogo.jpg">
                            <h5 class="gs">Guangzhou YUNHUA 2018</h5>
                     </div>

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
            
            //收起侧边栏目 
             openeds:['1'],
             uniqueOpened:true 
        }
    },

    components:{
            'v-userheader':Userheader
    },

    methods: {
        onSubmit() {
            // console.log('submit!');
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

    min-height:960px;

    text-align:center;

    line-height:1.2;

    background-color: #00BFFF;

    border-radius: 0px 20px 20px 0px;

   

}


.title {
    font-family: SimHei;
    font-weight: bold;
}
.title1 {
    color: red;
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

.el-submenu__title {
    height: 43px;
    line-height: 43px;
    position: relative;
    -webkit-box-sizing: border-box;
    white-space: nowrap;
    list-style: none;
    border-bottom: 1px solid black;
    width: 80%;
    margin: 0 auto;
}

.right{
     flex:1;
     width:82%;

}




.group{
    width:100%;

    line-height:2.2;

    font-size:20px;


}
.mainjiemian{
    position: relative;
    top: -10px;
}

.inner{
    width:85%;
    margin: 0 auto;
    position: relative;
    top: -50px;
    left: 5px;
}

img{
    width:100%;
}

.gs{
    position: relative;
    top: -45px;
    left: -6px;
}




</style>
