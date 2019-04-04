<template>
    <div id="maintain">   
        <div class="content">
          <div class="content-top">
            <div class="content-top-title">
              <p>当前位置：交易管理>标签管理</p>
            </div>
          </div>
          <div class="content-botton">
              <el-row class="elrow">
                  <el-button class="elbutton" size="small" @click="dialogVisible = true">添加</el-button>
                  <el-button class="elbutton" size="small" @click="changenotice">修改</el-button>
                  <el-button class="elbutton" size="small" @click="delnotice">删除</el-button>
                 
              </el-row>
          </div> 

        <el-dialog
          title="添加标签"
          :visible.sync="dialogVisible"
          width="40%"
          :before-close="handleClose">
          <el-form :model="form">
            <el-form-item label="标签:" :label-width="formLabelWidth">
              <el-input v-model="form.label" autocomplete="off" size="small" style="width:50%"></el-input>
            </el-form-item>
            <el-form-item label="排序:" :label-width="formLabelWidth">
              <el-input v-model="form.desc" autocomplete="off" size="small" style="width:50%"></el-input>
            </el-form-item>
            <el-form-item label="选择类型:" :label-width="formLabelWidth">
              <el-select v-model="value" size="small" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
           <el-form-item label="选择颜色:" :label-width="formLabelWidth">
              <el-color-picker v-model="form.color1"></el-color-picker>
            </el-form-item>

          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">关 闭</el-button>
            <el-button type="primary" @click="saveNotice">发 布</el-button>
          </span>
        </el-dialog>

        <el-dialog
          title="修改标签"
          :visible.sync="dialogVisiblec"
          width="40%"
          :before-close="handleClose">
          <el-form :model="form1">
            <el-form-item label="标签:" :label-width="formLabelWidth">
              <el-input v-model="form1.label" autocomplete="off" size="small" style="width:50%"></el-input>
            </el-form-item>
            <el-form-item label="排序:" :label-width="formLabelWidth">
              <el-input v-model="form1.desc" autocomplete="off" size="small" style="width:50%"></el-input>
            </el-form-item>
            <el-form-item label="选择类型:" :label-width="formLabelWidth">
              <el-select v-model="value1" size="small" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
           <el-form-item label="选择颜色:" :label-width="formLabelWidth">
              <el-color-picker v-model="form1.color1"></el-color-picker>
            </el-form-item>

          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisiblec = false">关 闭</el-button>
            <el-button type="primary" @click="saveNoticec">发 布</el-button>
          </span>
        </el-dialog>


          <div class="shoplist">
            <el-table
              :data="tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
              border
              :cell-style = "cellStyle"
              :header-cell-style="{background:'#eef1f6',color:'#606266'}"
              @selection-change="handleSelectionChange"
              style="width: 100%"
              >
              <el-table-column
                type="selection"
                width="50">
              </el-table-column>
              <el-table-column
                prop="labelname"
                label="标签"
                align="center"
                width="250">
              </el-table-column>
              <el-table-column
                prop="lblorder"
                label="排序"
                width="250"
                align="center">
              </el-table-column>
              <el-table-column
                prop="color"
                label="颜色"
                width="300"
                align="center">
              </el-table-column>
              <el-table-column
                prop="labeltype"
                label="标签类型"
                width="300"
                align="center">
              </el-table-column>
             
            </el-table>
             <div class="block">
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
              value: '',  
              value1: '', 
              color1: '#409EFF',
              multipleSelection:[],           
              tableData: [],
              currentPage:1,
              pagesize:5,
              dialogVisible: false,
              dialogVisiblec: false,
              dialogVisiblel: false,
              formLabelWidth: '120px',
             options: [
                {
                  value: '普通标签',
                  label: '普通标签'
                }, 
                {
                  value: '异常标签',
                  label: '异常标签'
                },
              ],
              form: {
                label: '',
                desc: '',
                color1: '#409EFF',
              },
              form1: {
                id: '',
                label: '',
                desc: '',
                color1: '',
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

          //发布
          saveNotice(){
            var that = this;
            var api = url.addLabel;
            Axios.post(api,
              {
                labelid:'0',
                labelname:that.form.label,
                labeltype:that.value,
                color:that.form.color1,
                lblorder:that.form.desc
              }
            )
            .then((response)=>{
              console.log(response)
              that.dialogVisible = false;
              that.getLabelList()
            })
            .catch((error)=>{
              console.log(error)
            })
          },

          getLabelList(){
            //
            var api = url.getLabelList;
            var _this = this
            Axios.post(api,
              {

              }
            )
            .then((response)=>{
              console.log(response);
              _this.tableData=response.data;

            })
            .catch((error)=>{
              console.log(error);
            })
          },
          changenotice(){
            this.dialogVisiblec=true
            console.log(this.multipleSelection)
            this.form1.id=this.multipleSelection[0].labelid
            this.form1.label=this.multipleSelection[0].labelname
            this.form1.desc=this.multipleSelection[0].lblorder
            this.value1=this.multipleSelection[0].labeltype
            this.form1.color1=this.multipleSelection[0].color

          },
          saveNoticec(){
            var that = this;
            var api = url.addLabel;

             Axios.post(api,
              {
        

                labelid:that.form1.id,
                labelname:that.form1.label,
                labeltype:that.value1,
                color:that.form1.color1,
                lblorder:that.form1.desc

              }
            )
            .then((response)=>{
              console.log(response)
              that.dialogVisiblec = false;
              that.getLabelList()
            })
            .catch((error)=>{
              console.log(error)
            })

          },
          delnotice(){
             var _this = this
              
              var labelid = this.multipleSelection[0].labelid
              // window.alert("群定")
              this.$confirm('确认删除这条记录吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then((action) => {
                if(action==='confirm'){
                    // console.log("6666666666669999999999999")
                    var api = url.delLabel
                    Axios.post(api,
                      {
                        labelid:labelid,
                        
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
                        _this.getLabelList()
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

          // cellcolor(row,column,rowindex,columnindex){
          //   if(rowindex == 1){
          //     return 'background-color: lightblue'

          //   }
          // }
          // 查看
          // looknotice(){
          //   this.dialogVisiblel=true
          //   this.form2.title=this.multipleSelection[0].bulletin_name
          //   this.form2.desc=this.multipleSelection[0].bulletin_content
          // }


          // tableRowStyle({ row, rowIndex }) {
          //   return 'background-color: pink'
          // },

          cellStyle:function(row,column,rowIndex,columnIndex){
              console.log(row.row.color)
              if(row.columnIndex == 3 && row.rowIndex == 1){
                // return 'background-color:#409EFF'
              }else{
                return ''
              }
          }
        },
        mounted(){
          this.getLabelList();
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