<template>
		<div class="dzdt_bg">
			<div>第{{floorNum}}层</div>
			<iframe ref="iframe" src="./static/1f.html" v-if="floorNum == 1"></iframe>
			<iframe ref="iframe" src="./static/2f.html" v-if="floorNum == 2"></iframe>
			<iframe ref="iframe" src="./static/3f.html" v-if="floorNum == 3"></iframe>
			<iframe ref="iframe" src="./static/4f.html" v-if="floorNum == 4"></iframe>
			<iframe ref="iframe" src="./static/-1f.html" v-if="floorNum == -1"></iframe>
      <iframe ref="iframe" src="./static/-2.html" v-if="floorNum == -2"></iframe>
			<!-- 跳转空调页面 -->
			<div v-for="Ba in ktFloor" :class="Ba.devcode" class="kt1">
        <!-- 跳转 -->
				<!-- <router-link :to="{ name: '空调系统1', params: { devcode: Ba.devcode,description:Ba.description,floor:Ba.floorNum,subsystem:Ba.subsystem}}"> -->
          <!-- 提示文字 -->
          <el-tooltip class="item" effect="dark" :content="Ba.description" placement="top">
            <!-- 空调图片 -->
	            <img src="../../../assets/lyzn/fanRightTrue.gif" width='35px'>
          </el-tooltip>
	       </router-link>
      </div>
		 </div>
</template>
<script>
import { ktFl } from '../../../service/getData';
	export default {
		data() {
			return {
			    showText: false,
          hideText: true,
		      floorNum:3,
          subsystem:'',
		      ktFloor:[],
			}
		},
		created(){
		      //楼层
           this.floorNum=this.$route.params.floorNum;
           this.subsystem=this.$route.params.subsystem;
          console.log(this.floorNum);
          // 数据为空跳转
          if(this.floorNum==null){
              this.$router.push({path: '/slushF' });
          }
          //获取楼层空调信息
          ktFl(this.floorNum,this.subsystem,res=>{
             console.log(res.data)
             this.ktFloor=res.data

           })
		},
		methods: {
		},
	};

</script>

<style scoped lang="scss">
 @import '/static/style/common.css';
   iframe{width: 820px;height: 900px;border: 0}
   .dzdt_bg{
    margin:0 auto;
   	background-size: 100%;
   	position:relative;
   	.kt1{
       position:absolute;
       top:40px;
       left: 120px;
   	}
    .SLU01000B01-1{
       top:420px;
       left: 660px;
    }
    .SLU01000B01-2{
       top:420px;
       left: 675px;
    }

     .SLU01000B02-1{
       top:380px;
       left: 660px;
    }
    .SLU01000B02-2{
       top:380px;
       left: 675px;
    }
     .SLU01000B03-1{
       top:160px;
       left: 690px;
    }
     .SLU01000B04-1{
       top:100px;
       left: 640px;
    }
     .SLU01000B05-1{
       top:60px;
       left: 520px;
    }
    .SLU01000B05-2{
       top:60px;
       left: 535px;
    }
     .SLU01000B06-1{
       top:204px;
       left: 550px;
    }
    .SLU01000B06-2{
       top:204px;
       left: 565px;
    }
      .SLU01000B07-1{
        top: 180px;
        left: 440px
    }
      .SLU01000B07-2{
        top: 180px;
        left: 455px
    }
      .SLU01000B08-1{
       top:60px;
       left: 260px;
    }
     .SLU01000B08-2{
       top:60px;
       left: 275px;
    }
      
   	  .SLU01000B09-1{
       top:180px;
       left: 260px;
    }
      .SLU01000B09-2{
       top:180px;
       left: 275px;
    }
    .SLU01000B10-1{
       top:190px;
       left: 160px;
    }
    .SLU01000B10-2{
       top:190px;
       left: 175px;
    }
   	.SLU01000B11-1{
   	   top:120px;
       left: 120px;
   	}
    .SLU01000B11-2{
       top:120px;
       left: 135px;
    }
    .SLU01000B12-1{
       top: 240px;
       left: 80px;
    }
     .SLU01000B12-2{
       top: 240px;
       left: 95px;
    }
     .SLU01000B13-1{
       top: 260px;
       left: 260px;
    }
      .SLU01000B13-2{
       top: 260px;
       left: 275px;
    }
    .SLU01000B14-1{
       top: 344px;
       left: 200px;
    }
    .SLU01000B14-2{
       top: 344px;
       left: 215px;
    }
    .SLU01000B15-1{
       top: 270px;
       left: 360px;
    }
    .SLU01000B16-1{
       top: 340px;
       left: 380px;
    }
     .SLU01000B16-2{
       top: 340px;
       left: 395px;
    }
    .SLU01000B17-1{
       top: 410px;
       left: 290px;
    }
    .SLU01000B18-1{
       top: 490px;
       left: 360px;
    }
    .SLU01000B18-2{
       top: 490px;
       left: 375px;
    }
    .SLU01000B19-1{
       top: 380px;
       left: 490px;
    }
     .SLU01000B19-2{
       top: 380px;
       left: 505px;
    }
     .SLU01000B20-1{
       top: 400px;
       left: 240px;
    }
    .SLU01000B21-1{
       top: 460px;
       left: 200px;
    }
    .SLU01000B22-1{
       top: 480px;
       left: 330px;
    }
   // 23位置不知道
   .SLU01000B23-1{
       top: 550px;
       left: 350px;
    }
    .SLU01000B23-2{
       top: 550px;
       left: 365px;
    }
    .SLU01000B24-1{
       top: 470px;
       left: 90px;
    }
    .SLU01000B25-1{
       top: 620px;
       left: 150px;
    }
    .SLU01000B26-1{
       top: 680px;
       left: 740px;
    }
    .SLU01000B26-2{
       top: 680px;
       left: 755px;
    }
    .SLU01000B27-1{
       top: 800px;
       left: 340px;
    }
    .SLU01000B27-2{
       top: 800px;
       left: 355px;
    }
    .SLU01000B28-1{
       top: 720px;
       left: 320px;
    }
    .SLU01000B29-1{
       top: 690px;
       left: 444px;
    }
    .SLU01000B30-1{
       top: 795px;
       left: 390px;
    }
    .SLU01000B30-2{
       top: 795px;
       left: 405px;
    }
    .SLU01000B31-1{
       top: 560px;
       left: 670px;
    }
    .SLU01000B31-2{
       top: 560px;
       left: 685px;
    }
    .SLU01000B32-1{
       top: 680px;
       left: 670px;
    }
    .SLU01000B32-2{
       top: 680px;
       left: 685px;
    }
    .SLU01000B33-1{
       top: 180px;
       left: 380px;
    }
    .SLU01000B33-2{
       top: 180px;
       left: 395px;
    }

   }


</style>