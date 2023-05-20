package com.tjsystem.service.Implement;

import com.tjsystem.mapper.CheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SetCheckStateServiceImplement implements com.tjsystem.service.SetCheckStateService{
    @Autowired(required = false)
    private CheckMapper checkMapper;
    @Override
    public boolean setCheckState(String patient_id, String indicator_name, String doctor_id){
        Date date = new Date();
        return checkMapper.CheckAndUpload(patient_id, indicator_name, doctor_id, date);
    }
}
