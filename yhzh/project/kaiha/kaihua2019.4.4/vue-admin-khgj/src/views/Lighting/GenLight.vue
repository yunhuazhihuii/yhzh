<template>    
    <div id="news" class="acgroup">  
      <!-- <h2>空调机组参数</h2>  -->

        
      <devdtab :devtypeid="devtypeid" :tabTitle="tabTitle" :floorScopeList="floorScopeList" :pageCount="pageCount"></devdtab>

    </div>
</template>


<script> 
import devdtab from '../../components/dev_dtab.vue';
export default{
  components:{devdtab},
  data(){
    return {  
       devtypeid: 112, //设备属性编号
       pageCount: 25, //表格数据行数
       floorScopeList: [{floor1: "-4",floor2: "-4"},
                        {floor1: "-3",floor2: "-3"},
                        {floor1: "-2",floor2: "-2"},
                        {floor1: "-1",floor2: "-1"},
                        {floor1: "1",floor2: "6"},
                        {floor1: "7",floor2: "14"},
                        {floor1: "15",floor2: "23"},
                        {floor1: "24",floor2: "31"},
                        {floor1: "32",floor2: "39"},
                        {floor1: "40",floor2: "46"},
                        {floor1: "47",floor2: "53"}], //楼层范围
       tabTitle: "普通照明" //表格标题
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
