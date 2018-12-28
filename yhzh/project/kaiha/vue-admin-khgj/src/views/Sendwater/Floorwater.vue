<template>    
    <div id="podiumbuilding">   
        <h2> 地下层B1-B4
        </h2>

       <div class="podiumbuilding" style="width:50%">
        <h2> 生活水泵</h2> 
        <el-table
            class="podium"
            :data="pumntableData"
            border
            style="width:62%">

            <el-table-column
              prop="NO"
              label="设备编号"
              align="center"
              width="180">
            </el-table-column>
           
            <el-table-column
              prop="status"
              label="运行状态"
              align="center"
              width="100">
            </el-table-column>

            <el-table-column
              prop="alarm"
              label="故障报警"
              align="center"
              width="100">
            </el-table-column>


        </el-table>

      </div>

      <div class="daypodiumbuilding" style="width:40%">
        <h2> 生活水池 </h2> 
        <el-table
            class="podium"
            :data="tableData"
            border
            style="width:62%">

            <el-table-column
              prop="NO"
              label="编号"
              align="center"
              width="150">
            </el-table-column>
            <el-table-column
              prop="hwater"
              label="高水位"
              align="center"
              width="80">
            </el-table-column>
            
            <el-table-column
              prop="lwater"
              label="低水位"
              align="center"
              width="80">
            </el-table-column>


        </el-table>

      </div>

    </div>

</template>


<script> 
// import Datatable from './Datatable.vue';


export default{
  data() {
    return {
      pumntableData:[
        {
          NO:'1#生活水泵',
          status:'运行',
          alarm:"正常"
        },
        {
          NO:'1#生活水泵',
          status:'运行',
          alarm:"正常"
        },
        {
          NO:'1#生活水泵',
          status:'运行',
          alarm:"正常"
        },
        {
          NO:'1#生活水泵',
          status:'运行',
          alarm:"正常"
        }

      ],

      tableData:[
        {
          NO:'1#生活水池',
          hwater:'正常',
          lwater:'正常'
        },
        {
          NO:'1#生活水池',
          hwater:'正常',
          lwater:'正常'
        },
        {
          NO:'1#生活水池',
          hwater:'正常',
          lwater:'正常'
        },
        {
          NO:'1#生活水池',
          hwater:'正常',
          lwater:'正常'
        }



      ]
          
    }

  },
  // components:{
 //            'v-Datatable':Datatable
 //  },
  methods: {
      onSubmit() {
        console.log('submit!');
      }
    },

  mounted(){
    //this.$route.query ： 获取动态路由的值
    console.log(this.$route.params);  /*获取动态路由传值*/
  }
}

 
</script>

<style scoped>

.podiumbuilding{
    margin:0 10px 10px 40px;
    float:left;
}

.podium{
  margin:0 auto;
}


.daypodiumbuilding{
    margin:0 40px 10px 10px;
    float:right;
}


</style>