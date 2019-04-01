<template>
        <div>
          <!-- 添加用户的界面 -->
          <el-dialog title="管理权限" :visible.sync="dialogFormVisible">
              <el-form :model="form">
                <el-form-item label="用户名" :label-width="formLabelWidth">
                  <el-input v-model="form.name" ></el-input>
                </el-form-item>
                 <el-form-item label="密 码" :label-width="formLabelWidth">
                  <el-input v-model="form.password" auto-complete="off" type="password"></el-input>
                </el-form-item>
                <el-form-item label="权限" :label-width="formLabelWidth">
                  <el-select v-model="form.admin" placeholder="请选择用户权限">
                    <el-option label="超级管理员" value="admin"></el-option>
                    <el-option label="楼宇系统" value="楼宇系统"></el-option>
                    <el-option label="照明系统" value="照明系统"></el-option>
                  </el-select>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addsUser">确 定</el-button>
              </div>
            </el-dialog>
            
         <div style="width: 80%;margin:60px auto">
          <!-- 添加用户的界面结束 -->
            <el-table
              :data="tableData3.slice((currentPage-1)*pagesize,currentPage*pagesize)"
              tooltip-effect="dark" border>

              <el-table-column
                prop="name"
                label="名字"
                show-overflow-tooltip>
              </el-table-column>
              <el-table-column
                prop="admin"
                label="操作权限"
                show-overflow-tooltip>
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="handleEdit(scope.$index+pagesize*(currentPage-1))">编辑</el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index+pagesize*(currentPage-1))">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
           <!-- 分页页码 -->
            <el-pagination style="margin-top:16px;"
             background
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[6, 8, 12]"
              :page-size="pagesize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="tableData3.length">
            </el-pagination>
          <div style="margin-top: 20px">
            <el-button @click="addsUserShow">增加</el-button>
          </div>
      </div>
  </div>
</template>

<script>
import {getAllUser,deleteUser,addAdminUser,exist ,adminEdit} from '../../service/getData.js';
  export default {
    data() {
      return {
         dialogFormVisible: false,
         form: {},
         formLabelWidth: '80px',
         //当前默认页
        currentPage:1,
        pagesize:6,
        tableData3: [],
      }
    },
    created(){
        var that=this
        getAllUser(res=>{
            that.tableData3=res.data;
            console.log( that.tableData3)
         })
    },
    methods: {
       //分页信息
      handleSizeChange: function (size) {
          this.pagesize = size;
         },
      handleCurrentChange: function(currentPage){
          this.currentPage = currentPage;
       },
       
       // 增加界面弹框
      addsUserShow(){
           this.form={
              name: '',
              admin: '',
              password:'',
            },
          this.dialogFormVisible=true
       },
      // 编辑
      handleEdit(index){
        var that=this;
        //编辑之前先赋值
        that.form=that.tableData3[index];
        // 添加可编辑属性
        that.$set( that.form,'edit',index);
        // 打开修改框
        that.dialogFormVisible = true;
      },
      // 单个删除
      handleDelete(index){
          let id= this.tableData3[index].name    //对应 prop="name"
          var that=this;
          deleteUser(id,res=>{

            // 操作成功删除数据
            // code==1:??意义
            if(res.code==1){
             that.tableData3.splice(index,1)
            }
          })
      },

      // 增加用户
      addsUser(){
        var that=this;
        // 如果存在edit属性就是编辑接口 否则添加用户接口
        if(that.form.edit){
            adminEdit(that.form,res=>{
                // 关闭弹框
              that.dialogFormVisible = false;
              // 更改界面数据
              that.tableData3[that.form.edit]=that.form
            })
        }else{
	          if(that.form.name==''){
	            that.$message({
	              type: 'error',
	              message: '请输入姓名 '
	             });
	         }else if(that.form.admin==''){
	             that.$message({
	              type: 'error',
	              message: '请选择管理员权限 '
	             });
	         }else if(that.form.password==''){
	             that.$message({
	              type: 'error',
	              message: '请输入密码 '
	             });
	         }else{
	          // 检测用户是否存在
	            exist(that.form.name,res=>{
	              if(res.code>0){
	                   that.$message({
	                    type: 'error',
	                    message: '用户名已存在'
	                  });
	                }else{

	                  // 保存数据
	                  addAdminUser(that.form,res=>{
	                    if(res.code==1){
	                      that.dialogFormVisible =false;
	                      that.$message({
	                      type: 'sucess',
	                      message: '添加成功'
	                    });

	                    // 重新加载
	                    getAllUser(res=>{
	                        that.tableData3=res.data;
	                    })
	                   }
	                 })
	                }
	              })
	         }  
	        }
      },
    }
  }
</script>

<style scoped lang="scss">

</style>