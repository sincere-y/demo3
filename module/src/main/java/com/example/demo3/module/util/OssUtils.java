package com.example.demo3.module.util;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
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

    String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    String bucketName = "web-static-images";
    // 替换为您的 Bucket 区域
    String region = "cn-beijing";


    //上传图片的方法
    public URL upload(MultipartFile file) throws com.aliyuncs.exceptions.ClientException, IOException {

        // 从环境变量中获取访问凭证。运行本代码示例之前，请先配置环境变量
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建 OSSClient 实例
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        // 显式声明使用 V4 签名算法
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();
        byte[] bytes = file.getBytes();


        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = generateObjectName() + fileExtension;

        // 创建PutObjectRequest对象，用于上传文件到OSS
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(bytes));

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
