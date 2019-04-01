<template>
	<div id="app">
		<transition name="fade"
		            mode="out-in">
			<router-view></router-view>
		</transition>
	</div>
</template>

<script>
export default {
	name: 'app',
}
</script>

<style lang="scss">
body {
	margin: 0px;
	padding: 0px;
	font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
	font-size: 14px;
	-webkit-font-smoothing: antialiased;
}
#app {
	position: absolute;
	top: 0px;
	bottom: 0px;
	width: 100%;
}
.el-submenu [class^=fa] {
	vertical-align: baseline;
	margin-right: 10px;
}
.el-menu-item [class^=fa] {
	vertical-align: baseline;
	margin-right: 10px;
}
.toolbar {
	background: #f2f2f2;
	padding: 10px;
	//border:1px solid #dfe6ec;
	margin: 10px 0px;
	.el-form-item {
		margin-bottom: 10px;
	}
}
// 离开 进入动画
.fade-enter-active,.fade-leave-active {
	transition: all 0.4s ease;
}
.fade-enter,.fade-leave-active {
	opacity: 0;
}
// 离开 进入动画结束
.el-input{
	font-size:12px;
}
 .el-input-group{
	padding: 4px 0px;
    border: 2px solid #eef1f6;
    background:#fbfdff;
    .el-input-group__prepend,.el-input-group__append{
	border: 0!important;
	padding-left: 8px;
	padding-right: 8px;
   }
    .el-input__inner{
    	height: 28px;
    	border-radius: 0;
    	width: 60px;
    }
}
//添加群聊
.bgTransfer{
   width: 500px;
   height: 360px;
   background: #fff;
   margin:0 auto;
   padding:24px;
}
.el-transfer {
    margin-bottom: 16px;
}
//背景显示
.bg{
	width: 100%;
	height: 100%;
	background:rgba(0,0,0,0.4);
	position:fixed;top:0;left:0;
	z-index: 1999
}
.el-button+.el-button {
    margin-left: 0px!important;
}
</style>