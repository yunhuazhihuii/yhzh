<template>
	<div>

		<!-- value / v-model	绑定值，选中选项卡的 name	string	—	第一个选项卡的 name 
			tab-click	tab 被选中时触发	被选中的标签 tab 实例

		-->
		<el-tabs v-model="activeName" @tab-click="toggleTab">

	      <el-tab-pane label="实时数据" name="first">

	      	<realtimedtab :devtypeid="devtypeid" :tabTitle="tabTitle" :floorScopeList="floorScopeList" 
	      		:devAttrData="devAttrData" :pageCount="pageCount"></realtimedtab>

	      </el-tab-pane>

	      <el-tab-pane label="历史数据" name="second">
	      	<hisdtab :devtypeid="devtypeid" :tabTitle="tabTitle" :floorScopeList="floorScopeList" 
	      		:devAttrData="devAttrData"></hisdtab>
	      </el-tab-pane>
	      
	 	</el-tabs>
 	</div>
</template>

<script>
	import { getDevAttr } from '../service/getData.js';
	import realtimedtab from './dev_rtime_dtab.vue';
	import hisdtab from './dev_his_dtab.vue';
	export default {
		props: ['devtypeid','tabTitle','floorScopeList','pageCount'],
		data() {
			return {
	      		devAttrData:[], //设备属性
				activeName:"first",
				currentTab: 'realtimedtab' // currentTab 用于标识当前触发的子组件
			}
		},
		components: { // 声明子组件
		  realtimedtab,
		  hisdtab
		 },
		created(){
			var that = this;
			//获取设备属性列表
	    	getDevAttr(that.devtypeid,res=>{
              console.log("获取设备属性列表",res.data);
              that.devAttrData = res.data;
            });
		},
		methods: {
			toggleTab: function(tab,event) {
			   //this.currentTab = tab; // tab 为当前触发标签页的组件名
			}
		}
	}
</script>

<!-- 注意不能加 scoped，否则会给样式前面加上范围限制 -->
<style>
	/*tab标题*/
	.el-tabs__item{
		color: white;
	}
	/*整个tab导航条*/
	.el-tabs__nav{
		margin-left: 10px;
	}
	/*楼层选择按钮*/
	.el-button{
		width: 69.03px;
	}
	/*楼层选择按钮 点中后颜色*/
	.el-button--info.is-disabled{
		color: #FF0B89;
	}
	/*楼层选择按钮 点中后颜色*/
	.el-button--info.is-disabled:hover{
		color: #FF0B89;
	}
</style>