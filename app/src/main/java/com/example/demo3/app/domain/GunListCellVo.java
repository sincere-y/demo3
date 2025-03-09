package com.example.demo3.app.domain;


import lombok.Data;

import java.math.BigInteger;
@Data
public class GunListCellVo {

    private BigInteger gunId;
    private String image;
    private String title;
    private String createTime;
    private String categoryName;


}
