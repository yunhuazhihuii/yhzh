<template>
  <div id="home">  
    <!-- 导航栏 -->
    <v-header></v-header>

    <!-- 内容区域 -->
    <div class="content">
      <div class="div1 bj">
        <p>公告栏</p>
      </div>
      <div class="div2 bj">
        <ul>
         <li><a href="javascript:;">元旦假期安排</a> <span>2018-12-28</span></li>
         <li><a href="javascript:;">元旦安排</a> <span>2018-12-28</span></li>
         <li><a href="javascript:;">元旦假期安排哈哈</a> <span>2018-12-28</span></li>
        </ul>
      </div>
      <div class="div3 bj">
        <div class="div4">
          <p>常用软件</p>
        </div>
      </div>
      <div class="div5 bj">
        <div class="div6">
          <p>代办事项</p>
        </div>
      </div>
      <div class="div7 bj">
        <div class="div8">
          <p>业绩</p>
        </div>
        <div>
          <div class="iamge1" id="chartmainbar" style="width:900px; height:550px;"></div>
        </div>
      </div>
      <div class="div9 bj">
        <div class="div10">
          <p>常用操作</p>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import Axios from 'axios';
  import Header from './Header.vue';

  export default {   
    components:{
      'v-header':Header    
    },

    data () {  /*业务逻辑里面定义的数据*/
      return {
        msg: '你好vue',
        name: '',
        list:[],
        optionbar:{
          title:{
              text:'10天业绩树状图'
          },
          tooltip:{},
          legend:{
              data:['用户来源']
          },
          xAxis:{
              data:["12-14","12-15","12-16","12-17","12-18","12-19","12-20","12-21","12-22","12-23"]
          },
          yAxis:{

          },
          series:[{
              name:'订单金额',
              type:'bar', //设置图表主题
              data:[500,200,360,100,200,400,200,300,300,100]
          }]
        }
      }
    },

    methods:{

      drawLine: function(){
        //基于准本好的DOM，初始化echarts实例
        let chartmainbar = this.$echarts.init(document.getElementById("chartmainbar"));
        //绘制图表
        chartmainbar.setOption(this.optionbar);
      }
    },

      
    mounted(){
      //生命周期函数，页面一加载就可以请求数据
      
       this.drawLine()
    }
  }

</script>


<style scoped>
  .head {
    width: 100%;
    height: 60px;
    background-color: #76A3A3;
  }
  .nav {
    float: left;
    margin-left: 200px;
    margin-top: 30px;
  }
  .nav ul {
    overflow: hidden;
  }
  .nav li {
    float: left;
    margin: 0 20px;
    list-style: none;
  }
  .nav li a {
    color: #fff;
    text-decoration: none;
    font-weight: bold;
  }
  .nav li a:hover {
    color: red;
  }
  .user {
    float: right;
  }

  .content {
    position:absolute;
    width: 100%;
    height: 1000px;
  }

  .div1 {
    border-width:0px;
    background-color: #F2F2F2;
    position: absolute;
    left: 15px;
    top: 12px;
    width: 300px;
    height: 48px;
    
  }
  .div1 p {
    font-weight: 700;
    font-style: normal;
    margin: 0 auto;
    margin-left: 10px;
    line-height: 48px;
    font-size: 13px;
  }
  .div2 {
    border-width:0px;
    background-color: #fff;
    position: absolute;
    top: 60px;
    left: 15px;
    height: 170px;
    width: 300px;
  }

.div2 ul {
    list-style-type: none;
    margin-top: 8px;
    margin-left: 14px;
    margin-right: 14px;
  }
  .div2 ul li {
    margin-top: 5px;
    width: 250px;
    list-style-position: inside;
    color: #76A3A3;
    border-bottom: 1px dashed #F2F2F2;
  }
  .div2 ul li a{
    display: inline-block;
    width: 150px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    color: #76A3A3;
    text-decoration: none;
  }

  .div3 {
    position: absolute;
    top: 260px;
    left: 15px;
    width: 300px;
    height: 218px;
  }
  .div4 p {
    font-weight: 700;
    font-style: normal;
    margin: 0 auto;
    margin-left: 10px;
    line-height: 48px;
    font-size: 13px;
  }
  .div4 {
    background-color: #F2F2F2;
    position: absolute;
    top: 0px;
    left: 0px;
    height: 48px;
    width: 300px;

  }
  .div5 {
    position: absolute;
    top: 12px;
    left: 330px;
    height: 218px;
    width: 922px;

  }
  .div6 {
    position: absolute;
    background-color: #F2F2F2;
    top: 0px;
    left: 0px;
    height: 48px;
    width: 922px;

  }

  .div6 p {
    font-weight: 700;
    font-style: normal;
    margin: 0 auto;
    margin-left: 10px;
    line-height: 48px;
    font-size: 13px;
  }
  .div7 {
    position: absolute;
    top: 260px;
    left: 330px;
    height: 580px;
    width: 922px;

  }
  .div8 {
    position: absolute;
    background-color: #F2F2F2;
    top: 0px;
    left: 0px;
    height: 48px;
    width: 922px;
  }
  .div8 p {
    font-weight: 700;
    font-style: normal;
    margin: 0 auto;
    margin-left: 10px;
    line-height: 48px;
    font-size: 13px;
  }
  .iamge1 {
    position: absolute;
    left: 0px;
    top: 50px;
  }
  .div9 {
    position: absolute;
    top: 12px;
    left: 1266px;
    height: 580px;
    width: 300px;

  }
  .div10 {
    position: absolute;
    background-color: #F2F2F2;
    top: 0px;
    left: 0px;
    height: 48px;
    width: 300px;
  }
  .div10 p {
    font-weight: 700;
    font-style: normal;
    margin: 0 auto;
    margin-left: 10px;
    line-height: 48px;
    font-size: 13px;
  }

</style>