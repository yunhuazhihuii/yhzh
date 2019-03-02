<template>
    <div id="allorder"> 

      <div class="tables">
  
        <table width="1710" align="center" cellspacing="0" cellpadding="0">
          <thead>
            <tr class="firsttitle">
              <td width="110"> <input type="checkbox" name=""> 商品图片</td>
              <td width="180">产品明细</td>
              <td width="150">订单日期</td>
              <td width="100">订单状态</td>
              <td width="75">订单金额</td>
              <td width="100">物流商</td>
              <td width="65">快递费</td>
              <td width="65">国家</td>
              <td width="120">运单号</td>
              <td width="150">采购运单信息</td>
              <td width="150">备注</td>
            </tr>
          </thead>

          <tbody> 
           
            <tr class="order-name" v-for="(stu, index) in students">
              <td colspan="14">
                <input type="checkbox">{{stu.ordersn}}   {{stu.buyer_username }}  
                <span>{{stu.compname}} </span>
              </td>
            </tr>

            <tr class="order-content">
              <!-- 商品图片 -->
              <td>
                <div class="imginfan" v-for="i in product">
                    <img :src="i.src"/ height="75" width="60" alt="">
                </div>

                

                <div>
                    <img src="../../assets/images/123.jpg" height="75" width="60" alt="">
                </div>
                
              </td>

              <!-- 产品明细 -->
              <td>
                <div class="imginfan">
                  <span>商品1</span> <br>  
                  <span>Item_sku: 564286534832</span> <br>  
                  <span>原价：427  </span> <br>  
                  <span>折后价：427</span> <br>  
                  <span>数量：500  </span>  
                  <a class="tooltips" href="#tooltips">采购信息
                    <div class="inf">
                      <span>商品id</span><br> 
                      <span>淘宝账号:  </span> <br>  
                      <span>淘宝订单号:</span> <br>  
                      <span>采购时间:</span> <br>   
                      <span>采购价格:</span> <br> 
                      <span>采购备注:</span><br> 
                      <span>淘宝客链接:  </span> <br>  
                      <span>淘宝客比例:</span> <br>  
                      <span>报关名:</span> <br>   
                    </div>
                    
                  </a><br>    
                </div>

                <div>
                  <span>商品1</span> <br>  
                  <span>Item_sku: 564286534832</span> <br>  
                  <span>原价：427  </span> <br>  
                  <span>折后价：427</span> <br>  
                  <span>数量：500</span>

                  <a class="tooltips" href="#tooltips">采购信息
                    <div class="inf">
                      <span>商品id</span><br> 
                      <span>淘宝账号:  </span> <br>  
                      <span>淘宝订单号:</span> <br>  
                      <span>采购时间:</span> <br>   
                      <span>采购价格:</span> <br> 
                      <span>采购备注:</span><br> 
                      <span>淘宝客链接:  </span> <br>  
                      <span>淘宝客比例:</span> <br>  
                      <span>报关名:</span> <br>   
                    </div>
                    
                  </a><br>     
                </div>
               
                <div>
                  <span>商品1</span> <br>  
                  <span>Item_sku: 564286534832</span> <br> 
                  <span>原价：427  </span> <br>  
                  <span>折后价：427</span> <br>  
                  <span>数量：500</span> 
                  <a class="tooltips" href="#tooltips">采购信息
                    <div class="inf">
                      <span>商品id</span><br> 
                      <span>淘宝账号:  </span> <br>  
                      <span>淘宝订单号:</span> <br>  
                      <span>采购时间:</span> <br>   
                      <span>采购价格:</span> <br> 
                      <span>采购备注:</span><br> 
                      <span>淘宝客链接:  </span> <br>  
                      <span>淘宝客比例:</span> <br>  
                      <span>报关名:</span> <br>   
                    </div>
                    
                  </a><br> 
                       
                </div>
 
              </td>

              <!-- 订单日期 -->
              <td>
                  <span>2018-12-29 18:01:22</span> <br>   
                  <span>发货剩余时间：</span> <br>     
                  <span>1天23小时54分钟 </span>   
              </td>

              <!-- 订单状态 -->
              <td>
                  <span>ready_to_ship</span><br>
                  <span>代发货</span><br>    
                  <span>价格出错: {{ selected }}</span><br>   
              </td>


                <!-- 订单金额     -->
              <td><span>756</span> </td>         

              <!-- 物流商 -->
              <td><span>虾皮店配-711</span> </td>  

              <!-- 快递费 -->
              <td><span>13</span> </td>

              <!-- 国家 -->
              <td><span> china</span> </td>

              <!-- 运单号 -->
              <td><span> 813659238294576655china</span> </td>

              <!-- 采购运单信息 -->
              <td>
                  <span>圆通速递：813659238294576655</span><br>
                  <span>已签收，签收人凭取货码签收。感谢使用雅居乐小区丰巢【自提柜】，期待再次为您服务。</span><br>
                  <span>2018-12-28 15:43:50</span><br>
              </td>
              
              <!-- 备注 -->
              <td><span>客户要求换颜色</span> </td>
            </tr>

            <tr class="order-name">
              <td colspan="14">
                <input type="checkbox">1812918019YAP胡芸芸 
                <span>YM-ADFAAFD</span>
              </td>
            </tr>

            <tr class="order-content">
              <!-- 商品图片 -->
              <td>
                <div class="imginfan">
                    <img src="../../assets/images/123.jpg" height="75" width="60" alt="">
                </div>

                <div>
                    <img src="../../assets/images/123.jpg" height="75" width="60" alt="">
                </div>

                <div>
                    <img src="../../assets/images/123.jpg" height="75" width="60" alt="">
                </div>
                
              </td>

              <!-- 产品明细 -->
              <td>
                <div class="imginfan">
                  <span>商品1</span> <br>  
                  <span>Item_sku: 564286534832</span> <br>  
                  <span>原价：427  </span> <br>  
                  <span>折后价：427</span> <br>  
                  <span>数量：500  </span>  
                  <a class="tooltips" href="#tooltips">采购信息
                    <div class="inf">
                      <span>商品id</span><br> 
                      <span>淘宝账号:  </span> <br>  
                      <span>淘宝订单号:</span> <br>  
                      <span>采购时间:</span> <br>   
                      <span>采购价格:</span> <br> 
                      <span>采购备注:</span><br> 
                      <span>淘宝客链接:  </span> <br>  
                      <span>淘宝客比例:</span> <br>  
                      <span>报关名:</span> <br>   
                    </div>
                    
                  </a><br>    
                </div>

                <div>
                  <span>商品1</span> <br>  
                  <span>Item_sku: 564286534832</span> <br>  
                  <span>原价：427  </span> <br>  
                  <span>折后价：427</span> <br>  
                  <span>数量：500</span>

                  <a class="tooltips" href="#tooltips">采购信息
                    <div class="inf">
                      <span>商品id</span><br> 
                      <span>淘宝账号:  </span> <br>  
                      <span>淘宝订单号:</span> <br>  
                      <span>采购时间:</span> <br>   
                      <span>采购价格:</span> <br> 
                      <span>采购备注:</span><br> 
                      <span>淘宝客链接:  </span> <br>  
                      <span>淘宝客比例:</span> <br>  
                      <span>报关名:</span> <br>   
                    </div>
                    
                  </a><br>     
                </div>
               
                <div>
                  <span>商品1</span> <br>  
                  <span>Item_sku: 564286534832</span> <br> 
                  <span>原价：427  </span> <br>  
                  <span>折后价：427</span> <br>  
                  <span>数量：500</span> 
                  <a class="tooltips" href="#tooltips">采购信息
                    <div class="inf">
                      <span>商品id</span><br> 
                      <span>淘宝账号:  </span> <br>  
                      <span>淘宝订单号:</span> <br>  
                      <span>采购时间:</span> <br>   
                      <span>采购价格:</span> <br> 
                      <span>采购备注:</span><br> 
                      <span>淘宝客链接:  </span> <br>  
                      <span>淘宝客比例:</span> <br>  
                      <span>报关名:</span> <br>   
                    </div>
                    
                  </a><br> 
                       
                </div>
 
              </td>

              <!-- 订单日期 -->
              <td>
                  <span>2018-12-29 18:01:22</span> <br>   
                  <span>发货剩余时间：</span> <br>     
                  <span>1天23小时54分钟 </span>   
              </td>

              <!-- 订单状态 -->
              <td>
                  <span>ready_to_ship</span><br>
                  <span>代发货</span><br>    
                  <span>价格出错: {{ selected }}</span><br>   
              </td>


                <!-- 订单金额     -->
              <td><span>756</span> </td>         

              <!-- 物流商 -->
              <td><span>虾皮店配-711</span> </td>  

              <!-- 快递费 -->
              <td><span>13</span> </td>

              <!-- 国家 -->
              <td><span> china</span> </td>

              <!-- 运单号 -->
              <td><span> 813659238294576655china</span> </td>

              <!-- 采购运单信息 -->
              <td>
                  <span>圆通速递：813659238294576655</span><br>
                  <span>已签收，签收人凭取货码签收。感谢使用雅居乐小区丰巢【自提柜】，期待再次为您服务。</span><br>
                  <span>2018-12-28 15:43:50</span><br>
              </td>
              
              <!-- 备注 -->
              <td><span>客户要求换颜色</span> </td>
            </tr>


            <tr class="order-name" >
              <td colspan="14">
                <input type="checkbox">1812918019YAP胡芸芸 
                <span>YM-ADFAAFD</span>
               <!--  <input type="checkbox">
                <span>{{site.number}}</span>
                <span>{{site.name}}</span>
                <span class="com">{{site.company}}</span> -->
              </td>
            </tr>
            
            <tr class="order-content">
              <!-- 商品图片 -->
              <td>
                <div>
                    <img src="../../assets/images/123.jpg" height="75" width="60" alt="">
                </div>
                <div>
                    <img src="../../assets/images/123.jpg" height="75" width="60" alt="">
                </div>
                
                
              </td>

              <!-- 产品明细 -->
              <td>
                <div>
                  <span>商品1</span> <br>  <!--  {{ id}} -->
                  <span>Item_sku: 564286534832</span> <br>  <!--  {{ Item_sku}} -->
                  <span>原价：427  </span> <br>  <!--  {{ preprice}} -->
                  <span>折后价：427</span> <br>  <!--  {{ countprice}} -->
                  <span>数量：500</span>  
                  <a class="tooltips" href="#tooltips">采购信息
                    <div class="inf">
                      <span>商品id</span><br> 
                      <span>淘宝账号:  </span> <br>  
                      <span>淘宝订单号:</span> <br>  
                      <span>采购时间:</span> <br>   
                      <span>采购价格:</span> <br> 
                      <span>采购备注:</span><br> 
                      <span>淘宝客链接:  </span> <br>  
                      <span>淘宝客比例:</span> <br>  
                      <span>报关名:</span> <br>   
                    </div>
                    
                  </a><br> 
                </div>
                 <div>
                  <span>商品1</span> <br>  <!--  {{ id}} -->
                  <span>Item_sku: 564286534832</span> <br>  <!--  {{ Item_sku}} -->
                  <span>原价：427  </span> <br>  <!--  {{ preprice}} -->
                  <span>折后价：427</span> <br>  <!--  {{ countprice}} -->
                  <span>数量：500</span> 
                  <a class="tooltips" href="#tooltips">采购信息
                    <div class="inf">
                      <span>商品id</span><br> 
                      <span>淘宝账号:  </span> <br>  
                      <span>淘宝订单号:</span> <br>  
                      <span>采购时间:</span> <br>   
                      <span>采购价格:</span> <br> 
                      <span>采购备注:</span><br> 
                      <span>淘宝客链接:  </span> <br>  
                      <span>淘宝客比例:</span> <br>  
                      <span>报关名:</span> <br>   
                    </div>
                    
                  </a><br>      
                </div>
               
              </td>

              <!-- 订单日期 -->
              <td>
                  <span>2018-12-29 18:01:22</span> <br>   
                  <span>发货剩余时间：</span> <br>     
                  <span>1天23小时54分钟 </span>  
              </td>

              <!-- 订单状态 -->
              <td>
                  <span>ready_to_ship</span><br>
                  <span>代发货</span><br>    
                  <span>价格出错</span><br>    
              </td>


                <!-- 订单金额     -->
              <td><span>756</span> </td>         

              <!-- 物流商 -->
              <td><span>虾皮店配-711</span> </td>  

              <!-- 快递费 -->
              <td><span>13</span> </td>

              <!-- 国家 -->
              <td><span> china</span> </td>

              <!-- 运单号 -->
              <td><span> 813659238294576655china</span> </td>

              <!-- 采购运单信息 -->
              <td>
                  <span>圆通速递：813659238294576655</span><br>
                  <span>已签收，签收人凭取货码签收。感谢使用雅居乐小区丰巢【自提柜】，期待再次为您服务。</span><br>
                  <span>2018-12-28 15:43:50</span><br>
              </td>
              
              <!-- 备注 -->
              <td><span>客户要求换颜色</span> </td>
            </tr>

          
          </tbody>
        </table>

        <!-- 分页页码 -->
        


      </div>

        
    </div>
