<template>
     <div>
        <!-- 温度设置 -->
        <el-form ref="form" :model="form" label-width="80px">
            <el-input v-model="form.name" placeholder="请输入标题" style="width: 60%"></el-input></br></br>
              <div class="demo-input-suffix">
                 节假日设置：
                   <el-date-picker
                      value-format="yyyy-MM-dd"
                      v-model="form.date"
                      type="date"
                      placeholder="选择日期">
                    </el-date-picker>
             </div>
             <!-- 确认按钮 -->
             <el-button type="primary" style="float: right; margin-top: -40px;"
             @click="addData">添加</el-button>
        </el-form>
        <!-- 数据展示表格 -->
        <el-table  :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"

            border
            style="width: 600px;margin-top: 20px;">
            <el-table-column
              prop="date"
              label="日期"
              width="180">
            </el-table-column>
            <el-table-column
              prop="name"
              label="标题"
              width="180">
            </el-table-column>
              <el-table-column
              label="操作">
              <template slot-scope="scope">
                <el-button
                  size="small"
                  @click.native.prevent="deleteData(scope.$index+pagesize*(currentPage-1))">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
             <!-- 分页页码 -->
           <el-pagination style="margin-top:16px;"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[4, 8, 10]"
              :page-size="pagesize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="tableData.length">
          </el-pagination>
  </div>
</template>

<script>

  import {deleteHDData,addDayData,queryDay} from '../../service/javaApi.js';

    export default {

        data() {
            return {
              // value1: '',  
               form: {
                date:'',
                name:'',
              },

              //当前默认页
              currentPage:1,
              pagesize:4,

              tableData:[],

              // tableData: [
               
              //   {
              //     date: '2016-05-04',
              //     name: '中秋节',
              //   }, 
              //   {
              //     date: '2016-05-01',
              //     name: '国庆节',
              //   }, 
              //   {
              //     date: '2016-05-03',
              //     name: '元旦',
              //   }
              // ]

            }
        },
        created(){
          var that=this;

          //查询-节假日：调用接口获取该页面下所有节假日 
          queryDay(that.form,res=>{

              // that.form=res;
              that.tableData = res;
              console.log("获取节假日信息成功==>",that.tableData);

          });
          
        },
        methods: {


           //分页信息
          handleSizeChange: function (size) {
              this.pagesize = size;
             },
          handleCurrentChange: function(currentPage){
              this.currentPage = currentPage;
           },

          //删除-节假日
          deleteData(index){

            var that=this;
            this.$confirm('请确认是否删除节假日' + that.tableData[index].form + '?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() =>{

              let id= that.tableData[index].form;

              deleteHDData(id,res=>{
                that.tableData.splice(index, 1); 
                this.$message({
                  message: '删除成功',
                  type: 'success'
                });
              });
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              });
            });

              // var that=this;
              // if(this.tableData.splice(index, 1)){
              //   // this.tableData.splice(index, 1);   //方法一是直接传入删除数组的索引
              //   this.$message({
              //     message: '恭喜你，删除成功',
              //     type: 'success'
              //   });
              // }else{
              //   this.$message({
              //     type: 'info',
              //     message: '已取消删除'
              //   });
              // }


          },

            
          // 添加-节假日
          addData(){
              
              var that=this;

              //如果日期和标题都不为空，则添加调用节假日接口
              if(that.form.name==''){
                that.$message({
                  type: 'error',
                  message: '请填写一个标题 '
                  
                });
                return

              }else if(that.form.date==''){
                that.$message({
                  type: 'error',
                  message: '请选择一个日期 '
                  
                });
                return
                console.log('一个日期')

              }else{

                addDayData(that.form,res =>{

                  that.form=res;
                  // that.form.Area.UserAreaID=res;
                  that.tableData.push(that.form);
                  console.log("新增节假日接口成功==>",that.form);
                });
              }


            // var that=this;
            // if(that.form.date==''||that.form.name==''){
            //    this.$message({
            //     message: '不能为空',
            //     type: 'error'
            //   });
            //   return
            // }
            // this.tableData.push(this.form);
          



          },



        }
    }


</script>

<style scoped lang="scss">
    form{
    width: 330px;
    padding: 12px;
    box-shadow:0 0 6px #ddd;
    border: 1px solid #ddd;
    margin-top: 20px;
    margin-left: 20px;
    .el-input{
        width: 165px;
    }
   }
</style>
