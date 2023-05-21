package com.tjsystem.service;

import com.tjsystem.pojo.UploadInfo;
import com.tjsystem.pojo.UploadResultInfo;

import java.util.List;

public interface UserUploadService {

    List<UploadInfo> getUserUploadInfo(String Doctor_id);

    boolean setUploadFormData(UploadResultInfo uploadResultInfo);
}
