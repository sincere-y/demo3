package com.example.demo3.tag;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan ("com.example.demo3.tag.mapper")
@SpringBootApplication
public class TagApplication {

    public static void main(String[] args) {
        SpringApplication.run(TagApplication.class, args);
    }

}
