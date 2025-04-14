package com.example.demo3.console.config;

import com.example.demo3.console.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 注入自定义的拦截器对象
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义的拦截器对象
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")// 设置拦截器拦截的请求路径（/** 表示拦截所有请求）
                .excludePathPatterns("/login");//设置拦截器 排除拦截的路径
    }

}
