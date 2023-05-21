package com.tjsystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddProjectsMapper {

    //查找医生所在的科室id
    @Select("select \"Doctor_department-id\" from \"Doctor\" where \"Doctor_id\" = #{Doctor_id};")
    String getDepartment_id(String Doctor_id);

    //查找当前患者在当前科室可增加的项目id
    @Select("select distinct \"Project_id\" from \"Project\" where \"Project_department-id\" = #{Department_id}\n" +
            "and \"Project_id\" not in(select distinct \"Project_id\" from \"Order\",\"Combo-Project\",\"Project\"\n" +
            "where \"Order_patient-id\" = #{Patient_id}\n" +
            "  and \"Order_date\" = '2023-05-21'\n" +
            "  and \"Project_department-id\" = #{Department_id}\n" +
            "  and ((\"Order_project/combo-id\" = \"Combo-Project_combo-id\"\n" +
            "       and \"Combo-Project_project-id\" = \"Project_id\" )\n" +
            "           or \"Order_project/combo-id\" = \"Project_id\"));")
    List<String> getProjectsToAdd(String Patient_id, String Department_id);

    //获取项目名称
    @Select("select \"Project_name\" from \"Project\"\n" +
            "where \"Project_id\" = #{Project_id};")
    String getProjectName(String Project_id);

    //获取项目价格
    @Select("select \"Project_price\" from \"Project\"\n" +
            "where \"Project_id\" = #{Project_id};")
    double getProjectPrice(String Project_id);

    //获取项目中所需检查的指标信息
    @Select("select \"Indicator_name\" from \"Indicator\" where \"Indicator_project-id\" = #{Project_id};")
    List<String> getIndicators(String Project_id);

    //获取当前最大的预约号
    @Select("select max(\"Order_id\") from \"Order\";")
    String getMaxOrderId();

    //通过项目名字获取项目id
    @Select("select \"Project_id\" from \"Project\" where \"Project_name\" = #{Project_name};")
    String getProjectId(String Project_name);

    //添加项目
    @Insert("insert into \"Order\" values (#{Order_id},#{Patient_id},#{Project_id},#{Order_date},true)")
    boolean addProject(String Order_id,String Patient_id,String Project_id,String Order_date);
}
