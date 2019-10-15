package com.concurrent;

/**
 * 等待通知机制(线程通信方式)
 * 两个线程通过对同一对象调用等待 wait() 和通知 notify() 方法来进行通讯。
 * 如两个线程交替打印奇偶数
 * @author liushun
 */
public class TwoThreadWaitNotify {
    private int start = 1;

    /**
     * 保证内存可见性
     * 其实用锁了之后也可以保证可见性 这里用不用 volatile 都一样
     */
    private boolean flag = false;

    /**
     * 偶数线程
     */
    private static class EvenNum implements Runnable {
        private TwoThreadWaitNotify number;

        public EvenNum(TwoThreadWaitNotify number) {
            this.number = number;
        }

        @Override
        public void run() {
            while(number.start <= 100) {
                synchronized(TwoThreadWaitNotify.class) {
                    System.out.println("偶数线程抢到锁了");
                    if(number.flag) {
                        System.out.println(Thread.currentThread().getName() + "+-+偶数" + number.start);
                        number.start++;

                        number.flag = false;
                        TwoThreadWaitNotify.class.notify();

                    } else {
                        try {
                            TwoThreadWaitNotify.class.wait();
                        }
                        catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    /**
     * 奇数线程
     */
    private static class OddNum implements Runnable {
        private TwoThreadWaitNotify number;

        public OddNum(TwoThreadWaitNotify number) {
            this.number = number;
        }

        @Override
        public void run() {
            while(number.start <= 100) {
                synchronized(TwoThreadWaitNotify.class) {
                    System.out.println("奇数线程抢到锁了");
                    if(!number.flag) {
                        System.out.println(Thread.currentThread().getName() + "+-+奇数" + number.start);
                        number.start++;

                        number.flag = true;
                        TwoThreadWaitNotify.class.notify();

                    } else {
                        try {
                            TwoThreadWaitNotify.class.wait();
                        }
                        catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TwoThreadWaitNotify twoThread = new TwoThreadWaitNotify();

        Thread even = new Thread(new EvenNum(twoThread));
        even.setName("Even");

        Thread odd = new Thread(new OddNum(twoThread));
        odd.setName("Odd");

        even.start();
        odd.start();
    }
}
