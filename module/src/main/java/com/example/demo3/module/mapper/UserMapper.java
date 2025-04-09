package com.example.demo3.module.mapper;

import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    int insert(@Param("user") User user);
    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);


}
