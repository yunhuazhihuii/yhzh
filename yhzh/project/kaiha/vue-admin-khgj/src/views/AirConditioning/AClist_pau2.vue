<template>
    <!-- 所有的内容要被根节点包含起来 -->
    <div id=""> 
      <div class="images"><i class="el-icon-back" @click="onAClist"></i></div>

        <h2>PAU空调机2-21/37/54层</h2>

        <!-- <ul>
          <li>
           <router-link to="/home/aclist">PAU类型</router-link>
           <router-link to="/home/aclist">AHU类型</router-link>
           
          </li>
        </ul> -->

        <el-table
            class="coldtable"
            :data="tableData"
            @row-dblclick="AClist_pau2_datail"
            border
            style="width:85%">
       
           <el-table-column
            prop="NO"
            label="设备编号"
              align="center"
            width="120">
            </el-table-column>

            <!-- <el-table-column
              prop="localtion"
              label="设备位置"
              align="center"
              width="120">
            </el-table-column> -->

            <el-table-column
            prop="runstatus"
            label="运行状态"
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
              prop="balarmel"
              label="滤网报警"
              align="center"
              width="100">
          </el-table-column>


          <el-table-column
              prop="Hzadjust"
              label="冷水阀调节"
            align="center"
              width="100">
            </el-table-column>

            <el-table-column
              prop="hzfeedback"
              label="冷水阀反馈"
              align="center"
              width="100">
            </el-table-column>

            <el-table-column
              prop="fantempersure"
              align="center"
              label="送风温度"
              width="80">
            </el-table-column>
            
            <el-table-column
              prop="tempersureset"
              label="送风设定温度"
              align="center"
              width="110">
            </el-table-column>

           
           <!--  <el-table-column
              prop="backfantem"
              label="回风温度"
              align="center"
              width="100">
            </el-table-column> -->

           <!--  <el-table-column
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
              prop="walljingya"
              align="center"
              label="管井静压"
              width="80">
            </el-table-column>

            <el-table-column
              prop="jingyaset"
              label="静压设定"
              align="center"
              width="80">
            </el-table-column> -->

           
           
        </el-table>


        
    </div>
</template>


<script>
    export default{
        data(){
            return {  
                tableData: [
                      {
                        NO:'PAU-5F-01',
                        localtion:"",
                        runstatus:"运行",
                        stastpotroller:"停止",
                        handauto:"手动",
                        alarm:"正常",
                        balarmel:"正常",
                        Hzadjust:"33.34",
                        hzfeedback:"78.5",
                        fantempersure:'22.30',
                        tempersureset:'22.30'
                        // fanstatus:'运行',
                        // fantroller:'开始',
                        // runmodel:'开始',
                        // modelcontroller:'开始'
                      }, 
                      {
                        NO:'PAU-5F-01',
                        localtion:"",
                        runstatus:"运行",
                        stastpotroller:"停止",
                        handauto:"手动",
                        alarm:"正常",
                        balarmel:"正常",
                        Hzadjust:"33.34",
                        hzfeedback:"78.5",
                        fantempersure:'22.30',
                        tempersureset:'22.30'
                        // fanstatus:'运行',
                        // fantroller:'开始',
                        // runmodel:'开始',
                        // modelcontroller:'开始'   
                      }, 
                      {
                        NO:'PAU-21F-01',
                        localtion:"",
                        runstatus:"运行",
                        stastpotroller:"停止",
                        handauto:"手动",
                        alarm:"正常",
                        balarmel:"正常",
                        Hzadjust:"33.34",
                        hzfeedback:"78.5",
                        fantempersure:'22.30',
                        tempersureset:'22.30'
                        // fanstatus:'运行',
                        // fantroller:'开始',
                        // runmodel:'开始',
                        // modelcontroller:'开始'
                      }, 
                      {
                       NO:'PAU-37F-01',
                       localtion:"",
                       runstatus:"运行",
                       stastpotroller:"停止",
                       handauto:"手动",
                       alarm:"正常",
                       balarmel:"正常",
                       Hzadjust:"33.34",
                       hzfeedback:"78.5",
                        fantempersure:'22.30',
                        tempersureset:'22.30'
                        // fanstatus:'运行',
                        // fantroller:'开始',
                        // runmodel:'开始',
                        // modelcontroller:'开始'
                      }
                  ] 
            
       
    }
  },

               
    

  methods:{

      AClist_pau2_datail(){
          this.$router.push({ name: 'AClist_pau2_datail'})

      },
      onAClist(){
        this.$router.push({ name: 'AClist'})

      }
  }
}

</script>

<style scoped>

.coldtable{
    margin:0 auto;
}

.images{
  float:left;
  width:50px;
  height:50px;
  font-size: 40px;
  margin-left: 20px;
}



</style>