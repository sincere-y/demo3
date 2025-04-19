package com.example.demo3.app.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo3.app.domain.*;
import com.example.demo3.module.dto.GunDto;
import com.example.demo3.module.entity.Category;
import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.entity.GunTagRelation;
import com.example.demo3.module.service.CategoryService;
import com.example.demo3.module.service.GunTagRelationService;
import com.example.demo3.module.service.TagService;
import com.example.demo3.module.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo3.module.service.GunService;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import java.nio.charset.StandardCharsets;
import java.util.*;


@Slf4j
@RestController
public class GunController {
    @Autowired
    private GunService gunService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GunTagRelationService gunTagRelationService;
    @Autowired
    private TagService tagService;


    @RequestMapping("/gun/list")
    public Response gunAllList(@RequestParam(name = "wp",required = false) String wp,
                               @RequestParam(name = "keyWord",required = false) String keyWord
                                /*@RequestParam(name = "page")Integer page,
                                @RequestParam(name ="gunName",required = false )String gunName*/) {
        WapperVo wapperVo;
        Integer page=1;
        String gunName= keyWord;
        if(wp!=null){
            byte[] decodedBytes = Base64.getUrlDecoder().decode(wp.getBytes(StandardCharsets.UTF_8));
            try{
                wapperVo = JSON.parseObject(decodedBytes, WapperVo.class);
                page = wapperVo.getPage();
                gunName = wapperVo.getGunName();
            } catch (Exception e) {
                return new Response(4004);
            }
        }
        Integer pageSize=6;
        List<Gun> guns = gunService.getInfoPage(page,pageSize,gunName);
        List<GunListCellVo> gunListCellVo = new ArrayList<>();
        List<BigInteger> categoryIds= new ArrayList<>();
        HashMap<BigInteger, String> map = new HashMap<>();
        for (Gun gun : guns) {
            categoryIds.add(gun.getCategoryId());
        }
        List<Category> categories = categoryService.getInfoByIds(categoryIds);
        for(Category category:categories){
            map.put(category.getId(),category.getName());
        }
        for (Gun gun : guns) {
            GunListCellVo vo = new GunListCellVo();
            String categoryName=map.get(gun.getCategoryId());
            if(categoryName!=null) {
                vo.setCategoryName(categoryName);
                vo.setGunId(gun.getId());
                vo.setTitle(gun.getTitle());
                vo.setCreateTime(gunService.timeText(gun.getCreateTime()));

                GunListImage gunListImage = new GunListImage();
                String firstImageUrl = gun.getImages().split("\\$")[0];
                gunListImage.setSrc(firstImageUrl);
                gunListImage.setAr(gunService.calculateAspectRatio(firstImageUrl));
               vo.setImage(gunListImage);

                gunListCellVo.add(vo);
            }

        }

        GunListVo gunListVo = new GunListVo();
        Integer presentpageSize = guns.size();

        if(presentpageSize < pageSize){//当前页面大小 小于 分页大小
            gunListVo.setIsEnd(true);
        }
        else gunListVo.setIsEnd(false);
        gunListVo.setList(gunListCellVo);

        WapperVo nextWapper = new WapperVo();
        nextWapper.setPage(page+1);
        nextWapper.setGunName(keyWord);
        String wpNext=null;
        try {
            // 将 Wapper 对象转换为 JSON 字符串
            String jsonString = JSON.toJSONString(nextWapper);
            // 对 JSON 字符串进行 URL 编码
            byte[] jsonBytes = jsonString.getBytes("UTF-8");
            wpNext = Base64.getUrlEncoder().encodeToString(jsonBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        gunListVo.setWp(wpNext);

        return new Response(1001,gunListVo);

    }

    @RequestMapping("/gun/info")
    public Response gunInfo(@RequestParam(name = "gunId") BigInteger gunId) {
        GunInfoVo gunInfoVo = new GunInfoVo();
        Gun gun = gunService.getById(gunId);
        if(gun==null) {
            return new Response(4004);
        }
        else {
            Category category = categoryService.getById(gun.getCategoryId());
            if (category == null) {
                return new Response(1001,gunInfoVo);
            } else {
                gunInfoVo.setTitle(gun.getTitle());
                gunInfoVo.setAuthor(gun.getAuthor());
                gunInfoVo.setCreateTime(gunService.timeText(gun.getCreateTime()));
                gunInfoVo.setImages(Arrays.asList(gun.getImages().split("\\$")));
                gunInfoVo.setCategoryName(category.getName());
                gunInfoVo.setCategoryImage(category.getImage());
                List<GunTagRelation> gunTagRelations=  gunTagRelationService.getByGunId(gunId);
                for(GunTagRelation gunTagRelation:gunTagRelations){
                    gunInfoVo.getTag().add(tagService.getById(gunTagRelation.getTagId()));
                }

                try {
                    List<BaseContentValueVo> contents = JSON.parseArray(gun.getContent(), BaseContentValueVo.class);
                    gunInfoVo.setContent(contents);
                } catch (Exception cause) {

                    return new Response(4004);
                }
                return new Response(1001,gunInfoVo);
            }
        }
    }
//联表查询测试
    @RequestMapping("/gun/list1")
    public Response gunAllListUseJoin(@RequestParam(name = "wp",required = false) String wp,
                                @RequestParam(name = "keyWord",required = false) String keyWord
                                /*@RequestParam(name = "page")Integer page,
                                @RequestParam(name ="gunName",required = false )String gunName*/) {
        WapperVo wapperVo;
        Integer page=1;
        String gunName= keyWord;
        if(wp!=null){
            byte[] decodedBytes = Base64.getUrlDecoder().decode(wp.getBytes(StandardCharsets.UTF_8));
            try{
                wapperVo = JSON.parseObject(decodedBytes, WapperVo.class);
                page = wapperVo.getPage();
                gunName = wapperVo.getGunName();
            } catch (Exception e) {
                return new Response(4004);
            }
        }
        Integer pageSize=6;
        List<GunDto> guns = gunService.getGunDtoList(page,pageSize,gunName);
        List<GunListCellVo> gunListCellVo = new ArrayList<>();

        for (GunDto gun : guns) {
            GunListCellVo vo = new GunListCellVo();
                if(gun.getCategoryName()!=null) {
                    vo.setCategoryName(gun.getCategoryName());
                    vo.setGunId(gun.getId());
                    vo.setTitle(gun.getTitle());
                    vo.setCreateTime(gunService.timeText(gun.getCreateTime()));
//                    vo.setImage(gun.getImages().split("\\$")[0]);
                    gunListCellVo.add(vo);
                }
        }

        GunListVo gunListVo = new GunListVo();
        Integer presentpageSize = guns.size();

        if(presentpageSize < pageSize){
            gunListVo.setIsEnd(true);
        }
        else gunListVo.setIsEnd(false);
        gunListVo.setList(gunListCellVo);

        WapperVo nextWapper = new WapperVo();
        nextWapper.setPage(page+1);
        nextWapper.setGunName(keyWord);
        String wpNext=null;
        try {

            String jsonString = JSON.toJSONString(nextWapper);

            byte[] jsonBytes = jsonString.getBytes("UTF-8");
            wpNext = Base64.getUrlEncoder().encodeToString(jsonBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        gunListVo.setWp(wpNext);

        return new Response(1001,gunListVo);

    }


}
