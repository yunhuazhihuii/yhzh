<template>
  <el-row class="container" :span="24">
   <!-- top组件 -->
  <top :name="name" ></top>
  <!-- 表单 -->
  <el-col :span="24" class="main">

      <!-- 查询条件 chenms 2018-9-8 -->
      <div style="width: 960px;margin: 0px auto;padding-top: 20px">
        <el-form ref="form_alarm" :model="form_alarm">
          <table width="100%">
            <tr>
              <td width="50%" style="text-align:left;"><label>报警时间 从</label>
                  <el-date-picker v-model="form_alarm.begindate" type="date"></el-date-picker>
              </td>
              <td width="50%" style="text-align:left;"><label>至</label>
                  <el-date-picker v-model="form_alarm.enddate" type="date"></el-date-picker>
                  <el-button type="primary" @click="btn_alarmQry" style="float:right">查询</el-button>
                  <!-- <el-button type="primary" @click="btn_test" style="float:right">测试</el-button> -->
              </td>
            </tr>
          </table>
        </el-form>
      </div>


      <div  style="width: 960px;margin: 10px auto;box-shadow:0 0 6px #ddd;">
       <el-table
        :data="alarmData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        row-class-name="warning-row"
        border width="100%">
          <el-table-column
          prop="date"
          label="报警时间"
          width="120">
          <template scope="scope">
            <span v-if="scope.row.ACK==='未确认'" style="color: red">{{scope.row.date}}</span>
            <span v-else >{{scope.row.date}}</span>
          </template>

        </el-table-column>
          <el-table-column
          prop="system"
          label="子系统"
          width="80">
          <template scope="scope">
            <span v-if="scope.row.ACK==='未确认'" style="color: red">{{scope.row.system}}</span>
            <span v-else >{{scope.row.system}}</span>
          </template>

        </el-table-column>

        <el-table-column
                    prop="description"
          label="设备名称"
          width="120">
          <template scope="scope">
            <span v-if="scope.row.ACK==='未确认'" style="color: red">{{scope.row.description}}</span>
            <span v-else >{{scope.row.description}}</span>
          </template>
        </el-table-column>

        <!-- <el-table-column
          prop="AlarmLevel"
          label="报警级别"
          width="80">
        </el-table-column>   -->
        <el-table-column
          prop="problem" 
          label="故障原因" width="120">
          <template scope="scope">
            <span v-if="scope.row.ACK==='未确认'" style="color: red">{{scope.row.problem}}</span>
            <span v-else >{{scope.row.problem}}</span>
          </template>

        </el-table-column>
        <el-table-column
          prop="ACK" 
          label="确认标志" width="80">
          <template scope="scope">
            <span v-if="scope.row.ACK==='未确认'" style="color: red">{{scope.row.ACK}}</span>
            <span v-else >{{scope.row.ACK}}</span>
          </template>

        </el-table-column>
         <el-table-column
          prop="sureTime" 
          label="确认时间" width="120">
        </el-table-column>
         <el-table-column
          prop="sureUser" 
          label="确认人" width="70">
        </el-table-column>
        <el-table-column
          prop="remark" 
          label="备注">
        </el-table-column>
         <el-table-column

          label="操作"  width="70">
          <template slot-scope="scope">
            <el-button
              size="small"
              @click.native.prevent="sureBut(scope.$index+pagesize*(currentPage-1))"
              v-if="scope.row.ACK==='未确认'"
              >确定</el-button>
              <!-- v-show="tableData[scope.$index+pagesize*(currentPage-1)].ACK=='未确认'"  -->

          </template>
        </el-table-column>
      </el-table>
    <!-- 分页页码 -->
       <el-pagination style="padding: 14px;"
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[4, 8, 10]"
          :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="alarmData.length">
      </el-pagination>
     </div>
      <!-- <audio id='ddsound' src='/static/audios/2.wav'   align='center' border='0' width='1' height='1' loop='false'></audio> -->
      <!-- <el-button  @click="playSound">声音</el-button>
      <el-button  @click="playSop">停止</el-button> -->

     </el-col >
       <el-dialog title="报警确认" :visible.sync="alarm_sure_show">
      <el-form ref="form_alarm_sure" :model="form_alarm_sure">
        <el-form-item label="备注">
            <textarea rows="5" cols="60" v-model="form_alarm_sure.remark"></textarea>
            <el-button type="primary" @click="alarmRemarkSure" style="float: right; margin-right: 20px">确定</el-button>
        </el-form-item>
      </el-form>

  </el-dialog>

  </el-row >
</template>

<script>

import {mapState} from 'vuex'

import top from '../../components/top';
import util from '../../common/js/util';

