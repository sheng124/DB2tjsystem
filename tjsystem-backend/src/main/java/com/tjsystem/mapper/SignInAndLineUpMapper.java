package com.tjsystem.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SignInAndLineUpMapper {
    //签到（根据预约ID设置isCheck=true)
    @Update("update \"Order\" set \"Order_ischeck\" = true \n" +
            "               where \"Order_date\" = '2023-05-13' and \"Order_patient-id\" = #{Patient_id};")
    boolean SignIn(String Patient_id);

    //查找拥有该预约ID的病人ID
    @Select("select reserve_patient_id from reservation where reserve_id = #{Reserve_id};")
    String Patient_idForLineUp(int Reserve_id);

    //查找预约ID对应的套餐的科室排序
    @Select("select distinct \"Project_department-id\" from \"Order\",\"Combo-Project\",\"Project\"\n" +
            "where \"Order_patient-id\" = #{Patient_id}\n" +
            "  and \"Order_date\" = '2023-05-13'\n" +
            "  and ((\"Order_project/combo-id\" = \"Combo-Project_combo-id\"\n" +
            "       and \"Combo-Project_project-id\" = \"Project_id\" )\n" +
            "           or \"Order_project/combo-id\" = \"Project_id\")\n" +
            "order by \"Project_department-id\";")
    List<String> Department_idForLineUp(String Patient_id);

    //将签到完成的病人加入第一个科室等候队列
    @Insert("insert into \"Lineup\" values (#{Patient_id},#{Department_id},#{time}::time);")
    boolean JoinQueue(String Patient_id, String Department_id, String time);

    //更新病人的等候队列
    @Update("update \"Lineup\" set \"Current_department_id\" = #{Department_id},\"Lineup_time\" = #{time}::time " +
            "where \"Patient_id\" = #{Patient_id};")
    boolean UpdateQueue(String Patient_id, String Department_id, String time);

    //检查完成
    @Delete("delete from \"Lineup\" where \"Patient_id\" = #{Patient_id};")
    boolean CheckOver(String Patient_id);
}
