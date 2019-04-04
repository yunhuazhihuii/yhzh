<template>
    <!-- 已完成 -->
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

                dqwz: "交易管理>已完成", //当前位置
                orderStatus: "COMPLETED"  //订单状态： 已完成        
               
            }
        }       
    }

</script>

<style scoped>

</style>