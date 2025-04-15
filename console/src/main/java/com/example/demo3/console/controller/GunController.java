package com.example.demo3.console.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo3.console.domain.BaseContentValueVo;
import com.example.demo3.console.domain.GunInfoVo;
import com.example.demo3.console.domain.GunListCellVo;
import com.example.demo3.console.domain.GunListVo;
import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.service.CategoryService;
import com.example.demo3.module.service.GunService;
import com.example.demo3.module.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
@RestController
public class GunController {

    @Autowired
    private GunService service;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/gun/create")
    public Response gunCreate(@RequestParam(name = "title")String title,
                              @RequestParam(name = "author")String author,
                              @RequestParam(name = "images")String images,
                              @RequestParam(name = "content")String content,
                              @RequestParam(name = "categoryId")BigInteger categoryId) {
        try {


            BigInteger id = service.edit(null, title.trim(), author.trim(), images, content,categoryId);
            return new Response(1001,id.toString());
        }catch (RuntimeException e){
            log.error("编辑枪支信息失败", e);
            return new Response(3051);

        }
    }

    @RequestMapping("/gun/update")
    public Response gunUpdate(@RequestParam(name = "gunId")BigInteger gunId,
                            @RequestParam(name = "title")String title,
                            @RequestParam(name = "author")String author,
                            @RequestParam(name = "images") String images,
                            @RequestParam(name = "content")String content,
                            @RequestParam(name = "categoryId")BigInteger categoryId){
        try{
            BigInteger id = service.edit(gunId,title.trim(),author.trim(),images,content,categoryId);
            return new Response(1001,id.toString());
        }
        catch (RuntimeException e){
            System.out.println(e);
            return new Response(3051);
        }

    }
    @RequestMapping("/gun/delete")
    public Response gunDelete(@RequestParam(name = "gunId")BigInteger gunId) {
        int result = service.deleteGun(gunId);
        return new Response(1001,1 == result ? "成功":"失败");
    }

    @RequestMapping("/gun/list")
    public Response gunAllList(@RequestParam(name = "page")Integer page,
                                @RequestParam(name ="gunName",required = false )String gunName) {

        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("sign")){
                    Integer pageSize = 4;
                    List<Gun> guns =service.getInfoPage(page,pageSize,gunName);
                    List<GunListCellVo> gunListCellVo = new ArrayList<>();
                    for (Gun gun : guns) {
                        GunListCellVo vo = new GunListCellVo();
                        vo.setGunId(gun.getId());
                        vo.setTitle(gun.getTitle());
                        vo.setCreateTime(service.timeText(gun.getCreateTime()));
                        vo.setImage(gun.getImages().split("\\$")[0]);
                        gunListCellVo.add(vo);
                    }
                    GunListVo gunListVo = new GunListVo();
                    gunListVo.setList(gunListCellVo);
                    gunListVo.setTotal(service.getTotal(gunName));
                    gunListVo.setPageSize(pageSize);

                    return new Response(1001, gunListVo );
                }
            }
        }
        return new Response(1002);

    }

    @RequestMapping("/gun/info")
    public Response gunInfo(@RequestParam(name = "gunId") BigInteger gunId) {
        GunInfoVo gunInfoVo = new GunInfoVo();
        Gun gun = service.getById(gunId);
        if(gun!=null) {
            gunInfoVo.setTitle(gun.getTitle());
            gunInfoVo.setAuthor(gun.getAuthor());
            try {
                List<BaseContentValueVo> contents = JSON.parseArray(gun.getContent(), BaseContentValueVo.class);
                gunInfoVo.setContent(contents);
            } catch (Exception cause) {

                return new Response(4004);
            }
            gunInfoVo.setCreateTime(service.timeText(gun.getCreateTime()));
            gunInfoVo.setUpdateTime(service.timeText(gun.getUpdateTime()));
            gunInfoVo.setImages(Arrays.asList(gun.getImages().split("\\$")));

        }
        return new Response(1001,gunInfoVo);
    }






}
