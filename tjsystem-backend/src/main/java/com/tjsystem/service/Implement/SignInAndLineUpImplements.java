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
    public boolean SignIn(int Reserve_id)
    {
        return signInAndLineUpMapper.SignIn(Reserve_id);
    }

    @Override
    public boolean LineUp(int Reserve_id)
    {
        List<String> Departments;
        Departments = signInAndLineUpMapper.Department_idForLineUp(Reserve_id);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        // 获取当前时间
        Date now = new Date();
        // 使用 SimpleDateFormat 对象将当前时间格式化为字符串
        String formattedTime = sdf.format(now);

        return signInAndLineUpMapper.JoinQueue(signInAndLineUpMapper.Patient_idForLineUp(Reserve_id), Departments.get(0)
                , formattedTime,Reserve_id);
    }

    @Override
    public boolean UpdateQueue(QueueInfo queueInfo){
        int reserve_id = signInAndLineUpMapper.getReserve_id(queueInfo.getPatient_id());
        List<String> Departments;
        Departments = signInAndLineUpMapper.Department_idForLineUp(reserve_id);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        // 获取当前时间
        Date now = new Date();
        // 使用 SimpleDateFormat 对象将当前时间格式化为字符串
        String formattedTime = sdf.format(now);
        int index = Departments.indexOf(queueInfo.getCurrent_department_id());
        return signInAndLineUpMapper.UpdateQueue(queueInfo.getPatient_id(), Departments.get(index+1),formattedTime);
    }
}
