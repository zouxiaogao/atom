package com.atom.zqy.message.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description     activemq消费者
 * @Date 2021/1/4 18:14
 * @Copyright © 2020 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */

@Slf4j
@Component
public class ActiveMQConsumer {

    //queue模式的消费者
    @JmsListener(destination="${spring.activemq.queue-name}", containerFactory="queueListener")
    public void receiveActiveQueue(String message) {
        log.info("ActiveMQ：queue模式消费消息={}",message);
    }

    //topic模式的消费者
    @JmsListener(destination="${spring.activemq.topic-name}", containerFactory="topicListener")
    public void receiveActiveTopic(String message) {
        log.info("ActiveMQ：topic模式消费消息={}",message);
    }
}
