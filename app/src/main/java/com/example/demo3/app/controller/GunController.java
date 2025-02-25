package com.example.demo3.app.controller;

import com.example.demo3.app.domain.GunInfoVo;
import com.example.demo3.app.domain.GunListCellVo;
import com.example.demo3.app.domain.GunListVo;
import com.example.demo3.module.entity.Gun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo3.module.service.GunService;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

@RestController
public class GunController {
    @Autowired
    private GunService service;


    @RequestMapping("/gun/list")
    public GunListVo gunAllList(@RequestParam(name = "page")Integer page) {
        Integer pageSize=6;
        List<Gun> guns =service.getInfoPage(service.getStratData(page,pageSize),pageSize);
        List<GunListCellVo> gunListCellVo = new ArrayList<>();

        for (Gun gun : guns) {
            GunListCellVo vo = new GunListCellVo();
            vo.setGunId(gun.getId());
            vo.setTitle(gun.getTitle());
            vo.setCreateTime(service.timeText(gun.getCreateTime()));
            vo.setImage(gun.getImages().split("\\$")[0]);
            gunListCellVo.add(vo);
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
        Gun gun = service.getGunInfoById(gunId);
        gunInfoVo.setTitle(gun.getTitle());
        gunInfoVo.setAuthor(gun.getAuthor());
        gunInfoVo.setContent(gun.getContent());
        gunInfoVo.setCreateTime(service.timeText(gun.getCreateTime()));
        gunInfoVo.setImages(Arrays.asList(gun.getImages().split("\\$")));
        return gunInfoVo;
    }



}
