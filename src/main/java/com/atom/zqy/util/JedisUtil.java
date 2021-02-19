package com.atom.zqy.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description
 * @Date 2020/12/31 12:08
 */
@Slf4j
public class JedisUtil {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 向Redis中存值，永久有效
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error("redis存值异常",e);
            return "0";
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 向Redis中取值
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            log.error("redis取值异常",e);
            return "0";
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 是否存在key
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            log.error("redis存值异常",e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 释放连接
     */
    private  void releaseResource(Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            jedis.close();
        }
    }
}
