package com.example.demo3.app.domain;

import lombok.Data;

import java.util.List;

@Data
public class CategoryListVo {

    private List<CategoryListCellVo> list;
    private Boolean isEnd;
}
