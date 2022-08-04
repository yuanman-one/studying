package com.example.springbootdemo.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanman
 */
@Slf4j
@Configuration
public class RabbitCallbackConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
        //rabbitTemplate.setReturnCallback(this);
        return rabbitTemplate;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            log.error("confirm==>发送到broker.exchange失败\r\n" +
                            "correlationData={}\r\n" + "ack={}\r\n" + "cause={}",
                    correlationData, ack, cause);
        } else {
            log.info("confirm==>发送到broker.exchange成功\r\n" +
                            "correlationData={}\r\n" + "ack={}\r\n" + "cause={}",
                    correlationData, ack, cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        Message message = returnedMessage.getMessage();
        String exchange = returnedMessage.getExchange();
        int replyCode = returnedMessage.getReplyCode();
        String replyText = returnedMessage.getReplyText();
        String routingKey = returnedMessage.getRoutingKey();
        log.info("returnedMessage==> \r\n" + "message={}\r\n" + "replyCode={}\r\n" +
                        "replyText={}\r\n" + "exchange={}\r\n" + "routingKey={}",
                message, replyCode, replyText, exchange, routingKey);
    }
}
