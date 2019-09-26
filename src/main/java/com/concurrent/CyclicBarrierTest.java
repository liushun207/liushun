package com.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 并发工具 测试.
 */
public class CyclicBarrierTest {

    /**
     * 首先初始化线程参与者。
     * 调用 await() 将会在所有参与者线程都调用之前等待。
     * 直到所有参与者都调用了 await() 后，所有线程从 await() 返回继续后续逻辑。
     * 可以看出由于其中一个线程休眠了五秒，所有其余所有的线程都得等待这个线程调用 await() 。
     * @throws Exception
     */
    private static void cyclicBarrier() throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3) ;

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1-thread run -" + LocalDateTime.now());
                try {
                    cyclicBarrier.await() ;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("1-thread end do something - " + LocalDateTime.now());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2-thread run - " + LocalDateTime.now());
                try {
                    cyclicBarrier.await() ;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("2-thread end do something - " + LocalDateTime.now());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("3-thread run - " + LocalDateTime.now());
                try {
                    Thread.sleep(5000);
                    cyclicBarrier.await() ;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("3-thread end do something - " + LocalDateTime.now());
            }
        }).start();

        System.out.println("main thread  - " + LocalDateTime.now());
    }

    public static void main(String[] args) throws Exception {
        cyclicBarrier();
    }
}
