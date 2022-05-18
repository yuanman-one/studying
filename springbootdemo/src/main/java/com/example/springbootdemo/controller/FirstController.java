package com.example.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/first")
@RestController
@Slf4j
public class FirstController {

    @GetMapping("/test/get")
    public String firstTestGet(String str){
        log.info("记录日志打印在控制台，这是get请求到的第一个控制器接口,接收的参数："+str);
        return "这是get请求的第一个控制器接口，接收的参数："+str;
    }
    @PostMapping("/test/post")
    public String firstTestPost(String str){
        log.info("记录日志打印在控制台，这是post请求到的第一个控制器接口,接收的参数："+str);
        return "这是post请求的第一个控制器接口，接收的参数："+str;
    }
}
