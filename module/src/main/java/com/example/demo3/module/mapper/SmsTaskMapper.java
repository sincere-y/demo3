package com.example.demo3.module.mapper;


import com.example.demo3.common.entity.SmsTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface SmsTaskMapper {

    @Select("select * from sms_task WHERE id=#{id} and is_deleted=0")
    SmsTask getById(@Param("id") BigInteger id);


    @Select("select * from sms_task where id=#{id}")
    SmsTask extractById(@Param("id") BigInteger id);


    int insert(@Param("sms_task") SmsTask sms_task);

    int update(@Param("sms_task") SmsTask sms_task);

    @Update("update sms_task set is_deleted=1,update_time = #{updateTime} where id=#{id} limit 1")
    int delete(@Param("id") BigInteger id ,@Param("updateTime") Integer updateTime);

    @Select("select * from sms_task where is_deleted=0 and status=0")
    List<SmsTask> getUnsentTasks();





}
