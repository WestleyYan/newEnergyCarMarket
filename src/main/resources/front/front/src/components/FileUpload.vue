<template>
	<div>
		<!-- 上传文件组件 -->
		<el-upload v-if="type==1" ref="upload" :action="getActionUrl" list-type="picture-card" :multiple="multiple" :limit="limit"
			:headers="myHeaders" :file-list="fileList" :on-exceed="handleExceed" :on-preview="handleUploadPreview"
			:on-remove="handleRemove" :on-success="handleUploadSuccess" :on-error="handleUploadErr" :timeout="600000"
			:before-upload="handleBeforeUpload">
			<i class="el-icon-plus"></i>
			<div slot="tip" class="el-upload__tip">{{tip}}</div>
		</el-upload>
		<el-upload v-else drag ref="upload" :action="getActionUrl" :multiple="multiple" :limit="limit"
			:headers="myHeaders" :file-list="fileList" :on-exceed="handleExceed" :on-preview="handleUploadPreview"
			:on-remove="handleRemove" :on-success="handleUploadSuccess" :on-error="handleUploadErr" :timeout="600000"
			:before-upload="handleBeforeUpload">
			<i class="el-icon-upload"></i>
			  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
			<div slot="tip" class="el-upload__tip" >{{tip}}</div>
		</el-upload>
		<el-dialog :visible.sync="dialogVisible" size="tiny" append-to-body>
			<img width="100%" v-if="type==1" :src="dialogImageUrl" alt>
			<video width="100%" v-if="type==2" :src="dialogImageUrl" alt controls />
		</el-dialog>
	</div>
</template>
<script>
	import storage from "@/common/storage";
	import config from "@/config/config";
	export default {
		data() {
			return {
				baseUrl: config.baseUrl,
				baseUrl2: config.name,
				// 查看大图
				dialogVisible: false,
				// 查看大图
				dialogImageUrl: "",
				// 组件渲染Image的数组字段，有特殊格式要求
				fileList: [],
				fileUrlList: [],
				myHeaders: {}
			};
		},
		props: {
			tip: {
				type: String
			},
			action: {
				type: String
			},
			/*Image大小*/
			limit: {
				type: Number,
				default: 3 //kb
			},
			multiple: {
				type: Boolean,
				default: false
			},
			fileUrls: {
				type: String
			},
			type: {
				type: Number,
				default: 1
			}
		},
		mounted() {
			this.init();
			this.myHeaders = {
				'Token': storage.get("frontToken")
			}
		},
		watch: {
			fileUrls: function(val, oldVal) {
				//   console.log("new: %s, old: %s", val, oldVal);
				this.init();
			}
		},
		computed: {
			// 计算属性的 getter
			getActionUrl: function() {
				return this.baseUrl2 + '/' + this.action;
			}
		},
		methods: {
			// 初始化
			init() {
				if (this.fileUrls) {
					this.fileUrlList = this.fileUrls.split(",");
					let fileArray = [];
					this.fileUrlList.forEach(function(item, index) {
						var url = item;
						var name = index;
						var file = {
							name: name,
							url: url
						};
						fileArray.push(file);
					});
					this.setFileList(fileArray);
				}else {
					this.fileUrlList = [];
					this.setFileList(this.fileUrlList);
				}
			},
			handleBeforeUpload(file) {

			},
			// 上传文件成功后执行
			handleUploadSuccess(res, file, fileList) {
				if (res && res.code === 0) {
					fileList[fileList.length - 1]["url"] = "upload/" + file.response.file;
					this.setFileList(fileList);
					this.$emit("change", this.fileUrlList.join(","));
				} else {
					this.$message.error(res.msg);
				}
			},
			// Image上传失败
			handleUploadErr(err, file, fileList) {
				this.$message.error("文件上传失败");
			},
			// 移除Image
			handleRemove(file, fileList) {
				this.setFileList(fileList);
				this.$emit("change", this.fileUrlList.join(","));
			},
			// 查看大图
			handleUploadPreview(file) {
				if(this.type>2){
					window.open(file.url)
					return false
				}
				this.dialogImageUrl = file.url;
				this.dialogVisible = true;
			},
			// 限制Image数量
			handleExceed(files, fileList) {
				this.$message.warning(`最多上传${this.limit}张Image`);
			},
			// 重新对fileList进行赋值
			setFileList(fileList) {
				var fileArray = [];
				var fileUrlArray = [];
				// 有些Image不是公开的，所以需要携带token信息做权限校验
				var token = storage.get("frontToken");
				let _this = this;
				fileList.forEach(function(item, index) {
					var url = item.url.split("?")[0];
					if (!url.startsWith("http")) {
						url = _this.baseUrl + url
					}
					var name = item.name;
					var file = {
						name: name,
						url: url + "?token=" + token
					};
					fileArray.push(file);
					fileUrlArray.push(url);
				});
				this.fileList = fileArray;
				this.fileUrlList = fileUrlArray;
			}
		}
	};
</script>
<style lang="scss" scoped>
</style>