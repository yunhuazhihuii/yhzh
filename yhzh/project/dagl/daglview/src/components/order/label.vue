<template>
    <div id="allorder">  
      <!-- 交易管理  > 标签管理 -->
        <p> 当前位置： {{msg}} </p> 

        <div class="btn">
            <button>添加</button>
            <button>修改</button>
            <button>删除</button>
            <button>查看</button>
        </div>


        <el-table
        ref="multipleTable"
        :data="tableData3"
        tooltip-effect="dark"
        border
        style="width:500 px"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          align="center"
          width="60">
        </el-table-column>
        <el-table-column
          label="退货原因"
          prop="mark"
          align="center"
          width="140">
            
        </el-table-column>
        <el-table-column
          prop="name"
          align="center"
          label="排序"
          width="100">
        </el-table-column>
        <el-table-column
          prop="color"
          align="center"
          label="颜色"
          width="100">
        </el-table-column>
      
        <el-table-column
          prop="types"
          label="标签类型"
          align="center"
          width="100"
          >
        </el-table-column>
      </el-table>
       
    </div>
</template>


<script>
    export default{
        data(){
            return {               
                msg:'交易管理  > 标签管理',

                tableData3: [

                    {
                      mark: '03',
                      name: '14',
                      color:'紫色' ,
                      types: '特种部队'
                    }, 
                    {
                      mark: '02',
                      name: '14',
                      color:'紫色' ,
                      types: '特种部队'
                    },
                    {
                      mark: '04',
                      name: '14',
                      color:'紫色' ,
                      types: '特种部队'
                    }, 
                    {
                      mark: '01',
                      name: '14',
                      color:'紫色' ,
                      types: '特种部队'
                    }, 
                    {
                      mark: '08',
                      name: '14',
                      color:'紫色' ,
                      types: '特种部队'
                    }, 
                    {
                      mark: '06',
                      name: '14',
                      color:'紫色' ,
                      types: '特种部队'
                    }, 
                    {
                      mark: '07',
                      name: '14',
                      color:'紫色' ,
                      types: '特种部队'
                    }
                ],
             
            }
        } ,

        methods: {
            handleSelectionChange(val) {
                this.multipleSelection = val;
            }
        }        
    }

</script>

<style scoped>

.btn{
    margin:20px;
}

.btn button{
    margin:2px 10px;
    padding:10px 20px;
    border-radius: 20px;
}

</style>