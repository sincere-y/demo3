package com.example.demo3.app.domain;

import lombok.Data;

import java.util.List;
@Data
public class ListVo {

    private List<GunListVo> list;

   public ListVo(List<GunListVo> list) {
       this.list = list;
   }

}
