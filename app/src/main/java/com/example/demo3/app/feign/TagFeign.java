package com.example.demo3.app.feign;

import com.example.demo3.common.entity.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@FeignClient(name = "module",contextId = "TagFeign")
public interface TagFeign {
    @RequestMapping("/tag/edit")
    public BigInteger edit(@RequestParam(name ="name")String name);

    @RequestMapping("/tag/byId")
    public Tag getById(@RequestParam(name ="id")BigInteger id) ;
}
