package com.example.demo3.banner.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BannerService {


        public List<String> getBanners() {

            return Arrays.asList("首页轮播1", "首页轮播2", "热门活动轮播");
        }
    }




