<template>
		<div class="dzdt_bg" >
			<div>第{{floorNum}}层</div>
			<iframe ref="iframe" src="./static/1f.html" v-if="floorNum == 1"></iframe>
			<iframe ref="iframe" src="./static/2f.html" v-if="floorNum == 2"></iframe>
			<iframe ref="iframe" src="./static/3f.html" v-if="floorNum == 3"></iframe>
			<iframe ref="iframe" src="./static/4f.html" v-if="floorNum == 4"></iframe>
      <iframe ref="iframe" src="./static/-1f.html" v-if="floorNum == -1"></iframe>
       <iframe ref="iframe" src="./static/-2.html" v-if="floorNum == -2"></iframe>
			<!-- 跳转空调页面 v-drag="{set:set,index:index}" -->
      <div v-for="(Ba,index) in ktFloor" :class="Ba.devcode" class="kt1" >
       <!--  跳转 -->
        <router-link :to="{ name: '排风机1', params: { devcode: Ba.devcode,description:Ba.description,floor:Ba.floorNum,subsystem:Ba.subsystem}}">
          <!-- 提示文字 -->
          <el-tooltip class="item" effect="dark" :content="Ba.description" placement="top">
            <!-- 空调图片 -->
              <img src="../../../assets/lyzn/fanRightTrue.gif" width='40px' >
          </el-tooltip>
         </router-link>
       </div>
      <!-- 组件传值 -->
    <scren :numFloor="floorNum" :KtData='KtData' :admin="admin" news="0"
    check="0" tops="10px"></scren>
		 </div>
</template>
<script>
import { ktFl } from '../../../service/getData';
import scren from '../../../components/newScren.vue';
import {mapState} from 'vuex'
	export default {
    components:{scren},
		data() {
			return {
         KtData:{ startBut:{subsystem:"fan"}},
		      floorNum:0,
          subsystem:'',
		      ktFloor:[],
			}
		},
    computed: {
        ...mapState([
          'admin'
        ])
      },
		created(){
		  //楼层
          this.floorNum=this.$route.params.floorNum;
          this.subsystem=this.$route.params.subsystem;
          console.log(this.floorNum);
          // 数据为空跳转
          if(this.floorNum==null){
              this.$router.push({path: '/newFan' });
          }
          //获取楼层空调信息
          ktFl(this.floorNum,this.subsystem,res=>{
             console.log(res.data)
             this.ktFloor=res.data

           })
		},
		methods: {
        set(index,x,y){
          this.ktFloor[index].left=x
          this.ktFloor[index].top=y
      }
		},
   directives: {
      // 定义拖拽指令
       drag:{
        // 当被绑定的元素插入到 DOM 中时……
        inserted: function (el,binding) {
        var that=this
         let x=0;
        let y=0;
        let px=0;
        let py=0;
        let onDrag = false;
        console.log(binding.value)
        el.onmousedown = function (e) {
          x = e.clientX;
          y = e.clientY;

          px = el.offsetLeft;
          py = el.offsetTop;

          onDrag = true;
          el.style.cursor = 'move';
        },
       el.onmousemove = function (e) {
          e.preventDefault()
          if (onDrag) {
            let nx = e.clientX + px - x;
            let ny = e.clientY + py - y;
            
            el.style.left = nx + 'px'
            el.style.top = ny + 'px'
          }
        },
        el.onmouseup = function (e) {
          if (onDrag) {
            onDrag = false;
            el.style.cursor = 'default';
            binding.value.set(binding.value.index,el.style.left,el.style.top)
            console.log("left:"+el.style.left)
            console.log("top:"+el.style.top)
          }
        }
      }
        }
      },
	};

</script>

<style scoped lang="scss">
   iframe{width: 900px;height: 900px;border: 0}
   .dzdt_bg{
    margin:0 auto;
   	background-size: 100%;
   	position:relative;
   	.kt1{
       position:absolute;
       top:40px;
       left: 120px;
   	}
    .FAN0100001{
      top:135px;
      left:440px;
    } 
    .FAN0100002{
       left:230px;
       top:120px;
    }.FAN0100003{
       top:330px;
       left:248px;
    }.FAN0100004{
       left:360px;
       top:401px
    }.FAN0200004{
       left:585px;
       top:352px
    }.FAN0200003{
        left:458px;
        top:158px
    }.FAN0200002{
       top:126px;
       left: 300px;
    }.FAN0200001{
       top:109px;
       left: 74px;
    }.FAN0200005{
       top:399px;
       left: 378px;
    } .FAN0300001{
       left:330px;
        top:377px
    }.FAN0300002{
       left:183px;
       top:279px
    }.FAN0300003{
       top:77px;
       left: 104px;
    }.FAN01000B12{
      top:340px;
      left: 160px;
    }.FAN01000B11{
      top:360px;
      left: 220px;
    }.FAN01000B10{
      top:460px;
      left: 260px;
    }.FAN01000B9{
      top:420px;
      left: 280px;
    }.FAN01000B8{
      top:390px;
      left: 320px;
    }.FAN01000B7{
      top:475px;
      left:560px;
    }.FAN01000B6{
      top:490px;
      left: 640px;
    }.FAN01000B5{
      top:140px;
      left: 520px;
    }.FAN01000B4{
      top:300px;
      left: 650px;
    }.FAN01000B3{
      top:260px;
      left: 630px;
    }.FAN01000B2{
      top:120px;
      left: 350px;
    }.FAN01000B1{
      top:60px;
      left: 300px;
    }.FAN02000B01{
      top:490px;
      left: 733px;
    }.FAN02000B02{
      top:345px;
      left: 733px;
    }.FAN02000B03{
      top:255px;
      left: 729px;
    }.FAN02000B04{
      top:68px;
      left: 580px;
    }.FAN02000B05{
      top:68px;
      left: 400px;
    }.FAN02000B06{
      top:68px;
      left: 360px;
    }.FAN02000B07{
      top:285px;
      left: 165px;
    }.FAN02000B08{
      top:375px;
      left: 269px;
    }.FAN02000B09{
      top:335px;
      left: 410px;
    }.FAN02000B10{
      top:550px;
      left: 150px;
    }.FAN02000B11{
      top:670px;
      left: 290px;
    }.FAN02000B12{
      top:700px;
      left: 420px;
    }.FAN02000B13{
      top:600px;
      left: 440px;
    }

   }


</style>