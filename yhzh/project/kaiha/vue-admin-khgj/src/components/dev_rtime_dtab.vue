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
					<el-table-column :show-overflow-tooltip="true" :prop="col.devAttrId" :label="col.attrCnName"
					:width="getColStyle(col.attrWidth)">
						<template scope="scope">
							<!-- 属性数据字典不为空，则将值转为对应字典值显示 -->
							<span style="color: red" v-if="col.attrDict != '' && scope.row[col.devAttrId] != undefined">
								{{col.dictData[scope.row[col.devAttrId].value]}}
							</span>
							<span style="color: red"  v-else-if="scope.row[col.devAttrId] != undefined">{{scope.row[col.devAttrId].value}}</span>
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
					<el-table-column :show-overflow-tooltip="true" :prop="col.devAttrId" :label="col.attrCnName"
					:width="getColStyle(col.attrWidth)">
						<template scope="scope">
							<!-- 属性数据字典不为空，则将值转为对应字典值显示 -->
							<span style="color: red" v-if="col.attrDict != '' && scope.row[col.devAttrId] != undefined">
								{{col.dictData[scope.row[col.devAttrId].value]}}
							</span>
							<span style="color: red"  v-else-if="scope.row[col.devAttrId] != undefined">{{scope.row[col.devAttrId].value}}</span>
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
	import { getDevDTab } from '../service/getData.js';
	import { viewConfig } from '../../config/yhzh_view_cfg.js';
	import util from '../common/js/util';
	export default {
   		props: ['devtypeid','tabTitle','floorScopeList',"devAttrData","pageCount"],
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
        	var that = this;
        	that.floor1 = that.floorScopeList[0].floor1;
        	that.floor2 = that.floorScopeList[0].floor2;

	        //websocket连接
	        this.websocket = io.connect(viewConfig.websocketUrl);
	        
            //获取设备及点表信息 以及订阅
	    	this.qryAndSubsPoints();
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
				that.floor1 = that.floorScopeList[index].floor1;
				that.floor2 = that.floorScopeList[index].floor2;
				//必须加bind(this)，调用方法中this的指向还是本vue
				this.$options.methods.qryAndSubsPoints.bind(this)();
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
			/**
			*查询与订阅点信息
			*/
			qryAndSubsPoints(){
				var that = this;
				console.log("floor1与floor2 ==>",that.floor1 + ";" + that.floor2 + ";devtypeid=" + 
					that.devtypeid + ";pageCount=" + that.pageCount);

				getDevDTab(that.devtypeid,that.floor1,that.floor2,that.pageCount,res=>{
	            	console.log("获取设备及点表信息==》",res.data);
	            	that.devPointData1 = res.data.devPointData1;
	            	that.devPointData2 = res.data.devPointData2;
		          	for (var i = 0; i < that.devPointData1.length; i++) {
		          	 	for (var key in that.devPointData1[i]){
		          	 		if(that.devPointData1[i][key] && that.devPointData1[i][key].pointId){
		          	 			this.in_outpoint.content.data.subscribeSubitem.push(that.devPointData1[i][key].pointId);
		          	 		}
		          	 	}
		          	}
		          	for (var i = 0; i < that.devPointData2.length; i++) {
		          	 	for (var key in that.devPointData2[i]){
		          	 		if(that.devPointData2[i][key] && that.devPointData2[i][key].pointId){
		          	 			this.in_outpoint.content.data.subscribeSubitem.push(that.devPointData2[i][key].pointId);
		          	 		}
		          	 	}
		          	}
	          		console.log("准备发送订阅的数据",this.in_outpoint);

	    	  		//订阅
	    	  	    this.websocket.emit('clientToS', this.in_outpoint);
	    	  	    console.log("发送订阅成功");

	    	  		var message=that.websocket.id;

	    	  		//监听接收数据
	    	  		// if(that.websocket.isConnected()){

	    	  		// 	console.log("websocket是连接着的");

	    	  			//socket事件  接收实时数据
		    	  		this.websocket.on(message, MESSAAGE => {
		                 	console.log("适配器发送来的数据",MESSAAGE);
		                 	let data = MESSAAGE.content.data;
		                 	data.forEach((sItem,sIndex)=>{
			                  	that.devPointData1.forEach((tItems,tIndex)=>{
			                      	// 遍历对象获取映射点
			                      	for (var key in tItems){
				                    	// 如果映射点相同就赋值
				                      	if(sItem.name == tItems[key].pointId){
				                      		tItems[key].value = sItem.value;        
				                      	}
			                      	}
			                  	});
			                  	that.devPointData2.forEach((tItems,tIndex)=>{
			                      	// 遍历对象获取映射点
			                      	for (var key in tItems){
				                    	// 如果映射点相同就赋值
				                      	if(sItem.name == tItems[key].pointId){
				                      		tItems[key].value = sItem.value;        
				                      	}
			                      	}
			                  	});
		                 	});
	              		});

	    	  		// }
	    		});
			},
      	}
    }
</script>
<style scoped >
 
</style>