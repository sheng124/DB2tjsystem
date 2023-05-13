package com.tjsystem.mapper;

import com.tjsystem.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select id,user_name userName,password from userinfo where user_name = #{userName} and password = #{password}")
    User getByUsernameAndPassword(User user);

    @Update("insert into userinfo(user_name,password) VALUES (#{userName},#{password})")
    boolean insertUserInfo(User user);
}
