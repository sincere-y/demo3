package com.example.demo3.app.domain;

import lombok.Data;

import java.util.List;
@Data
public class GunListVo {

    private List<GunListCellVo> list;
    private Boolean isEnd;
    private String wp;

}
