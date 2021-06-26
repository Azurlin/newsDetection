<template>
  <el-col :span="4" class="second_pad">
    <el-card :body-style="{ padding: '0px' }">
      <img
        style="height: auto"
        src="https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/xm.png"
        class="image"
      />
      <div style="padding: 14px">
        <span v-text="datasetName"></span>
        <span class="time" v-text="datasetId"><time></time></span>
        <div class="bottom clearfix">
          <el-button
            type="text"
            class="el-icon-delete button_del"
            style="color: red"
            @click="delopen"
            >删除</el-button
          >
          <el-button
            type="text"
            @click="gotoDataManager"
            class="el-icon-document button"
            >详情</el-button
          >
        </div>
      </div>
    </el-card>
  </el-col>
</template>

<script>
	import { removeDataset,getDatasetList } from '../api/api.js';

export default {
    props:{
        datasetName:String,
        datasetId:Number,
    },
  data() {
    return {
        dialogTableVisible: false,
    };
  },
      
  methods: {
    gotoDataManager() {
      //数据集详情按钮跳转到数据管理界面
      //this.$router.replace("/table/"+this.$props.datasetId);
      this.$router.push("/table/"+this.$props.datasetId)
    },
    //dialogTableVisible: false,

     delopen() {
       var that = this;
        this.$confirm('此操作将永久删除该数据集, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let para={
            datasetId:this.$props.datasetId
          }
          console.log(that.$parent.$data.items)
          removeDataset(para).then((res) => {
      
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
             getDatasetList().then((res) =>{
              that.$parent.$data.items = res.data.data
              console.log("dada")
              console.log(that.$parent.$data.items)
              console.log(res.data.data)
            })
					});
          
            that.$nextTick();
            that.$forceUpdate();

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },
  },
};
</script>

<style scoped>
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
  .button_del{
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
      clear: both
  }
time{
  padding-left: 20%;
}

</style>
