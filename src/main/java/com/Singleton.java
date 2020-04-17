package com;

/**
 * 双重锁的单例模式
 * 从名字上来说，饿汉和懒汉，
 * 　　饿汉就是类一旦加载，就把单例初始化完成，保证getInstance的时候，单例是已经存在的了，
 * 　　而懒汉比较懒，只有当调用getInstance的时候，才回去初始化这个单例。
 * 　　另外从以下两点再区分以下这两种方式：
 * 　　1、线程安全：
 * 　　饿汉式天生就是线程安全的，可以直接用于多线程而不会出现问题，
 * 　　懒汉式本身是非线程安全的，为了实现线程安全有几种写法，分别是上面的1、2、3，这三种实现在资源加载和性能方面有些区别。
 * 　　2、资源加载和性能：
 * 　　饿汉式在类创建的同时就实例化一个静态对象出来，不管之后会不会使用这个单例，都会占据一定的内存，但是相应的，在第一次调用时速度也会更快，因为其资源已经初始化完成，
 * 　　而懒汉式顾名思义，会延迟加载，在第一次使用该单例的时候才会实例化对象出来，第一次调用时要做初始化，如果要做的工作比较多，性能上会有些延迟，之后就和饿汉式一样了。
 */
public class Singleton {
    // region 饿汉式 在类初始化时，已经自行实例化

    private Singleton() {
    }

    private static final Singleton single = new Singleton();

    public static Singleton getInstance() {
        return single;
    }

    // endregion

    // region 懒汉式

    // /**
    //  * 使用 volatile 关键词 保证原子的 可见性，有序性，防止指令重排
    //  * 这里的 volatile 关键字主要是为了防止指令重排。
    //  *
    //  * 如果不用 ，singleton = new Singleton();，这段代码其实是分为三步：
    //  *
    //  * 分配内存空间。(1)
    //  * 初始化对象。(2)
    //  * 将 singleton 对象指向分配的内存地址。(3)
    //  *
    //  * 加上 volatile 是为了让以上的三步操作顺序执行，
    //  * 反之有可能第三步在第二步之前被执行就有可能某个线程拿到的单例对象是还没有初始化的，以致于报错。
    //  */
    // private static volatile Singleton singleton;
    //
    // private Singleton()
    // {
    // }
    //
    // /**
    //  * Gets instance.
    //  *
    //  * @return the instance
    //  */
    // public static Singleton getInstance()
    // {
    //     if (singleton == null)
    //     {
    //         synchronized (Singleton.class)
    //         {
    //             if (singleton == null)
    //             {
    //                 //防止指令重排
    //                 singleton = new Singleton();
    //             }
    //         }
    //     }
    //
    //     return singleton;
    // }

    // endregion

}
