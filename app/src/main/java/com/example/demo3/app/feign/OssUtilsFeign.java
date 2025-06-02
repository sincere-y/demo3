package com.example.demo3.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "ossutils",contextId = "OssUtilsFeign")
public interface OssUtilsFeign {


    @RequestMapping("/upload")
     String upload(@RequestParam(name = "file") MultipartFile file) throws com.aliyuncs.exceptions.ClientException, IOException ;






}
