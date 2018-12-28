<template>    
    <div id="news">   
        <h2>   冷冻泵参数 </h2> 

        <el-table
            class="coldtable"
            :data="tableData"
            border
            style="width: 730px">
       
            <el-table-column
              prop="NO"
              label="设备编号"
              align="center"
              width="150">
            </el-table-column>
            <el-table-column
              prop="stastpotroller"
              label="启停控制"
              align="center"
              width="80">
            </el-table-column>
            <el-table-column
              prop="runstatus"
              label="运行信号"
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
              prop="alarm"
              label="故障报警"
              align="center"
              width="80">
            </el-table-column>
            
            <el-table-column
              prop="Hzadjust"
              label="频率调节"
              align="center"
              width="80">
            </el-table-column>

            <el-table-column
              prop="hzfeedback"
              label="频率反馈"
              align="center"
              width="80">
            </el-table-column>
            
            <el-table-column
              prop="addrun"
              label="累计运行"
              align="center"
              width="80">
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
            NO:'CHWP-B1F-01',
            stastpotroller:'开启',
            handauto:'自动',
            runstatus:'运行',
            alarm:"正常",
            Hzadjust:"50",
            hzfeedback:"49.97",
            addrun:"789.5"
          }, 
          {
            NO:'CHWP-B1F-01',
            stastpotroller:'开启',
            handauto:'自动',
            runstatus:'运行',
            alarm:"正常",
            Hzadjust:"50",
            hzfeedback:"49.97",
            addrun:"789.5" 
          }, 
          {
            NO:'CHWP-B1F-01',
            stastpotroller:'开启',
            handauto:'自动',
            runstatus:'运行',
            alarm:"正常",
            Hzadjust:"50",
            hzfeedback:"49.97",
            addrun:"789.5"
          },
          {
            NO:'CHWP-B1F-01',
            stastpotroller:'开启',
            handauto:'自动',
            runstatus:'运行',
            alarm:"正常",
            Hzadjust:"50",
            hzfeedback:"49.97",
            addrun:"789.5"
          },
          {
            NO:'CHWP-B1F-01',
            stastpotroller:'开启',
            handauto:'自动',
            runstatus:'运行',
            alarm:"正常",
            Hzadjust:"50",
            hzfeedback:"49.97",
            addrun:"789.5"
          }, 
          {
            NO:'CHWP-B1F-01',
            stastpotroller:'开启',
            handauto:'自动',
            runstatus:'运行',
            alarm:"正常",
            Hzadjust:"50",
            hzfeedback:"49.97",
            addrun:"789.5"
          }, 
          {
            NO:'CHWP-B1F-01',
            stastpotroller:'开启',
            handauto:'自动',
            runstatus:'运行',
            alarm:"正常",
            Hzadjust:"50",
            hzfeedback:"49.97",
            addrun:"789.5"
          }
      ] 
        }

    },
     methods: {
      onSubmit() {
        console.log('submit!');
      }
    } 
}


    
</script>

<style>
.coldtable{
    margin:0 auto;
}



</style>