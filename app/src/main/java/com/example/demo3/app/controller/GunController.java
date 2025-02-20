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
    public GunListVo gunAllList() {
        List<Gun> guns =service.getAllGunInfo();

        List<GunListCellVo> gunListCellVo = new ArrayList<>(guns.size());
        for (Gun gun : guns) {
            GunListCellVo vo = new GunListCellVo();
            vo.setGunId(gun.getId());
            vo.setTitle(gun.getTitle());
            vo.setCreateTime(service.timeText(gun.getCreateTime()));
            vo.setImage(gun.getImages().split("\\$")[0]);
            gunListCellVo.add(vo);
        }
        GunListVo gunListVo = new GunListVo();
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
