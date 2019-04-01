import Vue from 'vue'
import axios from 'axios'

// 全局配置post请求
axios.defaults.headers['Content-Type'] = 'application/json'
axios.defaults.baseURL='http://localhost:9000/'

//判断用户是否存在用户
export const exist = (name,res) => {
  axios.post('/existeUser',{
    name:name
  }).then(function(response){
       res(response.data)
   }).catch(function(res){
     console.log("出错了")
   })
};
// 获取所有用户
export const getAllUser = (res) => {
  axios.post('/allUser').then(function(response){
       res(response.data)
   }).catch(function(res){
     console.log("出错了")
   })
};

// 获取登录信息 post请求
export const userLoginInfo = (loginUser,res) => {
  axios.post('/login', {
  	  user:loginUser,

   }).then(function(response){
       res(response.data)
   }).catch(function(res){
     console.log("出错了")
   })
};

// 获取信息 get请求  照明数据
export const getBaseInfo = (username,res) => {
	  axios.post('/get_zm',{ 

      // (Websocket连接--前端连接注册)
      type:"register", 
        content:{
          data:{
            userId:"U000000001",
            username:username,
            clientId:"H000000001",
            clientName:""
          },
          callback: 42
       }
    })
		.then(function (response) {
      res(response.data)
		})
		.catch(function (error) {
		  console.log(error);
		});
};
// 获取信息 照明映射信息
export const pagereflectInfo = (pageCode,res) => {
    axios.post('/get_reflect',{
      pageCode:pageCode,
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};
// 获取楼层空调信息
export const ktFl = (floorNum,subsystem,res) => {
    axios.post('/get_ktFl',{
      floorNum:floorNum,
      subsystem:subsystem
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};
// 获取空调映射信息
export const ktData = (devcode,res) => {
    axios.post('/get_kt',{
      devcode:devcode,
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};
// 获取所有空调映射信息
export const ktAllData = (subsystem,varType,res) => {
    axios.post('/get_allkt',{
      subsystem:subsystem,
      varType:varType
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};
//修改回路名称一条记录
export const updataInfo = (floor,tableData,res) => {
    axios.post("/updataValue",{
      tableData:tableData,
      floor:floor
    })
      .then(function (response) { 
         res(response.data)  
      })
      .catch(function (error){
         console.log(error);
       });
};
//修改权限一条记录
export const adminEdit = (form,res) => {
    axios.post("/adminInfo",{
      form:form,
    })
      .then(function (response) { 
         res(response.data)  
      })
      .catch(function (error){
         console.log(error);
       });
};
//删除用户权限一条记录
export const deleteUser = (id,res) => {
    axios.post("/deleteUser?id="+id)
      .then(function (response) { 
         res(response.data)  
      })
      .catch(function (error){
         console.log(error);
       });
};
//用户权限增加一条记录
export const addAdminUser = (form,res) => {
    axios.post("/addAdminUser",{
       form:form
     })
      .then(function (response) { 
         res(response.data) 
      })
      .catch(function (error){
         console.log(error);
       });
};



