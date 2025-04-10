package com.example.demo3.module.auth;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class Sign {
    private BigInteger id;
    private Integer expireDate;


}
