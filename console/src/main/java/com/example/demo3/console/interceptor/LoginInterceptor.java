package com.example.demo3.console.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.demo3.module.auth.Sign;
import com.example.demo3.module.entity.User;
import com.example.demo3.module.service.UserService;
import com.example.demo3.module.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String signJson = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sign".equals(cookie.getName())) {
                    signJson = cookie.getValue();
                    break;
                }
            }
        }

        if (signJson == null || signJson.isEmpty()) {
            System.out.println("signJson为空");
            return false;
        }
        try {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(signJson.getBytes(StandardCharsets.UTF_8));

            Sign sign = JSON.parseObject(decodedBytes, Sign.class);

            long currentTime = System.currentTimeMillis() / 1000;
            if (currentTime > sign.getExpireDate()) {
                System.out.println("过期");
                return false;
            }
            User user = userService.getById(sign.getId());
            if (user == null) {
                System.out.println("用户不存在");
                return false;
            }
          return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}