package com.example.springbootdemo.base.controller;

import com.example.springbootdemo.base.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/first")
@RestController
@Slf4j
public class FirstController {

    @GetMapping("/test/get")
    public ModelMap firstTestGet(String str){
        log.info("记录日志打印在控制台，这是get请求到的第一个控制器接口,接收的参数："+str);

        return ResponseUtil.retCorrectInfo("这是get请求的第一个控制器接口，接收的参数："+str);
    }
    @PostMapping("/test/post")
    public ModelMap firstTestPost(String str){

        log.info("记录日志打印在控制台，这是post请求到的第一个控制器接口,接收的参数："+str);
        return ResponseUtil.retCorrectInfo("这是post请求的第一个控制器接口，接收的参数："+str);
    }
}
