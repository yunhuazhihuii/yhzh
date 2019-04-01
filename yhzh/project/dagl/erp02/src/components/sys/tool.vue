<template>
    <div id="maintain">   
        <div class="content">
          <div class="content-top">
            <div class="content-top-title">
              <p>当前位置：交易管理>发布常用工具管理</p>
            </div>
          </div>
          <div class="content-botton">
              <el-row class="elrow">
                  <el-button class="elbutton" size="small" @click="dialogVisible = true">添加</el-button>
                  <el-button class="elbutton" size="small" @click="changenotice">修改</el-button>
                  <el-button class="elbutton" size="small" @click="delnotice">删除</el-button>
                  <el-button class="elbutton" size="small" @click="looknotice">查看</el-button>
                  <!-- <el-button class="elbutton" size="small">导出</el-button> -->
              </el-row>
          </div> 

        <el-dialog
          title="发布常用工具"
          :visible.sync="dialogVisible"
          width="40%"
          :before-close="handleClose">
          <el-form :model="form">
            <el-form-item label="工具名称:" :label-width="formLabelWidth">
              <el-input v-model="form.title" autocomplete="off" size="small" style="width:50%"></el-input>
            </el-form-item>
            <el-form-item label="工具链接:" :label-width="formLabelWidth">
              <el-input type="textarea" v-model="form.desc" :rows="10" style="width:88%"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">关 闭</el-button>
            <el-button type="primary" @click="saveNotice">发 布</el-button>
          </span>
        </el-dialog>

        <el-dialog
          title="查看工具"
          :visible.sync="dialogVisiblel"
          width="40%"
          :before-close="handleClose">
          <el-form :model="form2">
            <el-form-item label="工具名称:" :label-width="formLabelWidth">
              <el-input v-model="form2.title" :disabled="true" autocomplete="off" size="small" style="width:50%"></el-input>
            </el-form-item>
            <el-form-item label="工具链接:" :label-width="formLabelWidth">
              <el-input type="textarea" v-model="form2.desc" :disabled="true" :rows="10" style="width:88%"></el-input>
            </el-form-item>
          </el-form>
         <!--  <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisiblel = false">关 闭</el-button>
            <el-button type="primary" @click="saveNoticel">发 布</el-button>
          </span> -->
        </el-dialog>

        <el-dialog
          title="修改工具数据"
          :visible.sync="dialogVisiblec"
          width="40%"
          :before-close="handleClose">
          <el-form :model="form1">
            <el-form-item label="工具名称:" :label-width="formLabelWidth">
              <el-input v-model="form1.title" autocomplete="off" size="small" style="width:50%"></el-input>
            </el-form-item>
            <el-form-item label="工具链接:" :label-width="formLabelWidth">
              <el-input type="textarea" v-model="form1.desc" :rows="10" style="width:88%"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisiblec = false">关 闭</el-button>
            <el-button type="primary" @click="saveNoticec">修 改</el-button>
          </span>
        </el-dialog>


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
                prop="tool_name"
                label="公告标题"
                align="center"
                width="300">
              </el-table-column>
              <el-table-column
                prop="tool_content"
                label="链接"
                align="center"
                width="400">
              </el-table-column>
              <el-table-column
                prop="tool_cretime"
                label="发布时间"
                width="300"
                align="center">
              </el-table-column>
             
            </el-table>
             <div class="block">
           <!--    <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page.sync="currentPage"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="pagesize"
                layout="sizes, prev, pager, next"
                :total="tableData.length">
              </el-pagination> -->
              <el-pagination
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
    import url from '../../../config/sysAPI.config.js'
    export default{
        data(){
            return {    
              multipleSelection:[],           
              tableData: [],
              currentPage:1,
              pagesize:5,
              dialogVisible: false,
              dialogVisiblec: false,
              dialogVisiblel: false,
              formLabelWidth: '120px',
              form: {
                title: '',
                desc: ''
              },
              form1: {
                id: '',
                title: '',
                desc: ''
              },
              form2: {
                id: '',
                title: '',
                desc: ''
              },
            }
        },
        methods: {
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
          saveNotice(){
            var that = this;
            var api = url.addTool;
            Axios.post(api,
              {
                tool_name:that.form.title,
                tool_content:that.form.desc,
              }
            )
            .then((response)=>{
              console.log(response)
              that.dialogVisible = false;
              that.getToolList()
            })
            .catch((error)=>{
              console.log(error)
            })
          },

            getToolList(){
            //
            var api = url.getToolList;
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
          changenotice(){
            if(this.multipleSelection.length<1){
                this.$message({
                      type: 'info',
                      message: '请先勾选一条要修改的工具'
                    });
              }else if(this.multipleSelection.length>1){
                this.$message({
                      type: 'error',
                      message: '不能同时修改多条工具'
                    });
              }else{
                this.dialogVisiblec=true
                console.log(this.multipleSelection)
                this.form1.id=this.multipleSelection[0].tool_id
                this.form1.title=this.multipleSelection[0].tool_name
                this.form1.desc=this.multipleSelection[0].tool_content
              }
            
          },
          saveNoticec(){
            var that = this;
            var api = url.updateTool;

             Axios.post(api,
              {
                tool_id:that.form1.id,
                tool_name:that.form1.title,
                tool_content:that.form1.desc,
              }
            )
            .then((response)=>{
              console.log(response)
              that.dialogVisiblec = false;
              that.getToolList()
            })
            .catch((error)=>{
              console.log(error)
            })

          },
          delnotice(){
             var _this = this

             if(_this.multipleSelection.length<1){
                _this.$message({
                      type: 'info',
                      message: '请先勾选一条要删除的工具'
                    });
              }else if(_this.multipleSelection.length>1){
                _this.$message({
                      type: 'error',
                      message: '不能同时删除多条工具'
                    });
              }else{
                var tool_id = this.multipleSelection[0].tool_id
              // window.alert("群定")
              this.$confirm('确认删除这条记录吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then((action) => {
                if(action==='confirm'){
                    // console.log("6666666666669999999999999")
                    var api = url.delTool
                    Axios.post(api,
                      {
                        tool_id:tool_id,
                        
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
                        _this.getToolList()
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
          // 查看
          looknotice(){
            if(this.multipleSelection.length<1){
                this.$message({
                      type: 'info',
                      message: '请先勾选一条要查看的工具'
                    });
              }else if(this.multipleSelection.length>1){
                this.$message({
                      type: 'error',
                      message: '不能同时查看多条工具'
                    });
              }else{
                this.dialogVisiblel=true
              this.form2.title=this.multipleSelection[0].tool_name
              this.form2.desc=this.multipleSelection[0].tool_content
              }
            
          }
        },
        mounted(){
          this.getToolList();
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
    /* width: 1350px; */
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