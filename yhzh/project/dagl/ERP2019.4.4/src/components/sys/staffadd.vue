<template>
    <div>   
       <!-- <h2>添加角色</h2> -->
       <div class="top">
           <p>添加员工</p>
       </div>
       <div class="content">
           <div class="content-top">
               <el-form :model="form" class="roleform">
                <el-form-item label="员工登录名:" :label-width="formLabelWidth">
                  <el-input v-model="form.userid" autocomplete="off" size="small" style="width:30%"></el-input>
                </el-form-item>
                <el-form-item label="员工密码:" :label-width="formLabelWidth">
                  <el-input v-model="form.password" autocomplete="off" size="small" style="width:30%"></el-input>
                </el-form-item>
                <el-form-item label="员工姓名:" :label-width="formLabelWidth">
                  <el-input v-model="form.username" autocomplete="off" size="small" style="width:30%"></el-input>
                </el-form-item>
              </el-form>
           </div>
           <!-- <p>权限</p> -->
           <div class="allotrole">
              <div class="rolelist">
                 <span>分配角色：</span>
                 &nbsp
                 <!-- <template v-for="item in rolelist">
                    <el-radio v-model="radio">{{item.rolename}}</el-radio>
                  </template> -->
        
                  <el-radio-group v-model="radio">
                    <template v-for="item in rolelist">
                       <el-radio :label="item.roleid" @change="rolechange">{{item.rolename}}</el-radio>
                    </template>
                  </el-radio-group>
              </div>
            
           </div>
           
           <div class="allotcomp">
              <div class="complist">
                 <span>分配所属公司：</span>
                 &nbsp
                 <template>
                   <el-select v-model="value" size="small" @change="changcomp" placeholder="请选择" style="width:200px">
                      <el-option
                        v-for="item in complist"
                        :key="item.compid"
                        :label="item.compname"
                        :value="item.compid">
                      </el-option>
                  </el-select>
                  &nbsp&nbsp
                  <el-button class="serbutton" type="primary" @click="shaixuan" size="small" icon="el-icon-search">筛选</el-button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

                   <el-input class="elinput" v-model="searchcontent" size="small" placeholder="输入要搜索的店铺名称"></el-input>
                  <el-button class="serbutton" type="primary" @click="serchshop" size="small" icon="el-icon-search">搜索</el-button>
 
                  </template>
          
              </div>
            
           </div>
            
            <div class="shoplist">
              <div class="fpshop">分配店铺：</div>
              <el-table
                :data="tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
                border
                :header-cell-style="{background:'#F2F2F2'}"
                @selection-change="handleSelectionChange"
                style="width: 1101px"
                >
                <el-table-column width="50">
                   <template slot-scope="scope">
                     <el-checkbox v-model="tableData[scope.$index+pagesize*(currentPage-1)].checked" ></el-checkbox>
                  </template>

                  <!-- <template slot-scope="scope">
                     <el-checkbox @change="abc(scope.$index+pagesize*(currentPage-1))"  ></el-checkbox>
                  </template> -->
                </el-table-column>
                <el-table-column
                  prop="shop_name"
                  label="店铺名称"
                  align="center"
                  width="150">
                </el-table-column>
                <el-table-column
                  prop="account"
                  label="用户名"
                  width="150"
                  align="center">
                </el-table-column>
                <el-table-column
                  prop="shop_id"
                  label="shopid"
                  width="130"
                  align="center">
                </el-table-column>
                <el-table-column
                  prop="partner_id"
                  label="partnerid"
                  width="150"
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
                  width="120"
                  align="center">
                </el-table-column>
                <el-table-column
                  prop="compname"
                  width="250"
                  align="center"
                  label="公司">
                </el-table-column>
              </el-table>
          <!--     <div class="block">
                <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page.sync="currentPage"
                  :page-sizes="[5, 10, 15, 20]"
                  :page-size="pagesize"
                  layout="sizes, prev, pager, next"
                  :total="tableData.length">
                </el-pagination>
              </div> -->
              <div class="block">
                <el-pagination
                  background
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page.sync="currentPage"
                  :page-size="pagesize"
                  layout="total, prev, pager, next"
                  :total="tableData.length">
                </el-pagination>
              </div>
             
            </div>

           
       </div>
       <div class="bobutton">
           <el-button type="primary" @click="saveNotice">保 存 </el-button>
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
              searchcontent:'',
              shopids:[],
               checked:false, 
               value:'', 
               userid:'', 
               roleid:'', 
               compid:'',          
               msg:'组件',
               formLabelWidth: '120px',
               form: {
                userid: '',
                password: '',
                username: ''
              },
               tableData: [],
               multipleSelection: [],
               multipleSelectionAll: [], // 所有选中的数据包含跨页数据
               idKey: "personId", // 标识列表数据中每一行的唯一键的名称(需要按自己的数据改一下)
               currentPage:1,
              pagesize:7,
              complist:[],
              rolelist:[],
              radio: '',
              // checkAll: false,
              // checkedCities: ['上海', '北京'],
              // cities: cityOptions,
              // isIndeterminate: true
            }
        },
        methods:{
 
          handleSelectionChange(val) {
            console.log(val)
            this.multipleSelection = val
            
          },
           //改变时
           handleSizeChange(val) {
           
                this.pagesize = val;
               
           },
            //条目改变时
           handleCurrentChange(val) {
           
                this.currentPage = val;
           },
           // 选中角色获取选中角色的id
           rolechange(value){
            console.log(value)
            this.roleid=value
           },
           // change时间获取选中的公司的id
           changcomp(value){
            console.log(value)
            this.compid=value
           },

           // 条件查询搜索
          serchshop(){
            console.log('89')
            var api = url.getShopLike;
            var _this = this
            Axios.post(api,
              {
                userid:_this.userid,
                shop_name:_this.searchcontent
              }
            )
            .then((response)=>{
              console.log(response.data);
              _this.tableData=response.data

            })
            .catch((error)=>{
              console.log(error);
            })

          },
           // currentSel(){
           //  console.log('98')
           // },
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
              _this.complist = response.data
            })
            .catch((error)=>{
              console.log(error);
            })
          },

          getshopid(){
            
          },
          getShoplist(){
            // var ordersnT = '';
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

              for(var i=0;i<_this.tableData.length;i++){
                _this.tableData[i].checked = false;

                // if(_this.tableData[i].checked == true){
                //   ordersnT += this.tableData[i].shop_id +',';
                //   console.log(ordersnT +'++++++++++++++ordersnT');
                // }
              }
            })
            .catch((error)=>{
              console.log('54545')
              console.log(error);
            })
          },

          shaixuan(){
            console.log(this.value)

            var api = url.getShopList;
            var _this = this
            Axios.post(api,
              {
                userid:_this.userid,
                compid:_this.value
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
              _this.rolelist=response.data;

            })
            .catch((error)=>{
              console.log(error);
            })
          },



          saveNotice(){

            var shopid = ''
            // console.log('89898898989')
            var that = this;
            var api = url.addStaff;


            for(var i=0;i<that.tableData.length;i++){
              if(this.tableData[i].checked == true){
                shopid += this.tableData[i].shop_id +',';
                console.log(shopid)
              }
            }
            Axios.post(api,
              {
                userenname:that.form.userid,
                usercnname:that.form.username,
                password:that.form.password,
                userid:'0',
                remark:'',
                roleid:that.roleid,
                c_userid:that.userid,
                compid:that.compid,
                shop_id:shopid
                
              }
            )
            .then((response)=>{
              console.log(response)
              that.$router.push({ path: '/sys/staff' });
              // that.dialogVisible = false;
              // that.getBulletinList()
            })
            .catch((error)=>{
              console.log(error)
            })
          },
        },
        mounted(){
          this.userid = getSession("user_id");
          this.getCompList();
          this.getShoplist();
          this.getRoleList();
        }     
    }

