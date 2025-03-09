package com.example.demo3.module.service;


import com.example.demo3.module.entity.Category;
import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.mapper.CategoryMapper;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
 public class CategoryService {
 @Resource
 private CategoryMapper mapper;

 public Category getById(BigInteger id) {
 return mapper.getById(id);
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



 }



