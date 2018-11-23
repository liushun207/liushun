package com;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:
 * @Description:
 * @Data: 2018/8/3 15:25
 **/
public class VolatileInc implements Runnable
{
    /**
     * 使用 volatile 修饰基本数据内存不能保证原子性
     */
    private static volatile int count = 0 ;

    /**
     * 1. synchronize 或者是锁的方式来保证原子性
     * 2. 用 Atomic 包中 AtomicInteger 来替换 int，它利用了 CAS 算法来保证了原子性。
     */
    //private static AtomicInteger count = new AtomicInteger();

    @Override
    public void run()
    {
        for (int i = 0; i < 10000; i++)
        {
            count++;
            //count.incrementAndGet() ;
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        VolatileInc volatileInc = new VolatileInc();
        Thread t1 = new Thread(volatileInc, "t1");
        Thread t2 = new Thread(volatileInc, "t2");

        t1.start();
        //1.join();

        t2.start();
        //t2.join();

        for (int i = 0; i < 10000; i++)
        {
            count++;
            //count.incrementAndGet();
        }

        System.out.println("最终Count=" + count);
    }
}
