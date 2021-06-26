<template>
  <el-form :model="ruleForm" :rules="rules2" ref="ruleForm" label-position="left" label-width="0px" class="demo-ruleForm login-container">
    <h3 class="title">用户注册</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="ruleForm.account" auto-complete="off" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="ruleForm.checkPass" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item prop="email">
      <el-input type="text" v-model="ruleForm.email" auto-complete="off" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item prop="phone">
      <el-input type="text" v-model="ruleForm.phone" auto-complete="off" placeholder="手机号码"></el-input>
    </el-form-item>
    <el-form-item style="width:100%;">
        <el-button type="success" style="width:100%;" @click="checkEmail">注册</el-button>
      <!--<el-button @click.native.prevent="handleReset2">重置</el-button>-->
    </el-form-item>
    <el-form-item style="width:100%;">
        <el-button style="width:100%;" @click="backToLogin">返回登录</el-button>
      <!--<el-button @click.native.prevent="handleReset2">重置</el-button>-->
    </el-form-item>
  </el-form>
</template>

<script>
  import { requestLogin } from '../api/api';
  //import NProgress from 'nprogress'
  export default {
    data() {
      return {
        logining: false,
        ruleForm: {
          account: '',
          checkPass: '',
          email:'',
          phone:'',
        },
        rules2: {
          account: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            //{ validator: validaePass }
          ],
          checkPass: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            //{ validator: validaePass2 }
          ],
          email: [
            { required: true, message: '请输入邮箱', trigger: 'blur' },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
            //{ validator: validaePass2 }
          ],
          phone: [
            { required: true, message: '请输入手机号码', trigger: 'blur' },
            //{ validator: validaePass2 }
          ]
        },
        checked: true
      };
    },
    methods: {
        
        // checkEmail(){
        //     var re = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        //     console.log(re.test(this.ruleForm.email))
        //     if (!re || this.ruleForm.email == "" ) {
        //         this.rules2.email.required = false
        //     }
            
        // },
        backToLogin(){
            this.$router.push({ path: '/login' });
        },
        loginHandle(){
            this.$router.push({ path: '/' });
        },
      handleReset2() {
        this.$refs.ruleForm.resetFields();
      },
      handleSubmit2(ev) {
        var _this = this;
        this.$refs.ruleForm.validate((valid) => {
          if (valid) {
            //_this.$router.replace('/table');
            this.logining = true;
            //NProgress.start();
            var loginParams = { username: this.ruleForm.account, password: this.ruleForm.checkPass };
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