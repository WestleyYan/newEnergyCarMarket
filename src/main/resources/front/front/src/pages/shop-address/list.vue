<template>
	<div :style='{"padding":"0 calc((100% - 1200px)/2) 20px","margin":"0px auto","color":"#666","background":"#fff","width":"100%","fontSize":"16px","position":"relative"}'>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="section-title" :style='{"padding":"0","margin":"20px 0 0","color":"#fff","textAlign":"center","background":"url(http://codegen.caihongy.cn/20241228/f151b89f53c94b7fa5ae510c2f7bd563.png) no-repeat center bottom","width":"100%","fontSize":"32px","lineHeight":"100px","position":"relative","height":"191px"}'>My Account地址</div>
		<el-button type="primary" icon="el-icon-plus" @click="toAddAddr">ADD新地址</el-button>
		<el-table
			:data="tableData"
			style="width: 100%">
			<el-table-column
				label="联系人"
				prop="name"
				width="100">
			</el-table-column>
			<el-table-column
				label="PHONE号"
				prop="phone"
				width="120">
			</el-table-column>
			<el-table-column
				label="地址"
				prop="address">
			</el-table-column>
			<el-table-column
				label="默认"
				prop="isdefault"
				width="80">
				<template slot-scope="scope">
					<el-tag :type="scope.row.isdefault === '是' ? 'primary' : 'success'" size="medium">{{ scope.row.isdefault }}</el-tag>
				</template>
			</el-table-column>
			<el-table-column label="Actions">
				<template slot-scope="scope">
					<el-button
						size="mini"
						@click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button
						size="mini"
						type="danger"
						@click="handleDelete(scope.$index, scope.row)">DELETE</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-pagination
			background
			id="pagination" class="pagination"
			:pager-count="7"
			:page-size="pageSize"
			:page-sizes="pageSizes"
			prev-text="上一页"
			next-text="下一页"
			:hide-on-single-page="false"
			:layout='["total","prev","pager","next","sizes","jumper"].join()'
			:total="total"
			:style='{"padding":"0 calc((100% - 1400px)/2)","margin":"20px auto","whiteSpace":"nowrap","overflow":"hidden","color":"#333","textAlign":"center","width":"100%","clear":"both","fontSize":"16px","fontWeight":"500","order":"50"}'
			@current-change="curChange"
			@prev-click="prevClick"
			@size-change="sizeChange"
			@next-click="nextClick"
			></el-pagination>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				layouts: '',
				tableData: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1
			}
		},
		created() {
			this.getAddr(1);
		},
		methods: {
			backClick() {
				this.$router.push('/index/center')
			},
			getAddr(page) {
				this.$http.get('address/list', {params: {page, limit: this.pageSize, userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
					if (res.data.code == 0) {
						this.tableData = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			curChange(page) {
				this.getAddr(page);
			},
			prevClick(page) {
				this.getAddr(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getAddr(1);
			},
			nextClick(page) {
				this.getAddr(page);
			},
			handleEdit(index, row) {
				this.$router.push({path: '/index/shop-address/addOrUpdate', query: {id: row.id, editObj: JSON.stringify(row)}})
			},
			handleDelete(index, row) {
				this.$confirm('是否确认DELETE?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					let delIds = new Array();
					delIds.push(row.id);
					this.$http.post('address/delete', delIds).then(res => {
						if (res.data.code == 0) {
							this.$message({
								type: 'success',
								message: 'DELETE成功!',
								duration: 1500,
								onClose: () => {
									this.getAddr(1);
								}
							});
						}
					});
				});
			},
			toAddAddr() {
				this.$router.push('/index/shop-address/addOrUpdate');
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
</style>
