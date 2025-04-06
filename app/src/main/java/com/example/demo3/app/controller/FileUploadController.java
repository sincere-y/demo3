package com.example.demo3.app.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.demo3.module.service.GunService;
import com.example.demo3.module.util.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;

@RestController
public class FileUploadController {
    @Autowired
    private OssUtils ossUtils;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }

        try {
            String url = ossUtils.upload(file);
            return url;
        } catch (ClientException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
