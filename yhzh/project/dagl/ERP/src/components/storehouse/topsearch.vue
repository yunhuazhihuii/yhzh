<template>

  <!-- 货物筛选搜索 -->

  <div id="allorder" style="width: 1100px"> 

    <div class="top">

      <p class="reposition">当前位置：交易管理>待处理订单</p>

      <div class="psearch">

          <span>异常标签:
              <!-- :key="item4.value" -->
              <el-select v-model="value1" placeholder="请选择">
                  <el-option
                    v-for="item1 in options1"
                    
                    :label="item1.label"
                    :value="item1.value">
                  </el-option>
              </el-select>
          </span>

          <span>普通标签:
              <el-select v-model="value2" placeholder="请选择">
                  <el-option
                    v-for="item2 in options2"
                    
                    :label="item2.label"
                    :value="item2.value">
                  </el-option>
              </el-select>
          </span>

          <br><br>

          <span>店铺账号:
              <el-select v-model="value3" placeholder="请选择">
                  <el-option
                    v-for="item3 in options3"
                    
                    :label="item3.label"
                    :value="item3.value">
                  </el-option>
              </el-select>
          </span>

          <span>订单时间:

              <el-date-picker
                v-model="data1"
                type="datetime"
                placeholder="选择日期时间">
              </el-date-picker>

          </span>

          <span>订单状态:
              <el-select v-model="value4" placeholder="请选择">
                  <el-option
                    v-for="item4 in options4"
                    
                    :label="item4.label"
                    :value="item4.value">
                  </el-option>
              </el-select>
          </span>

          <button>筛选</button>

      </div>

      <div class="insearch">

          
        <p style="overflow: hidden;">
          <span style="float:left;margin-left: 10px;">搜索类型：</span>

          <ul v-for ="(item, key) in listtitle">
            <li> {{item.searchNO}} </li>
          </ul>
          <br>
          
        </p>
          
          <p style="clear: both; margin-left:15px">搜索内容：
              <input type="" name="" v-model="searchVal">

              <button>搜索</button>
          </p>
          
      </div>
        
    </div>

  </div>
</template>



<script>

export default{

    data(){
        return { 
          data1: '', 
          searchVal:'', //默认输入为空
          value1: '',
          value2: '',
          value3: '',
          value4: '',
          selected: '',

          listtitle:[
            {searchNO:"订单号" },
            {searchNO:"运单号" },
            {searchNO:"收件人" },
            {searchNO:"产品SKU" },
            {searchNO:"采购账号" },
            {searchNO:"采购运单号" },
          ],
          

          options1: [
            {
              value: '选项1',
              label: '价格出错'
            }, 
            {
              value: '选项2',
              label: '客人需要收据'
            }, 
            {
              value: '选项3',
              label: '缺货'
            }, 
            {
              value: '选项4',
              label: '客人要求改尺寸'
            }, 
            {
              value: '选项5',
              label: '客人要求改颜色'
            },
            {
              value: '选项5',
              label: '联系不上客户'
            }
          
          ],
          
            

          options2:[
            {
              value: '选项1',
              label: '正常'
            }, 
            {
              value: '选项2',
              label: '寄顺丰'
            }, 
            {
              value: '选项3',
              label: '超材'
            }, 
            {
              value: '选项4',
              label: '已购买'
            }
          
          ],

          options3:[
            {
              value: '选项1',
              label: '2587953548sagv'
            }, 
            {
              value: '选项2',
              label: '25953548sagva'
            }, 
            {
              value: '选项3',
              label: '7953548sagva'
            }, 
            {
              value: '选项2',
              label: '25953548sagva'
            }, 
            {
              value: '选项3',
              label: '7953548sagva'
            }, 
            {
              value: '选项4',
              label: '258795348sagva'
            }

          ],
          options4:[
            {
              value: '选项1',
              label: '待发货'
            }, 
            {
              value: '选项2',
              label: '已发货'
            }, 
            {
              value: '选项3',
              label: '已取消'
            }, 
            {
              value: '选项2',
              label: '已完成'
            } 
            
          ]
         
        }

    },

    methods:{

      
    },


    computed:{

        // 搜索框
        list: function(){
            var _this = this;
            //逻辑-->根据input的value值筛选listtitle中的数据
            var arrByZM = [];//声明一个空数组来存放数据
            for (var i=0;i<this.listtitle.length;i++){
              //for循环数据中的每一项（根据name值）
              if(this.listtitle[i].searchNO.search(this.searchVal) != -1){
                //判断输入框中的值是否可以匹配到数据，如果匹配成功
                arrByZM.push(this.listtitle[i]);
                //向空数组中添加数据
              }
            }
            //一定要记得返回筛选后的数据
            return arrByZM;
          }

    }


}

</script>


<style scoped>
.top{
  border: 1px solid; 
  font-size: 14px;
  margin-top:10px;
}

.reposition{
    padding:10px;
    background-color:#F2F2F2;
}


.psearch,.insearch{
    width: 100%;
    border: 1px solid; 
    padding:15px 0;  
}
.insearch ul{
  width:80%;
}
.insearch ul li{
  width:80px;
  float:left;
  margin:10px;
  list-style: none;
}



.top input,.top span{
    padding: 5px;
    margin: 5px;
}


button{
    padding: 6px 30px;
    border-radius: 10px;
}


.el-input__inner{
  
   
}

.el-select,.el-select>.el-input{
  height: 30px;
  line-height: 30px;
}





</style>