package com.example.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/first")
@RestController
@Slf4j
public class FirstController {

    @GetMapping("/test")
    public String firstTest(String str){
        log.info("记录日志打印在控制台，这是请求到的第一个控制器接口,接收的参数："+str);
        return "这是请求的第一个控制器接口，接收的参数："+str;
    }
}
