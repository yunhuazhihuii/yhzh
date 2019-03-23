<template>
  <div>  
    <!-- 导航栏 -->
    <div class="head">
      <div class="nav">
        <ul>
          <router-link to="/home">
            <li><a href="">首页</a></li>
          </router-link>
          <router-link to="/order/allorders">
            <li><a href="">交易管理</a></li>
          </router-link>
          <router-link to="/storehouse">
            <li><a href="">仓库管理</a></li>
          </router-link>
          <router-link to="/sys">
            <li><a href="">系统管理</a></li>
          </router-link>
          
        </ul>
          
        </ul>
      </div>
      <div class="user">  
      </div>
    </div>

    <div class="content">
      <div class="aside bj">
        <ul id="aside">
          <li class="other">店铺管理</li>
          <li><a href="">店铺维护</a></li>
          <li class="other">订单管理</li>
          <li class="active"><a href="">全部订单</a></li>
          <li><a href="">24小时到期订单</a></li>
          <li><a href="">待处理订单</a></li>
          <li><a href="">待收货订单</a></li>
          <li><a href="">运输中订单</a></li>
          <li><a href="">申请取消订单</a></li>
          <li><a href="">申请退款订单</a></li>
          <li><a href="">损耗订单</a></li>
          <li><a href="">已完成订单</a></li>
          <li><a href="">已取消订单</a></li>
          <li><a href="">采购退货订单</a></li>
          <li class="other">标签管理</li>
          <li><a href="">标志标签管理</a></li>
          <li><a href="">退货原因管理</a></li>
        </ul>
      </div>
      
      <div class="order-right">
        <div class="content-top">
        <div class="content-top-title">
          <p>当前位置：交易管理>全部订单</p>
        </div>
        <div class="content-top-ele">
          <div class="oneline">
            <span>异常标签</span>&nbsp
            <el-select v-model="value" size="small" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
            &nbsp&nbsp&nbsp
            <span>普通标签</span>&nbsp
            <el-select v-model="value" size="small" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </div>
          <div class="twoline">
            <span>店铺账号</span>&nbsp
            <el-select v-model="value" size="small" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
            &nbsp&nbsp&nbsp
            <span>订单时间</span>&nbsp
            <el-date-picker
              size="small"
              v-model="value1"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
            <span>订单状态</span>&nbsp
            <el-select v-model="value" size="small" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
            &nbsp&nbsp&nbsp
            <el-button class="serbutton" type="primary" size="small" icon="el-icon-search">筛选</el-button>
          </div>
        </div>
        <div class="content-top-bottom">
          <div class="content-top-bottom-one">
            <span class="sercht">搜索类型:</span>
            &nbsp&nbsp&nbsp
            <span>订单号</span>  &nbsp <span>运单号</span> &nbsp <span>收件人</span>  &nbsp <span>产品SKU</span> &nbsp <span>采购账号</span>&nbsp  <span>采购运单号</span>
          </div>
          <div class="content-top-bottom-two">
            <span class="serchc">搜索内容:</span>
            &nbsp&nbsp&nbsp
            <el-input class="elinput" v-model="input" size="small" placeholder="请输入内容"></el-input>
            &nbsp&nbsp&nbsp
            <el-button class="serbutton" type="primary" size="small" icon="el-icon-search">搜索</el-button>
          </div>
        </div>
      </div>

              <div class="content-botton">
        <el-row class="elrow">
            <el-button class="elbutton" size="small">同步订单</el-button>
            <el-button class="elbutton" size="small">批量导出</el-button>
            <el-button class="elbutton" size="small">批量打印</el-button>
            <el-button class="elbutton" size="small">异常标签</el-button>
            <el-button class="elbutton" size="small">普通标签</el-button>
          </el-row>
      </div>

      <div class="orderlist">
        <table width="1710" align="center" cellspacing="0" cellpadding="0">
          <tr class="firsttitle">
            <td width="110">商品图片</td>
            <td width="210">产品明细</td>
            <td width="140">订单日期</td>
            <td width="105">订单状态</td>
            <td width="75">订单金额</td>
            <td width="105">实际打款金额</td>
            <td width="105">物流商</td>
            <td width="65">快递费</td>
            <td width="65">实际运费</td>
            <td width="65">国家</td>
            <td width="125">面单打印时间</td>
            <td width="125">运单号</td>
            <td width="140">运单信息</td>
            <td width="140">采购运单信息</td>
            <td width="145">备注</td>
          </tr>
          <tr class="order-name">
            <td colspan="14"><input type="radio">1812918019YAP胡芸芸</td>
            <td>YM-ADFAAFD</td>
          </tr>
          <tr class="order-content">
            <td><img src="../assets/images/123.jpg" height="63" width="62" alt=""></td>
            <td>商品信息</td>
            <td>2018-12-29 18:01:22</td>
            <td>代发货</td>
            <td>756</td>
            <td>680</td>
            <td>虾皮店配-711</td>
            <td>13</td>
            <td>20</td>
            <td>china</td>
            <td>2019-1-1</td>
            <td>123456</td>
            <td>123456</td>
            <td>圆通速递：813659238294576655已签收，签收人凭取货码签收。</td>
            <td>客户要求换颜色</td>
          </tr>
          <tr class="order-name">
            <td colspan="14"><input type="radio">1812918019YAP胡芸芸</td>
            <td>YM-ADFAAFD</td>
          </tr>
          <tr class="order-content">
            <td><img src="../assets/images/123.jpg" height="63" width="62" alt=""></td>
            <td>商品信息</td>
            <td>2018-12-29 18:01:22</td>
            <td>代发货</td>
            <td>756</td>
            <td>680</td>
            <td>虾皮店配-711</td>
            <td>13</td>
            <td>20</td>
            <td>china</td>
            <td>2019-1-1</td>
            <td>123456</td>
            <td>123456</td>
            <td>圆通速递：813659238294576655已签收，签收人凭取货码签收。</td>
            <td>客户要求换颜色</td>
          </tr>
          <tr class="order-name">
            <td colspan="14"><input type="radio">1812918019YAP胡芸芸</td>
            <td>YM-ADFAAFD</td>
          </tr>
          <tr class="order-content">
            <td><img src="../assets/images/123.jpg" height="63" width="62" alt=""></td>
            <td>商品信息</td>
            <td>2018-12-29 18:01:22</td>
            <td>代发货</td>
            <td>756</td>
            <td>680</td>
            <td>虾皮店配-711</td>
            <td>13</td>
            <td>20</td>
            <td>china</td>
            <td>2019-1-1</td>
            <td>123456</td>
            <td>123456</td>
            <td>圆通速递：813659238294576655已签收，签收人凭取货码签收。</td>
            <td>客户要求换颜色</td>
          </tr>
        </table>
      </div>
      </div>
      
    </div>
  </div>
