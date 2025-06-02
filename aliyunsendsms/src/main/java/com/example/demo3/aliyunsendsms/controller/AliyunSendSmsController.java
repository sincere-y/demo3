package com.example.demo3.aliyunsendsms.controller;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;

import com.example.demo3.aliyunsendsms.service.AliyunSendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AliyunSendSmsController {
    @Autowired
    private AliyunSendSmsService aliyunSendSmsService;
    @RequestMapping("/send/message")
    public SendSmsResponse sendMessage(@RequestParam(name ="phone")String phone,
                                       @RequestParam(name ="templateCode")String templateCode,
                                       @RequestParam(name ="codeMap")Map<String, Object> codeMap) throws Exception {
        return aliyunSendSmsService.sendMessage(phone, templateCode, codeMap);

    }

    @RequestMapping("/record/sms/task")
    public String recordSmsTask(@RequestParam(name ="phone")String phone,
                                @RequestParam(name ="codeMap")Map<String, Object> codeMap){
        return aliyunSendSmsService.recordSmsTask(phone, codeMap);
    }

}
