package com.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程池服务.
 */
public class ThreadPoolService
{
    // region 线程池

    /**
     * 设置线程名称
     */
    private static final ThreadFactory THREADFACTORY = new ThreadFactoryBuilder()
            .setNameFormat("liushun-pool-%d").build();

    /**
     * 线程执行者
     * 核心线程数 50
     * 最大线程数 100
     * 缓存线程数 200
     * 线程活动保持时间 200 毫秒 (默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用)
     * 的线程池
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 100,
            200, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(200),
            THREADFACTORY, new ThreadPoolExecutor.AbortPolicy());

    /**
     * 执行.
     * @param command 命令
     */
    public static void execute(Runnable command)
    {
        executor.execute(command);
    }

    // endregion
}
