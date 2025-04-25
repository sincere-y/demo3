package com.example.demo3.module.service;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.example.demo3.module.entity.SmsRecord;
import com.example.demo3.module.entity.SmsTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class AliyunSendSmsService {

    @Resource
    private SmsRecordService smsRecordService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${aliyun.sms.templateCode}")
    private String templateCode;


    private ExecutorService executorService = Executors.newFixedThreadPool(10); // 创建一个固定大小的线程池
    @Autowired
    private SmsTaskService smsTaskService;
    String codeKey ="code";

    public static Client createClient() throws Exception {
        Config config = new Config()
                // 配置 AccessKey ID，请确保代码运行环境配置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
                .setAccessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
                // 配置 AccessKey Secret，请确保代码运行环境配置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
                .setAccessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
        // System.getenv()方法表示获取系统环境变量，不要直接在getenv()中填入AccessKey信息。

        // 配置 Endpoint。中国站使用dysmsapi.aliyuncs.com
        config.endpoint = "dysmsapi.aliyuncs.com";

        return new Client(config);
    }
    public void sendMessage(String phone, String templateCode, Map<String, Object> codeMap) throws Exception {


        // 初始化请求客户端
        Client client = AliyunSendSmsService.createClient();
        // 构建请求：
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("阿里云")
                .setTemplateCode(templateCode)
                .setTemplateParam(JSONObject.toJSONString(codeMap));

        executorService.submit(() -> {
            Boolean result = false;
            String failureReason = null;
            SendSmsResponse response = null;
            try {
                response = client.sendSms(sendSmsRequest);
                // 检查业务状态码
                if ("OK".equalsIgnoreCase(response.getBody().getCode())) {
                    result = true;
                } else {
                    failureReason = "阿里云返回错误: " + response.getBody().getCode() + " - " + response.getBody().getMessage();
                }

            } catch (Exception e) {
                failureReason = e.getMessage();
                e.printStackTrace();
            }

            // 记录短信发送结果（在异步线程中完成）
            int timestamp = (int) (System.currentTimeMillis() / 1000);
            SmsRecord smsRecord = new SmsRecord();
            smsRecord.setContent(JSONObject.toJSONString(codeMap));
            smsRecord.setCreateTime(timestamp);
            smsRecord.setIsDeleted(0);
            smsRecord.setPhone(phone);
            smsRecord.setUpdateTime(timestamp);
            smsRecord.setResult(result);
            smsRecord.setFailureReason(failureReason);
            smsRecordService.insert(smsRecord); // 确保线程安全的数据库操作
        });

    }


    public String recordSmsTask(String phone,Map<String, Object> codeMap){


        int timestamp = (int) (System.currentTimeMillis() / 1000);
        SmsTask smsTask = new SmsTask();
        smsTask.setPhone(phone);
        smsTask.setContent(JSONObject.toJSONString(codeMap));
        smsTask.setStatus(0); // 未发送
        smsTask.setCreateTime(timestamp);
        smsTask.setUpdateTime(timestamp);
        smsTask.setIsDeleted(0);
        smsTaskService.insert(smsTask);
        return phone + " ： 短信已提交发送";

    }



}
