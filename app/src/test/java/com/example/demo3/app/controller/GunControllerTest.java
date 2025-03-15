package com.example.demo3.app.controller;

import com.example.demo3.app.domain.GunListVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class GunControllerTest {

    @Autowired
    private GunController gunController;
    @Test
    void gunAllListTest() {

        GunListVo result= gunController.gunAllList(1,"M");
        System.out.println(result);
    }
}