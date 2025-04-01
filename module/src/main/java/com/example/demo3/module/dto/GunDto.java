package com.example.demo3.module.dto;

import lombok.Data;

import java.math.BigInteger;
@Data
public class GunDto {
    private BigInteger id;
    private String title;
    private String images;
    private Integer createTime;
    private String categoryName;


}
