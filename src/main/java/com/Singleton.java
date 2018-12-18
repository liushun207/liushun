package com;

/**
 * 双重锁的单例模式
 */
public class Singleton
{
    /**
     * 使用 volatile 关键词 保证原子的 可见性，有序性，防止指令重排
     * 这里的 volatile 关键字主要是为了防止指令重排。
     *
     * 如果不用 ，singleton = new Singleton();，这段代码其实是分为三步：
     *
     * 分配内存空间。(1)
     * 初始化对象。(2)
     * 将 singleton 对象指向分配的内存地址。(3)
     *
     * 加上 volatile 是为了让以上的三步操作顺序执行，
     * 反之有可能第三步在第二步之前被执行就有可能某个线程拿到的单例对象是还没有初始化的，以致于报错。
     */
    private static volatile Singleton singleton;

    private Singleton()
    {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Singleton getInstance()
    {
        if (singleton == null)
        {
            synchronized (Singleton.class)
            {
                if (singleton == null)
                {
                    //防止指令重排
                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }
}
