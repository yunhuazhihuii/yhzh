<template>
    <div  class="lccontain">
         <div class="m b" v-for="BA in BAsystem">
          <router-link :to="{ name: '污水泵2', params: { floorNum: BA.floorNum,subsystem:BA.subsystem}}">
            {{BA.SearchKey}}层
            <span class="top animate"></span>
            <span class="right animate"></span>
            <span class="bottom animate"></span>
            <span class="left animate"></span>
          </router-link>
      </div>

      <div style="margin-top: 40px" class="pump">
      <!-- 全程控和触发控 -->
        <!-- <el-radio v-model="autoCtroll" label="日程控制" border size="medium" @change="RcState">日程控制</el-radio>
        <el-radio v-model="autoCtroll"  label="手动控制" border size="medium" @change="RcState">手动控制</el-radio> -->

       <!-- 表格数据展示     stripe   ？？？  -->   
        <el-table 
            :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            
            border
            style="width: 621px;margin-top: 20px;">
            <el-table-column
              prop="description"
              label="设备名称"
              width="280"
              align="center">
            </el-table-column>
             <el-table-column
              prop="FanState.value"
              label="运行状态"
              width="170"
              align="center">
            </el-table-column>
             <el-table-column
              prop="FanAlarm.value"
              label="高水位报警"
              width="170"
              align="center">
            </el-table-column>

          <!--   <el-table-column
              prop="WaterAdjust.value"
              label="水阀调节"
              width="80">
            </el-table-column>
             <el-table-column
              prop="HandAuto.value"
              label="手自动" 
              width="80">
            </el-table-column>
            <el-table-column
              label="操作">
              <template slot-scope="scope">
                 <el-button size="small" 
                  v-if="autoCtroll=='日程控制'?disabled=true:disabled=false"
                  disabled @click="open_rotation(scope.$index+pagesize*(currentPage-1))">开启</el-button>
                <el-button size="small" 
                  v-if="autoCtroll=='手动控制'?disabled=true:disabled=false"
                  @click="open_rotation(scope.$index+pagesize*(currentPage-1))">开启
                </el-button>
                <el-button size="small"
                  v-if="autoCtroll=='日程控制'?disabled=true:disabled=false"
                  disabled @click="close_rotation(scope.$index+pagesize*(currentPage-1))">关闭</el-button>
                <el-button size="small" 
                  v-if="autoCtroll=='手动控制'?disabled=true:disabled=false"
                  @click="close_rotation(scope.$index+pagesize*(currentPage-1))">关闭
                </el-button>
              </template>
            </el-table-column> -->

          </el-table>

             <!-- 分页页码 -->
            <el-pagination style="margin-top:16px;"
              background
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[4, 8, 10]"
              :page-size="pagesize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="tableData.length">
            </el-pagination>
          </div>
    </div>
</template>

<script>
import { ktAllData } from '../../../service/getData';
import {mapState} from 'vuex'
  export default {
    data() {
      return {

        // sebsocket
        KtData:{ startBut:{subsystem:"slu"}},
        socket:'',

        // 向nodejs发送数据
        in_outpoint:{type:"rtsub",pointer:["clientId1","clientId2"],content:{data:{clientId:"H000000002",subscribeTopic:"控制器",fileCategory:"文件类型",subscribeSubitem:[]},callback:46}},
         // 手动控制
         autoCtroll:'日程控制',

         //当前默认页
         currentPage:1,
         
         pagesize:4,
         tableData:[],
         BAsystem:[
             {
               floorNum:-2,
               SearchKey:'-2',
              subsystem:'slu'
             },
         ]
      }
    },
    beforeRouteLeave(to, from, next){
      this.socket.disconnect();
      next()
     },
     computed: {
        ...mapState([
            'admin'
          ])
      },
    created(){
        var that=this;

         //websocket连接
        this.socket = io.connect('http://192.168.0.114:8090');
        //获取楼层空调信息 参数1代表空调 2代表排风系统 3代表污水泵
        ktAllData('slu',3,res=>{

          var devmessage=res.data[0];
          var viewreflect=res.data[1];

          devmessage.forEach(items =>{
            // console.log( "devmessage==>",devmessage );

             viewreflect.forEach(item =>{
              // console.log( "viewreflect==>",viewreflect );

              if(items.devcode==item.devcode){
                 //给对象添加属性
                that.$set(items,item.viewVar,item);
              }
            })
          })
        that.tableData=devmessage;
        console.log("污水泵that.tableData==>",that.tableData);

        // nodejs发送订阅信息
        // 获取第一页数据
        for (var i = 0; i < that.tableData.length; i++) {
             // 遍历对象获取映射点
              for (var Key in that.tableData[i]){
              // 添加映射点
              that.in_outpoint.content.data.subscribeSubitem.push(that.tableData[i][Key].ReflectVar);
            }
              }
              this.socket.emit('clientToS', this.in_outpoint);
            //nodejs发送订阅信息结束
            console.log( "this.in_outpoint==>",this.in_outpoint );

             var message=that.socket.id;

              //socket事件  接收实时数据 socketid为事件名
             this.socket.on(message, MESSAAGE => {
                 let data=MESSAAGE.content.data;
                 console.log( "MESSAAGE==>", MESSAAGE );

                 // 后台返回数据
                 data.forEach((item,index)=>{

                  // console.log("item==>",item);

                  // 灯的数据
                  that.tableData.forEach((items,Tindex)=>{

                      // 遍历对象获取映射点
                      for (var Key in items){
                        // 如果映射点相同就赋值
                        if(item.name==items[Key].ReflectVar){
                          
                          if(Key == 'FanState'){
                            if(item.value==1?item.value='运行':item.value='停止')
                            items[Key].value=item.value
                          }else if(Key == 'FanAlarm'){
                            if(item.value==1?item.value='报警':item.value='正常')
                            items[Key].value=item.value
                          }else{
                            items[Key].value=item.value
                          }             
                        }
                      }
                  })
                 })
             })
         })
    },
    
    methods: {
      // 日程控制或者手动控制状态 
        RcState(){
          var that=this;
          //获取数据
           this.$store.commit('RcStateVal',that.autoCtroll );
        },
       //分页信息
          handleSizeChange: function (size) {
              this.pagesize = size;
             },
          handleCurrentChange: function(currentPage){
              var that=this;
              this.currentPage = currentPage;
               },
            //关闭
          close_rotation(index){
              var that=this;
            },
             //开启
          open_rotation(index){
              var that=this;
              var MESSAAGE1= {"type": "clientSend","pointer": ["clientId1","clientId2"],"content": {
                     "sourcetype": "html5","data": [{
                           "name": that.tableData[index].InSport,
                           "value": 1}],"callback": 21}}
        //发送数据
        this.socket.emit('clientToS', MESSAAGE1);
            },
    },
  }
</script>
<style scoped lang="scss">
  @import '~scss_vars';
    a{display:block;}
  .pump {
    // background-color: pink;
    position: absolute;
    left:270px;
    top:100px;
  }

</style>