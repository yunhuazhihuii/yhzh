
align="center"<template>    
    <div id="news">   
        <h2>热水、盘管、电加热系统目录</h2>

        <!-- <ul>
            <li v-for="item in items"> <button @click="godatable()"> {{ item.message }}  </button></li>
        </ul> -->

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
                 
              </el-table>
  
    </div>

</template>


<script> 

export default{
    data(){
        return {               
            // items: [
            //     { message: 'B4--6F' },
            //     { message: '07--16F' },
            //     { message: '17--27F ' },
            //     { message: '28--38F' },
            //     { message: '39--44F ' },
            //     { message: '45--50F' },
            //     { message: '50--53F ' },
            //     { message: '漏水报警' }
            // ] 

            tableData: [
        {
          eqNO: '18-NR1-3',
          name: '风机盘管电源',
          status: '运行 ',
          handauto:"自动",
          baginstopcontroller:"开启"
          // obverse:"查看"
        }, 
        {
          eqNO: '18-NR1-3',
          name: '风机盘管电源',
          status: '运行 ',
          handauto:"自动",
          baginstopcontroller:"开启"
          // obverse:"查看"
        }, 
        {
          eqNO: '18-NR1-3',
          name: '风机盘管电源',
          status: '运行 ',
          handauto:"自动",
          baginstopcontroller:"开启"
          // obverse:"查看"
        }, 
        {
          eqNO: '18-NR1-3',
          name: '风机盘管电源',
          status: '运行 ',
          handauto:"自动",
          baginstopcontroller:"开启"
          // obverse:"查看"
        }
      ]     
        }
    },

    methods:{

            godatable(){
                this.$router.push({ name: 'ODatatable'})

            }
        }       
}


    
</script>

<style scoped>



h2{
  margin: 20px 0px 20px 0px;
}
.genlightable{
    margin:0 auto;
}


</style>