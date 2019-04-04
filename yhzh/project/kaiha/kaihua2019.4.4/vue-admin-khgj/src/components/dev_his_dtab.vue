<template>
  <div style="width: 1000px;margin: 10px auto;">
    <div style="float: left;">
      <template v-for='(floorScope,index) in floorScopeList'>
        <div style="margin-top: 15px">
          <el-button type="info" :disabled="floorBtnCho==index" size="mini" @click="queryFloors(index)">
            {{getFloorBtnText(floorScope.floor1,floorScope.floor2)}}
          </el-button>
        </div>
      </template>
    </div>
    <div style="float: left; width: 860px;margin-left: 30px">

      <!-- model 表单数据对象  object

        inline  行内表单模式  boolean — false

        label-position  表单域标签的位置，如果值为 left 或者 right 时，则需要设置 label-width string  right/left/top  right

        label-width 表单域标签的宽度，作为 Form 直接子元素的 form-item 会继承该值 string

        hide-required-asterisk  是否显示必填字段的标签旁边的红色星号  boolean — false


       -->
      <el-form ref="form" :model="form" label-width="80px" :inline="true" label-position="right" 
        :hide-required-asterisk="true" style="text-align: left;">

        <!-- required  是否必填，如不设置，则会根据校验规则自动生成  boolean — false 
        -->
        <el-form-item label="登记日期" :required="true" size="mini" style="margin-bottom: 0px;">

          <!-- value / v-model 绑定值 date(DatePicker) / array(DateRangePicker)

            type  显示类型  string  year/month/date/dates/ week/datetime/datetimerange/daterange  date

            format  显示在输入框中的格式  string  见日期格式 yyyy-MM-dd

            value-format  可选，绑定值的格式。不指定则绑定值为 Date 对象  string  见日期格式

            change  用户确认选定的值时触发 组件绑定值。格式与绑定值一致，可受 value-format 控制

           -->
          <el-date-picker
                v-model="form.recDate"
                type="date"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd" style="width: 130px"
                @change="qryRecTimeList"
                placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="登记时间" :required="true" size="mini" style="margin-bottom: 0px;">

          <!-- clearable 是否可以清空选项  boolean — false 
          -->
          <el-select clearable style="width: 100px" v-model="form.recTime" placeholder="请选择">
            <el-option v-for="item in recTimeList" :key="item.recTime" :label="item.recTime" :value="item.recTime">
            </el-option>
          </el-select>
        </el-form-item>
        <el-button type="primary" size="mini" @click="qryHisPointData">查询</el-button>
      </el-form>
      <div style="box-shadow:0 0 6px #ddd;">
        <h2 style="height: 50px;line-height: 50px;vertical-align: center;margin-bottom: 0px; background-color: white; color: red;">{{tabTitle}}</h2> 
        <div>
          <el-table
            :data="devPointData1"
            ref="multipleTable"
            border
            :row-style="tableRowStyle"
            :header-cell-style="tableHeaderStyle"
            tooltip-effect="dark"
            style="width: 50%;float: left;overflow: hidden;">
            <el-table-column prop="devEnName" label="设备编号" :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column prop="devCnName" label="设备名称" :show-overflow-tooltip="true">
            </el-table-column>
            
            <template v-for='(col) in devAttrData'>
              <el-table-column :show-overflow-tooltip="true" :prop="col.devAtrrId" :label="col.attrCnName"
              :width="getColStyle(col.attrWidth)">
                <template scope="scope">
                  <!-- 属性数据字典不为空，则将值转为对应字典值显示 -->
                  <span style="color: red" v-if="col.attrDict != ''">
                    {{col.dictData[scope.row[col.devAtrrId]]}}
                  </span>
                  <span style="color: red" v-else>{{scope.row[col.devAtrrId]}}</span>
                  <!-- 单位不为空则显示单位 -->
                  <span v-if="col.attrUnit != ''">{{col.attrUnit}}</span>
                </template> 
              </el-table-column>
            </template>
          </el-table>
          <el-table
            :data="devPointData2"
            ref="multipleTable"
            border
            :row-style="tableRowStyle"
            :header-cell-style="tableHeaderStyle"
            tooltip-effect="dark"
            style="width: 50%; float: left;overflow: hidden;">
            <el-table-column prop="devEnName" label="设备编号" :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column prop="devCnName" label="设备名称" :show-overflow-tooltip="true">
            </el-table-column>
            <template v-for='(col) in devAttrData'>
              <el-table-column :show-overflow-tooltip="true" :prop="col.devAtrrId" :label="col.attrCnName"
              :width="getColStyle(col.attrWidth)">
                <template scope="scope">
                  <!-- 属性数据字典不为空，则将值转为对应字典值显示 -->
                  <span style="color: red" v-if="col.attrDict != ''">
                    {{col.dictData[scope.row[col.devAtrrId]]}}
                  </span>
                  <span style="color: red" v-else>{{scope.row[col.devAtrrId]}}</span>
                  <!-- 单位不为空则显示单位 -->
                  <span v-if="col.attrUnit != ''">{{col.attrUnit}}</span>
                </template> 
              </el-table-column>
            </template>
          </el-table>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import util from '../common/js/util';
  import { getRecTimeList,getHisData } from '../service/getData.js';
  export default {
      props: ['devtypeid','tabTitle','floorScopeList',"devAttrData"],
      data() {
        return {
          devPointData1:[], //设备及点表信息1
          devPointData2:[], //设备及点表信息2
          recTimeList:[], //记录时间列表
          floor1: "", //楼层从
          floor2: "", //楼层到
          floorBtnCho: 0, //当前选中的楼层按钮号
          form:{
            recDate: util.formatDate.format(new Date()), //登记日期，默认当天
            recTime: ""
          }
        }
      },
      created(){
          var that = this;
          that.floor1 = that.floorScopeList[0].floor1;
          that.floor2 = that.floorScopeList[0].floor2;
          //查询记录时间列表
          that.qryRecTimeList();
      },
      methods: {
        //设置楼层选择按钮显示文字
        getFloorBtnText(f1,f2){
          let fl1 = f1.replace("-","B");
          let fl2 = f2.replace("-","B");
          if(f1 === f2){
            return fl1;
          }else{
            return fl1 + "~" + fl2 + "F";
          }
        },
        //楼层按钮点击事件
        queryFloors(index){
          var that = this;
          that.floorBtnCho = index; //选中按钮的序号
          that.floor1 = that.floorScopeList[index].floor1;
          that.floor2 = that.floorScopeList[index].floor2;
        },
        //设置表格列的宽度
        getColStyle(colWidth){
          if(!util.isNull(colWidth)){
            return colWidth + "px";
          }
        },
            // 修改table tr行的背景色    
        tableRowStyle({ row, rowIndex }){
          //return 'background-color: #F7F6Fd'
        },
        //修改table header的样式
        tableHeaderStyle({row, column, rowIndex, columnIndex }){
          var that = this;
        },
        //查询记录时间列表
        qryRecTimeList(){
          var that = this;
          console.log("登记日期为",that.form.recDate);
          if(util.isNull(that.form.recDate)){
            that.form.recTime = "";
            that.recTimeList = [];
            return false;
          }
          getRecTimeList(that.devtypeid,that.form.recDate,res=>{
                  console.log("获取记录时间列表",res.data);
                  that.form.recTime = "";
                  that.recTimeList = res.data;
          });
        },

        /**
      *查询历史数据
      */
      qryHisPointData(){
        var that = this;
        if(util.isNull(that.form.recDate)){
            that.$message({
              type: 'error',
                message: '请选择登记日期'
            });
          return false;
        }
        if(util.isNull(that.form.recTime)){
          that.$message({
            type: 'error',
                 message: '请选择登记时间'
            });
          return false;
        }
        /*getHisData(that.devtypeid,that.floor1,that.floor2,that.form.recDate,that.form.recTime,res=>{
          console.log("查询到历史数据",res.data);
          that.recDateTime = that.form.recDate + " " + that.form.recTime;
          that.devPointData = res.data;
        }); */
      },
      }
      
    }
</script>
<style >
  /*查询条件中label文字颜色*/
  .el-form-item__label{
    color: white;
  }
 
</style>