<template>

    <div id=""> 
      <!-- <div class="class">
        <ul class="qunlouclass">
       
          <li><router-link to='/home/podiumbuilding/waterfa'>裙楼层</router-link></li>
          
        </ul> 
        
      </div> -->

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