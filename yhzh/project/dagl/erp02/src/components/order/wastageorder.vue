<template>
  <!-- 损耗订单 -->
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
            dqwz: "交易管理>损耗订单", //当前位置
            orderStatus: "READY_TO_SHIP"  //订单状态： 损耗订单
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