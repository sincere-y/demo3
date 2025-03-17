
package com.example.demo3.module.mapper;

import com.example.demo3.module.entity.Category;
import com.example.demo3.module.entity.Gun;
import org.apache.ibatis.annotations.*;
import java.math.BigInteger;
import java.util.List;

@Mapper
public interface CategoryMapper {

@Select("SELECT * FROM category WHERE id =  #{id} AND is_deleted=0")
Category getById(@Param("id")BigInteger id);

@Select("SELECT * FROM category WHERE id =  #{id}")
Category extractById(@Param("id")BigInteger id);

int insert(@Param("category") Category category);

int update(@Param("category") Category category);

@Update("UPDATE category SET updated_time = #{time} , is_deleted = 1 WHERE id = #{id}")
int delete(@Param("id")BigInteger id,@Param("time") Integer time );

@Select("select * FROM category where is_deleted=0 order by id asc limit #{start},#{pageSize}")
List<Category> getInfoPage(@Param("start") Integer start, @Param("pageSize") Integer categorySize);

@Select("select * from category where is_deleted = 0")
List<Category> getAllInfo();

//@Select("select * from category where is_deleted = 0 and (IFNULL(#{parentId}, parent_id) = parent_id)")
    List<Category> getCategoryByParentId(@Param("parentId") BigInteger parentId);

}

