<template>
		<div class="dzdt_bg">
			<div>第{{floorNum}}层</div>
			<iframe ref="iframe" src="./static/1f.html" v-if="floorNum == 1"></iframe>
			<iframe ref="iframe" src="./static/2f.html" v-if="floorNum == 2"></iframe>
			<iframe ref="iframe" src="./static/3f.html" v-if="floorNum == 3"></iframe>
			<iframe ref="iframe" src="./static/4f.html" v-if="floorNum == 4"></iframe>
			<iframe ref="iframe" src="./static/-1f.html" v-if="floorNum == -1"></iframe>
			<!-- 跳转空调页面  v-drag="{set:set,index:index}" -->
			<div v-for="(Ba,index) in ktFloor" :class="Ba.devcode" class="kt1">
        <!-- 跳转 -->
				<router-link :to="{ name: '空调系统1', params: { devcode: Ba.devcode,description:Ba.description,floor:Ba.floorNum,subsystem:Ba.subsystem}}">
          <!-- 提示文字 -->
          <el-tooltip class="item" effect="dark" :content="Ba.description" placement="top">
            <!-- 空调图片 -->
	            <img src="../../../assets/lyzn/fanRightTrue.gif" width='40px'>
          </el-tooltip>
	       </router-link>
       </div>
        <!-- 组件传值：0-隐藏；1-显示 -->
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
          KtData:{ startBut:{subsystem:"BA"}},
			    showText: false,
          hideText: true,
		      floorNum:3,
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
         var that=this;
		      //楼层
          this.floorNum=this.$route.params.floorNum;
          this.subsystem=this.$route.params.subsystem;
          // 数据为空跳转
          if(this.floorNum==null){
              this.$router.push({path: '/Blowers' });
          }
          //获取楼层空调信息
          ktFl(this.floorNum,this.subsystem,res=>{
             console.log(res.data)
             this.ktFloor=res.data;
             this.ktFloor.forEach(item=>{
               that.$set(item,'left',100);
               that.$set(item,'top',180);
             })
            console.log(this.ktFloor)
           });

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
   iframe{width: 900px;height: 800px;border: 0}
   .dzdt_bg{
    margin:0 auto;
   	background-size: 100%;
   	position:relative;
   	.kt1{
       position:absolute;
       top:40px;
       left: 120px;
   	}
    .HBA01000B1{
      
      left:155px;
      top:82px
    }
    .HBA01000B2{
      
     left:113px;
      top:107px

    }
    .HBA01000B3{
      
       left:304px;
     top:407px
    }
    .HBA01000B4{
      left:390px;
       top:95px
    
    }
    .HBA01000B5{
      left:533px;
      top:379px

    }
    .HBA01000B6{
     left:429px;
     top:451px
    }
    .HBA01000B7{
      left:393px;
      top:452px
    }
    .HBA0100001{
       top:114px;
       left: 504px;
    }
     .HBA0100002{
       left:450px;
       top:137px
    }
     .HBA0100003{
       left:343px;
       top:110px
    }
     .HBA0100004{
       left:223px;
       top:292px
    }
     .HBA0100005{
       left:343px;
       top:392px
    }
     .HBA0100006{
       left:430px;
       top:392px
    }
     .HBA0200001{
       left:485px;
       top:426px
    }
      .HBA0200002{
        left:362px;
         top:433px
    }
      .HBA0200003{
       left:222px;
       top:310px
    }
      .HBA0200004{
       left:46px;
       top:175px
    }
      .HBA0200005{
      left:271px;
      top:96px
    }
    .HBA0200006{
       left:465px;
       top:134px
    }
    .HBA0200007{
       left:160px;
       top:120px
    }
    .HBA0200008{
       left:610px;
       top:350px
    }
   	  .HBA0300001{
       left:455px;
       top:417px
    }
    .HBA0300002{
      left:344px;
       top:412px
    }
   	.HBA0300003{
   	   left:198px;
       top:284px
   	}
    .HBA0300004{
       left:300px;
       top:130px
    }
    .HBA0400001{
       left:459px;
      top:408px
    }
   }


</style>