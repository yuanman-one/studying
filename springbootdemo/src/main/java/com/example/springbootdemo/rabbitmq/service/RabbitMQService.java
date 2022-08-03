package com.example.springbootdemo.rabbitmq.service;

public interface RabbitMQService {
    String sendMallMsg(String msg) throws Exception;
}
