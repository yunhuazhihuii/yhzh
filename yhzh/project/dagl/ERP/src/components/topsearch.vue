<template>

  <!-- 货物筛选搜索 -->
  <div class="searchDiv">

    <div class="searchDiv-title">
          <p>当前位置：{{dqwz}}</p>
    </div>

    <div class="searchDiv-ele">
      <div class="oneline">
        <span>异常标签</span>&nbsp;
        <el-select v-model="value1" size="small" placeholder="请选择">
          <el-option
            v-for="item1 in options1"
            :key="item1.value"
            :label="item1.label"
            :value="item1.value">
          </el-option>
        </el-select>
        &nbsp;&nbsp;&nbsp;

        <span>普通标签</span>&nbsp;

        <el-select v-model="value2" size="small" placeholder="请选择">
          <el-option
            v-for="item2 in options2"
            :key="item2.value"
            :label="item2.label"
            :value="item2.value">
          </el-option>
        </el-select>
        &nbsp;&nbsp;&nbsp;

        <span>店铺账号</span>&nbsp;

        <el-select v-model="value3" size="small" placeholder="请选择">
            <el-option
              v-for="item3 in options3"
              :key="item3.value"
              :label="item3.label"
              :value="item3.value">
            </el-option>
        </el-select>
      </div>

      <div class="oneline">

        <span>订单状态</span>&nbsp;

        <el-select v-model="value4" size="small" placeholder="请选择">
            <el-option
              v-for="item4 in options4"
              :key="item4.value"
              :label="item4.label"
              :value="item4.value">
            </el-option>
        </el-select>
        &nbsp;&nbsp;&nbsp;

        <span>订单时间 从</span>&nbsp;

        <el-date-picker
          size="small" 
          v-model="form.createTime1"
          type="datetime"
          placeholder="选择日期时间">
        </el-date-picker>

        <span>到</span>&nbsp;
        <el-date-picker
          size="small"
          v-model="form.createTime2"
          type="datetime"
          placeholder="选择日期时间">
        </el-date-picker>

        <el-button class="serbutton" type="primary" size="small" icon="el-icon-search" 
            @click="qryBtn()" >筛选</el-button>
      </div>
    </div>

    <div>
      <div class="searchDiv-exact-type">
        <span>搜索类型:</span> &nbsp;&nbsp;&nbsp;
        <span>订单号</span>  &nbsp; 
        <span>运单号</span> &nbsp; 
        <span>收件人</span>  &nbsp; 
        <span>产品SKU</span> &nbsp; 
        <span>采购账号</span>&nbsp;  
        <span>采购运单号</span>
      </div>
      <div class="searchDiv-exact-val">
        <span>搜索内容:</span>&nbsp;&nbsp;&nbsp;
        <el-input class="exactinput" size="small" v-model="form.searchCode" placeholder="请输入内容"></el-input>&nbsp;&nbsp;&nbsp;
        <el-button class="serbutton" type="primary" size="small" icon="el-icon-search"
            @click="searchCodeBtn()">搜索</el-button>
      </div>
    </div>
      
  </div>
</template>

