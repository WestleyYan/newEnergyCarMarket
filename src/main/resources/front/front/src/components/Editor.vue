<template>
	<div>
		<!-- Image上传组件辅助-->
		<el-upload :class="refname + 'upload'" :action="getActionUrl" name="file" :headers="header" :show-file-list="false"
			:on-success="uploadSuccess" :on-error="uploadError" :before-upload="beforeUpload"></el-upload>

		<quill-editor class="editor" v-model="value" :ref="myQuillEditor" :options="editorOption"
			@blur="onEditorBlur($event)" @focus="onEditorFocus($event)" @change="onEditorChange($event)"></quill-editor>
	</div>
</template>
<script>
	// 工具栏配置
	const toolbarOptions = [
		["bold", "italic", "underline", "strike"], // 加粗 斜体 下划线 DELETE线
		["blockquote", "code-block"], // 引用  代码块
		[{
			header: 1
		}, {
			header: 2
		}], // 1、2 级标题
		[{
			list: "ordered"
		}, {
			list: "bullet"
		}], // 有序、无序列表
		[{
			script: "sub"
		}, {
			script: "super"
		}], // 上标/下标
		[{
			indent: "-1"
		}, {
			indent: "+1"
		}], // 缩进
		[{
			size: ["small", false, "large", "huge"]
		}], // 字体大小
		[{
			header: [1, 2, 3, 4, 5, 6, false]
		}], // 标题
		[{
			color: []
		}, {
			background: []
		}], // 字体颜色、字体背景颜色
		[{
			font: []
		}], // 字体种类
		[{
			align: []
		}], // 对齐方式
		["clean"], // 清除文本格式
		["link", "image", "video"] // 链接、Image、视频
	];
	var _EditorOption_ = function(page_this) {
		return {
			modules: {
				toolbar: {
					container: toolbarOptions,
					handlers: {
						image(value) {
							if (value) {
								console.log(document.querySelector("." + page_this.refname + "upload input"))
								// 触发input框选择Image文件
								document.querySelector("." + page_this.refname + "upload input").click();
							} else {
								this.quill.format("image", false);
							}
						}
					},
				},
			},
			placeholder: "请输入正文",
		};
	};
	import config from "@/config/config";
	import {
		quillEditor
	} from "vue-quill-editor";
	import "quill/dist/quill.core.css";
	import "quill/dist/quill.snow.css";
	import "quill/dist/quill.bubble.css";

	export default {
		props: {
			/*编辑器的内容*/
			value: {
				type: String
			},
			action: {
				type: String
			},
			/*Image大小*/
			maxSize: {
				type: Number,
				default: 4000 //kb
			},
			myQuillEditor: {
				type: String
			}
		},

		components: {
			quillEditor
		},

		data() {
			return {
				baseUrl: config.baseUrl,
				baseUrl2: config.name,
				refname: this.myQuillEditor,
				content: this.value,
				quillUpdateImg: false, // 根据Image上传状态来确定是否显示loading动画，刚开始是false,不显示
				editorOption: _EditorOption_(this),
				header: {
					'Token': localStorage.getItem('frontToken')
				} // 有的Image服务器要求请求头需要有token
			};
		},
		computed: {
			// 计算属性的 getter
			getActionUrl: function() {
				return this.baseUrl2 + '/' + this.action;
			}
		},
		methods: {
			onEditorBlur() {
				//失去焦点事件
			},
			onEditorFocus() {
				//获得焦点事件
			},
			onEditorChange() {
				console.log(this.value);
				//内容改变事件
				this.$emit("input", this.value);
			},
			// 富文本Image上传前
			beforeUpload() {
				// 显示loading动画
				this.quillUpdateImg = true;
			},

			uploadSuccess(res, file) {
				// res为Image服务器返回的数据
				// 获取富文本组件实例
				let quill = this.$refs[this.refname].quill;
				// 如果上传成功
				if (res.code === 0) {
					// 获取光标所在位置
					let length = quill.getSelection().index;
					// 插入Image  res.url为服务器返回的Image地址
					quill.insertEmbed(length, "image", this.baseUrl + "upload/" + res.file);
					// 调整光标到最后
					quill.setSelection(length + 1);
				} else {
					this.$message.error("Image插入失败");
				}
				// loading动画消失
				this.quillUpdateImg = false;
			},
			// 富文本Image上传失败
			uploadError() {
				// loading动画消失
				this.quillUpdateImg = false;
				this.$message.error("Image插入失败");
			}
		}
	};
</script>

<style>
	.editor {
		line-height: normal !important;
	}

	.ql-snow .ql-tooltip[data-mode="link"]::before {
		content: "请输入链接地址:";
	}

	.ql-snow .ql-tooltip.ql-editing a.ql-action::after {
		border-right: 0px;
		content: "保存";
		padding-right: 0px;
	}

	.ql-snow .ql-tooltip[data-mode="video"]::before {
		content: "请输入视频地址:";
	}

	.ql-container {
		height: 400px;
	}

	.ql-snow .ql-picker.ql-size .ql-picker-label::before,
	.ql-snow .ql-picker.ql-size .ql-picker-item::before {
		content: "14px";
	}

	.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="small"]::before,
	.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="small"]::before {
		content: "10px";
	}

	.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="large"]::before,
	.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="large"]::before {
		content: "18px";
	}

	.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="huge"]::before,
	.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="huge"]::before {
		content: "32px";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item::before {
		content: "文本";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
		content: "标题1";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
		content: "标题2";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
		content: "标题3";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
		content: "标题4";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
		content: "标题5";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
		content: "标题6";
	}

	.ql-snow .ql-picker.ql-font .ql-picker-label::before,
	.ql-snow .ql-picker.ql-font .ql-picker-item::before {
		content: "标准字体";
	}

	.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="serif"]::before,
	.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="serif"]::before {
		content: "衬线字体";
	}

	.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="monospace"]::before,
	.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="monospace"]::before {
		content: "等宽字体";
	}
</style>