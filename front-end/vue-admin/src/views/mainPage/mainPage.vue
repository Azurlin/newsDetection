<template>
  <el-row class="container">
    <el-col :span="24" class="header" style="ma">
      <el-col >
       <img class="logow" src="https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/NEWS.png" />
      </el-col>
    </el-col>
    

      <el-tabs v-model="activeName"  @tab-click="datasetPagehandle">
        <el-tab-pane label="新闻检测" name="first">
          <el-form
            ref="form"
            :model="form"
            label-width="80px"
            class="first_data"
          >
            <!-- ===========================first================================== -->
            <el-form-item label="新闻标题">
              <el-input v-model="first_newsTitle"></el-input>
            </el-form-item>
            <el-form-item
              label="新闻内容"
              :rules="{
                required: true,
                message: '内容不能为空',
                trigger: 'blur',
              }"
            >
              <el-input
                type="textarea"
                :rows="4"
                v-model="first_newsText"
              ></el-input>
            </el-form-item>
            <el-button type="primary" class="first_data_btn" @click="newsDetectionHandle">提交数据</el-button>
            <el-form class="first_data_res">
              <el-alert
                v-show="detectionTrueRes"
                title="真实新闻"
                type="success"
                description="这则新闻可信度较高"
                show-icon
              >
              </el-alert>
              <el-alert
                v-show="detectionWarningRes"
                title="新闻内容为空"
                type="warning"
                description="请输入需要检测的新闻内容"
                show-icon
              >
              </el-alert>
              <el-alert
                v-show="detectionFakeRes"
                title="虚假新闻"
                type="error"
                description="这则新闻可信度较低"
                show-icon
              >
              </el-alert>
            </el-form>
          </el-form>
          <!-- ===========================first================================== -->
        </el-tab-pane>
        <el-tab-pane label="批量新闻检测" name="second">
          <!-- ===========================second================================== -->
          <span class="second_add_btn">
            <el-button type="primary" size="large" @click.native="createBut">创建数据集</el-button>
          </span>
          <!-- ===== 弹出框 ===== -->
          <el-dialog title="创建数据集" :visible.sync="dialogFormVisible">
            <el-form :model="form">
              <el-form-item label="数据集名称" :label-width="formLabelWidth">
                <el-input v-model="inName" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <!-- ====上传===== -->
            <el-upload
              class="upload-demo"
              ref="upload"
              action="http://localhost:8088/news/upload"
              :data="{
                datasetId: this.datasetId,
              }"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :file-list="fileList"
              :auto-upload="false"
            >
              <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
              <div slot="tip" class="el-upload__tip">
                只能上传csv文件，且不超过1M
              </div>
            </el-upload>

            <!-- ========= -->

            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="createDataset">确 定</el-button>
            </div>
          </el-dialog>
          <!-- ===== 弹出框 ===== -->

          <el-row  class="second_data_row" id="second_data_row">
            <card v-for="i in items" :key='i' :datasetName="i.name" :datasetId="i.datasetId"></card>
          </el-row>

          <!-- ===========================second================================== -->
        </el-tab-pane>
      </el-tabs>
   
  </el-row>
</template>

