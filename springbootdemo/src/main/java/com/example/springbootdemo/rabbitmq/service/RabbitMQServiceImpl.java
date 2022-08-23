package com.example.springbootdemo.rabbitmq.service;

import com.example.springbootdemo.base.util.JsonUtil;
import com.example.springbootdemo.base.util.ResponseUtil;
import com.example.springbootdemo.entity.Student;
import com.example.springbootdemo.rabbitmq.config.DeadLetterRabbitConfig;
import com.example.springbootdemo.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuanman
 */

@Service
@Slf4j
public class RabbitMQServiceImpl implements RabbitMQService {
    //日期格式化
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private RabbitTemplate rabbitTemplate;
    private AmqpTemplate amqpTemplate;

    @Override
    public ModelMap sendDirectMsg(String msg) throws Exception {
        try {
            String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
            String sendTime = sdf.format(new Date());
            Map<String, Object> map = new HashMap<>();
            map.put("msgId", msgId);
            map.put("sendTime", sendTime);
            map.put("msg", msg);
            log.info("发送DirectMsg时间：{},信息：{}", sdf.format(new Date()), map.toString());
            //发送消息
            rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE_DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_EXCHANGE_DIRECT_ROUTING, map);
            log.info("发送DirectMsg信息成功-------");
            return ResponseUtil.retCorrectInfo("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.retCorrectInfo("发送失败");
        }
    }

    @Override
    public ModelMap sendDelayMsg(String msg, String type) throws Exception {
        try {
            String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
            String sendTime = sdf.format(new Date());
            Map<String, Object> map = new HashMap<>();
            map.put("msgId", msgId);
            map.put("sendTime", sendTime);
            map.put("msg", msg);
            log.info("发送sendDelayMsg时间：{}，信息：{}", sdf.format(new Date()),map.toString());
            MessagePostProcessor postProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    //设置持久化
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    //设置过期时间毫秒
                    message.getMessageProperties().setExpiration(String.valueOf(55*1000));
                    return message;
                }
            };
            //发送消息
            if ("1".equals(type)) {
                rabbitTemplate.convertAndSend(DeadLetterRabbitConfig.DELAY_EXCHANGE_NAME
                        , DeadLetterRabbitConfig.DELAY_QUEUEA_ROUTING_KEY
                        , JsonUtil.toJsonString(map)
                        , postProcessor);
            } else {
                rabbitTemplate.convertAndSend(DeadLetterRabbitConfig.DELAY_EXCHANGE_NAME
                        , DeadLetterRabbitConfig.DELAY_QUEUEB_ROUTING_KEY
                        , JsonUtil.toJsonString(map)
                        , postProcessor);
            }
            log.info("发送sendDelayMsg信息成功-------");
            return ResponseUtil.retCorrectInfo("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.retCorrectInfo("发送失败");
        }
    }

    @Override
    public ModelMap sendFanoutMsg(String msg) throws Exception {
        try {
            String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
            String sendTime = sdf.format(new Date());
            Map<String, Object> map = new HashMap<>();
            map.put("msgId", msgId);
            map.put("sendTime", sendTime);
            map.put("msg", msg);
            Student student = new Student("满满", "24", "1");

            log.info("发送FanoutMsg时间：{},信息：{}", sdf.format(new Date()),student.toString());
            MessagePostProcessor postProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    //设置持久化
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    //设置过期时间毫秒
                    //message.getMessageProperties().setExpiration("10");
                    return message;
                }
            };
            //发送消息
            //rabbitTemplate.convertAndSend();
            rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE_DEMO_NAME,
                    null,
                    JsonUtil.toJsonString(student), postProcessor);
            log.info("发送FanoutMsg信息成功-------");
            return ResponseUtil.retCorrectInfo("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.retCorrectInfo("发送失败");
        }
    }
}
