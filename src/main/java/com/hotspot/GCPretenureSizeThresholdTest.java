/**
 * @author:
 * @Description:
 * @Data: 2019/1/3 11:33
 **/
package com.hotspot;

/**
 * GC 内存分配测试.
 * VM Args: -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8  -XX:+PrintGCDetails -Xloggc:./gclog2
 * -XX:PretenureSizeThreshold=3145728   -XX:+UseSerialGC
 * 固定内存20m 设置新生代为10m 新生代中 eden space 8m; from space 1m; to space 1m
 * 大于 PretenureSizeThreshold 的大小(3m)的直接分配到老年代, 指定垃圾收集器
 * PretenureSizeThreshold 参数只对 Serial 和 PerNew 收集器有用
 */
public class GCPretenureSizeThresholdTest
{
    private static final int oneMB = 1024 * 1024;

    public static void main(String[] args)
    {
        byte[] allocation;

        // 直接分配在老年代
        allocation = new byte[4 * oneMB];
    }
}
