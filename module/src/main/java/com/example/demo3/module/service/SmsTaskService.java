package com.example.demo3.module.service;

import com.example.demo3.common.entity.SmsTask;

import com.example.demo3.module.mapper.SmsTaskMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class SmsTaskService {
    @Resource
    private SmsTaskMapper mapper;

    public SmsTask getById(BigInteger id){
        return mapper.getById(id);
    }
    public SmsTask extractById(BigInteger id){
        return mapper.extractById(id);
    };

    public int insert(SmsTask smsTask){

        return mapper.insert(smsTask);
    }
    public int update(SmsTask smsTask){
        return mapper.update(smsTask);
    }
    public int delete(BigInteger gunId,int timestamp){
        return mapper.delete(gunId,timestamp);
    }

    public List<SmsTask> getUnsentTasks(){
        return mapper.getUnsentTasks();
    }

    public int update(BigInteger id,int timestamp, int status, String failureReason, String content){
        if(id!=null) {
            SmsTask smsTask = new SmsTask();
            smsTask.setId(id);
            smsTask.setUpdateTime(timestamp);
            smsTask.setStatus(status);
            smsTask.setFailureReason(failureReason);
            smsTask.setContent(content);
            return update(smsTask);
        }
        else {
            throw new RuntimeException("id is null");
        }
    }




}
