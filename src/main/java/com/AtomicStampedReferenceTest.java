package com;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference 使用版本号 来解决AtomicInteger中的ABA问题.
 */
public class AtomicStampedReferenceTest {
    public static AtomicStampedReference<Integer> integer = new AtomicStampedReference<>(0, 0);

    public static void main(String[] args) {
        for(int i = 0; i < 1000; i++) {
            // 获取版本号
            final int timeStamp = integer.getStamp();

            new Thread() {
                @Override
                public void run() {
                    while(true) {

                        // 错误 最大128
                        // 1、使用int类型而非Integer保存当前值
                        // 2、Interger对-128~127的缓存
                        // int current = integer.getReference();

                        // 正确
                        Integer current = integer.getReference();
                        if(integer.compareAndSet(current, current + 1, timeStamp, timeStamp + 1)) {

                            System.out.println(integer.getReference());
                            break;
                        }
                    }
                }
            }.run();
        }
    }
}
