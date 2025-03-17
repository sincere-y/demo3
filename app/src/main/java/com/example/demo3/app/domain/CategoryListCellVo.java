package com.example.demo3.app.domain;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class CategoryListCellVo {
    private BigInteger id;
    private String categoryName;
    private String categoryImage;
    private List<CategoryListCellVo> list;

}
