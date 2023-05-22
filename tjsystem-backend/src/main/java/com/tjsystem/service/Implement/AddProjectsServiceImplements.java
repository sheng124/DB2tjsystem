package com.tjsystem.service.Implement;

import com.tjsystem.mapper.AddProjectsMapper;
import com.tjsystem.mapper.CheckMapper;
import com.tjsystem.pojo.CheckInfo;
import com.tjsystem.service.AddProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddProjectsServiceImplements implements AddProjectsService {

    @Autowired(required = false)
    private AddProjectsMapper addProjectsMapper;

    @Override
    public List<CheckInfo> getProjectsToAdd(String patient_id, String doctor_id) {
        String Department_id = addProjectsMapper.getDepartment_id(doctor_id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前时间
        Date now = new Date();
        // 使用 SimpleDateFormat 对象将当前时间格式化为字符串
        String formattedTime = sdf.format(now);
        List<String> Projects = addProjectsMapper.getProjectsToAdd(patient_id, Department_id,formattedTime);
        List<CheckInfo> checkInfos = new ArrayList<>();
        for (String Project : Projects) {
            CheckInfo temp = new CheckInfo();
            temp.setProject_name(addProjectsMapper.getProjectName(Project));
            temp.setIndicators(addProjectsMapper.getIndicators(Project));
            temp.setProject_price(addProjectsMapper.getProjectPrice(Project));
            checkInfos.add(temp);
        }
        return checkInfos;
    }
    @Override
    public boolean addProject(String patient_id, String project_name) {
        String currentId = addProjectsMapper.getMaxOrderId();
        String Project_id = addProjectsMapper.getProjectId(project_name);

        // 构造新订单号
        String prefix = "O";
        int serialNum = Integer.parseInt(currentId.substring(1));
        int nextSerialNum = serialNum + 1;
        String nextId = prefix + String.format("%04d", nextSerialNum);

        // 获取当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前时间
        Date now = new Date();
        // 使用 SimpleDateFormat 对象将当前时间格式化为字符串
        String formattedTime = sdf.format(now);

        return addProjectsMapper.addProject(nextId,patient_id,Project_id,formattedTime);
    }


}
