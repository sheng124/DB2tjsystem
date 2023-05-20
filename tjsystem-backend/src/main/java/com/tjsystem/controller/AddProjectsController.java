package com.tjsystem.controller;

import com.tjsystem.pojo.AddProjectInfo;
import com.tjsystem.pojo.Result;
import com.tjsystem.service.GetUserCheckInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tjsystem.service.AddProjectsService;

@RestController
public class AddProjectsController {
    @Autowired
    private AddProjectsService addProjectsService;

    @GetMapping("/ProjectsToAdd")
    public Result ProjectsToAdd(String patient_id, String doctor_id) {
        //调用Service获取查询结果
        System.out.println("获取到的数据：" + patient_id + " " + doctor_id);
        return Result.success(addProjectsService.getProjectsToAdd(patient_id,doctor_id));
    }

    @PostMapping("/AddProject")
    public Result AddProject(@RequestBody AddProjectInfo addProjectInfo) {
        //调用Service获取查询结果
        System.out.println("获取到的数据：" + addProjectInfo.getPatient_id() + " " + addProjectInfo.getProject_name());
        if(addProjectsService.addProject(addProjectInfo.getPatient_id()
                ,addProjectInfo.getProject_name()))
            return Result.success();
        else
            return Result.error("添加失败");
    }
}
