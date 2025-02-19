package com.example.demo3.app.domain;

import java.util.List;

public class ListVo {

    private List<GunListVo> list;

    public ListVo(List<GunListVo> list) {
        this.list = list;
    }

    public void setList(List<GunListVo> list) {
        this.list = list;
    }
    public List<GunListVo> getList() {
        return list;
    }

}
