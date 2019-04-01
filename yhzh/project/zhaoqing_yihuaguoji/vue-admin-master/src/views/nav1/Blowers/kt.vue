<template>
	<div>
		<div>
			<router-link :to="{ name: '空调系统2', params: { floorNum:numFloor,subsystem:subsystem}}">
				<i class="el-icon-back" style="font-size: 24px;margin-right: 24px;"></i>
	        </router-link>
			     {{NumKt}}
		</div>

		<div class="kt_bg newFan">
			<!-- 风机 -->
			<img src="../../../assets/lyzn/fanRightTrue.gif"  class="kt_fj" v-if="KtData.FanState.FanState == '1'">

		   <!-- 总时长 -->
			<div class="time">
				  <el-input  v-model="KtData.HandAuto.HandAuto"  :readonly="true" >
				    <template slot="prepend" >总时长</template>
				  </el-input>
			</div>

			 <!-- 送风温度 -->
			<div class="Temperature">
				  <el-input  v-model="KtData.temperature.temperature"  :readonly="true" >
				    <template slot="prepend" >送风温度</template>
				    <template slot="append" >&nbsp;&nbsp;°</template>
				  </el-input>
			</div>

			<!-- 过滤网 -->
			<div class="glw">
				  <el-input placeholder="正常" v-model="pressurevalue" :readonly="true" v-if="KtData.pressure.pressure =='1'?pressurevalue='报警':pressurevalue='正常'">
				    <template slot="prepend" >滤网报警</template>
				  </el-input>
			</div>

			<!-- 水阀调节 -->
			<div class="modelFile">
				   <el-input  v-model="KtData.WaterAdjust.WaterAdjust"  :readonly="true">
				    <template slot="prepend">水阀调节</template>
				    <template slot="append" >%</template>
				  </el-input>
				   <el-input  v-model="KtData.WaterFeedback.WaterFeedback"  :readonly="true">
				    <template slot="prepend">水阀反馈</template>
				    <template slot="append" >%</template>
				  </el-input>
			</div>

			<!-- 风机 -->
			<div class="modelFile fsFile">
				<!--  风机状态 -->
				<div class="fsdata fanAlarm"> 
				     <el-input v-model="StateValue"  :readonly="true" v-if="KtData.FanState.FanState =='1'?StateValue='运行':StateValue='停止'">
				     <template slot="prepend"> 风机状态&nbsp;&nbsp;&nbsp;&nbsp;</template>
				     </el-input>
				</div>

				<!-- 风机报警 -->
				<div class="fsdata fanAlarm"> 
				    <el-input  v-model="AlarmValue"  :readonly="true"
				    v-if="KtData.FanAlarm.FanAlarm =='1'?AlarmValue='报警':AlarmValue='正常'">
				    <template slot="prepend"> 风机报警&nbsp;&nbsp;&nbsp;&nbsp;</template>
				  </el-input>
				</div>

				<!-- 手自动状态 -->
				<div class="fsdata fanAlarm"> 
				    <el-input  v-model="AutoValue"  :readonly="true"
				    v-if="KtData.HandAuto.HandAuto =='1'?AutoValue='自动':AutoValue='手动'">
				    <template slot="prepend"> 手自动状态</template>
				  </el-input>
			    </div>

				<!--  风机启停 -->
				<div class="fsdata">
					风机启停
				    <el-button type="primary" @click="rotation " v-if="RcState=='日程控制'" disabled>禁止</el-button>
				    <el-button type="primary" @click="rotation " v-show="RcState=='手动控制'" v-if="KtData.FanState.FanState =='0'?value='开启':value='关闭'">{{value}}</el-button>
				</div>

			</div>

		</div>


		  <!-- 温度设置 -->
		<el-form ref="form" :model="form" label-width="80px" class="formValue">
		 	<el-form-item label="模式选择">
			   <el-radio-group v-model="form.resource">
			      <el-radio label="联动" border></el-radio>
			      <el-radio label="点动" border></el-radio>
			    </el-radio-group>
			</el-form-item>

			<!-- 温度设置 -->
			<div class="demo-input-suffix" v-if="form.resource=='联动'">
  				 温度设置：
  				  <el-input
				    placeholder="请输入温度"
				    prefix-icon="el-icon-edit"
				    v-model="form.Teminput">
				  </el-input>
			</div>

			<div class="demo-input-suffix" v-if="form.resource=='点动'">
  				 水阀设置：
  				  <el-input
				    placeholder="水阀调节"
				    prefix-icon="el-icon-edit"
				    v-model="form.WaterInput">
				  </el-input>
			 </div>

			 <!-- 确认按钮 -->
			 <el-button type="primary" style="float: right; margin-top: -40px;"
			   v-if="RcState=='手动控制'" @click="setWaTem">确认</el-button>
			 <el-button type="primary" style="float: right; margin-top: -40px;" v-else
			   @click="setWaTem" disabled>确认</el-button>
		</el-form>

		<!-- 创建、查看：组件传值 -->
		<scren :numFloor="numFloor" :KtData='KtData' :admin="admin" news="1"
		check="1" tops="10px" v-if="screnShowDom"></scren>
	</div>
