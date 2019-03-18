//调用restful接口客户端方法文件 chenms 2019-2-6
import Vue from 'vue'
import axios from 'axios'
import {viewConfig} from '../../config/yhzh_view_cfg.js'   //API接口


// 全局配置post请求
axios.defaults.headers['Content-Type'] = 'application/json'   //请求头，处理跨域
axios.defaults.baseURL=viewConfig.restUrl     //全局基础路径


// 获取订单列表 chenms 2019-2-6    可调
export const getOrderList = (shopId,createTime1,createTime2,orderStatus,labelid2,labelid1,searchType,searchCode,pageIndex,pageCount,res) => {
    axios.post('/getPendingOrders',{
      shopId: shopId,//店铺id
      createTime1: createTime1,//订单时间 从
      createTime2: createTime2,//订单时间 到
      orderStatus: orderStatus,//订单状态
      labelid2:labelid2, // 异常标签
      labelid1:labelid1, // 普通标签
      searchType: searchType,//精确搜索类型
      searchCode: searchCode,//精确搜索代码
      pageIndex: pageIndex,//页码
      pageCount: pageCount //每页显示条数
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log("getPendingOrders", error);
    });
};

// 获取字典列表 chenms 2019-2-7    可调
export const getDictInfo = (dictname,res) => {
    axios.post('/getDictInfo',{
      dictname:dictname //字典名称
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};

// 获取店铺列表 chenms 2019-2-7   可调
export const getShopList = (res) => {
    axios.post('/getShopList',{})
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};

// 获取采购账号列表 chenms 2019-2-7    可调
export const getPurinfo = (res) => {
    axios.post('/getPurinfo',{})
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};

//提交采购信息 chenms 2019-2-7   可调
export const submitPurchase = (ordersn,item_id,tb_code,tb_order_no,purchase_time,purchase_price,purchase_note,tbk_url,tbk_bl,bgname,shipping_carrier,shippingno,res) => {
    axios.post('/submitPurchase',{
    ordersn: ordersn,//订单号
	  item_id: item_id,//商品id
	  tb_code: tb_code,//采购账号
	  tb_order_no: tb_order_no,//淘宝订单号
	  purchase_time: purchase_time,//采购时间
	  purchase_price: purchase_price,//采购价格
	  purchase_note: purchase_note,//采购备注
	  tbk_url: tbk_url,//淘宝客链接
	  tbk_bl: tbk_bl,//淘宝客比例
	  bgname: bgname,//报关名
	  shipping_carrier: shipping_carrier,//物流公司
	  shippingno: shippingno//物流单号
    })
    .then(function (response) {
      console.log("123");
      res(response)   //response.data
    })
    .catch(function (error) {
      console.log("123456");
      console.log(error);
    });
};

//根据物流单号查询所有对应订单信息 chenms 2019-2-11   可调
export const qryOrderByShipno = (shippingno,res) => {

    axios.post('/qryOrderByShipno',{
      shippingno:shippingno    //物流单号
    })
    .then(function (response) {
      res(response.data);
      console.log("请求数据", response.data);
    })
    .catch(function (error) {
      console.log(error);
    });
};

//保存包裹所在周转筐号(只有1对多及多对多情况需要) chenms 2019-2-11
export const submitBgzzkh = (ordersn,shippingno,bgzzkh,res) => {
    axios.post('/submitBgzzkh',{
      ordersn: ordersn,//订单号
      shippingno: shippingno,//物流单号
      bgzzkh: bgzzkh//包裹周转筐号
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};

//打印面单申请 chenms 2019-2-11
export const printBill = (ordersn,userid,res) => {
    axios.post('/printBill',{
      ordersn: ordersn,//订单号
      userid: userid //面单打印人
    })
    .then(function (response) {
      console.log(ordersn)
      res(response.data)
    })
    .catch(function (error) {
      console.log("打印面单申请失败",error);
    });
};



// 获取登录信息 post请求
export const userLoginInfo = (userid,password,res) => {
  axios.post('/userLogin', {
      userid:userid,
      password:password

   }).then(function(response){
       res(response.data)
       console.log(response)
       console,log("请求成功了")
   }).catch(function(error){
     console.log(error)
   })
};