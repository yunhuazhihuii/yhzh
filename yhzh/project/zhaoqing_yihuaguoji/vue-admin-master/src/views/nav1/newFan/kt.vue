<template>
	<div>
		<div>
		<router-link :to="{ name: '排风机2', params: { floorNum:numFloor,subsystem:subsystem}}">
			<i class="el-icon-back" style="font-size: 24px;margin-right: 24px;"></i>
        </router-link>
		     {{NumKt}}
		 </div>
         <!-- 排风机 -->
		 <div class="Blowers kt_bg">
		 	<!-- 运行状态 -->
		 	<el-col :span="12">
				<!-- 风机 -->
				<div class="modelFile fsFile">
					<!--  风机状态 -->
					<div class="fsdata fanAlarm"> 
					     <el-input v-model="p_StateValue"  :readonly="true" v-if="KtData.p_FanState.p_FanState == '1'?p_StateValue='运行':p_StateValue='停止'">
					     <template slot="prepend"> 风机状态&nbsp;&nbsp;&nbsp;&nbsp;</template>
					     </el-input>
					</div>
					<!--  手自动 -->
					<div class="fsdata fanAlarm"> 
					     <el-input v-model="p_HandAutoValue"  :readonly="true" v-if="KtData.p_HandAuto.p_HandAuto == '1'?p_HandAutoValue='自动':p_HandAutoValue='手动'">
					     <template slot="prepend"> 手自动状态</template>
					     </el-input>
					</div>
					<!-- 风机报警 -->
					<div class="fsdata fanAlarm"> 
					    <el-input  v-model="p_AlarmValue"  :readonly="true"
					    v-if="KtData.p_FanAlarm.p_FanAlarm == '1'?p_AlarmValue='报警':p_AlarmValue='正常'">
					    <template slot="prepend"> 风机报警&nbsp;&nbsp;&nbsp;&nbsp;</template>
					  </el-input>
					</div>
					<!--  风机启停 -->
					<div class="fsdata">
						风机启停
						<el-button type="primary" @click="rotation " v-if="NewFanVal=='日程控制'" disabled>禁止</el-button>
					    <el-button type="primary" @click="rotation "  v-show="NewFanVal=='手动控制'" v-if="KtData.p_FanState.p_FanState == '0'?p_value='开启':p_value='关闭'">{{p_value}}</el-button>
					</div>
				</div>
			</el-col>
			<el-col :span="12">
				<!-- 风机 -->
				<img src="../../../assets/lyzn/fanRightTrue.gif" v-if="KtData.p_FanState.p_FanState == '1'">
				<!-- 风机 -->
				<img src="../../../assets/lyzn/fanRightFalse.png" v-if="KtData.p_FanState.p_FanState == '0'">
			</el-col>
		</div>
		<!-- 模块 -->
		<scren :numFloor="numFloor" :KtData='KtData' :admin="admin" news="1"
		check="1" tops="10px" v-if="screnShowDom"></scren>
		</div>
