<template>
    <!-- 包裹 -->
    <div id="packagelist"> 
      <!-- Tree 树形控件 -->
      <!-- <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick">
       
      </el-tree> -->

      <el-col :span="12">
        
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose">

          <el-submenu index="1">
            <template slot="title">
              <!-- <i class="el-icon-arrow-down"></i> -->
              <span>虚拟可能发生的包裹情况</span>
            </template>

            <el-menu-item-group title="一个订单一包裹">

              <el-menu-item index="1-1">
                <router-link to="/storehouse/check/packagenewone">
                  一订单对应一包裹
                </router-link>
              </el-menu-item>

            </el-menu-item-group>

            <el-menu-item-group title="一个订单多个包裹 ">

              <el-menu-item index="2-1">
                <router-link to="/storehouse/check/packagenewtwo">
                  扫描第一个包裹
                </router-link>
              </el-menu-item>

              <el-menu-item index="2-2">
                <router-link to="/storehouse/check/packagenewthree">
                  扫描第二个包裹
                </router-link>
              </el-menu-item>

              <el-menu-item index="2-3">
                <router-link to="/storehouse/check/packagenewfour">
                  扫描第三个包裹
                </router-link>
              </el-menu-item>
            </el-menu-item-group>

            <el-menu-item-group title="多个订单">

              <el-menu-item index="3-1">
                <router-link to="/storehouse/check/packagenewfive">
                  多订单对应一包裹
                </router-link>
              </el-menu-item>

              <el-menu-item index="3-2">
                <router-link to="/storehouse/check/packagenewsix">
                  多订单对应多包裹
                </router-link>
              </el-menu-item>
            </el-menu-item-group>

          </el-submenu>

        </el-menu>
      </el-col>

    
    </div>

</template>

<script>

  export default {
    data() {
      return {

       

      };
    },
    methods: {
      
      //handleNodeClick 默认有一个参数
      handleNodeClick(obj){
         console.log(obj);//打印出来看哪个值对你有用
         this.$router.push({path:obj});
      },

      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }

    }


  };

</script>

<style scoped>

#packagelist{
  width: 200px;
  position: absolute;
  top: 320px;
  left: 5px;
}

.el-submenu .el-menu-item {
    height: 20px; 
    line-height: 20px; 
    padding: 0 5px;
    min-width: 200px;
    padding-right: 5px;
}

.el-menu {
    border-right: 0px;
    list-style: none;
    position: relative;
    margin: 0;
    padding-left: 0;
}

.el-submenu__title {
    font-size: 14px;
    color: #303133;
    padding: 0 5px !important;
    cursor: pointer; 
}

.el-menu-item-group__title {
    padding: 0px 0 0px 10px;
    line-height: normal;
    font-size: 12px;
    color: #909399;
}

.el-submenu{
  width: 210px;
}

.el-submenu__title {
   height: 20px !important; 
   line-height: 20px !important; 
    position: relative;
    -webkit-box-sizing: border-box;
    white-space: nowrap;
    list-style: none;
}

</style>