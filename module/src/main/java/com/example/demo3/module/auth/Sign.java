package com.example.demo3.module.auth;

import lombok.Data;

import java.util.Date;

@Data
public class Sign {
    private String username;
    private Date expireDate;


}
