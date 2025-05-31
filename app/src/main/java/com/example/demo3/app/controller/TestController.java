package com.example.demo3.app.controller;

import com.example.demo3.app.domain.CategoryListVo;

import com.example.demo3.common.utils.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {


    @RequestMapping("/concatenateStringBuilder")
    public Response safeLength() {
        List<Integer> list = generateList();
        StringBuilder result = new StringBuilder();
        Thread thread = new Thread(() -> {
            for (int i =0; i < 10000; i++) {
                result.append(list.get(i));
            }
        });
        Thread thread1 = new Thread(() -> {
            for (int i =0; i < 10000; i++) {
                result.append(list.get(i));
            }
        });
        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Response<>(1001,result.length()) ;
    }

    @RequestMapping("/concatenateStringBuffer")
    public Response unSafeLength() {
        List<Integer> list = generateList();
        StringBuffer result = new StringBuffer();
        Thread thread = new Thread(() -> {
            for (int i =0; i < 10000; i++) {
                result.append(list.get(i));
            }
        });
        Thread thread1 = new Thread(() -> {
            for (int i =0; i < 10000; i++) {
                result.append(list.get(i));
            }
        });
        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Response<>(1001,result.length()) ;
    }


    public List<Integer> generateList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(1);
        }
        return list;
    }
}
