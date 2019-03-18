<template>
    <div>   
       <!-- <h2>添加角色</h2> -->
       <div class="top">
           <p>添加员工</p>
       </div>
       <div class="content">
           <div class="content-top">
               <el-form :model="form" class="roleform">
                <el-form-item label="员工ID:" :label-width="formLabelWidth">
                  <el-input v-model="form.userid" autocomplete="off" size="small" style="width:30%"></el-input>
                </el-form-item>
                <el-form-item label="员工密码:" :label-width="formLabelWidth">
                  <el-input v-model="form.password" autocomplete="off" size="small" style="width:30%"></el-input>
                </el-form-item>
                <el-form-item label="员工姓名:" :label-width="formLabelWidth">
                  <el-input v-model="form.username" autocomplete="off" size="small" style="width:30%"></el-input>
                </el-form-item>
              </el-form>
           </div>
           <!-- <p>权限</p> -->
           <div class="allotrole">
              <div class="rolelist">
                 <span>分配角色：</span>
                 &nbsp
                 <template>
                    <!-- `checked` 为 true 或 false -->
                    <el-checkbox v-model="checked">运营</el-checkbox>
                    <el-checkbox v-model="checked">客服</el-checkbox>
                    <el-checkbox v-model="checked">仓库</el-checkbox>
                    <el-checkbox v-model="checked">拣货员</el-checkbox>
                  </template>
              </div>
            
           </div>
           
           
       </div>
       <div class="bobutton">
           <el-button @click="dialogVisible = false">关 闭</el-button>
           <el-button type="primary" @click="saveNotice">保 存 </el-button>
       </div>
       
    </div>
</template>


<script>
    // const cityOptions = ['上海', '北京', '广州', '深圳'];
    export default{
        data(){
            return {               
               msg:'组件',
               formLabelWidth: '120px',
               form: {
                userid: '',
                password: '',
                username: ''
              },
              checked: false,
              // checkAll: false,
              // checkedCities: ['上海', '北京'],
              // cities: cityOptions,
              // isIndeterminate: true
            }
        },
        methods:{
          //   handleCheckAllChange(val) {
          //       this.checkedCities = val ? cityOptions : [];
          //       this.isIndeterminate = false;
          //   },
          //   handleCheckedCitiesChange(value) {
          //       let checkedCount = value.length;
          //       this.checkAll = checkedCount === this.cities.length;
          //       this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
          // }
        }      
    }

</script>

<style scoped>
    .top {
        width: 1200px;
        height: 38px;
        background-color: #F2F2F2;
        border: 1px solid #CCCCCC;
    }
    .top p {
        margin-top: 7px;
        margin-left: 10px;
        color: #333368;
        font-weight: bold;
    }
    .content {
        width: 1200px;
        height: 910px;
        border: 1px solid #CCCCCC;
        margin-top: 20px;

    }
    .content .content-top{
        width: 100%;
        height: 200px;
        border-bottom: 1px solid #CCCCCC;
        /* background-color: pink; */
    }
    .roleform {
        padding-top: 15px
    }
    .allotrole {
        width: 100%;
        height: 100px;
        border-bottom: 1px solid #CCCCCC;
    }
    .allotrole .rolelist {
      padding-top: 40px; 
      padding-left: 46px;
    }
    
    
    
    .checkall {
        display: inline-block;
    }
    .bobutton {
        margin-top: 20px;
        margin-left: 1040px;
    }
</style>