<script>
import card from "../../components/datasetCard";
import { addDataset,getDatasetList,newsDetection,newsListDetection } from "../../api/api";
export default {
  components: {
    card,
  },
  data() {
    return {
      detectionWarningRes:null,
      detectionFakeRes:null,
      detectionTrueRes:null,
      first_newsTitle:"",
      first_newsText:null,
      activeName:"first",
      datasetId: 0,
      createDatasetId: null,
      inName: "",
      items: [],//存数据集
      dialogFormVisible: false,
      currentDate: "2021/3/10/14:34", //创建数据集时获取时间
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
    };
  },
  methods: {
    
    newsDetectionHandle(){ //单条新闻识别
      this.detectionTrueRes = false
      this.detectionFakeRes = false
      if (this.first_newsText == null || this.first_newsText =="") {
        this.detectionWarningRes = true;
        return;
      }

      console.log(this.first_newsText)
      let param = {
          newsTitle:this.first_newsTitle,
          newsText:this.first_newsText
      }
      newsDetection(param).then((res) =>{
          if (res.data == 'Fake') {
            this.detectionWarningRes = false
            this.detectionTrueRes = false
            this.detectionFakeRes = true
          }else{
            this.detectionWarningRes = false
            this.detectionFakeRes = false
            this.detectionTrueRes = true
          }
      })
    },
    datasetPagehandle(tab){
      if(tab.name == 'second'){//点击第二个标签
        	// 触发‘配置管理’事件
        	console.log("555555555555555555555555555555555555")
          getDatasetList().then((res) =>{
            console.log("-------------")
            console.log(res);
            console.log(res.data);
            console.log(res.data.data)
            this.items = res.data.data
            //this.$forceUpdate
          })
        }
        
    },
    /*gotoDataManager(){ //数据集详情按钮跳转到数据管理界面
      this.$router.replace('/table')
    },*/
    onSubmit() {
      console.log("submit!");
    },
    beforeRemove() {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    //文件上传成功返回结果
    handle_success(res) {
      console.log(res);
    },
    //创建数据集id
    getDatasetIdByTimestamp() {
      var timestamp = new Date().getTime();
      console.log(timestamp);
      timestamp = timestamp + Math.floor(Math.random() * 1000);
      console.log(timestamp);
      return timestamp;
    },
    createBut() {
      //点击创建数据集生成唯一数据集id
      console.log("啦啦啦拉拉阿拉啦啦啦啦啦啦啦啦阿拉蕾");
      this.datasetId = this.getDatasetIdByTimestamp();
      this.dialogFormVisible = true;
    },

    //创建数据集
    submitUpload() {
      this.$refs.upload.submit();
    },
    createDataset() {
      //上传文件
      
      if (this.inName == "") {
        //this.dialogFormVisible = false
        return this.$confirm(`请输入数据集名称`);
      }
      this.$refs.upload.submit();//上传
      let para = {
        datasetId: this.datasetId,
        name: this.inName,
      };
      addDataset(para).then((res) => {
        this.items.push(res.data)
        return this.$confirm(`创建成功`); 
      });
      this.$forceUpdate();
      this.dialogFormVisible = false;

    },
  },
};
//注册组件
</script>
<style scoped lang="scss">
@import "~scss_vars";
.container {
  position: absolute;
  top: 0px;
  bottom: 0px;
  width: 100%;
  .header {
    height: 60px;
    line-height: 60px;
    background:  #EEF1F6;
    color: #000;
  }
}
.logo {
  //width:230px;
  height: 60px;
  font-size: 22px;
  padding-left: 20px;
  padding-right: 20px;
  border-color: rgba(238, 241, 146, 0.3);
  border-right-width: 1px;
  //border-right-style: solid;
  img {
    width: 40px;
    float: left;
    margin: 10px 10px 10px 18px;
  }
  .txt {
    color: #fff;
  }
}
.nav {
  padding-top: 5px;
  padding-left: 2px;
  padding-right: 20px;
}
.first_data {
  padding-left: 5%;
  width: 600px;
}
.first_data_btn {
  margin-left: 13%;
}
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 300px;
}
.first_data_res {
  padding-top: 5%;
  padding-left: 13%;
}
//=====================================
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}
.button_del {
  padding: 0;
  padding-left: 0px;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
.second_data_row {
  padding-left: 10px;
}
.second_pad {
  padding-left: 15px;
  padding-top: 10px;
}
.second_upload {
  height: 500px;
}
.second_add_btn {
  padding-left: 26px;
}
.time {
  padding-left: 20%;
}
.el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
  .search{
    width: 700px;
    
    margin-left: 15%;
  }
  .check{
    margin-left: 10%;
    margin-top: 1%;
  }
  .logow{
    height:50px;
    width:160px;
    margin-left: 1%;
    margin-top: 5px;
}
</style>