</template>



<script>
// import './assets/js/fenye.js'

export default{

    data(){

        return { 
          //当前默认页
          currentPage:1,
          pagesize:6,
          
          students: [
              {ordersn: '1812918019YAP', buyer_username: '胡芸芸',compname:"YM-ADFAAFD"},
              {ordersn: '1812918019YAP', buyer_username: '胡芸芸',compname:"YM-ADFAAFD"},
              {ordersn: '1812918019YAP', buyer_username: '胡芸芸',compname:"YM-ADFAAFD"},
              {ordersn: '1812918019YAP', buyer_username: '胡芸芸',compname:"YM-ADFAAFD"}
          ], 

          product:[
              {
                "src":'../../assets/images/123.jpg',
                
              },
              {
                "src":"../../assets/images/123.jpg",
               
              },
              {
                "src":"../../assets/images/123.jpg",
                
              }
           ]

           
         
        }
    } ,

    methods:{
      //分页信息
      handleSizeChange: function (size) {
          this.pagesize = size;
         },
      handleCurrentChange: function(currentPage){
          this.currentPage = currentPage;
       },

    }


}

</script>


<style scoped>



.tables{
  width:100%;
  margin-top:15px;
  font-size: 12px;
}

.tables table {
    border-collapse: collapse;
  }
  .tables table .firsttitle {
    height: 35px;
    background-color: #F2F2F2;
    text-align: center;
  }
  .tables table .order-name {
    height: 34px;
    background-color: #F2F2F2;
    text-align: left;
  }
  .tables table .order-content {
    height: 160px;
    background-color: #ffffff;
    text-align: center;
  }
  .tables table td {
    border: 1px solid #CCCCCC;
  }

  .order-name td input span{
    float: right;
    margin-right: 50px;
  }


.order-name span{
  float:right;
  margin-right:40px;
}

.imginfan{
  margin-top:10px;
  margin-bottom: 15px;
}

.purnews{
  border-bottom: 1px solid #aaa;
  margin-left:10px;
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




.income_box{max-height: 581px;min-height:30px;_height:581px;height:581px;overflow: hidden;position: relative;margin:0 auto;width:795px;}
.colgroup1,.colgroup2{width:15%;}
.colgroup3,.colgroup4,.colgroup5,.colgroup6{width:14%;}
.income_table{position: absolute;top:0;left:0;}
.pptv_pages .pages{display:inline-block;top:20px;padding:4px 0;height:38px;overflow: hidden;position: relative;}
.pptv_pages .inner_pages{display:inline-block;position: absolute;top:12px;left:0;height: 38px;}


</style>