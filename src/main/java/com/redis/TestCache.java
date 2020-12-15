package com.redis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 *相关缓存
 * @author liushun
 * @date 2020/12/10
 */
public class TestCache {

    // region 私有

    private static final Logger logger = LoggerFactory.getLogger(TestCache.class);

    /**
     * 缓存时间 单位：秒
     */
    private static final int EXPIRE_TIME = 20 * 60;

    /**
     * 缓存key前缀
     */
    private static final String CACHE_PREFIX = "TEST_";

    /**
     * 分布式锁 lock key
     */
    private static final String LOCK_PREFIX = CACHE_PREFIX + "LOCK";

    private TestCache() {

    }

    // endregion

    /**
     * 获取 cache
     * @param config 渠道配置参数
     * @return String
     */
    public static String getcache(RedisConfig config) throws Exception {
        // 首次 直接获取
        CacheEntity cacheEntity = getCache(config.getAppId());

        String msg = cacheEntity == null ? null : JSON.toJSONString(cacheEntity);
        logger.info("首次获取cache缓存结果：" +  msg);

        // 如果缓存存在直接获取并返回
        if(cacheEntity != null && StringUtils.isNotBlank(cacheEntity.getToken())) {
            return cacheEntity.getToken();
        }

        // 加锁，获取腾讯云的 cache 保证原子性
        cacheEntity = getCacheEntity(config);
        msg = cacheEntity == null ? null : JSON.toJSONString(cacheEntity);
        logger.info("重新获取cache缓存结果：" + msg);

        if(cacheEntity != null && StringUtils.isNotBlank(cacheEntity.getToken())) {
            return cacheEntity.getToken();
        }

        throw new Exception("获取cache失败!!!");
    }

    // region 私有方法

    /**
     * 请求接口获取
     * synchronized 保证单应用只有一个进入
     * @param config 渠道配置参数
     * @return CacheEntity
     */
    private static synchronized CacheEntity getCacheEntity(RedisConfig config) throws Exception {
        // 加锁后，重新请求一次缓存
        CacheEntity cacheEntity = getCache(config.getAppId());

        // 如果缓存存在直接获取并返回
        if(cacheEntity != null && StringUtils.isNotBlank(cacheEntity.getToken())) {
            return cacheEntity;
        }

        cacheEntity = lock(config);
        return cacheEntity;
    }

    /**
     * 分布式缓存
     * @param config 渠道配置参数
     * @return CacheEntity
     */
    private static CacheEntity lock(RedisConfig config) throws Exception {
        String uuid = UUID.randomUUID().toString();

        try {
            // 自旋
            while(true) {
                // 尝试获取锁
                boolean hasLock = JedisClient.tryGetLock(LOCK_PREFIX, uuid);
                if(hasLock) {
                    // 加分布式锁后，重新请求一次缓存
                    CacheEntity cacheEntity = getCache(config.getAppId());

                    // 如果缓存存在直接获取并返回
                    if(cacheEntity != null && StringUtils.isNotBlank(cacheEntity.getToken())) {
                        return cacheEntity;
                    }

                    // 业务

                    // 设置缓存
                    boolean b = setCache(cacheEntity);
                    if(!b) {
                        logger.error("腾讯云人脸识别-设置cache缓存失败！");
                    }

                    return cacheEntity;
                } else {
                    // 加锁失败 睡眠100ms再自旋
                    Thread.sleep(100);
                }
            }
        } catch(Exception e) {
            throw new Exception("获取cache失败: " + e.getMessage());
        } finally {
            // 关闭锁
            JedisClient.releaseDistributedLock(LOCK_PREFIX, uuid);
        }
    }

    /**
     * 获取缓存
     * @param appId 腾讯云appId
     * @return CacheEntity
     */
    private static CacheEntity getCache(String appId) {
        String cacheStr = JedisClient.get(getKey(appId));
        if(StringUtils.isBlank(cacheStr)) {
            return null;
        }

        return JSON.parseObject(cacheStr, CacheEntity.class);
    }

    /**
     * 设置缓存
     * @param cacheEntity cache
     * @return true 成功
     */
    private static boolean setCache(CacheEntity cacheEntity) {
        // 设置缓存 EXPIRE_TIME 分钟后过期
        return JedisClient.set(getKey(cacheEntity.getAppId()), JSON.toJSONString(cacheEntity), EXPIRE_TIME);
    }

    /**
     * 获取缓存 key
     * @param appId 腾讯云appId
     * @return key
     */
    private static String getKey(String appId) {
        return CACHE_PREFIX + appId;
    }

    // endregion
}
