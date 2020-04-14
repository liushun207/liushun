package com.concurrent;

import jdk.internal.org.objectweb.asm.TypeReference;

/**
 * 死锁 demo.
 * 避免死锁的常见方法：
 * 避免一个线程同时获取多个锁。
 * 避免一个线程在锁内同时占用多个资源，尽量保证每个锁只占用一个资源。
 * 尝试使用定时锁，使用lock.tryLock（timeout）来替代使用内部锁机制。
 * 对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况。
 * @author liushun
 * @since JDK 1.8
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(A) {
                    try {
                        // 休眠2秒 等待 线程2执行
                        Thread.sleep(2000);
                    } catch(InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    synchronized(B) {
                        System.out.println("1");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(B) {
                    synchronized(A) {
                        System.out.println("2");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
