package com.example.demo3.user.controller;


import com.example.demo3.common.entity.User;

import com.example.demo3.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/user/byId")
    public User getById(@RequestParam(name ="id")BigInteger id){
        return userService.getById(id);
    }
    @RequestMapping("/user/insert")
    public int insert(@RequestParam(name ="username")String username,
                      @RequestParam(name ="password")String password){
        return userService.insert(username,password);
    }
    @RequestMapping("/user/selectByUsername")
    public User selectByUsername(@RequestParam(name ="username")String username){
        return userService.selectByUsername(username);
    }



}
