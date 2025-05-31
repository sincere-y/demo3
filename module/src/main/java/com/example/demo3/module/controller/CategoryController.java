package com.example.demo3.module.controller;

import com.example.demo3.common.entity.Category;
import com.example.demo3.module.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/category/idList")
    public List<Integer> getCategoryId(@RequestParam(name ="gunName")String gunName){
        return categoryService.getCategoryId(gunName);
    }
    @RequestMapping("/category/byId")
    public Category getById(@RequestParam(name ="id")BigInteger id) {
        return categoryService.getById(id);
    }
    @RequestMapping("/category/all")
    public List<Category> getAllInfo(){
        return categoryService.getAllInfo();
    }
    @RequestMapping("/categoriesByParentId")
    public List<Category> getCategoriesByParentId(@RequestParam(name ="parentId")BigInteger parentId){
        return categoryService.getCategoriesByParentId(parentId);
    }
    @RequestMapping("/category/extractById")
    public Category extractById(@RequestParam(name ="id")BigInteger id){
        return categoryService.extractById(id);
    }
    @RequestMapping("/category/insert")
    public int insert(@RequestParam(name ="category")Category category){
        return categoryService.insert(category);
    }
    @RequestMapping("/category/update")
    public int update(@RequestParam(name ="category")Category category){
        return categoryService.update(category);
    }
    @RequestMapping("/category/delete")
    public int delete(@RequestParam(name ="id")BigInteger id){
        return categoryService.delete(id);
    }
    @RequestMapping("/category/infoPage")
    public List<Category> getInfoPage(@RequestParam(name ="page")Integer page,
                                      @RequestParam(name ="pageSize")Integer pageSize){
        return categoryService.getInfoPage(page, pageSize);
    }
    @RequestMapping("/category/infoByIds")
    public List<Category> getInfoByIds(@RequestParam(name ="ids")List<BigInteger> ids){
        return categoryService.getInfoByIds(ids);
    }
    @RequestMapping("/category/leafCategories")
    public List<Category> getLeafCategories(@RequestParam(name ="parentId")BigInteger parentId) {
        return categoryService.getLeafCategories(parentId);
    }

}
