package com.example.demo3.console.controller;

import com.example.demo3.console.domain.GunInfoVo;
import com.example.demo3.console.domain.GunListCellVo;
import com.example.demo3.console.domain.GunListVo;
import com.example.demo3.module.entity.Category;
import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.service.CategoryService;
import com.example.demo3.module.service.GunService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String gunCreate(@RequestParam(name = "title")String title,
                            @RequestParam(name = "author")String author,
                            @RequestParam(name = "images")String images,
                            @RequestParam(name = "content")String content,
                            @RequestParam(name = "categoryId")BigInteger categoryId) {
        try {


            BigInteger id = service.edit(null, title.trim(), author.trim(), images, content,categoryId);
            return id.toString();
        }catch (RuntimeException e){
            log.error("编辑枪支信息失败", e);
            return e.getMessage();

        }
    }

    @RequestMapping("/gun/update")
    public String gunUpdate(@RequestParam(name = "gunId")BigInteger gunId,
                            @RequestParam(name = "title")String title,
                            @RequestParam(name = "author")String author,
                            @RequestParam(name = "images") String images,
                            @RequestParam(name = "content")String content,
                            @RequestParam(name = "categoryId")BigInteger categoryId){
        try{
            BigInteger id = service.edit(gunId,title.trim(),author.trim(),images,content,categoryId);
            return id.toString();
        }
        catch (RuntimeException e){
            System.out.println(e);
            return e.getMessage();
        }

    }
    @RequestMapping("/gun/delete")
    public String gunDelete(@RequestParam(name = "gunId")BigInteger gunId) {
        int result = service.deleteGun(gunId);
        return 1 == result ? "成功":"失败";
    }

    @RequestMapping("/gun/list")
    public GunListVo gunAllList(@RequestParam(name = "page")Integer page,
                                @RequestParam(name ="gunName",required = false )String gunName) {

        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("username")){
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

                    return gunListVo ;
                }
            }
        }
        return null;

    }

    @RequestMapping("/gun/info")
    public GunInfoVo gunInfo(@RequestParam(name = "gunId") BigInteger gunId) {
        GunInfoVo gunInfoVo = new GunInfoVo();
        Gun gun = service.getById(gunId);
        if(gun!=null) {
            gunInfoVo.setTitle(gun.getTitle());
            gunInfoVo.setAuthor(gun.getAuthor());
            gunInfoVo.setContent(gun.getContent());
            gunInfoVo.setCreateTime(service.timeText(gun.getCreateTime()));
            gunInfoVo.setUpdateTime(service.timeText(gun.getUpdateTime()));
            gunInfoVo.setImages(Arrays.asList(gun.getImages().split("\\$")));
        }
        return gunInfoVo;
    }






}
