package com.example.demo3.module.controller;

import com.example.demo3.module.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @RequestMapping("/banners")
    public List<String> getBanners() {
    return bannerService.getBanners();
    }
}
