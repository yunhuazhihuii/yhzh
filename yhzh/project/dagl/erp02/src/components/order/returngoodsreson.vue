<template>
    <div id="maintain">   
        <div class="content">
          <div class="content-top">
            <div class="content-top-title">
              <p>当前位置：交易管理 > 退货原因管理 </p>
            </div>
          </div>
          <div class="content-botton">
              <el-row class="elrow">
                  <el-button class="elbutton" size="small" @click="add">添加</el-button>
                  <el-button class="elbutton" size="small" @click="changenotice">修改</el-button>
                  <el-button class="elbutton" size="small" @click="delnotice">删除</el-button>
                 
              </el-row>
          </div> 

        <el-dialog
          title="添加标签"
          :visible.sync="dialogVisible"
          width="40%"
          >
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
          >
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
              @selection-change="handleSelectionChange"
              style="width: 100%"
              >
              <el-table-column
                type="selection"
                width="50">
              </el-table-column>
              <el-table-column
                prop="labelname"
                label="退货原因"
                align="center"
                width="350">
              </el-table-column>
              <el-table-column
                prop="lblorder"
                label="排序"
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
    export default{
        data(){
            return {               
                
                dialogVisible:false,
                dialogVisiblec:false,
                formLabelWidth: '120px',
                value:'',
                value1:'',
                options:[] ,
                currentPage:1,
                pagesize:5,
                multipleSelection:[],

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

                tableData: [
                  {labelname:"asd", lblorder:" 12365"  }
                    
                ],
                  
             
            }
        },

        methods: {

            handleSelectionChange(val) {
                this.multipleSelection = val;
            },

            cellStyle:function(row,column,rowIndex,columnIndex){

                console.log(row.row.color)
                if(row.columnIndex == 3 && row.rowIndex == 1){
                  // return 'background-color:#409EFF'
                }else{
                  return ''
                }

            },

            //添加
            add(){
              this.dialogVisible =true;
              console.log("123654")
            },

            //修改
            changenotice(){
              this.dialogVisiblec=true;
              console.log("78456");
            },

            //删除
            delnotice(){
              console.log("789654");
              var that = this;
              // var shopid = this.multipleSelection[0].shop_id;
              this.$confirm('确认删除这个店铺?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then((action)=>{
                if(action==='confirm'){
                  console.log("963");
                  var api = url.delShop;
                  Axios.post(api,
                      {
                        shop_id:shopid, 
                      }
                  ).then((response)=>{
                    console.log("789");
                    this.$message({
                          type: 'success',
                          message: '删除成功!'
                    });
                    this.getShoplist();

                  })


                }
              }).catch(()=>{
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                }); 
              })
            },

            //添加保存
            saveNotice(){
              console.log("741");
              var that =this;
              // var api = url.addShop;
              if( that.getshopid !=null){
                Axios.post(api,
                  {
                   
                  }
                ).then((response)=>{
                  console.log("741");
                  that.dialogVisible=false;
                }).catch((error)=>{
                  console.log("852")
                })

              }else{
                console.log("为空不能提交数据");
              }
            },

            //修改保存
            saveNoticec(){
              console.log("963");
              var that =this;
              // var api = url.addShop;
              
              Axios.post(api,
                {
                  
                }
              ).then((response)=>{
                console.log(response);
                this.dialogVisible=false
                // this.getShoplist();
              
              }).catch((error)=>{
                console.log(error);
              })
              
            }
        }         
    }

</script>



<style scoped>


.btn{
   margin:20px;
}

.btn button{
    margin:2px 10px;
    padding:10px 20px;
    border-radius: 20px;
}

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