<template>
  <!-- 待处理订单 -->
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
            dqwz: "交易管理>待处理订单", //当前位置
            orderStatus: "READY_TO_SHIP"  //订单状态： 待处理订单
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