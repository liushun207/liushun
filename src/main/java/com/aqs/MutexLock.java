package com.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 独占锁.
 * @author liushun
 */
public class MutexLock implements Lock {
    private MutexSynchronizer synchronizer = new MutexSynchronizer();

    private static class MutexSynchronizer extends AbstractQueuedSynchronizer {

        /**
         * @param unused 这个参数是用来传同步状态的累加值的，因为我们实现的是独占锁，
         * 因此这个参数实际用不到，我们在方法里累加值恒为1
         */
        @Override
        protected boolean tryAcquire(int unused) {

            /**
             * 用CAS来更新AQS的同步状态，如果原值是0则更新为1代表已经有线程获取了独占锁
             */
            if(compareAndSetState(0, 1)) {
                //设置当前独占锁的所有者线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * @param unused 这个参数是用来传同步状态的递减值的，因为我们实现的是独占锁，
         * 因此这个参数实际用不到，我们在方法里递减值恒为1
         */
        @Override
        protected boolean tryRelease(int unused) {
            //如果当前AQS同步状态是0，说明试图在没有获得同步状态的情况下释放同步状态，直接抛异常
            if(getState() == 0) {
                throw new IllegalMonitorStateException();
            }

            //这里不需要CAS而是直接把同步状态设置为0，因为我们实现的是独占锁，正常情况下会执行释放操作的线程只有同步状态的所有者线程
            setState(0);
            setExclusiveOwnerThread(null);
            return true;
        }

        protected Condition newCondition() {
            return new ConditionObject();
        }
    }

    /**
     * 获取锁
     */
    @Override
    public void lock() {
        synchronizer.acquire(1);
    }

    /**
     * 可中断地获取锁，在线程获取锁的过程中可以响应中断
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        synchronizer.acquireInterruptibly(1);
    }

    /**
     * 尝试非阻塞获取锁，调用方法后立即返回，成功返回true，失败返回false
     * @return
     */
    @Override
    public boolean tryLock() {
        return synchronizer.tryAcquire(1);
    }

    /**
     * 在超时时间内获取锁，到达超时时间将返回false,也可以响应中断
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return synchronizer.tryAcquireNanos(1, unit.toNanos(time));
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        synchronizer.release(1);
    }

    /**
     * 获取等待组件，等待组件实现类似于Object.wait()方法的功能
     * @return
     */
    @Override
    public Condition newCondition() {
        return synchronizer.newCondition();
    }
}
