import Vue from 'vue';
import App from './App.vue';


import VueRouter from 'vue-router';
Vue.use(VueRouter);

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);


import Login from './views/Login.vue';
import Home from './views/Home.vue';


	 import AClist from './views/AirConditioning/AClist.vue';
	 import Lobby from './views/AirConditioning/Lobby.vue';
	 import Podiumbuilding from './views/AirConditioning/Podiumbuilding.vue';

	 import Butterflybamp from './views/ChangingPlate/Butterflybamp.vue';
	 import Coldpate from './views/ChangingPlate/Coldpate.vue';

	 import Cold from './views/Cooling/Cold.vue';
	 import Butterflybamper from './views/Cooling/Butterflybamper.vue';
	 // import Water from './views/Drainage/Water.vue';
	 // import Dynamo from './views/Generator/Dynamo.vue';

	 import GenLight from './views/Lighting/GenLight.vue';
	 import Emergencylight from './views/Lighting/Emergencylight.vue';
	 import Toppanel from './views/Lighting/Toppanel.vue';
	 import Olist from './views/Other/Olist.vue';

	import Sendfan from './views/SendExhaust/Sendfan.vue';
	import BYL from './views/SendExhaust/BYL.vue';
	import Floor from './views/SendExhaust/Floor.vue';

	// import BYL from './views/Sendwater/BYL.vue';
	import Floorwater from './views/Sendwater/Floorwater.vue';
	import Equipment from './views/Sendwater/Equipment.vue';

 	import ACDatatable from './views/AirConditioning/ACDatatable.vue';
 	// import BYL from './views/SendExhaust/BYL.vue';
 	import Flood from './views/Other/Flood.vue';


const routes = [

	{ path: '*', redirect: '/login' },  /*默认跳转路由*/

	{
        path: '/login',
        component: Login,
        name: 'login'
       
    },
    {
    	path: '/home',
        component:Home,
        name: 'home',
        children: [  //这里就是二级路由的配置
	        {
	            path: 'aclist',
	            name: 'AClist',
	            component: AClist   
	        },

	        {
	            path: 'lobby',
	            name: 'Lobby',
	            component: Lobby  
	        },

	        {
	            path: 'podiumbuilding',
	            name: 'Podiumbuilding',
	            component: Podiumbuilding  
	        },


	        {
	            path: 'acDatatable',
	            name: 'ACDatatable',
	            component: ACDatatable 
	        },

	        {
	          path: 'butterflybamp',
	          name: 'Butterflybamp',
	          component: Butterflybamp
	        
	        },

	        {
	          path: 'coldpate',
	          name: 'Coldpate',
	          component: Coldpate
	        
	        },
	        {
	          path: 'cold',
	          name: 'Cold',
	          component: Cold
	        },

	        {
	          path: 'butterflybamper',
	          name: 'Butterflybamper',
	          component: Butterflybamper
	        },

	        {
	          path: 'floorwater',
	          name: 'Floorwater',
	          component: Floorwater
	        },

	        {
	          path: 'equipment',
	          name: 'Equipment',
	          component: Equipment
	        },

	        

	        {
	          path: 'Genlight',
	          name: 'GenLight',
	          component: GenLight
	        },

	        {
	          path: 'emergencylight',
	          name: 'Emergencylight',
	          component: Emergencylight
	        },

	        {
	          path: 'toppanel',
	          name: 'Toppanel',
	          component: Toppanel
	        },

	        {
	          path: 'othermain',
	          name: 'Olist',
	          component: Olist
	        },

	        {
	            path: 'flood',
	            name: 'Flood',
	            component: Flood 
	        },
	        {
	          path: 'sendfan',
	          name: 'Sendfan',
	          component: Sendfan
	        },

	        {
	          path: 'byl',
	          name: 'BYL',
	          component: BYL
	        },

	        {
	            path: 'floor',
	            name: 'Floor',
	            component: Floor 
	        }
        ]
        
    }

]





const router = new VueRouter({
    mode: 'history',   /*hash模式改为history*/
    routes // （缩写）相当于 routes: routes
})


export default router;