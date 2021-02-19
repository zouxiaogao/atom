package com.atom.zqy.controller;

import com.atom.zqy.common.JedisClusterLock;
import com.atom.zqy.common.JedisLock;
import com.atom.zqy.common.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description
 * @Date 2020/12/30 14:05
 * @Copyright © 2020 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
@RestController
public class RedisDataCtrl {

    @Autowired
    private RedisLock redisLock;
    @Autowired
    private JedisLock jedisLock;
    @Autowired
    private JedisClusterLock jedisClusterLock;

    /**
     * Redis 测试
     * 拼接的lockKey是保证当前的用户只能在规定时间内进行一次完成的操作，防止接口被怼,并且保证了用户只释放自己的锁
     * @param userId
     */
    @GetMapping("/redis/test")
    public void redisOperate(String userId){
        String lockKey = "redis-lock" + userId;

        boolean lock = redisLock.tryLock(lockKey, userId, 15, TimeUnit.SECONDS);
        if (lock){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                redisLock.unLock(lockKey);
            }
        }
    }

    /**
     * Jedis 测试
     * 拼接的lockKey是保证当前的用户只能在规定时间内进行一次完成的操作，防止接口被怼,并且保证了用户只释放自己的锁
     * @param userId
     */
    @GetMapping("/jedis/test")
    public void jedisOperate(String userId){
        String lockKey = "jedis-lock" + userId;

        boolean lock = jedisLock.tryLock(lockKey, userId, 5 * 1000);
        if (lock){
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                jedisLock.unLock(lockKey);
            }
        }
    }


    /**
     * Jedis集群 测试
     * 拼接的lockKey是保证当前的用户只能在规定时间内进行一次完成的操作，防止接口被怼,并且保证了用户只释放自己的锁
     * @param userId
     * TODO 未测试
     */
    @GetMapping("/jediscluster/test")
    public void jedisClusterOperate(String userId){
        String lockKey =userId;
        boolean lock = jedisClusterLock.lock(lockKey, userId, 5 * 1000);
        if (lock){
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                jedisClusterLock.unlock(lockKey,userId);
            }
        }
    }
}
