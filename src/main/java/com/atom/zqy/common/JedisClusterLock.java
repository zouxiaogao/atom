package com.atom.zqy.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.Collections;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description
 * @Date 2020/12/31 17:20
 * @Copyright © 2020 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
@Component
public class JedisClusterLock {
    @Autowired
    public JedisCluster jedisCluster;

    private static final String prefix = "jedis_lock";
    //锁状态
    private static final String LOCK_SUCCESS = "OK";

    //释放状态
    private static final Long RELEASE_SUCCESS=1L;

    //NX标志
    private static final String SET_IF_NOT_EXIST = "NX";

    //超时标志
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    public boolean lock(String key, String requestId, int tt1) {
        String result = jedisCluster.set(prefix + key, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, tt1);
        if (LOCK_SUCCESS.equals(result)) {
            log.info("申请锁(" + key + "," + requestId + ")成功");
            return true;
        }
        log.error("申请锁(" + key + "," + requestId + ")失败");
        return false;
    }

    public boolean unlock(String key, String requestId) {
        String script = "if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        Object result = jedisCluster.eval(script, Collections.singletonList(prefix + key), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            log.info("释放锁(" + key  + ")成功");
            return true;
        }
        log.error("释放锁(" + key +")失败,该锁不存在或锁已经过期");
        return false;
    }
}
