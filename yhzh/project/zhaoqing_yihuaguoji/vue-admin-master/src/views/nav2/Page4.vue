<template>
    <div  class="lccontain">
         <div class="m b" v-for="floor in floorInfon">
          <router-link :to="{ name: '给排水', params: { floor: floor.PLDefine,id:floor.SearchKey,pageCode:floor.PageCode }}">
            {{floor.SearchKey}}层
            <span class="top animate"></span>
            <span class="right animate"></span>
            <span class="bottom animate"></span>
            <span class="left animate"></span>
          </router-link>
      </div>
    </div>
</template>

<script>
import {mapState} from 'vuex'
  export default {
    data() {
      return {
         
      }
    },
    computed: {
      ...mapState([
        'floorInfon'
        ])
    },
  }
</script>
<style scoped lang="scss">
  @import '~scss_vars';
    a{display:block;}
</style>