
align="center"<template>    
    <div id="ODatatable"> 

        <!-- <v-Slist></v-Slist>   -->
        <!-- Flood Alarm     ODatatable-->
        <h2> 溢水报警</h2> 
        
        <el-table
            class="coldtable"
            :data="tableData"
            border
            style="width:450px;padding: 10px;">
       
            <el-table-column
              prop="eqNO"
              label="楼层"
              align="center"
              width="150">
            </el-table-column>
            <el-table-column
              prop="name"
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
            

            
            
        </el-table>

        
 
    </div>

</template>


<script> 

// import Slist from './Slist.vue';

export default{
  data(){
    return {  
      tableData: [
          {
            eqNO: '18-NR1-3',
            name: '风机盘管电源',
            status: '运行 '
           
          }, 
          {
            eqNO: '18-NR1-3',
            name: '风机盘管电源',
            status: '运行 '
           
          }, 
          {
            eqNO: '18-NR1-3',
            name: '风机盘管电源',
            status: '运行 '
           
          }, 
          {
            eqNO: '18-NR1-3',
            name: '风机盘管电源',
            status: '运行 '
           
          }
      ]


    }
  },

    // components:{
    //   'v-Slist':Slist
    // },

    methods:{

            // godatable(){
            //     this.$router.push({ name: 'ODatatable'})

            // }
        }       
}


    
</script>

<style scoped>

h2{
  margin: 20px 0px 20px 0px;
}

.coldtable{
    margin:0 auto;
}


</style>