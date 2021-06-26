<template>
<div>
    <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px" class="demo-ruleForm login-container">
    <h3 class="title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <a style="float:right" @click="linkToRegister">用户注册</a>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;"  @click="loginHandle">登录</el-button>
    </el-form-item>
  </el-form>
      
      <!--<el-button @click.native.prevent="handleReset2">重置</el-button>-->
</div>

</template>

<script>
  import { userLogin,userRegister } from '../api/api';
  //import NProgress from 'nprogress'
  export default {
    data() {
      return {
        logining: false,
        ruleForm2: {
          account: 'admin',
          checkPass: '123456'
        },
        rules2: {
          account: [
            { required: true, message: '请输入账号', trigger: 'blur' },
            //{ validator: validaePass }
          ],
          checkPass: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            //{ validator: validaePass2 }
          ]
        },
        checked: true
      };
    },
    methods: {
      linkToRegister(){
        this.$router.push({ path: '/Register' });
      },
      loginHandle(){
        let param = {
          userName:this.ruleForm2.account,
          userPassword:this.ruleForm2.checkPass,
        }
        userLogin(param).then((res) => {
              
              if (res.data.data != null) {
                
                //console.log(document.cookie.get('userId'))
                this.$cookies.set('user','123')
                console.log(this.$cookies.get('user'))
                console.log(this.$cookies.get('userId'))
                this.$router.push({ path: '/' });
              }else{
                 this.$message({
                  type: 'info',
                  message: '用户名或密码错误'
                });
              }
              
        });
        
      },
      handleReset2() {
        this.$refs.ruleForm2.resetFields();
      },
      handleSubmit2(ev) {
        var _this = this;
        this.$refs.ruleForm2.validate((valid) => {
          if (valid) {
            //_this.$router.replace('/table');
            this.logining = true;
            //NProgress.start();
            var loginParams = { username: this.ruleForm2.account, password: this.ruleForm2.checkPass };
            requestLogin(loginParams).then(data => {
              this.logining = false;
              //NProgress.done();
              let { msg, code, user } = data;
              if (code !== 200) {
                this.$message({
                  message: msg,
                  type: 'error'
                });
              } else {
                sessionStorage.setItem('user', JSON.stringify(user));
                this.$router.push({ path: '/' });
              }
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    }
  }

</script>

<style lang="scss" scoped>
a:link { 
color: #000000; 
text-decoration: none; 
} 
a:visited { 
color: #26a3f7; 
text-decoration: none; 
} 
a:hover { 
color: #26a3f7; 
text-decoration: underline; 
}
  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    .title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }
    .remember {
      margin: 0px 0px 35px 0px;
    }
  }
</style>