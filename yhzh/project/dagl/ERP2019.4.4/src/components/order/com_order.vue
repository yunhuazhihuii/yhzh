
<template> 
  <div class="pen_content">

    <!-- <v-topsearch :tableData="tableData" :orderStatus="orderStatus" :dqwz="dqwz"></v-topsearch> -->

    <!-- 头部筛选及查询功能 -->
    <div class="searchDiv">

      <div class="searchDiv-title">
            <p>当前位置：{{dqwz}}</p>
      </div>

      <div class="searchDiv-ele">
        <div class="oneline">
          <span>异常标签</span>&nbsp;
          <el-select v-model="value1" size="small" placeholder="请选择">
            <el-option
              v-for="item1 in options1"
              :key="item1.labelid"
              :label="item1.labelname"
              :value="item1.labelid">
            </el-option>
          </el-select>
          &nbsp;&nbsp;&nbsp;

          <span>普通标签</span>&nbsp;

          <el-select v-model="value2" size="small" placeholder="请选择">
            <el-option
              v-for="item2 in options2"
              :key="item2.labelid"
              :label="item2.labelname"
              :value="item2.labelid">
            </el-option>
          </el-select>
          &nbsp;&nbsp;&nbsp;

          <span>店铺账号</span>&nbsp;

          <el-select v-model="value3" size="small" placeholder="请选择">
              <el-option
                v-for="item3 in options3"
                :key="item3.shop_id"
                :label="item3.shop_name"
                :value="item3.shop_id">
              </el-option>
          </el-select>
        </div>

        <div class="oneline">

          <span>订单状态</span>&nbsp;

          <el-select v-model="value4" size="small" placeholder="请选择">
              <el-option
                v-for="item4 in options4"
                :key="item4.value"
                :label="item4.label"
                :value="item4.value">
              </el-option>
          </el-select>
          &nbsp;&nbsp;&nbsp;

          <span>订单时间 从</span>&nbsp;

          <el-date-picker
            size="small" 
            v-model="form.createTime1"
            type="datetime"
            placeholder="选择日期时间"
            format="yyyy年MM月dd日 HH小时mm分ss秒"
            value-format="yyyy-MM-dd HH:mm:ss"
            >
          </el-date-picker>

          <span>到</span>&nbsp;
          <el-date-picker
            size="small"
            v-model="form.createTime2"
            type="datetime"
            placeholder="选择日期时间"
            format="yyyy年MM月dd日 HH小时mm分ss秒"
            value-format="yyyy-MM-dd HH:mm:ss"
            >
          </el-date-picker>

          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search" 
              @click="qryBtn()" >筛选</el-button>
        </div>
      </div>

      <div>
        <div class="searchDiv-exact-type">
          <span>搜索类型:</span> &nbsp;&nbsp;&nbsp;
          <span>订单号</span>  &nbsp; 
          <span>运单号</span> &nbsp; 
          <span>收件人</span>  &nbsp; 
          <span>产品SKU</span> &nbsp; 
          <span>采购账号</span>&nbsp;  
          <span>采购运单号</span>
        </div>
        <div class="searchDiv-exact-val">
          <span>搜索内容:</span>&nbsp;&nbsp;&nbsp;
          <el-input class="exactinput" size="small" v-model="form.searchCode" placeholder="请输入内容"></el-input>&nbsp;&nbsp;&nbsp;
          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search"
              @click="searchCodeBtn()">搜索</el-button>
        </div>
      </div>
        
    </div>
    
    <!-- 表格上的功能按钮 -->
    <div class="pen_content-botton">

      <el-row class="pen_elrow">

            <el-button class="pen_elbutton" :loading="syncorders" type="info" size="small" @click="syncorder()">同步订单</el-button>

            <!-- 批量导出 -->
            <el-button class="pen_elbutton" type="info" size="small" @click="exportExcel()">批量导出</el-button>

            <!-- 批量打印功能：v-print -->
            <el-button class="pen_elbutton" type="info" size="small" v-print="'#pdfDom'">批量打印</el-button>

            <!-- 下拉菜单：异常标签 -->
            <el-dropdown @command="handleCommand">

              <el-button type="info" class="pen_elbutton" size="small">
                异常标签<i class="el-icon-arrow-down el-icon--right"></i>
              </el-button>

              <el-dropdown-menu slot="dropdown">
                <!-- v-model="selected" 这个会导致一进页面就弹出下拉 -->
                <template v-for="(item,index) in options1">
                  <el-dropdown-item :command="item.labelid" >{{item.labelname}}
                  </el-dropdown-item>
                </template>
              </el-dropdown-menu>
            </el-dropdown>
            
            <!--下拉菜单： 普通标签 -->
            <el-dropdown @command="handleCommand">

              <el-button type="info" class="pen_elbutton" size="small">
                普通标签<i class="el-icon-arrow-down el-icon--right"></i>
              </el-button>

              <el-dropdown-menu slot="dropdown">
                 <template v-for="(item,index) in options2">
                    <el-dropdown-item :command="item.labelid" >{{item.labelname}}</el-dropdown-item>
                </template>
                
              </el-dropdown-menu>
            </el-dropdown>

            <template v-if="orderStatus === 'READY_TO_SHIP'">  

              <el-button class="pen_elbutton" type="info" size="small" @click="zhuanyigetREADY()" >移至待收货 </el-button>

            </template>

            <template v-if="orderStatus === 'TO_BE_RECEIVED'">  

              <el-button class="pen_elbutton" type="info" size="small" @click="zhuanyigetRECEIVED()" >移至待处理 </el-button>

            </template>

            <template v-if="orderStatus === 'IN_CANCEL'">  

              <el-button class="pen_elbutton" type="info" size="small" @click="zhuanyigetRECEIVED()" >移至已取消 </el-button>

            </template>

            <template v-if="orderStatus === 'CANCELLED'">  

              <el-button class="pen_elbutton" type="info" size="small" @click="zhuanyigetRECEIVED()" >移至申请取消 </el-button>

            </template>


            <template v-if="orderStatus === 'SHIPPED'">  

              <el-button class="pen_elbutton" type="info" size="small" @click="zhuanyigetRECEIVED()" >移至已完成 </el-button>

            </template>

            <template v-if="orderStatus === 'SHIPPED'">  

              <el-button class="pen_elbutton" type="info" size="small" @click="zhuanyigetRECEIVED()" >移至运输中 </el-button>

            </template>


            <template v-if="orderStatus === 'TO_RETURN'">  
              <el-button class="pen_elbutton" type="info" size="small" @click="zhuanyigetRECEIVED()">移至 耗损订单 </el-button>
            </template>
            
          
            
      </el-row>
      
    </div>
 
    <!-- 表格信息 -->
    <div class="pen_orderlist" >

        <table @selection-change="handleSelectionChange" id="pdfDom" width="1410" align="center" cellspacing="0" cellpadding="0" border>

          <tr class="pen_firsttitle">
            <td width="110">
              <el-checkbox label="" name="type"></el-checkbox>&nbsp;商品图片
            </td>
            <td width="210" align="center">产品明细</td>
            <td width="140" align="center">订单日期</td>
            <td width="105" align="center">订单状态</td>
            <td width="75" align="center">订单金额</td>
            <td width="105" align="center">物流商</td>
            <td width="65" align="center">快递费</td>
            <td width="65" align="center">国家</td>
            <td width="125" align="center">运单号</td>
            <td width="140" align="center">采购运单信息</td>
            <td width="145" align="center">备注</td>
          </tr>

          <!-- .slice((currentPage-1)*PageSize,currentPage*PageSize) -->
          <template v-for="(rowdata,rowindex) in tableData">

            <tr class="pen_order-name">

              <td colspan="11" class="td_customer">

                <el-checkbox v-model="rowdata.checked" @change="currentSel" name="type"></el-checkbox>

                <!-- 弹出框   :content：显示的内容，也可以通过 slot 传入 DOM-->
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
                   &nbsp;&nbsp;&nbsp;&nbsp; {{rowdata.account}}
                </span>
              </td>
            </tr>

            <tr class="pen_order-content" align="center">
              <td><!-- 商品图片 -->
                <template v-for="(item,index) in rowdata.items">
                  <!-- <div>
                    <img :src="item.images" height="63" width="62" alt="">
                  </div> -->
                   <div  class="imginfan">

                    <a class="tooltip" @click=tourl(rowindex,index) href="javascript:;">
                      <img :src="item.images" height="63" width="62"  alt=""><br><br><br><br><br><br>

                            <!-- 放大图 -->
                          <div class="inner">
                              <img class="" :src="item.images" height="360" width="360" alt="">
                          </div>
                    </a>
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
                  <span>选项名称:{{item.variation_name}}</span><br/>
                  <span>选项sku:{{item.variation_sku}}</span><br/>
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
              <td>
                {{rowdata.create_time}}<br><br>
                <span class="syfhtime">发货剩余时间:</span><br>
                <span class="syfhtime">{{rowdata.days_to_ship}}</span>
              </td> <!-- 订单日期 -->
              <td>
                {{rowdata.order_status}}<br><br>
                <span class="syfhtime">{{rowdata.order_statuscn}}</span><br>

                <!-- 打标签 -->
                <!-- <el-tag size="" class="lableclass" closable>价格出错</el-tag><br><br> -->
             <!--    <template v-for="(item5,index5) in rowdata.items">
                  <el-tag size="" class="lableclass" closable>{{item5.labelname}}</el-tag>
               </template> -->

               <template v-for="(item5,index5) in rowdata.label">
                  <el-tag
                  class="lableclass" 
                  @close="handleClose1(rowindex,index5)"
                  size="" closable>{{item5.labelname}}</el-tag><br><br>
               </template>

                <!-- <template v-for="(item5,index5) in rowdata.label"> -->
