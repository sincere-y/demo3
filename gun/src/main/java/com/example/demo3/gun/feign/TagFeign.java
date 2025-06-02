package com.example.demo3.gun.feign;

import com.example.demo3.common.entity.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@FeignClient(name = "tag",contextId = "TagFeign")
public interface TagFeign {
    @RequestMapping("/tag/edit")
     BigInteger edit(@RequestParam(name ="name")String name);

    @RequestMapping("/tag/byId")
     Tag getById(@RequestParam(name ="id")BigInteger id) ;
    @RequestMapping("/tag/byName")
     Tag getByName(@RequestParam(name ="name")String name);
}
