<template>
    <div>   
       <!-- <h2>添加角色</h2> -->
       <div class="top">
           <p>添加角色</p>
       </div>
       <div class="content">
           <div class="content-top">
               <el-form :model="form" class="roleform">
                <el-form-item label="角色名称:" :label-width="formLabelWidth">
                  <el-input v-model="form.title" autocomplete="off" size="small" style="width:30%"></el-input>
                </el-form-item>
                <el-form-item label="角色描述:" :label-width="formLabelWidth">
                  <el-input type="textarea"  v-model="form.desc" :rows="3" style="width:48%"></el-input>
                </el-form-item>
              </el-form>
           </div>
           <!-- <p>权限</p> -->
           <div class="allpower">
               <span>权限：</span>
               &nbsp
               <template>
                  <!-- `checked` 为 true 或 false -->
                  <el-checkbox v-model="checked">全选</el-checkbox>
                </template>
           </div>
           <div class="powerlist">
               <div class="list">
                   <div class="listtitle">
                       <p>首页</p>
                   </div>
                   <div class="powercheck">
                    <!--    <el-checkbox :indeterminate="isIndeterminate" class="checkall" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox></br>
                        <div style="margin: 15px 0;"></div>
                      <el-checkbox-group v-model="checkedCities" @change="handleCheckedCitiesChange">
                        <el-checkbox v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox>
                      </el-checkbox-group> -->

                      <el-checkbox v-model="checked">全部</el-checkbox>
                      <el-checkbox v-model="checked">业绩栏</el-checkbox>
                      <el-checkbox v-model="checked">常用工具</el-checkbox>
                   </div>
               </div>
               <div class="list">
                   <div class="listtitle">
                       <p>交易管理</p>
                   </div>
                   <div class="powercheck">
                       <el-checkbox v-model="checked">全部</el-checkbox>
                      <el-checkbox v-model="checked">店铺维护</el-checkbox>
                      <el-checkbox v-model="checked">全部订单</el-checkbox>
                      <el-checkbox v-model="checked">24小时到期订单</el-checkbox>
                      <el-checkbox v-model="checked">代发货订单</el-checkbox>
                      <el-checkbox v-model="checked">已发货订单</el-checkbox>
                      <el-checkbox v-model="checked">被取消订单</el-checkbox>
                      <el-checkbox v-model="checked">标签管理</el-checkbox>
                   </div>
               </div>
               <div class="list">
                   <div class="listtitle">
                       <p>仓库管理</p>
                   </div>
                   <div class="powercheck">
                       <el-checkbox v-model="checked">全部</el-checkbox>
                      <el-checkbox v-model="checked">店铺维护</el-checkbox>
                      <el-checkbox v-model="checked">全部订单</el-checkbox>
                      <el-checkbox v-model="checked">24小时到期订单</el-checkbox>
                      <el-checkbox v-model="checked">代发货订单</el-checkbox>
                      <el-checkbox v-model="checked">已发货订单</el-checkbox>
                      <el-checkbox v-model="checked">被取消订单</el-checkbox>
                      <el-checkbox v-model="checked">标签管理</el-checkbox>
                   </div>
               </div>
               <div class="list">
                   <div class="listtitle">
                       <p>系统管理</p>
                   </div>
                   <div class="powercheck">
                       <el-checkbox v-model="checked">全部</el-checkbox>
                      <el-checkbox v-model="checked">店铺列表管理</el-checkbox>
                      <el-checkbox v-model="checked">采购账号管理</el-checkbox>
                      <el-checkbox v-model="checked">子账号管理</el-checkbox>
                   </div>
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
                title: '',
                desc: ''
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
        height: 168px;
        border-bottom: 1px solid #CCCCCC;
        /* background-color: pink; */
    }
    .roleform {
        padding-top: 15px
    }
    .allpower {
        margin-top: 10px;
        margin-left: 50px
    }
    .powerlist {
        width: 1095px;
        height: 650px;
        /* background-color: pink; */
        margin: 0 auto;
        margin-top: 25px;
        border: 1px solid #797979;
    }
    .powerlist .list {
        height: 118px;
        width: 998px;
        margin: 0 auto;
        border: 1px solid #CCCCCC;
        margin-top: 35px;

    }
    .powerlist .list .powercheck {
        margin-top: 25px;
        margin-left: 30px;
    }

    .powerlist .list .listtitle {
        width: 100%;
        height: 39px;
        border-bottom: 1px solid #CCCCCC;
        background-color: #F2F2F2;
    }
    .powerlist .list .listtitle p {
        color: #333333;
        font-weight: bold;
        padding-top: 7px;
        padding-left: 10px;
    }
    .checkall {
        display: inline-block;
    }
    .bobutton {
        margin-top: 20px;
        margin-left: 1040px;
    }
</style>