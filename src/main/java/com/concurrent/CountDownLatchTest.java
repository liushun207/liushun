package com.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 信号量同步，时间维度`CountDownLatch`测试.
 * @author liushun
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws Exception {
        CountDownLatch count = new CountDownLatch(3);

        Thread thread1 = new TranslateThread("1st content", count);
        Thread thread2 = new TranslateThread("2nd content", count);
        Thread thread3 = new TranslateThread("3rd content", count);

        // CustomExceptionHandler handler = new CustomExceptionHandler();
        // thread1.setUncaughtExceptionHandler(handler);
        thread1.start();
        thread2.start();
        thread3.start();

        count.await(10, TimeUnit.SECONDS);
        System.out.println("所有线程执行完成");
    }
}

class TranslateThread extends Thread {
    private String content;
    private final CountDownLatch count;

    public TranslateThread(String content, CountDownLatch count) {
        this.content = content;
        this.count = count;
    }

    @Override
    public void run() {
        // 在某种情况下，执行翻译解析式，抛出异常，此处模拟异常
        // 异常没有被主线程捕获到，最终此线程没有执行 count.countDown();
        // 出现异常程序执行时间会变长，问题难以定位，因为异常被吞
        // 子线程刻意通过设置线程方法setUncaughtExceptionHandler捕获异常
        if(Math.random() > 0.5) {
            throw new RuntimeException("原文存在非法字符");
        }

        System.out.println(content + " 的翻译已完成，译文。。。");
        count.countDown();
    }
}

class CustomExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("捕获异常");
    }
}