<template>    
    <div id="">  
      <h2>空调机组参数</h2> 
        
        <el-table
            class="coldtable"
            :data="tableData"
            border
            style="width:80%">
       
            <el-table-column
              prop="eqNO"
              align="center"
              label="设备编号"
              width="150">
            </el-table-column>

            <el-table-column
              prop="name"
              align="center"
              label="设备名称"
              width="150">
            </el-table-column>

            <el-table-column
              prop="sendfantempersure"
              align="center"
              label="送风温度"
              width="80">
            </el-table-column>
            
            <el-table-column
              prop="tempersureset"
              label="送风温度设定"
              align="center"
              width="110">
            </el-table-column>

            <el-table-column
              prop="benganalarm"
              label="初滤网报警"
              align="center"
              width="95">
            </el-table-column>

            <el-table-column
              prop="waterfeedback"
              label="水阀反馈"
              align="center"
              width="80">
            </el-table-column>

            <el-table-column
              prop="wateradjust"
              label="水阀调节"
              align="center"
              width="80">
            </el-table-column>

            <el-table-column
              prop="Hzadjust"
              align="center"
              label="频率调节"
              width="80">
            </el-table-column>

            <el-table-column
              prop="hzfeedback"
              label="频率反馈"
              align="center"
              width="80">
            </el-table-column>

            <el-table-column
              prop="wellpressure"
              label="管井静压"
              align="center"
              width="80">
            </el-table-column>

            <el-table-column
              prop="pressureset"
              label="静压设定"
              align="center"
              width="80">
            </el-table-column>



            <el-table-column
              prop="runstatus"
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
              prop="stastpotroller"
              label="启停控制"
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
              prop="addmodel"
              label="电加热模式"
              align="center"
              width="95">
            </el-table-column>
            <el-table-column
              prop="alarm"
              label="故障报警"
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
            eqNO: 'PAU-18F-01',
            name:'西面供14F-20F',
            sendfantempersure: '9.27',


            tempersureset: '27 ',
            benganalarm:"正常",
            waterfeedback:"100",
            wateradjust:" 100% ",

            Hzadjust:'50',
            hzfeedback:"49.97",
            wellpressure:'33.59',

            pressureset:'100',
            runstatus:'运行',
            handauto:'自动',
            
            stastpotroller:'开启',
            controllerlevel:'NB16',
            addmodel:'NB16关闭',
            alarm:'正常' 

          }, 
          {
            eqNO: 'PAU-18F-01',
            name:'西面供14F-20F',
            sendfantempersure: '9.27',

            tempersureset: '27 ',
            benganalarm:"正常",
            waterfeedback:"100",
            wateradjust:" 100% ",

            Hzadjust:'50',
            hzfeedback:"49.97",
            wellpressure:'33.59',

            pressureset:'100',
            runstatus:'运行',
            handauto:'自动',
            
            stastpotroller:'开始',
            controllerlevel:'NB16',
            addmodel:'NB16关闭',
            alarm:'正常'    
          }, 
          {
            eqNO: 'PAU-18F-01',
            name:'西面供14F-20F',
            sendfantempersure: '9.27',

            tempersureset: '27 ',
            benganalarm:"正常",
            waterfeedback:"100",
            wateradjust:" 100% ",

            Hzadjust:'50',
            hzfeedback:"49.97",
            wellpressure:'33.59',

            pressureset:'100',
            runstatus:'运行',
            handauto:'自动',
            
            stastpotroller:'开始',
            controllerlevel:'NB16',
            addmodel:'NB16关闭',
            alarm:'正常' 
          }, 
          {
            eqNO: 'PAU-18F-01',
            name:'西面供14F-20F',
            sendfantempersure: '9.27',

            tempersureset: '27 ',
            benganalarm:"正常",
            waterfeedback:"100",
            wateradjust:" 100% ",

            Hzadjust:'50',
            hzfeedback:"49.97",
            wellpressure:'33.59',

            pressureset:'100',
            runstatus:'运行',
            handauto:'自动',
            
            stastpotroller:'开始',
            controllerlevel:'NB16',
            addmodel:'NB16关闭',
            alarm:'正常' 
          }
      ] 
        
       
    }
  },

        methods:{

            
        }
    }


    
</script>

<style scoped>

.coldtable{
    margin:0 auto;
}




</style>