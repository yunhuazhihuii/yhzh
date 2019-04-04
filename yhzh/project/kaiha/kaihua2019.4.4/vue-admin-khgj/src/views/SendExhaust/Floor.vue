<template>    
    <div id="Fantable">   
        <h2> 地下层 </h2> 

        <el-table
            class="coldtable"
            :data="tableData"
            border
            style="width:61%">
       
            <el-table-column
              prop="eqNO"
              label="设备编号"
              align="center"
              width="180">
            </el-table-column>

            <el-table-column
              prop="location"
              label="位置"
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

            <el-table-column
              prop="alarm"
              label="故障报警"
              align="center"
              width="100">
            </el-table-column>
           
            

        
        </el-table>
        

    </div>

</template>


<script> 



export default{
	data() {
		return {

      tableData: [
                      {
                        eqNO:'18楼男卫',
                        location:'供西北面车场新风末端在27号车位',
                        status:'运行',
                        handauto:'自动',
                        baginstopcontroller:'开启',
                        alarm:'正常'
                      }, 
                      {
                        eqNO:'18楼男卫',
                        location:'供西北面车场新风末端在27号车位',
                        status:'运行',
                        handauto:'自动',
                        baginstopcontroller:'开启',
                        alarm:'正常'  
                      }, 
                      {
                        eqNO:'18楼男卫',
                        location:'供西北面车场新风末端在27号车位',
                        status:'运行',
                        handauto:'自动',
                        baginstopcontroller:'开启',
                        alarm:'正常'
                      }, 
                      {
                        eqNO:'18楼男卫',
                        location:'供西北面车场新风末端在27号车位',
                        status:'运行',
                        handauto:'自动',
                        baginstopcontroller:'开启',
                        alarm:'正常'
                      }
                  ] 
	        
    }

	},
	
	 methods: {
      onSubmit() {
        console.log('submit!');
      }
    },

    mounted(){

              //获取get传值

              //this.$route.query ： 获取动态路由的值
              console.log(this.$route.params);  /*获取动态路由传值*/
        }
}


    
</script>

<style scoped>

.coldtable{
    margin:0 auto;
    margin-top: 20px;
}



</style>