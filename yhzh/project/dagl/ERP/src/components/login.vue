<template>
    <div class="login-wrap">
        <div class="ms-title">登录管理系统</div>
        <div class="ms-login">
            <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-width="0px" class="demo-ruleForm">
                
                <el-form-item prop="account">
                    <el-input v-model="ruleForm2.account" placeholder="账号" ></el-input>
                </el-form-item>
                <el-form-item prop="checkPass">
                    <el-input type="password" placeholder="密码" v-model="ruleForm2.checkPass" @keyup.enter.native="submitForm('ruleForm2')"></el-input>
                </el-form-item>

                <div class="hehe">
                    
                </div>
                <el-form-item style="width:100%;">
                  <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit2" :loading="logining">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>    
</template>

<script>
    import { userLoginInfo } from '../service/getData';
    import {setSession} from '../common/js/util'
    export default {
        // name: 'login',
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
            }
        },
        mounted() {

            // if(!($.setSession('userIsLogin')=='true')){
            //   //如果用户并未登录 直接跳转到登录界面
            //   this.$router.push('/login')
            // }
          
        },
        methods: {
             handleSubmit2() {
                // this.$router.push('/home');
                var _this = this;

                this.$refs.ruleForm2.validate((valid) => {

                    if (valid) {
                        _this.logining = true;
                        // var loginUser = { userid: _this.ruleForm2.account, password: _this.ruleForm2.checkPass };
                        var userid = _this.ruleForm2.account
                        var password = _this.ruleForm2.checkPass
                        console.log(userid+"--------"+password)

                        // 存储登录session
                        userLoginInfo(userid,password,res=>{
                                // console.log(loginUser+"888888888888888888888")
                                console.log(res)
                              if(res.recode==0){
                                // 用户信息
                                // setSession('UserInfo',res.data[0]);
                                // 用户名字
                                // setSession('name',res.data.roleid);
                                // setSession('shop_id',res.data.shop_id);
                                setSession('user_id',res.userid);
                                setSession('shop_id',res.shop_id);
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

<style scoped>
    .login-wrap{
        background-color: #76A3A3;
        position: relative;
        width:100%;
        height:950px;
        text-align: center;
    }
    .ms-title {
        font-size: 25px;
        font-weight: bold;
        color: #fff;
        padding-top: 250px;
        padding-bottom: 10px;
    }
    .ms-login{
        margin: 0 auto;
        padding-top: 50px;
        padding-bottom: 30px;
        padding-left: 30px;
        padding-right: 30px;
        width:300px;
        height:240px;
        border-radius: 5px;
        background: #fff;
    }
    .ms-login span {
        color: red;
    }
    .hehe {
        height: 30px;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
    }
    .code {
        width: 112px;
        height: 35px;
        border: 1px solid #ccc;
        float: right;
        border-radius: 2px;
    }
    .validate-code {
        width: 136px;
        float: left;
    }
    .register {
        font-size:14px;
        line-height:30px;
        color:#999;
        cursor: pointer;
        float:right;
    }
</style>