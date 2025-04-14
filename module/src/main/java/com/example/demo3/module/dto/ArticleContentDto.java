package com.example.demo3.module.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ArticleContentDto {
    private String type;
    private String content;
}
