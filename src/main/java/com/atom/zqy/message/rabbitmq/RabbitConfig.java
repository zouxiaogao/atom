package com.atom.zqy.message.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description     rabbitmq配置三种类型的队列
 * @Date 2021/2/3 16:06
 */
@Configuration
@Slf4j
public class RabbitConfig {

    /**
     * direct
     */
    public static final String DIRECT_QUEUE = "test_direct_queue";
    public static final String DIRECT_EXCHANGE = "testDirectExchange";
    public static final String DIRECT_ROUTING = "testDirectRouting";

    /**
     * fanout
     */
    public static final String FANOUT_QUEUE_1 = "test_fanout_queue_1";
    public static final String FANOUT_QUEUE_2 = "test_fanout_queue_2";
    public static final String FANOUT_EXCHANGE = "testFanoutExchange";

    /**
     * topic
     */
    public static final String TOPIC_QUEUE = "test_topic_queue";
    public static final String TOPIC_EXCHANGE = "testTopicExchange";
    public static final String TOPIC_ROUTING = "testTopicRouting.#";


    /**
     * direct
     */
    //direct 队列
    @Bean
    public Queue createDirectQueue(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);
        //一般设置一下队列的持久化就好,其余两个就是默认false
        log.info("创建=[{}]队列",DIRECT_QUEUE);
        return new Queue(DIRECT_QUEUE);
    }
    //direct exchange交换机
    @Bean
    DirectExchange directExchange(){
        log.info("创建=[{}]Exchange",DIRECT_EXCHANGE);
        return new DirectExchange(DIRECT_EXCHANGE);
    }
    //绑定 ： direct routing 路由键
    @Bean
    Binding bindingDirect(){
        log.info("绑定=[{}]队列",DIRECT_QUEUE);
        return BindingBuilder.bind(createDirectQueue()).to(directExchange()).with(DIRECT_ROUTING);
    }

    /**
     * fanout
     */
    //fanout 队列1
    @Bean
    public Queue createFanoutQueue1(){
        return new Queue(FANOUT_QUEUE_1);
    }
    //fanout 队列2
    @Bean
    public Queue createFanoutQueue2(){
        return new Queue(FANOUT_QUEUE_2);
    }

    //fanout exchange交换机
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    //队列与交换机进行绑定
    @Bean
    Binding bindingFanout1() {
        return BindingBuilder.bind(createFanoutQueue1()).
                to(fanoutExchange());
    }
    //队列与交换机进行绑定
    @Bean
    Binding bindingFanout2() {
        return BindingBuilder.bind(createFanoutQueue2()).
                to(fanoutExchange());
    }

    /**
     * topic
     */
    //topic 队列1
    @Bean
    public Queue createTopicQueue(){
        return new Queue(TOPIC_QUEUE);
    }

    //topic exchange交换机
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    //绑定
    @Bean
    Binding bindingTopic(){
        return BindingBuilder.bind(createTopicQueue()).to(topicExchange()).with(TOPIC_ROUTING);
    }
}
