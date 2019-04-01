import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Alarm from './views/AlarmCall/alarm.vue'
import znh from './views/znh.vue'
import notic from './views/notic.vue'
import admin from './views/admin/admin.vue'
import Main from './views/Main.vue'
import Blowers from './views/nav1/Blowers/floor.vue'
import newFan from './views/nav1/newFan/floor.vue'
import slushF from './views/nav1/slushPump/floor.vue'

import BASystem from './views/nav1/Blowers/BASystem.vue'
import Blo from './views/nav1/Blowers/kt.vue'
import Fan from './views/nav1/newFan/BASystem.vue'
import FanKt from './views/nav1/newFan/kt.vue'
import slush from './views/nav1/slushPump/BASystem.vue'
import slushBASystem from './views/nav1/slushPump/slush.vue' 

import Page4 from './views/nav2/Page4.vue'
import Page5 from './views/nav2/Page5.vue'
import dayTable from './views/dayTable/dayTable.vue'

let routes = [
        {
            path: '/login',
            component: Login,
            name: 'login',
            hidden: true
        },
        {
            path: '/404',
            component: NotFound,
            name: '',
            hidden: true
        },
       {
        path: '/',
        component: Home,
        children: [
             {
                path: '/notic',
                component: notic,
                hidden: true,
           },
           {
                path: '/admin',
                component: admin,
                hidden: true,
           },
           {   
                path: '/',
                component: znh,
                name: '楼宇智能系统',
                iconCls: 'el-icon-message',//图标样式class
                children: [
                    { path: '/', component: Main,  hidden: true },
                    { path: '/Blowers', component: Blowers, name: '空调系统' },
                    { path: '/BASystem', component: BASystem, name: '空调系统2' ,hidden: true},
                    { path: '/Blo', component: Blo, name: '空调系统1',hidden: true },
                    { path: '/newFan', component: newFan, name: '排风系统' },
                    { path: '/Fan', component: Fan, name: '排风机2' ,hidden: true},
                    { path: '/FanKt', component: FanKt, name: '排风机1',hidden: true },
                    { path: '/slushF', component: slushF, name: '污水泵系统' },
                    { path: '/slush', component: slush, name: '污水泵2' ,hidden: true},
                    { path: '/slushBASystem', component: slushBASystem, name: '污水泵1',hidden: true },
                ]
            },
            {
                path: '/',
                component: znh,
                name: '智能照明系统',
                iconCls: 'fa fa-id-card-o',
                children: [
                    { path: '/page4', component: Page4, name: '照明系统' },
                    { path: '/page5', component: Page5, name: '给排水',hidden: true  }
                ]
            },
             {
                path: '/',
                component: znh,
                name: '节假日设置',
                iconCls: 'fa fa-bar-chart',
                children: [
                    { path: '/dayTable', component: dayTable, name: '节假日设置' }
                ]
            },
            {
                path: '/Alarm',
                component: Alarm,
                hidden: true,
            }
                ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;