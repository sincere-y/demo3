package com.example.demo3.gun.controller;


import com.example.demo3.common.entity.GunTagRelation;

import com.example.demo3.gun.service.GunTagRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class GunTagRelationController {
    @Autowired
    private GunTagRelationService gunTagRelationService;


    @RequestMapping("/gunTagRelation/edit")
    public BigInteger edit(@RequestParam(name ="gunId")BigInteger gunId,
                           @RequestParam(name ="tagId")BigInteger tagId){
        return gunTagRelationService.edit(gunId, tagId);
    }

    @RequestMapping("/gunTagRelation/ByGunId")
    public List<GunTagRelation> getByGunId(@RequestParam(name ="gunId")BigInteger gunId){
        return gunTagRelationService.getByGunId(gunId);
    }

}
