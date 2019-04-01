<template>
	<el-row style='height: 100%'  >
		<!-- alarm组件 chenms 2018-10-18 -->
		<alarm ></alarm>

		<!-- 场景创建显示 -->
		<div class="bg" v-if="groupChatBg"></div>
		<transition name="el-zoom-in-center">
		   <div v-if="groupChatBg" style="position:fixed;z-index:2000;background:#fff;left: 50%;
    margin-left: -250px;top:50%;margin-top:-240px;padding:12px;">
            <!-- 关闭图标 -->
            <i class="el-icon-close" @click="sceneClose"></i>
             <!--  输入聊天室名字-->
            <el-input v-model="chatName" placeholder="请输入聊天室名字" style='width: 60%;margin-left: 20px'></el-input>

		      <!-- 选择添加到群聊的用户 -->
		      <div  class="bgTransfer">
		        <el-transfer v-model="value2" :data="data2"
		           filterable
		           filter-placeholder="请输入用户名字"></el-transfer>
		        <el-button type="primary" @click="screneSure">确定</el-button>
		      </div>
		    </div>
		   </transition>  
		<!-- 添加群聊结束 -->
        <el-col  class='homeBox'>
			<el-col :span="24" class="boxlogo">
				<img src="../../static/mainlogo.jpg" >
			</el-col>
		  <div @click="classTrue">
		    <router-link :to="{ path: '/' }" :class="classStyle?'is-active':''" >
				<el-col :span="24" class="boxlogo icon">
					<i class="iconfont">&#xe7a3;</i>
					<div class="tootip">智能化</div>
				</el-col>
			</router-link>
		  </div>
			<!-- 聊天系统 -->
		 <!--  <div @click="classFalse">
			<router-link :to="{ path: 'notic' }">
				<el-col :span="24" class="boxlogo icon sx_icon">
				  <el-badge :value="1" v-show="showBadge"></el-badge>
				   <i class="iconfont" style="font-size:34px">&#xe503;</i>
				   <div class="tootip">消息</div>
				</el-col>
			</router-link>
		 </div> -->
		 <!-- 用户管理权限图标 -->
		 <div @click="classFalse">
		    <router-link :to="{ path:'admin' }"  v-if="admin=='admin'">
				<el-col :span="24" class="boxlogo icon">
					<i class="iconfont">&#xe503;</i>
					<div class="tootip" style="width: 64px">权限管理</div>
				</el-col>
			</router-link>
			<el-col :span="24" class="boxlogo icon" v-else>
			        <i class="iconfont" style="color:red">&#xe503;</i>
					<div class="tootip" style="width: 64px">无权限</div>
			</el-col>
		 </div>
		  <!-- 报警系统 -->
		  <div @click="classFalse">
			<router-link :to="{ path: 'Alarm' }">
				<el-col :span="24" class="boxlogo icon sx_icon">
				   <i class="iconfont" :style="{color:alramColor?'red':''}">&#xe63d;</i>
				   <div class="tootip" style="width: 64px">报警</div>
				</el-col>
			</router-link>
		 </div>

		  <el-dropdown trigger="click" placement="top-end">
		   	 <span class="el-dropdown-link icon">
		        <i class=" iconfont moreIcon">&#xe607;</i>
		      </span>
	      <el-dropdown-menu slot="dropdown">
	        <el-dropdown-item divided>个人设置</el-dropdown-item>
	        <el-dropdown-item divided  @click.native="groupChat">创建群聊</el-dropdown-item>
	        <el-dropdown-item divided>系统设置</el-dropdown-item>
	      </el-dropdown-menu>
	    </el-dropdown>
		</el-col>
		<el-row class="contain" :span="22">
			<router-view></router-view>
		</el-row>
   </el-row>
</template>

<script>
import { getSession} from '../common/js/util';
import util from '../common/js/util';

