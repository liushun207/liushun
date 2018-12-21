/**
 * @author:
 * @Description:
 * @Data: 2018/12/21 15:37
 **/
package com.hotspot;

/**
 * main 方法执行后， objA 和 objB 会不会被GC呢？
 * VM Args: -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:./gclogs
 * -XX:+PrintGC 输出GC日志
 * -XX:+PrintGCDetails 输出GC的详细日志
 * -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
 * -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
 * -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
 * -Xloggc:../logs/gc.log 日志文件的输出路径
 */
public class ReferenceCountingGC
{
    public Object instance = null;

    private static final int oneMB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占点内存，以便在 GC 日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2 * oneMB];

    public static void main(String[] args)
    {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }
}
