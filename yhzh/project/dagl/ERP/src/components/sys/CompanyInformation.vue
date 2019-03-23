<template>
    <div id="maintain">   
        <div class="content">
          <div class="content-top">
            <div class="content-top-title">
              <p>当前位置：交易管理>公司信息管理</p>
            </div>
          </div>
          <div class="content-botton">
              <el-row class="elrow">
                  <el-button @click="dialogVisible = true" class="elbutton" size="small">添加</el-button>
                  <el-button class="elbutton" size="small" @click="changecomp">修改</el-button>
                  <el-button class="elbutton" size="small" @click="delecomp">删除</el-button>
                  
              </el-row>
          </div> 

           <el-dialog
            title="添加公司"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
            <el-form :model="form">
              <el-form-item label="公司ID:" :label-width="formLabelWidth">
                <el-input v-model="form.comid" autocomplete="off" size="small" style="width:70%"></el-input>
              </el-form-item>
              <el-form-item label="公司名称:" :label-width="formLabelWidth">
                <el-input v-model="form.compname" autocomplete="off" size="small" style="width:70%"></el-input>
              </el-form-item>
              <el-form-item label="管理员账号:" :label-width="formLabelWidth">
                <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input>
              </el-form-item>
              <el-form-item label="密码:" :label-width="formLabelWidth">
                <el-input v-model="form.password" autocomplete="off" size="small" style="width:70%"></el-input>
              </el-form-item>
              <el-form-item label="备注:" :label-width="formLabelWidth">
                <el-input type="textarea" v-model="form.desc" :rows="7" style="width:70%"></el-input>
              </el-form-item>
              

            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="saveNotice">添 加</el-button>
            </span>
          </el-dialog>  
                 
          <el-dialog
            title="修改公司"
            :visible.sync="dialogVisiblec"
            width="30%"
            :before-close="handleClose">
            <el-form :model="form1">
              <el-form-item label="公司ID:" :label-width="formLabelWidth">
                <el-input v-model="form1.comid" autocomplete="off" :disabled="true" size="small" style="width:70%"></el-input>
              </el-form-item>
              <el-form-item label="公司名称:" :label-width="formLabelWidth">
                <el-input v-model="form1.compname" autocomplete="off" size="small" style="width:70%"></el-input>
              </el-form-item>
              <el-form-item label="管理员账号:" :label-width="formLabelWidth">
                <el-input v-model="form1.account" autocomplete="off" size="small" style="width:70%"></el-input>
              </el-form-item>
              <el-form-item label="密码:" :label-width="formLabelWidth">
                <el-input v-model="form1.password" autocomplete="off" size="small" style="width:70%"></el-input>
              </el-form-item>
              <el-form-item label="备注:" :label-width="formLabelWidth">
                <el-input type="textarea" v-model="form1.desc" :rows="7" style="width:70%"></el-input>
              </el-form-item>
              

            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisiblec = false">取 消</el-button>
              <el-button type="primary" @click="saveNoticec">修 改</el-button>
            </span>
          </el-dialog> 
          
          <el-input class="elinput" size="small" placeholder="公司ID/公司名称"></el-input>
            &nbsp;&nbsp;&nbsp;
          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search"></el-button> 
          <div class="shoplist">
            <el-table
              :data="tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
              border
              :header-cell-style="{background:'#F2F2F2'}"
              @selection-change="handleSelectionChange"
              style="width: 100%"
              >
              <el-table-column
                type="selection"
                width="50">
              </el-table-column>
              <el-table-column
                prop="compid"
                label="公司ID"
                align="center"
                width="150">
              </el-table-column>
              <el-table-column
                prop="compname"
                label="公司名称"
                width="250"
                align="center">
              </el-table-column>
              <el-table-column
                prop="mngusername"
                label="管理员"
                width="150"
                align="center">
              </el-table-column>
              <el-table-column
                prop="createusername"
                label="创建人"
                align="center">
              </el-table-column>
              <el-table-column
                prop="createtime"
                label="创建时间"
                width="200"
                align="center">
              </el-table-column>
              <el-table-column
                prop="modusername"
                label="修改人"
                width="100"
                align="center">
              </el-table-column>
              <el-table-column
                prop="modtime"
                width="200"
                align="center"
                label="修改时间">
              </el-table-column>
              <el-table-column
                prop="remark"
                align="center"
                width="150"
                label="备注">
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
                        
              userids:'',       
              tableData: [  
                    
              ],
              currentPage:1,
              pagesize:10,
              dialogVisible: false, 
              dialogVisiblec: false, 
              formLabelWidth: '120px',
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
            var api = url.getCompList;
            var _this = this
            // console.log(_this.userids)
            Axios.post(api,
              {
                userid:_this.userids
              }
            )
            .then((response)=>{
              console.log(response.data);
              _this.tableData=response.data;

            })
            .catch((error)=>{
              console.log(error);
            })
          },

          saveNotice(){
            var that = this;
            var api = url.addComp;
            Axios.post(api,
              {
                compid:that.form.comid,
                compname:that.form.compname,
                userid:that.userids,
                remark:that.form.desc,
                mnguserid:that.form.account,
                mnguserpwd:that.form.password,
              }
            )
            .then((response)=>{
              console.log(response)
              that.dialogVisible = false;
              that.getCompList()
            })
            .catch((error)=>{
              console.log(error)
            })
          },

          changecomp(){
            this.dialogVisiblec=true
            console.log(this.multipleSelection)
            this.form1.comid=this.multipleSelection[0].compid
            this.form1.compname=this.multipleSelection[0].compname
            this.form1.account=this.multipleSelection[0].mngusername
            this.form1.password=this.multipleSelection[0].mnguserpwd
            this.form1.desc=this.multipleSelection[0].remark
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
                    var api = url.delComp
                    Axios.post(api,
                      {
                        compid:compid,
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
    /* border: 1px solid #CCCCCC; */
    height: 65px;
    width: 1200px;
  }
  .shoplist {
    position: absolute;
    top: 104px;
    /* background-color: pink; */
    width: 1350px;
    /* height: 500px; */
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