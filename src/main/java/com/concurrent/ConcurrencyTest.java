package com.concurrent;

/**
 * 串行和并发测试
 * AMD R7 4800U 8核16线程 16G
 * @author liushun
 * @date 2020/11/17
 */
public class ConcurrencyTest {
    private static final long count = 1000000000;

    public static void main(String[] args) throws InterruptedException {
        /**
         * 次数                  并行,串行
         * count = 10000        1ms, 0ms
         * count = 100000       3ms, 2ms
         * count = 1000000      3ms, 4ms
         * count = 10000000     7ms, 10ms
         * count = 100000000    55ms,51ms
         * count = 1000000000   507ms,512ms
         */
        concurrency();
        serial();
    }

    /**
     * 并发执行并累加操作
     * @throws InterruptedException
     */
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for(long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });

        thread.start();

        int b = 0;
        for(long i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    /**
     * 串行执行并累加操作
     */
    private static void serial() {
        long start = System.currentTimeMillis();

        int a = 0;
        for(long i = 0; i < count; i++) {
            a += 5;
        }

        int b = 0;
        for(long i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }
}
