package com.tjsystem.mapper;

import com.tjsystem.pojo.ReservePatient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.List;

@Mapper
public interface ReservePatientMapper {
    @Select("select reserve_id,\"Patient_name\",\"Patient_gender\",\"Patient_phone\",\"reserve_date\" from \"Patient\",reservation\n" +
            "where reserve_date = #{Reserve_Date} and reserve_patient_id = \"Patient_id\" and isCheck = false;")
    @Results(id = "patientResultMap", value = {
            @Result(property = "Reserve_id", column = "reserve_id"),
            @Result(property = "Patient_name", column = "Patient_name"),
            @Result(property = "Patient_gender", column = "Patient_gender"),
            @Result(property = "Patient_phone", column = "Patient_phone"),
            @Result(property = "Reserve_date", column = "reserve_date")
    })
    List<ReservePatient> getReservePatients(String Reserve_Date);

}
