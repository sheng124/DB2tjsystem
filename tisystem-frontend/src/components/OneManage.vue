<!--
 * @Author: wyh 1370804207@qq.com
 * @Date: 2023-05-16 16:09:57
 * @LastEditors: wyh 1370804207@qq.com
 * @LastEditTime: 2023-05-16 17:15:09
 * @FilePath: \tisystem-frontend\src\components\OneManage.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->

<template>
  <div>
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      prop="SingleProject_id"
      label="单项编号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="SingleProject_name"
      label="单项名称"
      width="180">
    </el-table-column>
    <el-table-column
      prop="SingleProject_price"
      label="单项费用"
      width="180">
    </el-table-column>
    <el-table-column
      prop="SingleProject_department_id"
      label="科系编号">
    </el-table-column>
  </el-table>

  <el-button type="primary" round @click="centerDialogVisible = true">添加单项</el-button>

  <el-dialog
    title="输入信息"
    :visible.sync="centerDialogVisible"
    width="30%"
    center>
    <span>填写单项信息</span>
    <span slot="footer" class="dialog-footer">
      <el-button @click="centerDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
    </span>
  </el-dialog>

  </div>

</template>






<script>
  export default {
    data() {
      return {
        tableData: [
          {
            SingleProject_id:'0001',
            SingleProject_name:'内科检查',
            SingleProject_price:'100rmb',
            SingleProject_department_id:'0001',
          }
        ],
        centerDialogVisible: false
      }
    }
  }
</script>