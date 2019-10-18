package com.concurrent;

import javax.validation.constraints.AssertFalse;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 用户线程工厂.
 * @author liushun
 */
public class UserThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger();

    /**
     * 定义线程组名称，在使用jstack来排查线程问题时，非常有帮助
     */
    public UserThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, r, name, 0);
        System.out.println(thread.getName());
        return thread;
    }
}

class Task implements Runnable {
    private final AtomicLong count = new AtomicLong();

    @Override
    public void run() {
        System.out.println("running_" + count.getAndIncrement());
    }
}