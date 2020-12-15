package com.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * JedisClient
 * @author liushun
 * @date 2020/12/10
 */
@Component
public class JedisClient {
    // region 私有

    private static final Logger logger = LoggerFactory.getLogger(JedisClient.class);

    /**
     * jedis集群客户端
     */
    private static JedisCluster jedisCluster;

    /**
     * 设置默认值
     */
    private static final SetParams setParams = new SetParams().nx().px(Constant.REDIS_LOCK_EXPIRETIME);

    // endregion

    // region 注入

    @Autowired
    public void setJedisCluster(@Qualifier("jedisCluster") JedisCluster jedisCluster) {
        if(jedisCluster != null) {
            JedisClient.jedisCluster = jedisCluster;
        }
    }

    // endregion

    // region 公共方法

    /**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否获取成功 true 成功
     */
    public static boolean tryGetLock(String lockKey, String requestId) {
        if(checkJedisCluster()) {
            return false;
        }

        try {
            String result =JedisClient.jedisCluster.set(lockKey, requestId, setParams);
            return Constant.REDIS_LOCK_SUCCESS.equals(result);
        } catch(Exception e) {
            logger.error("redis加锁失败：" + e.getMessage(), e);

            return false;
        }
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功 true 失败
     */
    public static boolean releaseDistributedLock(String lockKey, String requestId) {
        if(checkJedisCluster()) {
            return false;
        }

        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = JedisClient.jedisCluster.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

            return Constant.REDIS_LOCK_RELEASE_SUCCESS.equals(result);
        } catch(Exception e) {
            logger.error("redis解锁异常：" + e.getMessage(), e);

            return false;
        }
    }

    /**
     * 设置值
     * @param key 键
     * @param value 值
     * @param expireTime 过期时间, 单位：秒
     * @return true 成功
     */
    public static boolean set(String key, String value, int expireTime) {
        if(checkJedisCluster()) {
            return false;
        }

        String result = JedisClient.jedisCluster.setex(key, expireTime, value);
        return Constant.REDIS_LOCK_SUCCESS.equals(result);
    }

    /**
     * 查询缓存
     * @param key 键
     * @return Object
     */
    public static String get(String key) {
        if(checkJedisCluster()) {
            return Constant.EMPTY_STRING;
        }

        return JedisClient.jedisCluster.get(key);
    }

    // endregion

    // region 私有方法

    /**
     * 检测redis客户端
     * @return true 未实例化
     */
    private static boolean checkJedisCluster() {
        if(JedisClient.jedisCluster == null) {
            logger.error("redis客户端未实例化！");

            return true;
        }

        return false;
    }

    // endregion
}
