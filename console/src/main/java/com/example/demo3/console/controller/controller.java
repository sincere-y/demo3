package com.example.demo3.console.controller;

import com.example.demo3.module.service.GunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class controller {

    @Autowired
    private GunService service;

    @RequestMapping("/gun/create")
    public String gunCreate(@RequestParam(name = "title")String title,
                            @RequestParam(name = "author")String author,
                            @RequestParam(name = "images")String images,
                            @RequestParam(name = "content")String content) {
        int result = service.createGun(title,author,images,content);
        return 1 == result ? "成功":"失败";
    }

    @RequestMapping("/gun/update")
    public String gunUpdate(@RequestParam(name = "id")BigInteger id,
                            @RequestParam(name = "title")String title,
                            @RequestParam(name = "author")String author,
                            @RequestParam(name = "images") String images,
                            @RequestParam(name = "content")String content) {
        int result = service.updateGun(id,title,author,images,content);
        return 1 == result ? "成功":"失败";
    }
    @RequestMapping("/gun/delete")
    public String gunDelete(@RequestParam(name = "id")BigInteger id) {
        int result = service.deleteGun(id);
        return 1 == result ? "成功":"失败";
    }
}
