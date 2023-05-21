package com.tjsystem.service.Implement;

import com.tjsystem.pojo.QueueInfo;
import com.tjsystem.mapper.SignInAndLineUpMapper;
import com.tjsystem.service.SignInAndLineUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SignInAndLineUpImplements implements SignInAndLineUpService {
    @Autowired(required = false)
    private SignInAndLineUpMapper signInAndLineUpMapper;

    @Override
    public boolean SignIn(String Patient_id)
    {
        return signInAndLineUpMapper.SignIn(Patient_id);
    }

    @Override
    public boolean LineUp(String Patient_id)
    {
        List<String> Departments;
        Departments = signInAndLineUpMapper.Department_idForLineUp(Patient_id);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        // 获取当前时间
        Date now = new Date();
        // 使用 SimpleDateFormat 对象将当前时间格式化为字符串
        String formattedTime = sdf.format(now);

        return signInAndLineUpMapper.JoinQueue(Patient_id, Departments.get(0), formattedTime);
    }

    @Override
    public boolean UpdateQueue(QueueInfo queueInfo){
        List<String> Departments;
        Departments = signInAndLineUpMapper.Department_idForLineUp(queueInfo.getPatient_id());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        // 获取当前时间
        Date now = new Date();
        // 使用 SimpleDateFormat 对象将当前时间格式化为字符串
        String formattedTime = sdf.format(now);
        int index = Departments.indexOf(signInAndLineUpMapper.Department_idForUpdate(queueInfo.getDoctor_id()));
        if(index == Departments.size()-1)
            return signInAndLineUpMapper.CheckOver(queueInfo.getPatient_id());
        else
            return signInAndLineUpMapper.UpdateQueue(queueInfo.getPatient_id(), Departments.get(index+1),formattedTime);
    }
}