import { getBaseInfo,getAllUser } from '../service/getData';
import {mapState} from 'vuex';
import alarm from '../views/AlarmCall/alarm_pub';
import { getAlarm,insertAlarm } from '../service/alarmDao';

	export default {
		components:{alarm},

		data() {
	      return {
	      	socket:'',
	      	alramColor:false,
	      	// 用户权限
	      	admin:'',
	      	chatName:'',
	      	// 聊天列表
	        friendName:[],
	        // 所有普用户
	        data2: [],
	        // 已选择用户
	        value2: [],
	        groupChatBg:false
	       //alarmData:[]

	      };
        },
		beforeRouteEnter(to, from, next) {
		    //如果不存在用户返回登录
		    if (getSession('name')==''||getSession('name')==null) {
		        next('/login')
		    } else {
		    	console.log(getSession('name'))
		        next();
		    }
		  },
		  beforeRouteLeave(to, from, next){
		      this.socket.disconnect();
		      next()
		     },
		 created(){
		 	   var that=this;
		 	   // 报警设置
     		   setInterval(function(){that.alramColor=!that.alramColor},500);
		 	   // 获取用户权限
		 	   that.admin=JSON.parse(getSession('UserInfo')).admin
		 	   // 提交用户权限
		 	   that.$store.commit('userAdmin',that.admin );
		 	   console.log(that.admin)
		 	   // 获取所有用户
		 	   getAllUser(res=>{
		 	   	  that.data2=res.data;
		 	   	  res.data.forEach(item=>{
		 	   	  	// 第一次登陆接收其它成员信息
				    that.friendName.push({username:item.name,msgs:[],newTip:false,group:false})
		 	   	  });
		 	   	  // 提交所有用户
		 	   	  that.$store.commit('friendNames',that.friendName );
		 	   });

		 	   	//查询照明数据（楼层信息？）并赋值：pagemessage表，用来干什么的？？？


		 	   
		       getBaseInfo(getSession('name'),res =>{
		       	var that=this;
                  //赋值
			     this.$store.commit('setfloorInfon',res.data );
			      //websocket连接
				     this.socket = io.connect('http://120.77.243.32:8090');
				     //this.socket = io.connect('http://192.168.1.103:8090');
				      
				    // 提交socket 聊天系统用
				    this.$store.commit('savaSocket',this.socket);
				    this.socket.on('connect', function () {
				        //发送用户名
				         that.socket.emit('new user',getSession('name'));
				      });
				        // 第一次登陆接收其群组信息
				        this.socket.on('login', function (msg) {
				        	console.log(msg)
				           // 添加群聊
				    	   that.friendName.push(JSON.parse(msg))
						   // 提交到vuex
						   that.$store.commit('friendNames',that.friendName );
				       });
				         // 第一次登陆接收一对一用户私聊离线信息
				        this.socket.on('sigle_login', function (msg) {
				        	console.log("离线信息")
				            console.log(msg)
				        	for (var i = 0; i < msg.length; i++) {
				        		console.log(msg[i])
				        		console.log(that.friendName)
				        		that.friendName.forEach(friend=>{
				        	  	 // 如果发来用户名字和列表名字相等就添加数据
				        		 if(msg[i].addresser.addresser==friend.username){
                                   // 添加私聊离线信息
						    	   friend.msgs.push(msg[i].addresser)
								   // 提交到vuex
								   that.$store.commit('friendNames',that.friendName );
				        		 }
				        	   })
				        	}		          
				       });
                        
				        // 收到广播消息 群聊系统
				        this.socket.on('message', function(msg){
				        	console.log("收到数据")
				        	console.log(msg)
				          that.friendName.forEach(item=>{
				          	// 如果是自己就不添加信息
				          	if(msg.addresser!=getSession('name')){
					          	// 添加信息到其他群组成员
	                              if(item.username==msg.recipient){
					         	 	// 添加信息到数据中
	                                item.msgs.push(msg);
	                         
				          	  }
				          	}
				         })
				        });
				        // 添加群聊的数据
				        this.socket.on('addGroup', function(group){
						   // 添加群聊
				    	   that.friendName.push(group)
						   // 提交到vuex
						   that.$store.commit('friendNames',that.friendName );
				        });
				        // 监听中途的成员加入
				        this.socket.on('user joined', function (tname) {
				          console.log("用户加入"+tname)
				        });
				          // 接收私聊信息
					    this.socket.on('receive private message', data => {
					    	// 不在聊天页面提示信息图标
					      if(that.classStyle == true){
					         that.$store.commit('showBadges',true );
					         that.friendName.forEach((item,index) =>{
					          //找到发送对象添加数据
					           if(item.username == data.addresser){
					               //添加数据
					               item.newTip=true;
					           }
					        });
					      }
					      console.log(data)
					      if(data.addresser == data.recipient){
					         console.log("不能给自己发信息")
					         return
					      }
					      that.friendName.forEach((item,index) =>{
					          //找到发送对象添加数据
					           if(item.username == data.addresser){
					               //添加数据
					               item.msgs.push(data);
					               // 换行显示消息
					           // setTimeout(() => this.oContent.scrollTop = this.oContent.scrollHeight , 0);
					           }
					        });
					        that.$store.commit('friendNames',that.friendName );
					    });
				      // 监听中途的成员离开
				    this.socket.on('user left', function (tname) {
				        //列表中删除用户
				         console.log("用户离开"+tname)
				      });		     
		      });

		       		 }, 
		 //在页面渲染出来后触发 chenms 2018-10-17
		 mounted() {
		 	var that = this;
		 	//这个是钩子函数
		 	//应该注意的是，使用mounted 并不能保证钩子函数中的 this.$el 在 document 中。为此还应该引入Vue.nextTick/vm.$nextTick
			this.$nextTick(function () {
			     //查询报警表数据
			     //console.log("查询报警表数据12");
			     //alarm.methods.qryAlarmData("2018-09-01","");
			     var nowDate = util.formatDate.format(new Date());
			     getAlarm(nowDate,"",res=>{
				   console.log("查询报警表数据为：",res.data);
				   
		     	   //存储报警数据
				   this.$store.commit('setAlarmData',res.data);
				   console.log("存储报警数据完成",that.$store.state.alarmData);
		           //发出或停止报警声音
		           alarm.methods.playOrStopAlarmSound(that.$store.state.alarmData); //不能写在这里，报错 .bind(this)

		           //订阅报警信息
			       this.socket.on('Alarm', function (data) {
			          //监听报警信息
			          console.log("接收到websocket报警信息",data);
			          /*示例：
						{        
						        "type":"Alarm",
							"Alarmtime": "2018:04:13:16:30:41",
							"Level": "1",
							"System": "BA",
							"Data": {
								"pointid": "100000000000000209",
								"pointname": "风机过滤网",
								"alarmvalue": "1",
								"alarmcontent": "风机过滤网报警"
							}
						}*/
			          //插入数据进报警表
			          var date = data[0].Alarmtime;
			          var reflectvar = data[0].Data.pointid;
			          var system = data[0].System;
			          var AlarmLevel = data[0].Level;
			          var problem = data[0].Data.alarmcontent;
			          insertAlarm(date,reflectvar,system,AlarmLevel,problem,res=>{
			          	console.log("插入数据进报警表成功，res.data数据为：",res.data);
			          	//插入存储区alarmData，不知可否可以试下？已试过直接修改alarmData有效。【unshift貌似IE8后有效，指在数组前头加元素】
			          	that.$store.state.alarmData.unshift(res.data[0]);
			          	//发出报警声音
			          	alarm.methods.playOrStopAlarmSound(that.$store.state.alarmData);
			          });

			       });
		        });
			 });


		    }, 
		 computed: {
			  ...mapState([
			      'classStyle','showBadge'
			    ])
           },
          methods: {
		      //关闭场景
		      sceneClose(){
		          this.groupChatBg=false
		      },
          	 //显示添加群组
          	 groupChat() {
          	 	//显示背景
          	 	this.groupChatBg=true;
          	 	//定义用户数据
          	 	const generateData2 = _ => {
		        const data = [];
		        const cities = this.data2;
		        const pinyin = this.data2;
		        cities.forEach((city, index) => {
		          data.push({
		            label: city.name,
		            key: index,
		            pinyin: pinyin[index]
		          });
		        });
		        return data;
              };
              this.data2=generateData2()
		        
		    },
		    // 创建群聊按钮
		    screneSure(){
		    	var that=this;
		    	let data=[];
               // 判断是否为空
		    	if(this.chatName==''){
                   that.$message({
                    type: 'error',
                    message: '名字不能为空 '
                  });
		    	}else if(that.value2.length<=0){
                   that.$message({
                    type: 'error',
                    message: '用户不能为空 '
                  });
		    	}else{
		    		this.value2.forEach((item)=>{
                       data.push(that.data2[item].label)
				    })
				    let group={ 
				    	username:that.chatName ,
		    	   	  	msgs:[],newTip:false,
		    	   	  	group:true,
		    	   	  	// 群聊用户名字
		    	   	  	chatName:data 
		    	   	  }
				   // 发送创建群组信息
				    this.socket.emit('join', {
			            group: group
			        });
				   that.groupChatBg=false;
		    	}
		    },
		    //全局导航样式
           	classFalse() {
                this.$store.commit('classStyle',false);
                this.$store.commit('showBadges',false );
           	},
           	classTrue() {
                this.$store.commit('classStyle',true);
           	},
           }	
	    }

