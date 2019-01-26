package com.concurrent;

import com.util.DateUtil;
import com.util.ScheduleManager;
import com.util.ThreadPoolService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 周期性执行.
 */
public class ScheduledExecutorServiceTest
{
    public static void main(String[] args) throws IOException
    {
        // region 测试一

        // ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        //
        // // 立刻执行，而且每隔1000毫秒执行一次。
        // executorService.scheduleAtFixedRate(new Runnable()
        // {
        //     @Override
        //     public void run()
        //     {
        //         System.out.println("run " + System.currentTimeMillis());
        //     }
        // }, 0, 1000, TimeUnit.MILLISECONDS);

        // endregion

        // region 测试二

        // ScheduleAtFixedRate 每次执行时间为上一次任务开始起向后推一个时间间隔，即每次执行时间为 :initialDelay, initialDelay+period, initialDelay+2*period, …；
        // ScheduleWithFixedDelay 每次执行时间为上一次任务结束起向后推一个时间间隔，即每次执行时间为：initialDelay, initialDelay+executeTime+delay, initialDelay+2*executeTime+2*delay。
        // 由此可见，
        // ScheduleAtFixedRate 是基于固定时间间隔进行任务调度，
        // ScheduleWithFixedDelay 取决于每次任务执行的时间长短，是基于不固定时间间隔进行任务调度。

        // ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        long initialDelay = 1;
        long period = 2;

        // ScheduleAtFixedRate 是基于固定时间间隔进行任务调度
        // service.scheduleAtFixedRate(new MyScheduledExecutor("job1"), initialDelay, period, TimeUnit.SECONDS);

        // ScheduleWithFixedDelay 取决于每次任务执行的时间长短，是基于不固定时间间隔进行任务调度。
        // service.scheduleWithFixedDelay(new MyScheduledExecutor("job2"), initialDelay, period, TimeUnit.SECONDS);

        // endregion

        // region 测试三

        // ScheduleManager.scheduleWithFixedDelay(new MyScheduledExecutor("job2"), initialDelay, period, TimeUnit.SECONDS);
        ScheduleManager.scheduleAtFixedRate(new MyScheduledExecutor("job2"), initialDelay, period, TimeUnit.SECONDS);
        // ScheduleManager.scheduleWithFixedDelay(new MyScheduledExecutor1(), initialDelay, period, TimeUnit.SECONDS);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        // endregion
    }

    static class MyScheduledExecutor implements Runnable
    {
        private String jobName;

        private static volatile boolean flag = true;

        MyScheduledExecutor()
        {

        }

        MyScheduledExecutor(String jobName)
        {
            this.jobName = jobName;
        }

        @Override
        public void run()
        {
            if(flag)
            {
                flag = false;

                int a = 1;
                int b = 0;

                try
                {
                    int c = a / b;
                }
                catch(Exception e)
                {
                    return;
                }
            }

            System.out.println(jobName + "-" + flag + " is running " + DateUtil.getTodayByFormat());

            // try
            // {
            //     Thread.sleep(1000 * 2);
            //     System.out.println(jobName + " is running " + DateUtil.getTodayByFormat());
            // }
            // catch(InterruptedException e)
            // {
            //     e.printStackTrace();
            // }
        }
    }

    static class MyScheduledExecutor1 implements Runnable
    {
        private String jobName;

        @Override
        public void run()
        {
            System.out.println(Thread.currentThread().getId() +  "线程 is running " + DateUtil.getTodayByFormat());

            // try
            // {
            //     Thread.sleep(1000 * 2);
            //     System.out.println(jobName + " is running " + DateUtil.getTodayByFormat());
            // }
            // catch(InterruptedException e)
            // {
            //     e.printStackTrace();
            // }
        }
    }
}
