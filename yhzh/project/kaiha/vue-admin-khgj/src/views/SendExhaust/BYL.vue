
align="center"<template>    
    <div id="news">  
      <h2> 商铺排油烟风机状态 </h2>  
        
        <el-table
            class="coldtable"
            :data="tableData"
            border
            style="width:23%">
       
            <el-table-column
              prop="eqNO"
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
           
        
        </el-table>

        

        
      
    </div>

</template>


<script> 

export default{
  data(){
    return {  

      tableData: [
          {
            eqNO: '107商铺排油烟风机',
            status: '运行 '  
          }, 
          {
            eqNO: '107商铺排油烟风机',
            status: '运行 '  
          }, 
          {
            eqNO: '107商铺排油烟风机',
            status: '运行 ' 
          },
          {
            eqNO: '107商铺排油烟风机',
            status: '运行 ' 
          },
          {
            eqNO: '107商铺排油烟风机',
            status: '运行 ' 
          }, 
          {
            eqNO: '107商铺排油烟风机',
            status: '运行 ' 
          }, 
          {
            eqNO: '107商铺排油烟风机',
            status: '运行 ' 
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
    margin-top: 20px;
}




</style>