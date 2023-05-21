<template>
  <div>
    <el-table
      :data="userUploadData"
      style="width: 100%"
    >
      <el-table-column
        label="用户ID"
        width="220"
      >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>用户ID: {{ scope.row.patientID }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.patientID }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="用户姓名"
        width="230"
      >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>用户姓名: {{ scope.row.patientName }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.patientName }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="项目名称"
        width="290"
      >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>项目名称: {{ scope.row.projectName }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.projectName }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="项目内容"
        width="290"
      >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>项目内容: {{ scope.row.indicatorName }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.indicatorName }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.uploadState === false"
            size="mini"
            type="primary"
            @click="handleUpload(scope.$index, scope.row)"
          >上传结果</el-button>
          <el-button
            v-if="scope.row.uploadState"
            disabled
            size="mini"
            type="success"
          >已上传结果</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="formTitle" :visible.sync="dialogFormVisible">
      <el-form :model="formData">
        <el-form-item label="检查结果：" :label-width="formLabelWidth">
          <el-input v-model="formData.checkResult" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="uploadFromData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import store from '@/store'
import { getUploadInfo, UploadFormData } from '@/api/user'
export default {
  data() {
    return {
      doctorID: store.getters.name,
      userUploadData: [
        {
          patientID: '0001',
          patientName: '用户0001',
          projectName: '项目0001',
          indicatorName: '项目内容0001',
          uploadState: false
        }
      ],
      dialogFormVisible: false,
      formData: {
        patientID: '',
        indicatorName: '',
        checkResult: ''
      },
      formLabelWidth: '120px',
      formTitle: '检查内容：',
      fromIndex: 0

    }
  },
  mounted: function() {
    this.userUploadData = []
    this.init()
  },
  methods: {
    handleUpload(index, row) {
      this.formTitle = '检查内容：' + row.indicatorName
      this.fromIndex = index
      this.dialogFormVisible = true
      this.formData.patientID = row.patientID
      this.formData.indicatorName = row.indicatorName
      console.log(index)
      console.log(row)
    },
    init() {
      getUploadInfo(this.doctorID).then(response => {
        console.log(response)
        const { data } = response
        console.log(data)
        this.userUploadData = data
      })
    },
    uploadFromData() {
      this.dialogFormVisible = false
      this.userUploadData[this.fromIndex].uploadState = true
      console.log(this.formData)
      UploadFormData(this.formData).then(response => {
        console.log(response)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
</style>

