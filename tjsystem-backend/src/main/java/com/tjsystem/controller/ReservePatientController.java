package com.tjsystem.controller;

import com.tjsystem.pojo.ReservePatient;
import com.tjsystem.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tjsystem.service.ReservePatientService;

import java.util.List;

@RestController
public class ReservePatientController {

    @Autowired
    private ReservePatientService reservePatientService;

    @GetMapping("/ReservePatient")
    public Result getReservePatient(String Reserve_date){
        System.out.println("获取到的数据："+Reserve_date);
        List<ReservePatient> result;
        result = reservePatientService.getAllReservePatients(Reserve_date);
        if(!result.isEmpty())
        {
            return Result.success(result);
        }
        else
            return Result.error("该时间段无预约人员!");
    }

}
