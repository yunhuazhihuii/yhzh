<template>
    <div id="allorder">  

      <!-- 当前位置： -->
      <div class="position"><span> 当前位置：系统管理 > 主页管理</span></div> 

      <!-- 发布公告开始 -->
      <div class="Announcement"> 

        <p><span>发布公告： </span> </p>

        <el-row>
            <el-button type="info" style="margin-left:20px;">添加</el-button>
            <el-button type="info">修改</el-button>
            <el-button type="info">删除</el-button>
            <el-button type="info">查看</el-button>
            <el-button type="info">导出</el-button>
        </el-row> 

        <el-table
          ref="multipleTable"
          :data="tableData3.slice((currentPage-1)*pagesize,currentPage*pagesize)"
          border
          tooltip-effect="dark"
          style="width: 600px ;margin:20px;"
          @selection-change="handleSelectionChange">
          
          <el-table-column
            type="selection"
            width="60">
          </el-table-column>

          <el-table-column
            prop="name"
            label="公告标题"
            width="360">
          </el-table-column>

          <el-table-column
            label="发布时间"
            width="180">
            <template slot-scope="scope">{{ scope.row.date }}</template>
          </el-table-column>
          
         
        </el-table>

        <!-- 这部分是分页 -->   
        <div class="yema">
          <el-pagination background
            @size-change="handleSizeChange" 
            @current-change="handleCurrentChange"  
            :current-page="currentPage"  
            :page-sizes="[5,10,15]"
            :page-size="pagesize"   
            layout="total,jumper,prev, pager, next,sizes"
            :total="tableData3.length" >
          </el-pagination>
        </div>
        <!-- 这部分是分页 End-->


      </div>
      <!-- 发布公告结束 -->


      <!-- 发布常用工具开始 -->
      <div class="tool"> 

        <p><span>发布常用工具： </span> </p>

        <el-row>
            <el-button type="info" style="margin-left:20px;">添加</el-button>
            <el-button type="info">修改</el-button>
            <el-button type="info">删除</el-button>
            <el-button type="info">查看</el-button>
        </el-row> 

        <el-table
          ref="multipleTable"
          :data="tableData3.slice((currentPage-1)*pagesize,currentPage*pagesize)"
          border
          tooltip-effect="dark"
          style="width:600px;margin:20px;"
          @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="60">
          </el-table-column>
          
          <el-table-column
            prop="name"
            label="工具名称"
            width="180">
          </el-table-column>

          <el-table-column
            prop="address"
            width="180"
            label="链接">
          </el-table-column>

          <el-table-column
            label="日期"
            width="180"
            show-overflow-tooltip>
            <template slot-scope="scope">{{ scope.row.date }}</template>
          </el-table-column>
        </el-table>
        

        <!-- 这部分是分页 -->   
        <div class="yema">
          <el-pagination background
            @size-change="handleSizeChange" 
            @current-change="handleCurrentChange"  
            :current-page="currentPage"  
            :page-sizes="[5,10,15]"
            :page-size="pagesize"   
            layout="total,jumper,prev, pager, next,sizes"
            :total="tableData3.length" >
          </el-pagination>
        </div>
        <!-- 这部分是分页 End-->

      </div>
      <!-- 发布常用工具结束 -->
       
      
    </div>
</template>


<script>
    export default{
        data(){
            return {               
          
              multipleSelection: [],

              currentPage:1,//
              pagesize:5,//每页显示条数

               tableData3: [
                {
                  date: '2016-05-03',
                  name: '王小虎'
                  
                }, 
                {
                  date: '2016-05-02',
                  name: '王小虎'
                
                }
              ]

             
            }
        } ,

        methods:{

           ///分页  初始页currentPage、初始每页数据数pagesize和数据testpage
          handleSizeChange:function(size){
              this.pagesize=size;
          },
          handleCurrentChange:function(currentPage){
              this.currentPage=currentPage;
          }
          ///分页End  初始页currentPage、初始每页数据数pagesize和数据testpage


        }      
    }

</script>

<style scoped>


.Announcement,.tool{
  margin:20px 5px;
  border: 1px solid #aaa;
}

.Announcement p,.tool p{
  margin-bottom: 10px;
  border: 1px solid #aaa;
  padding: 10px;
}

</style>