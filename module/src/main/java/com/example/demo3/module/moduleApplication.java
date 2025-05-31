package com.example.demo3.module;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.demo3.module.mapper")
@SpringBootApplication

public class moduleApplication {
       public static void main(String[] args) {
           SpringApplication.run(moduleApplication.class, args);
       }


}
