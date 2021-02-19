package com.atom.zqy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;

/**
 * @author zouqingyuan
 * @version v1.0
 * @Description
 * @Date 2020/12/31 16:48
 * @Copyright © 2020 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
@Configuration
@PropertySource("classpath:redis.properties")
public class JedisClusterConfig {
    @Value("${redis.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.timeout}")
    private Integer timeout;

    @Value("${redis.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis;


    @Value("${redis.cluster.nodes}")
    private String clusterNodes;


    @Bean
    public JedisCluster getJedisCluster(){
        String[] cNodes = clusterNodes.split(",");
        HashSet<HostAndPort> nodes = new HashSet<>();
        //分割集群节点
        for (String node : cNodes) {
            String[] hp = node.split(":");
            nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
        }
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxTotal);

        //创建集群对象
        JedisCluster jedisCluster = new JedisCluster(nodes, timeout, jedisPoolConfig);
        return jedisCluster;
    }
}
