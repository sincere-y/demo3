package com.example.demo3.gun.service;

import com.example.demo3.common.entity.GunTagRelation;

import com.example.demo3.gun.mapper.GunTagRelationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
 public class GunTagRelationService {
 @Resource
 private GunTagRelationMapper mapper;

 public GunTagRelation getById(BigInteger id) {
 return mapper.getById(id);
 }

 public GunTagRelation extractById(BigInteger id) {
 return mapper.extractById(id);
 }


 public int insert(GunTagRelation gunTagRelation){

 return mapper.insert(gunTagRelation);
 }


 public int update(GunTagRelation gunTagRelation){

 return mapper.update(gunTagRelation);

 }
public List<GunTagRelation> getByGunId(BigInteger gunId){
  return mapper.getByGunId(gunId);
}

 public int delete(BigInteger id) {
 return mapper.delete(id, (int) (System.currentTimeMillis() / 1000));
 }

 public BigInteger edit(BigInteger gunId, BigInteger tagId){
  if(gunId!= null||tagId!=null){
   int timestamp = (int) (System.currentTimeMillis() / 1000);
   GunTagRelation gunTagRelation= new GunTagRelation();

   gunTagRelation.setUpdateTime(timestamp).setCreateTime(timestamp).setIsDeleted(0).setGunId(gunId).setTagId(tagId);
   insert(gunTagRelation);
   return gunTagRelation.getId();
  }
  else {
   throw new RuntimeException("参数为空");
  }
 }



 }



