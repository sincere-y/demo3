package com.example.demo3.module.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;


@Data
@Accessors(chain = true)
public class Category {

    private BigInteger id;
    private BigInteger parentId;
    private Integer createTime;

    private Integer updateTime;

    private Integer isDeleted;

    private String name;

    private String image;

}
