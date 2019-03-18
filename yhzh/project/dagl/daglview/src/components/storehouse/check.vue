<template>
  
  <!-- 拆件核对包裹 -->
  <div class="pkgright">
    
    <div id="allorder"> 

      <v-packageList></v-packageList>

      <p class="position">当前位置：{{position}} </p>

      <div class="inputquest">

        <span>扫描/输入： 
          <input class="sinput" type="" v-model="qry_shippingno">
          <el-button class="serbutton" type="primary" size="small" icon="el-icon-search" 
            @click="qryByShipnoBtn()" >查询</el-button>
        </span>



      </div>

    </div>
    <div>  

      <iframe style="display:none" id="printIframe"></iframe>

    
      <!-- <v-orderPackages :qry_shippingno="qry_shippingno" :orderShipnoRef="orderShipnoRef"></v-orderPackages> -->

      <!-- 包裹信息  -->
      <div id="packagenews"> 

        <!-- 包裹标题说明栏 -->
        <div class="pcktextdiv">

          <span class="pcktext">该包裹对应
            
            <span class="numtext">{{orderShipnoRef.length}}</span>个订单,其中

            <template v-for="(order,index) in orderShipnoRef">

              订单{{index+1}}包含
              <span class="numtext">{{order.packages.length}}</span>个包裹


              <template v-if="index+1 < orderShipnoRef.length">
                ,
              </template>

            </template>
          </span>
        </div>

        <!-- 包裹详细信息 -->
        <template v-for="(order,index) in orderShipnoRef">

            <div>

              <!-- 包裹大图片  -->
              <div class="left">

                <img :src="order.items[0].images[0].src" class="bigimg">

              </div>

              <!-- 包裹信息 -->
              <div class="right">

                <!-- 订单编号 -->
                <p class="ordernum">订单{{index+1}}</p>

                <!-- 订单中的包裹数量 -->
                <span class="pcktext">该订单包含 

                  <span class="numtext"> {{order.packages.length}} </span>个包裹 

                  <template v-if="order.packages.length > 1 && order.md_print_time==''">
                    ,已扫描入周转筐包裹数：{{order.bgzzk_count}}
                  </template>

                </span>


                <p>客服：{{order.items[0].username}}</p>
                <p>订单号：{{order.ordersn}}  </p>
                <p>剩余发货天数：{{order.days_to_ship}}</p>
                <p>预估实收金额：{{order.escrow_amount}}</p>
                <p>收件人：{{order.buyer_username}}</p>
                <p>运单号：{{order.tracking_no}}</p>
                <p>店铺名：{{order.shop_name}}</p>
                <p>公司名：{{order.compname}}</p>

               <!-- 产品详细 -->
                <template v-for="(item,itemindex) in order.items">
                  <div class="productdiv">
                    <div>
                      <span class="productlabel">产品{{itemindex+1}}： </span>
                      <!-- 产品图片循环 -->
                      <template v-for="(img,imgindex) in item.images">
                       
                        <img :src="img.src" width="50" height="50">
                      </template>
                    </div>
                    <p>数量： <span class="numtext">{{item.variation_quantity_purchased}}</span></p>
                  </div>
                </template>
                
               <!-- 包裹信息 -->
                <template v-for="(pkg,pkgindex) in order.packages">
                  <template v-if="pkg.reciorder != ''">
                    <p>包裹{{pkg.reciorder}}：{{pkg.shipping_carrier}}&nbsp;&nbsp;{{pkg.shippingno}}
                      

                      
                      <template v-if="pkg.bgzzkh == '' && pkg.shippingno == qry_shippingno">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;该包裹已扫描，存入周转筐：
                          <span class="numtext">{{pkg.bgzzkh}}</span>
                      </template>
                      
                      <template  v-if="pkg.bgzzkh != ''">
                        
                        <template v-if="pkg.reciorder == order.packages.length">
                          <el-button type="primary" size="mini" @click="printBillBtn(order.ordersn,order.airway_bill)" class="btn_dymd">打印面单</el-button>
                        </template>

                        <template v-else>
                          <el-input size="small" class="bgzzkh_inp" v-model="bgzzkh"></el-input>
                          <el-button type="primary" size="mini" @click="saveBgzzkhBtn(order.ordersn,pkg.shippingno,bgzzkh)">输入包裹周转筐号</el-button>
                        </template>

                      </template>
                    </p>
                  </template>
                </template>

                <p><span class="numtext">备注：</span>{{order.items[0].purchase_note}}</p>
              </div>

              <!-- 打印面单 -->
              
              <!-- 分割线 -->
              <hr style="height:2px;border:none;border-top:1px solid #555555; clear: both;" />
            </div>

        </template>

        
      </div>

    </div>
  </div>

