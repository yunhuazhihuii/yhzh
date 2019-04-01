<template>
  <div>
  <!-- 场景创建显示 -->
  <div class="bg" v-if="screneShow"></div>
  <transition name="el-zoom-in-center">
   <div v-if="screneShow" style="position:fixed;z-index:2000;background:#fff;padding:20px;height:580px;top:10px;">
    <el-form ref="form" :model="form" label-width="80px">
      <!-- 关闭按钮 -->
      <i class="el-icon-close" @click="sceneClose"></i>

      <!-- 是否启用 -->
      <el-form-item label="是否启用">
          <el-checkbox v-model="form.checked"></el-checkbox>
      </el-form-item>


      <!-- 日期选择 -->
      <el-form-item label="选择日期">
           <el-select
            v-model="DateValue"
            multiple
            collapse-tags
            placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
          </el-select>

            <!-- 已经选择的时间 -->
            <el-dropdown trigger="click" style="float: right;">
            <span class="el-dropdown-link">
              已选时间<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-if="form.sureTime.length<=0">数据为空</el-dropdown-item>
              <div v-for="time in form.sureTime">
                <el-dropdown-item>{{time.fromtime}}-{{time.totime}}
                  <a href="#" style="color:red" title="删除" @click="delTime(time.fromtime)" >&nbsp;&nbsp;&nbsp;x</a>
              </el-dropdown-item>
              </div>
            </el-dropdown-menu>
          </el-dropdown>  
        </el-form-item>

        <!-- 选择开始结束时间 -->
       <el-form-item label="选择时间" style="float:left">
           <el-time-picker 
            is-range
            v-model="TimeValue"
            range-separator="至"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            placeholder="选择时间范围">
          </el-time-picker>
          <el-button type="primary" @click="TimeSure">确定</el-button>
        </el-form-item>  


        <!-- 选择灯数据 -->
       <!--  <div  class="bgTransfer"> -->
          <el-transfer v-model="value1" :titles="['已选', '未选']" :data="tableData"></el-transfer>
          
       <!--  </div> -->
        <!--表单标题和按钮 -->
        <el-input v-model="form.schedule.schedulename" placeholder="请输入标题" style="width: 60%"></el-input>
        <el-button type="primary" @click="screneSure">确定</el-button>
      </el-form>
    </div>
    </transition>

    <div>{{floor}}层照明</div>
    <div  class="itemList" style="position:relative">
      <el-tabs v-model="activeName">
        <el-tab-pane label="回路控制" name="first">
            <el-table v-loading="loading" :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border
            style="width: 640px">
            <el-table-column
              fixed
              prop="equipmentId"
              label="设备编号"
              width="120" height="100%">
            </el-table-column>
            <el-table-column
              label="设备名称" prop="equipment"
              width="140">
            </el-table-column>
              <el-table-column
              label="运行状态"
              width="80">
              <template scope="scope">
                   <i class="iconfont icon-light" v-if="scope.row.code == 0" >&#xe623;</i>
                   <i class="iconfont icon-light" v-if="scope.row.code == 1" style="color:red">&#xe623;</i>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              width="160">
              <template scope="scope">
                <el-button v-if="autoCtroll=='日程控制'?disabled=true:disabled=false"
                  size="small"  disabled
                  @click="open(scope.$index+pagesize*(currentPage-1))">开启</el-button>
                <el-button
                  size="small" v-if="autoCtroll=='日程控制'?disabled=true:disabled=false"
                  disabled
                  @click="close(scope.$index+pagesize*(currentPage-1))">关闭</el-button>
                  <el-button v-if="autoCtroll=='手动控制'"
                  size="small" 
                  @click="open(scope.$index+pagesize*(currentPage-1))">开启</el-button>
                <el-button
                  size="small" v-if="autoCtroll=='手动控制'"
                  @click="close(scope.$index+pagesize*(currentPage-1))">关闭</el-button>
              </template>
            </el-table-column>
             <el-table-column label="修改回路">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="danger"
                  @click="updataInfo(scope.$index+pagesize*(currentPage-1))">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!-- 分页页码 -->
           <el-pagination style="margin-top:16px;"
             background
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[6, 8, 12]"
              :page-size="pagesize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="tableData.length">
          </el-pagination>
       </el-tab-pane>

        <el-tab-pane label="场景控制" name="second">
          <!-- 创建，删除场景 -->
          <div style="margin-bottom: 20px">
            <el-button type="primary" @click="screneToggle"
             v-if="admin=='admin'||admin=='照明系统'">创建场景</el-button>
             <!-- 没有权限 -->
             <span v-else>您无权限进行日程控制</span>
             <!-- 删除场景按钮 -->
             <el-button type="primary" @click="DeleteScrene"  v-show="admin=='admin'||admin=='照明系统'"
              v-if="SceneClose?delOrCancle='取消删除':delOrCancle='删除场景'">{{delOrCancle}}</el-button>
          </div>
          <el-row>
            <!-- 控制全部灯开关 -->
             <el-col :span="6" v-for="(scene,index) in sceneCtroll">
              <el-card :body-style="{ padding: '0px' }">
                <div style="padding: 14px;position:relative">
                  <span>{{scene.schedule.schedulename}}</span>
                  <!-- 删除图标 -->
                  <i class="el-icon-error" v-if="SceneClose" @click="RemoveScene(scene,index)"
                    style="color: red;position:absolute;right: 4px;top:4px"></i>
                  <div class="bottom clearfix">
                     日程：<time class="time" v-for="data in scene.DateValue">
                            星期{{data.weekid}}、
                           </time>
                          <span v-show="!scene.checked" style="color:red;display：block;border:1px solid red">停用</span>    
                    <el-button type="text" class="button" @click="allControl(scene,index)">修改</el-button>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-tab-pane>

        <!-- 雨天模式 -->
        <el-tab-pane label="雨天模式" name="three">

          <el-button type="primary" @click="createYtBtn">新增自定义区域</el-button>

          <el-dialog title="自定义区域" :visible.sync="dialogFormVisible">

            <el-form ref="form_yt" :model="form_yt">
              
              <el-transfer
                filterable
                :titles="['已选', '未选']"
                filter-placeholder="雨天模式照明设备名称"
                v-model="value2"
                :data="tableData">
              </el-transfer>
              <!-- <div slot="footer" class="dialog-footer"> -->
                <el-input v-model="form_yt.Area.AreaName" placeholder="请输入标题" style="width: 60%"></el-input>
                <el-button type="primary" @click="yt_btn">确 定</el-button>
              <!-- </div> -->
            </el-form>

          </el-dialog>


          <!-- 雨天模式：表格 -->
          <el-table :data="tableData_yt.slice((currentPage-1)*pagesize,currentPage*pagesize)" style="width:570px" >
            <!-- prop中定义要按照form_yt的格式来，因为tableData_yt中每个元素的格式为form_yt 2018-9-5 -->
            <el-table-column                   
              prop="Area.AreaName"  
              label="区域名称"
              align="center"                    
              width="100">
            </el-table-column>

            <el-table-column
              label="操作"
              align="center"
              width="250">
              <template slot-scope="scope">
                <!-- 绑定区域开启与关闭事件 chenms 2018-9-5 -->
                <el-button type="primary" @click="openArea(scope.$index)">开启</el-button>
                <el-button type="primary" @click="closeArea(scope.$index)">关闭</el-button>
              </template>
            </el-table-column>
            
            <el-table-column
              label="编辑"
              align="center"
              width="200">
              <template slot-scope="scope">
                  <!-- 绑定修改事件 2018-9-5 -->
                  <el-button type="primary" @click="showAreaModify(scope.$index+pagesize*(currentPage-1))" round>修改</el-button>

                  <el-button type="danger" @click.native.prevent="deleteRow_yt(scope.$index+pagesize*(currentPage-1))" round>删除</el-button>                 
              </template>

            </el-table-column>

          </el-table>

          <!-- 分页页码 -->
           <el-pagination style="margin-top:16px;"
             background
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[6, 8, 12]"
              :page-size="pagesize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="tableData_yt.length">
            </el-pagination>
                
        </el-tab-pane>



      </el-tabs>
      <!-- 全部控制按钮 -->
      <div style="position: absolute;top: -6px;left: 300px">
          <el-button type="primary" 
            v-if="autoCtroll=='日程控制'" disabled
            disabled @click="AllOpen">全开</el-button>
          <el-button type="primary" 
            v-if="autoCtroll=='手动控制'"
            @click="AllOpen">全开</el-button>
              <el-button type="primary" 
            v-if="autoCtroll=='日程控制'" disabled
            disabled @click="AllClose">全关</el-button>
          <el-button type="primary"
            v-if="autoCtroll=='手动控制'"
            @click="AllClose">全关</el-button>
          <!-- 全程控和触发控 -->
          <el-radio v-model="autoCtroll" label="日程控制" border size="medium" 
          v-if="admin=='admin'||admin=='照明系统'">日程控制</el-radio>
          <el-radio v-model="autoCtroll"  label="手动控制" border size="medium"
            v-if="admin=='admin'||admin=='照明系统'">手动控制</el-radio>
      </div>
  </div>
  </div>
