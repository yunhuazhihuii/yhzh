<template>    
    <div id="news">   
        <h2>板换二次蝶阀  </h2> 

        <el-table
          class="coldtable"
          :data="tableData"
          border
          style="width: 600px">
       
          <el-table-column
            prop="NO"
            label="设备编号"
            color='red'
            align="center"
            width="100">
          </el-table-column>
          <el-table-column
            prop="openlocaltion"
            label="开到位信号"
            align="center"
            width="120">
          </el-table-column>
          <el-table-column
            prop="closelocaltion"
            label="关到位信号"
            align="center"
            width="120">
          </el-table-column>
          <el-table-column
            prop="handauto"
            label="手/自动"
            align="center"
            width="80">
          </el-table-column>
          <el-table-column
            prop="opencontroller"
            label="开控制"
            align="center"
            width="80">
          </el-table-column>
          
          <el-table-column
            prop="closecontroller"
            label="关控制"
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
            NO:'01#蝶阀',
            openlocaltion:'运行',
            closelocaltion:'停止', 
            handauto:'自动',
            opencontroller:'开启',
            closecontroller:'关闭'
          }, 
          {
            NO:'01#蝶阀',
            openlocaltion:'运行',
            closelocaltion:'停止', 
            handauto:'自动',
            opencontroller:'开启',
            closecontroller:'关闭'  
          }, 
          {
            NO:'01#蝶阀',
            openlocaltion:'运行',
            closelocaltion:'停止', 
            handauto:'自动',
            opencontroller:'开启',
            closecontroller:'关闭'
          },
          {
            NO:'01#蝶阀',
            openlocaltion:'运行',
            closelocaltion:'停止', 
            handauto:'自动',
            opencontroller:'开启',
            closecontroller:'关闭'
          },
          {
            NO:'01#蝶阀',
            openlocaltion:'运行',
            closelocaltion:'停止', 
            handauto:'自动',
            opencontroller:'开启',
            closecontroller:'关闭' 
          }, 
          {
            NO:'01#蝶阀',
            openlocaltion:'运行',
            closelocaltion:'停止', 
            handauto:'自动',
            opencontroller:'开启',
            closecontroller:'关闭' 
          }, 
          {
            NO:'01#蝶阀',
            openlocaltion:'运行',
            closelocaltion:'停止', 
            handauto:'自动',
            opencontroller:'开启',
            closecontroller:'关闭'
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