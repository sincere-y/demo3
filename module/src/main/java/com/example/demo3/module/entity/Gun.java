package com.example.demo3.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;



@Data
@Accessors(chain = true)
public class Gun {

    private BigInteger id;
    private String title;
    private String author;
    private String images;
    private String content;
    private Integer createTime;
    private Integer updateTime;
    private Integer isDeleted;
    private BigInteger categoryId;

}
