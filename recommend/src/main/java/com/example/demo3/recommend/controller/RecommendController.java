package com.example.demo3.recommend.controller;


import com.example.demo3.recommend.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

@RequestMapping("/recommend")
    public List<String> getRecommends() {
        return recommendService.getRecommends();
    }



}
