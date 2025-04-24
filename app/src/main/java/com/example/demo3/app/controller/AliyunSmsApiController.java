package com.example.demo3.app.controller;

import com.example.demo3.module.service.AliyunSendSmsService;
import com.example.demo3.module.utils.Response;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class AliyunSmsApiController {
    @Autowired
    private AliyunSendSmsService aliyunSendSmsService;



    @GetMapping("/send/phones")
    public Response sendCode(@Param("phones") String phones) throws Exception {

        String[] phoneArray = phones.split(",");
        for (String phone : phoneArray) {
            aliyunSendSmsService.sendSingleCode(phone);
        }
        return new Response(1001);
    }


}
