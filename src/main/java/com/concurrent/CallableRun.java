package com.concurrent;

import com.util.DateUtil;
import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ScheduledExecutorService使用Callable延迟运行
 */
public class CallableRun
{
    private static volatile boolean flag = false;

    public static void main(String[] args)
    {
        try
        {
            List<Callable> callableList = new ArrayList<>();
            callableList.add(new MyCallableA());
            callableList.add(new MyCallableB());

            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            ScheduledFuture futureA = executorService.schedule(callableList.get(0), 4L, TimeUnit.SECONDS);
            ScheduledFuture futureB = executorService.schedule(callableList.get(1), 4L, TimeUnit.SECONDS);

            System.out.println("            X = " + DateUtil.getTodayByFormat());
            System.out.println("返回值A：" + futureA.get());
            System.out.println("返回值B：" + futureB.get());
            System.out.println("            Y = " + DateUtil.getTodayByFormat());

            executorService.shutdown();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        catch(ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    static class MyCallableA implements Callable<String>
    {
        @Override
        public String call() throws Exception
        {
            try
            {
                System.out.println("callA begin " + Thread.currentThread().getName() + ", " + DateUtil.getTodayByFormat());

                flag = true;

                // 休眠3秒
                TimeUnit.SECONDS.sleep(3);

                System.out.println("callA end " + Thread.currentThread().getName() + ", " + DateUtil.getTodayByFormat());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return "returnA";
        }
    }

    static class MyCallableB implements Callable<String>
    {
        @Override
        public String call() throws Exception
        {
            System.out.println("callB begin " + Thread.currentThread().getName() + ", " + DateUtil.getTodayByFormat());
            System.out.println(flag);
            System.out.println("callB end " + Thread.currentThread().getName() + ", " + DateUtil.getTodayByFormat());
            return "returnB";
        }
    }
}
