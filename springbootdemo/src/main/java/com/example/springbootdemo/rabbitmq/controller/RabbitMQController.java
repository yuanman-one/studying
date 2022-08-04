package com.example.springbootdemo.rabbitmq.controller;

import com.example.springbootdemo.rabbitmq.service.RabbitMQService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuanman
 */
@RestController
@RequestMapping("/mall/rabbitmq")
public class RabbitMQController {
    @Resource
    private RabbitMQService rabbitMQService;
    /**
     * 发送消息
     * @return
     */
    @PostMapping("/sendDirectMsg")
    public ModelMap sendDirectMsg(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendDirectMsg(msg);
    }
    @PostMapping("/sendFanoutMsg")
    public ModelMap sendFanoutMsg(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendFanoutMsg(msg);
    }
}
