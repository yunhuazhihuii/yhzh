
align="center"<template>    
    <div id="news">   
        <!-- <div class="list">
            <button v-for="item in items"> <router-link to='/Cold'>{{ item.message }} </router-link></button>
        </div> -->

        <div class="table">
             <h2> 普 通 照 明 系 统  </h2>

           
              <el-table
                :data="tableData"
                class="genlightable"
                border
                style="width: 650px;padding: 10px;">
               
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
                 <!-- <el-table-column
                  prop="obverse"
                  label="查看"
                  width="100">
                </el-table-column> -->

                <!-- <el-table-column
                  prop="address"
                  label=""
                  width="5">
                </el-table-column> -->

                
                
              </el-table>
            
        </div>

        
      
    </div>

</template>


<script> 

export default{

  data(){
    return {  

      items: [
        { message: 'B4' },
        { message: 'B3' },
        { message: 'B2' },
        { message: 'B1' },
        { message: '01~06F' },
        { message: '07~14F' },
        { message: '15~23F' },
        { message: '24~31F' },
        { message: '32~39F' },
        { message: '40~46F' },
        { message: '47~53F' }
      ] ,

      tableData: [
        {
          eqNO: '18-NL1-3',
          name: '走廊照明',
          status: '运行 ',
          handauto:"自动",
          baginstopcontroller:"开启"
          // obverse:"查看"
        }, 
        {
          eqNO: '18-NL1-3',
          name: '走廊照明',
          status: '运行 ',
          handauto:"自动",
          baginstopcontroller:"开启"
          // obverse:"查看"
        }, 
        {
          eqNO: '18-NL1-3',
          name: '走廊照明',
          status: '运行 ',
          handauto:"自动",
          baginstopcontroller:"开启"
          // obverse:"查看"
        }, 
        {
          eqNO: '18-NL1-3',
          name: '走廊照明',
          status: '运行 ',
          handauto:"自动",
          baginstopcontroller:"开启"
          // obverse:"查看"
        }
      ]  
              
    }
  }       
}


    
</script>

<style scoped>

.genlightable{
    margin:0 auto;
}

</style>