package com.example.demo3.module.service;

import com.example.demo3.common.entity.User;

import com.example.demo3.module.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    public int update(User user){
        return mapper.update(user);
    }
    public int delete(BigInteger id,int timestamp){
        return mapper.delete(id,timestamp);
    }
    public User getById(BigInteger id){
        return mapper.getById(id);
    }

    public User extractById(BigInteger id){
        return mapper.extractById(id);
    }
    public List<User> getAllInfo(){
        return mapper.getAllInfo();
    }

    public int insert(String username,String password){
        if(username!=null&&password!=null) {
            int timestamp = (int) (System.currentTimeMillis() / 1000);
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setIsDeleted(0);
            user.setUpdateTime(timestamp);
            user.setCreateTime(timestamp);

            return mapper.insert(user);
        }
        else {
            return 0;
        }

    }

    public User selectByUsername(String username){
        return mapper.selectByUsername(username);
    }



}
