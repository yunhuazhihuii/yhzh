<template>
  <!-- 待收货订单 -->
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
            dqwz: "交易管理>待收货订单", //当前位置
            orderStatus: "TO_BE_RECEIVED"  //订单状态： 待收货
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