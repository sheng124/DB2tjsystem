<template>
  <div>
    <el-row type="flex" align="middle">
      <el-col :span="6"><h1>用户ID:{{ patient_id }}</h1></el-col>
      <el-col :span="7"><h1>用户姓名:{{ patient_name }}</h1></el-col>
      <el-col :span="7"><h1>当前科室:{{ ksName }}</h1></el-col>
      <el-col :span="4">
        <el-button type="primary" icon="el-icon-plus" @click="getProjects">增加体检项目</el-button>
      </el-col>
    </el-row>
    <el-table
      :data="userCheckData"
      style="width: 100%"
    >
      <el-table-column
        label="项目名称"
        width="540"
      >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>项目名称: {{ scope.row.project_name }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.project_name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="项目内容"
        width="540"
      >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>项目内容: {{ scope.row.indicator_name }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.indicator_name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.isCheck === false"
            size="mini"
            type="primary"
            @click="handleCheck(scope.$index, scope.row)"
          >检查</el-button>
          <el-button
            v-if="scope.row.isCheck"
            disabled
            size="mini"
            type="success"
          >已检查</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 对话框 -->
    <el-dialog title="体检项目" :visible.sync="outerVisible">
      <el-dialog
        width="50%"
        title="体检内容"
        :visible.sync="innerVisible"
        append-to-body
      >
        <el-table :data="indicatorData">
          <el-table-column property="projectName" label="项目名称" width="340" />
          <el-table-column property="indicatorName" label="项目内容" width="340" />
        </el-table>
      </el-dialog>
      <!-- 付费 -->
      <el-dialog
        width="50%"
        title="支付费用"
        :visible.sync="innerInnerVisible"
        append-to-body
      >
        <el-button
          v-if="!isafford"
          size="mini"
          type="primary"
          @click="isafford=true"
        >支付</el-button>
        <el-button
          v-if="isafford"
          disabled
          size="mini"
          type="success"
        >已支付</el-button>
      </el-dialog>
      <el-table :data="projectData">
        <el-table-column property="projectName" label="项目名称" width="240" />
        <el-table-column property="projectPrice" label="项目价格" width="230" />
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="primary"
              @click="getIndicatorData(scope.$index, scope.row)"
            >查看体检内容</el-button>
            <el-button
              v-if="scope.row.isAdd === false"
              size="mini"
              type="primary"
              @click="addProjectVue(scope.$index, scope.row)"
            >增加</el-button>
            <el-button
              v-if="scope.row.isAdd"
              disabled
              size="mini"
              type="success"
            >已增加</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import { getCheckInfo, setCheckState, updateQueue, addProjects, addProject } from '@/api/user'
import store from '@/store'
import { thisExpression } from '@babel/types'
export default {
  data() {
    return {
      doctorID: store.getters.name,
      ksName: '',
      patient_id: '0001',
      patient_name: '欧阳小花',
      userCheckData: [],
      nowProjectName: '',
      waitCheckNum: 0, // 等于0时表示已经全部检查完成
      outerVisible: false,
      innerVisible: false,
      innerInnerVisible: false,
      projectData: [
        {
          projectName: '',
          projectPrice: '',
          isAdd: false,
          indicatorDataPro: [
            {
              projectName: '',
              indicatorName: ''
            }
          ]
        }
      ],
      indicatorData: [
        {
          projectName: '',
          indicatorName: ''
        }
      ],
      nowProjectName_dialog: '',
      nowProjectIndex: -1,
      isafford: false
    }
  },
  mounted: function() {
    this.init()
  },
  methods: {
    handleCheck(index, row) {
      console.log(index)
      console.log(row)
      this.userCheckData[index].isCheck = true
      var setCheckStateData = { patient_id: this.patient_id, indicator_name: row.indicator_name, doctor_id: this.doctorID }
      console.log(setCheckStateData)
      setCheckState(setCheckStateData)
      this.waitCheckNum--
      if (this.waitCheckNum === 0) {
        var tempData = { patient_id: this.patient_id, doctor_id: this.doctorID }
        updateQueue(tempData)
      }
    },
    dispatchDataSecond(value) {
      var nowUserCheckDataUnit = { project_name: this.nowProjectName, indicator_name: value, isCheck: false }
      this.waitCheckNum++
      this.userCheckData.push(nowUserCheckDataUnit)
    },
    dispatchDataFirst(value) {
      this.nowProjectName = value.project_name
      value.indicators.forEach(this.dispatchDataSecond) // 空的indicators不会调用
    },
    init() {
      getCheckInfo(this.doctorID).then(response => {
        console.log(response)
        const { data } = response
        console.log(data)
        // 用户姓名和id
        this.patient_id = data.patient_id
        this.patient_name = data.patient_name
        // 用户检查项目和内容
        const checkInfos = data.checkInfos
        checkInfos.forEach(this.dispatchDataFirst)
        console.log(checkInfos)
        console.log(this.userCheckData)
        //科室
        if(this.doctorID === 'D0003'){
          this.ksName = '肠胃肝脏科'
        } else{
          this.ksName = '血液内科'
        }
      })
    },
    dispatchDataSecond_dialog(value) {
      var indicatorDataUnit = { projectName: this.nowProjectName_dialog, indicatorName: value }
      this.projectData[this.nowProjectIndex].indicatorDataPro.push(indicatorDataUnit)
    },
    dispatchDataFirst_dialog(value) {
      var projectDataUnit = { projectName: value.project_name, projectPrice: value.project_price, isAdd: false, indicatorDataPro: [] }
      this.nowProjectName_dialog = value.project_name
      this.nowProjectIndex++
      this.projectData.push(projectDataUnit)
      value.indicators.forEach(this.dispatchDataSecond_dialog)
    },
    getProjects() {
      this.projectData = []
      this.indicatorData = []
      this.outerVisible = true
      addProjects(this.patient_id, this.doctorID).then((result) => {
        console.log(result)
        const { data } = result
        console.log(data)
        data.forEach(this.dispatchDataFirst_dialog)
        console.log('projectData')
        console.log(this.projectData)
      })
    },
    getIndicatorData(index, row) {
      this.innerVisible = true
      this.indicatorData = this.projectData[index].indicatorDataPro
      this.nowProjectName_dialog = row.projectName
    },
    addProjectVue(index, row) {
      this.isafford = false
      var addData = { patient_id: this.patient_id, project_name: row.projectName }
      console.log(addData)
      addProject(addData).then((result) => {
        console.log(result)
        this.innerInnerVisible = true
      })
      this.innerInnerVisible = true
    }
  }
}
</script>

<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
  }
</style>
