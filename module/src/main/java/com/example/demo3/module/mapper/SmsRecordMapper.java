package com.example.demo3.module.mapper;

import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.entity.SmsRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface SmsRecordMapper {
    @Select("select * from sms_record WHERE id=#{id} and is_deleted=0")
    SmsRecord getById(@Param("id") BigInteger id);


    @Select("select * from sms_record where id=#{id}")
    SmsRecord extractById(@Param("id") BigInteger id);


    int insert(@Param("sms_record") SmsRecord sms_record);

    int update(@Param("sms_record") SmsRecord sms_record);

    @Update("update sms_record set is_deleted=1,update_time = #{updateTime} where id=#{id} limit 1")
    int delete(@Param("id") BigInteger id ,@Param("updateTime") Integer updateTime);

}
