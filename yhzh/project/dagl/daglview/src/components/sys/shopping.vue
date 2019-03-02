<template>
    <div id="maintain">   
        <div class="content">
          <div class="content-top">
            <div class="content-top-title">
              <p>当前位置：交易管理>店铺列表管理</p>
            </div>
          </div>
          <div class="content-botton">
              <el-row class="elrow">
                  <el-button @click="dialogVisible = true" class="elbutton" size="small">添加</el-button>
                  <el-dialog
                    title="添加店铺"
                    :visible.sync="dialogVisible"
                    width="30%"
                    :before-close="handleClose">
                    <div class="dialogdiv">
                      <span>账号：</span><el-input  size="small" class="addshop" v-model="input" placeholder="请输入内容"></el-input><br>
                    </div>
                    <div class="dialogdiv">
                      <span>密码：</span><el-input  size="small" class="addshop" v-model="input" placeholder="请输入内容"></el-input><br>
                    </div >
                    <div class="dialogdiv">
                      <span>用户名：</span><el-input  size="small" class="addshopname" v-model="input" placeholder="请输入内容"></el-input><br>
                    </div>
                    <div class="dialogdiv">
                      <span>国家：</span><el-input  size="small" class="addshop" v-model="input" placeholder="请输入内容"></el-input><br>
                    </div>
                    <div class="dialogdiv">
                      <span>产品数量：</span><el-input  size="small" class="acount" v-model="input" placeholder="请输入内容"></el-input><br>
                    </div>
                    <div class="dialogdiv">
                      <span>最大产品数量：</span><el-input  size="small" class="maxacount" v-model="input" placeholder="请输入内容"></el-input><br>
                    </div>
                   <div class="dialogdiv">
                      店铺状态：<el-radio v-model="radio" label="1">正常</el-radio>
                                <el-radio v-model="radio" label="2">封店</el-radio>
                    </div>
                    <span slot="footer" class="dialog-footer">
                      <el-button @click="dialogVisible = false">取 消</el-button>
                      <el-button type="primary" @click="dialogVisible = false">添 加</el-button>
                    </span>
                  </el-dialog>
                  <el-button class="elbutton" size="small">修改</el-button>
                  <el-button class="elbutton" size="small">删除</el-button>
                  <el-button class="elbutton" size="small">查看</el-button>
                  <el-button class="elbutton" size="small">分配</el-button>
                  <el-button class="elbutton" size="small">导出</el-button>
              </el-row>
          </div>
          <el-select class="select" size="small" v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>  
          
          <el-button class="selectbutton" size="small">筛选</el-button>
                 
              
          
          <el-input class="elinput" v-model="input" size="small" placeholder="店铺名称"></el-input>
            &nbsp&nbsp&nbsp
          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search"></el-button> 
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
                prop="shop_name"
                label="店铺名称"
                align="center"
                width="250">
              </el-table-column>
              <el-table-column
                prop="user_name"
                label="用户名"
                width="160"
                align="center">
              </el-table-column>
              <el-table-column
                prop="shop_id"
                label="shopid"
                width="150"
                align="center">
              </el-table-column>
              <el-table-column
                prop="partnerid"
                label="partnerid"
                align="center">
              </el-table-column>
              <el-table-column
                prop="item_limit"
                label="最大上新数"
                width="100"
                align="center">
              </el-table-column>
              <el-table-column
                prop="country"
                label="国家"
                width="100"
                align="center">
              </el-table-column>
              <el-table-column
                prop="compname"
                width="250"
                align="center"
                label="公司">
              </el-table-column>
              <el-table-column
                prop="operation"
                align="center"
                width="200"
                label="操作">
                <template slot-scope="scope">
                    <el-button
                      size="mini"
                      type="primary"
                      @click="handleEdit(scope.$index, scope.row)">查看用户</el-button>
                    <el-button
                      size="mini"
                      type="danger"
                      @click="handleDelete(scope.$index, scope.row)">检查</el-button>
                </template>
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
    export default{
        data(){
            return { 
              // shoplist:[],
              dialogVisible: false, 
               radio: '1',             
               tableData: [
              //  {
              //     shopname:'1',
              //     name:'1',
              //     shopid:'1',
              //     partnerid:'1',
              //     maxnew:'1',
              //     contry:'1',
              //     company:'1'
              // }
              ],
              currentPage:1,
              pagesize:10,

          

             
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
          getShoplist(){
            //请求数据
            var api = 'http://192.168.1.187:8888/api/getShopList';
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
          }
        },
        mounted(){
          this.getShoplist();
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
  .addshop {
    width: 300px;
    margin-left: 56px;
  }
  .addshopname{
    width: 300px;
    margin-left: 42px;
  }
  .acount {
    width: 300px;
    margin-left: 28px;
  }
   .maxacount {
    width: 300px;
    
  }
   .dialogdiv span {
   /* padding-right: 50px; */

  }
  .dialogdiv {
    margin-bottom: 5px;
  }
</style>