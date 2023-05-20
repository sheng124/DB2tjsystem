package com.tjsystem.controller;

import com.tjsystem.pojo.QueueInfo;
import com.tjsystem.pojo.Result;
import com.tjsystem.service.SignInAndLineUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInAndLineUpController {
    @Autowired
    private SignInAndLineUpService signInAndLineUpService;

    @PostMapping("/SignIn")
    public Result SignIn(@RequestBody String Patient_id){
        System.out.println("获取到的数据："+Patient_id);
        boolean result1 = signInAndLineUpService.SignIn(Patient_id);
        if(result1)
        {
            boolean result2 = signInAndLineUpService.LineUp(Patient_id);
            if(result2)
            {
                return Result.success();
            }
            else
                return Result.error("加入队列失败!");
        }
        else
            return Result.error("签到失败!");
    }

    @PostMapping("/UpdateQueue")
    public Result UpdateQueue(@RequestBody QueueInfo queueInfo)
    {
        System.out.println("获取到的数据："+queueInfo.getPatient_id()+" "+queueInfo.getCurrent_department_id());
        boolean result = signInAndLineUpService.UpdateQueue(queueInfo);
        if(result)
        {
            return Result.success();
        }
        else
            return Result.error("队列更新失败!");
    }

}
