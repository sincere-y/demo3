package com.example.demo3.tag.service;

import com.example.demo3.common.entity.Tag;

import com.example.demo3.tag.mapper.TagMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
 public class TagService {
 @Resource
 private TagMapper mapper;

 public Tag getById(BigInteger id) {
 return mapper.getById(id);
 }

 public Tag extractById(BigInteger id) {
 return mapper.extractById(id);
 }


 public int insert(Tag tag){

 return mapper.insert(tag);
 }
 public Tag getByName(String name){
  return mapper.getByName(name);
 }

 public int update(Tag tag){

 return mapper.update(tag);

 }


 public int delete(BigInteger id) {
 return mapper.delete(id, (int) (System.currentTimeMillis() / 1000));
 }

 public BigInteger edit(String name){
  if(name!= null){
   int timestamp = (int) (System.currentTimeMillis() / 1000);
   Tag tag = new Tag();

   tag.setUpdateTime(timestamp).setCreateTime(timestamp).setIsDeleted(0).setName(name);
   insert(tag);
   return tag.getId();
  }
  else {
   throw new RuntimeException("参数为空");
  }



 }



 }