</template>


<script>
import Axios from 'axios';

import packageList from './packageList.vue';
import orderPackages from './orderPackages.vue';
import {qryOrderByShipno} from '../../service/getData.js';

import {getSession} from '../../common/js/util';
import {submitBgzzkh,printBill} from '../../service/getData.js';

// import $ from 'expose?$!jquery';

 

// $(document).ready(function(){
    
//     $("#printIframe").load(function(){//等待iframe加载完成后再执行doPrint.每次iframe设置src之后都会重新执行这部分代码。
//         doPrint();
//     });
   
// });


//点击打印按钮，触发事件
// function printPDF(){
//     var src = $("#printIframe").attr("src");
//     if(!src){//当src为空，即第一次加载时才赋值，如果是需要动态生成的话，那么条件要稍稍变化一下
//                 $("#printIframe").attr("src","./attachment/Images.pdf");//暂时静态PDF文件
//     }else
//         $("#printIframe")[0].contentWindow.print();//不知为什么在IE中一直无法打印文件
// }

export default{

      components:{
        'v-packageList':packageList,
        'v-orderPackages':orderPackages

      },

      data(){
          return {
            bgzzkh: "" ,//输入包裹周转筐号(即使是不同的订单都要输入周转筐号，但只有一个包裹，周转筐号一样的吧？ 但保存是要各自保存还是只保存一次后台自动处理好？)
            // tabledata2:[],

            position:"仓库管理>拆件核对包裹",
            qry_shippingno:"", //物流单号查询条件

             //查询到的订单包裹信息
            orderShipnoRef: [ ]
          }
      },

      methods: {

        // qryOrderByShipno(){
               
        //     // 4.1.根据物流单号查询所有对应订单信息    shippingno   116.62.112.118
        //    var api='http://116.62.112.118:8888/api/qryOrderByShipno';
          
        //     Axios.post(api,{
        //       shippingno:shippingno //物流单号
        //     }).then((response)=>{
        //         this.orderShipnoRef=response.data;
        //         console.log( 'response11==>', response)  
        //     }).catch((error)=>{
        //         console.log("请求数据失败==》", error);
        //     })
        // },

        // submitBgzz(){
               
        //     // 4.1.根据submitBgzzkh信息    shippingno   
        //     var api='http://116.62.112.118:8888/api/submitBgzzkh';
          
        //     Axios.post(api,{
        //       "ordersn":"19012109163H0E9",
        //       "shippingno":"111",  //物流单号    
        //       "bgzzkh":"sfsfs"  //包裹周转筐号
        //     }).then((response)=>{
        //         // this.list=response.data;
        //         console.log( 'response2==>', response)  //控制台检验输出api请求的数据
        //     }).catch((error)=>{
        //         console.log("请求数据失败==》", error);
        //     })
        // },

        // printBill(){
               
        //     // 4.1.根据物流单号查询所有对应订单信息   ordersn   
        //     var api='http://116.62.112.118:8888/api/printBill';
          
        //     Axios.post(api,{
        //       ordersn:ordersn,
        //       userid:userid
        //     }).then((response)=>{
        //         // this.list=response.data;
        //         console.log( 'response3==>', response)  
        //     }).catch((error)=>{
        //         console.log("请求数据失败==》", error);
        //     })
        // },


        /**
        *根据物流单号查询所有对应订单信息
        */
        qryByShipnoBtn(){
        
          var that = this;
          // console.log("22");

          console.log("物流单号查询==>", that.qry_shippingno);

          //调用数据库接口查询订单包裹信息
          qryOrderByShipno(that.qry_shippingno,res=>{

            console.log("根据物流单号查询所有对应订单信息==>",res);

            that.orderShipnoRef = res;   //res和res.data：输出时有可能是调试的关键点，输出的结果会不同。 

          });
        },

        /**
        *保存包裹所在周转筐号
        */
        saveBgzzkhBtn(ordersn,shippingno,bgzzkh){
          var that = this;

          //调用数据库接口保存周转筐号
          submitBgzzkh(ordersn,shippingno,bgzzkh,res=>{

            console.log("保存包裹所在周转筐号==》",res);

            console.log("ordersn==》",ordersn);

            console.log("shippingno==》",shippingno);

            console.log("bgzzkh==》",bgzzkh);

            that.$message({
              message: '保存成功!',
              type: 'success'
            });
          });
        },

        /**
        *打印面单
        *@param ordersn 订单号
        *@param airway_bill 面单pdf地址
        *@userid   打印人
        */
        printBillBtn(ordersn,airway_bill){

          var that = this;

          ////从登陆session中获取用户id
          var userid = getSession("userid");

          console.log("userid==>",userid);

          //打印pdf功能，待补充-----------------

          //数据库保存打印时间与
          printBill(ordersn,userid,res=>{

            console.log("打印面单==》",res);

            console.log("ordersn==>",ordersn);

            console.log("airway_bill==>",airway_bill);

            // 打印pdf功能 
            window.location.href = airway_bill;

            

            that.$message({
              message: '打印面单成功!',
              type: 'success'
            });
          });
        },
      
      },

      mounted(){  /*生命周期函数*/
          // this.qryOrderByShipno();  
          // this.submitBgzz();
          // this.printBill()
      }     

}