</template>
<script>
import { ktData } from '../../../service/getData';
import scren from '../../../components/newScren.vue';
import {mapState} from 'vuex'
	export default {
		components:{scren},
		data() {
			return {
		     //socket
		     socket:'',
		     // 空调描述
		     NumKt:'',
             numFloor:0,
		     // 空调编号
		     devcode:'',
		     subsystem:'',
		     // 按需加载弹框
		     screnShowDom:true,
			 // 排风机
			 p_value:'开启',
			 //排风机状态值
			 p_StateValue:'',
			  //排风机报警
			 p_AlarmValue:'',
			 //排风机手自动
			 p_HandAutoValue:'',
			 //风机数据
			 KtData:{//p_FanState p_FanAlarm p_HandAuto startBut
			 	p_FanState:{p_FanState:"0"},
			 	p_FanAlarm:{p_FanAlarm:"1"},
			 	p_HandAuto:{p_HandAuto:"0"},
			 	startBut:{startBut:"1"}
			 },
			 in_outpoint:{},
			 tableData:'',
			}
		},
		beforeRouteLeave (to, from, next) {
		      this.socket.disconnect();
		      next()
		     },
		computed: {
		    ...mapState([
		      'NewFanVal','admin'
		    ])
		  },
		created(){
			//保存请求的空调数据
			 var ktdata=[];
			 var that=this;
			  //websocket连接
             this.socket = io.connect('http://192.168.0.114:8090');
              //空调编号
             this.NumKt=this.$route.params.description;
               //空调编号
             this.subsystem=this.$route.params.subsystem;
               //空调编号
             this.numFloor=this.$route.params.floor;
             //空调devcode
             this.devcode=this.$route.params.devcode;
             
            //nodejs发送订阅信息
	        this.in_outpoint={type:"rtsub",pointer:["clientId1","clientId2"],content:{data:{clientId:"H000000002",subscribeTopic:"控制器",fileCategory:"文件类型",subscribeSubitem:[]},callback:46}};
             // 获取数据库数据

             //console.log("devcode",this.devcode);
             ktData(this.devcode,res=>{
             	//对应相应的属性
                res.data.forEach((item,index) => {
              	  let property=item.viewVar;
              	  //给对象添加属性
                  that.$set(item,property,"1");
                  //添加数据到订阅信息
                  that.in_outpoint.content.data.subscribeSubitem.push(item.ReflectVar);
		         });
                 // 单个空调数据
                console.log("数据库查询到的风机数据")
                ktdata=res.data;
                //console.log(ktdata);

                //将数据库赋值给KtData，避免无websket接收时设置日程表报错 chenms 2018-8-28
				ktdata.forEach((item,index) => {
		            //给对象添加属性
                    that.$set(that.KtData,item.viewVar,item);
	         	});



	             // 发送nodejs的数据
                console.log("发送nodejs订阅的数据")
                console.log(that.in_outpoint)
                //nodejs发送订阅信息
                this.socket.emit('clientToS', this.in_outpoint);
		         //nodejs发送订阅信息结束
		         let message=that.socket.id;
		          //socket事件  接收实时数据
		          this.socket.on(message, MESSAAGE => {
		            console.log("适配器发送来的数据")
		            console.log(MESSAAGE);
		            //设备发来的数据
		            let data=MESSAAGE.content.data;
		              //遍历对象
			          data.forEach(items => {
			            ktdata.forEach((item,index) => {
			             //如果相等就改变状态
			              if(items.name==item.ReflectVar){
			                 item[item.viewVar]=items.value;
			              }
			            })
			         });
			      //把真实数据赋给变量 
                 ktdata.forEach((item,index) => {
		             //给对象添加属性
                    that.$set(that.KtData,item.viewVar,item);
		            });
                 that.screnShowDom=true;
		          })
		        })
		},
		methods: {
			//停止
			rotation(){
				console.log(this.KtData.startBut.ReflectVar);
				var that=this; 
				var message=that.socket.id;  
                    //如果发来的数据为1就发送0
                if(that.KtData.p_FanState.p_FanState == '1'){
                	 var MESSAAGE1= {"type": "clientSend","pointer": ["clientId1","clientId2"],
	                 "content": {
	                     "sourcetype": "html5",
	                     "data": [{"name": that.KtData.startBut.ReflectVar,"value": '0'}],
	                     "callback": 21
                            }}
                }else{
	             var MESSAAGE1= {"type": "clientSend","pointer": ["clientId1","clientId2"],
		                 "content": {
	                     "sourcetype": "html5",
	                     "data": [{"name": that.KtData.startBut.ReflectVar,"value": '1'}],
		                     "callback": 21
	                    }}
                      }
		        //发送数据
		        this.socket.emit('clientToS', MESSAAGE1);
			},
		},
	};

</script>

<style scoped lang="scss">
 @import '~scss_vars';
   .newFan{
   	background: url(../../../assets/lyzn/PAUC.jpg)  no-repeat;
   }
   // 排风机
   .Blowers{
   	   	img{
   	   	    margin-top: 40px;
   	   	}
        .fsFile{
        	position: static;
        }
   }
</style>