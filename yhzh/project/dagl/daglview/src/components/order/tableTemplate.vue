<template>
    <div id="allorder"> 
      <div>

        <table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border="1" cellspacing="0" cellpadding="0" style="position: relative;">
          
          <thead>
            <tr>  <!-- 表头 th:为表头标题 -->
                <th ><input type="checkbox" name="">商品图片</th>
                <th ><span>产品明细</span></th>
                <th ><span>订单日期</span> </th>
                <th ><span>订单状态</span> </th>
                <th ><span>订单金额</span></th>
                <th ><span>物流商</span></th> 
                <th ><span>快递费</span></th>
                <th ><span>国家</span></th>
                <th ><span>运单号</span></th>
                <th ><span>采购运单信息</span></th>
                <th ><span>备注</span></th>
                
            </tr> 
          
          </thead>

          <tbody>
            <template v-for="row in tableData">
              <tr>  <!-- 人物标题  -如果此处tr加入td则会出现表格 -->
                <input type="checkbox">
                <span style="left:20px;position: absolute;">{{row.ordersn}}</span>
                <span style="left: 220px;position: absolute;">{{row.buyer_username}}</span>
                
                <span style="right: 0;position: absolute;">{{row.company}} </span>
              </tr>

              <tr>   <!-- 产品内容信息： tr这里面的每个td和上面人物标题的tr里的内容绑定是平级关系 -->

                <td>    <!-- 商品图片 -->
                  <template v-for="itme2 in row.list2">
                    <div class="imginfan"> 

                      <!-- <img :src="defaultImg" alt="itme2"> -->
                      <a class="tooltip" href="#tooltips">
                        <img class="" src="../../assets/images/01.jpg" height="90" width="70" alt="">

                          <div class="in">
                            
                              <img class="" src="../../assets/images/01.jpg" height="360" width="280" alt="">
   
                          </div>
                        
                      </a>

                        <!-- <img class="images" src="../../assets/images/123.jpg" height="90" width="70" alt=""> -->
                    </div>
  
                  </template>
                </td>

                <!-- 此处td为表格里说需要遍历的内容， -->

                <td> <!-- 产品明细 -->
                  <template v-for="itme2 in row.list2">
                    <div class="produce" style="margin-top:10px">
                      <span > {{itme2.produceid}} </span><br>
                      <span>Item_sku: {{itme2.item_sku}} </span><br>
                      <span>原价： {{itme2.variation_original_price}} </span> 
                       
                      <span>折后价：{{itme2.variation_original_price}} </span><br>
                      
                      <span style="margin-right: 15px;">数量： {{itme2.variation_quantity_purchased}} </span>  
                      <a class="tooltips" href="#tooltips">采购信息
                        <div class="inf">
                          <span>商品id {{itme2.produceid}} </span><br> 
                          <span>淘宝账号: {{itme2.tb_code }} </span> <br>  
                          <span>淘宝订单号:{{itme2.tb_order_no }} </span> <br>  
                          <span>采购时间:{{itme2.purchase_time }} </span> <br>   
                          <span>采购价格:{{itme2.purchase_price }} </span> <br> 
                          <span>采购备注:{{itme2.purchase_note }} </span><br> 
                          <span>淘宝客链接: {{itme2.tbk_url}} </span> <br>  
                          <span>淘宝客比例:{{itme2.tbk_bl}} </span> <br>  
                          <span>报关名:{{itme2.bgname}} </span> <br>   
                        </div>
                        
                      </a>
                       
                      <br> 
                  </div>

                  </template>

                </td>

                <td>   <!-- 订单日期 -->
                  <div class="create_time" style="width:100px">
                    <span>{{row.create_time}}</span><br>
                    发货剩余时间：<br> 
                    <span>{{row.days_to_ship}}</span>
                  </div>
                </td> 

                <td>   <!-- 订单状态 -->
                  <div class="" style="width:100px">
                    <span>{{row.order_status}}</span> 
                  </div>
                </td> 

                <td>    <!-- 订单金额 -->
                  <div class="" style="width:100px">
                    <span>{{row.total_amount}}</span> 
                  </div>
                </td> 

                <td>    <!-- 物流商 -->
                  <div class="" style="width:100px">
                    <span>{{row.shipping_carrier}}</span> 
                  </div>
                </td> 

                <td>      <!-- 快递费 -->
                  <div class="" style="width:100px">
                    <span>{{row.estimated_shipping_fee}}</span> 
                  </div>
                </td> 

                <td>        <!-- 国家 -->
                  <div class="" style="width:100px">
                    <span>{{row.country}}</span> 
                  </div>
                </td>

                <td>        <!--  运单号 -->
                  <div class="" style="width:180px">
                    <span>{{row.tracking_no}}</span> 
                  </div>
                </td> 

                <td>    <!-- 采购运单信息 -->
                  <div class="" style="width:180px">
                    <span>{{row.shipping_carrier}}：</span> <br>
                    <span>{{row.shippingno}}</span> <br>
                    <span>已签收，签收人{{row.name}} 
                    凭取货码签收。感谢使用雅居乐小区丰巢【自提柜】，期待再次为您服务。</span> <br>
                    <span>{{row.date}}</span> <br>
                  </div>
                </td> 

                <td>      <!-- 备注 -->
                  <div class="" style="width:100px">
                    <span>{{row.message_to_seller}}</span> 
                  </div>
                </td> 
                

              </tr>
            </template>
          
          </tbody>
          
        </table>


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

          // tableData:[
          //   {
          //     ordersn:"",
          //     buyer_username:"",
          //     company:"",
          //     produceid:"",
          //     item_sku:"",
          //     variation_original_price:"",
          //     variation_quantity_purchased:"",
          //     create_time:"",
          //     order_status:"",
          //     days_to_ship:"",
          //     total_amount:"",
          //     shipping_carrier:"",
          //     estimated_shipping_fee:"",
          //     country:"",
          //     tracking_no:"",
          //     shipping_carrier:"",
          //     shippingno:"",
          //     date:"",
          //     name:"",
          //     message_to_seller:"",
              
          //     item2: [{
          //       img:" ",
          //       produceid:"",
          //       item_sku:,
          //       variation_original_price:,
          //       variation_original_price:,
          //       variation_quantity_purchased:,

          //       // 采购信息
          //       produceid:'',
          //       tb_code:, 
          //       purchase_time:'',
          //       purchase_price:,
          //       purchase_note :"",
          //       tbk_url:'',
          //       tbk_bl:"",
          //       bgname:''

          //     }] 
          //   }         
          // ],

          tableData:[
            {
                ordersn:"1812918019YAP",
                buyer_username:"胡芸芸",
                company:"YM-ADFAAFD",

                produceid:"商品1",
                item_sku:564286534832520,
                
                variation_original_price:427,
                variation_quantity_purchased:500,
                create_time:"2018-12-29 18:01:22 ",
                order_status:"待发货",
                days_to_ship:"1天23小时54分钟",

                total_amount:" 806",
                shipping_carrier:" 虾皮店配-711",
                estimated_shipping_fee:"13 ",
                country:"china ",
                tracking_no:"TW123546879465B",
                shipping_carrier:"圆通速递 ",
                shippingno:"813659238294576655 ",
                date:" 2018-12-28 15:43:50",
                name:"bBA",
                message_to_seller:"客户要求换颜色 ",

                // list1:[
                //   { }
                // ],

                // 产品明细
                list2:[
                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  },

                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  }
                ]
                
            },
            {
                ordersn:"1812918019YAP",
                buyer_username:"胡芸芸",
                company:"YM-ADFAAFD",

                produceid:"商品1",
                item_sku:564286534832520,
                variation_original_price:427,
                variation_original_price:427,
                variation_quantity_purchased:500,
                create_time:"2018-12-29 18:01:22 ",
                order_status:"待发货",
                days_to_ship:"1天23小时54分钟",

                total_amount:" 806",
                shipping_carrier:" 虾皮店配-711",
                estimated_shipping_fee:"13 ",
                country:"china ",
                tracking_no:"TW123546879465B",
                shipping_carrier:"圆通速递 ",
                shippingno:"813659238294576655 ",
                date:" 2018-12-28 15:43:50",
                name:"bBA",
                message_to_seller:"客户要求换颜色 ",

                // list1:[
                //   { }
                // ],

                // 产品明细
                list2:[
                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  },

                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  }
                ]
                
            },
            {
                ordersn:"1812918019YAP",
                buyer_username:"胡芸芸",
                company:"YM-ADFAAFD",

                produceid:"商品1",
                item_sku:564286534832520,
                variation_original_price:427,
                variation_original_price:427,
                variation_quantity_purchased:500,
                create_time:"2018-12-29 18:01:22 ",
                order_status:"待发货",
                days_to_ship:"1天23小时54分钟",

                total_amount:" 806",
                shipping_carrier:" 虾皮店配-711",
                estimated_shipping_fee:"13 ",
                country:"china ",
                tracking_no:"TW123546879465B",
                shipping_carrier:"圆通速递 ",
                shippingno:"813659238294576655 ",
                date:" 2018-12-28 15:43:50",
                name:"bBA",
                message_to_seller:"客户要求换颜色 ",

                list1:[
                  { }
                ],

                // 产品明细
                list2:[
                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  },

                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  }
                ]
                
            },
            {
                ordersn:"1812918019YAP",
                buyer_username:"胡芸芸",
                company:"YM-ADFAAFD",

                produceid:"商品1",
                item_sku:564286534832520,
                variation_original_price:427,
                variation_original_price:427,
                variation_quantity_purchased:500,
                create_time:"2018-12-29 18:01:22 ",
                order_status:"待发货",
                days_to_ship:"1天23小时54分钟",

                total_amount:" 806",
                shipping_carrier:" 虾皮店配-711",
                estimated_shipping_fee:"13 ",
                country:"china ",
                tracking_no:"TW123546879465B",
                shipping_carrier:"圆通速递 ",
                shippingno:"813659238294576655 ",
                date:" 2018-12-28 15:43:50",
                name:"bBA",
                message_to_seller:"客户要求换颜色 ",

                list1:[
                  { }
                ],

                // 产品明细
                list2:[
                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  },

                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  }
                ]
                
            },
            {
                ordersn:"1812918019YAP",
                buyer_username:"胡芸芸",
                company:"YM-ADFAAFD",

                produceid:"商品1",
                item_sku:564286534832520,
                variation_original_price:427,
                variation_original_price:427,
                variation_quantity_purchased:500,
                create_time:"2018-12-29 18:01:22 ",
                order_status:"待发货",
                days_to_ship:"1天23小时54分钟",

                total_amount:" 806",
                shipping_carrier:" 虾皮店配-711",
                estimated_shipping_fee:"13 ",
                country:"china ",
                tracking_no:"TW123546879465B",
                shipping_carrier:"圆通速递 ",
                shippingno:"813659238294576655 ",
                date:" 2018-12-28 15:43:50",
                name:"bBA",
                message_to_seller:"客户要求换颜色 ",

                list1:[
                  { }
                ],

                // 产品明细
                list2:[
                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  },

                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  }
                ]
                
            },
            {
                ordersn:"1812918019YAP",
                buyer_username:"胡芸芸",
                company:"YM-ADFAAFD",

                produceid:"商品1",
                item_sku:564286534832520,
                variation_original_price:427,
                variation_original_price:427,
                variation_quantity_purchased:500,
                create_time:"2018-12-29 18:01:22 ",
                order_status:"待发货",
                days_to_ship:"1天23小时54分钟",

                total_amount:" 806",
                shipping_carrier:" 虾皮店配-711",
                estimated_shipping_fee:"13 ",
                country:"china ",
                tracking_no:"TW123546879465B",
                shipping_carrier:"圆通速递 ",
                shippingno:"813659238294576655 ",
                date:" 2018-12-28 15:43:50",
                name:"bBA",
                message_to_seller:"客户要求换颜色 ",

                list1:[
                  { }
                ],

                // 产品明细
                list2:[
                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  },

                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  }
                ]
                
            },
            {
                ordersn:"1812918019YAP",
                buyer_username:"胡芸芸",
                company:"YM-ADFAAFD",

                produceid:"商品1",
                item_sku:564286534832520,
                variation_original_price:427,
                variation_original_price:427,
                variation_quantity_purchased:500,
                create_time:"2018-12-29 18:01:22 ",
                order_status:"待发货",
                days_to_ship:"1天23小时54分钟",

                total_amount:" 806",
                shipping_carrier:" 虾皮店配-711",
                estimated_shipping_fee:"13 ",
                country:"china ",
                tracking_no:"TW123546879465B",
                shipping_carrier:"圆通速递 ",
                shippingno:"813659238294576655 ",
                date:" 2018-12-28 15:43:50",
                name:"bBA",
                message_to_seller:"客户要求换颜色 ",

                list1:[
                  { }
                ],

                // 产品明细
                list2:[
                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  },

                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  }
                ]
                
            },

            {
                ordersn:"1812918019YAP",
                buyer_username:"胡芸芸",
                company:"YM-ADFAAFD",

                produceid:"商品1",
                item_sku:564286534832520,
                variation_original_price:427,
                variation_original_price:427,
                variation_quantity_purchased:500,
                create_time:"2018-12-29 18:01:22 ",
                order_status:"待发货",
                days_to_ship:"1天23小时54分钟",

                total_amount:" 806",
                shipping_carrier:" 虾皮店配-711",
                estimated_shipping_fee:"13 ",
                country:"china ",
                tracking_no:"TW123546879465B",
                shipping_carrier:"圆通速递 ",
                shippingno:"813659238294576655 ",
                date:" 2018-12-28 15:43:50",
                name:"bBA",
                message_to_seller:"客户要求换颜色 ",

                list1:[
                  { }
                ],

                // 产品明细
                list2:[
                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  },

                  {
                    produceid:"商品1",
                    item_sku:564286534832520,
                    variation_original_price:427,
                    variation_original_price:427,
                    variation_quantity_purchased:500,

                    // 采购信息
                    produceid:'商品1',
                    tb_code:564286534832, 
                    purchase_time:'2019/1/20',
                    purchase_price:512,
                    purchase_note :"fghasdhj",
                    tbk_url:'dbsbsjjsd',
                    tbk_bl:"1",
                    bgname:'ASD'
                  }
                 
                ]
            
            }
            
          ]

        }
    } ,

    mounted(){
        this.getproduceList();
    },

    methods:{
      

      //分页信息
      handleSizeChange: function (size) {
          this.pagesize = size;
      },
      handleCurrentChange: function(currentPage){
          this.currentPage = currentPage;
      },

      // 获取produce列表接口
      getproduceList(){
        var api='http://www.phonegap100.com/appapi.java?a=getPortalList&catid=20&page=1';

       //用箭头函数
        Axios.get(api).then((response)=>{
            this.tableData=response.data.result;
            console.log( 'response1==>', response)  //控制台检验输出api请求的数据
        }).catch((error)=>{
            console.log(error);
        })
      }

    }
}

</script>



<style scoped>

#allorder{
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