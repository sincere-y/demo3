package com.example.demo3.app.controller;

import cn.hutool.crypto.digest.MD5;

import com.alibaba.fastjson.JSON;
import com.example.demo3.app.domain.SignVo;
import com.example.demo3.module.auth.Sign;
import com.example.demo3.module.entity.User;
import com.example.demo3.module.service.UserService;
import com.example.demo3.module.utils.Response;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public Response login(@Param("username") String username, @Param("password") String password) {
        User user = userService.selectByUsername(username);
        String mPassword = MD5.create().digestHex(password);
        Sign sign = new Sign();
        if (user != null && user.getPassword().equals(mPassword)) {

            sign.setId(user.getId());
            sign.setExpireDate( (int) (System.currentTimeMillis() / 1000) + 3600*24);
            String signJson = null;
            try{
                String jsonString= JSON.toJSONString(sign);
                byte[] jsonBytes = jsonString.getBytes("UTF-8");
                signJson= Base64.getEncoder().encodeToString(jsonBytes);
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }

            SignVo signVo = new SignVo();
            signVo.setSign(signJson);
            return new Response<>(1001,signVo) ;
        }
        return new Response<>(1010);

    }


    @RequestMapping("/register")
    public Response register(@Param("username") String username, @Param("password") String password) {

        if(username!=null&&password!=null&&userService.selectByUsername(username)!=null){
            String mPassword = MD5.create().digestHex(password);
            int result = userService.insert(username, mPassword);
            return new Response<>(1001,1 == result ? "成功" : "失败") ;
        }
        else{
            return new Response<>(1001, "失败") ;
        }

    }
}
