package com.example.demo3.app.controller;

import com.example.demo3.app.domain.GunVo;
import com.example.demo3.app.domain.GunListVo;
import com.example.demo3.app.domain.ListVo;
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
    public ListVo gunAllList() {
        List<Gun> guns =service.getAllGunInfo();

        List<GunListVo> gunListVo = new ArrayList<>(guns.size());
        for (Gun gun : guns) {
            GunListVo vo = new GunListVo();
            vo.setGunId(gun.getId());
            vo.setTitle(gun.getTitle());
            vo.setCreateTime(service.timeText());
            vo.setImage(gun.getImages().split("\\$")[0]);
            gunListVo.add(vo);
        }
        return new ListVo(gunListVo);

    }

    @RequestMapping("/gun/info")
    public GunVo gunInfo(@RequestParam(name = "gunId") BigInteger gunId) {
        GunVo gunVo = new GunVo();
        Gun gun = service.getGunInfoById(gunId);
        gunVo.setTitle(gun.getTitle());
        gunVo.setAuthor(gun.getAuthor());
        gunVo.setContent(gun.getContent());
        gunVo.setCreateTime(service.timeText());
        gunVo.setImages(Arrays.asList(gun.getImages().split("\\$")));
        return gunVo;
    }



}
