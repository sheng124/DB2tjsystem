package com.tjsystem.mapper;

import com.tjsystem.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select \"User_name\" userName,\"Password\" from \"Userinfo\" where \"User_name\" = #{userName} and \"Password\" = #{password}")
    User getByUsernameAndPassword(User user);

    @Update("insert into \"Userinfo\"(\"User_name\",\"Password\") VALUES (#{userName},#{password})")
    boolean insertUserInfo(User user);
}
