<template>
<div>
<el-row >
  <el-col :span="24">
      <div class="iheader"  >
        <el-menu  :default-active="activeIndex"  mode="horizontal" @select="handleSelect" >
        <span style="header-font"><img class="logow" src="https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/NEWS.png" /></span>   
        <el-menu-item class="el-menu-t" index="4"><a  target="_blank" @click="logOut">退出登录</a></el-menu-item>
        <el-submenu index="2" class="el-menu-t">
            <template slot="title">我的工作台</template>
            <el-menu-item index="2-1" @click="goToAdmin">后台管理</el-menu-item>
            
        </el-submenu>
        </el-menu>
      </div></el-col>
</el-row>
<!-- 搜索和功能按钮 -->
<el-row>
    <el-col :span="16">
      <div>
        <div class="isearch">
            <el-row>
                <el-col :span="3">
                    <el-select  v-model="value" placeholder="请选择">
                        <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
                <el-col :span="18">
                    <el-input v-model="input" placeholder="请输入内容">
                        
                    </el-input>
                </el-col>
                <el-col :span="3">
                    <el-button  round icon="el-icon-search" @click="searchHandle">搜索</el-button>
                </el-col>
            </el-row>  
        </div>
      </div>
    </el-col>
    <el-col :span="8">
        <div class="top-but">
            <el-button @click="linkToMainPage">虚假新闻检测</el-button>
        </div>
    </el-col>
</el-row>
<!-- 新闻分类标签 -->
<el-row>
    <el-col :span="24">

    
        <div class="ilable fontsty"  id="topAnchor">
            
            <el-row>
            <el-col :span="4" ><el-button type="warning" @click="getNewsByLabel('0')">首页</el-button></el-col>
            <el-col :span="4"><el-button type="warning" @click="getNewsByLabel('1')">国际</el-button></el-col>
            <el-col :span="4"><el-button type="warning" @click="getNewsByLabel('2')">科技</el-button></el-col>
            <el-col :span="4"><el-button type="warning" @click="getNewsByLabel('3')">健康</el-button></el-col>
            <el-col :span="4"><el-button type="warning" @click="getNewsByLabel('4')">体育</el-button></el-col>
            <el-col :span="4"><el-button type="warning" @click="getNewsByLabel('5')">娱乐</el-button></el-col>
            </el-row>
        </div>
         

    </el-col>
</el-row>
<!-- 展示位置布局 -->
<el-row>
    <el-col :span="3">  
        <div class="main-side"></div>
     </el-col>
    <el-col :span="18">
      <div class="main-center">
          <!-- 新闻展示与右侧边栏 -->
          <!-- 新闻展示栏 -->
        <el-row>
            <el-col :span="18">
                <div class="mainlist">
                    <div class="mainlist-card">
                        <el-card class="box-card">
                            <el-row>
                                <el-col :span="24" style="margin-bottom:5px;text-align:center">
                                    <div class="title">{{this.labeltext[ilabel]}}</div>
                                </el-col>                               
                            </el-row>
                            <div style="margin-top:5px">
                                <!-- card -->
                                <newscard v-for="i in items" :key='i' :title="i.title" :author="i.author" :createDate="i.createDate" :sign="i.sign" :newsid="i.id"></newscard>
                                
                            <div>
                                
                                <el-button @click="prePage" style="margin-bottom:10px;margin-top:5px;float:left"><i class="el-icon-arrow-left el-icon--left"></i>上一页</el-button>

                                <el-button @click="nextPage" style="margin-bottom:10px;margin-top:5px;float:right"><a>下一页</a><i class="el-icon-arrow-right el-icon--right"></i></el-button>
                            </div>
                            </div>
                                                                    
                        </el-card>
                    </div>

                </div>
            </el-col>
            <!-- 右侧边栏 -->
            <el-col :span="6">
                <div class="rightlist">
                      <div class="block">
                            <el-carousel height="150px">
                            <el-carousel-item v-for="item in imgItem" :key="item">
                                <h3 class="small"><img style="height:150px;width:300px" :src="item.src"/></h3>
                            </el-carousel-item>
                            </el-carousel>
                        </div>
                        <!-- 
                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <span class="fontsty">热点新闻TOP10</span>
                                <el-button style="float: right; padding: 3px 0" type="text">刷新</el-button>
                            </div>
                            <div v-for="o in 10" :key="o" class="text item">
                                {{'列表内容 ' + o }}
                            </div>
                            <div>
                                <a href="">
                                    更多...
                                </a>    
                            </div>
                        </el-card>

                        -->

                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <span class="fontsty">  
                                    <el-alert
                                    show-icon
                                    title="虚假新闻预测"
                                    type="warning"
                                    :closable="false">
                                    </el-alert>
                                </span>
                            </div>
                            <div v-for="o in fakeitems" :key="o" class="text item indextitle">
                                <a :href="getid(o.id)">{{ o.title }}</a>
                                
                            </div>
                            <div>
                                <a href="">
                                    更多...
                                </a>    
                            </div>
                        </el-card>

                </div>
            </el-col>
        </el-row>
      </div>
    </el-col>
    <el-col :span="3"><div class="main-side"></div></el-col>
