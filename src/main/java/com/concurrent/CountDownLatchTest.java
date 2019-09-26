package com.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 并发工具 测试.
 */
public class CountDownLatchTest {

    /**
     * 初始化一个 CountDownLatch 时告诉并发的线程，然后在每个线程处理完毕之后调用 countDown() 方法。
     * 该方法会将 AQS 内置的一个 state 状态 -1 。
     * 最终在主线程调用 await() 方法，它会阻塞直到 state == 0 的时候返回。
     * @throws Exception
     */
    private static void countDownLatch() throws Exception {
        int thread = 3;
        long start = System.currentTimeMillis();
        final CountDownLatch countDown = new CountDownLatch(thread);
        for(int i = 0; i < thread; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI + "thread run");
                    try {
                        Thread.sleep(2000);
                        countDown.countDown();

                        System.out.println(finalI + "thread end");
                    }
                    catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        countDown.await();
        long stop = System.currentTimeMillis();
        System.out.println("main over total time=" + (stop - start));
    }

    public static void main(String[] args) throws Exception {
        countDownLatch();
    }
}
