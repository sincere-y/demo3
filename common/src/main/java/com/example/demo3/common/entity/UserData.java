package com.example.demo3.common.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class UserData {

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

}
