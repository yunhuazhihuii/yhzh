
<!-- donghp 2019.3.27   。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。你说什么就是什么吧！SB,懒得理你！ -->

<template>
    <div id="maintain">   

        <div class="content">

          <div class="content-top">

            <div class="content-top-title">
              <p>当前位置：交易管理>店铺维护 </p>
            </div>

          </div>

          <div class="content-botton">
              <el-row class="elrow">
                  <el-button class="elbutton" size="small" @click="change_dialogVisiblec()">修改</el-button>
                  <el-button class="elbutton" size="small" @click="look_dialogVisiblec()">查看</el-button>
              </el-row>
          </div> 

          <!-- 修改  弹出框   required：必填-->
          <el-dialog title="  修改数据" 
            :visible.sync="changedialogVisiblec" 
            >
            <el-form :model="form1">
              <el-form-item label="店铺名称：" :label-width="formLabelWidth" required>
                <el-input v-model="form1.name" auto-complete="off"></el-input>
              </el-form-item>

              <el-form-item label="店铺产品数量：" :label-width="formLabelWidth" required>
                <el-input v-model="form1.number" auto-complete="off"></el-input>
              </el-form-item>

              <el-form-item label="店铺评分：" :label-width="formLabelWidth" required>
                <el-input v-model="form1.rate" auto-complete="off"></el-input>
              </el-form-item>

              <el-form-item label="店铺状态：" :label-width="formLabelWidth" required>
                <template>
                  <el-radio v-model="form1.radio" label="1">正常</el-radio>
                  <el-radio v-model="form1.radio" label="2">封店</el-radio>
                </template>
              </el-form-item>
            </el-form>
            <br><br><br>

            <div slot="footer" class="dialog-footer">
              <el-button @click=" changedialogVisiblec= false ">取 消</el-button>
              <el-button type="primary" @click="changedata() ">确 定</el-button>
            </div>
          </el-dialog> 


          <!-- 查看  弹出框   required：必填-->
          <el-dialog title="  修改数据" 
            :visible.sync="lookdialogVisiblec" 
           >
            <el-form :model="form2">
              <el-form-item label="店铺名称：" :label-width="formLabelWidth" required>
                <el-input v-model="form2.name" auto-complete="off"></el-input>
              </el-form-item>

              <el-form-item label="店铺产品数量：" :label-width="formLabelWidth" required>
                <el-input v-model="form2.number" auto-complete="off"></el-input>
              </el-form-item>

              <el-form-item label="店铺评分：" :label-width="formLabelWidth" required>
                <el-input v-model="form2.rate" auto-complete="off"></el-input>
              </el-form-item>

              <el-form-item label="店铺状态：" :label-width="formLabelWidth" required>
                <template>
                  <el-radio v-model="form2.radio" label="1">正常</el-radio>
                  <el-radio v-model="form2.radio" label="2">封店</el-radio>
                </template>
              </el-form-item>
            </el-form>
            <br><br><br>

            <div slot="footer" class="dialog-footer">
              <el-button @click="lookdialogVisiblec = false">取 消</el-button>
              <el-button type="primary" @click="lookdtata">确 定</el-button>
            </div>
          </el-dialog> 

          <!-- 搜索框 -->
          <!-- <el-input class="elinput" v-model="searchname" size="small" placeholder="店铺名称"></el-input> -->

          <el-input class="elinput" v-model="searchname" size="small" placeholder="店铺名称"></el-input>
            &nbsp;&nbsp;&nbsp;

          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search" @click="searchs()"></el-button> 


          <div class="shoplist">
            <el-table
              :data="tableData"
              border
              @selection-change="handleSelectionChange"
              style="width: 100%"
              >
              <el-table-column
                type="selection"
                align="center"
                prop="ID"
                width="50">

              </el-table-column>
              <el-table-column
                prop="name"
                label="店铺名称"
                width="160"
                align="center">
              </el-table-column>
              <el-table-column
                prop="acount"
                label="店铺产品数量"
                width="150"
                align="center">
              </el-table-column>
              <el-table-column
                prop="history"
                label="店铺产品数量修改历史"
                align="center">
              </el-table-column>
              <el-table-column
                prop="score"
                label="店铺评分"
                width="80"
                align="center">
              </el-table-column>
              <el-table-column
                prop="ratehistory"
                label="店铺评分修改历史"
                align="center">
              </el-table-column>
              <el-table-column
                prop="state"
                width="150"
                align="center"
                label="店铺状态">
              </el-table-column>
              <el-table-column
                prop="statehis"
                align="center"
                label="店铺状态修改历史">
              </el-table-column>
            </el-table>
            <div class="block">
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page.sync="currentPage"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="pageSize"
                layout="total,sizes, prev, pager, next"
                :total="tableData.length">
              </el-pagination>
            </div>
           
          </div>



        </div>
       
        
        
    </div>
