package com.atom.zqy.controller;

import com.atom.zqy.common.RedisLock;
import com.atom.zqy.common.Result;
import com.atom.zqy.message.activemq.ActiveMQSender;
import com.atom.zqy.message.activemq.BasicMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description
 * @Date 2021/1/27 15:53
 */
@Slf4j
@Controller
@RequestMapping("/activeMq/")
public class ActiveMQCtrl {

    @Autowired
    private RedisLock redisLock;
    @Autowired
    private ActiveMQSender activeMQSender;

    private static final String PRE_LOCK = "test:lock:";


    /**
     * 测试数据
     */
    @PostMapping("/seek")
    @ResponseBody
    public Result seek(@RequestParam int id, @RequestParam String message,@RequestParam String value,@RequestParam String remark){
        String lock = PRE_LOCK + id;
        if (!redisLock.tryLock(lock,lock,1, TimeUnit.SECONDS)){
            return  Result.defaultFailure();
        }
        try {
            if (StringUtils.isNotBlank(message)){
                log.info("开始向ActiveMQ发送消息message={}",BasicMessage.buildMqString(new Date(),message,value,remark));
                //activeMQSender.sendMessageQueue(activeMQSender.queue, BasicMessage.buildMqString(new Date(),message,value,remark));
                activeMQSender.sendMessageTopic(activeMQSender.topic,BasicMessage.buildMqString(new Date(),message,value,remark));
            }else {
                log.info("消息为空={}",message);
            }
            return Result.defaultSuccess(message);
        }catch (Exception e) {
            log.error("测试消息异常", e);
            return Result.defaultFailure();
        }
    }

}