</template>
<script>
import { ktData } from '../../../service/getData';
import scren from '../../../components/newScren';
import {mapState} from 'vuex'
	export default {
		components:{scren},
		data() {
			return {
		    // 请求数据成功后加载dom
			screnShowDom:true,
		    //创建场景
	        screneShow:true,
	        // 删除场景文字切换
	        delOrCancle:'删除场景',
	        //删除场景图标
	        SceneClose:false,
		     //socket
		     socket:'',
		     numFloor:0,
		     // 空调描述
		     NumKt:'',
		     subsystem:'',
		     // 空调编号
		     devcode:'',
			 value:'开启',
			 //风机状态值
			 StateValue:'',
			 //风机报警
			 AlarmValue:'',
			 AutoValue:'',
			 pressurevalue:0,
			 //空调数据
			 KtData:{
			 	FanState:{FanState:"0"},
			 	FanAlarm:{FanAlarm:"1"},
			 	HandAuto:{HandAuto:"1"},
			 	pressure:{pressure:"1"},
			 	temperature:{temperature:"1"},
			 	WaterFeedback:{WaterFeedback:"1"},
			 	startbut:{startbut:"1"},
			 	WaterAdjust:{WaterAdjust:"1"},
			 	SetTemplete:{SetTemplete:'1'},
			 	startBut:{subsystem:"BA"}
			 },
			 in_outpoint:{},
			 ktdata:[],
			 // 向组件传递数据
			 TempLdata:{},
			 form: {
                resource: '联动',
                Teminput:'',
                WaterInput:'',
			  }
			}
		},
		beforeRouteLeave (to, from, next) {
		      this.socket.disconnect();
		      next()
		     },
		 computed: {
		    ...mapState([
		      'RcState','admin'
		    ])
		  },
		created(){
			//保存请求的空调数据
			 var that=this;
			  //websocket连接
             this.socket = io.connect('http://192.168.0.114:8090');
              //空调编号
             this.NumKt=this.$route.params.description;
              //空调编号
             this.numFloor=this.$route.params.floor;
              //空调类型
             this.subsystem=this.$route.params.subsystem;
             //空调devcode
             this.devcode=this.$route.params.devcode;

            //nodejs发送订阅信息(前端订阅实时数据（订阅请求）)
	        this.in_outpoint={type:"rtsub",pointer:["clientId1","clientId2"],content:{data:{clientId:"H000000002",subscribeTopic:"控制器",fileCategory:"文件类型",subscribeSubitem:[]},callback:46}};
             // 获取数据库数据
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
                console.log("从数据库中查询到空调数据");
                that.ktdata=res.data;
                console.log( that.ktdata);

                //将数据库赋值给KtData，避免无websket接收时设置日程表报错 chenms 2018-8-28
				that.ktdata.forEach((item,index) => {
		            //给对象添加属性
                    that.$set(that.KtData,item.viewVar,item);
	         	});


	              // 发送nodejs的数据
                console.log("发送nodejs的数据");
                console.log(that.in_outpoint);
                //nodejs发送订阅信息
                this.socket.emit('clientToS', this.in_outpoint);
		         //nodejs发送订阅信息结束
		         
		         var message=that.socket.id;
		          //socket事件  接收实时数据
		          this.socket.on(message, MESSAAGE => {
		            console.log("适配器发送来的数据")
		            console.log(MESSAAGE);
		            //设备发来的数据
		            let data=MESSAAGE.content.data;
		              //遍历对象
			          data.forEach(items => {
			             that.ktdata.forEach((item,index) => {
			             //如果相等就改变状态
			              if(items.name==item.ReflectVar){
			                 item[item.viewVar]=items.value;
			              }
			            });
			         });
			       //把真实数据赋给变量 
                 that.ktdata.forEach((item,index) => {
		             //给对象添加属性
                    that.$set(that.KtData,item.viewVar,item);
		            }); 

                  // 给温度、水阀设置添加值
                  that.form.Teminput=that.KtData.SetTemplete.SetTemplete;
                  that.form.WaterInput=that.KtData.WaterAdjust.WaterAdjust;
                  that.screnShowDom=true;
		          });	  
		        });
		},
		methods: {
			
			// 设置温度和水阀(前端向服务器发送数据)
		    setWaTem(){
		    	var that=this;
		    	var MESSAAGE1= {"type": "clientSend","pointer": ["clientId1","clientId2"],
	                 "content": {
	                     "sourcetype": "html5",
	                     "data": [{"name": that.KtData.SetTemplete.ReflectVar,"value":that.form.          Teminput }],
	                     "callback": 21
                            }}

		    	if(this.form.resource=="点动"){
		 	        // 发送水阀值
		    	    MESSAAGE1.content.data[0].name=that.KtData.WaterAdjust.ReflectVar;
		             MESSAAGE1.content.data[0].value=that.form.WaterInput;}
		             console.log(MESSAAGE1)
                 //发送温度或者水阀数据
		        that.socket.emit('clientToS', MESSAAGE1);
		    },
			//停止
			rotation(){
				console.log(this.KtData.startBut.ReflectVar);
				var that=this;
				var message=that.socket.id;    

                //如果发来的数据为1就发送0(前端向服务器发送数据)
                if(that.KtData.FanState.FanState == '1'){
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
		        that.socket.emit('clientToS', MESSAAGE1);
			},
		},
	};

</script>

<style scoped lang="scss">
 @import '~scss_vars';
   .newFan{
   	background: url(../../../assets/lyzn/PAUC.jpg)  no-repeat;
   }
   .formValue{
   	width: 280px;
    padding: 12px;
    box-shadow:0 0 6px #ddd;
    border: 1px solid #ddd;
    margin-top: 20px;
    margin-left: 20px;
    .el-input{
    	width: 120px;
    }
   }
   // 排风机
   .Blowers{
   	   	margin-top: 240px;
   	   	img{
   	   	    margin-top: 40px;
   	   	}
        .fsFile{
        	position: static;
        }
   }
</style>