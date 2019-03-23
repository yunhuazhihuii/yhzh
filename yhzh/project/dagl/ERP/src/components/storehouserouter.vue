<template>
  <div>  
    <!-- 导航栏 -->
    <v-header></v-header>

    <div class="content">

      <!-- 左侧边栏目录 -->
      <v-storehouseSide> </v-storehouseSide>

      <!-- 右边内容 -->
      <el-main>
          <router-view></router-view>
      </el-main>

    </div>
  </div>
</template>

<script>
import Header from './Header.vue';
import storehouseSide from './storehouseSide.vue';
    export default {     
      components:{
        'v-header':Header,
        'v-storehouseSide':storehouseSide   
      },

      data () {  /*业务逻辑里面定义的数据*/
        return {
          msg: '你好vue',

        }
      }
}
</script>

<style scoped>

</style>
