package com.example.demo3.banner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.demo3.banner.mapper")
@SpringBootApplication
public class BannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BannerApplication.class, args);
    }

}
