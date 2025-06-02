package com.example.demo3.tag.controller;


import com.example.demo3.common.entity.Tag;

import com.example.demo3.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping("/tag/edit")
    public BigInteger edit(@RequestParam(name ="name")String name){
       return tagService.edit(name);

    }
@RequestMapping("/tag/byId")
    public Tag getById(@RequestParam(name ="id")BigInteger id) {
        return tagService.getById(id);
    }


@RequestMapping("/tag/byName")
    public Tag getByName(@RequestParam(name ="name")String name){
        return tagService.getByName(name);
    }

}
