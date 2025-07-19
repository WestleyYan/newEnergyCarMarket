<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'——'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div v-if="centerType" class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="list-preview">
			<div class="category-3">
				<div class="item" :class="swiperIndex == '-1' ? 'active' : ''" @click="getList(1, '全部')" :plain="isPlain">
					<div class="text">全部</div>
				</div>
				<div class="item" :class="swiperIndex == index ? 'active' : ''" v-for="(item, index) in fenlei" :key="index" @click="getList(1, item[feileiColumn], 'btn' + index)" :ref="'btn' + index" plain>
					<img v-if="item.image" :src="baseUrl + (item.image?item.image.split(',')[0]:'')">
					<div class="text">{{item[feileiColumn]}}</div>
				</div>
			</div>
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="list-item">
					<div class="lable">Car Name：</div>
					<el-input v-model="formSearch.qichemingcheng" placeholder="Car Name" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-form-item class="list-item">
					<div class="lable">Price：</div>
					<el-input v-model="formSearch.pricestart" placeholder="最小Price" clearable></el-input>
				</el-form-item>
				<el-form-item class="list-item">
					<el-input v-model="formSearch.priceend" placeholder="最大Price" clearable></el-input>
				</el-form-item>
				<el-button class="list-search-btn" v-if=" true " type="primary" @click="getList(1, curFenlei)">
					<i class="el-icon-search"></i>
					查询
				</el-button>
				<el-button class="list-add-btn" v-if="btnAuth('remenqiche','新增')" type="primary" @click="add('/index/remenqicheAdd')">
					<i class="el-icon-circle-plus-outline"></i>
					ADD
				</el-button>
			</el-form>
			<div class="select2">
				<div class="select2-list" v-for="(item,index) in selectOptionsList" :key="index">
					<div class="label">{{item.name}}：</div>
					<div class="item-body">
						<div class="item" @click="selectClick2(item,-1)" :class="item.check ==-1 ? 'active' : ''">全部</div>
						<div class="item" @click="selectClick2(item,index1)" :class="item.check == index1 ? 'active' : ''" v-for="item1,index1 in item.list" :key="index1">{{item1}}</div>
					</div>
				</div>
			</div>
			<div class="sort_view">
				<el-button class="price-sort-btn" @click="sortClick('price')">
					<span class="icon iconfont icon-chujia13" v-if="sortType!='price'"></span>
					<span class="icon iconfont icon-jiantou23" v-else-if="sortType=='price'&&sortOrder=='desc'"></span>
					<span class="icon iconfont icon-jiantou24" v-else-if="sortType=='price'&&sortOrder=='asc'"></span>
					<span class="text">Price</span>
				</el-button>
				<el-button class="collect-sort-btn" @click="sortClick('storeupnum')">
					<span class="icon iconfont icon-shoucang10" v-if="sortType!='storeupnum'"></span>
					<span class="icon iconfont icon-jiantou23" v-else-if="sortType=='storeupnum'&&sortOrder=='desc'"></span>
					<span class="icon iconfont icon-jiantou24" v-else-if="sortType=='storeupnum'&&sortOrder=='asc'"></span>
					<span class="text">Number of Favorites</span>
				</el-button>
			</div>
			<div class="list">
				<!-- 样式四 -->
				<div class="list list4 index-pv1">
					<div v-for="(item, index) in dataList" :key="index" @click.stop="toDetail(item)" class="list-item">
						<img @click.stop="imgPreView(item.tupian)" v-if="item.tupian && item.tupian.substr(0,4)=='http'&&item.tupian.split(',w').length>1" :src="item.tupian" class="image" />
						<img @click.stop="imgPreView(item.tupian.split(',')[0])" v-else-if="item.tupian && item.tupian.substr(0,4)=='http'" :src="item.tupian.split(',')[0]" class="image" />
						<img @click.stop="imgPreView(baseUrl + (item.tupian?item.tupian.split(',')[0]:''))" v-else :src="baseUrl + (item.tupian?item.tupian.split(',')[0]:'')" class="image" />
						<div class="content">
							<div class="infoBox">
								<div class="name">{{item.qichemingcheng}}</div>
								<div class="price"><span style="font-size: 12px">￥</span>{{item.price}}</div>
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{item.addtime.split(' ')[0]}}</span>
								</div>
								<div class="collect_item">
									<span class="icon iconfont icon-shoucang10"></span>
									<span class="label">收藏量：</span>
									<span class="text">{{item.storeupnum}}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

	
			<el-pagination
				background
				id="pagination"
				class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				prev-text="上一页"
				next-text="下一页"
				:hide-on-single-page="false"
				:layout='["total","prev","pager","next","sizes","jumper"].join()'
				:total="total"
				:page-sizes="pageSizes"
				@current-change="curChange"
				@size-change="sizeChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>
		</div>
		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>
