package com.tjsystem.service.Implement;

import com.tjsystem.mapper.UploadMapper;
import com.tjsystem.pojo.UploadInfo;
import com.tjsystem.pojo.UploadResultInfo;
import com.tjsystem.service.UserUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserUploadServiceImplements implements UserUploadService {

    @Autowired(required = false)
    private UploadMapper uploadMapper;

    //获取医生待上传检查结果的项目名称、项目ID
    @Override
    public List<UploadInfo> getUserUploadInfo(String Doctor_id) {
        return uploadMapper.getByDoctorID(Doctor_id);
    }

    //将检查结果写入数据库
    @Override
    public boolean setUploadFormData(UploadResultInfo uploadResultInfo) {
        return uploadMapper.setByformData(uploadResultInfo);
    }
}
