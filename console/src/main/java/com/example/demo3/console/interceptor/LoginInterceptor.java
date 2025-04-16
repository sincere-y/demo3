package com.example.demo3.console.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.demo3.module.auth.Sign;
import com.example.demo3.module.entity.User;
import com.example.demo3.module.service.UserService;
import com.example.demo3.module.utils.Response;
import com.example.demo3.module.utils.ResponseStatus;
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
            sendErrorResponse(response, 1002, "没有登录哦~");
            return false;
        }
        try {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(signJson.getBytes(StandardCharsets.UTF_8));

            Sign sign = JSON.parseObject(decodedBytes, Sign.class);

            long currentTime = System.currentTimeMillis() / 1000;
            if (currentTime > sign.getExpireDate()) {
                sendErrorResponse(response, 4004, "链接超时");
                return false;
            }
            User user = userService.getById(sign.getId());
            if (user == null) {
                sendErrorResponse(response, 1004, "用户不存在");
                return false;
            }
          return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private void sendErrorResponse(HttpServletResponse response, int code, String msg) throws Exception {
    // 设置响应的内容类型为JSON，并指定字符编码为UTF-8
        response.setContentType("application/json;charset=UTF-8");
    // 创建一个ResponseStatus对象，设置状态码和消息
        ResponseStatus resp = new Response<>(code).getStatus().setMsg(msg);
    // 将ResponseStatus对象转换为JSON字符串
        String json = JSON.toJSONString(resp);
    // 获取响应的Writer对象，并写入JSON字符串
        response.getWriter().write(json);
    // 刷新Writer对象，确保所有数据都被发送到客户端
        response.getWriter().flush();
    }




}