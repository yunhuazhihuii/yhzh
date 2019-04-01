<template>
  <div>
      <!-- 创建，删除场景 -->
    <div :style="{top:tops}" class="but">
        <el-button type="primary" @click="screneToggle" v-show="news == '1'"
        v-if="admin=='admin'||admin=='楼宇系统'">创建场景</el-button>
        <el-button @click="showScrene" v-if="check == '1'">查看日程表</el-button>
    </div>
  <!-- 场景创建显示 -->
  <div class="bg" v-if="screneShow"></div>
  <transition name="el-zoom-in-center">
   <div v-if="screneShow" style="width:560px;position:fixed;z-index:2000;background:#fff;padding:20px;height:260px;top:80px;">
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
                <el-dropdown-item>{{time.fromtime}}-{{time.totime}}<a href="#"style="color:red" title="删除"
                  @click="delTime(time.fromtime)" >&nbsp;&nbsp;&nbsp;x</a>
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
      <!--表单标题和按钮 -->
      <el-input v-model="form.schedule.schedulename" placeholder="请输入标题" style="width: 60%"></el-input>
      <el-button type="primary" @click="screneSure">确定</el-button>
      </el-form>
    </div>
    </transition>
    <!--日程表展示 -->
  
     <el-dialog title="日程表展示" :visible.sync="show2">
        <el-button type="primary" @click="DeleteScrene" 
           style="position: absolute;right: 48px;top: 20px;"
          v-if="SceneClose?delOrCancle='取消删除':delOrCancle='删除场景'">{{delOrCancle}}</el-button>
        <el-card :body-style="{ padding: '0px' }" v-for="(scene,index) in sceneCtroll">
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
   </el-dialog>
  </div>
</template>

<script>
import { zmApi,Allcalendar,deleteInfo } from '../service/javaApi.js';
import { systemMenu } from '../common/js/data.js';
 export default {
   props: ['numFloor','KtData','admin','check','news','tops'],
    data() {
      return {
        show2: false,
        // 手动控制
         autoCtroll:'日程控制',
         options: [],
        //场景数据
        sceneCtroll:[],  //查看日程表的列表
        //创建场景
        screneShow:false,
        // 删除场景文字切换
        delOrCancle:'删除场景',
        //删除场景图标
        SceneClose:false,
        //修改日程的序列值
        screnIndex:0,
        // 时间选择
        TimeValue: [new Date(2018, 9, 10, 8, 40), new Date(2018, 9, 10, 9, 40)],
        sureTime:[],
         //日期数据
        DateValue:[],
        form:{
            //日期数据
            DateValue:[],  //即星期几、星期几
            // 选择时间
            sureTime:[],  
            checked:true,  //是否启用默认勾上
            light:[],   //点信息数据列表，对于空调、风机只有一条
            // 其它信息
            schedule:{
              scheduleid:'',
              schedulename:'',
              system:'',
              floor:this.numFloor.toString()
             }

        },
      }
    },
    created(){
      var that=this;
      console.log("系统权限==>",this.admin)
      
       this.options = systemMenu;

       console.log("传给日程表的设备信息KtData==>",this.KtData);
       
      Allcalendar(this.KtData.startBut.subsystem+'.'+that.numFloor + "." + this.KtData.startBut.ReflectVar,res=>{
        console.log("获取该页面所有日程表==>",res.all);
       
        that.sceneCtroll=res.all; //用于查看时显示所有日程表
      });


      // Allcalendar(this.KtData.startBut.subsystem+'.'+that.numFloor,res=>{
      //   console.log("获取日程表")
      //   console.log(res.all)
      //   that.sceneCtroll=res.all
      // });
    },
    methods: {
      //创建场景确定按钮
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
               }
               else if(that.form.schedule.schedulename==''){
                 that.$message({
                    type: 'error',
                    message: '请选择标题 '
                  }); 
               }else{
                // 转换日期选择格式
                 that.DateValue.forEach(item=>{
                     that.form.DateValue.push({weekid:item})
                 })
                  that.form.light.push(that.KtData);
                  // 创建场景接口

                  console.log("待发送接口数据",that.form);

                 zmApi(this.form,res=>{
                   //存储创建日程id
                   that.form.schedule.scheduleid=res;
                   // 添加到日程列表
                   that.sceneCtroll.push(that.form);
                   that.screneShow=false;
                   console.log( "新增日程表成功", that.form);   
                  });
               }    
          }else{
              that.form.DateValue=[];
              // 修改场景接口
              that.DateValue.forEach(item=>{
                 that.form.DateValue.push({weekid:item});
              });
              that.form.light = [];
              that.form.light.push(that.KtData);


             zmApi(this.form,res=>{
               // 添加到日程列表
               that.sceneCtroll[that.screnIndex].DateValue = []; //先清空

               that.DateValue.forEach(time=>{
                  that.sceneCtroll[that.screnIndex].DateValue.push({weekid:time});
              });
               that.screneShow=false;   
              })  ;
             console.log("修改日程表完成");
             console.log(that.form);
             console.log(that.DateValue);
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
            });
        }
        console.log("删除日程表完成");

      },
      //创建场景
      screneToggle(){
        var that=this;
         //日期数据
         this.DateValue=[],
         //先清空时间段 否则再次添加会存在以前的数据
         this.sureTime=[],
         this.form={
            //日期数据
            DateValue:[],
            sureTime:[],
            checked:true,
            light:[],
            schedule:{
              scheduleid:'',
              schedulename:'',
              system:that.KtData.startBut.subsystem,
              floor:that.numFloor.toString(),
             },

        },
          this.screneShow=true;
      },

      //查看日程表事件 chenms 2018-8-31  本文件的create会先与引入文件的create先执行，所以KtData此时还没从数据库查询出来，
      //所以“查看日程表”写成事件，第一次点击时才从调接口查日程表
      showScrene(){
        var that=this;
        this.show2 = !this.show2;
        if(that.sceneCtroll.length <= 0){
          
          Allcalendar(this.KtData.startBut.subsystem+'.'+that.numFloor + "." + this.KtData.startBut.ReflectVar,res=>{
            console.log("获取该页面所有日程表");
            //console.log("res",res);
            
            that.sceneCtroll=res.all; //用于查看时显示所有日程表
            console.log("res.all==>",res.all);
          });
        }
      },



      //关闭场景
      sceneClose(){
          this.screneShow=false;
      },
      //修改日程表
      allControl(scene,index){
        var that=this;
        that.show2=false;
         //先清空日期
         that.DateValue=[];
         // 修改日程的序列号
         this.screnIndex=index;
         this.screneShow=true;
        // 把请求数据的日期添加到创建表中
         scene.DateValue.forEach(time=>{
             that.DateValue.push(time.weekid);
         });
         this.form=scene;
      },
      // 添加时间段按钮
      TimeSure(){
        var that=this;
        var repeat=false;
        console.log(that.TimeValue[0].toTimeString())
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
        console.log( this.form)    
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


      },
    }

</script>
<style scoped >
 @import '~scss_vars';
    .time{line-height: 24px}
    .el-dialog__body{
      height: 320px;
      overflow: scroll;
    }
    .el-card{
      width: 48%;
      display: inline-block;
      margin-left: 1%;
    }
    .but{
      position:absolute;right: 145px;
    }
</style>