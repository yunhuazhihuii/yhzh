<template>
  <div id="app" v-cloak>
    <router-view></router-view>
  </div>
</template>

<script>
import './assets/css/base.css'
export default {
  name: 'app',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
}
</script>

<style>

[v-cloak] {
  display: none !important;
}

#app{
	height: 100%;
  width: 1920px;
}

body .el-table th.gutter{
    display: table-cell!important;
}
 
body .el-table colgroup.gutter{
    display: table-cell!important;
}



</style>
