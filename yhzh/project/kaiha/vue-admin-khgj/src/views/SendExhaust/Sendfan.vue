<template>    
    <div id="acdata" class="acgroup">  
      <!-- <h2>空调机组参数</h2>  -->
        
      <devtab :devtypeid="devtypeid" :tabTitle="tabTitle" :floorScopeList="floorScopeList"></devtab>

    </div>
</template>


<script> 
import devtab from '../../components/dev_tab.vue';
export default{
  components:{devtab},
  data(){
    return {  
       devtypeid: 110, //设备属性编号
       floorScopeList: [
                        {floor1: "1",floor2: "17"},
                        {floor1: "18",floor2: "29"},
                        {floor1: "30",floor2: "41"},
                        {floor1: "42",floor2: "53"}], //楼层范围
       tabTitle: "男女卫送排风机组参数" //表格标题
    }
  },

  created(){
    
  },

  methods:{

      
  }
}
    
</script>

<style scoped>

.acgroup{
 
}

</style>