</script>

<style scoped lang="scss">
	@import '~scss_vars';
	//@import '/static/style/common.css';
	 .is-active{
       color: #20a0ff!important;
      }
	.homeBox{
		background:#26292D;
		height: 100%;
		width:75px;
		position:relative;
		z-index: 2000;
	}
	.boxlogo{
		background:#fff;
		width: 50%;
		margin-left: 25%;
		margin-top: 14px;
		border-radius:4px;
		margin-bottom: 12px;
		img{
			width: 80%;
			padding: 10%;
			padding-top: 6px;
			padding-bottom: 6px;
		}

	}
	.icon{
		position:relative;
		background:none;
		width: 50%;
		margin-left: 25%;
		.tootip{
			position:absolute;
			left: 40px;
			top:6px;
			width: 56px;
			height: 34px;
			line-height: 34px;
			box-shadow: 2px 2px 2px #9e9e9e;
			text-align: center;
			background:#fff;
			display: none;
			border-radius:4px;
		}

	}
	.icon:hover{
		.tootip{
			display:block;
		}
	}
	.contain {
		position: absolute;
		top: 0px;
		bottom: 0px;
		left: 75px;
		width: 94%;
	
	}
	.el-button-group .el-button:first-child{
		border: 0;
		color: #fff;
		background:none;
	}
	 .el-dropdown {
       position: absolute;
       bottom: 24px;
       right: 22px;
       .moreIcon{
       	 color: gray;
       	 font-size:30px;
       	 cursor: pointer;
       }
    }
     .el-dropdown-menu{
       	padding:0;
       	border-radius:4px;
       	text-align: center;
       	font-size:12px;
       }
	  .el-dropdown-menu__item--divided{
       	margin-top:0;
       }
    .el-icon-close{
      float: right;
      margin-top: 13px;
      margin-right: 5px;
  }
</style>