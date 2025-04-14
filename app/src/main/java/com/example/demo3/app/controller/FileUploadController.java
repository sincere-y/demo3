package com.example.demo3.app.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.demo3.module.utils.OssUtils;
import com.example.demo3.module.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {
    @Autowired
    private OssUtils ossUtils;

    @PostMapping("/upload")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Response(4004);
        }

        try {
            String url = ossUtils.upload(file);
            return new Response(1001, url);
        } catch (ClientException e) {
           e.getMessage();
        } catch (IOException e) {
           e.getMessage();
        }
        return new Response(4004);
    }


}
