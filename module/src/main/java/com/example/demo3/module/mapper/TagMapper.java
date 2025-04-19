
package com.example.demo3.module.mapper;

import com.example.demo3.module.entity.Tag;
import org.apache.ibatis.annotations.*;
import java.math.BigInteger;

@Mapper
public interface TagMapper {

@Select("SELECT * FROM tag WHERE id =  #{id} AND is_deleted=0")
Tag getById(@Param("id")BigInteger id);

@Select("SELECT * FROM tag WHERE id =  #{id}")
Tag extractById(@Param("id")BigInteger id);

int insert(@Param("tag") Tag tag);

int update(@Param("tag") Tag tag);

@Update("UPDATE tag SET updated_time = #{time} , is_deleted = 1 WHERE id = #{id}")
int delete(@Param("id")BigInteger id,@Param("time") Integer time );

@Select("SELECT * FROM tag WHERE name =  #{name} AND is_deleted=0")
Tag getByName(@Param("name") String name);

}