<script>
import Axios from 'axios';
  import { getOrderList } from '../service/getData.js';
  import util from '../common/js/util';
  import {getSession} from '../common/js/util';

  export default{
    props: ['tableData','dqwz','orderStatus'],
    data(){
        return {
          form:{
            userid: "", //店铺id
            // orderStatus: "READY_TO_SHIP", // 订单状态
            createTime1: "", //订单时间 从
            createTime2: "", //订单时间 到
            searchType: "1", //精确搜索类型 1订单号,2运单号,3收件人,4产品SKU,5采购账号,6采购运单号
            searchCode: "", //精确搜索代码
            pageIndex: "1", //页码
            pageCount: "50" //每页显示条数
          },
          tableData1:[],
          data1: '',
          data2: '',
          searchVal:'', //默认输入为空
          value1: '',
          value2: '',
          value3: '',
          value4: '',
          selected: '',

          listtitle:[
            {searchNO:"订单号" },
            {searchNO:"运单号" },
            {searchNO:"收件人" },
            {searchNO:"产品SKU" },
            {searchNO:"采购账号" },
            {searchNO:"采购运单号" },
          ],
          
          options1: [
            {
              value: '选项1',
              label: '价格出错'
            }, 
            {
              value: '选项2',
              label: '客人需要收据'
            }, 
            {
              value: '选项3',
              label: '缺货'
            }, 
            {
              value: '选项4',
              label: '客人要求改尺寸'
            }, 
            {
              value: '选项5',
              label: '客人要求改颜色'
            },
            {
              value: '选项6',
              label: '联系不上客户'
            }
          
          ],
          
            

          options2:[
            {
              value: '选项1',
              label: '正常'
            }, 
            {
              value: '选项2',
              label: '寄顺丰'
            }, 
            {
              value: '选项3',
              label: '超材'
            }, 
            {
              value: '选项4',
              label: '已购买'
            }
          
          ],

          options3:[
           

          ],
          options4:[
            {
              value: '选项1',
              label: '待发货'
            }, 
            {
              value: '选项2',
              label: '已发货'
            }, 
            {
              value: '选项3',
              label: '已取消'
            }, 
            {
              value: '选项2-2',
              label: '已完成'
            } 
            
          ]
         
        }

    },
    created(){
        var that = this;
        //从登陆session中获取店铺id
        that.form.shop_id = getSession("shop_id");
    },

    mounted(){
        // var that = this;
        //从登陆session中获取店铺id
        this.userid = getSession("user_id");
        // console.log(this.userid+'444')
        // this.options3 = getSession('shop_id')
        this.getUserShop()
        // console.log(this.options3+"-----shopid数组------")
    },

    methods:{

      
      

      /*查询订单信息*/
       qryOrderList(shopId,createTime1,createTime2,orderStatus,searchType,searchCode,pageIndex,pageCount){
          var that = this;
          getOrderList(shopId,createTime1,createTime2,orderStatus,searchType,searchCode,pageIndex,pageCount,res=>{
            console.log("获取订单列表",res.data);
            that.tableData = res.data;
        });
      },

      /*多个查询条件按钮*/
      qryBtn(){
        var that = this;
        let shopId = that.form.shop_id;//店铺id
        let createTime1 = that.form.createTime1;//订单时间 从
        let createTime2 = that.form.createTime2;//订单时间 到
        let orderStatus = that.orderStatus;//订单状态
        let searchType = "";//精确搜索类型
        let searchCode = "";//精确搜索代码
        let pageIndex = that.form.pageIndex;//页码
        let pageCount = that.form.pageCount; //每页显示条数

       
        /*that.$options.methods.getData(shopId,createTime1,createTime2,orderStatus,searchType,searchCode,pageIndex,pageCount).bind(this)();*/
        that.$options.methods.getData.bind(this)(shopId,createTime1,createTime2,orderStatus,searchType,searchCode,pageIndex,pageCount);
      },

      /*按搜索内容查询按钮*/
      
      searchCodeBtn(){
        var that = this;
        let shopId = that.form.shop_id;//店铺id
        let createTime1 = "";//订单时间 从
        let createTime2 = "";//订单时间 到
        let orderStatus = that.orderStatus;//订单状态
        let searchType = that.form.searchType;//精确搜索类型
        let searchCode = that.form.searchCode;//精确搜索代码
        let pageIndex = that.form.pageIndex;//页码
        let pageCount = that.form.pageCount; //每页显示条数
       
        that.$options.methods.getData.bind(this)(shopId,createTime1,createTime2,orderStatus,searchType,searchCode,pageIndex,pageCount);
      },
      
    },

    computed:{

       
    }
}

</script>


<style scoped>
  .searchDiv{
    width: 1255px;
    height: 250px;
    border: 1px solid #CCCCCC;
    position: absolute;
    top: 0;
    left: 0;
  }
  .searchDiv-title {
    height: 40px;
    background-color: #F2F2F2;
    border-bottom: 1px solid #CCCCCC;
  }
  .searchDiv-title p {
    padding-top: 8px;
    font-size: 15px;
  }
  .searchDiv-ele {
    height: 120px;
    border-bottom: 1px solid #CCCCCC;
  }
  .oneline {
    padding-top: 15px;
    padding-left: 20px;
  }
  .serbutton {
    background-color: #76A3A3;
  }
  .searchDiv-exact-type {
    padding-top: 15px;
    padding-left: 20px;
  }
  .searchDiv-exact-val {
    padding-top: 12px;
    padding-left: 20px;
  }
  .exactinput {
    width: 570px;
  }

</style>