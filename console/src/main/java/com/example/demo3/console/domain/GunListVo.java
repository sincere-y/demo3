package com.example.demo3.console.domain;

import lombok.Data;

import java.util.List;
@Data
public class GunListVo {

    private List<GunListCellVo> list;
    private Integer total;
    private Integer pageSize;
}
