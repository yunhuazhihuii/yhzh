<template>
    <div id="maintain">   
        <div class="content">
          <div class="content-top">
            <div class="content-top-title">
              <p>当前位置：交易管理>采购退货订单登记</p>
            </div>
          </div>
          <div class="content-botton">
              <el-row class="elrow">
                  <el-button @click="addreturn() " class="elbutton" size="small">添加</el-button>
                  <el-button class="elbutton" size="small" @click="changereturn()">修改</el-button>
                  <el-button class="elbutton" size="small" @click="delereturn()">删除</el-button>
                   <el-button class="elbutton" size="small" @click="lookreturn() ">查看</el-button>  
              </el-row>

              <!-- <el-row class="elrow" v-if="order='2' ">
                  <el-button @click="addreturn() " class="elbutton" size="small">填写退件</el-button>
                  <el-button class="elbutton" size="small" @click="changereturn()">修改</el-button>
                  <el-button class="elbutton" size="small" @click="lookreturn() ">查看</el-button>
                  <el-button class="elbutton" size="small" @click="delereturn()">标记为无法退件</el-button>   
              </el-row> -->
          </div> 

          <!-- 添加退货订单登记 -->
          <el-dialog
              title="添加退货订单登记"
              :visible.sync="dialogVisible"
              width="50%">

              <el-form :model="form">
                <el-form-item label="订单号:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.comid" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="淘宝订单号:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.compname" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>

                <el-form-item label="退货原因:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>

                <el-form-item label="退货货值:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="退货运费:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.password" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="退货信息:" :label-width="formLabelWidth" required>
                  <el-input type="textarea" v-model="form.desc" :rows="7" style="width:70%"></el-input>
                </el-form-item>

                <el-form-item label="运费支付方:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                

              </el-form>
              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveNotice">添 加</el-button>
              </span>
          </el-dialog>  


          <!-- 修改退货订单登记 -->
          <el-dialog
              title="修改退货订单登记"
              :visible.sync="dialogVisibles"
              width="50%">
              
              <el-form :model="form">
                <el-form-item label="订单号:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.comid" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="淘宝订单号:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.compname" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>

                <el-form-item label="退货原因:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>

                <el-form-item label="退货货值:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="退货运费:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.password" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="退货信息:" :label-width="formLabelWidth" required>
                  <el-input type="textarea" v-model="form.desc" :rows="7" style="width:70%"></el-input>
                </el-form-item>

                <el-form-item label="运费支付方:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                
              </el-form>
              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisibles = false">取 消</el-button>
                <el-button type="primary" @click="saveNotice">添 加</el-button>
              </span>
          </el-dialog>  
                 
          
          
          <!-- <el-input class="elinput" size="small" placeholder="公司ID/公司名称"></el-input>
            &nbsp;&nbsp;&nbsp;
          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search"></el-button>  -->

          <div class="shoplist">
            <el-table
              :data="tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
              border
              
              @selection-change="handleSelectionChange"
              style="width: 100%"
              >
              <el-table-column
                type="selection"
                width="50">
              </el-table-column>

              <el-table-column
                prop="order"
                label="订单号"
                align="center"
                width="150">
              </el-table-column>

              <el-table-column
                prop="taoorder"
                label="淘宝订单号"
                width="200"
                align="center">
              </el-table-column>

              <el-table-column
                prop="reasonreturn"
                label="退货原因"
                width="150"
                align="center">
              </el-table-column>

              <el-table-column
                prop="valuereturn"
                label="退货货值"
                width="100"
                align="center">
              </el-table-column>

              <el-table-column
                prop="fee"
                label="退货运费"
                width="100"
                align="center">
              </el-table-column>

              <el-table-column
                prop="namenews"
                label="退货收件人信息"
                width="200"
                align="center">
              </el-table-column>

              <el-table-column
                prop="shoppingno"
                width="150"
                align="center"
                label="物流运输号">
              </el-table-column>

              <el-table-column
                prop="feestyle"
                align="center"
                width="150"
                label="退货运费支付方式">
              </el-table-column>

              <el-table-column
                prop="questreturn"
                label="仓库确认是否退货"
                width="150"
                align="center">
              </el-table-column>

              <el-table-column
                prop="questfee"
                width="150"
                align="center"
                label="仓库确认退货运费">
              </el-table-column>
              
              
            </el-table>
      
            <div class="block">
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page.sync="currentPage"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="pagesize"
                layout="sizes, prev, pager, next"
                :total="tableData.length">
              </el-pagination>
            </div>
           
          </div>
        </div>
       
        
        
    </div>
</template>


<script>

