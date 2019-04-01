<!-- 报警公共部分，供Home.vue与alarm.vue导入调用 chenms 2018-10-17 -->
<template>
	<audio id='ddsound' src='/static/audios/2.wav'   align='center' border='0' width='1' height='1' loop='false'></audio>
</template>

<script>
 import { getAlarm } from '../../service/alarmDao';
 export default {
 	//props: ['alarmData'],
    data() {
      return {
      }
    },
    created(){
    },
    mounted() {
     	/*var that = this;
    	this.$nextTick(function () {
    		that.$options.methods.qryAlarmData("2018-09-01","");
            //发出或停止报警声音
            that.$options.methods.playOrStopAlarmSound.bind(this)();
            console.log("正常1");
		});*/
    },
    methods: {
     /**
     *发出报警声音
     */	
     playSound(){ 
        document.getElementById('ddsound').play();
     },
     /**
     *停止报警声音
     */	
     playSop(){
        document.getElementById("ddsound").pause();
     },
     /**
     *发出或停止报警声音 2018-10-7
     */
     playOrStopAlarmSound(alarmData){
     	var that = this;
     	//var alarmData = that.$store.state.alarmData;
        var tableDataLength = alarmData.length;
        for(var i = 0;i < tableDataLength;i++){
          if(alarmData[i].ACK == "未确认"){
            //存在未确认的报警，则发出报警声音
            //that.$options.methods.playSound.bind(this)();
            document.getElementById('ddsound').play(); //该元素已在home中生成，测试过在报警页面也可获取到
            break;
          }
          if(i == tableDataLength - 1){
            //循环到最后，不存在"未确认"的数据，则停止报警
            //that.$options.methods.playSop.bind(this)();
            document.getElementById("ddsound").pause();
          }
        }
        if(tableDataLength <= 0){
        	//无数据，停止声音
        	document.getElementById("ddsound").pause();
        }
     },
     /**
     *从数据库查询报警数据
     *begindate,enddate: 格式yyyy-mm-dd
     */
     qryAlarmData(begindate,enddate){
     	var that = this;
     	var data1 = [];
     	getAlarm(begindate,enddate,res=>{
		  console.log("查询报警表数据为：",res.data);
		  that.alarmData = res.data;
		  console.log("执行完成回调1");
     	  //存储报警数据
		  //this.$store.commit('setAlarmData',res.data); //一直报错commit未定义，所以没办法将alarmData放在仓库
		  //console.log("存储报警数据完成");
          //发出或停止报警声音
          //that.$options.methods.playOrStopAlarmSound.bind(this)(); //不能写在这里，报错
        });
        //that.$options.methods.playOrStopAlarmSound.bind(this)();
     }

    }
 }
</script>