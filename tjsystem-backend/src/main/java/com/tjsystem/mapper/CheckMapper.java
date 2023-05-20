package com.tjsystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface CheckMapper {
    //获取医生的科室id
    @Select("select \"Doctor_department-id\" from \"Doctor\" where \"Doctor_id\" = #{doctor_id};")
    String getDepartmentId(String doctor_id);
    //获取当前科室的当前病人id
    @Select("select \"Patient_id\" from \"Lineup\" where \"Current_department_id\" = #{Department_id}\n" +
            "and \"Lineup_time\" in (select min(\"Lineup_time\") from \"Lineup\"\n" +
            "                                                where \"Current_department_id\" = #{Department_id});")
    String getCurrentPatientId(String Department_id);

    //获取当前病人在该科室所需检查的项目信息
    @Select("select distinct \"Project_id\" from \"Order\",\"Combo-Project\",\"Project\"\n" +
            "where \"Order_patient-id\" = #{Patient_id}\n" +
            "  and \"Order_date\" = '2023-05-13'\n" +
            "  and \"Project_department-id\" = #{Department_id}\n" +
            "  and ((\"Order_project/combo-id\" = \"Combo-Project_combo-id\"\n" +
            "       and \"Combo-Project_project-id\" = \"Project_id\" )\n" +
            "           or \"Order_project/combo-id\" = \"Project_id\");")
    List<String> getProjects(String Patient_id,String Department_id);

    @Select("select \"Patient_name\" from \"Patient\" where \"Patient_id\" = #{Patient_id};")
    String getPatientName(String Patient_id);

    @Select("select \"Project_name\" from \"Project\"\n" +
            "where \"Project_id\" = #{Project_id};")
    String getProjectName(String Project_id);

    //获取当前病人该项目中所需检查的指标信息
    @Select("select \"Indicator_name\" from \"Indicator\" where \"Indicator_project-id\" = #{Project_id};")
    List<String> getIndicators(String Project_id);

    //检查完成后更新待上传结果
    @Insert("insert into \"Result\" values (#{patient_id},#{indicator_name},null,#{doctor_id},#{date});")
    boolean CheckAndUpload(String patient_id, String indicator_name, String doctor_id, Date date);
}
