<template>
  <div class="bg">
    <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px" class="demo-ruleForm login-container">
      <div class="loginleft">
        <img src="../assets/kaihua.jpg">
      </div>
      <div class="loginright">
        <h1 class="title">智能化云平台集成系统</h1>
        <el-form-item prop="account">
          <el-input type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="请输入您的用户名" style="width:320px"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
          <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="请输入您的密码" style="width:320px"></el-input>
        </el-form-item>
        <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
        <el-form-item style="width:100%;">
          <el-button type="primary" style="width:320px;" @click.native.prevent="handleSubmit2" :loading="logining">登录</el-button>
        </el-form-item>
        
      </div>
        
    </el-form>
  </div>
</template>

<script>
  import { userLoginInfo } from '../service/getData';
  import {setSession} from '../common/js/util'
  export default {
    data() {
      return {
        logining: false,
        ruleForm2: {
          account: '',
          checkPass: ''
        },
        rules2: {
          account: [
            { required: true, message: '请输入账号', trigger: 'blur' },
          ],
          checkPass: [
            { required: true, message: '请输入密码', trigger: 'blur' },
          ]
        },
        checked: true
      };
    },
      methods: {
      handleSubmit2() {
        var _this = this;
        this.$refs.ruleForm2.validate((valid) => {
          if (valid) {
            _this.logining = true;
            var loginUser = { username: _this.ruleForm2.account, password: _this.ruleForm2.checkPass };
            // 存储登录session
              userLoginInfo(loginUser,res=>{
                  if(res.data.length>0){
                    // 用户信息
                    setSession('UserInfo',res.data[0]);
                    // 用户名字
                    setSession('name',res.data[0].name);
                   _this.$router.push({ path: '/home' });
                  }else{
                    _this.logining=false
                    _this.$message.error('用户名或密码错误');
                  }
              })
              // 本地保存用户名
              // _this.$store.commit('sessionName', _this.ruleForm2.account);
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
    margin: 100px auto;
    width: 60%;
    height: 500px;
    padding: 30px ;
    background: #eee;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    .loginleft {
      width:50%;
      height:500px;
      float:left;
    }

    .loginleft img{
      height:100%;
      width:100%;
    }
    .loginright {
      width:50%;
      float:right;
      margin:50px 0 0 0;
    }
    .title {
      margin: 0px 0px 20px 0px;
      text-align: center;
      color: #505458;
    }
    .remember {
      margin: 0px 0px 35px 0px;
      position: relative;
      left: 120px;
    }
  }
</style>