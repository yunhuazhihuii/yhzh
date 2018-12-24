<template>
  <div style="width: 1000px;margin: 10px auto;">
  	<div style="float: left;">
  		<template v-for='(floorScope,index) in floorScopeList'>
  			<div style="margin-top: 15px">
			  	<el-button type="info" :disabled="floorBtnCho==index" size="mini" @click="queryFloors(index)">
			  		{{getFloorBtnText(floorScope.floor1,floorScope.floor2)}}
			  	</el-button>
		  	</div>
	  	</template>
    </div>
    <div style="box-shadow:0 0 6px #ddd; float: left; width: 860px;margin-left: 30px">
    	<h2 style="height: 50px;line-height: 50px;vertical-align: center;margin-bottom: 0px; background-color: white; color: red;margin: 0px;">{{tabTitle}}</h2> 
		<div>
			<el-table
				:data="devPointData1"
				ref="multipleTable"
				border
				:row-style="tableRowStyle"
		 		:header-cell-style="tableHeaderStyle"
				tooltip-effect="dark"
				style="width: 50%;float: left;overflow: hidden;">
				<el-table-column prop="devEnName" label="设备编号" :show-overflow-tooltip="true">
				</el-table-column>
				<el-table-column prop="devCnName" label="设备名称" :show-overflow-tooltip="true">
				</el-table-column>
				<template v-for='(col) in devAttrData'>
					<el-table-column :show-overflow-tooltip="true" :prop="col.devAtrrId" :label="col.attrCnName"
					:width="getColStyle(col.attrWidth)">
						<template scope="scope">
							<!-- 属性数据字典不为空，则将值转为对应字典值显示 -->
							<span style="color: red" v-if="col.attrDict != ''">
								{{col.dictData[scope.row[col.devAtrrId].value]}}
							</span>
							<span style="color: red" v-else>{{scope.row[col.devAtrrId].value}}</span>
							<!-- 单位不为空则显示单位 -->
							<span v-if="col.attrUnit != ''">{{col.attrUnit}}</span>
						</template>	
					</el-table-column>
				</template>
			</el-table>
			<el-table
				:data="devPointData2"
				ref="multipleTable"
				border
				:row-style="tableRowStyle"
		 		:header-cell-style="tableHeaderStyle"
				tooltip-effect="dark"
				style="width: 50%; float: left;overflow: hidden;">
				<el-table-column prop="devEnName" label="设备编号" :show-overflow-tooltip="true">
				</el-table-column>
				<el-table-column prop="devCnName" label="设备名称" :show-overflow-tooltip="true">
				</el-table-column>
				<template v-for='(col) in devAttrData'>
					<el-table-column :show-overflow-tooltip="true" :prop="col.devAtrrId" :label="col.attrCnName"
					:width="getColStyle(col.attrWidth)">
						<template scope="scope">
							<!-- 属性数据字典不为空，则将值转为对应字典值显示 -->
							<span style="color: red" v-if="col.attrDict != ''">
								{{col.dictData[scope.row[col.devAtrrId].value]}}
							</span>
							<span style="color: red" v-else>{{scope.row[col.devAtrrId].value}}</span>
							<!-- 单位不为空则显示单位 -->
							<span v-if="col.attrUnit != ''">{{col.attrUnit}}</span>
						</template>	
					</el-table-column>
				</template>
			</el-table>
		</div>
	</div>

  </div>
</template>

<script>
	import { getDevTable } from '../service/getData.js';
	import { viewConfig } from '../../config/yhzh_view_cfg.js';
	import util from '../common/js/util';
	export default {
   		props: ['devtypeid','tabTitle','floorScopeList',"devAttrData"],
   		data() {
	      return {
	      	devPointData1:[], //设备及点表信息1
	      	devPointData2:[], //设备及点表信息2
        	websocket:'', //websocket连接
        	floor1: "", //楼层从
        	floor2: "", //楼层到
        	floorBtnCho: 0, //当前选中的楼层按钮号
        	//订阅接口数据格式
        	in_outpoint:{type:"rtsub",pointer:["clientId1","clientId2"],content:{data:{clientId:"H000000002",subscribeTopic:"控制器",fileCategory:"文件类型",subscribeSubitem:[]},callback:46}},
	      }
	    },
	    beforeRouteLeave(to, from, next){
	      this.websocket.disconnect();
	      next();
	    },
	    created(){

	    },
	    computed: {

	    },
    	methods: {
    		//设置楼层选择按钮显示文字
	    	getFloorBtnText(f1,f2){
	    		let fl1 = f1.replace("-","B");
	    		let fl2 = f2.replace("-","B");
	    		if(f1 === f2){
	    			return fl1;
	    		}else{
	    			return fl1 + "~" + fl2 + "F";
	    		}
	    	},
			//楼层按钮点击事件
			queryFloors(index){
				var that = this;
				that.floorBtnCho = index; //选中按钮的序号
			},
			//设置表格列的宽度
    		getColStyle(colWidth){
    			if(!util.isNull(colWidth)){
    				return colWidth + "px";
    			}
    		},
	      	// 修改table tr行的背景色    
			tableRowStyle({ row, rowIndex }){
				//return 'background-color: #F7F6Fd'
			},
			//修改table header的样式
			tableHeaderStyle({row, column, rowIndex, columnIndex }){
				var that = this;
				/*if (rowIndex === 0) {
					return 'background-color: #F7F6Fd;color: #B6B5C2;font-weight: 500;'
				}*/
				/*if(columnIndex === 0){ //设备编号

				}else if(!util.isNull(that.devAttrData) && 
					!util.isNull(that.devAttrData[columnIndex-1].attrWidth)){
					return "width:" + that.devAttrData[columnIndex-1].attrWidth + "px;color:red;";
				}*/
				//console.log("表头数据为",column);
			},
      	}

    }

</script>
<style scoped >
 
</style>