package com.example.demo3.app.domain;






import lombok.Data;

import java.util.List;

@Data
public class GunInfoVo {

    private List<String> images;
    private String title;
    private String author;
    private String createTime;
    private String content;
    private String categoryName;
    private String categoryImage;

}
