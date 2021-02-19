package com.atom.zqy.message.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description     activemq 生产者
 * @Date 2021/1/4 18:02
 */
@Slf4j
public class ActiveMQSender {

    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;


    /**
     * 默认队列
     */
    @Autowired
    @Qualifier("queue")
    public Queue queue;

    /**
     * 默认主题
     */
    @Autowired
    @Qualifier("topic")
    public Topic topic;


    @PostConstruct
    public void init(){
        log.info("初始化ActiveMQProducer生产者...");
        //sendMessageQueue(queue,"测试MQ第一条消息");
    }

    /**
     * 发送默认消息队列
     * @param destination
     * @param message
     */
    public void sendMessageQueue(final Destination destination,final String message){
        try {
            this.jmsTemplate.convertAndSend(destination, message);
        } catch (JmsException e) {
            log.error("发送消息失败,destination={},message={}",destination,message,e);
        }

    }

    /**
     * 发送主题
     * @param destination
     * @param message
     */
    public void sendMessageTopic(final Destination destination , final String message){

        try {
            this.jmsTemplate.convertAndSend(destination, message);
        } catch (JmsException e) {
            log.error("发送消息失败,destination={},message={}",destination,message,e);
        }
    }


}
