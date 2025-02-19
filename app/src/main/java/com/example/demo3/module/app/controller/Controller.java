package com.example.demo3.module.app.controller;


import com.example.demo3.module.app.domain.GunVo;
import com.example.demo3.module.app.domain.ListVo;
import com.example.demo3.module.entity.Gun;

import org.springframework.beans.BeanUtils;
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
public class Controller {
    @Autowired
    private GunService service;


    @RequestMapping("/gun/list")
    public List<ListVo> gunAllList() {

        List<Gun> gun = new ArrayList<>(service.getAllGunInfo());
        List<ListVo> listVo = new ArrayList<>(gun.size());
        for (Gun guns : gun) {
            ListVo vo = new ListVo();
            vo.setGunId(guns.getId()).setTitle(guns.getTitle()).setImage(guns.getImages());
//          BeanUtils.copyProperties(guns, vo);
            listVo.add(vo);
        }
        return listVo;

    }

    @RequestMapping("/gun/info")
    public GunVo gunInfo(@RequestParam(name = "id") BigInteger id) {
        GunVo vo = new GunVo();
        Gun gun = service.getGunInfoById(id);
        vo.setTitle(gun.getTitle())
                .setAuthor(gun.getAuthor())
                .setContent(gun.getContent())
                .setCreateTime(gun.getCreate_time())
                .setImages(Arrays.asList(gun.getImages().split("\\$")));
        return vo;
    }



}