import Axios from 'axios';
import url from '../../../config/sysAPI.config.js';
import {getSession} from '../../common/js/util';
    export default{
        data(){
            return { 
              props: [
                order 
              ],    
                        
              userids:'',       
              tableData: [  
                 {
                  order:"123 ",
                  taoorder:"123 ",
                  reasonreturn:"123 ",
                  valuereturn:"123 ",
                  fee:"123 ",
                  namenews:"123 ",
                  shoppingno:"123 ",
                  questreturn:"123 ",
                  questfee:"123 ",
                  feestyle:"123 "
                 } 
              ],
              currentPage:1,
              pagesize:10,
              dialogVisible: false, 
              dialogVisibles: false, 
              formLabelWidth: '240px',
              form: {
                comid: '',
                compname: '',
                account: '',
                password: '',
                desc: ''
                
              },
              form1: {
                comid: '',
                compname: '',
                account: '',
                password: '',
                desc: ''
                
              },
             
            }
        },
        methods: {
           //改变时
           handleSizeChange(val) {
                this.pagesize = val;
           },
            //条目改变时
           handleCurrentChange(val) {
                this.currentPage = val;
           },
          handleEdit(index, row) {
            console.log(index, row);
          },
          handleDelete(index, row) {
            console.log(index, row);
          },

          handleClose(done) {
            this.$confirm('确认关闭？')
              .then(_ => {
                done();
              })
              .catch(_ => {});
          },
          handleSelectionChange(val) {
  
            this.multipleSelection = val
          
          },

          getCompList(){
            
            //请求数据
            // var api = url.getCompList;
            var _this = this
            // console.log(_this.userids)
            // Axios.post(api,
            //   {
            //     // userid:_this.userids
            //   }
            // )
            // .then((response)=>{
            //   console.log(response.data);
            //   _this.tableData=response.data;

            // })
            // .catch((error)=>{
            //   console.log(error);
            // })
          },

          saveNotice(){
            var that = this;
            // var api = url.addComp;
            Axios.post(api,
              {
               
              }
            )
            .then((response)=>{
              console.log(response)
              that.dialogVisible = false;
              // that.getCompList()
            })
            .catch((error)=>{
              console.log(error)
            })
          },

          //修改
          changereturn(){
            this.dialogVisibles=true;
            console.log("123")
            //console.log(this.multipleSelection)
            //this.form1.comid=this.multipleSelection[0].compid
           // this.form1.compname=this.multipleSelection[0].compname
           /// this.form1.account=this.multipleSelection[0].mngusername
            ///this.form1.password=this.multipleSelection[0].mnguserpwd
            //this.form1.desc=this.multipleSelection[0].remark
          },

          saveNoticec(){
            var that = this;
            var api = url.updateComp;

             Axios.post(api,
              {
                compid:that.form1.comid,
                compname:that.form1.compname,
                userid:that.userids,
                remark:that.form1.desc,
                mnguserid:that.form1.account,
                mnguserpwd:that.form1.password,
              }
            )
            .then((response)=>{
              console.log(response)
              that.dialogVisiblec = false;
              that.getCompList()
            })
            .catch((error)=>{
              console.log(error)
            })

          },


          delecomp(){

              var compid = this.multipleSelection[0].compid
              // window.alert("群定")
              this.$confirm('确认删除这条记录吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then((action) => {
                if(action==='confirm'){
                    // console.log("6666666666669999999999999")
                    // var api = url.delComp
                    Axios.post(api,
                      {
                        // compid:compid,
                      }
                    )
                    .then((response)=>{
                      console.log(response);
                      // _this.dialogVisiblec=false
                      // _this.tableData=response.data;
                         this.$message({
                          type: 'success',
                          message: '删除成功!'
                        });
                        // location.reload()
                        this.getCompList()
                    })
                    .catch((error)=>{
                      console.log(error);
                    })

                 
                }
              
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                });          
              });
          },

          lookreturn(){
            console.log("123456");
          },

          //添加
          addreturn(){
            this.dialogVisible=true;
          },

          //删除
          delereturn(){
            console.log("456");

          }

        },
        mounted(){
          this.userids = getSession("user_id");
          console.log(this.userids)
          this.getCompList();
        }       
    }

</script>

<style scoped>
  .content {
    position:absolute;
    width: 100%;
    height: 1000px;
  }
  .content-top {
    width: 1255px;
    height: 40px;
    border: 1px solid #CCCCCC;
    position: absolute;
    top: 0;
    left: 0;
  }
  .content-top-title {
    height: 40px;
    background-color: #F2F2F2;
    border-bottom: 1px solid #CCCCCC;
  }
  .content-top-title p {
    padding-top: 8px;
    font-size: 15px;
  }
  .elinput {
    position: absolute;
    left: 965px;
    top: 58px;
    width: 250px;
  }
  .select {
    position: absolute;
    left: 665px;
    top: 58px;
    width: 150px;
  }
  .serbutton {
    position: absolute;
    left: 1215px;
    top: 58px;
    background-color: #76A3A3;
  }
  .content-botton {
    position: absolute;
    left: 0;
    top: 40px;
    
    height: 65px;
    width: 1200px;
  }
  .shoplist {
    position: absolute;
    top: 104px;
    width: 1551px;
    
  }
  .elrow {
    margin-top: 16px;
  }
  .elbutton {
    background-color: #76A3A3;
    color: #fff;
    font-weight: bold;
  }
  .selectbutton {
    position: absolute;
    top: 58px;
    left: 820px;
    background-color: #76A3A3;
    color: #fff;
    font-weight: bold;
  }

</style>