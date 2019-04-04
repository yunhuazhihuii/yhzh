<template>
    <div>   
        <div class="content">
          <div class="content-top">
            <div class="content-top-title">
              <p>当前位置：交易管理>十五天业绩预览</p>
            </div>
          </div>
          <div class="content-kpi">

            <el-tabs v-model="activeName2" type="card" @tab-click="handleClick">
              <el-tab-pane label="订单金额" name="first">
                <div id="chartmainbar" style="width:1000px; height:650px;">
              
                 </div>
              </el-tab-pane>
              <el-tab-pane label="订单总数" name="second">

                <div id="chartmainbar2" style="width:1000px; height:650px;">
              
                 </div>

              </el-tab-pane>
              
            </el-tabs>
            
          </div>

        </div>
       
        
        
    </div>
</template>


<script>
    import Axios from 'axios';
    import url from '../../../config/sysAPI.config.js'
    import {getSession} from '../../common/js/util';
    export default{
        data(){
            return { 
              userid:'', 
              // list:[],
              activeName2: 'first',  
            //   optionbar:{
            //     title:{
            //         text:'15天订单金额树状图'
            //     },
            //     tooltip:{},
            //     legend:{
            //         data:['用户来源']
            //     },
            //     xAxis:{
            //         // data:["12-14","12-15","12-16","12-17","12-18","12-19","12-20","12-21","12-22","12-23","12-24","12-25","12-26","12-27","12-28"]
            //         data:[]
            //     },
            //     yAxis:{
            //       type : 'value',
            //       axisLabel:{formatter:'{value} 元'}
            //     },
            //     series:[
            //     {
            //         name:'订单金额',
            //         type:'bar', //设置图表主题
            //         data:["500","200","360","100","200","400","200","300","300","100","100","100","200","100","309"],
            //         barWidth:35
            //     }
            //     ]
            // }
            }
        },
        methods: {
        
          drawLine: function(){
            //基于准本好的DOM，初始化echarts实例
            let chartmainbar = this.$echarts.init(document.getElementById("chartmainbar"));
            //绘制图表
            chartmainbar.setOption(this.optionbar);
          },
       
          handleClick(tab, event) {
            console.log(tab, event);
          },
       
          getdata(){

            //基于准本好的DOM，初始化echarts实例
            var chartmainbar = this.$echarts.init(document.getElementById("chartmainbar"));
            var chartmainbar2 = this.$echarts.init(document.getElementById("chartmainbar2"));
            //绘制图表
            

            var that = this;
            var api = url.Performance;
            Axios.post(api,
              {
                userid:that.userid
                
              }
            )
            .then((response)=>{
              console.log(response)
              console.log(response.data.time)

              setTimeout(()=>{  //未来让加载动画效果明显,这里加入了setTimeout,实现2s延时
                  chartmainbar.setOption({
                    title:{
                        // text:'15天订单金额树状图'
                    },
                    tooltip:{},
                    legend:{
                        data:['用户来源']
                    },
                    xAxis:{
                        data:response.data.time
                        // data:[]
                    },
                    yAxis:{
                      type : 'value',
                      axisLabel:{formatter:'{value} 元'}
                    },
                    series:[
                    {
                        name:'订单金额',
                        type:'bar', //设置图表主题
                        data:response.data.amount,
                        barWidth:35
                    }
                    ]


                  });


                  chartmainbar2.setOption({
                    title:{
                        // text:'15天订单金额树状图'
                    },
                    tooltip:{},
                    legend:{
                        data:['用户来源']
                    },
                    xAxis:{
                        type:'category',
                        data:response.data.time
                        // data:[]
                    },
                    yAxis:{
                      type : 'value',
                      // axisLabel:{formatter:'{value} 元'}
                    },
                    series:[
                    {
                        name:'订单总数',
                        type:'line', //设置图表主题
                        data:response.data.count,
                        // barWidth:35
                    }
                    ]


                });
              }, 20 )

              

              // console.log(JSON.parse(JSON.stringify(that.optionbar.xAxis.data)))
              // that.optionbar.xAxis.data = response.data.time
              // console.log(JSON.stringify(that.optionbar.xAxis.data))
              // that.list=JSON.parse(JSON.stringify(that.optionbar.xAxis.data))
              // console.log(that.optionbar.xAxis.data)

              // that.list = response.time
            })
            .catch((error)=>{
              console.log(error)
            })
          },

        },
        mounted(){
          this.userid = getSession("user_id");
          console.log(this.userid)
          // console.log(this.optionbar.xAxis.data)
          this.getdata()
          this.drawLine()

        }    
    }

</script>

<style scoped>
  .content {
    position:absolute;
    width: 100%;
    height: 1000px;
  }
  .content-top {
    width: 1255px;
    height: 40px;
    border: 1px solid #CCCCCC;
    position: absolute;
    top: 0;
    left: 0;
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

  .content-kpi {
    width: 1255px;
    height: 700px;
    border: 1px solid #CCCCCC;
    position: absolute;
    top: 60px;
    left: 0;
  }
 

</style>