</template>

<script>
import { getSession} from '../../common/js/util';
import { pagereflectInfo,updataInfo } from '../../service/getData';
import { systemMenu } from '../../common/js/data.js';
import { zmApi,Allcalendar,deleteInfo,admindarea,areadele,addarea,queryArea,areacontrol} from '../../service/javaApi.js';
 export default {
    data() {

      return {
     
        value2: [],        
        dialogFormVisible: false,

        // 雨天模式：表格数据
        tableData_yt: [],
        /*tableData_yt: [ 
          {
              light:[{pointname:1},{pointname:3}],
              Area:{
                UserAreaID: "",
                AreaName: "哈哈1",
                system: "ZM",
                Floor: ''
              }
          },
          {
              light:[{pointname:0},{pointname:4}],
              Area:{
                UserAreaID: "",
                AreaName: "复古风格2",
                system: "ZM",
                Floor: ''
              }
          }
        ],*/

        //2018-9-5  修改区域的索引号
        areaIndex:0,


        // 用户权限
         admin:'',
        // 手动控制
         autoCtroll:'日程控制',
         options: [],
        socket:'',
        //创建场景
        screneShow:false,
        // 删除场景文字切换
        delOrCancle:'删除场景',
        //删除场景图标
        SceneClose:false,
        //楼层
        floor:"0",  //原来是0，chenms
        pageCode:'',
        // 原始楼层数据
        TableData:[],
        //楼层数据
        tableData: [],
        loading: true,

        //全开数据(前端向服务器发送数据)
        openAll:{"type": "clientSend","pointer": ["clientId1","clientId2"],
                "content": {
                   "sourcetype": "html5",
                   "data": [],
                "callback": 21
          }},
         //全关数据
        closeAll:{"type": "clientSend","pointer": ["clientId1","clientId2"],
                "content": {
                   "sourcetype": "html5",
                   "data": [],
                "callback": 21
          }},


        //场景数据
        sceneCtroll:[],
        //修改日程的序列值
        screnIndex:0,
        // 时间选择
        TimeValue: [new Date(2018, 9, 10, 8, 40), new Date(2018, 9, 10, 9, 40)],
        sureTime:[],
         //日期数据
        DateValue:[],
        value1: [],

        //日程表
        form:{
            //日期数据
            DateValue:[],
            // 选择时间
            sureTime:[],
            checked:true,    //是否启用默认勾上
            light:[],
            
            // 其它信息  scheduleid：
            schedule:{
              scheduleid:'',
              schedulename:'',
              system:'ZM',
              floor:this.floor
             },
        },

        // 雨天模式
        form_yt: {  
          light:[],  
          Area:{
            UserAreaID: "",
            AreaName: "",    //prop="Area.AreaName"  
            system: "ZM",
            Floor: this.floor
          }
         },



        
        //tab
        activeName: 'first',
        
        //当前默认页
        currentPage:1,
        pagesize:6,

        //发送node的全控数据
        in_outpoint:[],
      }
    },
    beforeRouteLeave (to, from, next) {
        this.socket.disconnect();
        next()
      },
    created(){
      var that=this;
      this.options = systemMenu;
      // 获取用户权限
      that.admin=JSON.parse(getSession('UserInfo')).admin

      //websocket连接
      //this.socket = io.connect('http://172.16.8.2:8090');
      this.socket = io.connect('http://192.168.0.114:8090');
      //楼层
      this.floor=this.$route.params.id+"";//数字转字符串，因为日程表适配器那边报转换错误 chenms
      //传对应的楼层编号
      this.pageCode=this.$route.params.pageCode;
      // 原始楼层数据
      this.TableData=JSON.parse(this.$route.params.floor)
      //传对应的楼层信息
      this.tableData=JSON.parse(this.$route.params.floor);
      //加载成功后隐藏加载图标
      this.loading=false;

      //获取映射表信息
      pagereflectInfo(this.pageCode,res=>{
        console.log( res.data)
        //获取映射值 添加映射变量到json对象
         this.tableData.forEach((item,index) => {
           //输入点的名称
           that.$set(item,'InSport', res.data[index].ReflectVar);
           that.$set(item,'key', index);
           //设备的名称
           that.$set(item,'label', item.equipment );
        });

        //nodejs发送订阅信息
        this.in_outpoint={type:"rtsub",pointer:["clientId1","clientId2"],content:{data:{clientId:"H000000002",subscribeTopic:"控制器",fileCategory:"文件类型",subscribeSubitem:[]},callback:46}};

        //控制全开全关数据  （将订阅后发送过来的数据进行处理）
        res.data.forEach((data,index) => {
          if(data.LocalVar <1000){
              that.in_outpoint.content.data.subscribeSubitem.push(data.ReflectVar);
          }
          //控制全开全关数据
          if(data.VarType ==3){
             this.closeAll.content.data.push({name:data.ReflectVar,value:'3'})
          }
          if(data.VarType ==4){
             this.openAll.content.data.push({name:data.ReflectVar,value:'4'})
          }
        });
        // 发送适配器映射点
         console.log("发送适配器映射点")
         console.log(this.in_outpoint);
         this.socket.emit('clientToS', this.in_outpoint);

         //nodejs发送订阅信息结束
         var message=that.socket.id;

          //socket事件  接收实时数据 socketid为事件名
         this.socket.on(message, MESSAAGE => {
            // 适配器发送来的数据
            console.log("适配器发送来的数据")
            console.log(MESSAAGE);
            let data=MESSAAGE.content.data;
          // //遍历对象
          data.forEach(items => {
            that.tableData.forEach((item,index) => {
             //如果相等就改变状态
              if(items.name==item.InSport){
                  item.code=items.value;
              }
            })
           });
        })
      });

      //获取该页面下所有日程表
      Allcalendar('ZM.'+that.floor,res=>{
          console.log("获取日程表");
          console.log(res.all);
          that.sceneCtroll = res.all;
      });
      
      //查询：调用接口获取该页面下所有自定义区域信息 2018-9-5 chenms
      queryArea('ZM.'+that.floor,res=>{
          console.log("获取自定义区域信息");
          console.log(res.all);
          that.tableData_yt = res.all;
      });

      
    },
    methods: {
      // 修改灯的名称
      updataInfo(index){
          var that=this;
          this.$prompt('请输入修改名称', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              inputPattern:  /\S/,
              inputErrorMessage: '表单格式不正确'
            }).then(({ value }) => {
              //赋值
              this.tableData[index].equipment=value;
              this.TableData[index].equipment=value;
              // 提交修改数据
              updataInfo(that.floor,JSON.stringify(that.TableData),res=>{
                  that.$message({
                    type: 'success',
                    message: '修改成功 '
                  });
             })
            }).catch(() => {      
        });
      },
      //全部打开
      AllOpen(){
          //发送数据
        this.socket.emit('clientToS', this.closeAll);
        this.$message('请稍等片刻');
      },
      //全部关闭
      AllClose(){
          //发送数据
        this.socket.emit('clientToS', this.openAll);
        this.$message('请稍等片刻');
      },
      //分页信息
      handleSizeChange: function (size) {
          this.pagesize = size;
         },
      handleCurrentChange: function(currentPage){
          this.currentPage = currentPage;
       },
      //开启

      open(index){
        var that=this;
        var MESSAAGE1= {"type": "clientSend","pointer": ["clientId1","clientId2"],
                 "content": {
                     "sourcetype": "html5",
                     "data": [
                         {
                           "name": that.tableData[index].InSport,
                           "value": 1
                         }],
                     "callback": 21
                            }
                       }
        //发送数据
        this.socket.emit('clientToS', MESSAAGE1);
         console.log("deng")
        console.log(MESSAAGE1)
      },
      
      //关闭
       close(index){
        var that=this;
        
        //console.log( this.floor[0].tableData[index])
         var MESSAAGE1= {"type": "clientSend","pointer": [
                 "clientId1","clientId2" ],
                 "content": {
                     "sourcetype": "html5",
                     "data": [
                         {
                             "name": that.tableData[index].InSport,
                             "value": 0
                         }],
                     "callback": 21
                                }
                        }
        //发送数据
        this.socket.emit('clientToS', MESSAAGE1);
        console.log("deng")
        console.log(MESSAAGE1)
      },


      //添加：创建场景成功按钮
      screneSure(){
        var that=this;
        // 如果日程表存在就创建，否则修改
        if(that.form.schedule.scheduleid==''){
            if(that.DateValue==''){
               that.$message({
                    type: 'error',
                    message: '请选择日期 '
                  });
               }else if(that.form.sureTime==''){
                 that.$message({
                    type: 'error',
                    message: '请选择时间段 '
                  }); 
               }else if(that.value1.length <= 0){
                that.$message({
                    type: 'error',
                    message: '回路选择不能为空 '
                }); 
               }else if(that.form.schedule.schedulename==''){
                 that.$message({
                    type: 'error',
                    message: '请选择标题 '
                  }); 
               }else{
                   // 转换日期选择格式
                   that.DateValue.forEach(item=>{
                       that.form.DateValue.push({weekid:item});
                     });
                  //追加选择灯的数据到form
                   that.value1.forEach(item =>{
                      that.form.light.push(that.tableData[item]);
                   });
                  // 创建场景接口
                  console.log("创建日程表",that.form);
                  zmApi(that.form,res=>{
                     //存储创建日程id
                     that.form.schedule.scheduleid=res;
                     // 添加到日程列表
                     that.sceneCtroll.push(that.form);
                     that.screneShow=false; 
                     console.log("创建日程表成功！");
                    }) ;
                }
               }else{  
                //修改日程表
                  that.form.DateValue=[];
                  that.form.light=[];
                     // 转换日期选择格式
                   that.DateValue.forEach(item=>{
                      that.form.DateValue.push({weekid:item});
                     });
                    //追加选择灯的数据到form
                     that.value1.forEach(item =>{
                        that.form.light.push(that.tableData[item]);
                     });

                  // 修改场景接口
                 zmApi(this.form,res=>{
                   // 添加到日程列表
                  that.sceneCtroll[that.screnIndex]=that.form;
                   that.screneShow=false;  
                   
                  });
                 console.log("修改日程表成功");
                 //console.log(that.form);
                  }
              },


       // 确定按钮--添加：创建雨天模式
      yt_btn(){

        var that = this;
        // 如果日程表存在就创建，否则修改
        if(that.form_yt.Area.UserAreaID==''){
            if(that.value2.length <= 0){
                that.$message({
                    type: 'error',
                    message: '请至少选择一个回路 '
                });
            }else if(that.form_yt.Area.AreaName==''){
                that.$message({
                    type: 'error',
                    message: '请选择标题 '
                });
            }else{

                that.form_yt.light=[];
               
                //追加选择灯的数据到form_yt
                that.value2.forEach(item =>{
                    that.form_yt.light.push(that.tableData[item]);
                });
                console.log("创建自定义区域接口",that.form_yt);
      
                // 添加创建自定义区域接口
                addarea(that.form_yt,res=>{

                   //存储自定义区域id
                   that.form_yt.Area.UserAreaID=res;
                   
                   // 添加到自定义区域列表
                   //2018-9-5 tableData_yt里面的元素不能只是中文名，必须包含所有要素，要不修改与删除就拿不到值
                   //that.tableData_yt.push({AreaName:that.form_yt.Area.AreaName});

                   that.tableData_yt.push(that.form_yt);
                   console.log("新增自定义区域接口成功==>",res);

                   that.dialogFormVisible = false;
                   console.log("新增form_yt",that.form_yt)
                });
                
            }
        }else{

          //修改自定义区域 注意测试 chenms 2018-9-5
          that.form_yt.light=[];

          //重新设置form_yt中选择灯的数据
          that.value2.forEach(item =>{
             that.form_yt.light.push(that.tableData[item]);
          });
          //修改自定义区域
          addarea(this.form_yt,res=>{
             //更新自定义区域表格
             that.tableData_yt[that.areaIndex] = that.form_yt;
             that.dialogFormVisible = false; //关闭窗体
             console.log("修改自定义区域成功");
          });
        }

      },


      // 删除场景按钮
      DeleteScrene(){
        this.SceneClose=!this.SceneClose;
      },
      //删除场景
      RemoveScene(scren,index){
        var that=this;
        var bDel = confirm("请确定是否删除日程表[" + scren.schedule.schedulename + "]?");
        if(bDel == true){
          deleteInfo(scren.schedule.scheduleid,res=>{
            that.sceneCtroll.splice(index, 1);
            console.log("删除日程表完成");
          });
        }
      },
      //创建场景
      screneToggle(){
        var that=this;
         //日期数据
         this.DateValue=[],
         this.sureTime=[],
         this.value1=[],
         this.form={
            //日期数据
            DateValue:[],
            sureTime:[],
            checked:true,
            light:[],
            schedule:{
              scheduleid:'',
              schedulename:'',
              system:'ZM',
              floor:that.floor,
             },

        },
          this.screneShow=true
      },
      //关闭场景
      sceneClose(){
          this.screneShow=false;
      },

      //修改日程表
      allControl(scene,index){
        console.log("该日程场景数据",scene);
        var that=this;
         //先清空日期
         that.DateValue=[];
         that.value1=[];
         // 修改日程的序列号
         this.screnIndex=index;
         this.screneShow=true;
        // 把请求数据的日期添加到创建表中
         scene.DateValue.forEach(time=>{
             that.DateValue.push(time.weekid);
         });
          scene.light.forEach(item=>{
            let key;
            if('pointname' in item){
              key = item.pointname;
            }else{  
              key = item.key;
            }
              that.value1.push(key);
         });
        console.log("该日程场景已选中回路", that.value1);
         
         that.form=scene;
      },
      // 添加时间段按钮
      TimeSure(){
        var that=this;
        var repeat=false;
        console.log(that.TimeValue[0].toTimeString())
         console.log(this.sureTime)
        //禁止重复添加
        this.sureTime.forEach(time => {
            var fromtime=time.fromtime.valueOf();
            var totime=time.totime.valueOf();
           // 对比时间 如果在已选择区域报错
           if(fromtime<=that.TimeValue[0].valueOf()&&that.TimeValue[0].valueOf()<=totime||fromtime<=that.TimeValue[1].valueOf()&&that.TimeValue[1].valueOf()<=totime){
                that.$message({
                 message: '时间段重复',
                 type: 'error'
              })
              repeat=true;
          }
        })
        // 如果不重复添加
        if(repeat==false){
          // 添加时间到数组
          that.sureTime.push({fromtime:that.TimeValue[0],totime:that.TimeValue[1]})
          // 转换24小时时间类型
          var fromtime=that.TimeValue[0].getHours()+':'+that.TimeValue[0].getMinutes()+':'+that.TimeValue[0].getSeconds();
          var totime=that.TimeValue[1].getHours()+':'+that.TimeValue[1].getMinutes()+':'+that.TimeValue[1].getSeconds();
          // 添加时间到表单
          that.form.sureTime.push({fromtime:fromtime,totime:totime})  
          //添加成功提示
          that.$message({
             message: '时间添加成功',
             type: 'success'
          });
        }
        // console.log( this.form)    
      }, 

      //删除时间段 chenms 2018-8-11
      delTime(fromtime){
        for(var j = 0; j < this.form.sureTime.length; j++) {
          if(this.form.sureTime[j].fromtime == fromtime){
            this.sureTime.splice(j,1);
            this.form.sureTime.splice(j,1);
            this.$message({
               message: '删除时间段成功',
               type: 'success'
            });
          break;
          }
        }
      },

      // 新增自定义区域按钮--创建雨天模式 chenms 2018-9-1
       createYtBtn(){
          var that = this;
          that.form_yt = {
              light:[],
              Area:{
                UserAreaID: "",
                AreaName: "",
                system: "ZM",
                Floor: that.floor
              }
          };
          that.dialogFormVisible = true;
       },

      /**弹出修改自定义区域框事件 chenms 2018-9-5
      * @para index: 表格行号索引，从0开始
      */
      showAreaModify(index){
        var that = this;
        //console.log("弹出修改自定义区域框事件index",index);
        console.log("该自定义区域数据",that.tableData_yt[index]);
        that.areaIndex=index; //用于修改确认事件使用
        //先清空选中的照明回路数据
        that.value2 = [];
        //重新赋值value2

        console.log("tableData_yt[index]",that.tableData_yt[index]);

        that.tableData_yt[index].light.forEach(item=>{

            console.log("item==>",item);
            let key;
            if('pointname' in item){ //记得提醒邓城查询区域时要返回pointname这个字段
              key = item.pointname;
            }else{  
              key = item.key;
            }
              that.value2.push(key);

         });

        console.log("该自定义区域已选中回路", that.value2);

        console.log("form_yt==>",that.form_yt);

        // console.log("form_yt==>",that.form_yt);
        //整个行数据赋值给form_yt

        that.form_yt = that.tableData_yt[index];
        that.dialogFormVisible = true;
      },

      /**雨天模式--删除 2018-9-5优化
      *@para index: 表格行号索引，从0开始
      */
      deleteRow_yt(index) {
        var that = this;

        //确认是否删除自定义区域的弹出框
        this.$confirm('请确认是否删除自定义区域' + that.tableData_yt[index].Area.AreaName + '?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {

            let id = that.tableData_yt[index].Area.UserAreaID;    

            //调用删除接口
            areadele(id,res=>{
              // 操作成功删除数据
              // if(res.code==1){ //2018-9-5 请和邓城确认删除成功是否有返回code=1，若没有就不用写这个判断
                 //界面上清除该条数据
              that.tableData_yt.splice(index,1);   //index：作为参数
              // }
              this.$message({
                  type: 'success',
                  message: '删除成功!'
              });
            });
        }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
        });
      },

      /**雨天模式--开自定义区域中所有的灯 2018-9-5
      *@para index: 表格行号索引，从0开始
      */
      openArea(index) {
        var that = this;
        //console.log("开自定义区域中所有的灯index",index);
        let id = that.tableData_yt[index].Area.UserAreaID;
        //开关区域接口
        areacontrol(id + ".1",res=>{
           //完善下，弹出开启成功 2018-9-5
           this.$message({
              type: 'info',
              message: '开启成功'
            });
        });
      },
      /**雨天模式--关闭自定义区域中所有的灯 2018-9-5
      *@para index: 表格行号索引，从0开始
      */
      closeArea(index) {
        var that = this;
        let id = that.tableData_yt[index].Area.UserAreaID;
        //开关区域接口
        areacontrol(id + ".0",res=>{
           //完善下，弹出关闭成功 2018-9-5
           this.$message({
              type: 'info',
              message: '关闭成功'
            });
        });
      }

      },
    }

</script>
<style scoped >
 @import '~scss_vars';
    .el-col-6{
     padding-right: 3%;
     margin-top: 12px
    }
    .time{line-height: 24px}  
    
   
   
</style>