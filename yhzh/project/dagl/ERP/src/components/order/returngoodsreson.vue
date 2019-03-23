<template>
    <div id="allorder">  
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
        style="width: 40%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          align="center"
          width="55">
        </el-table-column>
        <el-table-column
          label="退货原因"
          align="center"
          prop="reason"
          width="200">
            
        </el-table-column>
        
        <el-table-column
          prop="name"
          label="排序"
          align="center"
          show-overflow-tooltip>
        </el-table-column>
      </el-table>
       
    </div>
</template>


<script>
    export default{
        data(){
            return {               
                msg:'交易管理 > 退货原因管理 ',

                tableData3: [

                    {
                      reason: '就是想退货，怎么着？3',
                      name: '14'
                    }, 
                    {
                      reason: '就是想退货，怎么着？2',
                      name: '14'
                    },
                    {
                      reason: '就是想退货，怎么着？4',
                      name: '14'
                    }, 
                    {
                      reason: '就是想退货，怎么着？1',
                      name: '14'
                    }, 
                    {
                      reason: '就是想退货，怎么着？8',
                      name: '14'
                    }, 
                    {
                      reason: '就是想退货，怎么着？6',
                      name: '14'
                    }, 
                    {
                      reason: '就是想退货，怎么着？7',
                      name: '14'
                    }
                ],
             
            }
        },

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