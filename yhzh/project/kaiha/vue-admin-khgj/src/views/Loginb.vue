<template>
  <div >
    

    <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign" class="demo-ruleForm login-container">

      <h3 class="title">系统登录</h3>

      <el-form-item label="账号">
        <el-input v-model="formLabelAlign.name"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="formLabelAlign.region"></el-input>
      </el-form-item>

      <el-checkbox v-model="formLabelAlign"  class="remember">记住密码</el-checkbox>

      <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click="sendParams">登录</el-button>
    </el-form-item>
    </el-form>


 <!--  <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px" class="demo-ruleForm login-container">
    <h3 class="title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="密码" @keyup.enter.native="handleSubmit2"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit2" :loading="logining">登录</el-button>
    </el-form-item>
  </el-form> -->
  </div>
</template>


<script>

export default {
    data() {
      return {
        labelPosition: 'right',
        formLabelAlign: {
          name: '',
          region: ''
         
        }
      };
    },

    methods: {
      sendParams () {
        this.$router.push({
            path: 'home', 
            // name: '要跳转的路径的 name,在 router 文件夹下的 index.js 文件内找',
            params: { 
                name: 'name', 
                dataObj: this.msg
            }
            /*query: {
                name: 'name', 
                dataObj: this.msg
            }*/
        })
      }
    },


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
    width: 400px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
}

.remember {
      margin: 0px 0px 30px 0px;
      position: relative;
      left: 147px;
    }


</style>