</script>

<style scoped>

.pkgright{
  margin-left:50px;
}

#allorder{
  border: 1px solid;
  margin-top:10px;
}

.pkgright .position{
    background-color:#F2F2F2;
    padding: 10px;

}

.inputquest{
  margin: 30px;
}

.inputquest span{
  font-size: 20px;
}

.infa{
  width: 100%;
  height: 600px;
  border: 1px solid blue;
  margin-top: 30px;
}


.inputquest .sinput{
  width: 200px;
  height: 25px;
  line-height: 25px;
}

.inputquest .btn{
  padding: 5px 20px;
  border-radius: 10px;
}

/*查询按钮*/
.serbutton {
    background-color: #76A3A3;
}



.left{
  width: 500px;
  height: 500px;
  float: left;
  text-align: center;
  position: relative; 
}

.left img {
  position: absolute;
  top:50%; 
  left:50%;
  transform: translate(-50%,-50%);
}

.right{
  width: 650px;
  float: left;
  margin-left: 60px;
  margin-top: 10px;
}

.right h2{
  text-align: center;
  margin-bottom: 30px;
}

.right p,.right div{
  margin-top: 5px;
}

/*.right div span{
    position: relative;
    top: -30px;

}*/

/*产品label*/
.productlabel{
  position: relative;
  top: -30px;
}

.right div p{
  margin-left: 40px;
}

.right div button{
  float: right;
}
/*订单1*/
.ordernum{
  color: red;
}
/*订单与包裹数量说明文字所在div*/
.pcktextdiv{
  padding-top: 20px;
}
/*订单与包裹数量说明文字*/
.pcktext{
  white-space: nowrap;
  font-size: 20px;
  color: #333333;
}
/*订单与包裹数量文字样式*/
.numtext{
  color: red;
}
/*订单左边大图片*/
.bigimg{
  border-width: 0px;
  width: 500px;
  height: 500px;
}

/*各个产品筐*/
.productdiv{

}
/*产品中图片*/
.productdiv img{
  margin-right: 7px;
}
/*输入周转筐号*/
.bgzzkh_inp{
  width: 70px;
  margin-left: 30px;
  margin-right: 10px;
}
/*面单打印按钮*/
.btn_dymd{
  margin-left: 60px;
}





</style>