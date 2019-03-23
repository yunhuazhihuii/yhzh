<template>
    <div id="maintain">   
        <div class="content">
          <div class="content-top">
            <div class="content-top-title">
              <p>当前位置：交易管理>角色管理</p>
            </div>
          </div>
          <div class="content-botton">
              <el-row class="elrow">
                  <el-button class="elbutton" size="small" @click="goroleadd">添加</el-button>
                  <el-button class="elbutton" size="small">修改</el-button>
                  <el-button class="elbutton" size="small">删除</el-button>
                  
              </el-row>
          </div> 
                 
              
          
          <el-input class="elinput" size="small" placeholder="公司ID/公司名称"></el-input>
            
          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search"></el-button> 
          <div class="shoplist">
            <el-table
              :data="tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
              border
              :header-cell-style="{background:'#F2F2F2'}"
              style="width: 100%"
              @selection-change="handleSelectionChange"
              >
              <el-table-column
                type="selection"
                width="50">
              </el-table-column>
              <el-table-column
                prop="rolename"
                label="角色名称"
                align="center"
                width="150">
              </el-table-column>
              <el-table-column
                prop="roleauth"
                label="角色权限"
                width="200"
                align="center">
              </el-table-column>
              <el-table-column
                prop="roledesc"
                label="角色描述"
                width="250"
                align="center">
              </el-table-column>
              <el-table-column
                prop="createusername"
                label="创建人"
                align="center">
              </el-table-column>
              <el-table-column
                prop="createtime"
                label="创建时间"
                width="200"
                align="center">
              </el-table-column>
              <el-table-column
                prop="modusername"
                label="修改人"
                width="150"
                align="center">
              </el-table-column>
              <el-table-column
                prop="modtime"
                width="200"
                align="center"
                label="修改时间">
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
import url from '../../../config/sysAPI.config.js'
import {getSession} from '../../common/js/util';
    export default{
        data(){
            return {   
              userid:'',    
              multipleSelection: [],        
               tableData: [ ],

                currentPage:1,
                pagesize:5,
             
            }
        },
        methods: {

          goroleadd(){
            this.$router.push({path:'/sys/roleadd'})
          },
          // 选择后可以获取到这一行的数据val把它放到multipleSelection
           handleSelectionChange(val) {
            this.multipleSelection = val;
            console.log(val)
          },


          handleEdit(index, row) {
            console.log(index, row);
          },
          handleDelete(index, row) {
            console.log(index, row);
          },
           getRoleList(){
            //请求数据
            var api = url.getRoleList;
            var _this = this
            Axios.post(api,
              {
                userid:_this.userid
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
          this.getRoleList();
          this.userid = getSession("user_id");
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

</style>
