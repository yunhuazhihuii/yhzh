<template>
    <div id="pen_content"> 

      <div class="">

        <table align="center" border="1" cellspacing="0" cellpadding="0" style="position: relative;">
          
          <thead>
            <tr class="pen_firsttitle" align="center">  <!-- 表头 th:为表头标题 -->
                <td width="110">
                  <el-checkbox label="" name="type"></el-checkbox>&nbsp;商品图片
                </td>
                <td width="210" >产品明细</td>
                <td width="140">订单日期</td>
                <td width="105">订单状态</td>
                <td width="75">订单金额</td>
                <!-- <td width="105">实际打款金额</td> -->
                <td width="105">物流商</td>
                <td width="65">快递费</td>
                <!-- <td width="65">实际运费</td> -->
                <td width="65">国家</td>
                <!-- <td width="125">面单打印时间</td> -->
                <td width="125">运单号</td>
                <!-- <td width="140">运单信息</td> -->
                <td width="140">采购运单信息</td>
                <td width="145">备注</td>
                    
            </tr> 
          
          </thead>

          <tbody>
            <template v-for="(rowdata,rowindex) in tableData">
              <tr >  <!-- 人物标题  -如果此处tr加入td则会出现表格 -->
                <td colspan="11" class="td_customer"><el-checkbox name="type"></el-checkbox>
                  <el-popover
                    placement="bottom-left"
                    title="客户备注"
                    width="50"
                    trigger="hover"
                    :content=rowdata.message_to_seller>
                    <span  class="pen_zhu" slot="reference">注</span>
                  </el-popover>
                  {{rowdata.ordersn}}  &nbsp;&nbsp;&nbsp;&nbsp; {{rowdata.buyer_username}}
                  <span style="float: right; margin-right: 20px">
                    {{rowdata.compname}} &nbsp;&nbsp;&nbsp;&nbsp; {{rowdata.shop_name}}
                  </span>
                </td>
              </tr>


              <tr>   <!-- 产品内容信息： tr这里面的每个td和上面人物标题的tr里的内容绑定是平级关系 -->

                <td align="center"><!-- 商品图片 -->
                  <template v-for="(item,index) in rowdata.items">
                    <div>
                      <img :src="item.images" height="63" width="62" alt="">
                    </div>
                    <template v-if="index+1 < rowdata.items.length">
                      <br><br>
                    </template>
                  </template>
                </td>

                <!-- 此处td为表格里说需要遍历的内容， -->

                <td align="center" class="td_productDetail"><!-- 产品明细 -->
                  <template v-for="(item,index) in rowdata.items">
                    {{item.item_name}}: <el-button class="pen_elbutton" @click="showPurDialog(rowindex,index)" size="mini">填写采购信息</el-button>
                    <br/>
                    <span>item_sku:{{item.item_sku}}</span><br/>
                    <span>原价:{{item.variation_original_price}}&nbsp;&nbsp; &nbsp;折扣价:{{item.variation_discounted_price}}</span><br>
                    <span>数量: {{item.variation_quantity_purchased}}   &nbsp;&nbsp;
                      <!-- <div class="cirle"></div>&nbsp;&nbsp;&nbsp; -->
                      <el-popover
                        placement="right"
                        width="200"
                        trigger="hover">
                        <div> <!-- 采购信息hover框 -->
                          <template v-for="(item_2,index_2) in rowdata.items">
                            <template v-if="index_2 != 0">
                              <br/>
                            </template>
                            <span>{{item_2.item_name}}</span><br/><!-- hover 商品名称 -->
                            <span>采购账号: {{item_2.tb_code}}</span><br/><!-- hover 采购账号 -->
                            <span>淘宝订单号: {{item_2.tb_order_no}}</span><br/><!-- hover 淘宝订单号 -->
                            <span>采购时间: {{item_2.purchase_time}}</span><br/><!-- hover 采购时间 -->
                            <span>采购价格: {{item_2.purchase_price}}</span><br/><!-- hover 采购价格 -->
                            <span>采购备注: {{item_2.purchase_note}}</span><br/><!-- hover 采购备注 -->
                            <span>淘宝客链接: {{item_2.tbk_url}}</span><br/><!-- hover 淘宝客链接 -->
                            <span>淘宝客比例: {{item_2.tbk_bl}}</span><br/><!-- hover 淘宝客比例 -->
                            <span>报关名: {{item_2.bgname}}</span><br/><!-- hover 报关名 -->
                          </template>
                        </div>
                        <span style="text-decoration: underline;" slot="reference">采购信息</span>
                    </el-popover>
                    </span>

                    <template v-if="index+1 < rowdata.items.length">
                      <br><br>
                    </template>
                  </template>
                </td>


                <td align="center">{{rowdata.create_time}}</td> <!-- 订单日期 -->

                <td align="center">{{rowdata.order_status}}</td> <!-- 订单状态 -->

                <td align="center">{{rowdata.total_amount}}</td> <!-- 订单金额 -->

                <td align="center">{{rowdata.shipping_carrier}}</td> <!-- 物流商 -->

                <td align="center">{{rowdata.actual_shipping_cost}}</td> <!-- 快递费 -->

                <td align="center">{{rowdata.country}}</td> <!-- 国家 -->

                <td align="center">{{rowdata.tracking_no}}</td> <!-- 运单号 -->

                <td align="center"></td> <!-- 采购运单信息 -->

                <td align="center">{{rowdata.message_to_seller}}</td> <!-- 备注 -->
                

              </tr>
            </template>
          
          </tbody>
          
        </table>

        <el-dialog
          title="填写采购数据"
          :visible.sync="dialogVisible"
          width="50%"
          :before-close="handleClose">
          <table width="98%" class="dlg_tab">
            <tr>
              <td width="15%"><span class="mustinmark">*</span>采购账号:</td>
              <td  width="35%">
                <el-select v-model="purForm.tb_code" size="mini" placeholder="请选择" class="dialog-sel">
                  <el-option
                    v-for="item in purForm.purAccountList"
                    :key="item.tb_code"
                    :label="item.tb_code"
                    :value="item.tb_code">
                  </el-option>
                </el-select>
              </td>

              <td width="15%"><span class="mustinmark">*</span>淘宝订单号:</td>
              <td width="35%">
                <el-input size="mini" class="ordernuminput" v-model="purForm.tb_order_no" placeholder="请输入内容"></el-input>
              </td>
            </tr>
            <tr>
              <td><span class="mustinmark">*</span>采购时间:</td>
              <td>
                <el-input size="mini" class="ordernuminput" v-model="purForm.purchase_time" placeholder="请输入内容"></el-input>
              </td>
              <td><span class="mustinmark">*</span>采购价格:</td>
              <td>
                <el-input size="mini" class="ordernuminput" v-model="purForm.purchase_price" placeholder="请输入内容"></el-input>
              </td>
            </tr>
            <tr>
              <td><span class="mustinmark">*</span>采购备注:</td>
              <td colspan="3">
                <el-input
                  type="textarea"
                  :rows="2"
                  v-model="purForm.purchase_note">
                </el-input>
              </td>
            </tr>
            <tr>
              <td>淘宝客链接:</td>
              <td>
                <el-input size="mini" class="ordernuminput" v-model="purForm.tbk_url"></el-input>
              </td>
               <td>淘宝客比例:</td>
              <td>
                <el-input size="mini" class="ordernuminput" v-model="purForm.tbk_bl"></el-input>
              </td>
            </tr>
            <tr>
              <td>报关名:</td>
              <td>
                <el-input size="mini" class="ordernuminput" v-model="purForm.bgname"></el-input>
                <span>(打印快递单时在明细中显示)</span>
              </td>
            </tr>
            <tr>
              <td><span class="mustinmark">*</span>物流公司:</td>
              <td>
                <el-select v-model="purForm.shipping_carrier" size="mini" placeholder="请选择" class="dialog-sel">
                  <el-option
                    v-for="item in purForm.shipCarrierList"
                    :key="item.mykey"
                    :label="item.val"
                    :value="item.mykey">
                  </el-option>
                </el-select>
              </td>

              <td><span class="mustinmark">*</span>物流单号:</td>
              <td>
                <el-input size="mini" class="ordernuminput" v-model="purForm.shippingno" placeholder="请输入内容"></el-input>
              </td>
            </tr>
            <tr>
              <td colspan="4" align="right">
                <el-button type="primary" size="mini" @click="savePurInfoBtn">保存</el-button>
                <el-button type="primary" size="mini" @click="closePurDlgBtn">关闭</el-button>
              </td>
            </tr>
          </table>
      </el-dialog>




        <!-- 分页页码 -->
        <el-pagination style="margin-top:16px;"
         background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[6, 8, 12]"
          :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="tableData.length">
        </el-pagination>

      </div>

        

    </div>
