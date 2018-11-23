<template>    
    <div id="news">   
        <h1> 送排风系统 </h1> 

        <el-table
            class="fantable"
            :data="tableData"
            border
            style="width:85%;padding: 10px;">
       
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
              width="80">
            </el-table-column>
            <el-table-column
              prop="handauto"
              label="手/自动"
              align="center"
              width="80">
            </el-table-column>
            <el-table-column
              prop="baginstopcontroller"
              label="启停控制"
              align="center"
              width="80">
            </el-table-column>

            <el-table-column
              prop="alarm"
              label="故障报警"
              align="center"
              width="80">
            </el-table-column>
            <el-table-column
              prop="adjust"
              label="风阀调节"
              align="center"
              width="80">
            </el-table-column>
            <el-table-column
              prop="fanback"
              label="风阀反馈"
              align="center"
              width="80">
            </el-table-column>
            <el-table-column
              prop="back"
              label="风量反馈"
              align="center"
              width="80">
            </el-table-column>

            <el-table-column
              prop="controllerlevel"
              label="控制级别"
              align="center"
              width="80">
            </el-table-column>
            

            <el-table-column
              prop="obverse"
              label="编辑"
              align="center"
              width="80">
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
            eqNO: 'EAF-18F-01',
            name: '茶水间排风机',
            status: '运行 ',
            handauto:"自动",
            baginstopcontroller:"开启",
            alarm:" 正常 ",
            adjust:" 100% ",
            fanback:" 99%",
            back:"246.7 ",
            controllerlevel:'DN16',
            obverse:"查看"


          }, 
          {
            eqNO: 'EAF-18F-01',
            name: '茶水间排风机',
            status: '运行 ',
            handauto:"自动",
            baginstopcontroller:"开启",
            alarm:" 正常 ",
            adjust:" 100% ",
            fanback:" 99%",
            back:"246.7 ",
            controllerlevel:'DN16',
            obverse:"查看"
          }, 
          {
            eqNO: 'EAF-18F-01',
            name: '茶水间排风机',
            status: '运行 ',
            handauto:"自动",
            baginstopcontroller:"开启",
            alarm:"正常",
            adjust:" 100% ",
            fanback:" 99%",
            back:"246.7 ",
            controllerlevel:'DN16',
            obverse:"查看"
          }, 
          {
            eqNO: 'EAF-18F-01',
            name: '茶水间排风机',
            status: '运行 ',
            handauto:"自动",
            baginstopcontroller:"开启",
            alarm:"正常",
            adjust:" 100% ",
            fanback:" 99%",
            back:"246.7 ",
            controllerlevel:'DN16',
            obverse:"查看"
          }
      ] 
        
       
    }
  },

        methods:{

            
        }
    }


    
</script>

<style scoped>

.fantable{
    margin:0 auto;
   
}



</style>