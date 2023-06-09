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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前时间
        Date now = new Date();
        // 使用 SimpleDateFormat 对象将当前时间格式化为字符串
        String formattedTime = sdf.format(now);
        return signInAndLineUpMapper.SignIn(Patient_id, formattedTime);
    }

    @Override
    public boolean LineUp(String Patient_id)
    {
        List<String> Departments;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前时间
        Date now = new Date();
        // 使用 SimpleDateFormat 对象将当前时间格式化为字符串
        String formattedTime1 = sdf1.format(now);
        Departments = signInAndLineUpMapper.Department_idForLineUp(Patient_id, formattedTime1);

        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        String formattedTime2 = sdf2.format(now);

        return signInAndLineUpMapper.JoinQueue(Patient_id, Departments.get(0), formattedTime2);
    }

    @Override
    public boolean UpdateQueue(QueueInfo queueInfo){
        List<String> Departments;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前时间
        Date now = new Date();
        // 使用 SimpleDateFormat 对象将当前时间格式化为字符串
        String formattedTime1 = sdf1.format(now);
        Departments = signInAndLineUpMapper.Department_idForLineUp(queueInfo.getPatient_id(), formattedTime1);
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        // 使用 SimpleDateFormat 对象将当前时间格式化为字符串
        String formattedTime2 = sdf2.format(now);
        int index = Departments.indexOf(signInAndLineUpMapper.Department_idForUpdate(queueInfo.getDoctor_id()));
        if(index == Departments.size()-1)
            return signInAndLineUpMapper.CheckOver(queueInfo.getPatient_id());
        else
            return signInAndLineUpMapper.UpdateQueue(queueInfo.getPatient_id(), Departments.get(index+1),formattedTime2);
    }
}
