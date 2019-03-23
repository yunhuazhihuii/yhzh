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
               <span>绑定权限：</span>
            
             
           </div>
           <div class="powerlist">
               <div class="list">
                   <div class="listtitle">
                       <p>首页</p>
                   </div>
                   <div class="powercheck">
                      <el-checkbox :indeterminate="isIndeterminate" @change="handleCheckAllChange1"  v-model="checkedall1">全部</el-checkbox>

                      <el-checkbox-group v-model="checkedhomes" @change="handleCheckedChange1">
                      <el-checkbox v-for="home in homes" :label="home" :key="home">{{home}}</el-checkbox>
                    </el-checkbox-group>
                   </div>
               </div>
               <div class="list">
                   <div class="listtitle">
                       <p>交易管理</p>
                   </div>
                   <div class="powercheck">

                      <el-checkbox :indeterminate="isIndeterminate" @change="handleCheckAllChange2"  v-model="checkedall2">全部</el-checkbox>

                      <el-checkbox-group v-model="checkedorders" @change="handleCheckedChange2">
                      <el-checkbox v-for="order in orders" :label="order" :key="order">{{order}}</el-checkbox>
                    </el-checkbox-group>

                   </div>
               </div>
               <div class="list">
                   <div class="listtitle">
                       <p>仓库管理</p>
                   </div>
                   <div class="powercheck">
                      <el-checkbox :indeterminate="isIndeterminate" @change="handleCheckAllChange3"  v-model="checkedall3">全部</el-checkbox>

                      <el-checkbox-group v-model="checkedcks" @change="handleCheckedChange3">
                      <el-checkbox v-for="ck in cks" :label="ck" :key="ck">{{ck}}</el-checkbox>
                    </el-checkbox-group>
                   </div>
               </div>
               <div class="list">
                   <div class="listtitle">
                       <p>系统管理</p>
                   </div>
                   <div class="powercheck">
                       <el-checkbox :indeterminate="isIndeterminate" @change="handleCheckAllChange4"  v-model="checkedall4">全部</el-checkbox>

                      <el-checkbox-group v-model="checkedsys" @change="handleCheckedChange4">
                      <el-checkbox v-for="sy in sys" :label="sy" :key="sy">{{sy}}</el-checkbox>
                    </el-checkbox-group>
                   </div>
               </div>
           </div>
           
       </div>
       <div class="bobutton">
           <el-button type="primary" @click="saveNotice">保 存 </el-button>
       </div>
       
    </div>
</template>


<script>
    import Axios from 'axios';
    import url from '../../../config/sysAPI.config.js'
    import {getSession} from '../../common/js/util';
    // const cityOptions = ['上海', '北京', '广州', '深圳'];
    export default{
        data(){
            return { 
               userid:'',           
               msg:'组件',
               formLabelWidth: '120px',
               form: {
                title: '',
                desc: ''
              },
              checked: false,
              checkedall1: false,
              checkedall2: false,
              checkedall3: false,
              checkedall4: false,
              checkedhomes: [],
              checkedorders: [],
              checkedcks: [],
              checkedsys: [],
              homes: ['公告栏', '常用工具', '待办事项', '业绩'],
              orders: ['店铺维护', '全部订单', '24小时到期订单', '待处理订单','待收货订单','运输中订单','申请取消订单','申请退款订单','耗损订单','已完成订单','已取消订单','采购退货订单','标志标签管理','退货原因管理'],
              cks: ['待收货订单', '拆件核对包裹', '已打印订单', '运输中订单','采购退货订单'],
              sys: ['店铺管理', '采购账号管理', '公司信息管理', '角色管理','员工管理','公告管理','工具管理'],

              isIndeterminate: true

              // checkAll: false,
              // checkedCities: ['上海', '北京'],
              // cities: cityOptions,
              // isIndeterminate: true
            }
        },
        methods:{
            handleCheckAllChange1(val) {
              console.log(val)
              this.checkedhomes = val ? this.homes : [];
              console.log(this.checkedhomes)
              this.isIndeterminate = false;
            },
            handleCheckAllChange2(val) {
              console.log(val)
              this.checkedorders = val ? this.orders : [];
              this.isIndeterminate = false;
            },
            handleCheckAllChange3(val) {
              console.log(val)
              this.checkedcks = val ? this.cks : [];
              this.isIndeterminate = false;
            },
            handleCheckAllChange4(val) {
              console.log(val)
              this.checkedsys = val ? this.sys : [];
              this.isIndeterminate = false;
            },
            handleCheckedChange1(value) {
                let checkedCount = value.length;
                console.log(value)
                console.log(checkedCount)
                console.log(this.checkedhomes)
                this.checkedall1 = checkedCount === this.homes.length;
                this.isIndeterminate = checkedCount > 0 && checkedCount < this.homes.length;
          },
          handleCheckedChange2(value) {
                let checkedCount = value.length;
                console.log(value)
                console.log(checkedCount)
                this.checkedall2 = checkedCount === this.orders.length;
                this.isIndeterminate = checkedCount > 0 && checkedCount < this.orders.length;
          },
          handleCheckedChange3(value) {
                let checkedCount = value.length;
                console.log(value)
                console.log(checkedCount)
                this.checkedall3 = checkedCount === this.cks.length;
                this.isIndeterminate = checkedCount > 0 && checkedCount < this.cks.length;
          },
          handleCheckedChange4(value) {
                let checkedCount = value.length;
                console.log(value)
                console.log(checkedCount)
                this.checkedall4 = checkedCount === this.sys.length;
                this.isIndeterminate = checkedCount > 0 && checkedCount < this.sys.length;
          },
          saveNotice(){
            console.log('89898898989')
            var that = this;
            var api = url.addRole;
            Axios.post(api,
              {
                roleid:'0',
                userid:that.userid,
                rolename:that.form.title,
                roledesc:that.form.desc,
                homerole:that.checkedhomes,
                orderrole:that.checkedorders,
                ckrole:that.checkedcks,
                sysrole:that.checkedsys,
              }
            )
            .then((response)=>{
              console.log(response)
              // that.dialogVisible = false;
              // that.getBulletinList()
            })
            .catch((error)=>{
              console.log(error)
            })
          }
        },
         mounted(){
          this.userid = getSession("user_id");
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
        height: 990px;
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
        height: 730px;
        /* background-color: pink; */
        margin: 0 auto;
        margin-top: 25px;
        border: 1px solid #797979;
    }
    .powerlist .list {
        height: 138px;
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
        margin-left: 1120px;
    }
</style>
