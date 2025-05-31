package com.example.demo3.app.feign;

import com.example.demo3.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@FeignClient(name = "module",contextId = "UserFeign")
public interface UserFeign {
    @RequestMapping("/user/byId")
    public User getById(BigInteger id);
    @RequestMapping("/user/insert")
    public int insert(@RequestParam(name ="username")String username,
                      @RequestParam(name ="password")String password);
    @RequestMapping("/user/selectByUsername")
    public User selectByUsername(@RequestParam(name ="username")String username);

}
