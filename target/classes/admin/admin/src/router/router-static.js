import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import news from '@/views/modules/news/list'
	import qichefenlei from '@/views/modules/qichefenlei/list'
	import yonghu from '@/views/modules/yonghu/list'
	import chat from '@/views/modules/chat/list'
	import discussxinnengyuanqiche from '@/views/modules/discussxinnengyuanqiche/list'
	import orders from '@/views/modules/orders/list'
	import discussremenqiche from '@/views/modules/discussremenqiche/list'
	import remenqiche from '@/views/modules/remenqiche/list'
	import config from '@/views/modules/config/list'
	import xinnengyuanqiche from '@/views/modules/xinnengyuanqiche/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: 'System Homepage',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: 'System Homepage',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: 'UPDATE密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '汽车资讯',
		component: news
	}
	,{
		path: '/qichefenlei',
		name: 'Car Categories',
		component: qichefenlei
	}
	,{
		path: '/yonghu',
		name: 'User',
		component: yonghu
	}
	,{
		path: '/chat',
		name: '在线客服',
		component: chat
	}
	,{
		path: '/discussxinnengyuanqiche',
		name: 'New Energy Vehicles评论',
		component: discussxinnengyuanqiche
	}
	,{
		path: '/orders/:status',
		name: 'Order Management',
		component: orders
	}
	,{
		path: '/discussremenqiche',
		name: 'Popular Cars评论',
		component: discussremenqiche
	}
	,{
		path: '/remenqiche',
		name: 'Popular Cars',
		component: remenqiche
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/xinnengyuanqiche',
		name: 'New Energy Vehicles',
		component: xinnengyuanqiche
	}
	,{
		path: '/newstype',
		name: '汽车资讯分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//UPDATE原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
