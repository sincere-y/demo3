package com.example.demo3.app.controller;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.example.demo3.module.auth.Sign;
import com.example.demo3.module.entity.User;
import com.example.demo3.module.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Calendar;

@RestController
public class LoginController {
@Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(@Param("username") String username, @Param("password") String password) {
        User user = userService.selectByUsername(username);
        String mPassword = MD5.create().digestHex(password);
        if (user != null && user.getPassword().equals(mPassword)) {
            Sign sign = new Sign();
            sign.setId(user.getId());
            sign.setExpireDate( (int) (System.currentTimeMillis() / 1000) + 3600*24);
            String signJson=null;
            try {

                String jsonString = JSON.toJSONString(sign);

                byte[] jsonBytes = jsonString.getBytes("UTF-8");
                signJson = Base64.getUrlEncoder().encodeToString(jsonBytes);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return signJson;
        }
        return "账号或密码错误";

    }
}
