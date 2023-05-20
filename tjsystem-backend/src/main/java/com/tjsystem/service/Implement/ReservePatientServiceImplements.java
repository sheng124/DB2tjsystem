package com.tjsystem.service.Implement;

import com.tjsystem.mapper.ReservePatientMapper;
import com.tjsystem.mapper.UserMapper;
import com.tjsystem.pojo.ReservePatient;
import com.tjsystem.service.ReservePatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservePatientServiceImplements implements ReservePatientService {
    @Autowired(required = false)
    private ReservePatientMapper reservePatientMapper;

    @Override
    public List<ReservePatient> getAllReservePatients(String Reserve_Date)
    {
        return reservePatientMapper.getReservePatients(Reserve_Date);
    }

}
