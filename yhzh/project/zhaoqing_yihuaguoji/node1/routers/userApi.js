   var express = require('express'); 
   var nodeApi = require('../module/NodeApi');
   var bodyParser = require('body-parser');
   var router = express.Router();
   // 用户登录
  router.post('/login', function (req, res) {
       nodeApi.query("SELECT * FROM login WHERE name=? and password=?",
        [req.body.user.username,req.body.user.password],
        function(data){
          res.send({code: 1,data: data})
      });
     })
  
    // 用户登录
   router.post('/loginApp', function (req, res) {
      console.log(req.body.user.username)
       console.log(req.body.user.password)
       nodeApi.query("SELECT * FROM login WHERE name=? and password=?",
        [req.body.user.username,req.body.user.password],
        function(data){
          if(data.length>0)
            res.send({code: 1,data: data})
          else  res.send({code: 0})
      });
     })
   // 用户是否存在
  router.post('/existeUser', function (req, res) {
       nodeApi.query("SELECT * FROM login WHERE name=?",[req.body.name],
        function(data){
          if(data.length>0){
             res.send({code: 1})
          }else{
             res.send({code: 0})
          }
      });
     })
    // 增加用户
  router.post('/addAdminUser', function (req, res) {
    console.log('ll',req.body.form.name)
    nodeApi.query('INSERT INTO login(name,password,admin) VALUES(?,?,?)',
      [req.body.form.name,req.body.form.password,req.body.form.admin],
    function(){
        res.send({code: 1})
      });
  })
     // 删除用户
  router.post('/deleteUser', function (req, res) {
    console.log(req.query.id)
    nodeApi.query('DELETE FROM login WHERE name =?',[req.query.id,],
    function(){
        res.send({code: 1})
      });
  })

    // 修改用户权限
  router.post('/adminInfo', function (req, res) {
    nodeApi.query('update login SET name =?,password = ?,admin = ? WHERE id = ?',
      [req.body.form.name,req.body.form.password,req.body.form.admin,req.body.form.id,],
    function(){
        res.send({code: 1})
      });
  })
    // 查询所有用户
   router.post('/allUser', function (req, res) {
    nodeApi.query('select * from login',[],
    function(data){
        res.send({code: 1,data: data})
      });
  })
   module.exports = router