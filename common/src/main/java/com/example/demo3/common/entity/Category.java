package com.example.demo3.common.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;


@Data
@Accessors(chain = true)
public class Category {
    @ExcelProperty("id")
    private BigInteger id;
    @ExcelProperty("父id")
    private BigInteger parentId;
    @ExcelProperty("创建时间")
    private Integer createTime;
    @ExcelProperty("更新时间")
    private Integer updateTime;
    @ExcelProperty("是否删除")
    private Integer isDeleted;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("图片")
    private String image;

}
