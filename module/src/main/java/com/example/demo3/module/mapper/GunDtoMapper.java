package com.example.demo3.module.mapper;

import com.example.demo3.module.dto.GunDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GunDtoMapper {
    List<GunDto> getGunDtoList(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("gunName")String gunName, @Param("categoryIds")String categoryIds);
}
