<template>    
    <div id="news">  

      <devtab :devtypeid="devtypeid" :tabTitle="tabTitle" :floorScopeList="floorScopeList"></devtab>
       
    </div>

</template>


<script> 

import devtab from '../../components/dev_tab.vue';

export default{

  components:{devtab},

	data() {
		return {

      devtypeid: 1, //设备属性编号
       floorScopeList: [{floor1: "B1"},
                       
                        {floor1: "B2"}], //楼层范围
       tabTitle: "地下室空调机组参数" ,//表格标题 


    }

	},
	 methods: {
      onSubmit() {
        console.log('submit!');
      },


        //双击table表格行跳转区域   push：name: 'Butterflybamp',：对应router的name: 'Butterflybamp',
        todetail(){
            this.$router.push({ name: 'ACfloor_detail'})
        }

    } 
}


    
</script>

<style scoped>
.coldtable{
	margin:0 auto;
}

.el-table th {
    padding: 6px 0 !important;
}



</style>