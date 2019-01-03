/**
 * @author:
 * @Description:
 * @Data: 2019/1/3 15:15
 **/
package com.hotspot;

import sun.text.resources.in.FormatData_in;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 监控测试.    jconsole.exe
 * VM Args: -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class MonitoringTest
{
    /**
     * The type Oom object.
     */
    static class OOMObject
    {
        /**
         * 占位符
         */
        public byte[] placeholder = new byte[64 * 1024];
    }

    /**
     * 装满Heap.
     * @param num the num
     */
    public static void fillHeap(int num) throws InterruptedException
    {
        List<OOMObject> oomObjectList = new ArrayList<>();

        for(int i = 0; i < num; i++)
        {
            // 稍作延时，令监视曲线变化更加明显
            Thread.sleep(50);

            oomObjectList.add(new OOMObject());
        }

        System.gc();
    }

    /**
     * 线程死循环演示
     */
    public static void createBusyThread()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(true);
            }
        }, "testBusyThread");

        thread.start();
    }

    /**
     * 线程锁等待演示
     */
    public static void createLockThread(final Object lock)
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                 synchronized(lock)
                 {
                     try
                     {
                         lock.wait();
                     }
                     catch(InterruptedException e)
                     {
                         e.printStackTrace();
                     }
                 }
            }
        }, "testLockThread");

        thread.start();
    }

    /**
     * 线程死锁等待演示
     */
    static class SynAddRunable implements Runnable
    {
        int a, b;

        public SynAddRunable(int a, int b)
        {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run()
        {
            synchronized(Integer.valueOf(a))
            {
                synchronized(Integer.valueOf(b))
                {
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        //fillHeap(1000);

        // region 演示死循环、锁等待

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // br.readLine();
        //
        // createBusyThread();
        //
        // br.readLine();
        //
        // Object obj = new Object();
        // createLockThread(obj);

        // endregion

        // region 演示死锁

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        // Integer.valueOf(a)方法基于减少对象创建次数和节省内存的考虑，
        // [-128, 127]之间的数字会被缓存，
        // 当valueOf方法传人参数在此范围时，将直接返回缓存中的对象
        // 也就是说代码调用200次Integer.valueOf()一共只返回了两个对象
        // 线程切换就会出现 线程A等着被线程B持有的Integer.valueOf(1)，线程B等着被线程A持有的Integer.valueOf(2)
        for(int i = 0; i < 100; i++)
        {
            new Thread(new SynAddRunable(1, 2)).start();
            new Thread(new SynAddRunable(2, 1)).start();
        }

        // endregion
    }
}
