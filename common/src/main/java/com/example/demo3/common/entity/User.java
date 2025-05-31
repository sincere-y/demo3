package com.example.demo3.common.entity;

import lombok.Data;

import java.math.BigInteger;

@Data
public class User {
    private BigInteger id;
    private String username;
    private String password;
    private Integer updateTime;
    private Integer createTime;
    private Integer isDeleted;
}
