<template>
		<el-row class="container" :span="24">
	   <!-- top组件 -->
		<scren :name="name" ></scren>
       <!-- 导航栏 -->
		<el-col :span="24" class="main">
			<aside :class="collapsed?'menu-collapsed':'menu-expanded'">
				<!--导航菜单-->
				<el-menu :default-active="$route.path" class="el-menu-vertical-demo" @select="handleselect"
					 unique-opened router v-show="!collapsed">
					<template v-for="(item,index) in $router.options.routes[2].children" v-if="!item.hidden">
						<el-submenu :index="index+''" v-if="!item.leaf">
							<template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>
							<el-menu-item v-for="child in item.children" :index="child.path" :key="child.path" v-if="!child.hidden">{{child.name}}</el-menu-item>
						</el-submenu>
					
					</template>
				</el-menu>
			</aside>
			<!-- 面包屑导航 -->
			<section class="content-container">
				<div class="grid-content bg-purple-light">
					<el-col :span="24" class="content-wrapper">
						<transition name="fade" mode="out-in">
							<router-view></router-view>
						</transition>
					</el-col>
				</div>
			</section>
		</el-col>
	</el-row>
</template>

<script>
  import {setStore, getStore,getSession} from '../common/js/util'
  import scren from '../components/top';
  import {mapState} from 'vuex'
	export default {
        components:{scren},
		data() {
			return {
				alramColor:false,
				collapsed:false,
				name:'',
			}
		},
		created(){
			var that=this;
	        this.name=getSession('name');
		},
		methods: {
			handleselect: function () {
                 this.$store.commit('classStyle',true );
			},

			//折叠导航栏
			collapse:function(){
				this.collapsed=!this.collapsed;
			},
			showMenu(i,status){
				this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-'+i)[0].style.display=status?'block':'none';
			},
		},
	}

</script>

<style scoped lang="scss">
	@import '~scss_vars';
	
</style>