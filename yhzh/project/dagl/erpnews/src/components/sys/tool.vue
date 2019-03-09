<template>
    <div id="maintain">   
        <div class="content">
          <div class="content-top">
            <div class="content-top-title">
              <p>当前位置：交易管理>常用工具管理</p>
            </div>
          </div>
          <div class="content-botton">
              <el-row class="elrow">
                  <el-button class="elbutton" size="small" @click="dialogVisible = true">添加</el-button>
                  <el-button class="elbutton" size="small">修改</el-button>
                  <el-button class="elbutton" size="small">删除</el-button>
                  <el-button class="elbutton" size="small">查看</el-button>
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
              
          
    <!--       <el-input class="elinput" v-model="input" size="small" placeholder="公司ID/公司名称"></el-input>
           
          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search"></el-button>  -->
          <div class="shoplist">
            <el-table
              :data="tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
              border
              :header-cell-style="{background:'#F2F2F2'}"
              style="width: 100%"
              >
              <el-table-column
                type="selection"
                width="50">
              </el-table-column>
              <el-table-column
                prop="userid"
                label="工具名称"
                align="center"
                width="350">
              </el-table-column>
              <el-table-column
                prop="usercnname"
                label="链接"
                width="300"
                align="center">
              </el-table-column>
              <el-table-column
                prop="rolename"
                label="发布时间"
                width="350"
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
    export default{
        data(){
            return {               
              tableData: [],
              currentPage:1,
              pagesize:5,
              dialogVisible: false,
              formLabelWidth: '120px',
              form: {
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
          //    getStaffList(){
          //   //请求数据
          //   var api = 'http://116.62.112.118:8888/api/getStaffList';
          //   var _this = this
          //   Axios.post(api,
          //     {

          //     }
          //   )
          //   .then((response)=>{
          //     console.log(response.data);
          //     _this.tableData=response.data;

          //   })
          //   .catch((error)=>{
          //     console.log(error);
          //   })
          // }
        },
        mounted(){
          // this.getStaffList();
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