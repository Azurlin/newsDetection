<template>
<div>
<el-row >
    <el-col :span="24">
      <div class="iheader"  >
        <el-menu  :default-active="activeIndex"  mode="horizontal" @select="handleSelect" >
        <el-button @click="back" style="margin-left:6%;margin-top:13px"><i class="el-icon-arrow-left el-icon--left"></i>返回</el-button> 
        <el-menu-item class="el-menu-t" index="4"><a href="" target="_blank">退出登录</a></el-menu-item>
        <el-submenu index="2" class="el-menu-t">
            <template slot="title">我的工作台</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
            <el-menu-item index="2-3">选项3</el-menu-item>
            <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项1</el-menu-item>
            <el-menu-item index="2-4-2">选项2</el-menu-item>
            <el-menu-item index="2-4-3">选项3</el-menu-item>
            </el-submenu>
        </el-submenu>
        </el-menu>
      </div>
    </el-col>
</el-row>

<el-row>
    <el-col :span="4">
        <div class="side">
  
        </div>
    </el-col>
    <el-col :span="16">
        <div class="dcol2">
            <el-card class="dcol">
            <div>
            <el-row class="newstitle">
                <h1>{{items.title}}</h1>    
            </el-row>

            <el-alert
                    title="该新闻可信度较低，谨防受骗"
                    type="warning"
                    v-show="detectionFakeRes"
                    :closable="false"
                    show-icon>
            </el-alert>
            <el-alert
                    title="该新闻可信度较高"
                    type="success"
                    v-show="detectionTrueRes"
                    :closable="false"
                    show-icon>
            </el-alert>
            <el-row>
                <el-col :span="24" style="margin-top:5px;margin-bottom:5px;text-align:center">
                    <div class="title">{{date}},{{items.author}}</div>
                </el-col>                               
            </el-row>

            <el-row>
                <div class="textdiv">
                    <span  class="newstitlefont">
                        <p>
                            {{items.content}}

                        </p>
                    </span>
                </div>

            </el-row>
            </div>

            <el-row style="margin-top:5px;" >
                            <div class="bottom">                               
                                <el-button @click="prePage" style="margin-bottom:10px;margin-top:5px;float:left"><i class="el-icon-arrow-left el-icon--left"></i></el-button>
                                <el-button @click="nextPage" style="margin-bottom:10px;margin-top:5px;float:right"><a></a><i class="el-icon-arrow-right el-icon--right"></i></el-button>
                            </div>
            </el-row>
            </el-card>
        </div>
    </el-col>
    <el-col :span="4">
        <div class="grid-content bg-purple">

        </div>
    </el-col>
</el-row>

</div>

</template>

<script>

import {getIndexNews } from '../../api/api.js';


export default {
   components: {
    
  },
  data() {
    return {
        detectionFakeRes:false,
        detectionTrueRes:false,
        date:'',
        items:[],
        options: [{
          value: '1',
          label: '标题'
        }, {
          value: '2',
          label: '作者'
        }
        ],
        value: '1'
    };
  },

methods: {
    back(){
        this.$router.back()
    },
   getNews(id){
        getIndexNews(id).then((res) => {
            console.log("=============")
            this.items = res.data.data;
            console.log(this.items);
            this.date = this.items.createDate.slice(0,10)
            if (this.items.sign == 0) {
                this.detectionFakeRes = true;
            }else if(this.items.sign == 1){
                this.detectionTrueRes = true;
            }
      });

   }

  },
  mounted() {
        //this.getNewsList();
        //this.fakeNewsShow();
        this.getNews(this.$route.params.id)
},
};
</script>

<style scoped>
.bottom{

}
.textdiv{
    padding-left: 2%;
}
.newstitlefont{
 font-size: 20px;
 text-indent:2em;
}
.newstitle{

}
.side{
    height: 800px;
}
.dcol{
    height: auto;
    margin-top: 2%;
    margin-bottom: 5%;
}
a{text-decoration:none}
.tag {
    height:30px;
    width:80px;
}
.header-font{
    color: #000;

}
.title{
    position: relative;
    text-align: center;
    overflow: hidden;
    font-size: 14px;
    color: #999;
}
.title::before,.title::after{
    content: '';
    display: inline-block;
    width: 100%;
    height: 1px;
    position: absolute;
    background: #ccc;
    top: 50%;
}
.title::before{
    margin-left: -10px;
    transform: translateX(-100%);
}
.title::after{
    margin-left: 10px;
}
.mainlist-card{
    margin-right: 10px;
}
.img{
    height: 80px;
    width: 90px;
    background-color: yellow;
}
.el-menu-t{
    float:right;
}
.fontsty{
        font-weight:bold;
}
.itabs{
    float: right;
}
.iheader{
    height: 50px;
    background-color: black;
}
.isearch{
    margin-left: 25%;
    margin-top: 15px;
    width: 750px;
}
.top-but{
    margin-top: 15px;

}
.ilable{
    margin-top: 2%;
    height: 50px;
    width: 500px;
    margin-left: 17%;
}
.mainlist{
    height: 600px;
}
.rightlist{
    height: 600px;
    
}
.main-side{
    height: 600px;
    background-color:white;
}
.main-center{
    height: 600px;
        
}
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  } 
    .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    margin-top: 6px;
    
  }
</style>