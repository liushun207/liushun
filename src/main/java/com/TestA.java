package com;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @since JDK 1.8
 **/
public class TestA {

    private static Integer count = 10000000;
    private static Integer size = 10000;
    protected static List<String> list = new ArrayList<>(count);

    public static ThreadPoolTaskExecutor notifyPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 线程核心数目
        threadPoolTaskExecutor.setCorePoolSize(40);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        // 最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(80);
        // 配置队列大小
        threadPoolTaskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        // 配置线程池前缀
        threadPoolTaskExecutor.setThreadNamePrefix("paygate-notify-async-notify-");
        // 配置拒绝策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 数据初始化
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    static {
        for(int i = 0; i < count; i++) {
            int i1 = new Random().nextInt(100000000);
            list.add(String.valueOf(i1));
        }
    }

    public static void main(String[] args) {
        // Bank bank = new Bank("1", "1", 1);
        // TestA testA = new TestA(bank.getMap());
        //
        // System.out.println(bank.getMap());
        // System.out.println("============");
        //
        // testA.map.put("5", 5);
        //
        // System.out.println(bank.getMap());


        // String formatStr = "%0" + 5 + "d";
        // String sRet = String.format(formatStr, 123456);
        // System.out.printf(sRet);


        List<List<String>> streamList = new ArrayList<>();
        for(int i = 0; i < list.size(); i += size) {
            int j = Math.min((i + size), list.size());
            List<String> subList = list.subList(i, j);
            streamList.add(subList);
        }

        ThreadPoolTaskExecutor threadPoolTaskExecutor = notifyPool();

        // 监听
        Thread timer = new Thread(new TimerTask());
        timer.setName("sentinel-timer-task");
        timer.start();

        for(List<String> strings : streamList) {
            threadPoolTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    testList(strings);
                }
            });
        }

        // long start = System.currentTimeMillis();
        //
        // // streamList.stream().forEach(TestA :: testList);
        //
        // long end = System.currentTimeMillis();
        // System.out.println("stream = " + ((end - start)) + " millisecond");
        //
        // start = System.currentTimeMillis();
        //
        // // streamList.parallelStream().forEach(TestA :: testList);
        //
        // System.out.println("当前线程数量1："+ Thread.getAllStackTraces().size() + "; ----------------------");
        //
        // ThreadPoolService.execute(new Runnable() {
        //     @Override
        //     public void run() {
        //         streamList.parallelStream().forEach(TestA :: testList);
        //     }
        // });
        //
        // System.out.println("当前线程数量2："+ Thread.getAllStackTraces().size() + "; ----------------------");
        //
        // ThreadPoolService.execute(new Runnable() {
        //     @Override
        //     public void run() {
        //         streamList.parallelStream().forEach(TestA :: testList);
        //     }
        // });
        //
        // System.out.println("当前线程数量3："+ Thread.getAllStackTraces().size() + "; ----------------------");
        //
        // ThreadPoolService.execute(new Runnable() {
        //     @Override
        //     public void run() {
        //         streamList.parallelStream().forEach(TestA :: testList);
        //     }
        // });
        //
        // System.out.println("当前线程数量4："+ Thread.getAllStackTraces().size() + "; ----------------------");
        // ThreadPoolService.execute(new Runnable() {
        //     @Override
        //     public void run() {
        //         streamList.parallelStream().forEach(TestA :: testList);
        //     }
        // });
        //
        // System.out.println("当前线程数量5："+ Thread.getAllStackTraces().size() + "; ----------------------");
        //
        // end = System.currentTimeMillis();
        // System.out.println("parallelStream = " + ((end - start)) + " millisecond");
    }

    public static void test(String string) {
        char[] chars = string.toCharArray();
    }

    public static void testList(List<String> list) {
        for(String s : list) {
            char[] chars = s.toCharArray();
        }

        // try {
        //     Thread.sleep(5000);
        // } catch(InterruptedException e) {
        //     e.printStackTrace();
        // }

        String str = "当前线程： " + Thread.currentThread().getId() + "; ";
        str += "当前线程名称：" + Thread.currentThread().getName() + "; ";
        str += "当前线程数量：" + Thread.getAllStackTraces().size() + "; ";
        str += "当前时间：" + System.nanoTime();

        System.out.println(str);
    }


    static class TimerTask implements Runnable {
        @Override
        public void run() {
            while(true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch(InterruptedException e) {
                }

                System.out.println("监听-----当前线程数量：" + Thread.getAllStackTraces().size() + "; ");
            }
        }
    }
}
