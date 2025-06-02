package com.example.demo3.aliyunsendsms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.demo3.aliyunsendsms.mapper")
@SpringBootApplication
public class AliyunsendsmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliyunsendsmsApplication.class, args);
    }

}
