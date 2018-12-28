<template>
  <div style="width: 960px;margin: 10px auto;">
    <div>
    	
	  	<el-button type="info" v-for='(floorScope,index) in floorScopeList' :disabled="floorBtnCho==index"
	  	  size="mini" @click="queryFloors(index)">
	  		{{floorScope.floor1}}~{{floorScope.floor2}}F
	  	</el-button>
    </div>
    <div style="box-shadow:0 0 6px #ddd;">
	  	<h2 style="height: 50px;line-height: 50px;vertical-align: center;margin-bottom: 0px; background-color: white; color: red">{{tabTitle}}</h2> 
	  	<el-table
			:data="devPointData"
			ref="multipleTable"
			border
			:row-style="tableRowStyle"
	 		:header-cell-style="tableHeaderStyle"
			tooltip-effect="dark"
			style="width: 100%">
			<el-table-column prop="devEnName" label="设备编号" :show-overflow-tooltip="true">
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
</template>


<script>
	import { getDevTable } from '../service/getData.js';
	import { viewConfig } from '../../config/yhzh_view_cfg.js';
	import util from '../common/js/util';
	export default {
   		props: ['devtypeid','tabTitle','floorScopeList',"devAttrData"],
    	data() {
	      return {
	      	devPointData:[], //设备及点表信息
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
	        //that.websocket = io.connect(viewConfig.websocketUrl);

            //获取设备及点表信息 以及订阅
	    	that.qryAndSubsPoints();
	    },
    	methods: {
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
			//楼层按钮点击事件
			queryFloors(index){
				var that = this;
				that.floorBtnCho = index; //选中按钮的序号
				that.floor1 = that.floorScopeList[index].floor1;
				that.floor2 = that.floorScopeList[index].floor2;
				//必须加bind(this)，调用方法中this的指向还是本vue
				that.$options.methods.qryAndSubsPoints.bind(this)();
			},
			/**
			*查询与订阅点信息
			*/
			qryAndSubsPoints(){
				var that = this;
				//console.log("floor1与floor2",that.floor1 + ";" + that.floor2);
				getDevTable(that.devtypeid,that.floor1,that.floor2,res=>{
	          		console.log("获取设备及点表信息",res.data);
	            	that.devPointData = res.data;
		          	for (var i = 0; i < that.devPointData.length; i++) {
		          	 	for (var key in that.devPointData[i]){
		          	 		if(that.devPointData[i][key] && that.devPointData[i][key].pointId){
		          	 			that.in_outpoint.content.data.subscribeSubitem.push(that.devPointData[i][key].pointId);
		          	 		}
		          	 	}
		          	}
	          		console.log("准备发送订阅的数据",that.in_outpoint);
	    	  		//订阅
	    	  		/*that.websocket.emit('clientToS', that.in_outpoint);
	    	  		var message=that.socket.id;
	    	  		//监听接收数据
	    	  		if(that.websocket.isConnected()){
	    	  			console.log("websocket是连接着的");
		    	  		that.websocket.on(message, MESSAAGE => {
		                 	//console.log(MESSAAGE);
		                 	let data = MESSAAGE.content.data;
		                 	data.forEach((sItem,sIndex)=>{
			                  	that.devPointData.forEach((tItems,tIndex)=>{
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
	    	  		}*/
	    		});
			}
      	},
    }
</script>
<style scoped >
 
</style>