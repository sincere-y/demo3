package com.example.demo3.recommend.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RecommendService {
    public List<String> getRecommends() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList("商品推荐1", "猜你喜欢2", "专属推荐3");
    }

}
