package com.example.demo3.app.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo3.app.domain.WapperVo;
import com.example.demo3.module.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(@Param("username") String username, @Param("password") String password) {
        if(username!=null&&password!=null){
            try {

                String jsonString = JSON.toJSONString(password);
                // 对 JSON 字符串进行 URL 编码
                byte[] jsonBytes = jsonString.getBytes("UTF-8");
                password = Base64.getUrlEncoder().encodeToString(jsonBytes);
            } catch (UnsupportedEncodingException e) {
                e.getMessage();
            }
        }


            int result = userService.insert(username,password);
            return 1 == result ? "成功":"失败";
    }



}
