package com.example.demo3.app.controller;

import com.example.demo3.module.util.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@RestController
public class FileUploadController {
    @Autowired
    private OssUtils ossUtils;

//    @Value("${file.upload-dir}")
//    private String uploadFolder;
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }

        try {
            URL url = ossUtils.upload(file);

//            Path path = Paths.get(uploadFolder + file.getOriginalFilename());
//            Files.write(path, bytes);

            return url.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return  e.getMessage();
        }
    }


}
