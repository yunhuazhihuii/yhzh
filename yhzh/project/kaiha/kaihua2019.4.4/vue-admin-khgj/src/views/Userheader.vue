<template> 
    <div class="userheader" >   

        <div class="admin" @click="dialogFormVisible = true"><span class="txt" >admin</span> <img src="../assets/user.png"> </div> 


        <!-- visible 是否显示 Dialog，支持 .sync 修饰符  boolean — false -->
        <el-dialog title="用户编辑" :visible.sync="dialogFormVisible">
            <el-table 
            :data="tableData"
            style="width: 100%">
            <el-table-column
              label="序号"
              prop="date"
              width="100">
            </el-table-column>

            <el-table-column
              label="中文名"
              prop="name"
              width="100">
            </el-table-column>

            <el-table-column
              label="英文名"
              prop="English"
              width="100">
              
            </el-table-column>

            <el-table-column
              label="密码"
              prop="pwad"
              width="100">
            </el-table-column>

            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>
           </el-table>
         
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
          </div>
        </el-dialog>

        
    
    </div>
</template>


<script> 

export default{
    data(){
        return { 
            tableData: [{
              date: '1',
              name: '张三',
              English: 'vgjab ',
              pwad:" ####"
            }, {
              date: '1',
              name: '张三',
              English: 'vgjab ',
              pwad:" ####"
            }, {
              date: '1',
              name: '张三',
              English: 'vgjab ',
              pwad:" ####"
            }, {
              date: '1',
              name: '张三',
              English: 'vgjab ',
              pwad:" ####"
            }],

            dialogFormVisible: false,
            form: {
              name: '',
              region: '',
              date1: '',
              date2: '',
              delivery: false,
              type: [],
              resource: '',
              desc: ''
            },
            formLabelWidth: '120px'              
            
        }
    },

    methods: {
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      }
    }      
}


    
</script>

<style scoped>

.userheader{
    overflow: hidden;
    margin-right: 15px;
    margin-top:10px;
}

.admin{
    float:right;
}
.txt{
    position: relative;
    top: -10px;
}


img{
    height:30px;
    width:30px;
    border-radius: 50%;
}


</style>