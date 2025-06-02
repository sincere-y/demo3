package com.example.demo3.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "channel",contextId = "ChannelFeign")
public interface ChannelFeign {
    @RequestMapping("/channel/list")
     List<String> getChannels();
}
