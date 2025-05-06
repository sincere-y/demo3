package com.example.demo3.module.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ChannelService {
    public List<String> getChannels() {

        return Arrays.asList("精选频道", "限时特惠", "新品上市");
    }
}
