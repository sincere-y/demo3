package com.example.demo3.app.domain;

import lombok.Data;

import java.util.List;
@Data
public class MultiLevelCategoriesVo {

 private List<MultiLevelCategoriesCellVo> categoriesListVo;
 private List<GunListCellVo> gunListVo;

}
