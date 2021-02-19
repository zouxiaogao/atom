package com.atom.zqy.common;

import com.atom.zqy.config.JedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description
 * @Date 2020/12/30 16:30
 */
@Slf4j
@Component
public class JedisLock {

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String EXPIRE_TIME = "PX"; //毫秒

    @Autowired
    private JedisConfig jedisConfig;

    /**
     * 加锁
     * @param key   唯一的key
     * @param value 值
     * @param expireTime   过期时间（毫秒）
     * @return
     */
    public boolean tryLock(String key, String value, long expireTime) {
        Jedis jedis = jedisConfig.jedisPoolFactory().getResource();
        String result = jedis.set(key, value, SET_IF_NOT_EXIST, EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            log.info("申请锁(" + key + "," + value + ")成功");
            return true;
        }
        log.error("申请锁(" + key + "," + value + ")失败");
        return false;
    }

    /**
     * 释放锁
     * @param lockKey   锁名
     */
    public void unLock(String lockKey) {
        Jedis jedis = jedisConfig.jedisPoolFactory().getResource();
        Long del = jedis.del(lockKey);
        if (del > 0) {
            log.info("释放锁(" + lockKey  + ")成功");
        } else {
            log.error("释放锁(" + lockKey +")失败,该锁不存在或锁已经过期");
        }
    }
}
