<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px">
      <el-form :inline="true">
        <el-form-item>
          <el-input type="text" v-model="inTitle"  placeholder="输入标题关键字"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getNews">查询</el-button>
        </el-form-item>
        <!-- <el-form-item>
          <el-button type="primary" @click="handleAdd">新增</el-button>
        </el-form-item> -->
        <!-- <el-form-item>
          <el-button type="success" @click="detectionNewsListHandle">批量检测</el-button>
        </el-form-item> -->
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="news"  highlight-current-row  v-loading="listLoading"  @selection-change="selsChange"  style="width: 100%" >
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column type="index" width="60"> </el-table-column>
      <el-table-column :show-overflow-tooltip="true" prop="title" label="标题" width="120" sortable>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" prop="sign" label="标识" width="100" sortable >
        <template slot-scope="scope">
          <span v-if="scope.row.sign === 1" style="color: green">真</span>
          <span v-else-if="scope.row.sign === 0" style="color: red" >假</span>
          <span v-else style="color: black">待检测</span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" prop="author" label="作者" min-width="80" sortable>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" prop="content" label="内容" min-width="180" sortable>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template scope="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)" >编辑</el-button >
          <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)" >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <div style="padding-top: 5px">
        <span style="display: inline"> </span>
			<!-- <el-upload style="display: inline" class="upload-demo"
			action="http://localhost:8088/news/upload"
			:data="{
				datasetId: this.$route.params.id,
			}"
			multiple
			:limit="1"
			:file-list="fileList"
			>
          	<el-button type="success">导入数据</el-button>
        </el-upload> -->
      </div>
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="10" :total="total" style="float: right" >
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm" >
        <el-form-item label="标题" prop="title">
          <el-input v-model="editForm.title" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input type="textarea" v-model="editForm.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>

    <!--新增界面-->
    <el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
        <el-form-item label="标题" prop="title">
          <el-input v-model="addForm.title" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input type="textarea" v-model="addForm.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="addFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
import {
addIndexNews,
getIndexNewsList,
removeIndexNews,
editIndexNews,
getIndexNews
} from "../../../api/api.js";

export default {
  data() {
    return {
      inTitle: "",
      news: [],
      total: 0,
      pageNum: 0,
      pageSize: 10,
      listLoading: false,
      sels: [], //列表选中列

      editFormVisible: false, //编辑界面是否显示
      editLoading: false,
      editFormRules: {
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
      },
      //编辑界面数据
      editForm: {
        id: null,
        title: "",
        content: "",
      },

      addFormVisible: false, //新增界面是否显示
      addLoading: false,
      addFormRules: {
        title: [{ required: true, message: "请输入标题", trigger: "blur" }],
      },
      //新增界面数据
      addForm: {
        title: "",
        content: "",
      },
    };
  },
  methods: {
    highlight(row, column) {
      if (row.sign === 0) {
        return true;
      }
      return false;
    },
    stateFormat(row, column) {
      if (row.sign === 1) {
        return "真";
      } else if (row.sign === 0) {
        return "假";
      } else {
        return "待检测";
      }
    },
    detectionNewsListHandle() {
      //批量检测
      let param = {
        datasetId: this.$route.params.id,
      };
      newsListDetection(param).then((res) => {
        newsCount(param).then((res) => {
          console.log(res.data);
        });
        this.getNews();
      });
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getNews();
    },
    //获取新闻列表
    getNews() {
      let para = {
        datasetId: this.$route.params.id,
        pageNum: this.pageNum - 1,
        pageSize: this.pageSize,
        title: this.inTitle,
      };
      this.listLoading = true;
      getIndexNewsList(para).then((res) => {
        this.news = res.data.data;
        this.total = res.data.data;
        console.log(this.news);
        this.listLoading = false;
      });
    },
    //删除
    handleDel: function (index, row) {
      console.log("id值" + row.id + "====" + index);
      this.$confirm("确认删除该记录吗?", "提示", {
        type: "warning",
      })
        .then(() => {
          this.listLoading = true;
          let para = { id: row.id };
          let para2 = {
            datasetId: this.$route.params.id,
          };
          removeIndexNews(para).then((res) => {
            this.listLoading = false;
            this.$message({
              message: "删除成功",
              type: "success",
            });
            newsCount(para2).then((res) => {
              this.getNews();
            });
          });
        })
        .catch(() => {});
    },
    //显示编辑界面
    handleEdit: function (index, row) {
      this.editFormVisible = true;
      this.editForm = Object.assign({}, row);
      //console.log("xxxxxx" + row.id);
      //console.log("yyyyyy" + this.editForm.id);
    },
    //显示新增界面
    handleAdd: function () {
      this.addFormVisible = true;
      this.addForm = {
        datasetId: this.$route.params.id,
        title: "",
        content: "",
      };
    },
    //编辑
    editSubmit: function () {
      this.$refs.editForm.validate((valid) => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            this.editLoading = true;
            let para = Object.assign({}, this.editForm);
            editIndexNews(para).then((res) => {
              console.log("修改" + para.id);
              this.editLoading = false;
              this.$message({
                message: "提交成功",
                type: "success",
              });
              this.$refs["editForm"].resetFields();
              this.editFormVisible = false;
              this.getNews();
            });
          });
        }
      });
    },
    //新增
    addSubmit: function () {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            this.addLoading = true;
            let para = Object.assign({}, this.addForm);
            let para2 = {
              datasetId: this.$route.params.id,
            };
            addIndexNews(para).then((res) => {
              console.log(para);
              console.log("结果：" + res);
              this.addLoading = false;
              this.$message({
                message: "提交成功",
                type: "success",
              });
              this.$refs["addForm"].resetFields();
              this.addFormVisible = false;
              newsCount(para2).then((res) => {
                this.getNews();
              });
            });
          });
        }
      });
    },
    selsChange: function (sels) {
      this.sels = sels;
    },
    //批量删除
    batchRemove: function () {
      var ids = this.sels.map((item) => item.id).toString();
      this.$confirm("确认删除选中记录吗？", "提示", {
        type: "warning",
      })
        .then(() => {
          this.listLoading = true;
          let para = { ids: ids };
          let para2 = {
            datasetId: this.$route.params.id,
          };
          batchRemoveNews(para).then((res) => {
            this.listLoading = false;
            this.$message({
              message: "删除成功",
              type: "success",
            });

            newsCount(para2).then((res) => {
              this.getNews();
            });
          });
        })
        .catch(() => {});
    },
  },
  mounted() {
    this.getNews();
  },
};
</script>

<style scoped>
</style>