
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
              :key="item1.value"
              :label="item1.label"
              :value="item1.value">
            </el-option>
          </el-select>
          &nbsp;&nbsp;&nbsp;

          <span>普通标签</span>&nbsp;

          <el-select v-model="value2" size="small" placeholder="请选择">
            <el-option
              v-for="item2 in options2"
              :key="item2.value"
              :label="item2.label"
              :value="item2.value">
            </el-option>
          </el-select>
          &nbsp;&nbsp;&nbsp;

          <span>店铺账号</span>&nbsp;

          <!-- yongfeng 2019.3.6 -->
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
            placeholder="选择日期时间">
          </el-date-picker>

          <span>到</span>&nbsp;
          <el-date-picker
            size="small"
            v-model="form.createTime2"
            type="datetime"
            placeholder="选择日期时间">
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

            <el-button class="pen_elbutton" type="info" size="small" @click="ajaxs()">同步订单</el-button>

            <!-- 批量导出 -->
            <el-button class="pen_elbutton" type="info" size="small" :loading="downloadLoading" @click="handleDownload">批量导出</el-button>

            <!-- 批量打印功能：v-print -->
            <el-button class="pen_elbutton" type="info" size="small" v-print="'#pdfDom'">批量打印</el-button>


            <!-- 下拉菜单：异常标签 -->
            <el-dropdown>

              <el-button type="info" class="pen_elbutton" size="small" >
                异常标签<i class="el-icon-arrow-down el-icon--right"></i>
              </el-button>

              <el-dropdown-menu slot="dropdown" >
                <!-- v-model="selected" 这个会导致一进页面就弹出下拉 -->
                <el-dropdown-item>价格出错</el-dropdown-item>
                <el-dropdown-item>客人需要收据</el-dropdown-item>
                <el-dropdown-item>缺货</el-dropdown-item>
                <el-dropdown-item>客人要求改尺寸</el-dropdown-item>
                <el-dropdown-item>客人要求改颜色</el-dropdown-item>
                <el-dropdown-item>联系不上客户</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>


            <!-- <select v-model="selected">
              <option v-for="option in options" v-bind:value="option.value">
                {{ option.text }}
              </option>
            </select> -->




            
            <!--下拉菜单： 普通标签 -->
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
    <div class="pen_orderlist" >

        <!-- v-loading.body="listLoading" -->
        <table  id="pdfDom"   @selection-change="handleSelectionChange" highlight-current-row width="1710" align="center" cellspacing="0" cellpadding="0" border >

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

              <td colspan="11" class="td_customer"><el-checkbox name="type"></el-checkbox>

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
                  {{rowdata.compname}} &nbsp;&nbsp;&nbsp;&nbsp; {{rowdata.shop_name}}
                </span>
              </td>
            </tr>

            <tr class="pen_order-content" align="center">
              <td><!-- 商品图片 -->
                <template v-for="(item,index) in rowdata.items">
                  
                  <div  class="imginfan">

                    <a class="tooltip" href="#">
                      <img :src="item.images" height="63" width="62" alt="">

                            <!-- 放大图 -->
                          <div class="inner">
                              <img class="" :src="item.images" height="360" width="280" alt="">
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
                  <span>数量:
                    <div class="cirle">{{item.variation_quantity_purchased}}</div>&nbsp;&nbsp;&nbsp;
                      
                    <!-- 采购信息  trigger="hover"  ref="popover1" v-model="tableData.rowdata.visible"   @show="handleStatus(scope) -->
                    <el-popover
                      placement="right"
                     
                      trigger="hover"
                     
                      width="200"
                      >
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

                        <!-- :hover="tableData.rowdata.visible = false"   v-popover:popover1-->
                      <span style="text-decoration: underline;" slot="reference"  >采购信息</span>

                  </el-popover>


                  </span>

                  <template v-if="index+1 < rowdata.items.length">
                    <br><br>
                  </template>
                </template>
              </td>
              <td>{{rowdata.create_time}}  <br/>
                  <span style="color:red" >发货剩余时间：  </span>  <br/>

                  <span style="color:red" > {{rowdata.days_to_ship}} </span>

              </td> <!-- 订单日期  发货剩余时间：待完善  -->

              <td>{{rowdata.order_status}}  </td> <!-- 订单状态 -->

              <td>{{rowdata.total_amount}}</td> <!-- 订单金额 -->

              <td>{{rowdata.shipping_carrier}}</td> <!-- 物流商 -->

              <td>{{rowdata.actual_shipping_cost}}</td> <!-- 快递费 -->

              <td>{{rowdata.country}}</td> <!-- 国家 -->

              <td>{{rowdata.tracking_no}}</td> <!-- 运单号 -->


              <td> {{ rowdata.shipping_carrier }} ：
                   <a href="" style="color:blue"> {{ rowdata.tracking_no }} </a> <br/>
                  已签收，签收人凭取货码签收。感谢使用雅居乐小区丰巢【自提柜】，期待再次为您服务。<br/>
                  {{ rowdata.create_time }} 

              </td> <!-- 采购运单信息  待完善 -->
              <td>{{rowdata.message_to_seller}}</td> <!-- 备注 -->
            </tr>
          </template>

        </table>

        <!-- 这部分是分页 -->   
        <div class="yema">
          <el-pagination 
            background
            @size-change="handleSizeChange" 
            @current-change="handleCurrentChange"  
            :page-sizes="[10,20,30]"
            :page-size="pageCount"   
            layout="total,jumper,prev, pager, next,sizes"
            :total="totalpage" >
          </el-pagination>
        </div>
        <!-- 这部分是分页 End-->

        <!-- page-size：每页显示条目个数，支持 .sync 修饰符 
            totalpage 总条目数
            page-count  总页数，totalpage 和 page-count 设置任意一个就可以达到显示页码的功能；如果要支持 page-sizes 的更改，则需要使用 totalpage 属性
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
// import topsearch from '../topsearch.vue';
import { submitPurchase,getDictInfo,getPurinfo,getOrderList } from '../../service/getData.js';

