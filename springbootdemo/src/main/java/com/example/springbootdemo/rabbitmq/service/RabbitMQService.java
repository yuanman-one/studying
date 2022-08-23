package com.example.springbootdemo.rabbitmq.service;

import org.springframework.ui.ModelMap;

public interface RabbitMQService {
    ModelMap sendDirectMsg(String msg) throws Exception;

    ModelMap sendDelayMsg(String msg, String type) throws Exception;

    ModelMap sendFanoutMsg(String msg) throws Exception;
}
