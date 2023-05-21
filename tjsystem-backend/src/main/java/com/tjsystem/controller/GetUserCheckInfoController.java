package com.tjsystem.controller;

import com.tjsystem.pojo.Result;
import com.tjsystem.service.GetUserCheckInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class GetUserCheckInfoController {
    @Autowired
    private GetUserCheckInfoService getUserCheckInfoService;

    @GetMapping("/userCheckInfo")
    public Result GetUserCheckInfo(String doctor_id) {
        //调用Service获取查询结果
        System.out.println("获取到的数据：" + doctor_id);
        return Result.success(getUserCheckInfoService.getUserCheckInfo(doctor_id));
    }
}
