<!--
 * @Author: wyh 1370804207@qq.com
 * @Date: 2023-05-16 13:15:54
 * @LastEditors: wyh 1370804207@qq.com
 * @LastEditTime: 2023-05-21 16:43:15
 * @FilePath: \tisystem-frontend\src\components\Qiandao.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>

  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      prop="name"
      label="姓名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="id"
      label="编号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="date"
      label="日期"
      width="180">
    </el-table-column>
    <el-table-column
      prop="phone"
      label="手机号码"
      width="280">
    </el-table-column>
    <el-table-column
      prop="gender"
      label="性别"
      width="180">
    </el-table-column>
    <el-table-column label="操作">
    <template slot-scope="scope">
      <el-button
        size="mini"
        @click="handleCheck(scope.$index, scope.row),centerDialogVisible = true">签到</el-button>
        <el-dialog
  title="提示"
  :visible.sync="centerDialogVisible"
  width="30%"
  center>
  <span>已签到成功！</span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
  </span>
</el-dialog>
    </template>
  </el-table-column>
  </el-table>

 

</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      tableData: [],
      value:'100',
      ID:{
        id:3
      },
      centerDialogVisible : false
    }
  },
  mounted(){
    this.gettableData()
  },
  methods:{
    handleCheck(index, row) {
        console.log(index, row);
        this.sendID();
        //centerDialogVisible = true
    },
    gettableData(){
        axios.get('http://localhost:8080/ReservePatient',{
          params:{
            Reserve_date:'2023-05-13'
          }
        }).then((res) => {
            //console.log(res.data)
            this.tableData = res.data.data;//把传回来数据赋给packData
            console.log(this.tableData)
            //console.log(res.data)
            //console.log(res.data.data)
            //console.log(res.data.code)

            
        }).catch(function(error){
            console.log(error);
        })
    },

    sendID(){
      axios.post('http://localhost:8080/SignIn',JSON.stringify(this.ID),{
        headers: {
          'Content-Type': 'application/json;charset=UTF-8'
	      }
      }).then((res) => {

      }).catch(function(error){
        console.log(error)
      })
    }

  }
} 
  </script>


        <!-- // sendID(){
        //   axios.post('http://localhost:8080/SignIn',{
        //     params:{

        //     }
        //   }).then((res) => {
               
        //     }).catch(function(error){
        //         console.log(error);
        //     })
        // }
      //}
    //   methods:{
    
    //   }

//       mounted() {
//   	    this.fetchdata()
//         },
//     methods: {
//     //获取后端数据
//     fetchdata() {
//       axios
//         .get("http://localhost:8080/ReservePatient")
//         .then(
//           response => {
//             this.tableData = response.data;
//           },
//           response => {
//             console.log("error");
//             alert("请求失败");
//           }
//         );
//     },
//    } -->
