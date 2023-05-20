package com.tjsystem.controller;

import com.tjsystem.pojo.Result;
import com.tjsystem.service.SetCheckStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tjsystem.pojo.Result_to_upload;

@RestController
public class SetCheckStateController {
    @Autowired
    private SetCheckStateService setCheckStateService;

    @PutMapping("/setCheckState")
    public Result SetCheckState(@RequestBody Result_to_upload result_to_upload) {
        //调用Service获取查询结果
        System.out.println("获取到的数据：" + result_to_upload.getPatient_id() + " " + result_to_upload.getIndicator_name() +
                " " + result_to_upload.getDoctor_id());
        if(setCheckStateService.setCheckState(result_to_upload.getPatient_id(), result_to_upload.getIndicator_name()
                , result_to_upload.getDoctor_id()))
            return Result.success();
        else
            return Result.error("待上传结果更新失败!");
    }

}
