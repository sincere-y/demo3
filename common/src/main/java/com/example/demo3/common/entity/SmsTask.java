package com.example.demo3.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@Accessors(chain = true)
public class SmsTask {
    private BigInteger id;
    private Integer createTime;
    private Integer updateTime;
    private Integer isDeleted;
    private String phone;
    private Integer status;
    private String content;
    private String failureReason;

}
