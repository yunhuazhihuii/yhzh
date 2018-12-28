<template>
	<div>
		<el-tabs v-model="activeName" @tab-click="toggleTab">
	      <el-tab-pane label="实时数据" name="first">
	      	<newfanrtime :devtypeid="devtypeid" :tabTitle="tabTitle" :floorScopeList="floorScopeList" 
	      		:devAttrData="devAttrData" ></newfanrtime>
	      </el-tab-pane>
	      <el-tab-pane label="历史数据" name="second">
	      	<newfanhis :devtypeid="devtypeid" :tabTitle="tabTitle" :floorScopeList="floorScopeList" 
	      		:devAttrData="devAttrData"></newfanhis>
	      </el-tab-pane>
	 	</el-tabs>
 	</div>
</template>

<script>
	import { getDevAttr } from '../service/getData.js';
	import newfanrtime from './newfan_rtime.vue';
	import newfanhis from './newfan_his.vue';
	export default {
		props: ['devtypeid','tabTitle','floorScopeList'],
		data() {
			return {
	      		devAttrData:[], //设备属性
				activeName:"first",
				currentTab: 'newfan_rtime' // currentTab 用于标识当前触发的子组件
			}
		},
		components: { // 声明子组件
		  newfanrtime,
		  newfanhis
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
	/*.el-button{
		padding: 5px 10px;
	}*/
	/*楼层选择按钮 点中后颜色*/
	.el-button--info.is-disabled{
		color: #FF0B89;
	}
	/*楼层选择按钮 点中后颜色*/
	.el-button--info.is-disabled:hover{
		color: #FF0B89;
	}
</style>