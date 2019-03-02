import Vue from 'vue'
import App from './App.vue'

import $ from 'jquery'

import VueRouter from 'vue-router';
Vue.use(VueRouter);

import axios from 'axios';

window.axios = require('axios');
window.axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest'
Vue.prototype.$http = window.axios


// import htmlToPdf from '@/components/utils/htmlToPdf'
// Vue.use(htmlToPdf)

import Home from './components/home.vue'
import Allorders from './components/allorders.vue'
import Storehouse from './components/storehouse.vue'
import Sys from './components/sys.vue'
import Order from './components/order.vue'
import Login from './components/login.vue'
// import storehouserouter from './components/storehouserouter.vue'

	import Maintain from './components/order/maintain.vue'
	import AllOrder from './components/order/allorders.vue'
	import Tforder from './components/order/24order.vue'
	import Pendingorder from './components/order/pendingorder.vue'
	import Tobereceorder from './components/order/tobereceorder.vue'
	import Tranorder from './components/order/tranorder.vue'
	import Cancelorder from './components/order/cancelorder.vue'
	import Refundorder from './components/order/refundorder.vue'
	import Wastageorder from './components/order/wastageorder.vue'
	import Completedorder from './components/order/completedorder.vue'
	import Cancorder from './components/order/cancorder.vue'
	import Returngoodsorder from './components/order/returngoodsorder.vue'
	import Label from './components/order/label.vue'
	import Returngoodsreson from './components/order/returngoodsreson.vue'

	import tocollected from './components/storehouse/tocollected.vue'
	import check from './components/storehouse/check.vue'
	import Alreadyprinted from './components/storehouse/Alreadyprinted.vue'
	import Intransportation from './components/storehouse/Intransportation.vue'
	import Returngoods from './components/storehouse/Returngoods.vue'
		import package_news from './components/storehouse/package_news.vue'
		import packagenewfive from './components/storehouse/packagenewfive.vue'
		import packagenewfour from './components/storehouse/packagenewfour.vue'
		import packagenewone from './components/storehouse/orderPackages.vue'
		import packagenewseven from './components/storehouse/packagenewseven.vue'
		import packagenewsix from './components/storehouse/packagenewsix.vue'
		import packagenewthree from './components/storehouse/packagenewthree.vue'
		import packagenewtwo from './components/storehouse/packagenewtwo.vue'


	import CompanyInformation from './components/sys/CompanyInformation.vue'
	import mainsys from './components/sys/mainsys.vue'
	import Purchase from './components/sys/Purchase.vue'
	import role from './components/sys/role.vue'
	import staff from './components/sys/staff.vue'
	import shopping from './components/sys/shopping.vue'
	


import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

const routes = [
	{ path: '/home', component: Home },
	{ path: '/login', component: Login },
	{ 
		path: '/order', 
		component: Order,
		children:[
			{ path: 'maintain', component: Maintain },
			{ path: 'allorders', component: AllOrder },
			{ path: 'tforder', component: Tforder },
			{ path: 'pendingorder', component: Pendingorder },
			{ path: 'tobereceorder', component: Tobereceorder },
			{ path: 'tranorder', component: Tranorder },
			{ path: 'cancelorder', component: Cancelorder },
			{ path: 'refundorder', component: Refundorder },
			{ path: 'wastageorder', component: Wastageorder },
			{ path: 'completedorder', component: Completedorder },
			{ path: 'cancorder', component: Cancorder },
			{ path: 'returngoodsorder', component: Returngoodsorder },
			{ path: 'label', component: Label },
			{ path: 'returngoodsreson', component: Returngoodsreson }
		]
	},
	{ 
		path: '/storehouse', 
		component: Storehouse,
		children:[
			
			{   path: 'check', 
				component: check
				/*children:[
					{ path: 'package_news', component: package_news },
					{ path: 'packagenewfive', component: packagenewfive },
					{ path: 'packagenewfour', component: packagenewfour },
					{ path: 'packagenewone', component: packagenewone },
					{ path: 'packagenewseven', component: packagenewseven },
					{ path: 'packagenewsix', component: packagenewsix },
					{ path: 'packagenewthree', component: packagenewthree },
					{ path: 'packagenewtwo', component: packagenewtwo }
				]*/

			 },
			{ path: 'Alreadyprinted', component: Alreadyprinted },
			{ path: 'tocollected', component: tocollected },
			{ path: 'Intransportation', component: Intransportation },
			{ path: 'Returngoods', component: Returngoods }
		
		]
	},

	{ 
		path: '/sys', 
		component: Sys,
		children:[
			{ path: 'CompanyInformation', component: CompanyInformation },
			{ path: 'mainsys', component: mainsys },
			{ path: 'Purchase', component: Purchase },
			{ path: 'role', component: role },
			{ path: 'shopping', component: shopping },
			{ path: 'staff', component: staff }
			
		
		]
	},

	{ path: '*', redirect: '/login' }   /*默认跳转路由*/
]

const router = new  VueRouter({
	routes: routes
})

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
