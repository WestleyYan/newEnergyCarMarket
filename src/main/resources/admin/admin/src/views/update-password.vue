<template>
	<div :style='{"padding":"70px 40px 0 90px"}'>
		<el-form
			:style='{"padding":"30px 20% 30px 15%","borderColor":"#eee","margin":"0 20px","flexWrap":"wrap","borderWidth":"0px 0 0","background":"none","flexDirection":"row","display":"flex","width":"100%","borderStyle":"solid"}'
			class="add-update-preview"
			ref="ruleForm"
			:rules="rules"
			:model="ruleForm"
			label-width="180px"
		>
			<el-form-item :style='{"border":"1px solid #CBCBCB","padding":"0","background":"#fff","flexDirection":"row","display":"block","width":"100%","justifyContent":"flex-start"}' label="原密码" prop="password">
				<el-input type="password" v-model="ruleForm.password" ></el-input>
			</el-form-item>
			<el-form-item :style='{"border":"1px solid #CBCBCB","padding":"0","background":"#fff","flexDirection":"row","display":"block","width":"100%","justifyContent":"flex-start"}' label="新密码" prop="newpassword">
				<el-input type="password" v-model="ruleForm.newpassword" ></el-input>
			</el-form-item>
			<el-form-item :style='{"border":"1px solid #CBCBCB","padding":"0","background":"#fff","flexDirection":"row","display":"block","width":"100%","justifyContent":"flex-start"}' label="确认密码" prop="repassword">
				<el-input type="password" v-model="ruleForm.repassword" ></el-input>
			</el-form-item>
			<el-form-item :style='{"padding":"0","margin":"0"}'>
				<el-button class="btn3" :style='{"border":"0px solid #ccc","cursor":"pointer","padding":"0 10px","margin":"0 30px 0 20px","color":"#000","borderRadius":"5px","background":"#AFD0F5","width":"auto","fontSize":"16px","minWidth":"110px","height":"40px"}' type="primary" @click="onUpdateHandler">
					<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
					提交
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>
<script>
export default {
	data() {
		return {
			dialogVisible: false,
			ruleForm: {},
			user: {},
			rules: {
				password: [
					{
						required: true,
						message: "密码不能为空",
						trigger: "blur"
					}
				],
				newpassword: [
					{
						required: true,
						message: "新密码不能为空",
						trigger: "blur"
					}
				],
				repassword: [
					{
						required: true,
						message: "确认密码不能为空",
						trigger: "blur"
					}
				]
			}
		};
	},
	mounted() {
		this.$http({
			url: `${this.$storage.get("sessionTable")}/session`,
			method: "get"
		}).then(({ data }) => {
			if (data && data.code === 0) {
				this.user = data.data;
			} else {
				this.$message.error(data.msg);
			}
		});
	},
	methods: {
		onLogout() {
			this.$storage.remove("Token");
			this.$router.replace({ name: "login" });
		},
		// UPDATE密码
		async onUpdateHandler() {
			this.$refs["ruleForm"].validate(async valid => {
				if (valid) {
					var password = "";
					if (this.user.mima) {
						password = this.user.mima;
					} else if (this.user.password) {
						password = this.user.password;
					}
					if(this.$storage.get("sessionTable")=='users'){
						if (this.ruleForm.password != password) {
							this.$message.error("原密码错误");
							return;
						}
						if (this.ruleForm.newpassword != this.ruleForm.repassword) {
							this.$message.error("两次密码输入不一致");
							return;
						}
						this.user.password = this.ruleForm.newpassword;
						this.user.mima = this.ruleForm.newpassword;
						this.$http({
							url: `${this.$storage.get("sessionTable")}/update`,
							method: "post",
							data: this.user
						}).then(({ data }) => {
							if (data && data.code === 0) {
								this.$message({
									message: "UPDATE密码成功,下次登录系统生效",
									type: "success",
									duration: 1500,
									onClose: () => {
									}
								});
							} else {
								this.$message.error(data.msg);
							}
						});
						return false
					}
					var nowpassword = ''
					await this.$http({
						url: '/encrypt/des?text=' + this.ruleForm.password,
						method: 'get'
					}).then(res=>{
						if(res.data&&res.data.code==0){
							nowpassword = res.data.data
						}
					})
					if(nowpassword!=password){
						this.$message.error("原密码错误");
						return;
					}
					if (this.ruleForm.newpassword != this.ruleForm.repassword) {
						this.$message.error("两次密码输入不一致");
						return;
					}
					if (this.ruleForm.newpassword == this.ruleForm.password) {
						this.$message.error("新密码与原密码相同");
						return;
					}
					this.user.password = this.ruleForm.newpassword;
					this.user.mima = this.ruleForm.newpassword;
					this.$http({
						url: `${this.$storage.get("sessionTable")}/update`,
						method: "post",
						data: this.user
					}).then(({ data }) => {
						if (data && data.code === 0) {
							this.$message({
								message: "UPDATE密码成功,下次登录系统生效",
								type: "success",
								duration: 1500,
								onClose: () => {
								}
							});
						} else {
							this.$message.error(data.msg);
						}
					});
				}
			});
		}
	}
};
</script>
<style lang="scss" scoped>
	.el-date-editor.el-input {
		width: auto;
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
	
	.add-update-preview .el-select /deep/ .el-input__inner {
	  	  border: 0px solid #CBCBCB;
	  	  border-radius: 0px;
	  	  padding: 0 10px;
	  	  color: #000;
	  	  width: 100%;
	  	  font-size: 16px;
	  	  height: 40px;
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
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
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
	
	.add-update-preview .btn3 {
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
			}
	
	.add-update-preview .btn3:hover {
				opacity: 0.8;
			}
</style>
