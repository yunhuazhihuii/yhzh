<template>
    <!-- 包裹列表 -->
    <div id="packagenews"> 

      <div v-for="news in packagenewsdata">
          <div class="left"><img src="../../assets/images/01.jpg"></div>


          <!-- v-for ="item in packagenewinfer" -->
          <div class="right">  
            <h2>该订单包含 1 个包裹</h2>   <!-- 订单包裹数量没有该字段吗？   -->
            <p>客服： </p>

            <p> 订单号：{{ news.ordersn}}  </p>

            <p>剩余发货天数： {{ news.days_to_ship}} </p>

            <p> 预估实收金额：</p>

            <p> 收件人：{{ news.names }}  </p>

            <p> 店铺名： {{ news.shop_name }} </p>

            <p> 公司名：{{news.compname }}   </p>

            <p> 运单号：{{ news.tracking_no}} </p>

            <p> 包裹：{{ news.shipping_carrier }}  <span></span>   {{news.shippingno }} </p>

            <div>
              <div>
                <span> 产品： </span>
                <img src="../../assets/images/01.jpg" width="50" height="50"> 

                <img src="../../assets/images/01.jpg" width="50" height="50"> 

                <img src="../../assets/images/01.jpg" width="50" height="50"> 
              </div>
              
              <!-- 购买数量   ？？ -->
              <p>数量： {{ news.variation_quantity_purchased}} </p> 
              <button>打印面单</button>
            </div>
            
            <!-- 这里是谁的备注？卖家note/买家message_to_seller -->
            <p> 备注：{{ news.note}}  </p>

            
          </div>
            

      </div>

      
    </div>

</template>

<script>

import Axios from 'axios';

export default {
  data() {
    return {
      packagenewsdata:[
        {
          ordersn:"12236587556 ",
          days_to_ship:"2-1 ",
          names:" AB",
          shop_name:"QAW ",
          compname:"QZSWA ",
          tracking_no:" 1254785821",
          shipping_carrier:"中通快递 ",
          shippingno:"8811234564789 ",
          variation_quantity_purchased:"11 ",
          note:"NO PRO "
        }
      ]
      
    };
  },
  methods: {

    // 获取produce列表
      getpackagenewsList(){
        var api='https://192.168.1.187';
       //用箭头函数
        Axios.get(api).then((response)=>{
            this.packagenewsdata=response.data.result;
            console.log( 'response1==>', response)  //控制台检验输出api请求的数据
        }).catch((error)=>{
            console.log(error);
        })
      }
    
  },

  mounted(){
    this.getpackagenewsList();
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

.right div span{
    position: relative;
    top: -30px;

}


.right div p{
  margin-left: 40px;
}

.right div button{
  float: right;
}



</style>