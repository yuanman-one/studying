package com.example.springbootdemo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列，即使用延迟队列加普通（死信）队列实现
 * 即发送消息到延迟队列（消息有时限，过期后自动发送至死信队列）
 * 消费者只要监听消费死信队列的消息即可
 * 注意：当队列消息的 TTL 和消息 TTL 都被设置，时间短的 TTL 设置生效
 * @author yuanman
 */
@Configuration
public class DeadLetterRabbitConfig {

    public static final String DELAY_EXCHANGE_NAME = "delay.queue.demo.business.exchange";//普通的交换机
    public static final String DELAY_QUEUEA_NAME = "delay.queue.demo.business.queuea";//声明两个队列 A B
    public static final String DELAY_QUEUEB_NAME = "delay.queue.demo.business.queueb";
    public static final String DELAY_QUEUEA_ROUTING_KEY = "delay.queue.demo.business.queuea.routingkey";
    public static final String DELAY_QUEUEB_ROUTING_KEY = "delay.queue.demo.business.queueb.routingkey";
    public static final String DEAD_LETTER_EXCHANGE = "delay.queue.demo.deadletter.exchange";//Dead Letter Exchanges
    public static final String DEAD_LETTER_QUEUEA_ROUTING_KEY = "delay.queue.demo.deadletter.delay_10s.routingkey";//死信交换机
    public static final String DEAD_LETTER_QUEUEB_ROUTING_KEY = "delay.queue.demo.deadletter.delay_60s.routingkey";
    public static final String DEAD_LETTER_QUEUEA_NAME = "delay.queue.demo.deadletter.queuea";
    public static final String DEAD_LETTER_QUEUEB_NAME = "delay.queue.demo.deadletter.queueb";


    // 声明延时Exchange
    @Bean("delayExchange")
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME, true, false);
    }

    // 声明死信Exchange
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE, true, false);
    }

    // 声明延时队列A 延时10*60s
    // 并绑定到对应的死信交换机
    @Bean("delayQueueA")
    public Queue delayQueueA() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEA_ROUTING_KEY);
        // x-message-ttl  声明队列的TTL即时间
        args.put("x-message-ttl", 1000 * 60*10);
        //return QueueBuilder.durable(DELAY_QUEUEA_NAME).withArguments(args).build();
        return new Queue(DELAY_QUEUEA_NAME, true, false, false, args);
    }

    // 声明延时队列B 延时 60*30s
    // 并绑定到对应的死信交换机
    @Bean("delayQueueB")
    public Queue delayQueueB() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEB_ROUTING_KEY);
        // x-message-ttl  声明队列的TTL
        args.put("x-message-ttl", 1000*60*30);
        // return QueueBuilder.durable(DELAY_QUEUEB_NAME).withArguments(args).build();
        return new Queue(DELAY_QUEUEB_NAME, true, false, false, args);
    }

    // 声明死信队列A 用于接收延时10s处理的消息
    @Bean("deadLetterQueueA")
    public Queue deadLetterQueueA() {
        return new Queue(DEAD_LETTER_QUEUEA_NAME, true, false, false);
    }

    // 声明死信队列B 用于接收延时60s处理的消息
    @Bean("deadLetterQueueB")
    public Queue deadLetterQueueB() {
        return new Queue(DEAD_LETTER_QUEUEB_NAME, true, false, false);
    }

    // 声明延时队列A绑定关系
    @Bean
    public Binding delayBindingA() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(delayQueueA())
                //到交换机
                .to(delayExchange())
                //并设置匹配键
                .with(DELAY_QUEUEA_ROUTING_KEY);
    }
    //    @Bean
    //    public Binding delayBindingA(@Qualifier("delayQueueA") Queue queue,
    //                                 @Qualifier("delayExchange") DirectExchange exchange) {
    //        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEA_ROUTING_KEY);
    //    }

    // 声明业务队列B绑定关系
    @Bean
    public Binding delayBindingB() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(delayQueueB())
                //到交换机
                .to(delayExchange())
                //并设置匹配键
                .with(DELAY_QUEUEB_ROUTING_KEY);
    }
    //    @Bean
    //    public Binding delayBindingB(@Qualifier("delayQueueB") Queue queue,
    //                                 @Qualifier("delayExchange") DirectExchange exchange) {
    //        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEB_ROUTING_KEY);
    //    }

    // 声明死信队列A绑定关系
    @Bean
    public Binding deadLetterBindingA() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(deadLetterQueueA())
                //到交换机
                .to(deadLetterExchange())
                //并设置匹配键
                .with(DEAD_LETTER_QUEUEA_ROUTING_KEY);
    }
    //    @Bean
    //    public Binding deadLetterBindingA(@Qualifier("deadLetterQueueA") Queue queue,
    //                                      @Qualifier("deadLetterExchange") DirectExchange exchange) {
    //        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEA_ROUTING_KEY);
    //    }

    // 声明死信队列B绑定关系
    @Bean
    public Binding deadLetterBindingB() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(deadLetterQueueB())
                //到交换机
                .to(deadLetterExchange())
                //并设置匹配键
                .with(DEAD_LETTER_QUEUEB_ROUTING_KEY);
    }
    //    @Bean
    //    public Binding deadLetterBindingB(@Qualifier("deadLetterQueueB") Queue queue,
    //                                      @Qualifier("deadLetterExchange") DirectExchange exchange) {
    //        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEB_ROUTING_KEY);
    //    }

}