</template>

<script>
    export default {     
      data () {  /*业务逻辑里面定义的数据*/
        return {
          msg: '你好vue',

        }
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
  .aside {
    height: 680px;
    width: 180px;
    background-color: #fff;
    position:absolute;
    border: 1px solid #CCCCCC;
    top: 15px;
    left: 25px;
  }
  #aside {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
  }
  #aside li {
    flex-wrap: nowrap;
    list-style: none;
    width: 100%;
    height: 60px;
    /* background-color: pink; */
    border-bottom: 1px solid #CCCCCC;
  }
  #aside li a {
    text-decoration: none;
    color: #333333;
    display: block;
    width: 100%;
    height: 30px;
    /* height: 100%; */
    text-align: center;
    margin-top: 8px;
  }
  .other {
    font-weight: bold;
    background-color: #F2F2F2;
  }
  .active {
    background-color: #76A3A3;
  }
  #aside li a :hover {

  }
  .content-top {
    width: 1255px;
    height: 250px;
    border: 1px solid #CCCCCC;
    position: absolute;
    top: 15px;
    left: 220px;
  }

  .content-top-title {
    height: 40px;
    background-color: #F2F2F2;
    border-bottom: 1px solid #CCCCCC;
  }
  .content-top-title p {
    padding-top: 8px;
    font-size: 15px;
  }
  .content-top-ele {
    height: 120px;
    border-bottom: 1px solid #CCCCCC;
  }
  
  .oneline {
    padding-top: 15px;
    padding-left: 20px;
  }
  .twoline {
    padding-top: 15px;
    padding-left: 20px;
  }
  .content-top-bottom-one {
    padding-top: 15px;
    padding-left: 20px;
  }
  .content-top-bottom-two {
    padding-top: 12px;
    padding-left: 20px;
  }
  .elinput {
    width: 570px;
  }
  .serbutton {
    background-color: #76A3A3;
  }
  .content-botton {
    position: absolute;
    left: 220px;
    top: 270px;
    /* border: 1px solid #CCCCCC; */
    height: 65px;
    width: 1200px;
  }
  .elrow {
    margin-top: 16px;
  }
  .elbutton {
    background-color: #76A3A3;
    color: #fff;
    font-weight: bold;
  }
  
  .orderlist {
    width: 1710px;
    height: 500px;
    background-color: #99D9EA;
    position: absolute;
    top: 338px;
    left: 220px;
  }
  .orderlist table {
    border-collapse: collapse;
  }
  .orderlist table .firsttitle {
    height: 35px;
    background-color: #F2F2F2;
    text-align: center;
  }
  .orderlist table .order-name {
    height: 34px;
    background-color: #F2F2F2;
    text-align: left;
  }
  .orderlist table .order-content {
    height: 160px;
    background-color: #ffffff;
    text-align: center;
  }
  .orderlist table td {
    border: 1px solid #CCCCCC;
  }
</style>