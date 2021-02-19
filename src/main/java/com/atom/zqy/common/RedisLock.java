package com.atom.zqy.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description
 * @Date 2020/12/30 11:10
 */
@Component
@Slf4j
public class RedisLock {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 加锁
     * @param key   锁名
     * @param value  客户端id
     * @param expireTime    过期时间
     * @param timeUnit  时间级别：秒/毫秒
     * @return
     */
    public boolean tryLock(String key, String value, int expireTime, TimeUnit timeUnit) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, value, expireTime, timeUnit);
        if (flag == null || !flag) {
            log.error("申请锁(" + key + "," + value + ")失败");
            return false;
        }
        log.info("申请锁(" + key + "," + value + ")成功");
        return true;
    }

    /**
     * 释放锁
     * @param lockKey   锁名
     */
    public void unLock(String lockKey) {
        Boolean flag = stringRedisTemplate.delete(lockKey);
        if (flag == null || !flag) {
            log.info("释放锁(" + lockKey +")失败,该锁不存在或锁已经过期");
        } else {
            log.info("释放锁(" + lockKey  + ")成功");
        }
    }

}
