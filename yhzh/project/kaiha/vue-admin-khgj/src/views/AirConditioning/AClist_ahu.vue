<template>    
    <div id="lobby">   

        <div class="images"><i class="el-icon-back" @click="onAClist"></i></div>
        
        <h2> AHU空调机-1夹层/2/33/37层 </h2> 
        <!-- <ul>
        	<li><router-link to='/home/conditioner/datatable'>7-17F</router-link></li>
        	<li><router-link to='/home/conditioner/datatable'>7-17F</router-link></li>
        	<li><router-link to='/home/conditioner/datatable'>7-17F</router-link></li>
        	<li><router-link to='/home/conditioner/datatable'>7-17F</router-link></li>
        </ul> -->

        <el-table
            class="coldtable"
            :data="tableData"
            @row-dblclick="Lobby_detail"
            border
            style="width:70%">
       
            <el-table-column
              prop="NO"
              label="设备编号"
              align="center"
              width="150">
            </el-table-column>

            <el-table-column
              prop="localtion"
              label="设备位置"
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
              prop="benganalarm"
              label="滤网报警"
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
              prop="waterfeedback"
              label="水阀反馈"
              align="center"
              width="80">
            </el-table-column>

            <!-- <el-table-column
              prop="tempersurefeedback"
              label="回风温度"
              align="center"
              width="80">
            </el-table-column>
            
            <el-table-column
              prop="humidityfeedback"
              label="回风湿度"
              align="center"
              width="110">
            </el-table-column>

            <el-table-column
              prop="sendfantempersure"
              label="送风温度"
              align="center"
              width="80">
            </el-table-column>

            <el-table-column
              prop="tempersureset"
              align="center"
              label="环境温度设定"
              width="110">
            </el-table-column> -->

            
        </el-table>

    </div>

</template>


<script> 
// import Datatable from './Datatable.vue';


export default{
	data() {
		return {
      tableData:[
        {
          NO:"AHU-33F-01",
          tempersurefeedback:"100",
           humidityfeedback:"100",
           sendfantempersure: '9.27',
           tempersureset: '27 ',
           benganalarm:"正常",
           wateradjust:" 100% ",
          waterfeedback:"100",
           runstatus:'运行',
           stastpotroller:'开启',
           handauto:'自动',
           alarm:'正常'
        },
        {
          NO:"AHU-33F-01",
          tempersurefeedback:"100",
           humidityfeedback:"100",
           sendfantempersure: '9.27',
           tempersureset: '27 ',
           benganalarm:"正常",
           wateradjust:" 100% ",
          waterfeedback:"100",
           runstatus:'运行',
           stastpotroller:'开启',
           handauto:'自动',
           alarm:'正常'
        },
        {
          NO:"AHU-33F-01",
          tempersurefeedback:"100",
           humidityfeedback:"100",
           sendfantempersure: '9.27',
           tempersureset: '27 ',
           benganalarm:"正常",
           wateradjust:" 100% ",
          waterfeedback:"100",
           runstatus:'运行',
           stastpotroller:'开启',
           handauto:'自动',
           alarm:'正常'
        },
        {
          NO:"AHU-33F-01",
          tempersurefeedback:"100",
           humidityfeedback:"100",
           sendfantempersure: '9.27',
           tempersureset: '27 ',
           benganalarm:"正常",
           wateradjust:" 100% ",
          waterfeedback:"100",
           runstatus:'运行',
           stastpotroller:'开启',
           handauto:'自动',
           alarm:'正常'
        }
      ]
	        
        }

	},
	// components:{
 //            'v-Datatable':Datatable
 //  },
	methods: {
      onSubmit() {
        console.log('submit!');
      },

      Lobby_detail(){
            this.$router.push({ name: 'AClist_ahu_detail'})
      },
      onAClist(){
        this.$router.push({ name: 'AClist'})

      }
    },

  mounted(){
    //this.$route.query ： 获取动态路由的值
    console.log(this.$route.params);  /*获取动态路由传值*/
  }
}

 
</script>

<style scoped>

.images{
  float:left;
  width:50px;
  height:50px;
  font-size: 40px;
  margin-left: 20px;
}



.coldtable{
    margin:0 auto;
}


</style>