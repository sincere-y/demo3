package com.example.demo3.app.controller;

import com.example.demo3.app.domain.HomeVo;
import com.example.demo3.module.service.BannerService;
import com.example.demo3.module.service.ChannelService;
import com.example.demo3.module.service.EventService;
import com.example.demo3.module.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class HomeController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private EventService eventService;
    @Autowired
    private RecommendService recommendService;

    @GetMapping("/home")
    public HomeVo getHome() throws ExecutionException, InterruptedException {
        // 异步获取各模块数据
        CompletableFuture<List<String>> bannersFuture = CompletableFuture.supplyAsync(bannerService::getBanners);
        CompletableFuture<List<String>> channelsFuture = CompletableFuture.supplyAsync(channelService::getChannels);
        CompletableFuture<List<String>> eventsFuture = CompletableFuture.supplyAsync(eventService::getEvents);
        CompletableFuture<List<String>> recommendsFuture = CompletableFuture.supplyAsync(recommendService::getRecommends);

        // 等待所有任务完成
        CompletableFuture.allOf(bannersFuture, channelsFuture, eventsFuture, recommendsFuture).join();

        // 构建返回对象
        HomeVo vo = new HomeVo();
        vo.setBanners(bannersFuture.get());
        vo.setChannels(channelsFuture.get());
        vo.setEvents(eventsFuture.get());
        vo.setRecommends(recommendsFuture.get());

        return vo;
    }



}
