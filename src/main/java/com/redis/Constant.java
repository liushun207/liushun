package com.redis;

/**
 * Constant
 * @author liushun
 * @date 2020/12/10
 */
public class Constant {
    private Constant() {
    }

    /**
     * 空串
     */
    public static final String EMPTY_STRING = "";

    /**
     * redis锁 超时时间
     */
    public static final int REDIS_LOCK_EXPIRETIME = 15 * 1000;

    /**
     * redis 加锁成功
     */
    public static final String REDIS_LOCK_SUCCESS = "OK";

    /**
     * redis 解锁成功
     */
    public static final Long REDIS_LOCK_RELEASE_SUCCESS = 1L;
}
