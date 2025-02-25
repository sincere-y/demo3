package com.example.demo3.console.domain;

import lombok.Data;

import java.util.List;

@Data
public class GunInfoVo {
    private List<String> images;
    private String title;
    private String author;
    private String createTime;
    private String updateTime;
    private String content;
}
