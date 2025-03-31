package com.example.demo3.module.service;

import com.example.demo3.module.dto.GunDto;
import com.example.demo3.module.mapper.GunDtoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GunDtoService {
    @Resource
    private GunDtoMapper gunDtoMapper;
    @Resource
    private CategoryService categoryService;

    public List<GunDto> getGunDtoList(Integer page, Integer pageSize, String gunName){
        if(page!=null&&pageSize!=null) {
            List<Integer> ids=categoryService.getCategoryId(gunName);
            StringBuilder resultIds = new StringBuilder();
            if (ids != null) {
                for (int i = 0; i < ids.size(); i++) {
                    if (i > 0) {
                        resultIds.append(",");
                    }
                    resultIds.append(ids.get(i));
                }
            }
            String categoryIds = resultIds.toString();
            Integer start = (page - 1) * pageSize;
            return gunDtoMapper.getGunDtoList(start,pageSize,gunName,categoryIds);
        }
        else {
            throw new RuntimeException("参数内容为空");
        }

    }

}
