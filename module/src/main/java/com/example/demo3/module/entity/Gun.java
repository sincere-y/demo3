package com.example.demo3.module.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;



@Data
@Accessors(chain = true)
public class Gun {
    @ExcelProperty("id")
    private BigInteger id;
    @ExcelProperty("标题")
    private String title;
    @ExcelProperty("作者")
    private String author;
    @ExcelProperty("图片")
    private String images;
    @ExcelProperty("内容")
    private String content;
    @ExcelProperty("创建时间")
    private Integer createTime;
    @ExcelProperty("更新时间")
    private Integer updateTime;
    @ExcelProperty("是否删除")
    private Integer isDeleted;
    @ExcelProperty("分类id")
    private BigInteger categoryId;

}
