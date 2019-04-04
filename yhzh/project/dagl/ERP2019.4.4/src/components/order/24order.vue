<template>
  <!-- 24小时到期订单 -->
  <div>
    <v-comOrder :dqwz="dqwz" :orderStatus="orderStatus"></v-comOrder>
  </div>
</template>


<script>
    import comOrder from './com_order.vue';

    export default{
      components:{
        'v-comOrder':comOrder
        
             
      },

      data(){
          return {
            dqwz: "交易管理>24小时到期订单", //当前位置
            orderStatus: "ONEDAY"  //订单状态： 24小时到期订单
          }
      },

      created(){

      },

      methods: {

      }

    }

</script>

<style scoped>
 
</style>