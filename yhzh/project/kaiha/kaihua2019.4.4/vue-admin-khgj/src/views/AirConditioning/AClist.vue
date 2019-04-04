<template>

    <div id=""> 
     
      <waterfa :devtypeid="devtypeid" :tabTitle="tabTitle" :floorScopeList="floorScopeList"></waterfa>

         
    </div>
</template>


<script>

import waterfa from '../../components/waterfa.vue';

    export default{

      components:{waterfa},
        data(){
            return { 
              devtypeid: 1, //设备属性编号
              floorScopeList: [
                                {floor1: "1",floor2: "6"}
                               ], //楼层范围
              tabTitle: "裙楼层商铺空调水阀" ,//表格标题     
    }
  },

  methods:{

      goNews(){
          this.$router.push({ name: 'Conditionertable'})

      }
  }
}

</script>

<style scoped>

.coldtable{
    margin:0 auto;
}

ul li{
  margin-left:20px;
}




</style>