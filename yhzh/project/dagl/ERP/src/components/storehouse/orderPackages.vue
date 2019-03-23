<template>
    <!-- 包裹列表 -->
    <div id="packagenews"> 

      <div class="pcktextdiv">

        <span class="pcktext">该包裹对应
          <!-- 订单个数（数量） -->
          <span class="numtext">{{orderShipnoRef.length}}</span>个订单,其中

          <template v-for="(order,index) in orderShipnoRef">

            订单{{index+1}}包含<span class="numtext">{{order.packages.length}}</span>个包裹
            <template v-if="index+1 < orderShipnoRef.length">
              ,
            </template>

          </template>
        </span>
      </div>

      <template v-for="(order,index) in orderShipnoRef">
          <div>
            <div class="left">
              <img :src="order.items[0].images[0].src" class="bigimg">
            </div>

            <div class="right">

              <p class="ordernum">订单{{index+1}}</p>

              <span class="pcktext">该订单包含 

                <span class="numtext"> {{order.packages.length}} </span>个包裹 


                <!-- 如果包裹数大于1且 面单打印时间为空 则显示 -->
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

              <!-- 订单产品循环 -->
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
              
              <!-- 包裹循环，“包裹到达顺序”不为空才需要显示 -->
              <template v-for="(pkg,pkgindex) in order.packages">

                <template v-if="pkg.reciorder != ''">

                  <p>包裹{{pkg.reciorder}}：{{pkg.shipping_carrier}}&nbsp;&nbsp;{{pkg.shippingno}}
                    

                    <!-- 对于包裹周转筐号不为空的 -->
                    <template v-if="pkg.bgzzkh != ''">
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;该包裹已扫描，存入周转筐：
                        <span class="numtext">{{pkg.bgzzkh}}</span>
                    </template>

                    <!-- 对于包裹周转筐号为空,且物流单号为当前查询输入的物流单号-->
                    <template v-if="pkg.bgzzkh == '' && pkg.shippingno == qry_shippingno">

                      <!-- 若包裹到达顺序等于包裹总数，说明订单的包裹已经到齐，可以打印面单了 -->
                      <template v-if="pkg.reciorder == order.packages.length">
                        <el-button type="primary" size="mini" @click="printBillBtn(order.ordersn,order.airway_bill)" class="btn_dymd">打印面单</el-button>
                      </template>


                      <!-- 否则说明包裹未到齐，需要输入包裹周转筐 -->
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
            
            <hr style="height:2px;border:none;border-top:1px solid #555555; clear: both;" />
          </div>

      </template>

      
    </div>

</template>

<script>

import Axios from 'axios';
import {submitBgzzkh,printBill} from '../../service/getData.js';
import {getSession} from '../../common/js/util';

export default {

  props: ['qry_shippingno','orderShipnoRef','tabledata2'],
  data() {
    return {
      bgzzkh: "" ,//输入包裹周转筐号(即使是不同的订单都要输入周转筐号，但只有一个包裹，周转筐号一样的吧？ 但保存是要各自保存还是只保存一次后台自动处理好？)
      tabledata2:[]
    }
  },
  methods: {

   

    submitBgzz(){
               
            // 4.1.根据周转筐号查询所有对应订单信息    shippingno   
            var api='http://192.168.1.187:8888/api/submitBgzzkh';
          
            Axios.post(api,{
              "ordersn":"19012109163H0E9",
              "shippingno":"111",  //物流单号    
              "bgzzkh":"sfsfs"  //包裹周转筐号
  
            }).then((response)=>{
                // this.list=response.data;
                console.log( 'response2==>', response)  //控制台检验输出api请求的数据
            }).catch((error)=>{
                console.log("请求数据失败==》", error);
            })
    },


    


    /**
    *保存包裹所在周转筐号
    */
    saveBgzzkhBtn(ordersn,shippingno,bgzzkh){
      var that = this;
      //调用数据库接口保存周转筐号
      submitBgzzkh(ordersn,shippingno,bgzzkh,res=>{
        console.log("保存包裹所在周转筐号",res.data);
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
    */
    printBillBtn(ordersn,airway_bill){
      var that = this;
      ////从登陆session中获取用户id
      var userid = getSession("userid");
      //打印pdf功能，待补充-----------------

      //数据库保存打印时间与打印人
      printBill(ordersn,userid,res=>{
        console.log("打印面单",res.data);

        /*that.$message({
          message: '打印面单成功!',
          type: 'success'
        });*/
      });
    },

    
  },

  mounted(){
    // this.qryOrderByShip();  
    this.submitBgzz();
    // this.printBill()

  },


};

</script>

<style scoped>

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