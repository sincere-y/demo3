package com.example.demo3.app.controller;

import com.example.demo3.app.domain.CategoryListCellVo;
import com.example.demo3.app.domain.CategoryListVo;
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
    public CategoryListVo gunAllList(@RequestParam(name ="parentId",required = false ) BigInteger parentId) {
        if (parentId == null) {
            parentId = null;
        }
        List<Category> categories = categoryService.getCategoryByParentId(parentId);
        List<CategoryListCellVo> categoryListCellVo = new ArrayList<>();

        for (Category category : categories) {
            CategoryListCellVo vo = new CategoryListCellVo();
            vo.setId(category.getId());
            vo.setCategoryImage(category.getImage());
            vo.setCategoryName(category.getName());

            List<Category> childrens = categoryService.getCategoryByParentId(category.getId());
            List<CategoryListCellVo> categoryChildrenListCellVo = new ArrayList<>();
            for (Category children : childrens) {
                CategoryListCellVo childrenVo = new CategoryListCellVo();
                childrenVo.setId(children.getId());
                childrenVo.setCategoryImage(children.getImage());
                childrenVo.setCategoryName(children.getName());
                categoryChildrenListCellVo.add(childrenVo);
            }
            vo.setList(categoryChildrenListCellVo);
            categoryListCellVo.add(vo);
        }
        CategoryListVo categoryListVo = new CategoryListVo();
        categoryListVo.setList(categoryListCellVo);
        return categoryListVo ;

    }




}
