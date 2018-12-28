
align="center"<template>    
    <div id="ODatatable"> 

        <h2>天面LOGO/首层商铺照明</h2> 
        
        <el-table
            class="coldtable"
            :data="tableData"
            border
            style="width:650px;padding: 10px;">
       
            <el-table-column
              prop="eqNO"
              label="设备编号"
              align="center"
              width="150">
            </el-table-column>
            <el-table-column
              prop="name"
              label="设备名称"
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
              prop="handauto"
              label="手/自动"
              align="center"
              width="100">
            </el-table-column>
            <el-table-column
              prop="baginstopcontroller"
              label="启停控制"
              align="center"
              width="100">
            </el-table-column>

            
            
        </el-table>

        
 
    </div>

</template>


<script> 


export default{
  data(){
    return {  
      tableData: [
          {
            eqNO: '54-LOGO-1',
            name: '天面LOGO-1',
            status: '运行 ',
            handauto:"自动",
            baginstopcontroller:"开启"
          }, 
          {
            eqNO: '54-LOGO-1',
            name: '天面LOGO-1',
            status: '运行 ',
            handauto:"自动",
            baginstopcontroller:"开启"
          }, 
          {
            eqNO: '54-LOGO-1',
            name: '天面LOGO-1',
            status: '运行 ',
            handauto:"自动",
            baginstopcontroller:"开启"
          }, 
          {
            eqNO: '54-LOGO-1',
            name: '天面LOGO-1',
            status: '运行 ',
            handauto:"自动",
            baginstopcontroller:"开启"
          }
      ]


    }
  },

    

    methods:{

            // godatable(){
            //     this.$router.push({ name: 'ODatatable'})

            // }
        }       
}


    
</script>

<style scoped>

h2{
  margin: 20px 0px 20px 0px;
}

.coldtable{
    margin:0 auto;
}


</style>