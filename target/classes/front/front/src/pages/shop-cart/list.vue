<template>
	<div :style='{"padding":"0 calc((100% - 1200px)/2) 20px","margin":"0px auto","color":"#666","background":"#fff","width":"100%","fontSize":"16px","position":"relative"}'>
		<div class="section-title" :style='{"padding":"0","margin":"20px 0 0","color":"#fff","textAlign":"center","background":"url(http://codegen.caihongy.cn/20241228/f151b89f53c94b7fa5ae510c2f7bd563.png) no-repeat center bottom","width":"100%","fontSize":"32px","lineHeight":"100px","position":"relative","height":"191px"}'>购物车</div>
		<el-button size="small" type="danger" @click="delAllClick" :disabled="selRows.length?false:true">DELETE</el-button>
		<el-table @selection-change="handleSelectionChange" :data="tableData" style="width: 100%">
			<el-table-column type="selection" width="50" align="center" />
			<el-table-column label="Product Name">
				<template slot-scope="scope">
					<div class="shangpin">
						<el-image style="width: 100px; height: 100px;flex: 1"
							:src="baseUrl + scope.row.picture" fit="fill"></el-image>
						<span style="margin-left: 10px;flex: 1">{{ scope.row.goodname }}</span>
					</div>
				</template>
			</el-table-column>
			<el-table-column label="Price">
				<template slot-scope="scope">
					<span :style='{"fontSize":"12px"}'>￥</span>{{ scope.row.price }}
				</template>
			</el-table-column>
			<el-table-column label="数量">
				<template slot-scope="scope">
					<el-input-number :min="1" v-model="scope.row.buynumber" @change="(value) => handleChange(value, scope.$index, scope.row)" size="small"></el-input-number>
				</template>
			</el-table-column>
			<el-table-column label="总价">
				<template slot-scope="scope">
					<span :style='{"fontSize":"12px"}'>￥</span>{{ (scope.row.price * scope.row.buynumber).toFixed(2) }}
				</template>
			</el-table-column>
			<el-table-column label="Actions" width="150">
				<template slot-scope="scope">
					<el-button size="mini" type="danger"
						@click="handleDelete(scope.$index, scope.row)">DELETE</el-button>
				</template>
			</el-table-column>
		</el-table>
		<div class="buy" v-if="tableData.length > 0">
			<span style="color: red;margin-right: 10px;font-weight: 700;">总价：<span :style='{"fontSize":"12px"}'>￥</span>{{totalPrice}}</span>
			<el-button type="warning" @click="buyClick">点击购买</el-button>
		</div>
	</div>
</template>

<script>
	import config from '@/config/config'
	export default {
		data() {
			return {
				baseUrl: config.baseUrl,
				tableData: [],
				selRows:[]
			}
		},
		created() {
			this.getCartList();
		},
		methods: {
			handleSelectionChange(e){
				this.selRows = e
			},
			getCartList() {
				this.$http.get('cart/list', {params: {page: 1, limit: 1000, userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
					if (res.data.code == 0) {
						this.tableData = res.data.data.list;
					}
				});
			},
			handleChange(value, index, row) {
				this.$http.get(`${row.tablename}/detail/${row.goodid}`).then(res => {
					if (res.data.code == 0) {
						if (res.data.data.onelimittimes&&res.data.data.onelimittimes > 0) {
							if (value > res.data.data.onelimittimes) {
								this.tableData[index].buynumber = --value;
								this.$message.error(`每人单次只能购买${res.data.data.onelimittimes}件`);
							} else {
								row.buynumber = value;
								this.$http.post('cart/update', row).then(res => {});
							}
						} else {
							row.buynumber = value;
							this.$http.post('cart/update', row).then(res => {});
						}
					}
				});
			},
			handleDelete(index, row) {
				this.$confirm('是否确认DELETE?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					let delIds = new Array();
					delIds.push(row.id);
					this.$http.post('cart/delete', delIds).then(res => {
						if (res.data.code == 0) {
							this.$message({
								type: 'success',
								message: 'DELETE成功!',
								duration: 1500,
								onClose: () => {
									this.getCartList();
								}
							});
						}
					});
				});
			},
			delAllClick(){
				this.$confirm('是否确认选中ProductDELETE?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					let delIds = new Array();
					for(let x in this.selRows){
						delIds.push(this.selRows[x].id)
					}
					this.$http.post('cart/delete', delIds).then(res => {
						if (res.data.code == 0) {
							this.$message({
								type: 'success',
								message: 'DELETE成功!',
								duration: 1500,
								onClose: () => {
									this.getCartList();
								}
							});
						}
					});
				});
			},
			buyClick() {
				if(this.selRows.length==0) {
					this.$message({
						type: 'warning',
						message: '请选择Product',
						duration: 1500,
					});
					return
				}
				var gn = "";
				for(let x in this.selRows){
					var g = this.selRows[x];
					console.log(g.goodname)
					let that = this;
					this.$http.get(`${g.tablename}/detail/${g.goodid}`).then(res => {
						if (res.data.code == 0 && g.buynumber > res.data.data.alllimittimes) {
							gn = g.goodname;
						}
					});
				}
			
				setTimeout(() => {
					if(gn=="") {
						localStorage.setItem('orderGoods', JSON.stringify(this.selRows));
						this.$router.push('/index/shop-order/orderConfirm');
					} else {
						this.$message.error(gn+` Stock不足`);
						return;
					}
				}, 200)
			}
		},
		computed: {
			totalPrice() {
				let totalPrice = 0;
				for(let x in this.selRows){
					totalPrice += Number(this.selRows[x].price) * Number(this.selRows[x].buynumber)
				}
				return totalPrice.toFixed(2);
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.section {
		width: 900px;
		margin: 0 auto;
	}
	
	.shangpin {
		display: flex;
	}
	.buy {
		text-align: right;
		padding-right: 82px;
	}
</style>
