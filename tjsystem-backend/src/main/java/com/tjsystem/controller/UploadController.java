package com.tjsystem.controller;

import com.tjsystem.pojo.Result;
import com.tjsystem.pojo.UploadInfo;
import com.tjsystem.pojo.UploadResultInfo;
import com.tjsystem.service.UserUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UploadController {

    @Autowired
    private UserUploadService userUploadService;

    @GetMapping("/userUploadInfo")
    public Result getUserUploadInfo(String Doctor_id){
        //调用Service获取查询结果
        System.out.println("获取到的数据："+Doctor_id);
        List<UploadInfo> uploadInfoList=userUploadService.getUserUploadInfo(Doctor_id);
        System.out.println("Service处理的结果："+uploadInfoList);
        //封装Result返回数据
        if(!uploadInfoList.isEmpty()){
            return Result.success(uploadInfoList);
        }
        else{
            return Result.error("没有待上传的体检项目");
        }
    }

    @PutMapping("/UploadFormData")
    public Result uploadFormData(@RequestBody UploadResultInfo uploadResultInfo){
        //调用Service获取查询结果
        System.out.println("获取到的数据："+uploadResultInfo);
        boolean uploadResult=userUploadService.setUploadFormData(uploadResultInfo);
        System.out.println("Service处理的结果："+uploadResult);
        //封装Result返回数据
        if(uploadResult){
            return Result.success();
        }
        else{
            return Result.error("上传检查结果失败");
        }
    }
}
