package com.example.demo3.app.controller;

import com.example.demo3.app.domain.*;
import com.example.demo3.module.entity.Category;
import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.service.CategoryService;
import com.example.demo3.module.service.GunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GunService gunService;


    @RequestMapping("/category/list")
    public CategoryListVo gunAllList(@RequestParam(name = "parentId", required = false) BigInteger parentId) {
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
        return categoryListVo;

    }


    @RequestMapping("/category/multiLevelCategories")
    public MultiLevelCategoriesVo multiLevelCategories(@RequestParam(name = "parentId") BigInteger parentId) {
        //顶级类目直接返回
        if(categoryService.getById(parentId).getParentId()==null){
            return null;
        }
        if (parentId == null) {
            return null;
        }
        //不存在直接返回
        List<Category> categories = categoryService.getCategoryByParentId(parentId);
        if (categories == null) {
            return null;
        }

        List<MultiLevelCategoriesCellVo> resultList = new ArrayList<>();

        for (Category category : categories) {


                MultiLevelCategoriesCellVo childrenVo = new MultiLevelCategoriesCellVo();
                childrenVo.setId(category.getId());
                childrenVo.setCategoryImage(category.getImage());
                childrenVo.setCategoryName(category.getName());
                resultList.add(childrenVo);

        }
        MultiLevelCategoriesVo multiLevelCategoriesVo = new MultiLevelCategoriesVo();
        multiLevelCategoriesVo.setCategoriesListVo(resultList);

        // 叶子节点查询方法
        List<Category> leafCategories = categoryService.getLeafCategories(parentId);

        List<GunListCellVo> content = new ArrayList<>();
        List<BigInteger> categoryIds = new ArrayList<>();

        for (Category category : leafCategories) {
            categoryIds.add(category.getId());
        }
        HashMap<BigInteger, String> map = new HashMap<>();
        for (Category category : leafCategories) {
            map.put(category.getId(), category.getName());
        }
        if (categoryIds.size() > 0) {


                List<Gun> guns = gunService.getGunByCategoryId(categoryIds);
                for (Gun gun : guns) {
                    if(gun!=null) {
                        String categoryName = map.get(gun.getCategoryId());
                        GunListCellVo vo = new GunListCellVo();

                        if (categoryName != null) {
                            vo.setCategoryName(categoryName);
                            vo.setGunId(gun.getId());
                            vo.setTitle(gun.getTitle());
                            vo.setCreateTime(gunService.timeText(gun.getCreateTime()));

                            GunListImage gunListImage = new GunListImage();
                            String firstImageUrl = gun.getImages().split("\\$")[0];
                            gunListImage.setSrc(firstImageUrl);
                            gunListImage.setAr(gunService.calculateAspectRatio(firstImageUrl));
                            vo.setImage(gunListImage);

                            content.add(vo);
                        }
                    }
                }

            multiLevelCategoriesVo.setGunListVo(content);

        }
        return multiLevelCategoriesVo;
    }



}