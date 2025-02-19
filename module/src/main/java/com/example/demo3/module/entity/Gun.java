package com.example.demo3.module.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.List;


@Data
@Accessors(chain = true)
public class Gun {
    private BigInteger id;
    private String title;
    private String author;
    private String images;
    private String content;
    private Integer create_time;
    private Integer update_time;
    private Integer is_deleted;


}
