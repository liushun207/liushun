package com.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

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

    // region 定时任务

    /**
     * 计划任务线程,需要控制定时任务的线程数
     */
    private static ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(5,
            new BasicThreadFactory.Builder().namingPattern("liushun-schedule-pool-%d").daemon(true).build());

    /**
     * 基于固定时间间隔进行任务调度.
     * @param command 要执行的任务, 注意捕捉异常
     * @param initialDelay 初始延迟
     * @param period 周期
     * @param unit 周期时间单位
     * @return the scheduled future
     */
    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                                  long initialDelay,
                                                  long period,
                                                  TimeUnit unit)
    {
        return schedule.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    /**
     * 取决于每次任务执行的时间长短，是基于不固定时间间隔进行任务调度.
     * 以上一次任务的结束时间计算下一次任务的开始时间
     * @param command 要执行的任务, 注意捕捉异常
     * @param initialDelay 初始延迟
     * @param delay 延迟时间（上次任务完成结束后的时间 再延迟 delay 执行）
     * @param unit 周期时间单位
     * @return the scheduled future
     */
    public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
                                                     long initialDelay,
                                                     long delay,
                                                     TimeUnit unit)
    {
        return schedule.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }

    // endregion
}
