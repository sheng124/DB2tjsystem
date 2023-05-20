package com.tjsystem.mapper;

import com.tjsystem.pojo.ReservePatient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.List;

@Mapper
public interface ReservePatientMapper {
    @Select("select distinct \"Order_patient-id\",\"Patient_name\",\"Patient_gender\",\"Patient_phone\",\"Order_date\" from \"Patient\",\"Order\"\n" +
            "where \"Order_date\" = #{Reserve_date} and \"Order_patient-id\" = \"Patient_id\" and \"Order_ischeck\" = false;")
    @Results(id = "patientResultMap", value = {
            @Result(property = "Patient_id", column = "Order_patient-id"),
            @Result(property = "Patient_name", column = "Patient_name"),
            @Result(property = "Patient_gender", column = "Patient_gender"),
            @Result(property = "Patient_phone", column = "Patient_phone"),
            @Result(property = "Reserve_date", column = "Order_date")
    })
    List<ReservePatient> getReservePatients(String Reserve_Date);

}
