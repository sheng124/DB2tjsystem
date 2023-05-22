package com.tjsystem.service.Implement;

import com.tjsystem.pojo.PatientCheckInfo;
import com.tjsystem.service.GetUserCheckInfoService;
import org.springframework.stereotype.Service;
import com.tjsystem.mapper.CheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.tjsystem.pojo.CheckInfo;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetUserCheckInfoServiceImplements implements GetUserCheckInfoService{

    @Autowired(required = false)
    private CheckMapper checkMapper;

    @Override
    public PatientCheckInfo getUserCheckInfo(String doctor_id) {
        String Department_id = checkMapper.getDepartmentId(doctor_id);
        String Patient_id = checkMapper.getCurrentPatientId(Department_id);
        List<String> Projects = checkMapper.getProjects(Patient_id, Department_id);
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