import { getPdf } from './../utils/htmlToPdf.js';

 // import { getOrderList } from './service/getData.js';
import util from '../../common/js/util';
import {getSession} from '../../common/js/util';


//导出插件
import FileSaver from 'file-saver'
import XLSX from 'xlsx'

    export default{

      props: [
          'dqwz',   //当前位置
          'orderStatus' //订单状态
      ],


      data(){

        return{
          dialogVisible: false,

          
          totalpage: " ",
          currentPage:1,   //  默认显示第一条
          pageCount:5,//每页显示条数

          // list: null,
          tableData: [ ],
          // listLoading: true,
          multipleSelection: [],
          downloadLoading: false,
          filename: '',

          

          // selected:[ ],
          // options: [
          //   { text: 'One', value: 'A' },
          //   { text: 'Two', value: 'B' },
          //   { text: 'Three', value: 'C' }
          // ],


          

          userid: "", //用户id

          form:{
            // orderStatus: "READY_TO_SHIP", // 订单状态
            createTime1: "", //订单时间 从
            createTime2: "", //订单时间 到
            searchType: "1", //精确搜索类型 1订单号,2运单号,3收件人,4产品SKU,5采购账号,6采购运单号
            searchCode: "", //精确搜索代码
            pageIndex: "1", //页码
            pageCount: "10" //每页显示条数
            // total:" "
          },

          tableData1:[],
          data1: '',
          data2: '',
          searchVal:'', //默认输入为空
          value1: '价格出错',
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
          
          options1: [
            {
              value: '选项1',
              label: '价格出错'
            }, 
            {
              value: '选项2',
              label: '客人需要收据'
            }, 
            {
              value: '选项3',
              label: '缺货'
            }, 
            {
              value: '选项4',
              label: '客人要求改尺寸'
            }, 
            {
              value: '选项5',
              label: '客人要求改颜色'
            },
            {
              value: '选项6',
              label: '联系不上客户'
            }
          
          ],
          
            

          options2:[
            {
              value: '选项1',
              label: '正常'
            }, 
            {
              value: '选项2',
              label: '寄顺丰'
            }, 
            {
              value: '选项3',
              label: '超材'
            }, 
            {
              value: '选项4',
              label: '已购买'
            }
          
          ],

          options3:[ ],

          options4:[
            {
              value: '选项1',
              label: '待发货'
            }, 
            {
              value: '选项2',
              label: '已发货'
            }, 
            {
              value: '选项3',
              label: '已取消'
            }, 
            {
              value: '选项2-2',
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

        // this.fetchData();

        //从登陆session中获取店铺id
        // that.form.shop_id = getSession("shop_id");

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

      
        // 批量导出
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


        fetchData() {

          // this.listLoading = true;

          console.log( '导出Export') 

          var api='http://192.168.1.178:8888/api/ExportOrder';
            Axios.post(api,{
            
            }).then((response)=>{

              this.tableData = response.data;
              // this.listLoading = false

              console.log( '导出ExportOrder123==>', response.data)  

            }).catch((error)=>{

              console.log("请求数据失败==》", error);
            })
        },

        // 导出按钮方法
        handleDownload() {

          // excel数据导出   tHeader:标题     filterVal：字段名    tableData:前端请求的数据

          //如果存在选中的，则打印
          if (this.multipleSelection.length) {

            console.log("请求打印数据==》");

            this.downloadLoading = true;

            // // @  :src 
            
            // import('../../vendor/Export2Excel').then(excel => {
            //   // const tHeader = ['Id', 'Title', 'Author', 'Readings', 'Date']
            //   // const filterVal = ['id', 'title', 'author', 'pageviews', 'display_time']
              // const tableData = this.multipleSelection
            //   // const data = this.formatJson(filterVal, tableData)
            //   // excel.export_json_to_excel({
            //   //   header: tHeader,
            //   //   data,
                // filename: this.filename
            //   // })
            //   // this.$refs.multipleTable.clearSelection()
            //   // this.downloadLoading = false
            // })
             //否则，提示
          } else {   
            this.$message({
              message: '请选择',
              type: 'warning'
            })
          }
        },

        formatJson(filterVal, jsonData) {
            return jsonData.map(v => filterVal.map(j => v[j]))
        },

        // 选中时打印
        handleSelectionChange(val) {
          this.multipleSelection = val
        },


        // exportExcel() {

        

        //     require.ensure([], () => {
        //         const {
        //             export_json_to_excel
        //         } = require('../../assets/js/Export2Excel');
        //         const tHeader = ['序号','省份', '投资总额', '收益总额', '主要投资项目','投资周期', '投资人数', '投资年变化率','备注'];
        //         const filterVal = ['index','provinces', 'orderMoney', 'incomeMoney', 'payType','orderPeriod', 'orderPersonConunt', 'orderYearRate', 'remarks'];
        //         const list = this.tableData;
        //         const data = this.formatJson(filterVal, list);
        //         export_json_to_excel(tHeader, data, '列表excel');
        //     })
        // },
        
        


        getUserShop(){

          // http://192.168.0.122:8888/api/
          var api='http://192.168.1.178:8888/api/getUserShop';
          // var api='http://116.62.112.118:8888/api/getUserShop';

          var userid = this.userid
          // console.log(userid+"888")
            Axios.post(api,{
              userid:userid
            }).then((response)=>{
              console.log( '获取有权限店铺列表==>', response.data)  

              this.options3 = response.data

              console.log(this.options3)
            }).catch((error)=>{
              console.log("请求数据失败==》", error);
            })
        },



        /*查询订单信息*/
         qryOrderList(shopId,createTime1,createTime2,orderStatus,searchType,searchCode,pageIndex,pageCount){
            var that = this;
            
            getOrderList(shopId,createTime1,createTime2,orderStatus,searchType,searchCode,pageIndex,pageCount,res=>{
              
              console.log("获取订单列表",res);

              that.tableData = res[0].table;   //
              that.totalpage = res[0].totalcount;     //把后台返回的res[0].totalcount总条数赋值给自定义定义的totalpage

          });
        },

        /*多个查询条件按钮*/
        qryBtn(){

          var that = this;
          let shopId = that.value3;//店铺id
          let createTime1 = that.form.createTime1;//订单时间 从
          let createTime2 = that.form.createTime2;//订单时间 到
          let orderStatus = that.orderStatus;//订单状态
          let searchType = "";//精确搜索类型
          let searchCode = "";//精确搜索代码
          let pageIndex = that.form.pageIndex;//页码
          let pageCount = that.form.pageCount; //每页显示条数


          console.log("shopId ="+shopId+",createTime1="+createTime1+",createTime2="+createTime2);
          console.log("orderStatus=" +orderStatus +",pageIndex="+pageIndex+",pageCount="+pageCount+",searchType="+searchType+",searchCode="+searchCode );

         
         // 方法调用方法
          /*that.$options.methods.getData(shopId,createTime1,createTime2,orderStatus,searchType,searchCode,pageIndex,pageCount).bind(this)();*/
          that.$options.methods.qryOrderList.bind(this)(shopId,createTime1,createTime2,orderStatus,searchType,searchCode,pageIndex,pageCount);
        },

        /*按搜索内容查询按钮*/
        
        searchCodeBtn(){
          var that = this;
          let shopId = that.value3;//店铺id
          let createTime1 = "";//订单时间 从
          let createTime2 = "";//订单时间 到
          let orderStatus = that.orderStatus;//订单状态
          let searchType = that.form.searchType;//精确搜索类型
          let searchCode = that.form.searchCode;//精确搜索代码
          let pageIndex = that.form.pageIndex;//页码
          let pageCount = that.form.pageCount; //每页显示条数
         
          that.$options.methods.qryOrderList.bind(this)(shopId,createTime1,createTime2,orderStatus,searchType,searchCode,pageIndex,pageCount);
        },


        // 获取待处理订单接口
        // getData(){

      //       var that = this;

      //       var status = that.orderStatus;

      //       // let para = {
      //       //         pageNum: this.pageNum,
      //       //         pageSize: this.pageSize,
      //       // };

      //       // this.loading = true;
      //       // var start = (this.page - 1) * this.limit;
      //       // var end   = this.page * this.limit;

      //       // 116.62.112.118     192.168.1.187
        //     var api='http://116.62.112.118:8888/api/getPendingOrders';

      //       Axios.post(api,{
      //          orderStatus: status //待处理订单参数
      //          // pagesize:this.pagesize,
      //          // currentPage:this.currentPage
      //       }).then((response)=>{
      //         this.tableData=response.data;
      //         console.log( 'response1==>', response)  
      //       }).catch((error)=>{
      //         console.log("请求数据失败==》", error);
      //       })
        // },

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

      
          //采购信息悬浮
          handleStatus(scope){
              console.log(scope.row.userId);
              var that = this;
              that.rowdata.index=scope;
              console.log("scope=",scope);

          },


          //分页  初始页currentPage、初始每页数据数pagesize和数据testpage

          // 每页条数 console.log(`每页 ${val} 条`);
          handleSizeChange:function(pageCount){

            var that = this;
            
            that.form.pageCount = pageCount; //每页显示条数

            that.$options.methods.qryBtn.bind(this)();

            console.log("pageCount==>",pageCount );

            
          },

          // 当前 页码数 console.log(`当前页: ${val}`);
          handleCurrentChange:function(pageIndex){
            var that = this;

            // 赋值：后面的pageIndex 赋值给 that.form.pageIndex
            that.form.pageIndex = pageIndex;//页码

            // 方法调用方法
            that.$options.methods.qryBtn.bind(this)();
          
            console.log("pageIndex==>", this.form.pageIndex);
              
          },




          
      },

      mounted(){

        // var that = this;
        //从登陆session中获取店铺id
        this.userid = getSession("user_id");
        // console.log(this.userid+'444')
        // this.options3 = getSession('shop_id')

        this.getUserShop()
        // console.log(this.options3+"-----shopid数组------")

        // this.getData()
       
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
    background-color: #76A3A3;
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
    width: 570px;
  }



/* 图片居中*/
.imginfan{
    position: relative; 
}

.imginfan img {

  /*text-align:center;
  position: absolute;
  top:50%; 
  left:50%;
  transform: translate(-50%,-50%);

  */

  margin: 10px 5px;
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
top:-30px;
left:150px;
/*
top:100px;
left:250px;

background-color:#F2F2F2;
padding: 3px;
color:black;
border:1px solid black;

*/

text-align:left;
z-index:999;
}










</style>
   

