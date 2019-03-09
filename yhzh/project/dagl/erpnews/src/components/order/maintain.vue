<template>
    <div id="maintain">   
        <div class="content">
          <div class="content-top">
            <div class="content-top-title">

              <!-- 交易管理>店铺维护1 -->
              <p>当前位置：{{msg}} </p>
            </div>
          </div>
          <div class="content-botton">
              <el-row class="elrow">
                  <el-button class="elbutton" size="small">修改</el-button>
                  <el-button class="elbutton" size="small">查看</el-button>
              </el-row>
          </div>  
          <el-input class="elinput" v-model="input" size="small" placeholder="店铺名称"></el-input>
            &nbsp;&nbsp;&nbsp;
          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search"></el-button> 
          <div class="shoplist">
            <el-table
              :data="tableData"
              border
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
                :current-page.sync="currentPage2"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="100"
                layout="sizes, prev, pager, next"
                :total="1000">
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
              msg:"交易管理>店铺维护1",    

              tableData: [

                {
                  date: '1',
                  name: 'ng929',
                  acount: '1000',
                  producthis: '2018-12-20 19:07:06修改为：979',
                  score: '5',
                  scorehis: '2018-12-20 19:07:06修改为：4.0',
                  state: '正常',
                  statehis: '2018-09-25 17:53:23修改为：正常'  
                },
                {
                  date: '1',
                  name: 'ng929',
                  acount: '1000',
                  producthis: '2018-12-20 19:07:06修改为：979',
                  score: '5',
                  scorehis: '2018-12-20 19:07:06修改为：4.0',
                  state: '正常',
                  statehis: '2018-09-25 17:53:23修改为：正常'  
                },
                {
                  date: '1',
                  name: 'ng929',
                  acount: '1000',
                  producthis: '2018-12-20 19:07:06修改为：979',
                  score: '5',
                  scorehis: '2018-12-20 19:07:06修改为：4.0',
                  state: '正常',
                  statehis: '2018-09-25 17:53:23修改为：正常'  
                }
              ]
             
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