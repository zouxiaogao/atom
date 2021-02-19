package com.atom.zqy.controller;

import com.atom.zqy.common.Result;
import com.atom.zqy.message.rabbitmq.MsgProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description
 * @Date 2021/2/3 18:12
 * @Copyright © 2020 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@RestController
@RequestMapping("/rabbitmq")
@Slf4j
public class RabbitMQCtrl {

    @Autowired
    private MsgProducer msgProducer;

    @GetMapping("/direct/send")
    public Result sendDirectMsg(String message){
        try {
            log.info("发送direct信息");
            msgProducer.sendToDirectTestQueue(message);
            return Result.defaultSuccess(null);
        }catch (Exception e){
            log.error("发送失败",e);
            return Result.defaultFailure();
        }
    }

    @GetMapping("/fanout/send")
    public Result sendFanoutMsg(String message){
        try {
            log.info("发送fanout信息");
            msgProducer.sendToFanoutTestQueue(message);
            return Result.defaultSuccess(null);
        }catch (Exception e){
            log.error("发送失败",e);
            return Result.defaultFailure();
        }
    }


    @GetMapping("/topic/send")
    public Result sendTopicMsg(String message){
        try {
            log.info("发送topic1信息");
            msgProducer.sendToTopicTestAQueue(message);
            return Result.defaultSuccess(null);
        }catch (Exception e){
            log.error("发送失败",e);
            return Result.defaultFailure();
        }
    }

    @GetMapping("/topic/send2")
    public Result sendTopicMsg2(String message){
        try {
            log.info("发送topic2信息");
            msgProducer.sendToTopicTestBQueue(message);
            return Result.defaultSuccess(null);
        }catch (Exception e){
            log.error("发送失败",e);
            return Result.defaultFailure();
        }
    }
}
