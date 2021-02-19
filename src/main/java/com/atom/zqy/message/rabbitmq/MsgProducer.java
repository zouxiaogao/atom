package com.atom.zqy.message.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description 消息生产者
 * @Date 2021/2/3 17:05
 */
@Component
@Slf4j
public class MsgProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * direct
     * @param message
     */
    public void sendToDirectTestQueue(String message){
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE,
                RabbitConfig.DIRECT_ROUTING, message);
    }

    /**
     * fanout
     * @param message
     */
    public void sendToFanoutTestQueue(String message){
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE,
                "", message);
    }

    /**
     * topic
     * @param message
     */
    public void sendToTopicTestAQueue(String message){
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,
                "testTopicRouting.aaa", message);
    }

    public void sendToTopicTestBQueue(String message){
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,
                "testTopicRouting.bbb", message);
    }


}