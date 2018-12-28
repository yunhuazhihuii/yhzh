<template>    
  <div id="podiumbuilding"> 
    <div class="class">
      <ul class="qunlouclass">
        <li><router-link to='/home/podiumbuilding/devtab'>办公楼层</router-link></li>
        <li><router-link to='/home/podiumbuilding/devtab'>裙楼层</router-link></li>
        <li><router-link to='/home/podiumbuilding/devtab'>地下室</router-link></li>
        <li><router-link to='/home/podiumbuilding/devtab'>33楼</router-link></li>
      </ul> 
      
    </div>

    <router-view></router-view>
    

    <!-- <devtabposition :devtypeid="devtypeid" :tabTitle="tabTitle" :floorScopeList="floorScopeList"></devtabposition>  -->

      
      </div>

    </div>

</template>


<script> 
// import Datatable from './Datatable.vue';
import devtabposition from '../../components/dev_tab.vue';


export default{

  components:{devtabposition},

	data() {

		return {
      devtypeid: 1, //设备属性编号
       floorScopeList: [{floor1: "1",floor2: "4"},
                       
                        {floor1: "6"}], //楼层范围
       tabTitle: "裙楼层空调机组参数" ,//表格标题 

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

      ],


      coolpuild:[
        {
          coolNO:'1楼商铺',
          opened:"运行",
          closed:"停止",
          handauto:"手动",
          open:'开启',
          close:'关闭'
        },
        {
          coolNO:'1楼商铺',
          opened:"运行",
          closed:"停止",
          handauto:"手动",
          open:'开启',
          close:'关闭'
        },
        {
          coolNO:'1楼商铺',
          opened:"运行",
          closed:"停止",
          handauto:"手动",
          open:'开启',
          close:'关闭'
        },
        {
          coolNO:'1楼商铺',
          opened:"运行",
          closed:"停止",
          handauto:"手动",
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
    //this.$route.query : 获取动态路由的值
    console.log(this.$route.params);  /*获取动态路由传值*/
  }
}

 
</script>

<style scoped>
 
.podium{
  margin:0 auto;
}
.class{
    width: 100%;
    overflow: hidden;
}
.qunlouclass{
 
}

.qunlouclass li{
  width:20%;
 color:#red;
 font-size:20px;
}




</style>