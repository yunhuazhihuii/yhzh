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
                  <!-- <el-button @click="dialogVisible = true" class="elbutton" size="small">添加</el-button> -->
                  <el-button @click="enter" class="elbutton" size="small">绑定店铺</el-button>
                  <el-button @click="addshop" class="elbutton" size="small">添加</el-button>&nbsp&nbsp
                  <el-dialog
                    title="修改店铺"
                    :visible.sync="dialogVisiblec"
                    width="30%"
                    :before-close="handleClose">
                    <el-form :model="form1">
                      <el-form-item label="账号:" :label-width="formLabelWidth">
                        <el-input v-model="form1.account" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <el-form-item label="密码:" :label-width="formLabelWidth">
                        <el-input v-model="form1.password" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <el-form-item label="用户名:" :label-width="formLabelWidth">
                        <el-input v-model="form1.username" :disabled="true" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <el-form-item label="国家:" :label-width="formLabelWidth">
                        <el-input v-model="form1.country" :disabled="true" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <el-form-item label="产品数量:" :label-width="formLabelWidth">
                        <el-input v-model="form1.pronum" :disabled="true" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <el-form-item label="最大产品数量:"  :label-width="formLabelWidth">
                        <el-input v-model="form1.maxpronum" :disabled="true" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <!-- <el-form-item label="店铺状态:" :label-width="formLabelWidth">
                        <el-radio v-model="radio" label="1">正常</el-radio>
                        <el-radio v-model="radio" label="2">封店</el-radio>
                      </el-form-item> -->
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                      <el-button @click="dialogVisiblec = false">取 消</el-button>
                      <el-button type="primary" @click="saveNoticec">保 存</el-button>
                    </span>
                  </el-dialog>
                  <el-dialog
                    title="查看店铺"
                    :visible.sync="dialogVisiblel"
                    width="30%"
                    :before-close="handleClose">
                    <el-form :model="form2">
                      <el-form-item label="账号:" :label-width="formLabelWidth">
                        <el-input v-model="form2.account" autocomplete="off" size="small" style="width:80%" :disabled="true"></el-input>
                      </el-form-item>
                      <el-form-item label="密码:" :label-width="formLabelWidth">
                        <el-input v-model="form2.password" autocomplete="off" size="small" style="width:80%" :disabled="true"></el-input>
                      </el-form-item>
                      <el-form-item label="用户名:" :label-width="formLabelWidth">
                        <el-input v-model="form2.username" autocomplete="off" size="small" style="width:80%" :disabled="true"></el-input>
                      </el-form-item>
                      <el-form-item label="国家:" :label-width="formLabelWidth">
                        <el-input v-model="form2.country" autocomplete="off" size="small" style="width:80%" :disabled="true"></el-input>
                      </el-form-item>
                      <el-form-item label="产品数量:" :label-width="formLabelWidth">
                        <el-input v-model="form2.pronum" autocomplete="off" size="small" style="width:80%" :disabled="true"></el-input>
                      </el-form-item>
                      <el-form-item label="最大产品数量:" :label-width="formLabelWidth">
                        <el-input v-model="form2.maxpronum" autocomplete="off" size="small" style="width:80%" :disabled="true"></el-input>
                      </el-form-item>
                      <!-- <el-form-item label="店铺状态:" :label-width="formLabelWidth">
                        <el-radio v-model="radio" label="1">正常</el-radio>
                        <el-radio v-model="radio" label="2">封店</el-radio>
                      </el-form-item> -->
                    </el-form>
          
                  </el-dialog>
                  <el-dialog
                    title="添加店铺"
                    :visible.sync="dialogVisible"
                    width="30%"
                    :before-close="handleClose">
                    <el-form :model="form">
                      <el-form-item label="账号:" :label-width="formLabelWidth">
                        <el-input v-model="form.account" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <el-form-item label="密码:" :label-width="formLabelWidth">
                        <el-input v-model="form.password" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <el-form-item label="用户名:" :label-width="formLabelWidth">
                        <el-input v-model="form.username" :disabled="true" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <el-form-item label="国家:" :label-width="formLabelWidth">
                        <el-input v-model="form.country" :disabled="true" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <el-form-item label="产品数量:" :label-width="formLabelWidth">
                        <el-input v-model="form.pronum" :disabled="true" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <el-form-item label="最大产品数量:" :label-width="formLabelWidth">
                        <el-input v-model="form.maxpronum" :disabled="true" autocomplete="off" size="small" style="width:60%"></el-input>
                      </el-form-item>
                      <!-- <el-form-item label="店铺状态:" :label-width="formLabelWidth">
                        <el-radio v-model="radio" label="1">正常</el-radio>
                        <el-radio v-model="radio" label="2">封店</el-radio>
                      </el-form-item> -->
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                      <el-button @click="dialogVisible = false">取 消</el-button>
                      <el-button type="primary" @click="saveNotice">保 存</el-button>
                    </span>
                  </el-dialog>

                  <el-dialog
                    title="分配公司"
                    :visible.sync="dialogVisibleallotcomp"
                    width="30%"
                    :before-close="handleClose">
                   <el-select v-model="value" placeholder="请选择" @change="getVaule">
                    <el-option
                      v-for="item in complist"
                      :key="item.compname"
                      :label="item.compname"
                      :value="item.compname">
                    </el-option>
                  </el-select>
                    <span slot="footer" class="dialog-footer">
                      <el-button @click="dialogVisibleallotcomp = false">取 消</el-button>
                      <el-button type="primary" @click="allotcompsave">保 存</el-button>
                    </span>
                  </el-dialog>
                  <el-button class="elbutton" size="small" @click="shopchange">修改</el-button>
                  <el-button class="elbutton" size="small" @click="deleshop">删除</el-button>
                  <el-button class="elbutton" size="small" @click="look">查看</el-button>
                  <el-button class="elbutton" size="small" @click="allotcomp">分配</el-button>
                  <el-button class="elbutton" size="small" @click="expor">导出</el-button>
              </el-row>
          </div>
          <el-select class="select" size="small"  placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>

            <el-option
              :value="item.value"
            </el-option>
          </el-select>  
          
          <el-button class="selectbutton" size="small">筛选</el-button>
                 
              
          
          <el-input class="elinput" size="small" placeholder="店铺名称"></el-input>
            &nbsp&nbsp&nbsp
          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search"></el-button> 
          <div class="shoplist">
            <el-table
              id="export"
              :data="tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
              border
              :header-cell-style="{background:'#F2F2F2'}"
              @selection-change="handleSelectionChange"
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
                prop="account"
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
                prop="partner_id"
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
import url from '../../../config/sysAPI.config.js';
import FileSaver from 'file-saver'
import XLSX from 'xlsx'
    export default{
        data(){
            return { 
              // shoplist:[],
              userid:'',
              multipleSelection:[],
              getshopid:'',
              url:'',
              dialogVisible: false, 
              dialogVisiblec:false,
              dialogVisiblel:false,
              dialogVisibleallotcomp:false,
              complist:[],
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
              formLabelWidth: '120px',

               value: '',
              shop_id:'',
              form: {
                account: '',
                password: '',
                username: '',
                country: '',
                pronum: '',
                maxpronum: ''
              },
              form1: {
                shopid: '',
                account: '',
                password: '',
                username: '',
                country: '',
                pronum: '',
                maxpronum: ''
              },
              form2: {
                shopid: '',
                account: '',
                password: '',
                username: '',
                country: '',
                pronum: '',
                maxpronum: ''
              }


          

             
            }
        },
        methods: {

          //获取公司列表
          getCompList(){
            //请求数据
            var api = url.getCompList;
            var _this = this
            Axios.post(api,
              {
                userid:_this.userid
              }
            )
            .then((response)=>{
              console.log(response.data);
              this.complist=response.data
              // console.log(this.complist+'--+++789')

              // for(var item in response.data){
              //   console.log(response.data[item].compname)
              // }
              // this.complist.push=response.data[item].compname
              // _this.tableData=response.data;
              // this.complist=response.data.compname
              // console.log(this.complist+response.data.compname+"++++----77777")

            })
            .catch((error)=>{
              console.log(error);
            })
          },
           handleSelectionChange(val) {
            // console.log(val[0].country)
            // this.form1.country=val[0].country
            // console.log(this.form1.country)
            this.multipleSelection = val
            // console.log(this.multipleSelection[0].country)
            // this.form1.country=this.multipleSelection[0].country
            // this.form1.username=this.multipleSelection[0].user_name
            // console.log(this.multipleSelection)
          },

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
            var api = url.getShopList;
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
          },
          saveNotice(){
            var api = url.addShop;
            var _this = this
            if(_this.getshopid!=null){
              Axios.post(api,
                {
                  account:_this.form.account,
                  password:_this.form.password,
                  user_name:_this.form.username,
                  shop_id:_this.getshopid,
                  country:_this.form.country,
                  item:_this.form.pronum,
                  item_limit:_this.form.maxpronum

                }
              )
              .then((response)=>{
                console.log(response);
                _this.dialogVisible=false
                _this.getShoplist()
                // _this.tableData=response.data;

              })
              .catch((error)=>{
                console.log(error);
              })
            }else{
              console.log("shopid为空不能提交数据")
            }

          },

          // 修改店铺
          shopchange(){
            this.dialogVisiblec=true
            console.log(this.multipleSelection)
            this.form1.shopid=this.multipleSelection[0].shop_id
            this.form1.account=this.multipleSelection[0].account
            this.form1.password = this.multipleSelection[0].password
            this.form1.username = this.multipleSelection[0].user_name
            this.form1.country=this.multipleSelection[0].country
            this.form1.pronum = this.multipleSelection[0].item
            this.form1.maxpronum = this.multipleSelection[0].item_limit
            // this.form=this.multipleSelection
            // console.log(this.multipleSelection)
            // this.form.country=this.smultipleSelection.country
              // var api = "http://192.168.1.187:8888/api/changeShopByID";
              // var _this = this
              // Axios.post(api,
              //   {
                  
              //     shop_id:''

              //   }
              // )
              // .then((response)=>{
              //   console.log(response.data);
              //   window.open (response.data,'_blank')
              //   // window.open (response.data,'_top')
              //   // _this.tableData=response.data;
                
              // })
              // .catch((error)=>{
              //   console.log(error);
              // })

          },
          saveNoticec(){

            var api = url.addShop;
            var _this = this
            
              Axios.post(api,
                {
                  shop_id:_this.form1.shopid,
                  account:_this.form1.account,
                  password:_this.form1.password,
                  user_name:_this.form1.username,
                  country:_this.form1.country,
                  item:_this.form1.pronum,
                  item_limit:_this.form1.maxpronum

                }
              )
              .then((response)=>{
                console.log(response);
                _this.dialogVisiblec=false
                _this.getShoplist()
                // _this.tableData=response.data;

              })
              .catch((error)=>{
                console.log(error);
              })
            },
            look(){

              this.dialogVisiblel=true
              // console.log(this.multipleSelection)
              this.form2.shopid=this.multipleSelection[0].shop_id
              this.form2.account=this.multipleSelection[0].account
              this.form2.password = this.multipleSelection[0].password
              this.form2.username = this.multipleSelection[0].user_name
              this.form2.country=this.multipleSelection[0].country
              this.form2.pronum = this.multipleSelection[0].item
              this.form2.maxpronum = this.multipleSelection[0].item_limit


            },
            deleshop(){

              var _this = this
              
              var shopid = this.multipleSelection[0].shop_id
              // window.alert("群定")
              this.$confirm('确认删除这个店铺?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then((action) => {
                if(action==='confirm'){
                    // console.log("6666666666669999999999999")
                    var api = url.delShop
                    Axios.post(api,
                      {
                        shop_id:shopid,
                        
                      }
                    )
                    .then((response)=>{
                      console.log(response);
                      // _this.dialogVisiblec=false
                      // _this.tableData=response.data;
                         this.$message({
                          type: 'success',
                          message: '删除成功!'
                        });
                        // location.reload()
                        _this.getShoplist()
                    })
                    .catch((error)=>{
                      console.log(error);
                    })

                 
                }
              
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                });          
              });
              // window.confirm("您确认删除吗？")
              // console.log(shopid+"===========99999999999999999")
              // Axios.post(api,
              //   {
              //     shop_id:shopid,
                  
              //   }
              // )
              // .then((response)=>{
              //   console.log(response);
              //   _this.dialogVisiblec=false
              //   // _this.tableData=response.data;

              // })
              // .catch((error)=>{
              //   console.log(error);
              // })
            },

          addshop(){
            this.dialogVisible=true

          },
          enter(){
            // this.dialogVisible=true
            var api = url.requestUrl;
            var _this = this
            Axios.post(api,
              {
                


              }
            )
            .then((response)=>{
              console.log(response.data);
              window.open (response.data,'_self')
              // window.open (response.data,'_top')
              // _this.tableData=response.data;
              
            })
            .catch((error)=>{
              console.log(error);
            })
            
          },
            get_shopid(){
              // var id = this.$route.query
              // console.log(this.$route.params.id)
              console.log(this.$route.query.shop_id)
              this.getshopid = this.$route.query.shop_id
              console.log(this.getshopid)
            },
            allotcomp(){
              // var that = this
              this.dialogVisibleallotcomp=true
              this.shop_id = this.multipleSelection[0].shop_id
              // console.log(that.multipleSelection)
              // console.log(shopID+'------------87987987978')
              // that.getCompList()
              // console.log(that.complist)
              // console.log(that.value)
              // for(var item in that.complist){
              //   console.log(that.complist[item].compname)
              // }
            },
            // 分配公司的保存方法
            allotcompsave(){
              var shopID = this.shop_id
              var compname = this.value
              // 这里拿到了shopid和compname保存的时候把这两个传到后台
              // console.log(shopID+compname+"99999")

              var api = url.associateShop;
              // var _this = this
              Axios.post(api,
                {
                  shop_id:shopID,
                  compname:compname

                }
              )
              .then((response)=>{
                console.log(response.data);
                this.dialogVisibleallotcomp=false
                this.getShoplist()
                
              })
              .catch((error)=>{
                console.log(error);
              })

              
            },
            getVaule(value){
              // this.tb_code = val;
              // console.log(this.value)
            },
            expor(){
              // table的dom节点
              let et = XLSX.utils.table_to_book(document.getElementById('export'));
              let etout = XLSX.write(et, {
                bookType: 'xlsx',
                bookSST: true,
                type: 'array'
              });
              try {
                FileSaver.saveAs(new Blob([etout], { 
                  type: 'application/octet-stream' 
                }), '店铺列表.xlsx');   //trade-publish.xlsx 为导出的文件名
              } catch (e) {
                  console.log(e, etout) ;
              }
              return etout;
            }
        },
        mounted(){
          this.userid = getSession("user_id");
          this.getShoplist();
          this.get_shopid();
          this.getCompList()
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