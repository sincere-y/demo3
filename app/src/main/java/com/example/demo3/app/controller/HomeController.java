package com.example.demo3.app.controller;

import com.example.demo3.app.domain.HomeVo;
import com.example.demo3.app.feign.BannerFeign;
import com.example.demo3.app.feign.ChannelFeign;
import com.example.demo3.app.feign.EventFeign;
import com.example.demo3.app.feign.RecommendFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class HomeController {
    @Autowired
    private BannerFeign bannerFeign;
    @Autowired
    private ChannelFeign channelFeign;
    @Autowired
    private EventFeign eventFeign;
    @Autowired
    private RecommendFeign recommendFeign;

    @GetMapping("/home")
    public HomeVo getHome() throws ExecutionException, InterruptedException {
        // 异步获取各模块数据
        CompletableFuture<List<String>> bannersFuture = CompletableFuture.supplyAsync(bannerFeign::getBanners);
        CompletableFuture<List<String>> channelsFuture = CompletableFuture.supplyAsync(channelFeign::getChannels);
        CompletableFuture<List<String>> eventsFuture = CompletableFuture.supplyAsync(eventFeign::getEvents);
        CompletableFuture<List<String>> recommendsFuture = CompletableFuture.supplyAsync(recommendFeign::getRecommends);

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