</template>



<script>

 import Axios from 'axios';

export default{

    data(){

        return { 
          // 当前默认页
          currentPage:1,
          pagesize:6,
          dialogVisible: false,

          tableData: [
              {
                "ordersn":"3434",
                "buyer_username":"哈哈",
                "compname": "某某公司",
                "shop_name":"哈哈",
                "create_time":"2019-02-02 23:31:11",
                "days_to_ship":"1.5",
                "order_status":"待发货",
                "total_amount":"100",
                "escrow_amount":"99",
                "shipping_carrier":"中通",
                "estimated_shipping_fee":"10",
                "actual_shipping_cost":"10",
                "country":"TW",
                "airway_bill":"",
                "md_print_time":"",
                "tracking_no":"1243434",
                "message_to_seller":"早点发货谢谢！",
                "items":[
                  {
                    "item_id":"3434",
                    "item_name":"某个商品1",
                    "item_sku":"34345",
                    "images":"https://www.baidu.com/img/bd_logo1.png?where=super",
                    "variation_original_price":"3434",
                    "variation_discounted_price":"3434",
                    "variation_quantity_purchased":"3434",
                    "tb_code":"3434",
                    "tb_order_no":"3434",
                    "purchase_time":"3434",
                    "purchase_price":"3434",
                    "purchase_note":"3434",
                    "tbk_url":"3434",
                    "tbk_bl":"3434",
                    "bgname":"3434",
                    "shipping_carrier":"3434",
                    "shippingno":"3434"
                  },
                  {
                    "item_id":"34341",
                    "item_name":"某个商品2",
                    "item_sku":"343451",
                    "images":"https://www.baidu.com/img/bd_logo1.png?where=super",
                    "variation_original_price":"34342",
                    "variation_discounted_price":"34342",
                    "variation_quantity_purchased":"34342",
                    "tb_code":"3434",
                    "tb_order_no":"3434",
                    "purchase_time":"3434",
                    "purchase_price":"3434",
                    "purchase_note":"3434",
                    "tbk_url":"3434",
                    "tbk_bl":"3434",
                    "bgname":"3434",
                    "shipping_carrier":"3434",
                    "shippingno":"3434"
                  }
                ]
              }
            
          ],

          purForm: { //采购信息填写框
              purAccountList: [ //采购账号列表(淘宝账号)
                {tb_code: "11"},
                {tb_code: "12"}
              ],
              shipCarrierList: [ //
                {mykey: "1",val: "顺丰"},
                {mykey: "2",val: "中通"}
              ],
              rowIndex: "",
              itemIndex: "",
              ordersn: "", //订单号
              item_id: "", //商品id
              tb_code: "", //采购账号
              tb_order_no: "", //淘宝订单号
              purchase_time: "", //采购时间
              purchase_price: "", //采购价格
              purchase_note: "", //采购备注
              tbk_url: "", //淘宝客链接
              tbk_bl: "", //淘宝客比例
              bgname: "", //报关名
              shipping_carrier: "", //物流公司
              shippingno: ""//物流单号
            
          }

        }
    } ,

    

    methods:{
      
      /*关闭采购信息填写框*/
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },

      //分页信息
      handleSizeChange: function (size) {
          this.pagesize = size;
      },
      handleCurrentChange: function(currentPage){
          this.currentPage = currentPage;
      },

      // 获取待处理订单接口
      getData(){

          var api='http://192.168.1.187:8888/api/getPendingOrders';

          Axios.post(api,{
            "orderStatus":"all"  //待处理订单参数
          }).then((response)=>{
              this.tableData=response.data;
              console.log( 'response1==>', response)  
          }).catch((error)=>{
              console.log("请求数据失败==》", error);
          })
      },

      // 获取提交采购信息接口
      getDataT(){          
          var api='http://192.168.1.187:8888/api/submitPurchase';
          Axios.post(api,{
            "ordersn":"19012109163H0E9"  //  提交采购信息参数
          }).then((response)=>{
              this.purForm=response.data;
              console.log( 'response2==>', response)  
          }).catch((error)=>{
              console.log("请求数据失败==》", error);
          })
      },

       // 获取初始化采购账号列表
      getPurinfo(){          
          var api='http://192.168.1.187:8888/api/getPurinfo';

          Axios.post(api,{
            "ordersn":"19012109163H0E9"  
          }).then((response)=>{
              this.purForm.purAccountList=response.data;
              console.log( 'response3==>', response)  
          }).catch((error)=>{
              console.log("请求数据失败==》", error);
          })
      },


      getLogistics(){          
          var api='http://192.168.1.187:8888/api/getLogistics';

          Axios.post(api,{

          }).then((response)=>{
              this.purForm.shipCarrierList=response.data;
              console.log( 'response4==>', response)  
          }).catch((error)=>{
              console.log("请求数据失败==》", error);
          })
      },

      /**弹出采购信息填写框
        * @input rowIndex 表格行数据所在索引号，从0开始
        * @input itemIndex 商品item索引号，从0开始
        */
      showPurDialog(rowIndex,itemIndex){
          var that = this;
          that.purForm.rowIndex = rowIndex;
          that.purForm.itemIndex = itemIndex;
          //将该行采购信息数据赋值给 采购信息表单
          var rowData = that.tableData[rowIndex];
          var itemInfo = rowData.items[itemIndex];
          that.purForm.ordersn = rowData.ordersn;
          that.purForm.item_id = itemInfo.item_id;
          that.purForm.tb_code = itemInfo.tb_code;
          that.purForm.tb_order_no = itemInfo.tb_order_no;
          that.purForm.purchase_time = itemInfo.purchase_time;
          that.purForm.purchase_price = itemInfo.purchase_price;
          that.purForm.purchase_note = itemInfo.purchase_note;
          that.purForm.tbk_url = itemInfo.tbk_url;
          that.purForm.tbk_bl = itemInfo.tbk_bl;
          that.purForm.bgname = itemInfo.bgname;
          that.purForm.shipping_carrier = itemInfo.shipping_carrier;
          that.purForm.shippingno = itemInfo.shippingno;
          that.dialogVisible = true;
      },

      /*关闭采购信息填写框按钮事件*/
      closePurDlgBtn(){
        var that = this;
        that.dialogVisible = false;
      },


      /*保存采购信息*/
      savePurInfoBtn(){
        var that = this;
        //提交采购信息

        submitPurchase(that.purForm.ordersn,that.purForm.item_id,that.purForm.tb_code,
              that.purForm.tb_order_no,that.purForm.purchase_time,that.purForm.purchase_price,
              that.purForm.purchase_note,that.purForm.tbk_url,that.purForm.tbk_bl,
              that.purForm.bgname,that.purForm.shipping_carrier,that.purForm.shippingno,res=>{
            console.log("提交采购信息反馈",res.data);
            //若提交成功则更新tableData中采购信息
            var rowData = that.tableData[that.purForm.rowIndex];
            var itemInfo = rowData.items[that.purForm.itemIndex];
            itemInfo.tb_code = that.purForm.tb_code;
            itemInfo.tb_order_no = that.purForm.tb_order_no;
            itemInfo.purchase_time = that.purForm.purchase_time;
            itemInfo.purchase_price = that.purForm.purchase_price;
            itemInfo.purchase_note = that.purForm.purchase_note;
            itemInfo.tbk_url = that.purForm.tbk_url;
            itemInfo.tbk_bl = that.purForm.tbk_bl;
            itemInfo.bgname = that.purForm.bgname;
            itemInfo.shipping_carrier = that.purForm.shipping_carrier;
            itemInfo.shippingno = that.purForm.shippingno;
        });
        that.dialogVisible = false;
      },




    },

    mounted(){
        this.getData();
        this.getDataT();
        this.getPurinfo();
        this.getLogistics()
    }


}