</template>


<script>

/*

donghp   2019.3.27

这个人很懒什么也没留下，你自己看着办吧。


 */

import Axios from 'axios';
import url from '../../../config/sysAPI.config.js';

    export default{

        data(){
            return { 
              
              searchname:' ',   //搜索
              currentPage: 5, 
              pageSize: 10,
              
              total:0,

              tableData: [
                {
                  name:"asd", 
                  acount:" 12365" ,
                  history:"12365" ,
                  score:" 4587",
                  ratehistory:"qwr",
                  state:" asdw",
                  statehis:" awsc"
                }
                    
              ],
              multipleSelection:[],   //选中栏目的全部信息
              
              changedialogVisiblec:false,
              lookdialogVisiblec:false,

              form1: {
                name: '',
                number:'',
                tate:'',
                radio:''
              },

              form2: {
                name: '',
                number:'',
                tate:'',
                radio:'',
              },
              formLabelWidth: '150px'


             
            }
        
        }, 

        methods:{

          handleSelectionChange(val) {
            this.multipleSelection = val;
            
          },

          //改变时
          handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.pageSize = val;
           
          },

          //条目改变时
          handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.currentChangePage = val;
          },


          //请求数据
          getShoplist(){
            // var api = url.getShopList;
            var that = this;
            Axios.post(api,
              {
                // userid:that.userid
              }
            )
            .then((response)=>{
              console.log(response.data);
              that.tableData=response.data;

            })
            .catch((error)=>{
              console.log(error);
            })
          },

        

          change_dialogVisiblec(){
            this.changedialogVisiblec=true;
            console.log( "123");
          },

          look_dialogVisiblec(){
            this.lookdialogVisiblec=true;
          },

          //修改
          changedata(){

            
            // console.log("multipleSelection---------", this.multipleSelection);

            var that = this;
            var api = url.getShopLike;

            Axios.post(api,
              {
                userid:that.userid,
                shop_name:that.searchcontent
              }
            )
            .then((response)=>{
              console.log(response.data);
              // that.tableData=response.data;

            })
            .catch((error)=>{
              console.log(error);
            })
          },


          //查看
          lookdtata(){

            console.log("multipleSelection-------",this.multipleSelection);
            var that = this;
            that.lookdialogVisiblec=true;
            that.form2.name =that.multipleSelection[0].name;
            that.form2.number =that.multipleSelection[0].number;
            that.form2.rate =that.multipleSelection[0].rate;
            that.form2.radio =that.multipleSelection[0].radio

          },

          //店铺名称搜索
          searchs(){

            console.log("123654----");

            var api = url.getShopLike;
            var that = this;
            Axios.post(api,
              {
                userid:that.userid,
                shop_name:that.searchcontent
              }
            )
            .then((response)=>{
              console.log(response.data);
              // that.tableData=response.data

            })
            .catch((error)=>{
              console.log(error);
            })

          }

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
    left: 632px;
    top: 58px;
    width: 370px;
  }
  .serbutton {
    position: absolute;
    left: 1000px;
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
    
    width: 1350px;
   
  }
  .elrow {
    margin-top: 16px;
  }
  .elbutton {
    background-color: #76A3A3;
    color: #fff;
    font-weight: bold;
  }

</style>