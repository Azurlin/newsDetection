import Login from './views/Login.vue'
import Register from './views/Register.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Table from './views/nav1/Table.vue'
//import Form from './views/nav1/Form.vue'
//import user from './views/nav1/user.vue'
//import Page4 from './views/nav2/Page4.vue'
//import Page5 from './views/nav2/Page5.vue'
//import Page6 from './views/nav3/Page6.vue'
import echarts from './views/charts/echarts.vue'
import mainPage from './views/mainPage/mainPage.vue' 
import admin from './views/admin/admin.vue'
import indexnews from './views/admin/nav/indexnews.vue'
import indexnewsManager from './views/admin/nav/indexnewsManager.vue'
import index from './views/index/index.vue' 
import news from './views/index/news.vue' 

let routes = [
    {
        path:'/Register',
        component: Register,
        name:'Register',
        meta: {keepAlive: false}
    },
    {
        path:'/Login',
        component: Login,
        name:'Login',
        meta: {keepAlive: false}
    },
    {
        path:'/',
        component: index,
        name:'index',
        meta: {keepAlive: true}
    },
    {
        path: '/admin',
        component: admin,
        name: '',
        hidden: true,
        children: [
            { 
                path: '/indexnews', 
                component: indexnews, 
                name: '', 
                meta: {keepAlive: false}
             },
            { 
                path: '/indexnewsManager', 
                component: indexnewsManager, 
                name: '',
                meta: {keepAlive: false} 
            },
                
        ]
       , meta: {keepAlive: false}
        
    },
    {
        path: '/mainPage',
        component: mainPage,
        name: '',
        hidden: true
        , meta: {keepAlive: false}
    },
    {
        path: '/news/:id',
        component: news,
        name: '',
        hidden: true
        , meta: {keepAlive: false}
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
        , meta: {keepAlive: false}
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        
        component: Home,
        name: '新闻数据管理',
        iconCls: 'fa fa-archive',//图标样式class
        children: [
            { 
            path: '/table/:id', 
            component: Table, 
            name: '数据管理' 
            , meta: {keepAlive: false}
        },
            { 
            path: '/echarts/:id', 
            component: echarts, 
            name: '图表' 
            , meta: {keepAlive: false}
        }
        ]
        , meta: {keepAlive: false}
    },

    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
        , meta: {keepAlive: false}
    }
];

export default routes;