import { getSession} from '../../common/js/util';
import { getAlarm,sureAlarm,insertAlarm } from '../../service/alarmDao';
import alarm from './alarm_pub';

 export default {
  components:{top},
    data() {
      return {
          name:'',
          userId: "",
          alarm_sure_show: false, //是否显示确认报警框
          form_alarm:{
            begindate: util.formatDate.format(new Date()),
            enddate: ""
          },
          form_alarm_sure:{
            sure_index: 0, //点击“确认”所在tableData的索引号
            remark: "" //备注内容
          },

         //当前默认页
          currentPage:1,
          pagesize:4,

          //tableData: [ ]
          /* {
            date: '2019-7-8 9:00',
            system: '空调系统',
            description: '一楼1号空调',
            //AlarmLevel: '一般',
            problem: '过滤网报警',
            ACK: '未确认',
            sureTime: '2019-7-8 12:00',
            sureUser: '',
            remark: '已确认'
         }   */
      }
    },

    computed: {
      ...mapState([
        'alarmData'
        ])
    },

    created(){
      //console.log("日期时间格式化函数测试",util.formatDate.format(new Date(),"yyyyMMddhhmmss"));

      this.name=getSession('name');

      this.userId = getSession('userId');
      //执行查询方法btn_alarmQry
      this.$options.methods.btn_alarmQry.bind(this)();
      //console.log("当前日期",util.formatDate.format(new Date()));

    },
    methods: {
      //分页信息
      handleSizeChange: function (size) {
          this.pagesize = size;
      },

      handleCurrentChange: function(currentPage){
          this.currentPage = currentPage;
      },
      
     // 确认按钮，弹出备注填写框
      sureBut(index){
          // 确认人
          //this.tableData[index].sureUser=getSession('name')
        this.alarm_sure_show = true;
        this.form_alarm_sure = {
            sure_index: index, //点击“确认”所在tableData的索引号
            remark: "" //备注内容
        };

      },

      /**
     *报警查询按钮事件
     */
     btn_alarmQry(){
        var that = this;
        getAlarm(this.form_alarm.begindate,that.form_alarm.enddate,res=>{
          //this.tableData = res.data;
          //存储报警数据
          that.$store.commit('setAlarmData',res.data);
          console.log("存储报警数据完成2",that.$store.state.alarmData);
          
          //发出或停止报警声音
          //this.$options.methods.playOrStopAlarmSound.bind(this)();
          alarm.methods.playOrStopAlarmSound(that.$store.state.alarmData);
        });


             },
    /**
     *仅用测试，无用 2018-10-21
     */
     btn_test(){
        var that = this;
      /*{
              date: '2019-7-8 9:00',
              system: '空调系统',
              description: '一楼1号空调',
              //AlarmLevel: '一般',
              problem: '过滤网报警',
              ACK: '未确认',
              sureTime: '2019-7-8 12:00',
              sureUser: '',
              remark: '已确认'
           }*/
       var date = "2019-10-26 09:00:45";
       var reflectvar = "A001.1100000000000006.1100000000000618";
       var system = "BA";
       var AlarmLevel = "1";
       var problem = "空调温度过高报警测试哈哈1";
       insertAlarm(date,reflectvar,system,AlarmLevel,problem,res=>{
          console.log("插入数据进报警表成功，数据为：",res.data);
          //插入存储区alarmData，不知可否可以试下？已试过直接修改alarmData有效。【unshift貌似IE8后有效，指在数组前头加元素】
          that.$store.state.alarmData.unshift(res.data[0]);
          //发出报警声音
          alarm.methods.playOrStopAlarmSound(that.$store.state.alarmData);
        });
     },
     /**
     *报警备注确认事件，最终提交到后台 chenms 2018-9-17
     */
     alarmRemarkSure(){
        var that = this;
        var alarmData = that.$store.state.alarmData;
        var remark1 = util.trim(this.form_alarm_sure.remark);
        if(remark1 == ''){
            this.$message({
              type: 'error',
              message: '备注不能为空！'
            });
        }else{
          var index1 = this.form_alarm_sure.sure_index; 
          //提交数据到后台
          sureAlarm(alarmData[index1].alarmId,this.userId,remark1,res=>{
            //更新表格确认人
            alarmData[index1].sureUser = this.name;
            alarmData[index1].remark = remark1;
            alarmData[index1].ACK = "已确认";      //貌似这样改之后，公共存储区中的数据也同步更新了
            alarmData[index1].sureTime = res.data[0].sureTime;
            //该行不再需要确定按钮

            this.$message({
              type: 'success',
              message: '确认成功！'
            });
            //关闭确认框
            this.alarm_sure_show = false;
            //发出或停止报警声音
            //this.$options.methods.playOrStopAlarmSound.bind(this)();
            alarm.methods.playOrStopAlarmSound(that.$store.state.alarmData);
          });
      }
     }
  
    }
 }

</script>
<style scoped >
 @import '~scss_vars';
</style>