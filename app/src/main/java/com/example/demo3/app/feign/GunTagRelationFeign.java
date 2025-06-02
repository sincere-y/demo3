package com.example.demo3.app.feign;

import com.example.demo3.common.entity.GunTagRelation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

@FeignClient(name = "gun",contextId = "GunTagRelationFeign")
public interface GunTagRelationFeign {

    @RequestMapping("/gunTagRelation/edit")
     BigInteger edit(@RequestParam(name ="gunId")BigInteger gunId,
                           @RequestParam(name ="tagId")BigInteger tagId);


    @RequestMapping("/gunTagRelation/ByGunId")
     List<GunTagRelation> getByGunId(@RequestParam(name ="gunId")BigInteger gunId);
}
