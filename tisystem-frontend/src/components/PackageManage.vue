<template>
    <div>
    <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        prop="Combo_SingleProject_id"
        label="套餐编号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="Combo_SingleProject_project_id"
        label="单项编号">
      </el-table-column>
    </el-table>
  
    <el-button type="primary" round @click="centerDialogVisible = true">添加套餐</el-button>
  
    <el-dialog
    title="输入信息"
    :visible.sync="centerDialogVisible"
    width="30%"
    center>
    <span>填写套餐信息</span>
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
                Combo_SingleProject_id:'0001',
                Combo_SingleProject_project_id:'0002',
            }
          ],
          centerDialogVisible :false
        }
      }
    }
  </script>