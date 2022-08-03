package com.example.springbootdemo.rabbitmq.service;

import com.example.springbootdemo.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

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
    public String sendMallMsg(String msg) throws Exception {
        try {
            String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
            String sendTime = sdf.format(new Date());
            Map<String, Object> map = new HashMap<>();
            map.put("msgId", msgId);
            map.put("sendTime", sendTime);
            map.put("msg", msg);
            log.info("发送Mall信息：{}",map.toString());
            //发送消息
            rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE_DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_EXCHANGE_DIRECT_ROUTING, map);
            log.info("发送Mall信息成功-------");
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
