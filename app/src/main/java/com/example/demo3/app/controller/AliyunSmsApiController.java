package com.example.demo3.app.controller;

import com.example.demo3.module.service.AliyunSendSmsService;
import com.example.demo3.module.utils.Response;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AliyunSmsApiController {
    @Autowired
    private AliyunSendSmsService aliyunSendSmsService;



    @RequestMapping("/send/phones")
    public Response sendCode(@Param("phones") String phones) throws Exception {

        String[] phoneArray = phones.split(",");
        for (String phone : phoneArray) {
            aliyunSendSmsService.sendSingleCode(phone);
        }
        return new Response(1001);
    }

    @RequestMapping("/send/phone")
    public Response sendCode1(@Param("phone") String phone) throws Exception {

            aliyunSendSmsService.recordSmsTask(phone);

        return new Response(1001,"发送成功");
    }



}
