package com.concurrent;

import java.util.concurrent.Semaphore;

/**
 * `Semaphore`信号同步类测试
 * 场景：
 * 假设某机场的海关通道有3个窗口，一批需要出关的人排成长队，每个人都是一个线程
 * 当3个窗口中的任意一个出现空闲时，工作人员指示队列中的第一个人出队到该空闲窗口检查
 * @author liushun
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        // 设定3个信号量，即3个窗口
        // 如果这里设置为1，就是典型的互斥锁
        Semaphore semaphore = new Semaphore(3);

        // 这个队伍排了5个人
        for(int i = 0; i < 5; i++) {
            new SecurityCheckThread(i, semaphore).start();
        }
    }

    private static class SecurityCheckThread extends Thread {
        private int seq;
        private Semaphore semaphore;

        public SecurityCheckThread(int seq, Semaphore semaphore) {
            this.seq = seq;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 只有调用此方法成功后，才可以往下执行
                semaphore.acquire();
                System.out.println("No." + seq + " 乘客，正在检查中。。。");

                // 假设号码是整除2的人事身份可疑的人，需要花更长的时间来安检
                if(seq % 2 == 0) {
                    Thread.sleep(1000);
                    System.out.println("No." + seq + " 乘客，身份可疑不能出国。");
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                // 完成后，释放信号量
                semaphore.release();
                System.out.println("No." + seq + " 乘客已完成服务");
            }
        }
    }
}