<!-- 
                  <el-tag 
                  v-for="tag in labellist"
                  @close="handleClose1(tag)"
                  size=""
                  closable>
                  {{tag.labelname}}
                </el-tag>
               -->
                
              </td> <!-- 订单状态 -->
              <td>{{rowdata.total_amount}}</td> <!-- 订单金额 -->
              <td>{{rowdata.shipping_carrier}}</td> <!-- 物流商 -->
              <td>{{rowdata.actual_shipping_cost}}</td> <!-- 快递费 -->
              <td>{{rowdata.country}}</td> <!-- 国家 -->
              <td>{{rowdata.tracking_no}}</td> <!-- 运单号 -->
              <td></td> <!-- 采购运单信息 -->

              <!-- <td>{{rowdata.items[0].purchase_note}}</td>   -->

              <td>
                <template v-for="(item_3,index_3) in rowdata.items">
                  <template v-if="index_3 != 0">
                    <br/>
                  </template>
                  <span>{{item_3.purchase_note}}</span><br/>
                </template>

              </td> 
            </tr>
          </template>

        </table>

        <!-- 这部分是分页 -->   
        <div class="yema">
          <el-pagination 
            background
            @size-change="handleSizeChange" 
            @current-change="handleCurrentChange"  
            :page-sizes="[30,50,100,300]"
             
            layout="total,jumper,prev, pager, next,sizes"
            :total="totalpage" >
          </el-pagination>
        </div>
        <!-- 这部分是分页 End-->

        <!-- page-size：每页显示条目个数，支持 .sync 修饰符 
            total 总条目数
            page-count  总页数，total 和 page-count 设置任意一个就可以达到显示页码的功能；如果要支持 page-sizes 的更改，则需要使用 total 属性
            page-sizes  每页显示个数选择器的选项设置
        -->
   
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
                  format="yyyy年MM月dd日 HH小时mm分ss秒"
                  value-format="yyyy-MM-dd HH:mm:ss"
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
import { submitPurchase,getDictInfo,getPurinfo,getOrderList } from '../../service/getData.js';
import url from '../../../config/sysAPI.config.js'
import util from '../../common/js/util';
import {getSession} from '../../common/js/util';

