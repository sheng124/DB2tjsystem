package com.tjsystem.mapper;

import com.tjsystem.pojo.UploadInfo;
import com.tjsystem.pojo.UploadResultInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UploadMapper {

    @Select("select \"Patient\".\"Patient_id\" patientID,\"Patient\".\"Patient_name\" patientName,\"Result\".\"Result_indicator-name\" indicatorName,\"Project\".\"Project_name\" projectName from \"Result\",\"Indicator\",\"Project\",\"Doctor\",\"Patient\"\n" +
            "where \"Result_value\" is null\n" +
            "  and \"Result\".\"Result_indicator-name\"=\"Indicator\".\"Indicator_name\"\n" +
            "  and \"Result\".\"Result_patient-id\"=\"Patient\".\"Patient_id\"\n" +
            "  and \"Indicator\".\"Indicator_project-id\"=\"Project\".\"Project_id\"\n" +
            "  and \"Project\".\"Project_department-id\"=\"Doctor\".\"Doctor_department-id\"\n" +
            "  and \"Doctor\".\"Doctor_id\"= #{Doctor_id}")
    List<UploadInfo> getByDoctorID(String Doctor_id);

    @Update("update \"Result\"\n" +
            "set \"Result_value\" = #{checkResult}\n" +
            "where \"Result_patient-id\" = #{patientID}\n" +
            "and \"Result_indicator-name\"=#{indicatorName}")
    boolean setByformData(UploadResultInfo uploadResultInfo);

}