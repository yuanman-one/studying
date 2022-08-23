package com.example.springbootdemo.rabbitmq.config;

/**
 * @author yuanman
 */
public class RabbitMQConfig {
    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的队列 A 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_A = "fanout.A";

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的队列 B 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_B = "fanout.B";

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的名称
     */
    public static final String FANOUT_EXCHANGE_DEMO_NAME = "fanout.exchange.demo.name";

    /**
     * RabbitMQ的DIRECT_EXCHANGE交换机类型的队列名称
     */
    public static final String DIRECT_EXCHANGE_TOPIC = "direct.name";
    /**
     * RabbitMQ的DIRECT_EXCHANGE交换机类型绑定队列匹配键
     */
    public static final String DIRECT_EXCHANGE_DIRECT_ROUTING = "direct.name.key";
    /**
     * RabbitMQ的DIRECT_EXCHANGE交换机类型的名称
     */
    public static final String DIRECT_EXCHANGE_DIRECT_EXCHANGE = "direct";
    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机类型的名称
     */
    public static final String TOPIC_EXCHANGE_DIRECT_EXCHANGE = "topic";
    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机类型的队列 A 的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_A = "topic.queue.a";
    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机类型的队列 B 的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_B = "topic.queue.b";
    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机类型的队列 C 的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_C = "topic.queue.c";
    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机类型绑定队列匹配键
     */
    public static final String TOPIC_EXCHANGE_TOPIC_ROUTING_A = "topic.*";
    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机类型绑定队列匹配键
     */
    public static final String TOPIC_EXCHANGE_TOPIC_ROUTING_B = "topic.#";
    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机类型绑定队列匹配键
     */
    public static final String TOPIC_EXCHANGE_TOPIC_ROUTING_C = "rabbit.*";
}
