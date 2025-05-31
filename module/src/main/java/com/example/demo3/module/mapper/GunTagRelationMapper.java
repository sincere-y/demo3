
package com.example.demo3.module.mapper;


import com.example.demo3.common.entity.GunTagRelation;
import org.apache.ibatis.annotations.*;
import java.math.BigInteger;
import java.util.List;

@Mapper
public interface GunTagRelationMapper {

@Select("SELECT * FROM gun_tag_relation WHERE id =  #{id} AND is_deleted=0")
GunTagRelation getById(@Param("id")BigInteger id);

@Select("SELECT * FROM gun_tag_relation WHERE id =  #{id}")
GunTagRelation extractById(@Param("id")BigInteger id);

int insert(@Param("gun_tag_relation") GunTagRelation gun_tag_relation);

int update(@Param("gun_tag_relation") GunTagRelation gun_tag_relation);

@Update("UPDATE gun_tag_relation SET update_time = #{time} , is_deleted = 1 WHERE id = #{id}")
int delete(@Param("id")BigInteger id,@Param("time") Integer time );

@Select("SELECT * FROM gun_tag_relation WHERE gun_id =  #{gunId} AND is_deleted=0")
List<GunTagRelation> getByGunId(@Param("gunId")BigInteger gunId);

}

