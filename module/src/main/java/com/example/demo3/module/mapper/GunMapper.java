package com.example.demo3.module.mapper;


import com.example.demo3.module.entity.Gun;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface GunMapper {
    @Select("select * from gun WHERE id=#{id} and is_deleted=0")
    Gun getById(@Param("id") BigInteger id);

    @Select("select * from gun WHERE category_id=#{id} and is_deleted=0")
    List<Gun> getByCategoryId(@Param("id") BigInteger id);


    @Select("select * from gun where id=#{id}")
    Gun extractById(@Param("id") BigInteger id);

    @Select("select * from gun where is_deleted = 0")
    List<Gun> getAllInfo();

    int insert(@Param("gun") Gun gun);

    int update(@Param("gun") Gun gun);

    @Update("update gun set is_deleted=1,update_time = #{updateTime} where id=#{id} limit 1")
    int delete(@Param("id") BigInteger id ,@Param("updateTime") Integer updateTime);

    int getTotal(@Param("gunName") String gunName);

    List<Gun> getInfoPage(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("gunName")String gunName);




}