</el-row>
</div>

</template>

<script>

import { addIndexNews,getIndexNewsList,removeIndexNews,editIndexNews } from '../../api/api.js';

import newscard from "../../components/newsCard.vue";
export default {
   components: {
    newscard,
  },
  data() {
    return {
        labeltext:['首页','国际','科技','健康','体育','娱乐'],
        ilabel:'0',
        imgItem:[
            {
                src:'https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/l1.jpg'
            },
            {
                src:'https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/l2.jpg'
            },
            {
                src:'https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/l6.png'
            }
        ],
        pageNum:0,
        pageSize:5,
        input:'',
        author:'',
        inTitle:'',
        items:[],
        fakeitems:[],
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
    logOut(){
        this.$router.push("/login")
    },
    goToAdmin(){
        this.$router.push("/admin")
    },
    getNewsByLabel(lab){
       this.pageNum = 0
        if (lab == '0') {
            this.ilabel=lab
            this.getNewsList();
            return
        }
         this.ilabel = lab
        console.log("%%%%%%%%%%%%%%%"+lab)
        let param = {
            label: this.ilabel,
            title: this.inTitle,
            author:this.author,
            pageNum:0,
            pageSize:this.pageSize
        }
        getIndexNewsList(param).then((res) => {
            console.log("=============")
            this.items = res.data.data;
            console.log(this.items);
            this.author=''
            this.inTitle=''
      });
    },
    getid(id){
        console.log(id)
        return "http://localhost:8080/#/news/" + id
    },
    fakeNewsShow(){

        let param = {
            sign:0,
            pageNum:0,
            pageSize:10
        }
        getIndexNewsList(param).then((res) => {
            console.log("=============")
            this.fakeitems = res.data.data;
            console.log(this.fakeitems);

            
      });
    },
    searchHandle(){
        this.ilabel=''
        console.log("++++++++++++"+this.value)
        if (this.value == 1) {
            this.inTitle = this.input
        }else if(this.value == 2){
            this.author = this.input
            
        }
        console.log("input===="+this.author)
            let param = {
            title: this.inTitle,
            author:this.author,
            pageNum:0,
            pageSize:this.pageSize
        }
        getIndexNewsList(param).then((res) => {
            console.log("=============")
            this.items = res.data.data;
            console.log(this.items);
      });
    },
    nextPage(){
        console.log("helooooooooooooooooooooooooooooooooooo");
        //this.pageNum++;
        let param = {
            label: this.ilabel,
            title: this.inTitle,
            author:this.author,
            pageNum:this.pageNum+1,
            pageSize:this.pageSize
        }
        getIndexNewsList(param).then((res) => {
            console.log("=============")
            
            if (res.data.data != 0) {
                this.items = res.data.data;
                this.pageNum++;
                console.log("pageNum->"+this.pageNum)
            }
            console.log(this.items);
      });
    },
    prePage(){
        if (this.pageNum > 0) {
            this.pageNum--;
            
        let param = {
            label: this.ilabel,
            title: this.inTitle,
            author:this.author,
            pageNum:this.pageNum,
            pageSize:this.pageSize
        }
        getIndexNewsList(param).then((res) => {
            console.log("=============")
            
            if (res.data.data != 0) {
                this.items = res.data.data;
                
                console.log("pageNum->"+this.pageNum)
            }
            console.log(this.items);
      });
            


            console.log("pageNum->"+this.pageNum)
        }
    },
    linkToMainPage(){
        this.$router.push("/mainPage")
    },
    getNewsList(page){
        let param = {
            label: this.ilabel,
            title: this.inTitle,
            author:this.author,
            pageNum:this.pageNum,
            pageSize:this.pageSize
        }
        getIndexNewsList(param).then((res) => {
            console.log("=============")
            this.items = res.data.data;
            console.log(this.items);
            this.author=''
            this.inTitle=''
            this.ilabel='0'
      });
    },

  },
  mounted() {
        this.getNewsList();
        this.fakeNewsShow();
        
        
},
};
</script>

<style scoped>
.el-button--warning{
    background: #99a9bf;
    border-color: #99a9bf;
    color: #FFF;
}
.newlable{
    border-radius:10px;
    border:2px solid  rgb(199, 198, 198);
    margin-top: 1%;
    margin-bottom: 10px;
    margin-left: 200px;
    height: 40px;
    width: 820px;
}
.logow{
    height:50px;
    width:160px;
    margin-left: 1%;
    margin-top: 5px;
}
.indextitle{
white-space: nowrap;
overflow: hidden;
text-overflow: ellipsis;
max-width: 800px;
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