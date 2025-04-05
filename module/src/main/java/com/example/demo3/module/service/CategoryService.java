package com.example.demo3.module.service;


import com.example.demo3.module.entity.Category;
import com.example.demo3.module.mapper.CategoryMapper;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
 public class CategoryService {
 @Resource
 private CategoryMapper mapper;

 public List<Integer> getCategoryId(String gunName){return mapper.getCategoryId(gunName);}
 public Category getById(BigInteger id) {
 return mapper.getById(id);
 }
 public List<Category> getAllInfo(){
  return mapper.getAllInfo();
 }
 public List<Category> getCategoryByParentId(BigInteger parentId){
  return mapper.getCategoryByParentId(parentId);
 }


 public Category extractById(BigInteger id) {
 return mapper.extractById(id);
 }

 public int insert(Category category){

 return mapper.insert(category);
 }
 public int update(Category category){

 return mapper.update(category);
 }

 public int delete(BigInteger id) {
 return mapper.delete(id, (int) (System.currentTimeMillis() / 1000));
 }

 public List<Category> getInfoPage(Integer page, Integer pageSize){

  Integer start = (page - 1) * pageSize;
  return mapper.getInfoPage(start, pageSize);
 }

public List<Category> getInfoByIds(List<BigInteger> ids){
 StringBuilder resultIds = new StringBuilder();
 if (ids != null) {
  for (int i = 0; i < ids.size(); i++) {
   if (i > 0) {
    resultIds.append(",");
   }
   resultIds.append(ids.get(i));
  }
 }
 String categoryIds = resultIds.toString();
  return mapper.getInfoByIds(categoryIds);
}




 public List<Category> getLeafCategories(BigInteger parentId) {
  List<Category> result = new ArrayList<>();
  findLeaves(parentId, result);
  return result;
 }

 private void findLeaves(BigInteger parentId, List<Category> result) {
  //根据parentID查询当前类目
  List<Category> categories = getCategoryByParentId(parentId);

  //根据当前类目查询子类目
  for (Category category : categories) {
   List<Category> childrens = getCategoryByParentId(category.getId());
   if (childrens.isEmpty()) {
    // 当前类目自身是叶子节点
    Category self = getById(category.getId());

    result.add(self);
   }else {
    findLeaves(category.getId(), result);
   }

  }

//  for (Category child : childrens) {
//   List<Category> grandChildren = getCategoryByParentId(child.getId());
//   if (grandChildren.isEmpty()) {
//    result.add(child); // 叶子节点
//   } else {
//    findLeaves(child.getId(), result); // 递归查找
//   }
//  }
 }








 }



