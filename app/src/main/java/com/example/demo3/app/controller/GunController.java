package com.example.demo3.app.controller;

import com.example.demo3.app.domain.*;
import com.example.demo3.module.entity.Category;
import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo3.module.service.GunService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
@RestController
public class GunController {
    @Autowired
    private GunService gunService;
    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/gun/list")
    public GunListVo gunAllList(@RequestParam(name = "page")Integer page,
                                @RequestParam(name ="gunName",required = false )String gunName) {
        Integer pageSize=6;
        List<Gun> guns = gunService.getInfoPage(page,pageSize,gunName);
        List<GunListCellVo> gunListCellVo = new ArrayList<>();

        for (Gun gun : guns) {
            GunListCellVo vo = new GunListCellVo();
                Category category=categoryService.getById(gun.getCategoryId());
                if(category!=null) {
                    vo.setCategoryName(category.getName());
                    vo.setGunId(gun.getId());
                    vo.setTitle(gun.getTitle());
                    vo.setCreateTime(gunService.timeText(gun.getCreateTime()));
                    vo.setImage(gun.getImages().split("\\$")[0]);
                    gunListCellVo.add(vo);
                }
        }
        GunListVo gunListVo = new GunListVo();
        Integer presentpageSize = guns.size();

        if(presentpageSize < pageSize){//当前页面大小 小于 分页大小
            gunListVo.setIsEnd(true);
        }
        else gunListVo.setIsEnd(false);
        gunListVo.setList(gunListCellVo);
        return gunListVo ;

    }

    @RequestMapping("/gun/info")
    public GunInfoVo gunInfo(@RequestParam(name = "gunId") BigInteger gunId) {
        GunInfoVo gunInfoVo = new GunInfoVo();
        Gun gun = gunService.getById(gunId);
        if(gun==null) {
            return gunInfoVo;
        }
        else {
            Category category = categoryService.getById(gun.getCategoryId());
            if (category == null) {
                return gunInfoVo;
            } else {
                gunInfoVo.setTitle(gun.getTitle());
                gunInfoVo.setAuthor(gun.getAuthor());
                gunInfoVo.setContent(gun.getContent());
                gunInfoVo.setCreateTime(gunService.timeText(gun.getCreateTime()));
                gunInfoVo.setImages(Arrays.asList(gun.getImages().split("\\$")));
                gunInfoVo.setCategoryName(category.getName());
                gunInfoVo.setCategoryImage(category.getImage());

                return gunInfoVo;
            }
        }
    }



}
