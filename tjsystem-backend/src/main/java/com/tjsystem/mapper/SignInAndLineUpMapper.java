package com.tjsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SignInAndLineUpMapper {
    //签到（根据预约ID设置isCheck=true)
    @Update("update reservation set isCheck = true where reserve_id = #{Reserve_id};")
    boolean SignIn(int Reserve_id);

    //查找拥有该预约ID的病人ID
    @Select("select reserve_patient_id from reservation where reserve_id = #{Reserve_id};")
    String Patient_idForLineUp(int Reserve_id);

    @Select("select reserve_id from lineup where Patient_id =#{Patient_id};")
    int getReserve_id(String Patient_id);

    //查找预约ID对应的套餐的科室排序
    @Select("select distinct \"Project_department-id\" from \"Project\",\"Combo-Project\",reservation\n" +
            "where reserve_id = #{Reserve_id} and Reservation.project_combo_id = \"Combo-Project_combo-id\"\n" +
            "and \"Combo-Project_project-id\" = \"Project_id\"\n" +
            "order by \"Project_department-id\";")
    List<String> Department_idForLineUp(int Reserve_id);

    //将签到完成的病人加入第一个科室等候队列
    @Insert("insert into lineup values (#{Patient_id},#{Department_id},#{time}::time,#{Reserve_id});")
    boolean JoinQueue(String Patient_id, String Department_id, String time,int Reserve_id);

    //更新病人的等候队列
    @Update("update lineup set current_department_id = #{Department_id},lineup_time = #{time}::time " +
            "where Patient_id =#{Patient_id};")
    boolean UpdateQueue(String Patient_id, String Department_id, String time);
}
