<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
			>
			<el-form-item class="add-item" label="Car Name" prop="qichemingcheng">
				<el-input v-model="ruleForm.qichemingcheng" 
					placeholder="Car Name" clearable :disabled=" false  ||ro.qichemingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="Image" v-if="type!='cross' || (type=='cross' && !ro.tupian)" prop="tupian">
				<file-upload
					tip="点击上传Image"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.tupian?ruleForm.tupian:''"
					@change="tupianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="Image" prop="tupian">
				<img v-if="ruleForm.tupian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.tupian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.tupian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item"  label="Car Categories" prop="qichefenlei">
				<el-select v-model="ruleForm.qichefenlei" placeholder="请选择Car Categories" :disabled=" false  ||ro.qichefenlei" >
					<el-option
						v-for="(item,index) in qichefenleiOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="Specification" prop="guige">
				<el-input v-model="ruleForm.guige" 
					placeholder="Specification" clearable :disabled=" false  ||ro.guige"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="Features" prop="tedian">
				<el-input v-model="ruleForm.tedian" 
					placeholder="Features" clearable :disabled=" false  ||ro.tedian"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="Unit Limit" prop="onelimittimes">
				<el-input v-model.number="ruleForm.onelimittimes" 
					placeholder="Unit Limit" clearable :disabled=" false  ||ro.onelimittimes"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="Stock" prop="alllimittimes">
				<el-input v-model.number="ruleForm.alllimittimes" 
					placeholder="Stock" clearable :disabled=" false  ||ro.alllimittimes"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="Price" prop="price">
				<el-input-number v-model="ruleForm.price" placeholder="Price" :disabled=" false ||ro.price"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="汽车参数" prop="qichecanshu">
				<editor 
					v-model="ruleForm.qichecanshu" 
					class="editor" 
					myQuillEditor="qichecanshu"
					action="file/upload">
				</editor>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit">
					<span class="icon iconfont "></span>
					<span class="text">提交</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont "></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
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
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					qichemingcheng: '',
					tupian: '',
					qichefenlei: '',
					guige: '',
					tedian: '',
					qichecanshu: '',
					onelimittimes: Number('-1') ,
					alllimittimes: Number('-1') ,
					discussnum: '',
					totalscore: '',
					price: '',
					onshelves: 1,
					storeupnum: '',
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
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					alllimittimes: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					discussnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					totalscore: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					price: [
						{ required: true, message: 'Price不能为空', trigger: 'blur' },
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					onshelves: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
				},
				centerType: false,
			};
		},
		computed: {



		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o=='qichemingcheng'){
							this.ruleForm.qichemingcheng = obj[o];
							this.ro.qichemingcheng = true;
							continue;
						}
						if(o=='tupian'){
							this.ruleForm.tupian = obj[o]?obj[o].split(",")[0]:'';
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
				}else if(type=='edit'){
					this.info()
				}
				// 获取User信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
					}
				});
				this.$http.get('option/qichefenlei/qichefenlei', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.qichefenleiOptions = res.data.data;
					}
				});

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`remenqiche/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
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
				if(!this.ruleForm.id) {
					delete this.ruleForm.userid
				}
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
							}
						}


						await this.$http.post(`remenqiche/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: 'Actions成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
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
				this.$router.go(-1);
			},
			tupianUploadChange(fileUrls) {
				this.ruleForm.tupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 0 calc((100% - 1200px)/2) 20px;
		margin: 0px auto;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 16px;
		position: relative;
		.add-update-form {
			border: 1px solid #24355950;
			padding: 40px 20px;
			margin: 0px auto;
			background: #fff;
			width: 100%;
			position: relative;
			.add-item.el-form-item {
				border-radius: 0px;
				padding: 6px 0 0;
				margin: 0 0 20px 0;
				background: none;
				border-color: #475a8310;
				border-width:  0 0 0px;
				border-style: solid;
				/deep/ .el-form-item__label {
					padding: 0 10px 0 0;
					color: #666;
					font-weight: 500;
					width: 180px;
					font-size: inherit;
					line-height: 40px;
					text-align: right;
				}
				/deep/ .el-form-item__content {
					margin-left: 180px;
				}
				.el-input {
					width: auto;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: rgba(85, 85, 127, 1.0);
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input__inner {
					text-align: left;
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .is-disabled .el-input__inner {
					text-align: left;
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: rgba(85, 85, 127, 1.0);
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input-number__decrease {
					display: none;
				}
				.el-input-number /deep/ .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: auto;
				}
				.el-select /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 10px;
					color: inherit;
					width: 100%;
					font-size: 16px;
					min-width: inherit !important;
					height: 40px;
				}
				.el-select /deep/ .is-disabled .el-input__inner {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 10px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-date-editor {
					width: auto;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				/deep/ .el-upload--picture-card {
					background: transparent;
					border: 0;
					border-radius: 0;
					width: auto;
					height: auto;
					line-height: initial;
					vertical-align: middle;
				}
				/deep/ .upload .upload-img {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 4px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
				}
				/deep/ .el-upload-list .el-upload-list__item {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 4px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
					font-size: 14px;
					line-height: 1.8;
				}
				/deep/ .el-upload .el-icon-plus {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 4px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
				}
				/deep/ .el-upload__tip {
					color: #888;
					font-size: 16px;
				}
				.el-textarea /deep/ .el-textarea__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					min-height: 150px;
					min-width: 48%;
					height: auto;
				}
				.el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
					border: 0px solid #ddd;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 12px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: auto;
					font-size: 16px;
					min-height: 150px;
					min-width: 50%;
					height: auto;
				}
				/deep/ .el-input__inner::placeholder {
					color: inherit;
					font-size: inherit;
				}
				/deep/ textarea::placeholder {
					color: inherit;
					font-size: inherit;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: none;
					margin: 0;
					width: 100%;
					min-height: 350px;
					border-color: #ccc;
					border-width: 1px;
					border-style: solid;
					height: auto;
				}
				.upload-img {
					object-fit: cover;
					width: 100px;
					height: 100px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 20px;
					margin: 0;
					color: #fff;
					background: #0674fc60;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 34px;
					height: 34px;
				}
				.viewBtn:hover {
				}
				.unviewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					color: #333;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 4px;
					outline: none;
					background: #eee;
					width: auto;
					height: 34px;
				}
				.unviewBtn:hover {
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 20px 0;
				text-align: center;
				.submitBtn {
					border: 0;
					cursor: pointer;
					padding: 0 24px;
					margin: 0 20px 0 0;
					display: inline-block;
					font-size: 16px;
					line-height: 40px;
					border-radius: 0px;
					background: #3397da;
					width: auto;
					text-align: center;
					min-width: 120px;
					height: 40px;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.submitBtn:hover {
					.icon {
					}
					.text {
					}
				}
				.closeBtn {
					border: 0px solid #ddd;
					cursor: pointer;
					padding: 0 24px;
					margin: 0 20px 0 0;
					color: #fff;
					display: inline-block;
					font-size: 16px;
					line-height: 40px;
					border-radius: 0px;
					background: #243559;
					width: auto;
					text-align: center;
					min-width: 120px;
					height: 40px;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn:hover {
					.icon {
					}
					.text {
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
