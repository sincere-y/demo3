package com.example.demo3.console.feign;

import com.example.demo3.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@FeignClient(name = "module",contextId = "UserFeign")
public interface UserFeign {
    @RequestMapping("/user/byId")
     User getById(@RequestParam(name ="id")BigInteger id);
    @RequestMapping("/user/insert")
     int insert(@RequestParam(name ="username")String username,
                      @RequestParam(name ="password")String password);
    @RequestMapping("/user/selectByUsername")
     User selectByUsername(@RequestParam(name ="username")String username);

}
