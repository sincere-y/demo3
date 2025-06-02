package com.example.demo3.aliyunsendsms.service;


import com.example.demo3.aliyunsendsms.mapper.SmsRecordMapper;
import com.example.demo3.common.entity.SmsRecord;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class SmsRecordService {
    @Resource
    private SmsRecordMapper mapper;


    public SmsRecord getById(BigInteger id){
        return mapper.getById(id);
    }
    public SmsRecord extractById(BigInteger id){
        return mapper.extractById(id);
    };

    public int insert(SmsRecord sms_record){

        return mapper.insert(sms_record);
    }
    public int update(SmsRecord sms_record){
        return mapper.update(sms_record);
    }
    public int delete(BigInteger gunId,int timestamp){
        return mapper.delete(gunId,timestamp);
    }




}
