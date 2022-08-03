package com.example.springbootdemo.rabbitmq;

import com.example.springbootdemo.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * rabbitmq消费者
 * <p>1.@RabbitListener 注解是指定某方法作为消息消费的方法，例如监听某 Queue 里面的消息。</p>
 * <p>2.@RabbitListener标注在方法上，直接监听指定的队列，此时接收的参数需要与发送时类型一致</p>
 * <p>3.@RabbitListener 可以标注在类上面，需配合 @RabbitHandler 注解一起使用</p>
 * <p>@RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，根据接受的参数类型进入具体的方法中。</p>
 * <p>4.使用下列方式可以在没有队列的时候消费者会创建队列，否则如果没有队列时启动报错</p>
 * <p>@RabbitListener(queuesToDeclare =@Queue(RabbitMQConfig.DIRECT_EXCHANGE_TOPIC))</p>
 *
 * @author yuanman
 */
//@RabbitListener(queues ={RabbitMQConfig.DIRECT_EXCHANGE_TOPIC})
//@RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.DIRECT_EXCHANGE_TOPIC))
@Component
@Slf4j
public class RabbitMQConsumer {

    /**
     * 是
     * @param map
     */
    @RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.DIRECT_EXCHANGE_TOPIC))
    public void getMall(Map map) {
        log.info("接收到Mall信息：{}", map.toString());
    }
    //    @RabbitHandler
    //    public void getMall(Map map) {
    //        log.info("接收到Mall信息：{}", map.toString());
    //    }
}
