package com.example.demo3.module.controller;

import com.example.demo3.common.dto.GunDto;
import com.example.demo3.common.entity.Gun;

import com.example.demo3.module.service.GunService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class GunController {
    @Autowired
    private GunService gunService;
    @RequestMapping("/gun/getGunById")
    public Gun getById(@RequestParam(name ="id")BigInteger id){
        return gunService.getById(id);
    }
    @RequestMapping("/gun/extractById")
    public Gun extractById(@RequestParam(name ="id")BigInteger id){
        return gunService.extractById(id);
    }
    @RequestMapping("/gun/allInfo")
    public List<Gun> getAllInfo(){
        return gunService.getAllInfo();
    }

    @RequestMapping("/gun/total")
    public int getTotal(@RequestParam(name ="gunName")String gunName){
        return gunService.getTotal(gunName);
    }

    @RequestMapping("/gun/edit")
    public BigInteger edit(@RequestParam(name ="id")BigInteger id,
                           @RequestParam(name ="title")String title,
                           @RequestParam(name ="author")String author,
                           @RequestParam(name ="images")String images,
                           @RequestParam(name ="content")String content,
                           @RequestParam(name ="categoryId")BigInteger categoryId,
                           @RequestParam(name ="arrTags")String[] arrTags){
      return  gunService.edit(id,title,author,images,content,categoryId,arrTags);

    }

    @RequestMapping("/gun/delete")
    public int deleteGun(@RequestParam(name ="gunId")BigInteger gunId){
       return gunService.deleteGun(gunId);
    }
    @RequestMapping("/gun/infoPage")
    public List<Gun> getInfoPage(@RequestParam(name ="page")Integer page,
                                 @RequestParam(name ="pageSize")Integer pageSize,
                                 @RequestParam(name ="gunName")String gunName){
       return gunService.getInfoPage(page,pageSize,gunName);
    }


    @RequestMapping("/gun/updateImageUrl")
    public int  updateImageUrl(@RequestParam(name ="gunId")BigInteger gunId,
                               @RequestParam(name ="url")String url){
        return gunService.updateImageUrl(gunId,url);


    }
    @RequestMapping("/gun/calculateAspectRatio")
    public float calculateAspectRatio(@RequestParam(name ="imageUrl")String imageUrl) {
        return gunService.calculateAspectRatio(imageUrl);
    }
    @RequestMapping("/gun/gunsByCategoryIds")
    public List<Gun> getGunsByCategoryIds(@RequestParam(name ="ids")List<BigInteger> ids){
        return gunService.getGunsByCategoryIds(ids);
    }
@RequestMapping("/timeText")
    public String timeText(@RequestParam(name ="createTime")Integer createTime) {
        return gunService.timeText(createTime);
    }

@RequestMapping("/gun/getGunDtoList")
    public List<GunDto> getGunDtoList(Integer page, Integer pageSize, String gunName){
        return gunService.getGunDtoList(page,pageSize,gunName);

    }
}
