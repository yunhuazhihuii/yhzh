<template>
    <div id="allorder">   
      <div class="top">
        <p>当前位置：收货管理>采购退货订单管理</p>
       
        <div class="btnseach">
          <button>填写退件</button>
          <button>修改</button>
          <button>查看</button>
          <button>标记为无法退件</button>
        </div>
        
      </div>

      <el-table
        ref="multipleTable"
        border
        :data="tableData"
        tooltip-effect="dark"
        width="100%"
        @selection-change="handleSelectionChange">

        <el-table-column
          type="selection"
          width="55">
        </el-table-column>

        <el-table-column
            property="ordersn"
            label="订单号"
            width="120">
        </el-table-column>

        <el-table-column
            property="tb_order_no"
            label="淘宝订单号"
            width="120">
          </el-table-column>
          <el-table-column
            property="reason"
            width="120"
            label="退货原因">
          </el-table-column>
          <el-table-column
            property="returnvalue"
            label="退货货值"
            width="120">
          </el-table-column>
          <el-table-column
            property="returnfee"
            label="退货运费"
            width="100">
          </el-table-column>
          <el-table-column
            property="returnermessage"
            width="130"
            label="退货收件人信息">
          </el-table-column>
          <el-table-column
            property="shippingno"
            label="物流运输号"
            width="120">
          </el-table-column>
          <el-table-column
            property="returnfeestyle"
            label="退货运费支付方式"
            width="140">
          </el-table-column>
          <el-table-column
            property="questreturn"
            width="140"
            label="仓库确认是否退货">
          </el-table-column>
          <el-table-column
            property="questreturnfee"
            width="140"
            label="仓库确认退货运费">
          </el-table-column>

      </el-table>


      
    </div>
</template>


<script>
    export default{
        data(){
            return {               
               msg:'组件',
               tableData:[
                 {
                  ordersn:'123 ',
                  tb_order_no:'456 ',
                  reason:'无可奉告 ',
                  returnvalue:'1千万 ',
                  returnfee:'1千万 ',
                  returnermessage:'SB ',
                  shippingno:' 9394',
                  returnfeestyle:'支票',
                  questreturn:' 等一下',
                  questreturnfee:'再说吧 '
                 },
                 {
                  ordersn:'123 ',
                  tb_order_no:'456 ',
                  reason:'无可奉告 ',
                  returnvalue:'1千万 ',
                  returnfee:'1千万 ',
                  returnermessage:'SB ',
                  shippingno:' 9394',
                  returnfeestyle:'支票',
                  questreturn:' 等一下',
                  questreturnfee:'再说吧 '
                 },
                 {
                  ordersn:'123 ',
                  tb_order_no:'456 ',
                  reason:'无可奉告 ',
                  returnvalue:'1千万 ',
                  returnfee:'1千万 ',
                  returnermessage:'SB ',
                  shippingno:' 9394',
                  returnfeestyle:'支票',
                  questreturn:' 等一下',
                  questreturnfee:'再说吧 '
                 }

               ]


             
            }
        },

        methods:{
          //  handleDownload() {
          //   this.downloadLoading = true
          //   require.ensure([], () => {
          //     const { export_json_to_excel } = require('../../vendor/Export2Excel')
          //     const tHeader = ['日期', '姓名', '地址']
          //     const filterVal = ['date', 'name', 'address']
          //     const list = this.tableData
          //     const data = this.formatJson(filterVal, list)
          //     export_json_to_excel(tHeader, data, '列表excel')
          //     this.downloadLoading = false
          //   })
          // },
          
        }     
    }

</script>

<style scoped>



#allorder p{
  margin:10px;
  background-color: #aaa;
  padding: 10px;
}

.btnseach button{
  padding: 6px 30px;
  border-radius: 10px;
  margin:10px;
}


.el-table th{
    padding: 0px !important; 
}

</style>