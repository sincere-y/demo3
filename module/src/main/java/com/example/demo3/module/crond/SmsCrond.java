package com.example.demo3.module.crond;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;

import com.example.demo3.common.entity.SmsTask;
import com.example.demo3.module.mapper.SmsTaskMapper;
import com.example.demo3.module.service.AliyunSendSmsService;
import com.example.demo3.module.service.SmsTaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SmsCrond {

    @Resource
    private SmsTaskService smsTaskService;

    @Resource
    private AliyunSendSmsService aliyunSendSmsService;

    @Value("${aliyun.sms.templateCode}")
    private String templateCode;


    @Scheduled(cron = "0 */1 * * * ?") // 每分钟执行一次
    public void processSmsTasks() {
        List<SmsTask> tasks = smsTaskService.getUnsentTasks();
        for (SmsTask task : tasks) {
            int timestamp = (int) (System.currentTimeMillis() / 1000);
            try {
                Map<String, Object> codeMap = JSONObject.parseObject(task.getContent(), Map.class);
                SendSmsResponse response = aliyunSendSmsService.sendMessage(task.getPhone(), templateCode, codeMap);
                Integer status=0;
                String failureReason = null;

                if ("OK".equalsIgnoreCase(response.getBody().getCode())) {
                    status=1;
                } else {
                    status=2;
                    failureReason = "阿里云返回错误: " + response.getBody().getCode() + " - " + response.getBody().getMessage();
                }
                smsTaskService.update(task.getId(),timestamp,status,failureReason,null); // 发送成功
            } catch (Exception e) {

                smsTaskService.update(task.getId(),timestamp, 2, e.getMessage(),null); // 发送失败
            }
        }
    }
}
