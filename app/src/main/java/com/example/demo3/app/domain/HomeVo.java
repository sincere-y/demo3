package com.example.demo3.app.domain;

import lombok.Data;

import java.util.List;

@Data
public class HomeVo {
    private List<String> banners;
    private List<String> channels;
    private List<String> events;
    private List<String> recommends;
}
