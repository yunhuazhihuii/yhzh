<template>
    <!-- 已取消订单 -->
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

                dqwz: "交易管理>已取消订单", //当前位置
                orderStatus: "TO_BE_RECEIVED"  //订单状态： 已取消订单        
               
            }
        }       
    }

</script>

<style scoped>
.top{
  border: 1px solid; 
}

#allorder p{
    
    padding: 10px;
}

el-container{
    position: absolute;
    width: 100%;
    height: 100%;
}

.reposition{
    background-color:#aaa;
}

.psearch,.insearch{
    
    width: 100%;
    border: 1px solid; 
    padding-top:15px;  
}

.top input,.top span{
    padding: 10px;
    margin: 15px;
}


button{
    padding: 6px 30px;
    border-radius: 10px;
}

.tables{
  width:100%;
}

.tables table {
    border-collapse: collapse;
  }
  .tables table .firsttitle {
    height: 35px;
    background-color: #F2F2F2;
    text-align: center;
  }
  .tables table .order-name {
    height: 34px;
    background-color: #F2F2F2;
    text-align: left;
  }
  .tables table .order-content {
    height: 160px;
    background-color: #ffffff;
    text-align: center;
  }
  .tables table td {
    border: 1px solid #CCCCCC;
  }


</style>