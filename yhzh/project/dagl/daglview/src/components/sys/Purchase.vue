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
                  <el-button class="elbutton" size="small">添加</el-button>
                  <el-button class="elbutton" size="small">修改</el-button>
                  <el-button class="elbutton" size="small">删除</el-button>
                  <el-button class="elbutton" size="small">查看</el-button>
              </el-row>
          </div> 
                 
              
          
          <el-input class="elinput" size="small" placeholder="淘宝账号"></el-input>
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

  export default{

        data(){
            return {               
              tableData: [],
              currentPage:1,
              pagesize:5,
             
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

          getPurinfo(){

            //请求数据
            var api = 'http://192.168.1.187:8888/api/getPurinfo';
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