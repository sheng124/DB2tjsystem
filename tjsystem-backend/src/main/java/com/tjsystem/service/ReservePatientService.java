package com.tjsystem.service;

import com.tjsystem.pojo.ReservePatient;
import java.util.List;

public interface ReservePatientService {

    List<ReservePatient> getAllReservePatients(String Reserve_Date);

}
