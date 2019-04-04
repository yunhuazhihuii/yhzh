<template>
    <!-- 已打印订单 -->
  <div class="mr">
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
                dqwz: "交易管理>已打印订单", //当前位置
                orderStatus: "TO_CONFIRM_RECEIVE"  //订单状态： 已打印订单        
            }
        }       
    }

</script>

<style scoped>
</style>