	<template> 
	  <el-col :span="24" class="header">
			<el-col :span="10" class="logo" >
				管理界面
			</el-col>
			<el-col :span="9" style="margin-left:30px">
				<el-input
				  placeholder="请输入搜索内容"
				  icon="search"
				  v-model="input2"
				  :on-icon-click="handleIconClick">
				</el-input>
			</el-col>
			<el-col class="userinfo" style="width: 140px">
				<el-dropdown trigger="hover">
					<span class="el-dropdown-link userinfo-inner"><img src="../assets/user.png" /> {{name}}
					</span>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item>我的消息</el-dropdown-item>
						<el-dropdown-item>设置</el-dropdown-item>
						<el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</el-col>
		</el-col>
	</template>
<script>
 export default {
   props: ['name'],
    data() {
      return {
      	input2: '',
      }
    },
    created(){
    },
    methods: {
  	  //搜索框
	    handleIconClick(ev) {
	        console.log(ev);
	    },
		//退出登录
		logout: function () {
			var _this = this;
			this.$confirm('确认退出吗?', '提示', {
				//type: 'warning'
			}).then(() => {
				sessionStorage.removeItem('name');
			   _this.$router.push('/login');
			   
			}).catch(() => {

			});

		},
      },
   }

</script>
<style scoped lang="scss">
@import '~scss_vars';
.logo{
	width: 230px;
	height: 60px;
    font-size: 22px;
    padding-left: 20px;
    padding-right: 20px;
    border-color: rgba(238, 241, 146, 0.3);
    border-right-width: 1px;
    border-right-style: solid;
}
.header .userinfo {
    float: right;
}
.header .userinfo .userinfo-inner{
    cursor: pointer;
    color: #fff;
}
.header img{
	width: 40px;
    height: 40px;
    border-radius: 20px;
    margin: 10px 0px 10px 10px;
    float: right;
}
</style>