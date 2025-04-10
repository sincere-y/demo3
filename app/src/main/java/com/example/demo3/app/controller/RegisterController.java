package com.example.demo3.app.controller;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.example.demo3.app.domain.WapperVo;
import com.example.demo3.module.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;



@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(@Param("username") String username, @Param("password") String password) {
        if(username!=null&&password!=null){
            String mPassword = MD5.create().digestHex(password);
            int result = userService.insert(username,mPassword);
            return 1 == result ? "成功":"失败";

        }
        else{
            return "失败";
        }


    }



}
