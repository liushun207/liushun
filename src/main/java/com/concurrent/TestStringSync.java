package com.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试字符串锁
 * 直接锁字符串,如果是字符对象 使用 lock.intern() 获取字符串值本身;
 * @author 刘顺
 * @date 2020/9/25
 */
public class TestStringSync {
    private static Integer CNT = 0;

    public static void main(String[] args) {
        final String lock = "abcdefg";
        run(lock);
    }

    private static void run(String lock) {
        final Integer threadNum = 10;
        final CyclicBarrier cb = new CyclicBarrier(threadNum, () -> System.out.println("threadNum : " + threadNum));

        for(int i = 0; i < threadNum; i++) {
            String tmpLock = new String(lock);
            new TestThread(cb, tmpLock.toString()).start();
        }
    }

    static class TestThread extends Thread {
        private CyclicBarrier cbLock;
        private String lock;

        public TestThread(CyclicBarrier cbLock, String lock) {
            this.cbLock = cbLock;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                cbLock.await();
            } catch(InterruptedException e) {
                e.printStackTrace();
            } catch(BrokenBarrierException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            // intern() 直接获取的是字符串的值本身，而不是取的String的对象，以此保证同一个字符串拿到的是同一个String对象，自然在同一个进程中就是同一个对象锁了
            synchronized(lock.intern()) {
                CNT = CNT + 1;
                System.out.println("Value:" + CNT + "-----------" + lock.intern());
            }
        }
    }
}
