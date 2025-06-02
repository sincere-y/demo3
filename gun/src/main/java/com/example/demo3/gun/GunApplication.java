package com.example.demo3.gun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableFeignClients
@MapperScan ("com.example.demo3.gun.mapper")
@SpringBootApplication
public class GunApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunApplication.class, args);
    }

}
