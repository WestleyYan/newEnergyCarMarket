<template>
	<div class="center-preview">
		<div class="center-title">{{ title }}</div>
		<div class="center-info">
			<div class="center-info-title">个人信息</div>

			<div class="img-box" v-if="userTableName=='yonghu'">
				<img :src="sessionForm.touxiang?baseUrl + sessionForm.touxiang:require('@/assets/avator.png')">
			</div>
			<div class="info-item1" v-if="userTableName=='yonghu'">
				<span class="icon iconfont "></span>
				<div class="label">账号：</div>
				<div class="text">{{sessionForm.zhanghao}}</div>
			</div>
			<div class="info-item2" v-if="userTableName=='yonghu'">
				<span class="icon iconfont "></span>
				<div class="label">NAME：</div>
				<div class="text">{{sessionForm.xingming}}</div>
			</div>
			<div class="info-item3" v-if="userTableName=='yonghu'">
				<span class="icon iconfont "></span>
				<div class="label">SEX：</div>
				<div class="text">{{sessionForm.xingbie}}</div>
			</div>
			<div class="info-item4" v-if="userTableName=='yonghu'">
				<span class="icon iconfont "></span>
				<div class="label">PHONE：</div>
				<div class="text">{{sessionForm.shouji}}</div>
			</div>
			<div class="info-item5" v-if="userTableName=='yonghu'">
				<span class="icon iconfont "></span>
				<div class="label">余额：</div>
				<div class="text">{{sessionForm.money}}</div>
			</div>
		
		</div>
	
		<el-tabs class="center-tabs" tab-position="left" @tab-click="handleClick">
			<el-tab-pane label="个人中心">
				<el-form class="center-preview-pv" ref="sessionForm" :model="sessionForm" :rules="rules" label-width="180px">
					<el-form-item class="center-item" v-if="userTableName=='yonghu'" label="账号" prop="zhanghao">
						<el-input v-model="sessionForm.zhanghao" placeholder="账号" readonly></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='yonghu'" label="NAME" prop="xingming">
						<el-input v-model="sessionForm.xingming" placeholder="NAME" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='yonghu'" label="SEX" prop="xingbie">
						<el-select v-model="sessionForm.xingbie" placeholder="请选择SEX" >
							<el-option v-for="(item, index) in dynamicProp.xingbie" :key="index" :label="item" :value="item"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='yonghu'" label="PHONE" prop="shouji">
						<el-input v-model="sessionForm.shouji" placeholder="PHONE" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='yonghu'" label="头像" prop="touxiang">
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="sessionForm.touxiang?sessionForm.touxiang:''"
							@change="yonghutouxiangHandleAvatarSuccess"
							></file-upload>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='yonghu'" label="余额">
						<div class="balance-item">
							<el-input v-model="sessionForm.money" placeholder="余额" readonly></el-input>
							<div class="balanceBtn" @click="dialogFormVisibleMoney = true">
								<span class="icon iconfont icon-tijiaoyanzi"></span>
								<span class="text">点我充值</span>
							</div>
						</div>
					</el-form-item>
					<el-form-item class="center-btn-item">
						<div class="updateBtn" type="primary" @click="onSubmit('sessionForm')">
							<span class="icon iconfont "></span>
							<span class="text">确定</span>
						</div>
						<div class="closeBtn" type="danger" @click="logout">
							<span class="icon iconfont "></span>
							<span class="text">退出登录</span>
						</div>
					</el-form-item>
				</el-form>
			</el-tab-pane>
			<el-tab-pane label="UPDATE密码">
				<el-form class="center-preview-pv" ref="passwordForm" :model="passwordForm" :rules="passwordRules" label-width="180px">
					<el-form-item class="center-item" label="原密码" prop="password">
						<el-input type="password" v-model="passwordForm.password" placeholder="原密码"></el-input>
					</el-form-item>
					<el-form-item class="center-item" label="新密码" prop="newpassword">
						<el-input type="password" v-model="passwordForm.newpassword" placeholder="新密码"></el-input>
					</el-form-item>
					<el-form-item class="center-item" label="确认密码" prop="repassword">
						<el-input type="password" v-model="passwordForm.repassword" placeholder="确认密码"></el-input>
					</el-form-item>
					<el-form-item class="center-btn-item">
						<div class="updateBtn" type="primary" @click="updatePassword">
							<span class="icon iconfont "></span>
							<span class="text">UPDATE密码</span>
						</div>
					</el-form-item>
				</el-form>
			</el-tab-pane>
			<el-tab-pane v-for="(item,index) in menuList" :key="index" v-if="hasBack(item.menu)" :label="item.child[0].menu" :name="item.child[0].tableName"></el-tab-pane>
			<el-tab-pane label="My Account订单"></el-tab-pane>
			<el-tab-pane label="My Account地址" name="MyAddress">
				<router-view></router-view>
			</el-tab-pane>
			<el-tab-pane label="My Account收藏"></el-tab-pane>
		</el-tabs>

		<el-dialog title="User充值" :visible.sync="dialogFormVisibleMoney" width="726px" center>
			<el-form :model="chongzhiForm">
				<el-form-item label="充值金额" label-width="120px">
					<el-input type="number" v-model="chongzhiForm.money" autocomplete="off" placeholder="充值金额"></el-input>
				</el-form-item>
				<el-form-item label-width="120px">
					<el-radio-group v-model="chongzhiForm.radio">
						<el-radio style="margin-bottom: 30px" label="微信支付">
							<el-image
								style="width: 60px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/weixin.png')"
								fit="fill"></el-image>
							<span style="display: inline-block;margin-left: 10px">微信支付</span>
						</el-radio>
						<el-radio label="支付宝支付">
							<el-image
								style="width: 60px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/zhifubao.png')"
								fit="fill"></el-image>
							<span style="display: inline-block;margin-left: 10px">支付宝支付</span>
						</el-radio>
						<el-radio label="中国建设银行支付">
							<el-image
								style="width: 120px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/jianshe.png')"
								fit="fill"></el-image>
						</el-radio>
						<el-radio label="中国农业银行支付">
							<el-image
								style="width: 126px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/nongye.png')"
								fit="fill"></el-image>
						</el-radio>
						<el-radio label="中国银行支付">
							<el-image
								style="width: 140px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/zhongguo.png')"
								fit="fill"></el-image>
						</el-radio>
						<el-radio label="交通银行支付">
							<el-image
								style="width: 120px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/jiaotong.png')"
								fit="fill"></el-image>
						</el-radio>
					</el-radio-group>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialogFormVisibleMoney = false">取 消</el-button>
				<el-button type="primary" @click="chongzhi">确认充值</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	import config from '@/config/config';
	import menu from '@/config/menu';
	import Vue from 'vue';
	export default {
		//数据集合
		data() {
			return {
				title: '个人中心',
				baseUrl: config.baseUrl,
				sessionForm: {},
				passwordForm: {},
				passwordRules: {
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
				},
				rules: {},
				chongzhiForm: {
					money: '',
					radio: ''
				},
				menuList: [],
				disabled: false,
				dialogFormVisibleMoney: false,
				uploadUrl: config.baseUrl + 'file/upload',
				imageUrl: '',
				headers: {Token: localStorage.getItem('frontToken')},
				userTableName: localStorage.getItem('UserTableName'),
				dynamicProp: {},
			}
		},
		created() {
			let menus = menu.list()
			for(let x in menus){
				if(menus[x].tableName == this.userTableName){
					for(let i in menus[x].backMenu){
						if(menus[x].backMenu[i].child&&menus[x].backMenu[i].child.length&&menus[x].backMenu[i].child[0].tableName.indexOf('exam')!=-1){
							menus[x].backMenu.splice(i,1)
						}
					}
					this.menuList = menus[x].backMenu
				}
			}
			if ('yonghu' == this.userTableName) {
				this.$set(this.sessionForm, 'zhanghao', null);
			}
			if ('yonghu' == this.userTableName) {
				this.$set(this.sessionForm, 'mima', null);
			}
			if ('yonghu' == this.userTableName) {
				this.$set(this.sessionForm, 'xingming', null);
			}
			if ('yonghu' == this.userTableName) {
				this.$set(this.sessionForm, 'xingbie', null);
			}
			if ('yonghu' == this.userTableName) {
				this.$set(this.sessionForm, 'shouji', null);
			}
			if ('yonghu' == this.userTableName) {
				this.$set(this.sessionForm, 'touxiang', null);
			}
			if ('yonghu' == this.userTableName) {
				this.$set(this.sessionForm, 'money', null);
			}

			if ('yonghu' == this.userTableName&&this.rules['zhanghao']){
				this.rules['zhanghao'].push({ required: true, message: '请输入账号', trigger: 'blur' })
			}else if('yonghu' == this.userTableName&&!this.rules['zhanghao']) {
				this.$set(this.rules, 'zhanghao', [{ required: true, message: '请输入账号', trigger: 'blur' }]);
			}
			if ('yonghu' == this.userTableName&&this.rules['mima']){
				this.rules['mima'].push({ required: true, message: '请输入密码', trigger: 'blur' })
			}else if('yonghu' == this.userTableName&&!this.rules['mima']) {
				this.$set(this.rules, 'mima', [{ required: true, message: '请输入密码', trigger: 'blur' }]);
			}
			if ('yonghu' == this.userTableName) {
				this.$set(this.rules, 'shouji', [{ required: false, validator: this.$validate.isMobile, trigger: 'blur' }]);
			}
			if ('yonghu' == this.userTableName) {
				this.$set(this.rules, 'money', [{ required: false, validator: this.$validate.isNumber, trigger: 'blur' }]);
			}

			this.init();
			this.sessionForm = JSON.parse(localStorage.getItem('sessionForm'))
		},
		//方法集合
		methods: {
			init() {
				if ('yonghu' == this.userTableName) {
					this.dynamicProp.xingbie = '男,女'.split(',');
				}
			},
			setSession(){
				localStorage.setItem('sessionForm',JSON.stringify(this.sessionForm))
			},
			onSubmit(formName) {
				if(`yonghu` == this.userTableName && this.sessionForm.touxiang!=null){
					this.sessionForm.touxiang = this.sessionForm.touxiang.replace(new RegExp(this.$config.baseUrl,"g"),"");
				}
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$http.post(this.userTableName + '/update', this.sessionForm).then(res => {
							if (res.data.code == 0) {
								this.setSession()
								this.$message({
									message: '更新成功',
									type: 'success',
									duration: 1500
								});
							}
						});
					} else {
						return false;
					}
				});
			},
			yonghutouxiangHandleAvatarSuccess(fileUrls) {
				this.sessionForm.touxiang = fileUrls;
			},
			chongzhi() {
				if (this.chongzhiForm.money == '') {
					this.$message({
						message: '请输入充值金额',
						type: 'error',
						duration: 1500
					});
					return;
				}
				if (this.chongzhiForm.money <= 0) {
					this.$message({
						message: '请输入正确的充值金额',
						type: 'error',
						duration: 1500
					});
					return;
				}
				if (this.chongzhiForm.radio == '') {
					this.$message({
						message: '请选择充值方式',
						type: 'error',
						duration: 1500
					});
					return;
				}
				if(!this.sessionForm.money) {
					this.sessionForm.money = parseFloat(this.chongzhiForm.money)
				}else{
					this.sessionForm.money = parseFloat(this.sessionForm.money) + parseFloat(this.chongzhiForm.money);
				}
				
				this.$http.post(this.userTableName + '/update', this.sessionForm).then(res => {
					if (res.data.code == 0) {
						this.$http.post('chargerecord/add',{
							username: localStorage.getItem("username"),
							role: localStorage.getItem("frontRole"),
							amount: parseFloat(this.chongzhiForm.money),
							userid: this.sessionForm.id
						}).then(rs=>{
							this.setSession()
							this.$message({
								message: '充值成功',
								type: 'success',
								duration: 1500,
								onClose: () => {
									this.dialogFormVisibleMoney = false;
								}
							});
						})
					}
				});
			},
			handleClick(tab, event) {
				switch(event.target.outerText) {
					case '个人中心':
						tab.$router.push('/index/center');
						break;
					case 'UPDATE密码':
						this.passwordForm = {
							password: '',
							newpassword: '',
							repassword: '',
						}
						this.$forceUpdate()
						break;
					case 'My Account订单':
						tab.$router.push('/index/shop-order/order');
						break;
					case 'My Account地址':
						tab.$router.push('/index/shop-address/list');
						break;
					case 'My Account收藏':
						localStorage.setItem('storeupType', 1);
						tab.$router.push('/index/storeup');
						break;
					default:
						tab.$router.push(`/index/${tab.name}?centerType=1`);
				}

				this.title = event.target.outerText;
			},
			async updatePassword(){
				this.$refs["passwordForm"].validate(async valid => {
					if (valid) {
						var password = "";
						if (this.sessionForm.mima) {
							password = this.sessionForm.mima;
						} else if (this.sessionForm.password) {
							password = this.sessionForm.password;
						}
						if (this.userTableName == 'yonghu') {
						}
						var nowpassword = ''
						await this.$http.get('encrypt/des?text=' + this.passwordForm.password,).then(res=>{
							if(res.data&&res.data.code==0){
								nowpassword = res.data.data
							}
						})
						if(nowpassword!=password){
							this.$message.error("原密码错误");
							return;
						}
						if (this.passwordForm.newpassword != this.passwordForm.repassword) {
							this.$message.error("两次密码输入不一致");
							return;
						}
						if (this.passwordForm.newpassword == this.passwordForm.password) {
							this.$message.error("新密码与原密码相同！");
							return;
						}
						this.sessionForm.password = this.passwordForm.newpassword;
						this.sessionForm.mima = this.passwordForm.newpassword;
						this.$http.post(`${this.userTableName}/update`,this.sessionForm).then(({data})=>{
							if (data && data.code === 0) {
								this.$message({
									message: "UPDATE密码成功,下次登录系统生效",
									type: "success",
									duration: 1500,
									onClose: () => {
									}
								});
								this.setSession()
							} else {
								this.$message.error(data.msg);
							}
						});
					}
				})
			},
			logout() {
				localStorage.clear();
				Vue.http.headers.common['Token'] = "";
				this.$router.push('/index/home');
				this.activeIndex = '0'
				localStorage.setItem('keyPath', this.activeIndex)
				this.$forceUpdate()
				this.$message({
					message: '登出成功',
					type: 'success',
					duration: 1500,
				});
			},
			hasBack(name){
				switch(name){
					case 'Order Management':
						return false
						break;
					case 'My Account收藏管理':
						return false
						break;
					default:
						return true
				}
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.center-preview {
		padding: 0;
		margin: 0px auto;
		color: #666;
		background: none;
		display: flex;
		width: 1200px;
		font-size: 16px;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.center-title {
			padding: 0;
			margin: 20px 0 0;
			color: #fff;
			background: url(http://codegen.caihongy.cn/20241228/f151b89f53c94b7fa5ae510c2f7bd563.png) no-repeat center bottom;
			width: 100%;
			font-size: 32px;
			line-height: 100px;
			position: relative;
			text-align: center;
			height: 191px;
		}
		.center-info {
			border: 0px solid #fcc285;
			padding: 80px 20px 20px 280px;
			margin: 0 auto;
			color: #fff;
			display: none;
			min-height: 220px;
			line-height: 2;
			flex-wrap: wrap;
			border-radius: 0;
			box-shadow: none;
			overflow: hidden;
			align-content: flex-start;
			background: url(http://codegen.caihongy.cn/20241026/6862e5282b0241c4ad58379c4d145c18.png) no-repeat center top;
			width: 1400px;
			justify-content: flex-start;
			align-items: flex-start;
			position: relative;
			height: auto;
			order: 2;
			.center-info-title {
				color: #333;
				display: none;
				width: 100%;
				font-size: 18px;
				line-height: 40px;
				text-align: center;
				height: 40px;
			}
			.img-box {
				top: 10px;
				left: 20px;
				display: inline-block;
				width: auto;
				font-size: 0;
				border-color: #efefef;
				border-width: 0 0 0px 0;
				position: absolute;
				border-style: solid;
				height: auto;
				img {
					border-radius: 100%;
					margin: 40px auto 0 40px;
					object-fit: cover;
					display: block;
					width: 130px;
					height: 130px;
				}
			}
			.info-item1 {
				padding: 5px 0;
				display: flex;
				width: auto;
				border-color: #ddd;
				border-width: 0 0 0px 0;
				line-height: inherit;
				border-style: dashed;
				min-width: 25%;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
			.info-item2 {
				padding: 5px 0;
				display: flex;
				width: auto;
				border-color: #ddd;
				border-width: 0 0 0px 0;
				line-height: inherit;
				border-style: dashed;
				min-width: 25%;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
			.info-item3 {
				padding: 5px 0;
				display: flex;
				width: auto;
				border-color: #ddd;
				border-width: 0 0 0px 0;
				line-height: inherit;
				border-style: dashed;
				min-width: 25%;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
			.info-item4 {
				padding: 5px 0;
				display: flex;
				width: auto;
				border-color: #ddd;
				border-width: 0 0 0px 0;
				line-height: inherit;
				border-style: dashed;
				min-width: 25%;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
			.info-item5 {
				padding: 5px 0;
				display: flex;
				width: auto;
				border-color: #ddd;
				border-width: 0 0 0px 0;
				line-height: inherit;
				border-style: dashed;
				min-width: 25%;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
			.info-item6 {
				padding: 5px 0;
				display: flex;
				width: auto;
				border-color: #ddd;
				border-width: 0 0 0px 0;
				line-height: inherit;
				border-style: dashed;
				min-width: 25%;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
		}
		.center-tabs.el-tabs {
			border: 1px solid #27385c50;
			border-radius: 4px;
			padding: 20px;
			margin: 30px auto;
			background: #fff;
			display: flex;
			width: 1400px;
			flex-wrap: wrap;
			order: 3;
			/deep/ .el-tabs__header {
				border-radius: 4px;
				padding: 10px 10px 0;
				margin: 0 0 20px;
				background: #f5f5f5;
				width: 180px;
				border-color: #39496950;
				border-width: 1px;
				position: relative;
				border-style: solid;
				text-align: center;
			}
			/deep/ .el-tabs__header .el-tabs__item {
				border: 1px solid #ddd;
				padding: 0;
				margin: 0 0 10px;
				color: #333;
				font-weight: 500;
				display: block;
				font-size: inherit;
				line-height: 48px;
				border-radius: 0px;
				background: none;
				width: 100%;
				position: relative;
				text-align: center;
				height: 48px;
			}
			/deep/ .el-tabs__header .el-tabs__item:hover {
				border: 0;
				color: #fff;
				background: #3397da;
			}
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				border: 0;
				margin: 0 0 10px;
				color: #fff;
				background: #3397da;
				width: 100%;
			}
			/deep/ .el-tabs__content {
				padding: 20px;
				background: none;
				flex: 1;
				display: flex;
				width: calc(100% - 200px);
				justify-content: space-between;
				flex-wrap: wrap;
			}
			/deep/ .el-tabs__content .el-tab-pane {
				background: none;
				width: 100%;
			}
			& /deep/ .el-tabs__header {
				.el-tabs__nav{
					overflow: auto;
				}
				::-webkit-scrollbar {
					-webkit-appearance: none;
					width: 6px;
					height: 6px;
				}
				::-webkit-scrollbar-track {
					background: rgba(0, 0, 0, 0.1);
					border-radius: 0;
				}
				::-webkit-scrollbar-thumb {
					cursor: pointer;
					border-radius: 5px;
					background: rgba(0, 0, 0, 0.15);
					transition: color 0.2s ease;
				}
				::-webkit-scrollbar-thumb:hover {
					background: rgba(0, 0, 0, 0.3);
				}
				.el-tabs__nav-wrap {
					margin: 0;
					&::after {
						content: none;
					}
				}
				.el-tabs__active-bar {
					display: none !important;
				}
			}
			.center-preview-pv {
				.center-item.el-form-item {
					border-radius: 0px;
					padding: 6px 0 0;
					margin: 0 0 20px 0;
					background: #fff;
					border-color: #475a8310;
					border-width:  0 0 0px;
					border-style: solid;
					/deep/ .el-form-item__label {
						padding: 0 10px 0 0;
						color: inherit;
						font-weight: 500;
						width: 180px;
						font-size: inherit;
						line-height: 40px;
						text-align: right;
					}
					.el-form-item__content {
						margin-left: 180px;
					}
					.el-input {
						width: 100%;
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
						color: inherit;
						background: none;
						width: auto;
						font-size: 16px;
						height: 40px;
					}
					.el-select {
						width: auto;
					}
					.el-select /deep/ .el-input__inner {
						border: 1px solid #ddd;
						border-radius: 4px;
						padding: 0 10px;
						box-shadow: none;
						color: inherit;
						width: auto;
						font-size: 16px;
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
						width: auto;
						font-size: 16px;
						height: 40px;
					}
					.el-date-editor {
						width: 100%;
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
						font-size: 32px;
						line-height: 80px;
						text-align: center;
						height: 80px;
					}
					/deep/ .el-upload-list .el-upload-list__item {
						border: 1px solid #ddd;
						cursor: pointer;
						border-radius: 4px;
						color: #999;
						background: #fff;
						width: 80px;
						font-size: 32px;
						line-height: 80px;
						text-align: center;
						height: 80px;
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
						font-size: 32px;
						line-height: 80px;
						text-align: center;
						height: 80px;
					}
					/deep/ .el-upload__tip {
						color: #838fa1;
						font-size: 16px;
					}
					/deep/ .el-input__inner::placeholder {
						color: #999;
						font-size: inherit;
					}
					.balance-item {
						display: flex;
						flex-wrap: wrap;
						.el-input {
							width: auto;
						}
						.el-input /deep/ .el-input__inner {
							border: 1px solid #ddd;
							border-radius: 4px;
							padding: 0 12px;
							box-shadow: none;
							color: inherit;
							background: #fff;
							display: inline-block;
							width: auto;
							font-size: 16px;
							height: 40px;
						}
						.balanceBtn {
							border: 0;
							cursor: pointer;
							border-radius: 4px;
							padding: 0 15px;
							margin: 0 20px 0 10px;
							background: #475a8330;
							display: inline-block;
							width: auto;
							font-size: 14px;
							line-height: 40px;
							height: 40px;
							.icon {
								color: #333;
							}
							.text {
								color: #333;
							}
						}
						.balanceBtn:hover {
							.icon {
								color: #000;
							}
							.text {
								color: #000;
							}
						}
					}
				}
				.center-btn-item {
					padding: 0;
					margin: 30px 0 0;
					width: 100%;
					text-align: center;
					.updateBtn {
						border: 0;
						cursor: pointer;
						padding: 0 24px 0 30px;
						margin: 0 20px 0 0;
						display: inline-block;
						font-size: 16px;
						line-height: 44px;
						border-radius: 2px;
						background: #3397da;
						width: auto;
						text-align: center;
						min-width: 120px;
						height: 44px;
						.icon {
							color: #fff;
						}
						.text {
							color: #fff;
						}
					}
					.updateBtn:hover {
						.icon {
						}
						.text {
						}
					}
					.closeBtn {
						border: 0px solid #ddd;
						cursor: pointer;
						padding: 0 24px 0 30px;
						margin: 0 20px 0 0;
						color: #80593c;
						display: inline-block;
						font-size: 16px;
						line-height: 44px;
						border-radius: 2px;
						background: #243559;
						width: auto;
						text-align: center;
						min-width: 120px;
						height: 44px;
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
				.el-date-editor.el-input {
					width: auto;
				}
			}
		}
	}
</style>
