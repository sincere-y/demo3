package com.example.demo3.module.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class EventService {


    public List<String> getEvents() {

        return Arrays.asList("618大促", "每日签到", "会员专享");
    }
}
