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
          @click="handleCheck(scope.$index, scope.row)">签到</el-button>
      </template>
    </el-table-column>
    </el-table>
  </template>
  
  <script>
    export default {
      data() {
        return {
          tableData: [{
            "name": "欧阳小花",
            "id": "0001",
            "date": "2023-05-13",
            "phone": "18888888888",
            "gender": "女"
        },
        {
            "name": "赵子龙",
            "id": "0002",
            "date": "2023-05-13",
            "phone": "12134512545",
            "gender": "男"
        }],
        value:'100'
        }
      },
      methods:{
        handleCheck(index, row) {
            console.log(index, row);
        }
      }
    //   methods:{
    //     gettableData(){
    //         this.$axios.post('http://localhost:8080/ReservePatient').then((res) => {
    //             this.tableData = res.data;//把传回来数据赋给packData

    //         }).catch(function(error){
    //             console.log(error);
    //         })
    //     }
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
//    }
    }
  </script>