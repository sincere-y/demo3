package com.example.demo3.app.controller;

import com.example.demo3.app.domain.*;
import com.example.demo3.app.feign.CategoryFeign;
import com.example.demo3.app.feign.GunFeign;

import com.example.demo3.common.entity.Category;
import com.example.demo3.common.entity.Gun;
import com.example.demo3.common.utils.Response;
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
    private CategoryFeign categoryFeign;
    @Autowired
    private GunFeign gunFeign;

    @RequestMapping("/category/list")
    public Response gunAllList(@RequestParam(name = "parentId", required = false) BigInteger parentId) {
        if (parentId == null) {
            return new Response(4006);
        }
        List<Category> categories = categoryFeign.getCategoriesByParentId(parentId);
        List<CategoryListCellVo> categoryListCellVo = new ArrayList<>();

        for (Category category : categories) {
            CategoryListCellVo vo = new CategoryListCellVo();
            vo.setId(category.getId());
            vo.setCategoryImage(category.getImage());
            vo.setCategoryName(category.getName());

            List<Category> childrens = categoryFeign.getCategoriesByParentId(category.getId());
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
        return new Response(1001,categoryListVo);

    }


    @RequestMapping("/category/multiLevelCategories")
    public Response multiLevelCategories(@RequestParam(name = "parentId") BigInteger parentId) {
        //顶级类目直接返回
        if (parentId == null) {
            return new Response(4006);
        }
        Category parentCategory= categoryFeign.getById(parentId);
        if(parentCategory==null){
            return new Response(4005);
        }else if (parentCategory.getParentId()==null){
            return new Response(4004);
        }
       
        //不存在直接返回
        List<Category> categories = categoryFeign.getCategoriesByParentId(parentId);
        if (categories.isEmpty()) {
            return new Response(4004);
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
        List<Category> leafCategories = categoryFeign.getLeafCategories(parentId);

        List<GunListCellVo> content = new ArrayList<>();
        List<BigInteger> categoryIds = new ArrayList<>();
        HashMap<BigInteger, String> map = new HashMap<>();
        for (Category category : leafCategories) {
            categoryIds.add(category.getId());
            map.put(category.getId(), category.getName());
        }
        if (categoryIds.size() > 0) {
                List<Gun> guns = gunFeign.getGunsByCategoryIds(categoryIds);
                if(guns.isEmpty()){
                    for (Gun gun : guns) {
                        String categoryName = map.get(gun.getCategoryId());
                        GunListCellVo vo = new GunListCellVo();
                        if (categoryName != null) {

                            vo.setCategoryName(categoryName);
                            vo.setGunId(gun.getId());
                            vo.setTitle(gun.getTitle());
                            vo.setCreateTime(gunFeign.timeText(gun.getCreateTime()));

                            GunListImage gunListImage = new GunListImage();
                            String firstImageUrl = gun.getImages().split("\\$")[0];
                            gunListImage.setSrc(firstImageUrl);
                            gunListImage.setAr(gunFeign.calculateAspectRatio(firstImageUrl));
                            vo.setImage(gunListImage);
                            content.add(vo);

                            }
                    }
                }

            multiLevelCategoriesVo.setGunListVo(content);

        }
        return new Response(1001, multiLevelCategoriesVo);
    }



}