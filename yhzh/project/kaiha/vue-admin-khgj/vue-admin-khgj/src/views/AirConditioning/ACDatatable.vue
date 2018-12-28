<template>    
    <div id="acdata" class="acgroup"> 
      
      <!-- <div class="class">
        <ul class="qunlouclass">
          <li><router-link to='/home/podiumbuilding/devtab'>办公楼层</router-link></li>
          <li><router-link to='/home/podiumbuilding/waterfa'>裙楼层</router-link></li>
          <li><router-link to='/home/podiumbuilding/waterfa'>地下室</router-link></li>
           <li><router-link to='/home/podiumbuilding/waterfa'>33f</router-link></li>
        </ul> 
        
      </div>

      <router-view></router-view>

    </div> -->


      <!-- <div class="class">
      <ul class="qunlouclass">
       
        <li><router-link to='/home/podiumbuilding/waterfa'>裙楼层</router-link></li>
        <li><router-link to='/home/ACAirDatatable'>避难层</router-link></li>
         
      </ul> 
    </div> -->
    
    <newfan :devtypeid="devtypeid" :tabTitle="tabTitle" :floorScopeList="floorScopeList"></newfan>
        
 </div>
 

</template>

<script> 

import newfan from '../../components/newfan.vue';

export default{
  components:{newfan},
  data(){
    return { 

       devtypeid: 1, //设备属性编号
       floorScopeList: [{floor1: "裙楼层1",floor2: "6"},
                        {floor1: "21"},
                        {floor1: "37"},
                        {floor1: "天面"}], //楼层范围
       tabTitle: "裙楼层、避难层空调机组参数" ,//表格标题 
  
    }
  },

  created(){
    
  },

  methods:{

    //双击table表格行跳转区域   push：name: 'Butterflybamp',：对应router的name: 'Butterflybamp',
    // todetail(){
    //   this.$router.push({ name: 'ACDatatable_detail'})
    // },
   
  }
}


    
</script>

<style scoped>

.acgroup{
  /*margin-top: -36px;*/
}



.coldtable,.elpagination{
    margin:0 auto;
}


.el-table__body-wrapper {
   
    color: red;
}

.el-table th {
    padding: 5px 0 !important;
}


</style>