package com.example.demo3.console.controller;

import com.example.demo3.module.entity.User;
import com.example.demo3.module.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletResponse response;

    @RequestMapping("/login")
    public String login(@Param("username") String username, @Param("password") String password) {
        User user = userService.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(3600*24);
            response.addCookie(cookie);
            return "登录成功";
        }
        return "账号或密码错误";

    }


}
