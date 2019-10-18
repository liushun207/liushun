package com.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试.
 * @author liushun
 */
public class UserThreadPool {
    public static void main(String[] args) {
        // 缓存队列设置固定长度为2，为了快速触发rejectHandler
        BlockingQueue queue = new LinkedBlockingQueue(2);

        // 假设外部任务线程的来源由机房1和机房2混合调用
        UserThreadFactory f1 = new UserThreadFactory("第一机房");
        UserThreadFactory f2 = new UserThreadFactory("第二机房");

        UserRejetHandler handler = new UserRejetHandler();

        // 核心线程1，最大线程2，为了保证触发rejectHandler
        ThreadPoolExecutor threadPoolFirst = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, queue, f1, handler);

        // 利用第二个线程工厂创建第二个线程池
        ThreadPoolExecutor threadPoolSecond = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, queue, f2, handler);

        // 创建400个线程
        Runnable task = new Task();
        for(int i = 0; i < 200; i++) {
            threadPoolFirst.execute(task);
            threadPoolSecond.execute(task);
        }
    }
}
