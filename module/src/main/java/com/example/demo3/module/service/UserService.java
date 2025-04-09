package com.example.demo3.module.service;

import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.entity.User;
import com.example.demo3.module.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;
    public int insert(String username,String password){
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsDeleted(0);
        user.setUpdateTime(timestamp);
        user.setCreateTime(timestamp);

        return mapper.insert(user);

    }

    public User selectByUsername(String username){
        return mapper.selectByUsername(username);
    }



}
