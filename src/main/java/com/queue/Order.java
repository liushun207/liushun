package com.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * The type Order.
 * @author Administrator
 * @since JDK 1.8
 */
public class Order implements Delayed {
    /**
     * 延迟时间
     */
    private long time;

    String name;

    public Order(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    /**
     * 返回与此对象关联的剩余延迟
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Order Order = (Order) o;
        long diff = this.time - Order.time;
        if(diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
