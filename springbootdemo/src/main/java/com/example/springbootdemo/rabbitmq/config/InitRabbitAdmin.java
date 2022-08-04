package com.example.springbootdemo.rabbitmq.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化rabbitAdmin对象 暂时未发现什么作用
 *
 * @author yuanman
 */
@Configuration
public class InitRabbitAdmin implements BeanPostProcessor {

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
    //后置处理器
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //创建交换机
        //rabbitAdmin.declareExchange(rabbitmqDemoDirectExchange());
        //创建队列
        //rabbitAdmin.declareQueue(rabbitmqDemoDirectQueue());
        return null;
    }
}
