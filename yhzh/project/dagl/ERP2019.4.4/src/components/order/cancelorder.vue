<template>
    <!-- 申请取消订单 -->
  <div>
    <v-comOrder :dqwz="dqwz" :orderStatus="orderStatus"></v-comOrder>
  </div>

</template>


<script>
  import comOrder from '../order/com_order.vue';  
    export default{

      components:{
        'v-comOrder':comOrder    
      },

        data(){
            return {    

                dqwz: "交易管理>申请取消订单", //当前位置
                orderStatus: "IN_CANCEL"  //订单状态： 申请取消订单        
               
            }
        }       
    }

</script>

<style scoped>


</style>