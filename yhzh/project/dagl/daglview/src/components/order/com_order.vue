
<template> 
  <div class="pen_content">

    <v-topsearch :tableData="tableData" :orderStatus="orderStatus" :dqwz="dqwz"></v-topsearch>
    
    <div class="pen_content-botton">

      <el-row class="pen_elrow">

            <el-button class="pen_elbutton" type="info" size="small" @click="ajaxs()">同步订单</el-button>

            <el-button class="pen_elbutton" type="info" size="small" @click="getPdf()">批量导出</el-button>


            <el-button class="pen_elbutton" type="info" size="small" @click="exportExcel()">批量打印</el-button>

            <!-- 异常标签 -->
            <el-dropdown>
              <el-button type="info" class="pen_elbutton" size="small">
                异常标签<i class="el-icon-arrow-down el-icon--right"></i>
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <!-- v-model="selected" 这个会导致一进页面就弹出下拉 -->
                <el-dropdown-item>价格出错</el-dropdown-item>
                <el-dropdown-item>客人需要收据</el-dropdown-item>
                <el-dropdown-item>缺货</el-dropdown-item>
                <el-dropdown-item>客人要求改尺寸</el-dropdown-item>
                <el-dropdown-item>客人要求改颜色</el-dropdown-item>
                <el-dropdown-item>联系不上客户</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            
            <!-- 普通标签 -->
            <el-dropdown>
              <el-button type="info" class="pen_elbutton" size="small">
                普通标签<i class="el-icon-arrow-down el-icon--right"></i>
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>正常</el-dropdown-item>
                <el-dropdown-item>寄顺丰</el-dropdown-item>
                <el-dropdown-item>超材</el-dropdown-item>
                <el-dropdown-item>已购买</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
           
      </el-row>
      
    </div>
 
    <!-- 表格信息 -->
    <div class="pen_orderlist" id="pdfDom">

        <table width="1710" align="center" cellspacing="0" cellpadding="0">

          <tr class="pen_firsttitle">
            <td width="110">
              <el-checkbox label="" name="type"></el-checkbox>&nbsp;商品图片
            </td>
            <td width="210">产品明细</td>
            <td width="140">订单日期</td>
            <td width="105">订单状态</td>
            <td width="75">订单金额</td>
            <td width="105">物流商</td>
            <td width="65">快递费</td>
            <td width="65">国家</td>
            <td width="125">运单号</td>
            <td width="140">采购运单信息</td>
            <td width="145">备注</td>
          </tr>

          <template v-for="(rowdata,rowindex) in tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)">

            <tr class="pen_order-name">
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

            <tr class="pen_order-content">
              <td><!-- 商品图片 -->
                <template v-for="(item,index) in rowdata.items">
                  <div>
                    <img :src="item.images" height="63" width="62" alt="">
                  </div>
                  <template v-if="index+1 < rowdata.items.length">
                    <br><br>
                  </template>
                </template>
              </td>
              <td class="td_productDetail"><!-- 产品明细 -->
                <template v-for="(item,index) in rowdata.items">
                  {{item.item_name}}: 
                  <!-- 待处理的订单才可以填写采购信息 -->
                  <template v-if="orderStatus === 'READY_TO_SHIP'">
                    <el-button class="pen_elbutton" @click="showPurDialog(rowindex,index)" size="mini">填写采购信息</el-button>
                  </template>
                  <br/>
                  <span>item_sku:{{item.item_sku}}</span><br/>
                  <span>原价:{{item.variation_original_price}}&nbsp;&nbsp;折扣价:{{item.variation_discounted_price}}</span><br>
                  <span>数量:
                    <div class="cirle">{{item.variation_quantity_purchased}}</div>&nbsp;&nbsp;&nbsp;
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
              <td>{{rowdata.create_time}}</td> <!-- 订单日期 -->
              <td>{{rowdata.order_status}}</td> <!-- 订单状态 -->
              <td>{{rowdata.total_amount}}</td> <!-- 订单金额 -->
              <td>{{rowdata.shipping_carrier}}</td> <!-- 物流商 -->
              <td>{{rowdata.actual_shipping_cost}}</td> <!-- 快递费 -->
              <td>{{rowdata.country}}</td> <!-- 国家 -->
              <td>{{rowdata.tracking_no}}</td> <!-- 运单号 -->
              <td></td> <!-- 采购运单信息 -->
              <td>{{rowdata.message_to_seller}}</td> <!-- 备注 -->
            </tr>
          </template>

        </table>

        <!-- 这部分是分页 -->   
        <div class="yema">
          <el-pagination background
            @size-change="handleSizeChange" 
            @current-change="handleCurrentChange"  
            :current-page="currentPage"  
            :page-sizes="[5,10,15]"
            :page-size="pagesize"   
            layout="total,jumper,prev, pager, next,sizes"
            :total="tableData.length" >
          </el-pagination>
        </div>
        <!-- 这部分是分页 End-->

   
    </div>


    <!-- 填写采购数据 -->
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
                <el-date-picker
                  v-model="purForm.purchase_time"
                  type="datetime"
                  placeholder="选择日期时间">
                </el-date-picker>
              
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
  </div>
