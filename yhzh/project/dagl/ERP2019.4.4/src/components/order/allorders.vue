<template>
  <!-- 全部订单 -->
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
            dqwz: "交易管理>全部订单", //当前位置
            orderStatus: "all"  //订单状态： 全部订单
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