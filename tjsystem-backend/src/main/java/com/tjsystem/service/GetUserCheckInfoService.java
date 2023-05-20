package com.tjsystem.service;

import java.util.List;
import com.tjsystem.pojo.CheckInfo;
import com.tjsystem.pojo.PatientCheckInfo;

public interface GetUserCheckInfoService {

        PatientCheckInfo getUserCheckInfo(String doctor_id);
}
