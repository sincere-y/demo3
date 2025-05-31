package com.example.demo3.app.controller;

import cn.hutool.crypto.digest.MD5;

import com.alibaba.fastjson.JSON;
import com.example.demo3.app.domain.SignVo;
import com.example.demo3.app.feign.UserFeign;

import com.example.demo3.common.auth.Sign;
import com.example.demo3.common.entity.User;
import com.example.demo3.common.utils.Response;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@RestController
public class UserController {
    @Autowired
    private UserFeign userFeign;
    @RequestMapping("/login")
    public Response login(@Param("username") String username, @Param("password") String password) {
        User user = userFeign.selectByUsername(username);
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

        if(username!=null&&password!=null&& userFeign.selectByUsername(username)!=null){
            String mPassword = MD5.create().digestHex(password);
            int result = userFeign.insert(username, mPassword);
            if(result==1){
                return new Response<>(1001);
            }else {
                return new Response<>(5000);
            }
        }
        else{
            return new Response<>(5000) ;
        }

    }
}
