package com.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * `CyclicBarrier`信号同步类测试
 * 场景：
 * 假设在机场排队打车时：每次放3辆车进来，坐满后开走，再放一批车和人进来
 * 通过reset()来释放线程资源
 * @author liushun
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {

        // 设定3辆车
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        // 这个队伍排了3个人
        for(int i = 0; i < 3; i++) {
            new SecurityCheckThread(i, cyclicBarrier).start();
        }
    }

    private static class SecurityCheckThread extends Thread {
        private int seq;
        private CyclicBarrier cyclicBarrier;

        public SecurityCheckThread(int seq, CyclicBarrier cyclicBarrier) {
            this.seq = seq;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("No." + seq + " 乘客，开始上车。。。");

                if(seq == 2){
                    Thread.sleep(5000);
                }

                // 调用await方法的线程告诉CyclicBarrier自己已经到达同步点，然后当前线程被阻塞
                cyclicBarrier.await();

                System.out.println("No." + seq + " 乘客，上车中。。。");

                // 这里用Thread.sleep()来模拟业务处理
                // if(seq == 2){
                //     Thread.sleep(2000);
                // }

                System.out.println("No." + seq + " 乘客，完成");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
