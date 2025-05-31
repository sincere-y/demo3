package com.example.demo3.console.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    Response handleControllerException(Exception exception) {
        return new Response(4004, exception.getStackTrace());
    }


}
