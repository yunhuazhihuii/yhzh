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
        <div v-if="home_role.includes('公告栏')">
        <ul>
         <li v-for="(item,key) in noticelist"><a @click="look(key)" title="点击查看公告信息" href="javascript:;">{{item.bulletin_name}}</a> <span>{{item.bulletin_cretime}}</span></li>
        </ul>
      </div>
      </div>
      <el-dialog
          title="查看公告"
          :visible.sync="dialogVisiblel"
          width="40%"
          :before-close="handleClose">
          <el-form :model="form2">
            <el-form-item label="公告标题:" :label-width="formLabelWidth">
              <el-input v-model="form2.title" :disabled="true" autocomplete="off" size="small" style="width:50%"></el-input>
            </el-form-item>
            <el-form-item label="公告内容:" :label-width="formLabelWidth">
              <el-input type="textarea" v-model="form2.desc" :disabled="true" :rows="10" style="width:88%"></el-input>
            </el-form-item>
          </el-form>
        </el-dialog>
      <div class="div3 bj">
        <div class="div4">
          <p>常用工具</p>
          <template v-if="home_role.includes('常用工具')">
          <ul>
           <li v-for="(item,key) in toollist"><a @click="tourl(key)" title="点击进入" href="javascript:;">{{item.tool_name}}</a> <span>{{item.tool_cretime}}</span></li>
          </ul>
        </template>
        </div>
      </div>
      <div class="div5 bj">
        <div class="div6">
          <p>代办事项</p>

          <div class="dbsx">
            <div v-if="home_role.includes('待办事项')">
            <div>订单总数：{{totalOrder}}</div>
            <div>未处理订单：{{unprocessedOrder}}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  剩余时间<24小时：{{onedayOrder}} </div>
            <div>店铺状态异常：{{abnormalShop}}</div>
            </div>
          </div> 
        </div>
      </div>
      <div class="div7 bj">
        <div class="div8">
          <p>业绩</p>
        </div>
        <div v-if="home_role.includes('业绩')">
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
  import url from '../../config/sysAPI.config.js';
  import {getSession} from '../common/js/util';
  export default {   
    components:{
      'v-header':Header    
    },

    data () {  /*业务逻辑里面定义的数据*/
      return {

        home_role:'',
        userid: '',
        totalOrder: '',
        onedayOrder: '',
        unprocessedOrder: '',
        abnormalShop: '',
        dialogVisiblel:false,
        formLabelWidth: '120px',
        form2:
         {
            id: '',
            title: '',
            desc: ''
          },
        name: '',
        noticelist:[],
        toollist:[],
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
              data:["500","200","360","100","200","400","200","300","300","100"]
          }]
        }
      }
    },

    created(){
      
        this.home_role = getSession('home_role')
        console.log(this.home_role+'主页的权限')

      },

    methods:{
     handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },

      drawLine: function(){
        //基于准本好的DOM，初始化echarts实例
        let chartmainbar = this.$echarts.init(document.getElementById("chartmainbar"));
        //绘制图表
        chartmainbar.setOption(this.optionbar);
      },
      getnoticelist(){
        var api = url.getBulletinList;
        var _this = this
        Axios.post(api,
          {

          }
        )
        .then((response)=>{
          console.log(response.data);
          _this.noticelist=response.data;

        })
        .catch((error)=>{
          console.log(error);
        })
      },

      gettoollist(){
        var api = url.getToolList;
        var _this = this
        Axios.post(api,
          {

          }
        )
        .then((response)=>{
          console.log(response.data);
          _this.toollist=response.data;
        })
        .catch((error)=>{
          console.log(error);
        })
      },
      tourl(key){
        var that = this
        var url = that.toollist[key].tool_content
        window.open (url,'_blank')
      },
      look(key){
        this.dialogVisiblel = true
        console.log(this.noticelist[key])
        this.form2.title=this.noticelist[key].bulletin_name
        this.form2.desc=this.noticelist[key].bulletin_content

      },
      gettolist(){
        var api = url.todoList;
        var _this = this
        var id = _this.userid
        Axios.post(api,
          {
            userid:id
          }
        )
        .then((response)=>{
          console.log(response.data);
          // _this.toollist=response.data;
          _this.totalOrder = response.data.totalOrder
          _this.onedayOrder = response.data.onedayOrder
          _this.unprocessedOrder = response.data.unprocessedOrder
          _this.abnormalShop = response.data.abnormalShop
        })
        .catch((error)=>{
          console.log(error);
        })
      }
    },

    //生命周期函数，页面一加载就可以请求数据
    mounted(){
      //获取存储额user_id
      this.userid = getSession("user_id");
      this.drawLine()
      this.getnoticelist()
      this.gettoollist()
      this.gettolist()
    }
  }

</script>


<style scoped>
#home{
  height:100%;
}
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
    position: relative;
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
    overflow: auto;
  }

  ul {
    list-style-type: none;
    margin-top: 8px;
    margin-left: 14px;
    margin-right: 14px;
  }
   ul li {
    margin-top: 5px;
    width: 250px;
    list-style-position: inside;
    color: #76A3A3;
    border-bottom: 1px dashed #F2F2F2;
  }
   ul li a{
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

  .dbsx {
    margin-top: 20px;
    margin-left: 33px;
  }
  .dbsx div{
    margin-top: 20px;
    font-size: 16px;
    color: #333333
  }
</style>
