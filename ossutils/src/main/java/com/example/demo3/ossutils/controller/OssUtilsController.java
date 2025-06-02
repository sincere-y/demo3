package com.example.demo3.ossutils.controller;



import com.example.demo3.ossutils.utils.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class OssUtilsController {
    @Autowired
    private OssUtils ossUtils;


        //上传图片的方法
    @RequestMapping("/upload")
        public String upload(@RequestParam(name = "file") MultipartFile file) throws com.aliyuncs.exceptions.ClientException, IOException {
            return ossUtils.upload(file);


        }



}
