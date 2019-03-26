<template>
    <div id="maintain">   
        <div class="content">
          <div class="content-top">
            <div class="content-top-title">
              <p>当前位置：交易管理>采购账号管理</p>
            </div>
          </div>
          <div class="content-botton">
              <el-row class="elrow">
                  <el-button class="elbutton" size="small" @click="dialogVisible = true">添加</el-button>
                  <el-button class="elbutton" size="small" @click="purchasechange">修改</el-button>
                  <el-button class="elbutton" size="small" @click="delpurchase">删除</el-button>
                  <!-- <el-button class="elbutton" size="small">查看</el-button> -->
              </el-row>
          </div> 

          <!-- 新增数据 -->
           <el-dialog
            title="新增数据"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
            <el-form :model="form">
              <el-form-item label="淘宝账号:" :label-width="formLabelWidth">
                <el-input v-model="form.title" autocomplete="off" size="small" style="width:70%"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">关 闭</el-button>
              <el-button type="primary" @click="saveNotice">发 布</el-button>
            </span>
          </el-dialog>   

          <!-- 修改数据 -->
          <el-dialog
            title="修改数据"
            :visible.sync="dialogVisiblec"
            width="30%"
            :before-close="handleClose">
            <el-form :model="form1">
              <el-form-item label="淘宝账号:" :label-width="formLabelWidth">
                <el-input v-model="form1.title" autocomplete="off" size="small" style="width:70%"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisiblec = false">关 闭</el-button>
              <el-button type="primary" @click="saveNoticec">确认修改</el-button>
            </span>
          </el-dialog> 
                 
              
          <!-- 搜索框 -->
          <el-input class="elinput" size="small" placeholder="淘宝账号"></el-input>
            &nbsp;&nbsp;&nbsp;
          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search"></el-button> 
          
          <div class="shoplist">

            <el-table
              :data="tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
              border
              stripe
              :header-cell-style="{background:'#F2F2F2'}"
              @selection-change="handleSelectionChange"
              style="width: 100%"
              >

              <el-table-column
                type="selection"
                label="序号"
                width="50">
              </el-table-column>

              <el-table-column
                prop="tb_code"
                label="采购账号"
                align="center"
                width="950">
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

  export default{

    data(){
        return {   
          multipleSelection:[],                 
          tableData: [],
          currentPage:1,
          pagesize:5,

          dialogVisible: false,
          dialogVisiblec: false,
          formLabelWidth: '120px',
          form: {
            title: '',
           
          },
          form1: {
            title: '',
            tb_id: ''
           
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

          // 查 询 
          getPurinfo(){

            //请求数据
            var api = url.getPurinfo;
            var _this = this
            Axios.post(api,
              {

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
          // 添加账号
          saveNotice(){

            var api = url.addPurinfo;
            var _this = this
          
            Axios.post(api,
              {
                tb_id:'',
                tb_code:_this.form.title,
              }
            )
            .then((response)=>{
              console.log(response);
              _this.dialogVisible=false
               _this.getPurinfo()
              // _this.tableData=response.data;

            })
            .catch((error)=>{
              console.log(error);
            })
           
          },

          // 修改账号
          purchasechange(){
            this.dialogVisiblec=true
            console.log(this.multipleSelection)
            this.form1.title=this.multipleSelection[0].tb_code
            this.form1.tb_id = this.multipleSelection[0].tb_id
            // this.form1.title=this.multipleSelection[0].

          },

          //修改后保存
          saveNoticec(){
            var api = url.addPurinfo;
            var _this = this
            
              Axios.post(api,
                {
                  tb_id:_this.form1.tb_id,
                  tb_code:_this.form1.title,
                 
                }
              )
              .then((response)=>{
                console.log(response);
                _this.dialogVisiblec=false
                _this.getPurinfo()
                // _this.tableData=response.data;

              })
              .catch((error)=>{
                console.log(error);
              })
          },

          // 删除账号
          delpurchase(){

              var _this = this
              
              var tb_id = this.multipleSelection[0].tb_id
              // window.alert("群定")
              this.$confirm('确认删除这个账号吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then((action) => {
                if(action==='confirm'){

                    var api = url.delPurinfo
                    Axios.post(api,
                      {
                        tb_id:tb_id,
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
                        _this.getPurinfo()
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
          this.getPurinfo();
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
    width: 1001px;
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