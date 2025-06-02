package com.example.demo3.app.feign;

import com.example.demo3.common.entity.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

@FeignClient(name = "category",contextId = "CategoryFeign")
public interface CategoryFeign {
    @RequestMapping("/category/idList")
     List<Integer> getCategoryId(@RequestParam(name ="gunName")String gunName);
    @RequestMapping("/category/byId")
     Category getById(@RequestParam(name ="id") BigInteger id) ;
    @RequestMapping("/category/all")
     List<Category> getAllInfo();
    @RequestMapping("/categoriesByParentId")
    List<Category> getCategoriesByParentId(@RequestParam(name ="parentId")BigInteger parentId);
    @RequestMapping("/category/extractById")
     Category extractById(@RequestParam(name ="id")BigInteger id);
    @RequestMapping("/category/insert")
     int insert(@RequestParam(name ="category")Category category);
    @RequestMapping("/category/update")
     int update(@RequestParam(name ="category")Category category);
    @RequestMapping("/category/delete")
     int delete(@RequestParam(name ="id")BigInteger id);
    @RequestMapping("/category/infoPage")
     List<Category> getInfoPage(@RequestParam(name ="page")Integer page,
                                @RequestParam(name ="pageSize")Integer pageSize);
    @RequestMapping("/category/infoByIds")
     List<Category> getInfoByIds(@RequestParam(name ="ids")List<BigInteger> ids);
    @RequestMapping("/category/leafCategories")
     List<Category> getLeafCategories(@RequestParam(name ="parentId")BigInteger parentId) ;

}
