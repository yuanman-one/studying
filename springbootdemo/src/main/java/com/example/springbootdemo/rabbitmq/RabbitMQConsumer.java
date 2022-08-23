package com.example.springbootdemo.rabbitmq;

import com.example.springbootdemo.rabbitmq.config.DeadLetterRabbitConfig;
import com.example.springbootdemo.rabbitmq.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 手动确认消费消息
     * 参数：该消息的index，
     * BasicAck(long deliveryTag, bool multiple);
     *
     * deliveryTag:该消息的index；
     * multiple：是否批量true：将一次性ack所有小于deliveryTag的消息；确认收到消息。
     */
    /**
     * 简单
     *
     * @param map
     */
    @RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.DIRECT_EXCHANGE_TOPIC))
    public void getDirectMsg(Map map, Channel channel, Message message) throws IOException {
        log.info("接收到DirectMsg信息时间：{}", sdf.format(new Date()));
        log.info("接收到DirectMsg信息：{}", map.toString());
        //手动确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_A))
    public void getFanoutA(String json, Channel channel, Message message) throws IOException {
        log.info("getFanoutA接收到信息时间：{}", sdf.format(new Date()));
        log.info("getFanoutA接收到信息：{}", json);
        //手动确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_B))
    public void getFanoutB(String json, Channel channel, Message message) throws IOException {
        log.info("getFanoutB接收到信息时间：{}", sdf.format(new Date()));
        log.info("getFanoutB接收到信息：{}", json);
        //手动确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queuesToDeclare = @Queue(DeadLetterRabbitConfig.DEAD_LETTER_QUEUEA_NAME))
    public void receiveA(String json, Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列A收到消息：{}", sdf.format(new Date()), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queuesToDeclare = @Queue(DeadLetterRabbitConfig.DEAD_LETTER_QUEUEB_NAME))
    public void receiveB(String json, Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列B收到消息：{}",sdf.format(new Date()), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
