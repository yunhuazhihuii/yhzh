import Vue from 'vue'
import axios from 'axios'



// {"all":[{"holidate":2018-06-05,"holidesc":asd},{"holidate":2018-06-06,"holidesc":asdf}]}

// 创建日程修改表信息 //http://localhost:8080  http://172.16.8.2:8080
export const zmApi = (form,res) => {
    axios.post('http://localhost:8080/YHZHADAPTER/rest/holiday/addsche',{
      form:form,
    })
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};


// 获取日程表信息
export const Allcalendar = (id,res) => {
    axios.get('http://localhost:8080/YHZHADAPTER/rest/holiday/sche/'+id)
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};

//删除一条记录
export const deleteInfo = (id,res) => {
    axios.get('http://localhost:8080/YHZHADAPTER/rest/holiday/schedele/'+id)
	    .then(function (response){ 
	       res(response.data)
	    })
	    .catch(function (error){ 
	    	console.log(error);  
	    });
};

/**
*雨天模式-新建及修改区域接口：addarea/json
*/
export const addarea = (form_yt,res) => {
    axios.post('http://localhost:8080/YHZHADAPTER/rest/holiday/addarea/',{
      form_yt:form_yt,
    })
    .then(function (response) {
      res(response.data);
    })
    .catch(function (error) {
      console.log(error);
    });
};

/**
*雨天模式-获取自定义区域信息接口：
*@param id:格式如[ZM.楼层号]
*/
export const queryArea = (id,res) => {
    axios.get('http://localhost:8080/YHZHADAPTER/rest/holiday/queryArea/'+id)
    .then(function (response) {
      res(response.data)
    })
    .catch(function (error) {
      console.log(error);
    });
};

/**
*雨天模式-删除自定义区域信息接口：
*@param id:自定义区域编号
*/
export const areadele = (id,res) => {
    axios.get('http://localhost:8080/YHZHADAPTER/rest/holiday/areadele/'+id)
      .then(function (response){ 
         res(response.data); 
      })
      .catch(function (error){ 
        console.log(error);  
      });
};

/**
*雨天模式-控制（开关）自定义区域信息接口：
*@param id:自定义区域编号.1或0
*/
export const areacontrol = (id,res) => {
    axios.get('http://localhost:8080/YHZHADAPTER/rest/holiday/areacontrol/'+id)
      .then(function (response){ 
         res(response.data); 
      })
      .catch(function (error){ 
        console.log(error);  
      });
};

//http://localhost:8080/JustForZM/rest/holiday/holi

// 添加节假日
export const addDayData = (form,res) => {
    axios.post('http://localhost:8080/JustForZM/rest/holiday/addHoli/',{
       form:form,     
    })
    .then(function (response) {
      res(response.data);
    })
    .catch(function (error) {
      console.log(error);
    });
};



// 删除节假日
export const deleteHDData = (id,res) => {
    axios.get('http://localhost:8080/JustForZM/rest/holiday/holidele/'+id)
      .then(function (response){ 
         res(response.data); 
      })
      .catch(function (error){ 
        console.log(error);  
      });
};

// 查询节假日
export const queryDay = (id,res) => {
    axios.get('http://localhost:8080/JustForZM/rest/holiday/holi')
      .then(function (response){ 
         res(response.data); 
      })
      .catch(function (error){ 
        console.log(error);  
      });
};

//http://172.16.8.2:8080
