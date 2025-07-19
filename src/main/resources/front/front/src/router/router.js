import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import AddrList from '../pages/shop-address/list'
import AddrAdd from '../pages/shop-address/addOrUpdate'
import Order from '../pages/shop-order/list'
import OrderConfirm from '../pages/shop-order/confirm'
import Cart from '../pages/shop-cart/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import qichefenleiList from '../pages/qichefenlei/list'
import qichefenleiDetail from '../pages/qichefenlei/detail'
import qichefenleiAdd from '../pages/qichefenlei/add'
import xinnengyuanqicheList from '../pages/xinnengyuanqiche/list'
import xinnengyuanqicheDetail from '../pages/xinnengyuanqiche/detail'
import xinnengyuanqicheAdd from '../pages/xinnengyuanqiche/add'
import remenqicheList from '../pages/remenqiche/list'
import remenqicheDetail from '../pages/remenqiche/detail'
import remenqicheAdd from '../pages/remenqiche/add'
import chargerecordList from '../pages/chargerecord/list'
import chargerecordDetail from '../pages/chargerecord/detail'
import chargerecordAdd from '../pages/chargerecord/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import discussxinnengyuanqicheList from '../pages/discussxinnengyuanqiche/list'
import discussxinnengyuanqicheDetail from '../pages/discussxinnengyuanqiche/detail'
import discussxinnengyuanqicheAdd from '../pages/discussxinnengyuanqiche/add'
import discussremenqicheList from '../pages/discussremenqiche/list'
import discussremenqicheDetail from '../pages/discussremenqiche/detail'
import discussremenqicheAdd from '../pages/discussremenqiche/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
                {
                    path: 'shop-address/list',
                    component: AddrList
                },
                {
                    path: 'shop-address/addOrUpdate',
                    component: AddrAdd
                },
				{
					path: 'shop-order/order',
					component: Order
				},
				{
					path: 'cart',
					component: Cart
				},
				{
					path: 'shop-order/orderConfirm',
					component: OrderConfirm
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'qichefenlei',
					component: qichefenleiList
				},
				{
					path: 'qichefenleiDetail',
					component: qichefenleiDetail
				},
				{
					path: 'qichefenleiAdd',
					component: qichefenleiAdd
				},
				{
					path: 'xinnengyuanqiche',
					component: xinnengyuanqicheList
				},
				{
					path: 'xinnengyuanqicheDetail',
					component: xinnengyuanqicheDetail
				},
				{
					path: 'xinnengyuanqicheAdd',
					component: xinnengyuanqicheAdd
				},
				{
					path: 'remenqiche',
					component: remenqicheList
				},
				{
					path: 'remenqicheDetail',
					component: remenqicheDetail
				},
				{
					path: 'remenqicheAdd',
					component: remenqicheAdd
				},
				{
					path: 'chargerecord',
					component: chargerecordList
				},
				{
					path: 'chargerecordDetail',
					component: chargerecordDetail
				},
				{
					path: 'chargerecordAdd',
					component: chargerecordAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'discussxinnengyuanqiche',
					component: discussxinnengyuanqicheList
				},
				{
					path: 'discussxinnengyuanqicheDetail',
					component: discussxinnengyuanqicheDetail
				},
				{
					path: 'discussxinnengyuanqicheAdd',
					component: discussxinnengyuanqicheAdd
				},
				{
					path: 'discussremenqiche',
					component: discussremenqicheList
				},
				{
					path: 'discussremenqicheDetail',
					component: discussremenqicheDetail
				},
				{
					path: 'discussremenqicheAdd',
					component: discussremenqicheAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
