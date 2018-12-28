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
	 import AClist_pau1 from './views/AirConditioning/AClist_pau1.vue';
	 import AClist_pau2 from './views/AirConditioning/AClist_pau2.vue';
	

	 import AClist_ahu from './views/AirConditioning/AClist_ahu.vue';
	 
	 import Podiumbuilding from './views/AirConditioning/Podiumbuilding.vue';
	 	import waterfa from './components/waterfa.vue';
	 	import dev_tab from './components/dev_tab.vue';

	 import ACfloor from './views/AirConditioning/ACfloor.vue';
	
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
	import CSendfan from './views/SendExhaust/CSendfan.vue';
	import BYL from './views/SendExhaust/BYL.vue';
	import Floor from './views/SendExhaust/Floor.vue';

	// import BYL from './views/Sendwater/BYL.vue';
	import Floorwater from './views/Sendwater/Floorwater.vue';
	import Equipment from './views/Sendwater/Equipment.vue';

 	import ACDatatable from './views/AirConditioning/ACDatatable.vue';
 	import ACAirDatatable from './views/AirConditioning/ACAirDatatable.vue';
 	
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
	            path: 'aClist_pau1',
	            name: 'AClist_pau1',
	            component: AClist_pau1   
	        },

	        {
	            path: 'aClist_pau2',
	            name: 'AClist_pau2',
	            component: AClist_pau2   
	        },

	       

	        {
	            path: 'AClist_ahu',
	            name: 'AClist_ahu',
	            component: AClist_ahu  
	        },

	        

	        {
	            path: 'podiumbuilding',
	            name: 'Podiumbuilding',
	            component: Podiumbuilding,
	            children:[
			      { path: 'waterfa', component: waterfa },
			      { path: 'devtab', component: dev_tab }
			    ]  
	        },

	        {
	            path: 'acDatatable',
	            name: 'ACDatatable',
	            component: ACDatatable 
	        },

	        {
	            path: 'ACAirDatatable',
	            name: 'ACAirDatatable',
	            component: ACAirDatatable 
	        },


	        {
	            path: 'acfloor',
	            name: 'ACfloor',
	            component: ACfloor 
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
	          path: 'Csendfan',
	          name: 'CSendfan',
	          component: CSendfan
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