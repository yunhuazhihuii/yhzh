<template>
    <!-- 包裹列表 -->
    <div id="packagenews"> 

      <v-packagenews></v-packagenews>
 
    </div>

</template>

<script>

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
    
  }
  
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

.right div button, .right .zhouzhuan{
  float: right;
  padding: 5px;
  border-radius: 5px;
  margin: 5px;
}



</style>