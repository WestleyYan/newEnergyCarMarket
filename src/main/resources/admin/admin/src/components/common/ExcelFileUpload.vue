<template>
  <div>
    <!-- 上传文件组件 -->
    <el-upload
      ref="upload"
      :action="getActionUrl"
      list-type="picture-card"
      accept=".xls,.xlsx"
      :limit="limit"
      :headers="myHeaders"
      :on-exceed="handleExceed"
      :on-preview="handleUploadPreview"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-error="handleUploadErr"
      :before-upload="handleBeforeUpload"
      :show-file-list="false"
    >
      <i class="el-icon-plus"></i>
      <div slot="tip" class="el-upload__tip" style="color:#838fa1;">{{tip}}</div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible" size="tiny" append-to-body>
      <img width="100%" :src="dialogImageUrl" alt>
    </el-dialog>
  </div>
</template>
<script>
import storage from "@/utils/storage";
import base from "@/utils/base";
export default {
  data() {
    return {
      // 查看大图
      dialogVisible: false,
      // 查看大图
      dialogImageUrl: "",
      // 组件渲染Image的数组字段，有特殊格式要求
      fileList: [],
      fileUrlList: [],
      myHeaders:{}
    };
  },
  props: ["tip", "action", "limit", "multiple", "fileUrls"],
  mounted() {
    this.init();
    this.myHeaders= {
      'Token':storage.get("Token")
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
		console.log(123)
		this.fileList = []
      // return base.url + this.action + "?token=" + storage.get("token");
      return `/${this.$base.name}/` + this.action;
    }
  },
  methods: {
    // 初始化
    init() {
      //   console.log(this.fileUrls);
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
        this.$message.success("文件导入成功");
      } else {
        this.$message.error(res.msg);
      }
    },
    // Image上传失败
    handleUploadErr(err, file, fileList) {
      this.$message.error("文件导入失败");
    },
    // 移除Image
    handleRemove(file, fileList) {
      this.setFileList(fileList);
      this.$emit("change", this.fileUrlList.join(","));
    },
    // 查看大图
    handleUploadPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    // 限制Image数量
    handleExceed(files, fileList) {
      this.$message.warning(`数据已经ADD，请刷新重试!`);
    },
    // 重新对fileList进行赋值
    setFileList(fileList) {
      var fileArray = [];
      var fileUrlArray = [];
      // 有些Image不是公开的，所以需要携带token信息做权限校验
      var token = storage.get("token");
      let _this = this;
      fileList.forEach(function(item, index) {
        var url = item.url.split("?")[0];
    if(!url.startsWith("http")) {
      url = _this.$base.url+url
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

