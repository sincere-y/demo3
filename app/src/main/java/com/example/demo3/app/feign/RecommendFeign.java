package com.example.demo3.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@FeignClient(name = "recommend",contextId = "RecommendFeign")
public interface RecommendFeign {
    @RequestMapping("/recommend")
     List<String> getRecommends() ;
}
