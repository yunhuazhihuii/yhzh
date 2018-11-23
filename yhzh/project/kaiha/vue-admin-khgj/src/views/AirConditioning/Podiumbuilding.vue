<template>    
    <div id="podiumbuilding">   
        <h2> 裙楼层 </h2> 

       <div class="podiumbuilding" style="width:40%">
        <h2> 普通水阀 </h2> 
        <el-table
            class="podium"
            :data="gentableData"
            border
            style="width:62%">

            <el-table-column
              prop="genNO"
              label="编号"
              align="center"
              width="150">
            </el-table-column>
            <el-table-column
              prop="genopen"
              label="开控制"
              align="center"
              width="80">
            </el-table-column>
            
            <el-table-column
              prop="genclose"
              label="关控制"
              align="center"
              width="80">
            </el-table-column>


        </el-table>

      </div>

      <div class="daypodiumbuilding" style="width:40%">
        <h2> 24小时水阀 </h2> 
        <el-table
            class="podium"
            :data="tableData"
            border
            style="width:62%">

            <el-table-column
              prop="NO"
              label="编号"
              align="center"
              width="150">
            </el-table-column>
            <el-table-column
              prop="open"
              label="开控制"
              width="80">
            </el-table-column>
            
            <el-table-column
              prop="close"
              label="关控制"
              width="80">
            </el-table-column>


        </el-table>

      </div>

    </div>

</template>


<script> 
// import Datatable from './Datatable.vue';


export default{
	data() {
		return {
      gentableData:[
        {
          genNO:'101商铺',
          genopen:'开启',
          genclose:'关闭'
        },
        {
          genNO:'101商铺',
          genopen:'开启',
          genclose:'关闭'
        },
        {
          genNO:'101商铺',
          genopen:'开启',
          genclose:'关闭'
        },
        {
          genNO:'101商铺',
          genopen:'开启',
          genclose:'关闭'
        }

      ],

      tableData:[
        {
          NO:'101商铺',
          open:'开启',
          close:'关闭'
        },
        {
          NO:'101商铺',
          open:'开启',
          close:'关闭'
        },
        {
          NO:'101商铺',
          open:'开启',
          close:'关闭'
        },
        {
          NO:'101商铺',
          open:'开启',
          close:'关闭'
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
      }
    },

  mounted(){
    //this.$route.query ： 获取动态路由的值
    console.log(this.$route.params);  /*获取动态路由传值*/
  }
}

 
</script>

<style scoped>

.podiumbuilding{
    margin:0 20px 10px 80px;
    float:left;
}

.podium{
  margin:0 auto;
}


.daypodiumbuilding{
    margin:0 80px 10px 20px;
    float:right;
}


</style>