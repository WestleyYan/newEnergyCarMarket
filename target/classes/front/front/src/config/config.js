export default {
	baseUrl: 'http://localhost:8080/springbootksp0l7ew/',
	name: '/springbootksp0l7ew',
	indexNav: [
		{
			name: 'New Energy Vehicles',
			url: '/index/xinnengyuanqiche',
		},
		{
			name: 'Popular Cars',
			url: '/index/remenqiche',
		},
		{
			name: '汽车资讯',
			url: '/index/news'
		},
	],
	cateList: [
		{
			name: 'New Energy Vehicles',
			refTable: 'qichefenlei',
			refColumn: 'qichefenlei',
		},
		{
			name: 'Popular Cars',
			refTable: 'qichefenlei',
			refColumn: 'qichefenlei',
		},
		{
			name: '汽车资讯',
			refTable: 'newstype',
			refColumn: 'typename',
		},
	]
}
