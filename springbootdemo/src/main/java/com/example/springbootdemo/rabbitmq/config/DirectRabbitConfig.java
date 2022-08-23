package com.example.springbootdemo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *  Direct Exchange
 * 见文知意，直连交换机意思是此交换机需要绑定一个队列，要求该消息与一个特定的路由键完全匹配。
 * 简单点说就是一对一的，点对点的发送。
 * 这种类型需要配置routing key
 * @author yuanman
 */
@Configuration
public class DirectRabbitConfig  {

    @Bean
    public Queue rabbitmqDirectQueue() {
        /**
         * 1、name:    队列名称
         * 2、durable: 是否持久化
         * 3、exclusive: 是否独享、排外的。如果设置为true，定义为排他队列。则只有创建者可以使用此队列。也就是private私有的。
         * 4、autoDelete: 是否自动删除。也就是临时队列。当最后一个消费者断开连接后，会自动删除。
         * 5.arguments 绑定死信交换机死信队列
         * */
        return new Queue(RabbitMQConfig.DIRECT_EXCHANGE_TOPIC, true, false, false);
    }

    @Bean
    public DirectExchange rabbitmqDirectExchange() {
        //Direct交换机
        return new DirectExchange(RabbitMQConfig.DIRECT_EXCHANGE_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindDirect() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(rabbitmqDirectQueue())
                //到交换机
                .to(rabbitmqDirectExchange())
                //并设置匹配键
                .with(RabbitMQConfig.DIRECT_EXCHANGE_DIRECT_ROUTING);
    }

}
