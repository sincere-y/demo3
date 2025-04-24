package com.example.demo3.module.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Accessors(chain = true)
@Data
public class SmsRecord {
    private BigInteger id;


    private Integer createTime;


    private Integer updateTime;


    private Integer isDeleted;


    private String phone;
    private Boolean result;

    private String content;

private String failureReason;


}
