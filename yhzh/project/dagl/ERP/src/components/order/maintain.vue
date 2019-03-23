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
                  <el-button class="elbutton" size="small" @click="change = true">修改</el-button>
                  <el-button class="elbutton" size="small" @click="look = true">查看</el-button>
              </el-row>
          </div> 

          <!-- 修改  弹出框   required：必填-->
          <el-dialog title="  修改数据" :visible.sync="change">
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
              <el-button @click="change = false">取 消</el-button>
              <el-button type="primary" @click="change = false">确 定</el-button>
            </div>
          </el-dialog> 

          <!-- 查看  弹出框   required：必填-->
          <el-dialog title="  修改数据" :visible.sync="look">
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
              <el-button @click="look = false">取 消</el-button>
              <el-button type="primary" @click="look = false">确 定</el-button>
            </div>
          </el-dialog> 

          <!-- 搜索框 -->
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
                prop="date"
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
                prop="producthis"
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
                prop="scorehis"
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
                :page-size="100"
                layout="total,sizes, prev, pager, next"
                :total="total">
              </el-pagination>
            </div>
           
          </div>



        </div>
       
        
        
    </div>
</template>


<script>
    export default{

        data(){
            return { 
              
              searchname:' ',   //搜索
              currentPage: 5, 
              pageSize: 2,
              total:0,
              filtertableData:[],
              flag:false,

              dialogTableVisible: false,
              change: false,
              look:false,
              form1: {
                name: '',
                number:'',
                tate:'',
                radio:'',
              },
              form2: {
                name: '',
                number:'',
                tate:'',
                radio:'',
              },
              formLabelWidth: '150px',

             
              
              tableData: [

                {
                  date: '1',
                  name: 'n929',
                  acount: '1000',
                  producthis: '2018-12-20 19:07:06修改为：979',
                  score: '5',
                  scorehis: '2018-12-20 19:07:06修改为：4.0',
                  state: '正常',
                  statehis: '2018-09-25 17:53:23修改为：正常'  
                },
                {
                  date: '1',
                  name: 'ng29',
                  acount: '1000',
                  producthis: '2018-12-20 19:07:06修改为：979',
                  score: '5',
                  scorehis: '2018-12-20 19:07:06修改为：4.0',
                  state: '正常',
                  statehis: '2018-09-25 17:53:23修改为：正常'  
                },
                {
                  date: '1',
                  name: 'ng9',
                  acount: '1000',
                  producthis: '2018-12-20 19:07:06修改为：979',
                  score: '5',
                  scorehis: '2018-12-20 19:07:06修改为：4.0',
                  state: '正常',
                  statehis: '2018-09-25 17:53:23修改为：正常'  
                },{
                  date: '1',
                  name: 'n9289',
                  acount: '1000',
                  producthis: '2018-12-20 19:07:06修改为：979',
                  score: '5',
                  scorehis: '2018-12-20 19:07:06修改为：4.0',
                  state: '正常',
                  statehis: '2018-09-25 17:53:23修改为：正常'  
                },
                {
                  date: '1',
                  name: 'g219',
                  acount: '1000',
                  producthis: '2018-12-20 19:07:06修改为：979',
                  score: '5',
                  scorehis: '2018-12-20 19:07:06修改为：4.0',
                  state: '正常',
                  statehis: '2018-09-25 17:53:23修改为：正常'  
                },
                {
                  date: '1',
                  name: 'ng98',
                  acount: '1000',
                  producthis: '2018-12-20 19:07:06修改为：979',
                  score: '5',
                  scorehis: '2018-12-20 19:07:06修改为：4.0',
                  state: '正常',
                  statehis: '2018-09-25 17:53:23修改为：正常'  
                }
              ]
             
            }
        
        }, 

        methods:{
          handleSelectionChange(val) {
            this.multipleSelection = val;
          },

          handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.pageSize = val;
            this.handleCurrentChange(this.currentChangePage);
          },
          handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.currentChangePage = val;
            //需要判断是否检索
            if(!this.flag){
              this.currentChangePage(this.tableData);
            }else{
              this.currentChangePage(this.filterTableData)  
            }
          },

          //店铺名称 搜索
          searchs(){
            console.log("123654----");

            if (this.searchname == "") {
            this.$message.warning("查询条件不能为空！");
            return;
            }
            this.tableData = [];
            //每次手动将数据置空,因为会出现多次点击搜索情况
            this.filtertableData=[];

            this.tableData.forEach((value, index) => {
              if(value.name){
               if(value.name.indexOf(this.searchname)>=0){
               this.filtertableData.push(value)
               }
            }
            });

            //页面数据改变重新统计数据数量和当前页
            this.currentChangePage=5;
            this.totalItems=this.filtertableData.length
            //渲染表格,根据值
            this.currentChangePage(this.filtertableData)
            //页面初始化数据需要判断是否检索过
            this.flag=true
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