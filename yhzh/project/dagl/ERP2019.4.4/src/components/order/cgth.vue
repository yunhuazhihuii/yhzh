<template>
    
  <div>

  	<div class="content">
          <div class="content-top">
            <div class="content-top-title">
              <p>当前位置：交易管理>采购退货订单登记</p>
            </div>
          </div>
          <div class="content-botton">
              <el-row class="elrow">
                  <el-button class="elbutton" size="small" @click="addreturn()">添加</el-button>
                  <el-button class="elbutton" size="small" @click="changereturn()">修改</el-button>
                  <el-button class="elbutton" size="small" @click="delereturn()">删除</el-button>
                  <el-button class="elbutton" size="small" @click="lookreturn()">查看</el-button>  
                  <el-button class="elbutton" size="small" @click="bjwftj()">标记为无法退件</el-button>
              </el-row>

          </div> 

          <!-- 添加退货订单登记 -->
          <el-dialog
              title="添加退货订单登记"
              :visible.sync="dialogVisible"
              width="50%">

              <el-form :model="form">
                <el-form-item label="订单号:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.comid" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="淘宝订单号:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.compname" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>

                <el-form-item label="退货原因:" :label-width="formLabelWidth" required>
                  <!-- <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input> -->
                  	<template>
					  <el-select v-model="value" placeholder="请选择">
					    <el-option
					      v-for="item in options"
					      :key="item.value"
					      :label="item.label"
					      :value="item.value">
					    </el-option>
					  </el-select>
					</template>
                </el-form-item>

                <el-form-item label="退货货值:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="退货运费:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.password" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="退货信息:" :label-width="formLabelWidth" required>
                  <el-input type="textarea" v-model="form.desc" :rows="7" style="width:70%"></el-input>
                </el-form-item>

                <el-form-item label="运费支付方:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                

              </el-form>
              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveNotice">添 加</el-button>
              </span>
          </el-dialog>  


          <!-- 修改退货订单登记 -->
          <el-dialog
              title="修改退货订单登记"
              :visible.sync="dialogVisibles"
              width="50%">
              
              <el-form :model="form">
                <el-form-item label="订单号:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.comid" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="淘宝订单号:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.compname" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>

                <el-form-item label="退货原因:" :label-width="formLabelWidth" required>
                  <!-- <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input> -->
                  	<template>
					  <el-select v-model="value" placeholder="请选择">
					    <el-option
					      v-for="item in options"
					      :key="item.value"
					      :label="item.label"
					      :value="item.value">
					    </el-option>
					  </el-select>
					</template>

                </el-form-item>

                <el-form-item label="退货货值:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="退货运费:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.password" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                <el-form-item label="退货信息:" :label-width="formLabelWidth" required>
                  <el-input type="textarea" v-model="form.desc" :rows="7" style="width:70%"></el-input>
                </el-form-item>

                <el-form-item label="运费支付方:" :label-width="formLabelWidth" required>
                  <el-input v-model="form.account" autocomplete="off" size="small" style="width:70%"></el-input>
                </el-form-item>
                
              </el-form>
              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisibles = false">取 消</el-button>
                <el-button type="primary" @click="savexiugai()">保存</el-button>
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
                prop="ordern"
                label="订单号"
                align="center"
                width="150">
              </el-table-column>

              <el-table-column
                prop="tb_order_no"
                label="淘宝订单号"
                width="200"
                align="center">
              </el-table-column>

              <el-table-column
                prop="returnreason"
                label="退货原因"
                width="150"
                align="center">
              </el-table-column>

              <el-table-column
                prop="th_value"
                label="退货货值"
                width="100"
                align="center">
              </el-table-column>

              <el-table-column
                prop="th_fee"
                label="退货运费"
                width="100"
                align="center">
              </el-table-column>

              <el-table-column
                prop="thperson_new"
                label="退货收件人信息"
                width="200"
                align="center">
              </el-table-column>

              <el-table-column
                prop="thwl_no"
                width="150"
                align="center"
                label="物流运输号">
              </el-table-column>

              <el-table-column
                prop="feestyle"
                align="center"
                width="150"
                label="退货运费支付方式">
              </el-table-column>

              <el-table-column
                prop="quest_th"
                label="仓库确认是否退货"
                width="150"
                align="center">
              </el-table-column>

              <el-table-column
                prop="questfee"
                width="150"
                align="center"
                label="仓库确认退货运费">
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
import url from '../../../config/sysAPI.config.js';
import {getSession} from '../../common/js/util';

export default{
        data(){
            return { 
                  
                        
              userids:'',
              
              multipleSelection:[],

              options: [
              	{
		          value: '选项1',
		          label: '12356'
		        }, 
		        {
		          value: '选项2',
		          label: '789'
		        }, 
		        {
		          value: '选项3',
		          label: '852'
		        }, 
		        {
		          value: '选项4',
		          label: '741'
		        }, 
		        {
		          value: '选项5',
		          label: '951'
		        }
		      ],
		      value: '',
		       

		      
              tableData: [  
                 {
                  ordern:"123 ",
                  tb_order_no:"123 ",
                  returnreason:"123 ",
                  th_value:"123 ",
                  th_fee:"123 ",
                  thperson_new:"123 ",
                  thwl_no:"123 ",
                  quest_th:"123 ",
                  questfee:"123 ",
                  feestyle:"123 "
                 } 
              ],
              currentPage:1,
              pagesize:10,
              dialogVisible: false, 
              dialogVisibles: false, 
              formLabelWidth: '240px',
              form: {
                
                
              },
              form1: {
                
                
              },
             
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
          

          handleSelectionChange(val) {
            this.multipleSelection = val
          },

          //请求数据、查询数据
          getreturndata(){

            var api = url.getreturn;
            var that = this;
            
            Axios.post(api,
              {
               
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


          //添加
          addreturn(){
            this.dialogVisible=true;
          },


          //添加保存
          saveNotice(){

            var that = this;

            var api = url.addreturn;

             Axios.post(api,
              {
                
              }
            ).then((response)=>{
              console.log("添加保存---", response)
              that.dialogVisiblec = false;
              that.getreturndata();
            })
            .catch((error)=>{
              console.log(error)
            })

          },


          //修改
          changereturn(){

          	console.log("123");

            if(this.multipleSelection.length<1){
              this.$message({
                    type: 'info',
                    message: '请先勾选一个要修改的店铺'
                  });
            }else if(this.multipleSelection.length>1){
              this.$message({
                    type: 'error',
                    message: '不能同时修改多个店铺'
                  });
            }else{
              this.dialogVisibles=true;
              console.log(this.multipleSelection);
              // this.form1.shopid=this.multipleSelection[0].shop_id
              
            }
          },


          //修改保存
          savexiugai(){
            var that = this;
            var api = url.addreturn;
            Axios.post(api,
              {

              }
            ).then((response)=>{
              console.log("修改保存----",response);
              that.dialogVisible = false;
              that.getreturndata()
            })
            .catch((error)=>{
              console.log(error)
            })
          },

          
          


          lookreturn(){
            console.log("123456");
          },

          

          //删除
          delereturn(){
            console.log("456");
            if(this.multipleSelection.length<1){
            	this.$message({
                   type: 'info',
                   message: '请先勾选一个要删除的订单'
                });
            }else{

            }
          },

          //标记为无法退件
          bjwftj(){
            console.log("789")
          }

        },
        
        mounted(){
          this.userids = getSession("user_id");
          console.log("this.userids----",this.userids)
          this.getreturndata();
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
    
    height: 65px;
    width: 1200px;
  }
  .shoplist {
    position: absolute;
    top: 104px;
    width: 1551px;
    
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