</template>

<script>
import Axios from 'axios';
import topsearch from '../topsearch.vue';
import { submitPurchase,getDictInfo,getPurinfo } from '../../service/getData.js';

import { getPdf } from './../utils/htmlToPdf.js';

//打印插件
import FileSaver from 'file-saver'
import XLSX from 'xlsx'

    export default{

    	props: ['dqwz',   //当前位置
            'orderStatus' //订单状态
        ],

	    components:{
	        'v-topsearch':topsearch    
	    },

	    data(){

	    	return{
	    		dialogVisible: false,

          htmlTitle: 'PDF01',

          currentPage:1,//
          pagesize:5,//每页显示条数

	    		tableData: [
	             
	            
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
	    
	    },


      created(){
        var that =this;
        //初始化采购账号列表
        getPurinfo(res=>{
            console.log("采购账号列表",res);
            that.purForm.purAccountList = res;
        });
        //初始化物流公司列表
        getDictInfo("shipping_carrier",res=>{

            console.log("物流公司列表",res);
            that.purForm.shipCarrierList = res;
        });
      },


	    methods: {

        ajaxs(){
          console.log("456")
        },

        getPdf() {
          let _this = this
          let pdfDom = document.querySelector('#pdfDom')
          html2Canvas(pdfDom, {
           onrendered: function(canvas) {
            let contentWidth = canvas.width
            let contentHeight = canvas.height
            let pageHeight = contentWidth / 592.28 * 841.89
            let leftHeight = contentHeight
            let position = 0
            let imgWidth = 595.28
            let imgHeight = 592.28 / contentWidth * contentHeight
       
            let pageData = canvas.toDataURL('image/jpeg', 1.0)
       
            let PDF = new JsPDF('', 'pt', 'a4')
       
            if (leftHeight < pageHeight) {
             PDF.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight)
            } else {
             while (leftHeight > 0) {
              PDF.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
              leftHeight -= pageHeight
              position -= 841.89
              if (leftHeight > 0) {
               PDF.addPage()
              }
             }
            }
            PDF.save(_this.pdfData.title + '.pdf')
           }
          })
          html2Canvas()
        },

        // 打印功能
        exportExcel(){
             
             var wb = XLSX.utils.table_to_book(document.querySelector('#pdfDom'))
             
             var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })

             try {
                 FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), 'sheetjs.xlsx')
             } catch (e) { 
              if (typeof console !== 'undefined') 
                console.log(e, wbout) 
            }
             return wbout
        },


	    	// 获取待处理订单接口
		    getData(){

            var that = this;
            var status = that.orderStatus

            // 116.62.112.118     192.168.1.187
		        var api='http://116.62.112.118:8888/api/getPendingOrders';

		            Axios.post(api,{
		               "orderStatus": status //待处理订单参数
		            }).then((response)=>{
		              this.tableData=response.data;
		              console.log( 'response1==>', response)  
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

	        /*关闭采购信息填写框*/
	        handleClose(done) {
	          this.$confirm('确认关闭？')
	            .then(_ => {
	              done();
	            })
	            .catch(_ => {});
	        },

	        /*保存采购信息*/
	        savePurInfoBtn(){
	            var that = this;
	            //提交采购信息
	            submitPurchase(that.purForm.ordersn,that.purForm.item_id,that.purForm.tb_code,
	                that.purForm.tb_order_no,that.purForm.purchase_time,that.purForm.purchase_price,
	                that.purForm.purchase_note,that.purForm.tbk_url,that.purForm.tbk_bl,
	                that.purForm.bgname,that.purForm.shipping_carrier,that.purForm.shippingno,res=>{
	              console.log("提交采购信息反馈",res);
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

	        /*关闭采购信息填写框按钮事件*/
	        closePurDlgBtn(){
	          var that = this;
	          that.dialogVisible = false;
	        },

          ///分页  初始页currentPage、初始每页数据数pagesize和数据testpage
          handleSizeChange:function(size){
              this.pagesize=size;
          },
          handleCurrentChange:function(currentPage){
              this.currentPage=currentPage;
          }
          ///分页End  初始页currentPage、初始每页数据数pagesize和数据testpage

	    },

	    mounted(){

        this.getData()
       
    }


    }



</script>


<style>
  .pen_content {
    position:absolute;
    width: 100%;
    height: 1000px;
  }
  .pen_content-top {
    width: 1255px;
    height: 250px;
    border: 1px solid #CCCCCC;
    position: absolute;
    top: 0;
    left: 0;
  }

  .pen_content-top-title {
    height: 40px;
    background-color: #F2F2F2;
    border-bottom: 1px solid #CCCCCC;
  }
  .pen_content-top-title p {
    padding-top: 8px;
    font-size: 15px;
  }
  .pen_content-top-ele {
    height: 120px;
    border-bottom: 1px solid #CCCCCC;
  }
  
  .pen_oneline {
    padding-top: 15px;
    padding-left: 20px;
  }
  .pen_twoline {
    padding-top: 15px;
    padding-left: 20px;
  }
  .pen_content-top-bottom-one {
    padding-top: 15px;
    padding-left: 20px;
  }
  .pen_content-top-bottom-two {
    padding-top: 12px;
    padding-left: 20px;
  }
  .pen_elinput {
    width: 570px;
  }
  .pen_serbutton {
    background-color: #76A3A3;
  }
  .pen_content-botton {
    position: absolute;
    left: 0;
    top: 245px;
    /* border: 1px solid #CCCCCC; */
    height: 65px;
    width: 1200px;
  }
  .pen_elrow {
    margin-top: 16px;
  }
  .pen_elbutton {
    background-color: #76A3A3;
    color: #fff;
    font-weight: bold;
  }
  
  .pen_orderlist {
    width: 1710px;
    /* height: 500px; */
    /* background-color: #99D9EA; */
    position: absolute;
    top: 310px;
    left: 0;
  }
  .pen_orderlist table {
    border-collapse: collapse;
  }
  .pen_orderlist table .pen_firsttitle {
    height: 35px;
    background-color: #F2F2F2;
    text-align: center;
  }
  .pen_orderlist table .pen_order-name {
    height: 34px;
    background-color: #F2F2F2;
    text-align: left;
  }
  .pen_orderlist table .pen_order-content {
    height: 160px;
    background-color: #ffffff;
    text-align: center;
  }
  .pen_orderlist table td {
    border: 1px solid #CCCCCC;
  }
  .cirle {
    width: 18px;
    height: 18px;
    display: inline-block;
    background-color: #76A3A3;
    border-radius: 50%;      
    -moz-border-radius: 50%;      
    -webkit-border-radius: 50%;
  }
  .pen_order-content td {
    font-size: 13px;
    font-family: 'Arial Normal', 'Arial';
    color: #33336C
  }
  /*产品明细td*/
  .td_productDetail{
    padding-top: 5px;
    padding-bottom: 5px;
  }
  /*客户信息单独那行的td*/
  .td_customer{
    padding-left: 32px;
  }
  .pen_zhu {
    height: 50px;
    width: 50px;
    background-color: red;
  }
  i {
    color: red;
  }
  /*采购信息hover框，覆盖原始样式*/
  .el-popper{
    background-color: #F2F2F2;
  }
  /*填写采购信息-表格*/
  .dlg_tab{
    border-collapse:separate;
    border-spacing:5px 10px;/*行间距10，列间距5*/
  }
  /*填写采购信息框必填标志*/
  .mustinmark{
    color: red;
  }
  
  /*采购信息填写框-下拉选择框，貌似设置宽度无效*/
  .dialog-sel{

  }
</style>
   

