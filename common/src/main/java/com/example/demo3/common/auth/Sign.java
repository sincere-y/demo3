package com.example.demo3.common.auth;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Sign {
    private BigInteger id;
    private Integer expireDate;


}
