package com.tjsystem.service.Implement;

import com.tjsystem.pojo.PatientCheckInfo;
import com.tjsystem.service.GetUserCheckInfoService;
import org.springframework.stereotype.Service;
import com.tjsystem.mapper.CheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.tjsystem.pojo.CheckInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GetUserCheckInfoServiceImplements implements GetUserCheckInfoService{

    @Autowired(required = false)
    private CheckMapper checkMapper;

    @Override
    public PatientCheckInfo getUserCheckInfo(String doctor_id) {
        String Department_id = checkMapper.getDepartmentId(doctor_id);
        String Patient_id = checkMapper.getCurrentPatientId(Department_id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前时间
        Date now = new Date();
        // 使用 SimpleDateFormat 对象将当前时间格式化为字符串
        String formattedTime = sdf.format(now);
        List<String> Projects = checkMapper.getProjects(Patient_id, Department_id,formattedTime);
        List<CheckInfo> checkInfos = new ArrayList<>();
        for (String Project : Projects) {
            CheckInfo temp = new CheckInfo();
            temp.setProject_name(checkMapper.getProjectName(Project));
            temp.setIndicators(checkMapper.getIndicators(Project,Patient_id));
            temp.setProject_price(checkMapper.getProjectPrice(Project));
            checkInfos.add(temp);
        }
        PatientCheckInfo patientCheckInfo = new PatientCheckInfo();
        patientCheckInfo.setPatient_id(Patient_id);
        patientCheckInfo.setCheckInfos(checkInfos);
        patientCheckInfo.setPatient_name(checkMapper.getPatientName(Patient_id));
        return patientCheckInfo;
    }

}
