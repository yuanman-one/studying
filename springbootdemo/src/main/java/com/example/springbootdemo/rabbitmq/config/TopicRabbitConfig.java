package com.example.springbootdemo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic Exchange
 * <p>直接翻译的话叫做主题交换机，如果从用法上面翻译可能叫通配符交换机会更加贴切。这种交换机是使用通配符去匹配，路由到对应的队列。通配符有两种："*" 、 "#"。需要注意的是通配符前面必须要加上"."符号。
 * <p>
 * * 符号：有且只匹配一个词。比如 a.*可以匹配到"a.b"、"a.c"，但是匹配不了"a.b.c"。
 * <p>
 * # 符号：匹配一个或多个词。比如"rabbit.#"既可以匹配到"rabbit.a.b"、"rabbit.a"，也可以匹配到"rabbit.a.b.c"
 * </p>
 *
 * @author yuanman
 */
@Configuration
public class TopicRabbitConfig {

    /**
     * 1、name:    队列名称
     * 2、durable: 是否持久化
     * 3、exclusive: 是否独享、排外的。如果设置为true，定义为排他队列。则只有创建者可以使用此队列。也就是private私有的。
     * 4、autoDelete: 是否自动删除。也就是临时队列。当最后一个消费者断开连接后，会自动删除。
     */
    @Bean
    public Queue topicExchangeQueueA() {
        //队列A
        return new Queue(RabbitMQConfig.TOPIC_EXCHANGE_QUEUE_A, true, false, false);
    }

    @Bean
    public Queue topicExchangeQueueB() {
        //队列B
        return new Queue(RabbitMQConfig.TOPIC_EXCHANGE_QUEUE_B, true, false, false);
    }
    @Bean
    public Queue topicExchangeQueueC() {

        //队列C
        return new Queue(RabbitMQConfig.TOPIC_EXCHANGE_QUEUE_C, true, false, false);
    }

    @Bean
    public TopicExchange rabbitmqTopicExchange() {
        //Topic交换机
        return new TopicExchange(RabbitMQConfig.TOPIC_EXCHANGE_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindTopicA() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(topicExchangeQueueA())
                //到交换机
                .to(rabbitmqTopicExchange())
                //并设置匹配键
                .with(RabbitMQConfig.TOPIC_EXCHANGE_TOPIC_ROUTING_A);
    }
    @Bean
    public Binding bindTopicB() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(topicExchangeQueueB())
                //到交换机
                .to(rabbitmqTopicExchange())
                //并设置匹配键
                .with(RabbitMQConfig.TOPIC_EXCHANGE_TOPIC_ROUTING_B);
    }
    @Bean
    public Binding bindTopicC() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(topicExchangeQueueC())
                //到交换机
                .to(rabbitmqTopicExchange())
                //并设置匹配键
                .with(RabbitMQConfig.TOPIC_EXCHANGE_TOPIC_ROUTING_C);
    }

}
