package com.example.demo3.module.app.domain;

import com.example.demo3.module.entity.Gun;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;


@Data
@Accessors(chain = true)
public class GunVo {

    private List<String> images;
    private String title;
    private String author;
    private Integer createTime;
    private String content;

}
