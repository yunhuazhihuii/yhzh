<template>
  <div>  
    <!--头部 导航栏 -->
    <v-header></v-header>

    <div class="content">

      <!-- 左侧边栏目录 -->
      <v-storehouseSide> </v-storehouseSide>

      <!-- 右边内容 -->
      <div style="float:left;margin-left:10px;margin-bottom:30px;">
       
        <router-view></router-view>

      </div>

    </div>
   

  </div>
</template>

<script>
 import '../assets/css/storehouse.css';
 import Header from './Header.vue';
 import storehouseSide from './storehouseSide.vue';
   export default {   
      components:{
        'v-header':Header,
        'v-storehouseSide':storehouseSide   
      },

      data () {  /*业务逻辑里面定义的数据*/
        return {
          
          select: '',
          search: '',
          currentPage1: 10,

          tableData3: [
              {
                
              }],
              multipleSelection: []

        }
      },

      methods: {
        toggleSelection(rows) {
          if (rows) {
            rows.forEach(row => {
              this.$refs.multipleTable.toggleRowSelection(row);
            });
          } else {
            this.$refs.multipleTable.clearSelection();
          }
        },
        handleSelectionChange(val) {
          this.multipleSelection = val;
        },

        indexMethod(index) {
          return index+1;
        },

        handleEdit(index, row) {
            console.log(index, row);
        },

        handleDelete(index, row) {
            console.log(index, row);
        },



        handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
          console.log(`当前页: ${val}`);
        }



      }

    }
</script>


<style scoped>
.content{
  
}

</style>