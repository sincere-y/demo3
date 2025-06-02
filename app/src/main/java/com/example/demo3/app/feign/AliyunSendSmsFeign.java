package com.example.demo3.app.feign;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "aliyunsendsms",contextId = "AliyunSendSmsFeign")
public interface AliyunSendSmsFeign {

    @RequestMapping("/send/message")
     SendSmsResponse sendMessage(@RequestParam(name ="phone")String phone,
                                 @RequestParam(name ="templateCode")String templateCode,
                                 @RequestParam(name ="codeMap") Map<String, Object> codeMap) throws Exception ;

    @RequestMapping("/record/sms/task")
     String recordSmsTask(@RequestParam(name ="phone")String phone,
                          @RequestParam(name ="codeMap")Map<String, Object> codeMap);


}
