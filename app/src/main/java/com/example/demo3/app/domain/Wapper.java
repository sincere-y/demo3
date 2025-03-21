package com.example.demo3.app.domain;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Wapper {
    private Integer page;
    private String gunName;



}