//打印插件
import FileSaver from 'file-saver'
import XLSX from 'xlsx'

    export default{

      props: [
          'dqwz',   //当前位置
          'orderStatus' //订单状态
      ],

      data(){

        return{
          syncorders: false,
          dialogVisible: false,

          // tags:[],
          multipleSelection:[],
          totalpage: 0,
          
          // currentPage:1,   //  默认显示第一条
          // pageCount:10,//每页显示条数

          checked:false,
          tableData: [ ],
          labellist:[],
          userid: "", //用户id

          tableDataDC:[ ],

          form:{
            // orderStatus: "READY_TO_SHIP", // 订单状态
            createTime1: "", //订单时间 从
            createTime2: "", //订单时间 到
            searchType: "1", //精确搜索类型 1订单号,2运单号,3收件人,4产品SKU,5采购账号,6采购运单号
            searchCode: "", //精确搜索代码
            pageIndex: "1", //页码
            pageCount: "30" //每页显示条数
          },

          command:'',
          ordersn: [],
          ordersns: '',

          tableData1:[],
          data1: '',
          data2: '',
          searchVal:'', //默认输入为空
          value1: '',
          value2: '',
          value3: '',
          value4: '',
          selected: '',

          listtitle:[
            {searchNO:"订单号" },
            {searchNO:"运单号" },
            {searchNO:"收件人" },
            {searchNO:"产品SKU" },
            {searchNO:"采购账号" },
            {searchNO:"采购运单号" },
          ],
          
          options1: [ ],
          
          options2:[ ],

          options3:[ ],

          options4:[
            {
              value: '1',
              label: '待发货'
            }, 
            {
              value: '2',
              label: '已发货'
            }, 
            {
              value: '3',
              label: '已取消'
            }, 
            {
              value: '4',
              label: '已完成'
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
            userid:"",
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

        //从登陆session中获取userid
        that.userid = getSession("user_id");

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

        //图片链接地址
        tourl(rowindex,index){
          var that = this
          var url = that.tableData[rowindex].items[index].purchase_link
          window.open (url,'_blank')

        },
        
        

        handleSelectionChange(val) {
            this.multipleSelection = val
        },

        // 同步订单的方法
        syncorder(){
          var _this = this
          this.syncorders = true
          var api=url.SynchronizeOrder;
          var userid = this.userid
            Axios.post(api,{
              userid:userid
            }).then((response)=>{
              console.log(response.data)
              if(response.data.recode==0){
                _this.syncorders = false
                _this.$message.success('同步订单成功');
              }
            }).catch((error)=>{
              console.log(error);
            })
        },

        //导出功能请求数据
        getexportExcel(){
            
              var api=url.ExportOrder;
              var userid = this.userid;
              var orderStatus = this.orderStatus;
              var shop_id = this.shop_id;
              var createTime1 = this.createTime1;
              var createTime2 = this.createTime2;
              var compid = this.compid;
              console.log(userid+"----888");
              
              Axios.post(api,{
                userid:userid,
                orderStatus:orderStatus,
                shop_name:shop_id,
                createTime1:createTime1,
                createTime2:createTime2,
                compid:compid
              }).then((response)=>{
                console.log("导出功能请求数据----", response) ;
                 this.tableDataDC = response.data
                // console.log(this.options3)
              }).catch((error)=>{
                console.log(error);
              })
        },

        //导出
        exportExcel() {

          console.log("123456666")

　　　　　　require.ensure([], () => {
　　　　　　　　const { export_json_to_excel } = require('../../excel/Export2Excel');

              console.log("filterVal",filterVal);

              // Excel表头设定
　　　　　　　　const tHeader = ['订单号','订单日期', '订单状态', '订单金额', '商品明细', 

                              '淘宝账号','淘宝订单号', '采购时间', '采购价格', '采购备注', 

                              '淘宝客链接','淘宝客比例','店铺', '物流商','快递单号', 

                              '运单号', '快递商','快递费', '实际运费', '备注'];   

　　　　　　　　const filterVal = ['ordersn', 'create_time', 'order_status', 'total_amount', 'item', 

                                'tb_code','tb_order_no', 'purchase_time', 'purchase_price', 'purchase_note',

                                'tbk_url','tbk_bl','shop_name', 'shipping_carrier', 'shippingno',

                                'tracking_no','sh_carrier','estimated_shipping_fee','actual_shipping_cost', 'message_to_seller']; 

　　　　　　　　const list = this.tableDataDC;
                // for(var i=0;i<list.length;i++){
                //   console.log(list[i].airway_bill)
                // }
               console.log("list=",list);
　　　　　　　　const data = this.formatJson(filterVal, list);
　　　　　　　　export_json_to_excel(tHeader, data, '批量列表excel');
　　　　　　})
　　　　 },

　　　　 formatJson(filterVal, jsonData) {

　　　　　 return jsonData.map(v => filterVal.map(j => v[j]));

          console.log("filterVal11=",filterVal);
　　　　 },

            //移至待收货
          zhuanyigetREADY(){

            console.log("123654---");
            
            var ordersnT = '';

            for(var i=0;i<this.tableData.length;i++){

              if(this.tableData[i].checked == true){

                ordersnT += this.tableData[i].ordersn +',';

                console.log(this.tableData[i].ordersn +'++++++++++++++55555');

                console.log(ordersnT +'++++++++++++++ordersnT');

                for(var j=0;j<this.tableData[i].items.length;j++){

                    // 判断不能为null :util.isNull()
                  if(util.isNull(this.tableData[i].items[j].tb_code)){

                    this.$message.warning("订单编号为"+this.tableData[i].ordersn +" 的采购信息未填写完全，不能转移到待收货！");
                   
                    return;

                  }
                }
                
              }

            }

            if (ordersnT =='') {
                this.$message.warning("请先选择订单 ");
                return ;
              }else{
                ordersnT = ordersnT.substring(0,ordersnT.length-1);
                console.log("截取后的订单编号",ordersnT);
            }

            var api=url.sentReady;
            var userid = this.userid;
            Axios.post(api,{
              userid:userid,
              ordersn:ordersnT,
              direction:"1"
            }).then((response)=>{

              console.log("转移到待收货成功", response) ; 

              this.qryBtn(); //转移后重新调用查询

            }).catch((error)=>{
              console.log(error);
            })

        },

        //移至待处理
        zhuanyigetRECEIVED(){
            console.log("asdfe---");

            var ordersnT = '';

             //遍历表格里的数据
            for(var i=0;i<this.tableData.length;i++){

              //如果被选中的项
              if(this.tableData[i].checked == true){

                ordersnT += this.tableData[i].ordersn +',';

                console.log(ordersnT +'++++++++++++++ordersnT');

              }

            }

            if (ordersnT =='') {
                this.$message.warning("请先选择订单 ");
                return ;
              }else{
                // substring：截取字符串
                ordersnT = ordersnT.substring(0,ordersnT.length-1);
                console.log("截取后的订单编号",ordersnT);
            }

            var api=url.sentReady;
          
            Axios.post(api,{
              userid: this.userid,
              ordersn:ordersnT,
              direction:"0"
            }).then((response)=>{
              console.log("转移到待处理成功", response) ; 
              this.qryBtn();
            }).catch((error)=>{
              console.log(error);
            })
        },
       
        //获取店铺列表的方法
        getUserShop(){

          // http://192.168.0.122:8888/api/
          var api=url.getUserShop;
          // var api='http://116.62.112.118:8888/api/getUserShop';

          var userid = this.userid
          // console.log(userid+"888")
            Axios.post(api,{
              userid:userid
            }).then((response)=>{
              console.log("获取店铺列表----", response.data)  

              this.options3 = response.data

              console.log("获取店铺列表options3----", this.options3)
            }).catch((error)=>{
              console.log(error);
            })
        },

        /*查询订单信息*/
         qryOrderList(userid,shopId,createTime1,createTime2,orderStatus,labelid2,labelid1,searchType,searchCode,pageIndex,pageCount){
            var that = this;
            
            getOrderList(userid,shopId,createTime1,createTime2,orderStatus,labelid2,labelid1,searchType,searchCode,pageIndex,pageCount,res=>{
              
              console.log("获取订单列表----",res);

              that.tableData = res[0].table;
              that.totalpage = res[0].totalcount;

              console.log("that.tableData= ",that.tableData);
              console.log("that.totalpage= ",that.totalpage);
              // for(var i=0;i<res[0].table.length;i++){
              //   console.log(res[0].table[i])
              //   that.labellist=res[0].table[i].label
              //   console.log(that.labellist)
              // }
              // that.labellist = res[0].table.label

              // console.log(res[0].table.)

              // that.total = res.data.total; 

          });
        },

        /*多个查询条件按钮*/
        qryBtn(){
          var that = this;
          let userid = that.userid;
          let shopId = that.value3;//店铺id
          let createTime1 = that.form.createTime1;//订单时间 从
          let createTime2 = that.form.createTime2;//订单时间 到
          let orderStatus = that.orderStatus;//订单状态 待处理待收货这种
          let labelid2 = that.value1;  //异常标签id
          let labelid1 = that.value2;   //普通标签id
          let searchType = "";//精确搜索类型
          let searchCode = "";//精确搜索代码
          let pageIndex = that.form.pageIndex;//页码
          let pageCount = that.form.pageCount; //每页显示条数

          console.log("userid ="+userid+"shopId ="+shopId+",createTime1="+createTime1+",createTime2="+createTime2);
          console.log("orderStatus=" +orderStatus+",labelid2="+labelid2 +",labelid1="+labelid1+",pageIndex="+pageIndex+",pageCount="+pageCount+",searchType="+searchType+",searchCode="+searchCode );

          /*that.$options.methods.getData(shopId,createTime1,createTime2,orderStatus,searchType,searchCode,pageIndex,pageCount).bind(this)();*/
          that.$options.methods.qryOrderList.bind(this)(userid,shopId,createTime1,createTime2,orderStatus,labelid2,labelid1,searchType,searchCode,pageIndex,pageCount);
        },

        /*按搜索内容查询按钮*/
        
        searchCodeBtn(){
          var that = this;
          let shopId = that.value3;//店铺id
          let createTime1 = "";//订单时间 从
          let createTime2 = "";//订单时间 到
          let orderStatus = that.orderStatus;//订单状态
          let labelid2 = that.value1;  //异常标签id
          let labelid1 = that.value2;   //普通标签id
          let searchType = that.form.searchType;//精确搜索类型
          let searchCode = that.form.searchCode;//精确搜索代码
          let pageIndex = that.form.pageIndex;//页码
          let pageCount = that.form.pageCount; //每页显示条数
         
          that.$options.methods.qryOrderList.bind(this)(shopId,createTime1,createTime2,orderStatus,labelid2,labelid1,searchType,searchCode,pageIndex,pageCount);
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


          //分页  初始页currentPage、初始每页数据数pagesize和数据testpage

          // 每页条数 console.log(`每页 ${val} 条`);
          handleSizeChange:function(pageCount){
            var that = this
            that.form.pageCount = pageCount;
            that.qryBtn();
            console.log("pageCount==>",pageCount )
          },

          // 当前 页码数 console.log(`当前页: ${val}`);
          handleCurrentChange:function(pageIndex){
              var that = this
              that.form.pageIndex=pageIndex
              that.qryBtn();
              console.log("pageIndex==>", pageIndex);
          },


          //获取异常标签列表的方法
          getErrLabelList(){
            // http://192.168.0.122:8888/api/
            var api=url.getLabelList;
            // var api='http://116.62.112.118:8888/api/getUserShop';
            var userid = this.userid
            // console.log(userid+"888")
              Axios.post(api,{
                labeltype:"异常标签"
              }).then((response)=>{
                console.log(response)  

                this.options1 = response.data

                console.log("this.options1 ===", this.options1)
              }).catch((error)=>{
                console.log(error);
              })
          },

          //获取普通标签列表的方法
          getLabelList(){
            // http://192.168.0.122:8888/api/
            var api=url.getLabelList;
            // var api='http://116.62.112.118:8888/api/getUserShop';
            var userid = this.userid;

            // console.log(userid+"888")
              Axios.post(api,{
                labeltype:"普通标签"
              }).then((response)=>{
                console.log(response)  

                this.options2 = response.data
                // console.log(this.options3)
              }).catch((error)=>{
                console.log(error);
              })
          },

          //标签及多选框
          currentSel(){
            // this.tabledata.forEach(item,index)=>{
    
            //   console.log(item)
            // }
            // var ordersns = [];
            
            for(var i=0;i<this.tableData.length;i++){
              // console.log(this.tableData[i])
              // console.log(checked)
              // console.log(this.tableData[i].checked)
              if(this.tableData[i].checked == true){

                this.ordersn.push(this.tableData[i].ordersn)

                // this.ordersn.push(this.tableData[i].ordersn)
                console.log(this.ordersn+'++++++++++++++55555')
              }
            }
            var aList = this.ordersn

            // console.log(aList)
            var aList2 = [];

            for(var i=0;i<aList.length;i++)

            {
                if(aList.indexOf(aList[i])==i)
                {
                    aList2.push(aList[i]);
                }
            }

            console.log(aList2)

            this.ordersns = aList2.join(',');

            console.log(this.ordersns)

          },

          //打标签
          handleCommand(command){
            console.log("1111---", command)
            // this.command = command
            //这里获取到的点击选择打标签的id
            // console.log(this.command+'---99999999999')

              var api=url.LabelRef;
              // var userid = this.userid
              // console.log(userid+"888")
                Axios.post(api,{
                  labelid:command,
                  ordersn:this.ordersns
                }).then((response)=>{
                  console.log(response)
                  this.qryBtn()
                }).catch((error)=>{
                  console.log(error);
                })

          },

          //关闭打标签
          handleClose1(rowindex,index5){
            var that = this
            // 获取对应订单的下标值和对应标签的下标值
            console.log("rowindex==="+rowindex+"index5==="+index5)
            // 获取到标签所在的订单的ordersn
            var ordersn = that.tableData[rowindex].ordersn;
            // 获取到标签labelid
            var labelid = that.tableData[rowindex].label[index5].labelid;

            var api=url.delLabelRef;
              // var userid = this.userid
              // console.log(userid+"888")
                Axios.post(api,{
                  labelid:labelid,
                  ordersn:ordersn
                }).then((response)=>{
                  console.log(response)
                  this.qryBtn()
                }).catch((error)=>{
                  console.log(error);
                })
          },

          //移除按钮
          deleteRow(index, rows) {
            rows.splice(index, 1);
          }
          
      },

      mounted(){

        this.qryBtn()

        // var that = this;
        //从登陆session中获取店铺id
        this.userid = getSession("user_id");
        // console.log(this.userid+'444')
        // this.options3 = getSession('shop_id')

        this.getUserShop();
        // this.getShoplist();
        this.getErrLabelList();
        this.getLabelList();
        // console.log(this.options3+"-----shopid数组------")

        // this.getData()
        this.getexportExcel();
       
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
    background-color: #76A3A3 !important;
    color: #fff !important;
    font-weight: bold;
  }
  
  .pen_orderlist {
    
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


  .searchDiv{
    width: 1255px;
    height: 250px;
    border: 1px solid #CCCCCC;
    position: absolute;
    top: 0;
    left: 0;
  }
  .searchDiv-title {
    height: 40px;
    background-color: #F2F2F2;
    border-bottom: 1px solid #CCCCCC;
  }
  .searchDiv-title p {
    padding-top: 8px;
    font-size: 15px;
  }
  .searchDiv-ele {
    height: 120px;
    border-bottom: 1px solid #CCCCCC;
  }
  .oneline {
    padding-top: 15px;
    padding-left: 20px;
  }
  .serbutton {
    background-color: #76A3A3 !important;
  }
  .searchDiv-exact-type {
    padding-top: 15px;
    padding-left: 20px;
  }
  .searchDiv-exact-val {
    padding-top: 12px;
    padding-left: 20px;
  }
  .exactinput {
      width: 40% !important; 
   }
  .syfhtime {
    color: red;
  }


  
/* 图片居中*/
.imginfan{
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


/* 图片放大部分  相对于原先盒子定位 */
.tooltip{

z-index:2;
}
.tooltip:hover{
z-index:3;

}
.tooltip .inner{
display: none;
}
.tooltip:hover .inner{    /*span 标签仅在 :hover 状态时显示*/
display:block;
position:absolute;
top:100px;
left:250px;
border:1px solid black;
background-color:#F2F2F2;
padding: 3px;
color:black;
text-align:left;
z-index:999;
}

.lableclass{
  background-color: #00FF00;
  color: #fff;
}

</style>
   

