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
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit2" :loading="logining">登录</el-button>
    </el-form-item>
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
    margin: 120px auto;
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