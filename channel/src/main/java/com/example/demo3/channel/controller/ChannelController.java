package com.example.demo3.channel.controller;


import com.example.demo3.channel.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChannelController {
    @Autowired
    private ChannelService channelService;
@RequestMapping("/channel/list")
    public List<String> getChannels() {
        return channelService.getChannels();
    }


}