</script>

<style scoped>
    .top {
        width: 1200px;
        height: 38px;
        background-color: #F2F2F2;
        border: 1px solid #CCCCCC;
    }
    .top p {
        margin-top: 7px;
        margin-left: 10px;
        color: #333368;
        font-weight: bold;
    }
    .content {
        width: 1200px;
        height: 910px;
        border: 1px solid #CCCCCC;
        margin-top: 20px;

    }
    .content .content-top{
        width: 100%;
        height: 200px;
        border-bottom: 1px solid #CCCCCC;
        /* background-color: pink; */
    }
    .roleform {
        padding-top: 15px
    }
    .allotrole {
        width: 100%;
        height: 100px;
        border-bottom: 1px solid #CCCCCC;
    }
    .allotrole .rolelist {
      padding-top: 40px; 
      padding-left: 46px;
    }
    .allotcomp {
      width: 100%;
      height: 100px;
      border-bottom: 1px solid #CCCCCC;
    }
    .allotcomp .complist {
      padding-top: 40px; 
      padding-left: 46px;
    }
    
    .shoplist {
      padding-top: 20px; 
      padding-left: 46px;
    }
    .fpshop {
      margin-bottom: 10px;
    }
    
    .checkall {
        display: inline-block;
    }
    .bobutton {
        margin-top: 20px;
        margin-left: 1120px;
    }
  
    .elinput {
      display: inline-block;
      width: 200px;
    }
</style>