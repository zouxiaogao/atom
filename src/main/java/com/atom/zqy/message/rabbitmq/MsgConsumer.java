package com.atom.zqy.message.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description     消息消费者
 * @Date 2021/2/3 17:05
 */
@Slf4j
@Component
public class MsgConsumer {
    /**
     * Direct类型监听
     * @param message
     */
    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(
                                    value = @Queue(value = RabbitConfig.DIRECT_QUEUE, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.DIRECT_EXCHANGE),
                                    key = RabbitConfig.DIRECT_ROUTING)
                    })
    @RabbitHandler
    public void processDirectMsg(Message message) {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("received Direct message : " + msg);
    }


    /**
     * Fanout类型监听
     * @param message
     */
    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitConfig.FANOUT_QUEUE_1, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.FANOUT_EXCHANGE, type = "fanout"))
                    })
    @RabbitHandler
    public void processFanout1Msg(Message message) {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("received Fanout1 message : " + msg);
    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitConfig.FANOUT_QUEUE_2, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.FANOUT_EXCHANGE, type = "fanout"))
                    })
    @RabbitHandler
    public void processFanout2Msg(Message message) {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("received Fanout2 message : " + msg);
    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(
                                    value = @Queue(value = RabbitConfig.TOPIC_QUEUE, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.TOPIC_EXCHANGE, type = "topic"),
                                    key = RabbitConfig.TOPIC_ROUTING)
                    })
    @RabbitHandler
    public void processTopicMsg(Message message){
        String msg = new String(message.getBody(),StandardCharsets.UTF_8);
        log.info("received topic message : " + msg);
    }
}
