<template>
	<div>
	<!--  -->
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'——'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" :to="'/index/qichefenlei?centerType=' + (centerType?'1':'0')"><a>{{item.name}}</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item3"><a href="javascript:void(0);">详情</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="detail-preview">
			<div class="attr">
				<div class="info">
					<div class="title-item">
						<div class="detail-title">
						</div>
					</div>
					<div class="item">
						<div class="lable">Car Categories</div>
						<div class="text "  >{{detail.qichefenlei}}</div>
					</div>
					<div class="btn_box">
						<el-button class="editBtn" v-if="btnAuth('qichefenlei','UPDATE')" @click="editClick">UPDATE</el-button>
						<el-button class="delBtn" v-if="btnAuth('qichefenlei','DELETE')" @click="delClick">DELETE</el-button>
					</div>
				</div>
			</div>
		
			<div class="detail-swpier2" v-if="detailBanner.length">
				<div class="swiper21">
					<div class="swiper-container mySwiper21">
						<div class="swiper-wrapper">
							<div class="swiper-slide" v-for="item in detailBanner" :key="item.id">
								<div class="swiper-item">
									<img v-if="item.substr(0,4)=='http'" :src="item" class="image">
									<img v-else :src="baseUrl + item" class="image">
								</div>
							</div>
						</div>
						<div class="swiper-button-prev">
							<span class="icon iconfont icon-jiantou39"></span>
						</div>
						<div class="swiper-button-next">
							<span class="icon iconfont icon-jiantou18"></span>
						</div>
					</div>
				</div>
				<div class="swiper22">
					<div class="swiper-container mySwiper22">
						<div class="swiper-wrapper">
							<div class="swiper-slide" v-for="item in detailBanner" :key="item.id">
								<div class="swiper-item">
									<img v-if="item.substr(0,4)=='http'" :src="item" class="image">
									<img v-else :src="baseUrl + item" class="image">
								</div>
							</div>
						</div>
						<div class="swiper-button-prev">
							<span class="icon iconfont icon-jiantou39"></span>
						</div>
						<div class="swiper-button-next">
							<span class="icon iconfont icon-jiantou18"></span>
						</div>
					</div>
				</div>
			</div>


		

			<el-tabs class="detail-tabs" v-model="activeName" type="border-card" v-if="tabsNum>0" >
			</el-tabs>

			<div class="idea1"></div>
			<div class="idea2"></div>
		</div>
		<div class="share_view">
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	import Swiper from "swiper";
	export default {
		//数据集合
		data() {
			return {
				tablename: 'qichefenlei',
				baseUrl: '',
				breadcrumbItem: [
					{
						name: 'Car Categories'
					}
				],
				title: '',
				detailBanner: [],
				userid: Number(localStorage.getItem('frontUserid')),
				id: 0,
				detail: {},
				tabsNum: 0,
				activeName: 'first',
				total: 1,
				pageSize: 10,
				totalPage: 1,
				buynumber: 1,
				centerType: false,
				storeupType: false,
				shareUrl: location.href,
			}
		},
		created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0) {
				this.centerType = true
			}
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			
			this.init();
		},
		mounted() {
			setTimeout(()=>{
				let mySwiper22 = new Swiper(".mySwiper22", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"freeMode":true,"watchSlidesVisibility":true,"watchSlidesProgress":true,"loopedSlides":5,"slidesPerView":3,"spaceBetween":30})
				let option21 = {...{"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"loopedSlides":5,"spaceBetween":10}}
				option21.thumbs = {
					swiper: mySwiper22
				}
				new Swiper(".mySwiper21", option21)
			},100)
		},
		//方法集合
		methods: {
			init() {
				this.id = this.$route.query.id
				this.baseUrl = this.$config.baseUrl;
				this.$http.get(this.tablename + '/detail/'  + this.id, {}).then(res => {
					if (res.data.code == 0) {
						this.detail = res.data.data;
						this.$forceUpdate();
						if(localStorage.getItem('frontToken')){
						}

					}
				});
			},
			curChange(page) {
				this.getDiscussList(page);
			},
			prevClick(page) {
				this.getDiscussList(page);
			},
			nextClick(page) {
				this.getDiscussList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getDiscussList(1);
			},
			// 返回按钮
			backClick(){
				if(this.storeupType){
					history.back()
				}else{
					let params = {}
					if(this.centerType){
						params.centerType = 1
					}
					this.$router.push({path: '/index/qichefenlei', query: params});
				}
			},
			// 下载
			download(file ){
				if(!file) {
					this.$message({
						type: 'error',
						message: '文件不存在',
						duration: 1500,
					});
					return;
				}
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.baseUrl + '/file/download?fileName=' + arr, {
					headers: {
						token: localStorage.getItem("frontToken")
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$config.name).length>1 ? location.href.split(this.$config.name)[0] :'') + this.$config.name + '/file/download?fileName=' + arr, {
						headers: {
							token: localStorage.getItem("frontToken")
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},


			// 权限判断
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			// UPDATE
			editClick(){
				this.$router.push(`/index/qichefenleiAdd?type=edit&&id=${this.detail.id}`);
			},
			// DELETE
			async delClick(){
				await this.$confirm('是否DELETE此Car Categories？') .then(_ => {
					this.$http.post('qichefenlei/delete', [this.detail.id]).then(async res => {
						if (res.data.code == 0) {
							this.$message({
								type: 'success',
								message: 'DELETE成功!',
								duration: 1500,
								onClose: () => {
									history.back()
								}
							});
						}
					});
				}).catch(_ => {});
			},
		},
		components: {
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.detail-preview {
		padding: 20px calc((100% - 1200px)/2);
		margin: 0px auto;
		color: #666;
		align-content: flex-start;
		background: #fff;
		display: flex;
		width: 100%;
		font-size: 16px;
		justify-content: space-between;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.attr {
			padding: 0;
			background: none;
			display: block;
			width: calc(100% - 0px);
			position: relative;
			order: 2;
			.info {
				padding: 0;
				margin: 0 0 0 -6px;
				align-content: flex-start;
				background: none;
				display: flex;
				width: calc(100% + 12px);
				align-items: flex-start;
				flex-wrap: wrap;
				.title-item {
					border-radius: 0px;
					padding: 10px 6px;
					margin: 0 auto 10px;
					background: #f5f5f5;
					display: flex;
					width: calc(100% - 12px);
					border-color: #24355930;
					border-width: 1px;
					line-height: 1.5;
					justify-content: space-between;
					align-items: center;
					border-style: solid;
					.detail-title {
						color: #000;
						font-weight: 600;
						font-size: 18px;
					}
				}
				.item {
					border-radius: 0px;
					padding: 10px !important;
					margin: 0 5px 10px;
					background: #f5f5f5;
					display: inline-flex;
					width: calc(50% - 10px);
					border-color: #24355930;
					border-width: 1px;
					border-style: solid;
					.lable {
						padding: 0 10px 0 0;
						color: #333;
						white-space: nowrap;
						font-weight: 500;
						width: auto;
						font-size: inherit;
						line-height: 40px;
						text-align: right;
						height: auto;
					}
					.text {
						padding: 8px 10px 0;
						color: inherit;
						word-break: break-all;
						flex: 1;
						font-size: inherit;
						line-height: 24px;
						height: auto;
					}
					.price {
						color: #f00;
					}
					.bold {
						font-weight: bold;
					}
					.link {
						cursor: pointer;
						text-decoration: underline;
					}
				}
				.btn_box {
					padding: 10px 0;
					display: flex;
					font-size: 14px;
					flex-wrap: wrap;
				}
				.editBtn {
					border: 0px solid rgba(64, 158, 255, .3);
					cursor: pointer;
					border-radius: 0px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					color: #fff;
					background: #2301bb;
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.editBtn:hover {
				}
				.delBtn {
					border: 0px solid rgba(209, 6, 2, .3);
					cursor: pointer;
					border-radius: 0px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: #97bdff;
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.delBtn:hover {
				}
			}
		}
		.detail-swpier2 {
			margin: 0 40px 20px 0;
			display: block;
			width: 100%;
			text-align: center;
			height: auto;
			order: 1;
			.swiper21 {
				width: 100%;
				height: auto;
				.swiper-button-prev:after {
					display:none;
				}
				.swiper-button-next:after {
					display:none;
				}
				.swiper-item {
					width: 100%;
					height: auto;
					img {
						border: 1px solid #ddd;
						object-fit: contain;
						width: calc(100% - 2px);
						height: 600px;
					}
				}
				.swiper-button-prev {
					margin: -12px 0 0;
					top: 50%;
					width: 24px;
					height: 24px;
					.icon {
						color: #fff;
						width: 24px;
						font-size: 24px;
						height: 24px;
					}
				}
				.swiper-button-next {
					margin: -12px 0 0;
					top: 50%;
					width: 24px;
					height: 24px;
					.icon {
						color: #fff;
						width: 24px;
						font-size: 24px;
						height: 24px;
					}
				}
			}
			.swiper22 {
				margin: 20px 0 0;
				width: 100%;
				height: auto;
				
				.swiper-button-prev:after {
					display:none;
				}
				.swiper-button-next:after {
					display:none;
				}
				
				.swiper-item {
					border-radius: 10px;
					width: 100%;
					opacity: 0.4;
					height: auto;
					img {
						border: 1px solid #ddd;
						object-fit: contain;
						width: calc(100% - 2px);
						height: 200px;
					}
				}
				.swiper-slide.swiper-slide-thumb-active div {
					opacity: 1;
				}
				.swiper-button-prev {
					margin: -7px 0 0;
					top: 50%;
					width: 14px;
					height: 14px;
					.icon {
						color: #fff;
						width: 14px;
						font-size: 14px;
						height: 14px;
					}
				}
				.swiper-button-next {
					margin: -7px 0 0;
					top: 50%;
					width: 14px;
					height: 14px;
					.icon {
						color: #fff;
						width: 14px;
						font-size: 14px;
						height: 14px;
					}
				}
			}
		}
		.detail-tabs {
			border: 1px solid #2a3b5e50;
			border-radius: 8px;
			box-shadow: none;
			padding: 10px;
			margin: 20px auto 40px;
			background: #fff;
			flex: 1;
			display: flex;
			width: 100%;
			order: 21;
			& /deep/ .el-tabs__header .el-tabs__nav-wrap {
				margin-bottom: 0;
			}
			/deep/ .el-tabs__header {
				padding: 30px 10px;
				margin: 0 20px 0 0;
				flex-direction: column;
				background: #f5f5f5;
				display: flex;
				width: 140px;
				border-color: #24355950;
				border-width: 1px;
				border-style: solid;
				flex-wrap: wrap;
			}
			
			/deep/ .el-tabs__header .el-tabs__item {
				border: 0;
				padding: 0 0px;
				margin: 0 0 20px;
				color: #000;
				font-weight: 500;
				display: block;
				font-size: 16px;
				line-height: 34px;
				transition: all 0s;
				background: none;
				width: 120px;
				position: relative;
				list-style: none;
				text-align: center;
				height: 34px;
			}
			
			/deep/ .el-tabs__header .el-tabs__item:hover {
				border: 0;
				margin: 0 0 20px;
				color: #fff;
				background: #3397da;
				width: 120px;
				font-size: 16px;
				line-height: 34px;
				text-align: center;
				height: 34px;
			}
			
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				border: 0;
				margin: 0 0 20px;
				color: #fff;
				background: #3397da;
				width: 120px;
				font-size: 16px;
				line-height: 34px;
				text-align: center;
				height: 34px;
			}
			
			/deep/ .el-tabs__content {
				border: 1px solid #24355950;
				padding: 15px;
				width: 100%;
			}
		}
	}
	.share_view{
		box-shadow: 0 1px 6px rgba(0,0,0,.3);
		z-index: 11;
		bottom: 20%;
		background: #fff;
		position: fixed;
		right: 0;
		.share:last-of-type{
			border:none;
		}
	}
	.idea1 {
		background: #fff;
		width: 100%;
		height: 1px;
		order: 4;
	}
	.idea2 {
		background: #fff;
		width: 100%;
		height: 1px;
		order: 6;
	}
</style>
