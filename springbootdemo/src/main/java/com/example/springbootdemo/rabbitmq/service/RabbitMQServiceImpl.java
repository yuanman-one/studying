package com.example.springbootdemo.rabbitmq.service;

import com.example.springbootdemo.base.util.JsonUtil;
import com.example.springbootdemo.base.util.ResponseUtil;
import com.example.springbootdemo.entity.Student;
import com.example.springbootdemo.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
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

    @Override
    public ModelMap sendDirectMsg(String msg) throws Exception {
        try {
            String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
            String sendTime = sdf.format(new Date());
            Map<String, Object> map = new HashMap<>();
            map.put("msgId", msgId);
            map.put("sendTime", sendTime);
            map.put("msg", msg);
            log.info("发送DirectMsg时间：{}", sdf.format(new Date()));
            log.info("发送DirectMsg信息：{}", map.toString());
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
    public ModelMap sendFanoutMsg(String msg) throws Exception {
        try {
            String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
            String sendTime = sdf.format(new Date());
            Map<String, Object> map = new HashMap<>();
            map.put("msgId", msgId);
            map.put("sendTime", sendTime);
            map.put("msg", msg);
            Student student = new Student("满满", "24", "1");

            log.info("发送FanoutMsg时间：{}", sdf.format(new Date()));
            log.info("发送FanoutMsg信息：{}", student.toString());
            MessagePostProcessor postProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                }
            };
            //发送消息
            //rabbitTemplate.convertAndSend();
            rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE_DIRECT_EXCHANGE,
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
