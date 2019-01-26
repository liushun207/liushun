package com.util;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程定时任务.
 */
public class ScheduleManager
{
    /**
     * 计划任务线程,需要控制定时任务的线程数
     */
    private static ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(5,
            new BasicThreadFactory.Builder().namingPattern("liushun-schedule-pool-%d").daemon(true).build());

    // region 定时任务执行

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

    // region 具体任务

    // endregion
}
