package com.atom.zqy.config;

import com.atom.zqy.message.activemq.ActiveMQSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description
 * @Date 2021/1/4 17:16
 * @Copyright © 2020 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
@Configuration
@PropertySource("classpath:activemq.properties")
@EnableJms
public class ActiveMqConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String username;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.activemq.queue-name}")
    private String queueName;

    @Value("${spring.activemq.topic-name}")
    private String topicName;


    /**
     * 默认队列
     */
    @Bean(name = "queue")
    public Queue queue() {
        log.info("创建默认队列....");
        return new ActiveMQQueue(queueName);
    }

    /**
     * 默认主题
     */
    @Bean(name = "topic")
    public Topic topic() {
        log.info("创建主题队列....");
        return new ActiveMQTopic(topicName);
    }

    @Bean
    public ActiveMQSender activeMQSender(){
        return new ActiveMQSender();
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(username, password, brokerUrl);
    }

    @Bean
    public JmsMessagingTemplate jmsMessageTemplate() {
        return new JmsMessagingTemplate(connectionFactory());
    }

    // 在Queue模式中，对消息的监听需要对containerFactory进行配置
    @Bean("queueListener")
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        log.info("队列 模式开始启动...");
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        log.info("队列 模式启动成功...");
        return factory;
    }

    //在Topic模式中，对消息的监听需要对containerFactory进行配置
    @Bean("topicListener")
    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        log.info("主题/订阅 模式开始启动...");
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        log.info("主题/订阅 模式启动成功...");
        return factory;
    }
}
