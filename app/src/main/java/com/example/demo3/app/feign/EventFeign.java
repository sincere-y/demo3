package com.example.demo3.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@FeignClient(name = "event",contextId = "EventFeign")
public interface EventFeign {
    @RequestMapping("/events")
     List<String> getEvents();




}
