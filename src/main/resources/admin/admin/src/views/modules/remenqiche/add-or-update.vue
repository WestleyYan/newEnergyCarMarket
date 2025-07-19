<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="Car Name" prop="qichemingcheng" >
					<el-input v-model="ruleForm.qichemingcheng" placeholder="Car Name" clearable  :readonly="ro.qichemingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="Car Name" prop="qichemingcheng" >
					<el-input v-model="ruleForm.qichemingcheng" placeholder="Car Name" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.tupian" label="Image" prop="tupian" >
					<file-upload
						tip="点击上传Image"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.tupian?ruleForm.tupian:''"
						@change="tupianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.tupian" label="Image" prop="tupian" >
					<img v-if="ruleForm.tupian.substring(0,4)=='http'&&ruleForm.tupian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian" width="100" height="100">
					<img v-else-if="ruleForm.tupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.tupian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="Car Categories" prop="qichefenlei" >
					<el-select :disabled="ro.qichefenlei" v-model="ruleForm.qichefenlei" placeholder="请选择Car Categories" >
						<el-option
							v-for="(item,index) in qichefenleiOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="Car Categories" prop="qichefenlei" >
					<el-input v-model="ruleForm.qichefenlei"
						placeholder="Car Categories" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="Specification" prop="guige" >
					<el-input v-model="ruleForm.guige" placeholder="Specification" clearable  :readonly="ro.guige"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="Specification" prop="guige" >
					<el-input v-model="ruleForm.guige" placeholder="Specification" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="Features" prop="tedian" >
					<el-input v-model="ruleForm.tedian" placeholder="Features" clearable  :readonly="ro.tedian"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="Features" prop="tedian" >
					<el-input v-model="ruleForm.tedian" placeholder="Features" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="Unit Limit" prop="onelimittimes" >
					<el-input v-model.number="ruleForm.onelimittimes" placeholder="Unit Limit" clearable  :readonly="ro.onelimittimes"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="Unit Limit" prop="onelimittimes" >
					<el-input v-model="ruleForm.onelimittimes" placeholder="Unit Limit" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="Stock" prop="alllimittimes" >
					<el-input v-model.number="ruleForm.alllimittimes" placeholder="Stock" clearable  :readonly="ro.alllimittimes"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="Stock" prop="alllimittimes" >
					<el-input v-model="ruleForm.alllimittimes" placeholder="Stock" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="Price" prop="price" >
					<el-input-number v-model="ruleForm.price" placeholder="Price" :disabled="ro.price"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="Price" prop="price" >
					<el-input v-model="ruleForm.price" placeholder="Price" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item v-if="type!='info'"  label="汽车参数" prop="qichecanshu" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.qichecanshu" 
					class="editor"
					myQuillEditor="qichecanshu"
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.qichecanshu" label="汽车参数" prop="qichecanshu" >
				<span class="text ql-snow ql-editor" v-html="ruleForm.qichecanshu"></span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	import { 
		isNumber,
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isNumber(value)) {
					callback(new Error("请输入数字"));
				} else {
					callback();
				}
			};
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					qichemingcheng : false,
					tupian : false,
					qichefenlei : false,
					guige : false,
					tedian : false,
					qichecanshu : false,
					onelimittimes : false,
					alllimittimes : false,
					discussnum : false,
					totalscore : false,
					price : false,
					onshelves : false,
					storeupnum : false,
				},
			
				ruleForm: {
					qichemingcheng: '',
					tupian: '',
					qichefenlei: '',
					guige: '',
					tedian: '',
					qichecanshu: '',
					onelimittimes: Number('-1'),
					alllimittimes: Number('-1'),
					price: '',
					onshelves: 1,
				},
				qichefenleiOptions: [],

				rules: {
					qichemingcheng: [
						{ required: true, message: 'Car Name不能为空', trigger: 'blur' },
					],
					tupian: [
					],
					qichefenlei: [
					],
					guige: [
						{ required: true, message: 'Specification不能为空', trigger: 'blur' },
					],
					tedian: [
					],
					qichecanshu: [
						{ required: true, message: '汽车参数不能为空', trigger: 'blur' },
					],
					onelimittimes: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					alllimittimes: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					discussnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					totalscore: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					price: [
						{ required: true, message: 'Price不能为空', trigger: 'blur' },
						{ validator: validateNumber, trigger: 'blur' },
					],
					onshelves: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='qichemingcheng'){
							this.ruleForm.qichemingcheng = obj[o];
							this.ro.qichemingcheng = true;
							continue;
						}
						if(o=='tupian'){
							this.ruleForm.tupian = obj[o];
							this.ro.tupian = true;
							continue;
						}
						if(o=='qichefenlei'){
							this.ruleForm.qichefenlei = obj[o];
							this.ro.qichefenlei = true;
							continue;
						}
						if(o=='guige'){
							this.ruleForm.guige = obj[o];
							this.ro.guige = true;
							continue;
						}
						if(o=='tedian'){
							this.ruleForm.tedian = obj[o];
							this.ro.tedian = true;
							continue;
						}
						if(o=='qichecanshu'){
							this.ruleForm.qichecanshu = obj[o];
							this.ro.qichecanshu = true;
							continue;
						}
						if(o=='onelimittimes'){
							this.ruleForm.onelimittimes = obj[o];
							this.ro.onelimittimes = true;
							continue;
						}
						if(o=='alllimittimes'){
							this.ruleForm.alllimittimes = obj[o];
							this.ro.alllimittimes = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='totalscore'){
							this.ruleForm.totalscore = obj[o];
							this.ro.totalscore = true;
							continue;
						}
						if(o=='price'){
							this.ruleForm.price = obj[o];
							this.ro.price = true;
							continue;
						}
						if(o=='onshelves'){
							this.ruleForm.onshelves = obj[o];
							this.ro.onshelves = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
							continue;
						}
					}
					this.ruleForm.onelimittimes = Number('-1'); 					this.ruleForm.alllimittimes = Number('-1'); 				}
				// 获取User信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/qichefenlei/qichefenlei`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.qichefenleiOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `remenqiche/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传Image后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.qichecanshu = this.ruleForm.qichecanshu.replace(reg,'../../../springbootksp0l7ew/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.tupian!=null) {
						this.ruleForm.tupian = this.ruleForm.tupian.replace(new RegExp(this.$base.url,"g"),"");
					}
					if(this.ruleForm.price<0){
						this.$message.error("Price不能输入负数");
						return
					}
					if(this.ruleForm.alllimittimes<0){
						this.$message.error("Stock不能输入负数");
						return
					}
					if(this.ruleForm.onelimittimes<0){
						this.$message.error("单次购买不能输入负数");
						return
					}
					var objcross = this.$storage.getObj('crossObj');
					if(!this.ruleForm.id) {
						delete this.ruleForm.userid
					}
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
								}
							}
							
							await this.$http({
								url: `remenqiche/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "Actions成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.remenqicheCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.remenqicheCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			tupianUploadChange(fileUrls) {
				this.ruleForm.tupian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 70px 40px 0 90px;
	}
	.add-update-preview {
		padding: 30px 20% 30px 15%;
		margin: 0 20px;
		flex-direction: row;
		background: none;
		display: flex;
		width: 100%;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
		flex-wrap: wrap;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 1px solid #CBCBCB;
		padding: 0;
		flex-direction: row;
		background: #fff;
		display: block;
		width: 100%;
		justify-content: flex-start;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		border: 0px solid #CBCBCB;
		border-radius: 0px;
		padding: 0 10px;
		margin: 0;
		color: #000;
		background: #fff;
		font-weight: 400;
		width: 180px;
		font-size: 16px;
		line-height: 34px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		border: 0px solid #CBCBCB;
		cursor: pointer;
		padding: 0 15px;
		margin: 0;
		color: #000;
		display: inline-block;
		font-size: 15px;
		line-height: 34px;
		border-radius: 0px;
		word-break: break-all;
		background: #fff;
		width: 100%;
		text-align: left;
		height: auto;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 0px solid #CBCBCB;
		border-radius: 0px;
		padding: 0 12px;
		color: #000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #CBCBCB;
		border-radius: 0px;
		padding: 0 12px;
		color: #000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 0px solid #CBCBCB;
		border-radius: 0px;
		padding: 0 12px;
		color: #000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #CBCBCB;
		border-radius: 0px;
		padding: 0 12px;
		color: #000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 0px solid #CBCBCB;
		border-radius: 0px;
		padding: 0 10px;
		color: #000;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0px solid #CBCBCB;
		border-radius: 0px;
		padding: 0 10px;
		color: #000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 0px solid #CBCBCB;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #CBCBCB;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 0px solid #CBCBCB;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0;
		color: #000;
		background: #fff;
		width: 100%;
		font-size: 15px;
		line-height: 34px;
		text-align: left;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 0px solid #CBCBCB;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0;
		color: #000;
		background: #fff;
		width: 100%;
		font-size: 15px;
		line-height: 34px;
		text-align: left;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0px solid #CBCBCB;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		line-height: 34px;
		text-align: left;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border:  1px solid #CBCBCB;
		cursor: pointer;
		border-radius: 5px  ;
		margin: 5px 0 0 10px;
		color: #666;
		background: #fff;
		object-fit: cover;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border:  1px solid #CBCBCB;
		cursor: pointer;
		border-radius: 5px  ;
		margin: 5px 0 0 10px;
		color: #666;
		background: #fff;
		object-fit: cover;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border:  1px solid #CBCBCB;
		cursor: pointer;
		border-radius: 5px  ;
		margin: 5px 0 0 10px;
		color: #666;
		background: #fff;
		object-fit: cover;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		margin: 0 0 0 10px;
		color: #666;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 0px solid #CBCBCB;
		border-radius: 5px;
		padding: 12px;
		color: #000;
		background: #fff;
		width: 100%;
		font-size: 14px;
		min-width: 514px;
		height: 120px;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 0px solid #CBCBCB;
				border-radius: 5px;
				padding: 12px;
				color: #000;
				background: #fff;
				width: 100%;
				font-size: 14px;
				min-width: 514px;
				height: 120px;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 0;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 5px;
			padding: 0 10px;
			margin: 0 30px 0 20px;
			color: #000;
			background: #AFD0F5;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 5px;
			padding: 0 10px;
			margin: 0 30px 0 20px;
			color: #000;
			background: #AFD0F5;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 5px;
			padding: 0 10px;
			margin: 0 30px 0 20px;
			color: #000;
			background: #AFD0F5;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 5px;
			padding: 0 10px;
			margin: 0 30px 0 20px;
			color: #000;
			background: #AFD0F5;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 5px;
			padding: 0 10px;
			margin: 0 30px 0 20px;
			color: #000;
			background: #AFD0F5;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
