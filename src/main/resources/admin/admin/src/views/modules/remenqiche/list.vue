<template>
	<div class="main-content" :style='{"padding":"60px 20px","margin":"0","fontSize":"16px"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"margin":"0 0px 0px"}' :inline="true" :model="searchForm">
				<el-row :style='{"width":"100%","padding":"10px","flexWrap":"wrap","background":"none","display":"flex"}' >
					<div :style='{"margin":"0 10px 20px 0","display":"flex"}'>
						<label :style='{"border":"1px solid #CBCBCB","padding":"0 10px","margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#000","textAlign":"center","display":"inline-block","borderRadius":"10px","background":"#fff","width":"auto","lineHeight":"34px","fontSize":"inherit","fontWeight":"500","height":"34px"}' class="item-label">Car Name</label>
						<el-input v-model="searchForm.qichemingcheng" placeholder="Car Name" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 20px 0","display":"flex"}'>
						<label :style='{"border":"1px solid #CBCBCB","padding":"0 10px","margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#000","textAlign":"center","display":"inline-block","borderRadius":"10px","background":"#fff","width":"auto","lineHeight":"34px","fontSize":"inherit","fontWeight":"500","height":"34px"}' class="item-label">Price</label>
						<el-input v-model="searchForm.pricestart" placeholder="最小Price" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 20px 0","display":"flex"}' :label="'至'">
						<el-input v-model="searchForm.priceend" placeholder="最大Price" clearable></el-input>
					</div>
					<el-button class="search" type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing07" :style='{"margin":"0 2px","fontSize":"16px","color":"#0F1330","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>

				<el-row class="actions" :style='{"padding":"10px","margin":"0px 0","flexWrap":"wrap","background":"none","display":"flex"}'>
					<el-button class="add" v-if="isAuth('remenqiche','新增')" type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-tianjia1" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","borderRadius":"5px","height":"34px"}'></span>
						ADD
					</el-button>
					<el-button class="del" v-if="isAuth('remenqiche','DELETE')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 2px","fontSize":"14px","color":"#6EADEF","height":"34px"}'></span>
						DELETE
					</el-button>


				</el-row>
			</el-form>
			<div :style='{"width":"100%","padding":"0 10px","fontSize":"15px","color":"#000"}'>
				<el-table class="tables"
					:stripe='true'
					:style='{"padding":"0","borderColor":"#eee","borderRadius":"20px 20px 0 0","borderWidth":"1px 0 0 1px","background":"#fff","width":"100%","fontSize":"inherit","borderStyle":"solid"}' 
					:border='true'
					v-if="isAuth('remenqiche','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'  
						prop="qichemingcheng"
						label="Car Name">
						<template slot-scope="scope">
							{{scope.row.qichemingcheng}}
						</template>
					</el-table-column>
					<el-table-column  :resizable='true' prop="tupian" width="200" label="Image">
						<template slot-scope="scope">
							<div v-if="scope.row.tupian">
								<img v-if="scope.row.tupian.substring(0,4)=='http'&&scope.row.tupian.split(',w').length>1" :src="scope.row.tupian" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.tupian)">
								<img v-else-if="scope.row.tupian.substring(0,4)=='http'" :src="scope.row.tupian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.tupian.split(',')[0])">
								<img v-else :src="$base.url+scope.row.tupian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView($base.url+scope.row.tupian.split(',')[0])">
							</div>
							<div v-else>无Image</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="qichefenlei"
						label="Car Categories">
						<template slot-scope="scope">
							{{scope.row.qichefenlei}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="guige"
						label="Specification">
						<template slot-scope="scope">
							{{scope.row.guige}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="tedian"
						label="Features">
						<template slot-scope="scope">
							{{scope.row.tedian}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="onelimittimes"
						label="Unit Limit">
						<template slot-scope="scope">
							{{scope.row.onelimittimes}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="alllimittimes"
						label="Stock">
						<template slot-scope="scope">
							{{scope.row.alllimittimes}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="discussnum"
						label="Number of Reviews">
						<template slot-scope="scope">
							{{scope.row.discussnum}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="totalscore"
						label="Rating">
						<template slot-scope="scope">
							{{scope.row.totalscore}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="price"
						label="Price">
						<template slot-scope="scope">
							{{scope.row.price}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="onshelves" label="Listed or Not">
						<template slot-scope="scope">
							<el-switch
								v-model="scope.row.onshelves"
								:width="60"
								active-icon-class="el-icon-edit"
								inactive-icon-class="el-icon-share"
								active-text="开"
								inactive-text="关"
								:active-value="1"
								:inactive-value="0"
								active-color="#3478FF"
								inactive-color="#999"
								@change="(e)=>onshelvesChange(e,scope.row)">
							</el-switch>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="storeupnum"
						label="Number of Favorites">
						<template slot-scope="scope">
							{{scope.row.storeupnum}}
						</template>
					</el-table-column>
					<el-table-column width="300" label="Actions">
						<template slot-scope="scope">
							<el-button class="view" v-if=" isAuth('remenqiche','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								查看
							</el-button>
							<el-button class="edit" v-if=" isAuth('remenqiche','UPDATE') " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								UPDATE
							</el-button>

							<el-button class="view" v-if="isAuth('remenqiche','查看评论')" type="success" @click="disscussListHandler(scope.row.id)">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								查看评论
							</el-button>



							<el-button class="del" v-if="isAuth('remenqiche','DELETE') " type="primary" @click="deleteHandler(scope.row.id )">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								DELETE
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				background
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:layout="layouts.join()"
				:total="totalPage"
				prev-text="< "
				next-text="> "
				:hide-on-single-page="false"
				:style='{"border":" 0px solid #F1F1F1","padding":"0","margin":"20px auto 0","whiteSpace":"nowrap","color":"#000000","textAlign":"center","display":"flex","justifyContent":"space-between","borderRadius":"5px","background":"none","width":"750px","fontSize":"12","fontWeight":"400","height":"50px"}'
			></el-pagination>
		</template>
		
		<!-- ADD/UPDATE页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>





		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>

<script>
	import axios from 'axios';
	import AddOrUpdate from "./add-or-update";
	import {
		Loading
	} from 'element-ui';
	export default {
		data() {
			return {
				indexQueryCondition: '',
				searchForm: {
					key: ""
				},
				form:{},
				colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: true,
				addOrUpdateFlag:false,
				layouts: ["total","prev","pager","next","sizes","jumper"],
				previewImg: '',
				previewVisible: false,
			};
		},
		created() {
			this.init();
			this.getDataList();
			this.contentStyleChange();
		},
		mounted() {
		},
		filters: {
			htmlfilter: function (val) {
				return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
			}
		},
		computed: {
			tablename(){
				return this.$storage.get('sessionTable')
			},
		},
		components: {
			AddOrUpdate,
		},
		methods: {
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
				
			},
			contentStyleChange() {
				this.contentPageStyleChange()
			},
			// 分页
			contentPageStyleChange(){
				let arr = []

				// if(this.contents.pageTotal) arr.push('total')
				// if(this.contents.pageSizes) arr.push('sizes')
				// if(this.contents.pagePrevNext){
				//   arr.push('prev')
				//   if(this.contents.pagePager) arr.push('pager')
				//   arr.push('next')
				// }
				// if(this.contents.pageJumper) arr.push('jumper')
				// this.layouts = arr.join()
				// this.contents.pageEachNum = 10
			},





			init () {
			},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},

			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				let params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: 'id',
					order: 'desc',
				}
 				if(this.searchForm.qichemingcheng!='' && this.searchForm.qichemingcheng!=undefined){
					params['qichemingcheng'] = '%' + this.searchForm.qichemingcheng + '%'
				}
				if(this.searchForm.qichefenlei!='' && this.searchForm.qichefenlei!=undefined){
					params['qichefenlei'] = this.searchForm.qichefenlei
				}
 				if(this.searchForm.pricestart!='' && this.searchForm.pricestart!=undefined ){
					params['pricestart'] = this.searchForm.pricestart
				}
				if(this.searchForm.priceend!='' && this.searchForm.priceend!=undefined){
					params['priceend'] = this.searchForm.priceend
				}
				let user = JSON.parse(this.$storage.getObj('userForm'))
				this.$http({
					url: "remenqiche/page",
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			},
			// ADD/UPDATE
			addOrUpdateHandler(id,type) {
				this.showFlag = false;
				this.addOrUpdateFlag = true;
				this.crossAddOrUpdateFlag = false;
				if(type!='info'&&type!='msg'){
					type = 'else';
				}
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(id,type);
				});
			},
			// 查看评论
			disscussListHandler(id,type) {
				this.$router.push({path:'/discussremenqiche',query:{refid:id}});
			},
			onshelvesChange(e,row){
				this.$http({
					url:'remenqiche/update',
					method:'post',
					data:row
				}).then(res=>{})
			},
			// DELETE
			async deleteHandler(id ) {
				var ids = id? [Number(id)]: this.dataListSelections.map(item => {
					return Number(item.id);
				});
				await this.$confirm(`确定进行[${id ? "DELETE" : "批量DELETE"}]Actions?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(async () => {
					await this.$http({
						url: "remenqiche/delete",
						method: "post",
						data: ids
					}).then(async ({ data }) => {
						if (data && data.code === 0) {
							for(let x in ids){
								await this.$http.get('storeup/list',{params: {
									page: 1,
									limit: 100,
									refid: ids[x],
									tablename: 'remenqiche'
								}}).then(async obj=>{
									if(obj.data&&obj.data.code==0){
										if(obj.data.data.list.length){
											let arr = []
											for(let i in obj.data.data.list){
												arr.push(obj.data.data.list[i].id)
											}
											await this.$http.post('storeup/delete',arr).then(()=>{})
										}
									}
								})
							}
							this.$message({
								message: "Actions成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.search();
								}
							});
			
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			},


		}

	};
</script>
<style lang="scss" scoped>
	
	.center-form-pv {
		.el-date-editor.el-input {
			width: auto;
		}
	}
	
	.el-input {
		width: auto;
	}
	
	// form
	.center-form-pv .el-input {
		width: 100%;
	}
	.center-form-pv .el-input /deep/ .el-input__inner {
		border: 1px solid #CBCBCB;
		border-radius: 0px;
		padding: 0 12px;
		color: #000;
		background: #fff;
		width: 170px;
		font-size: 16px;
		height: 34px;
	}
	.center-form-pv .el-select {
		width: 100%;
	}
	.center-form-pv .el-select /deep/ .el-input__inner {
		border: 1px solid #ddd;
		border-radius: 0px;
		padding: 0 10px;
		color: #000;
		width: 170px;
		font-size: 16px;
		height: 34px;
	}
	.center-form-pv .el-date-editor {
		width: 100%;
	}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #ddd;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #000;
		width: 170px;
		font-size: 16px;
		height: 34px;
	}
	
	.center-form-pv .search {
		border: 0;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 10px;
		color: #0F1330;
		background: #AFD0F5;
		width: auto;
		font-size: 16px;
		height: 34px;
	}
	
	.center-form-pv .search:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .add {
		border: 0px solid #ddd;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 10px;
		margin: 4px;
		color: #fff;
		background: #6EADEF;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .add:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .del {
		border: 1px solid #6EADEF;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 10px;
		margin: 4px;
		color: #6EADEF;
		background: #fff;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .del:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .statis {
		border: 1px solid #6EADEF;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 10px;
		margin: 4px;
		color:  #6EADEF;
		background: #fff;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .statis:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .btn18 {
		border: 1px solid #6EADEF;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 10px;
		margin: 4px;
		color:  #6EADEF;
		background: #fff;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .btn18:hover {
		opacity: 0.8;
	}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
		width: 100%;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
		padding: 0 10px;
		background: #EEEEEE;
		border-color: #eee;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
		padding: 0 0 0 0px;
		word-wrap: normal;
		color: #000000;
		white-space: normal;
		font-weight: 400;
		display: flex;
		vertical-align: middle;
		font-size: 16px;
		line-height: 24px;
		text-overflow: ellipsis;
		word-break: break-all;
		width: 100%;
		align-items: center;
		position: relative;
	}

	.el-table /deep/ .el-table__body-wrapper {
		position: relative;
	}
	.el-table /deep/ .el-table__body-wrapper tbody {
		width: 100%;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
		background: #fff;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding:  0px;
		color: #333;
		background: #fff;
		font-size: inherit;
		border-color: #eee;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
		.el-table /deep/ .el-table__body-wrapper tbody tr.el-table__row--striped td {
		background: #f8f8f8;
	}
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
		padding: 0;
		color: #333;
		background: #f0f0f0;
		border-color: #eee;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding:  0px;
		color: #333;
		background: #fff;
		font-size: inherit;
		border-color: #eee;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
		padding: 0 0 0 8px;
		overflow: hidden;
		color: #333;
		word-break: break-all;
		white-space: normal;
		line-height: 24px;
		text-overflow: ellipsis;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
		border: 1px solid #D8D8D8;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 5px;
		margin: 0 5px 5px 0;
		color: #000000;
		background: none;
		font-weight: 400;
		width: auto;
		font-size: 16px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add:hover {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
		border: 1px solid #D8D8D8;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 5px;
		margin: 0 5px 5px 0;
		color: #000000;
		background: none;
		font-weight: 400;
		width: auto;
		font-size: 16px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
		border: 1px solid #D8D8D8;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 5px;
		margin: 0 5px 5px 0;
		color: #000000;
		background: none;
		font-weight: 400;
		width: auto;
		font-size: 16px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8 {
		border: 1px solid #D8D8D8;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 5px;
		margin: 0 5px 5px 0;
		color: #000000;
		background: none;
		font-weight: 400;
		width: auto;
		font-size: 16px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8:hover {
		opacity: 0.8;
	}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
		margin: 0 10px 0 0;
		color: #666;
		font-weight: 400;
		vertical-align: top;
		font-size: inherit;
		line-height: 42px;
		height: 42px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev {
		border: none;
		border-radius: 100%;
		padding: 0;
		margin: 0 5px;
		color: #fff;
		background: #0F1330;
		display: block;
		vertical-align: top;
		width: 42px;
		font-size: 16px;
		line-height: auto;
		height: 42px;
	}
	
	.main-content .el-pagination /deep/ .btn-next {
		border: none;
		padding: 0;
		margin: 0 5px;
		color: #fff;
		display: block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		border-radius: 50%;
		background: #0F1330;
		width: 42px;
		min-width: 35px;
		height: 42px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
		border: none;
		cursor: not-allowed;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		display: block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		border-radius: 50%;
		background: #0F1330;
		width: 42px;
		height: 42px;
	}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
		border: none;
		cursor: not-allowed;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		display: block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		border-radius: 50%;
		background: #0F1330;
		width: 42px;
		height: 42px;
	}

	.main-content .el-pagination /deep/ .el-pager {
		padding: 0;
		margin: 0;
		display: flex;
		vertical-align: top;
	}

	.main-content .el-pagination /deep/ .el-pager .number {
		cursor: pointer;
		padding: 0 0px;
		color: #000000;
		font-weight: 400;
		display: flex;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		background: none;
		justify-content: center;
		align-items: center;
		text-align: center;
		min-width: 42px;
		height: 42px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
		cursor: pointer;
		padding: 0 0px;
		color: #FFFFFF;
		font-weight: 400;
		display: flex;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		border-radius: 50%;
		background: #6EADEF;
		align-items: center;
		text-align: center;
		height: 42px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
		cursor: pointer;
		padding: 0px;
		color: #FFFFFF;
		font-weight: 400;
		display: flex;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		border-radius: 50%;
		background: #6EADEF;
		justify-content: center;
		align-items: center;
		text-align: center;
		min-width: 42px;
		height: 42px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
		margin: 0 0px 0 0;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 42px;
		height: 42px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #C0C4CC;
		width: 25px;
		font-size: 14px;
		line-height: 48px;
		text-align: center;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #606266;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 42px;
		height: 42px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: 14px;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 3px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	// list one
	.one .list1-view {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #157ed2;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-view:hover {
		opacity: 0.8;
	}
	
	.one .list1-edit {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #409eff;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-edit:hover {
		opacity: 0.8;
	}
	
	.one .list1-del {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: rgba(255, 0, 0, 1);
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-del:hover {
		opacity: 0.8;
	}
	
	.one .list1-btn8 {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 24px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: rgba(255, 128, 0, 1);
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-btn8:hover {
		opacity: 0.8;
	}
	
	.main-content .el-table .el-switch {
		display: inline-flex;
		vertical-align: middle;
		line-height: 30px;
		position: relative;
		align-items: center;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--left {
		cursor: pointer;
		margin: 0 10px 0 0;
		color: #333;
		font-weight: 500;
		display: none;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--right {
		cursor: pointer;
		margin: 0 0 0 10px;
		color: #333;
		font-weight: 500;
		display: none;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core {
		border: 1px solid #75c0d6;
		cursor: pointer;
		border-radius: 15px;
		margin: 0;
		background: #75c0d6;
		display: inline-block;
		width: 42px;
		box-sizing: border-box;
		transition: border-color .3s,background-color .3s;
		height: 20px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core::after {
		border-radius: 100%;
		top: 1px;
		left: 1px;
		background: #fff;
		width: 16px;
		position: absolute;
		transition: all .3s;
		height: 16px;
	}
	.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after {
		margin: 0 0 0 -18px;
		left: 100%;
	}
	
	.main-content .el-table .el-rate /deep/ .el-rate__item {
		cursor: pointer;
		display: inline-block;
		vertical-align: middle;
		font-size: 0;
		position: relative;
	}
	.main-content .el-table .el-rate /deep/ .el-rate__item .el-rate__icon {
		margin: 0 3px;
		display: inline-block;
		font-size: 18px;
		position: relative;
		transition: .3s;
	}

</style>
