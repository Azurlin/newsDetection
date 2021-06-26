<template>
<el-card class="box-card" shadow="never" >
    <el-row>
        <el-col :span="4">
            <div > 
                <img style="width:50%;height:auto;" :src="imgurl"/> 
            </div>
        </el-col>
        <el-col :span="20">
            <span class="fontsty"><a :href="newsurl"><div class="texttitle">{{title}}</div></a></span>
            <div style="margin-top:5px;text-align:left">
               <p><el-tag class="fontsty" type="warning" dark>{{date}}</el-tag>,{{author}}</p>
            </div>
        </el-col>                               
    </el-row>
</el-card>
</template>

<script>

export default {
    props:{
        title:String,
        text:String,
        author:String,
        createDate:Date,
        sign:Number,
        newsid:Number
    },
  data() {
    return {
        imgurl:"https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/"+ this.$props.sign+ ".png",
        dialogTableVisible: false,
        date:'',
        newsurl:''
    };
  },
      
  methods: {

  },
  mounted() {
      this.newsurl = 'http://localhost:8080/#/news/'+ this.$props.newsid
      this.date = this.$props.createDate.slice(0,10)     
},
};
</script>

<style scoped>
.texttitle{
white-space: nowrap;
overflow: hidden;
text-overflow: ellipsis;
max-width: 800px;
}
.fontsty{
        font-weight:bold;
}
</style>