</script>



<style scoped>

#pen_content{
  margin-top:10px;
  font-size: 12px;

}

.produce{
  width: 200px;
  height: 100px;
  position: relative;
}

.produce span{
  margin: 10px 20px 10px 10px ;

}

.imginfan{
    width: 100px;
    height: 100px;
    text-align: center;
    position: relative; 
}

.imginfan img {
  text-align:center;
  position: absolute;
  top:50%; 
  left:50%;
  transform: translate(-50%,-50%);
  transition: all 0.6s; 
}

.imginfan img :hover{
  transform: scale(1.2); 
}



.tooltips{
position:relative; /*这个是关键*/
z-index:2;
}
.tooltips:hover{
z-index:3;
background:none; /*没有这个在IE中不可用*/
}
.tooltips .inf{
display: none;
}
.tooltips:hover .inf{ /*span 标签仅在 :hover 状态时显示*/
display:block;
position:absolute;
top:-150px;
left:9px;
width:15em;
border:1px solid black;
background-color:#F2F2F2;
padding: 3px;
color:black;
text-align:left;
}



.tooltip{

z-index:2;
}
.tooltip:hover{
z-index:3;

}
.tooltip .in{
display: none;
}
.tooltip:hover .in{ /*span 标签仅在 :hover 状态时显示*/
display:block;
position:absolute;
top:100px;
left:200px;
border:1px solid black;
background-color:#F2F2F2;
padding: 3px;
color:black;
text-align:left;
z-index:999;
}




.create_time{
  width:150px;
}





</style>