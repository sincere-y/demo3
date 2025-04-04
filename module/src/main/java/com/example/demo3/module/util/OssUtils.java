package com.example.demo3.module.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class OssUtils {

//    @Value("${aliyun.oss.endpoint}")
    private String endpoint="https://oss-cn-beijing.aliyuncs.com";
//    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId="LTAI5tG1L4Atn9yk3jed93Le";
//    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret="9otxlGEorgZfK44WcjCmi40U3ix8ia";
//    @Value("${aliyun.oss.bucketName}")
    private String bucketName="web-static-images";

    //上传图片的方法
    public URL upload(MultipartFile file) throws IOException {


        byte[] bytes = file.getBytes();


        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = generateObjectName() + fileExtension;

        // 创建PutObjectRequest对象，用于上传文件到OSS
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(bytes));
        //创建OSSClient实例，用于与OSS服务进行交互
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //推送数据到OSS
        PutObjectResult result = ossClient.putObject(putObjectRequest);
        //关闭OSSClient
        ossClient.shutdown();
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        URL url =ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        return url;
//        return generateImageUrl(objectName);



    }
    private String generateObjectName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM/dd/");
        String datePath = dateFormat.format(new Date());
        String uniqueName = UUID.randomUUID().toString().replace("-", "");
        return "image/" + datePath + uniqueName;
    }
//    private String generateImageUrl(String objectName) {
//        return "https://" + bucketName + "." + endpoint + "/" + objectName;
//    }




}
