package com.example.demo3.app.domain;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class WapperVo {
    private Integer page;
    private String gunName;



}
