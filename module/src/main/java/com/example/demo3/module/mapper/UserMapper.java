package com.example.demo3.module.mapper;


import com.example.demo3.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface UserMapper {
    int insert(@Param("user") User user);

    int update(@Param("user") User user);

    @Update("update user set is_deleted=1,update_time = #{updateTime} where id=#{id} limit 1")
    int delete(@Param("id") BigInteger id,@Param("updateTime") Integer updateTime);

    @Select("select * from user where username=#{username} and is_deleted=0")
    User selectByUsername(String username);

    @Select("select * from user WHERE id=#{id} and is_deleted=0")
    User getById(@Param("id") BigInteger id);


    @Select("select * from user where id=#{id}")
    User extractById(@Param("id") BigInteger id);

    @Select("select * from user where is_deleted = 0")
    List<User> getAllInfo();




}
