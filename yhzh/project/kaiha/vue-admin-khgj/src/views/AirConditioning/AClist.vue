<template>
    <!-- 所有的内容要被根节点包含起来 -->
    <div id=""> 

        <h2>风机盘管</h2>

        <el-table
            class="coldtable"
            :data="tableData"
            border
            style="width:55%">
       
            <el-table-column
              prop="eqNO"
              align="center"
              label="设备编号"
              width="150">
            </el-table-column>

            <el-table-column
              prop="fantempersure"
              align="center"
              label="现场温度"
              width="80">
            </el-table-column>
            
            <el-table-column
              prop="tempersureset"
              label="设定温度"
              align="center"
              width="110">
            </el-table-column>

            <el-table-column
              prop="fanstatus"
              label="风机状态"
              align="center"
              width="80">
            </el-table-column>

            <el-table-column
              prop="fantroller"
              label="风速控制"
              align="center"
              width="80">
            </el-table-column>
            
            <el-table-column
              prop="runmodel"
              label="运行模式"
              align="center"
              width="80">
            </el-table-column>
            <el-table-column
              prop="modelcontroller"
              label="模式控制"
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
                        eqNO:'18楼男卫',
                        fantempersure:'22.30',
                        tempersureset:'22.30',
                        fanstatus:'运行',
                        fantroller:'开始',
                        runmodel:'开始',
                        modelcontroller:'开始'
                      }, 
                      {
                        eqNO:'18楼男卫',
                        fantempersure:'22.30',
                        tempersureset:'22.30',
                        fanstatus:'运行',
                        fantroller:'开始',
                        runmodel:'开始',
                        modelcontroller:'开始'   
                      }, 
                      {
                        eqNO:'18楼男卫',
                        fantempersure:'22.30',
                        tempersureset:'22.30',
                        fanstatus:'运行',
                        fantroller:'开始',
                        runmodel:'开始',
                        modelcontroller:'开始'
                      }, 
                      {
                       eqNO:'18楼男卫',
                        fantempersure:'22.30',
                        tempersureset:'22.30',
                        fanstatus:'运行',
                        fantroller:'开始',
                        runmodel:'开始',
                        modelcontroller:'开始'
                      }
                  ] 
            
       
    }
  },

               
    

  methods:{

      goNews(){
          this.$router.push({ name: 'Conditionertable'})

      }
  }
}

</script>

<style scoped>

.coldtable{
    margin:0 auto;
}




</style>