package com.example.demo3.app.controller;

import com.example.demo3.app.feign.AliyunSendSmsFeign;

import com.example.demo3.common.utils.Response;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
public class AliyunSmsApiController {
    @Autowired
    private AliyunSendSmsFeign aliyunSendSmsFeign;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${aliyun.sms.templateCode}")
    private String templateCode;


    String codeKey ="code";


    @RequestMapping("/send/phones")
    public Response sendCode(@Param("phones") String phones) {

        String[] phoneArray = phones.split(",");
        for (String phone : phoneArray) {
            // 根据手机号从redis中拿验证码
            String code = redisTemplate.opsForValue().get(phone);
            if (!StringUtils.isEmpty(code)) {
                continue;
            }
            // 如果redis 中根据手机号拿不到验证码，则生成4位随机验证码
            code = UUID.randomUUID().toString().substring(0, 4);
            // 验证码存入codeMap
            Map<String, Object> codeMap = new HashMap<>();
            codeMap.put(codeKey, code);
            // 调用aliyunSendSmsService发送短信
            try {
                aliyunSendSmsFeign.sendMessage(phone, templateCode, codeMap);
            } catch (Exception e) {
                return new Response(4004);
            }
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

        }
        return new Response(1001,"发送成功");
    }

    @RequestMapping("/send/phone")
    public Response sendCode1(@Param("phone") String phone){

        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return new Response<>(400);
        }
        // 如果redis 中根据手机号拿不到验证码，则生成4位随机验证码
        code = UUID.randomUUID().toString().substring(0, 4);
        // 验证码存入codeMap
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put(codeKey, code);
        aliyunSendSmsFeign.recordSmsTask(phone,codeMap);

        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

        return new Response(1001,"发送成功");

    }





}