<script>
	export default {
		//数据集合
		data() {
			return {
				selectIndex2: 0,
				selectOptionsList: [],
				layouts: '',
				swiperIndex: -1,
				baseUrl: '',
				breadcrumbItem: [
					{
						name: 'Popular Cars'
					}
				],
				formSearch: {
					qichemingcheng: '',
					price: '',
				},
				fenlei: [],
				feileiColumn: '',
				dataList: [],
				total: 1,
				pageSize: 12,
				pageSizes: [],
				totalPage: 1,
				curFenlei: '全部',
				isPlain: false,
				indexQueryCondition: '',
				timeRange: [],
				centerType:false,
				previewImg: '',
				previewVisible: false,
				sortType: 'id',
				sortOrder: 'desc',
			}
		},
		async created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0){
				this.centerType = true
			}
			this.baseUrl = this.$config.baseUrl;
			await this.getFenlei();
			let fenlei = '全部'
			if(this.$route.query.homeFenlei){
				fenlei = this.$route.query.homeFenlei
			}
			this.getList(1, fenlei);
		},
		watch:{
			$route(newValue){
				this.getList(1, newValue.query.homeFenlei);
			}
		},
		//方法集合
		methods: {
			selectClick2(row,index) {
				row.check = index
				if(index == -1){
					this.formSearch[row.tableName] = ''
				}else {
					this.formSearch[row.tableName] = row.list[index]
				}
				this.getList()
			},
			add(path) {
				let query = {}
				if(this.centerType){
					query.centerType = 1
				}
				this.$router.push({path: path,query:query});
			},
			async getFenlei() {
				await this.$http.get('qichefenlei/list',{params: {sort: 'qichefenlei',order: 'desc'}}).then(res => {
					if (res.data.code == 0) {
						this.fenlei = res.data.data.list
					}
				});
				this.feileiColumn = 'qichefenlei'
			},
			getList(page, fenlei, ref = '') {
				if(fenlei == '全部') this.swiperIndex = -1;
				for(let i=0;i<this.fenlei.length;i++) {
					if(fenlei == this.fenlei[i][this.feileiColumn]) {
						this.swiperIndex = i;
						break;
					}
				}
				if(fenlei){
					this.curFenlei = fenlei;
				}
				let params = {
					page,
					limit: this.pageSize,
					onshelves: 1
				};
				let searchWhere = {};
				if (this.formSearch.qichemingcheng != '') searchWhere.qichemingcheng = '%' + this.formSearch.qichemingcheng + '%';
				if(this.formSearch.pricestart!='' && this.formSearch.pricestart!=undefined ){
					searchWhere.pricestart = this.formSearch.pricestart
				}
				if(this.formSearch.priceend!='' && this.formSearch.priceend!=undefined){
					searchWhere.priceend = this.formSearch.priceend
				}
				if (this.curFenlei != '全部') searchWhere.qichefenlei = this.curFenlei;
				let user = JSON.parse(localStorage.getItem('sessionForm'))
				if (this.sortType) searchWhere.sort = this.sortType
				if (this.sortOrder) searchWhere.order = this.sortOrder
				this.$http.get(`remenqiche/${this.centerType?'page':'list'}`, {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.dataList = res.data.data.list;
						this.total = Number(res.data.data.total);
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			sortClick(type){
				if(this.sortType==type){
					if(this.sortOrder == 'desc'){
						this.sortOrder = 'asc'
					}else{
						this.sortOrder = 'desc'
					}
				}else{
					this.sortType = type
					this.sortOrder = 'desc'
				}
				this.getList(1, '全部')
			},
			curChange(page) {
				this.getList(page,this.curFenlei);
			},
			prevClick(page) {
				this.getList(page,this.curFenlei);
			},
			sizeChange(size){
				this.pageSize = size
				this.getList(1,this.curFenlei);
			},
			nextClick(page) {
				this.getList(page,this.curFenlei);
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			toDetail(item) {
				let params = {
					id: item.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/remenqicheDetail', query: params});
			},
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			backClick() {
				this.$router.push({path: '/index/center'});
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.list-preview {
		padding: 0 calc((100% - 1200px)/2);
		margin: 0px auto;
		color: #333;
		background: #fff;
		display: flex;
		width: 100%;
		font-size: 16px;
		justify-content: flex-start;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.category-3 {
			padding: 0;
			margin: 20px 0 0;
			background: none;
			display: flex;
			width: 100%;
			flex-wrap: wrap;
			height: auto;
			order: 3;
			.item {
				cursor: pointer;
				border: 1px solid #b3b3b3;
				padding: 0px 20px;
				margin: 0 20px 20px 0;
				color: #b3b3b3;
				display: flex;
				font-size: 16px;
				line-height: 60px;
				flex-wrap: wrap;
				border-radius: 4px;
				background: #fff;
				justify-content: center;
				align-items: center;
				min-width: 120px;
				img {
					border-radius: 100%;
					margin: 0 5px 0 0;
					object-fit: cover;
					display: block;
					width: 34px;
					height: 34px;
				}
				.text {
					color: inherit;
					font-size: inherit;
				}
			}
			.item:hover {
				color: #fff;
				background: #3397da;
				border-color: #3397da;
			}
			.item.active {
				color: #fff;
				background: #3397da;
				border-color: #3397da;
			}
		}
		.list-form-pv {
			padding: 10px;
			margin: 20px 0;
			color: inherit;
			background: none;
			display: flex;
			width: 100%;
			font-size: inherit;
			flex-wrap: wrap;
			height: auto;
			order: 2;
			.list-item {
				padding: 0;
				margin: 0 0px 10px 0;
				display: flex;
				font-size: inherit;
				align-items: center;
				flex-wrap: wrap;
				/deep/.el-form-item__content {
					display: flex;
				}
				.lable {
					padding: 0 10px;
					color: #333;
					white-space: nowrap;
					display: inline-block;
					width: auto;
					font-size: 16px;
					line-height: 40px;
				}
				.el-input {
					width: auto;
				}
				.datetimerange {
					border: 1px solid #243559;
					border-radius: 8px;
					padding: 3px 3px;
					background: #fff;
					width: auto;
					justify-content: center;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #243559;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					color: #333;
					width: auto;
					font-size: 16px;
					line-height: 40px;
					height: 40px;
				}
				.el-select {
					width: 100%;
				}
				.el-select /deep/ .el-input__inner {
				}
				.el-date-editor {
					width: auto;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #243559;
					border-radius: 4px;
					padding: 0 0px 0 30px;
					margin: 0;
					color: #333;
					width: auto;
					font-size: 16px;
					line-height: 40px;
					height: 40px;
				}
			}
			.list-search-btn {
				cursor: pointer;
				border: 0;
				border-radius: 4px;
				padding: 0px 15px;
				margin: 0 10px 0 10px;
				color: #fff;
				background: #3397da;
				width: auto;
				font-size: inherit;
				line-height: 40px;
				height: 40px;
				i {
					margin: 0 10px 0 0;
					color: #fff;
					font-size: inherit;
				}
			}
			.list-add-btn {
				cursor: pointer;
				border: 0;
				border-radius: 4px;
				padding: 0px 15px;
				margin: 0 10px 0 0;
				color: #fff;
				background: #dfb858;
				width: auto;
				font-size: inherit;
				line-height: 40px;
				height: 40px;
				i {
					margin: 0 10px 0 0;
					color: #fff;
					font-size: inherit;
				}
			}
		}
		.select2 {
			border: 0px solid #243559;
			padding: 0;
			margin: 0 auto;
			background: none;
			width: 100%;
			font-size: 15px;
			height: auto;
			order: 2;
			.select2-list {
				border: 1px solid #243559;
				border-radius: 6px;
				padding: 0 0px;
				margin: 0 0 10px;
				background: #f6f6f6;
				width: 100%;
				min-height: 70px;
				height: auto;
				.label {
					padding: 0 5px;
					margin: 0 20px 0 0;
					color: #fff;
					background: #243559;
					font-weight: 500;
					display: inline-block;
					font-size: inherit;
					line-height: 70px;
					text-align: right;
					min-width: 100px;
				}
				.item-body {
					display: inline-block;
					width: auto;
					flex-wrap: wrap;
					height: auto;
					.item {
						border-radius: 4px;
						padding: 0 5px;
						color: inherit;
						background: none;
						display: inline-block;
						font-size: inherit;
						line-height: 40px;
						text-align: center;
						min-width: 80px;
					}
					.item:hover {
						cursor: pointer;
						color: #fff;
						background: #3397da;
					}
					.item.active {
						cursor: pointer;
						color: #fff;
						background: #3397da;
						display: inline-block;
						min-width: 80px;
						text-align: center;
					}
				}
			}
		}
		.sort_view {
			border-radius: 4px;
			padding: 10px 20px 10px;
			margin: 20px auto 0;
			color: #fff;
			background: #243559;
			width: 100%;
			font-size: inherit;
			border-color: #eee;
			border-width: 0px;
			border-style: outset;
			order: 3;
			.price-sort-btn {
				border: 0;
				border-radius: 8px;
				padding: 0 5px;
				margin: 0 5px;
				color: inherit;
				background: none;
				font-size: inherit;
				.icon {
					margin: 0 2px 0 0;
					color: inherit;
					font-size: inherit;
					line-height: 40px;
				}
				.text {
					color: inherit;
					font-size: inherit;
					line-height: 40px;
				}
			}
			.collect-sort-btn {
				border: 0;
				border-radius: 8px;
				padding: 0 5px;
				margin: 0 5px;
				color: inherit;
				background: none;
				font-size: inherit;
				.icon {
					margin: 0 2px 0 0;
					color: inherit;
					font-size: inherit;
					line-height: 40px;
				}
				.text {
					color: inherit;
					font-size: inherit;
					line-height: 40px;
				}
			}
		}
		.list {
			margin: 20px 0 0;
			overflow: hidden;
			background: none;
			width: calc(100% - 0px);
			clear: both;
			font-size: 15px;
			order: 8;
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
				
			.index-pv1 .animation-box:hover {
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
				
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list4 {
				padding: 0;
				margin: 0 0 0 -10px;
				color: #fff;
				background: #fff;
				display: flex;
				width: 1220px;
				font-size: 13px;
				flex-wrap: wrap;
				height: auto;
				.list-item {
					cursor: pointer;
					margin: 20px 10px 0;
					overflow: hidden;
					background: #fff;
					width: calc(33.33% - 20px);
					position: relative;
					height: 350px;
					.image {
						object-fit: cover;
						display: block;
						width: 100%;
						transition: 0.3s;
						height: 100%;
					}
					.content {
						padding: 30px 20px;
						left: 0;
						bottom: -100%;
						background: rgba(0,0,0,.5);
						display: flex;
						width: 100%;
						position: absolute;
						transition: 0.3s;
						height: 100%;
						.desc {
							color: #fff;
							flex: 1;
							display: none;
							font-size: 14px;
							line-height: 21px;
						}
						.infoBox {
							padding: 0 0 0 10px;
							flex-direction: column;
							align-content: center;
							display: flex;
							width: 100%;
							justify-content: center;
							flex-wrap: wrap;
							height: 100%;
							.name {
								overflow: hidden;
								color: #fff;
								white-space: nowrap;
								width: 100%;
								font-size: 15px;
								line-height: 24px;
								text-overflow: ellipsis;
							}
							.price {
								color: #f00;
								font-size: 16px;
								line-height: 30px;
							}
							.time_item {
								padding: 0;
								order: 11;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.publisher_item {
								padding: 0;
								color: #3397da;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.like_item {
								padding: 0;
								color: #dfb858;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.collect_item {
								padding: 0;
								color: #3397da;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.view_item {
								padding: 0;
								color: #dfb858;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
						}
					}
				}
				.list-item:hover {
					.image {
						transform: scale(1.1);
					}
					.content {
						bottom: 0;
					}
				}
			}
		}
	}
</style>
