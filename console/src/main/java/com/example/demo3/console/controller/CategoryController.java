package com.example.demo3.console.controller;


import com.example.demo3.console.domain.CategoryListCellVo;
import com.example.demo3.console.domain.CategoryListVo;
import com.example.demo3.module.entity.Category;
import com.example.demo3.module.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category/list")
    public CategoryListVo gunAllList(@RequestParam(name = "parentId", required = false) BigInteger parentId) {
        // 获取指定parentId的分类列表（若parentId为null，则获取顶级分类）
        List<Category> categories = categoryService.getCategoriesByParentId(parentId);
        List<CategoryListCellVo> categoryVos = new ArrayList<>();

        // 遍历每个分类，递归构建其子分类结构
        for (Category category : categories) {
            categoryVos.add(buildCategoryVo(category));
        }

        CategoryListVo result = new CategoryListVo();
        result.setList(categoryVos);
        return result;
    }


    private CategoryListCellVo buildCategoryVo(Category category) {
        CategoryListCellVo vo = new CategoryListCellVo();
        vo.setId(category.getId());
        vo.setCategoryImage(category.getImage());
        vo.setCategoryName(category.getName());

        // 获取当前分类的直接子分类
        List<Category> children = categoryService.getCategoriesByParentId(category.getId());
        List<CategoryListCellVo> childrenVos = new ArrayList<>();

        // 递归处理每个子分类
        for (Category child : children) {
            childrenVos.add(buildCategoryVo(child));
        }

        vo.setList(childrenVos);
        return vo;
    }
}
