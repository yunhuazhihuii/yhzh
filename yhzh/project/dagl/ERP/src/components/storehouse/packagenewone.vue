<template>
    <!-- 包裹列表 -->
    <div id="packagenews"> 

      <v-packagenews></v-packagenews>

      
    </div>

</template>

<script>

import Axios from 'axios';

import package_news from './package_news.vue';

export default {

  components:{
    'v-packagenews':package_news
    
  },

  data() {
    return {
      
    };
  },
  methods: {

    // 获取produce列表
      getpackagenewsList(){
        var api='http://192.168.1.187：8080/api/getPendingOrders';
       //用箭头函数
        Axios.get(api).then((response)=>{
            this.packagenews=response.data.result;
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