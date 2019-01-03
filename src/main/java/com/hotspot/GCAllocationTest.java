/**
 * @author:
 * @Description:
 * @Data: 2019/1/2 17:15
 **/
package com.hotspot;

/**
 * GC 内存分配测试.
 * VM Args: -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8  -XX:+PrintGCDetails -Xloggc:./gclog1
 * 固定内存20m 设置新生代为10m 新生代中 eden space 8m; from space 1m; to space 1m
 */
public class GCAllocationTest
{
    private static final int oneMB = 1024 * 1024;

    public static void main(String[] args)
    {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2 * oneMB];
        allocation2 = new byte[2 * oneMB];
        allocation3 = new byte[2 * oneMB];

        // 出现一次 Minor GC, 这里 Eden 已被占用6m 剩余空间不足4m,
        // 因此 发生 Minor GC, GC期间发现3个2m大小的对象无法放入 Survivor(只有1m大小)，
        // 所以会提前放入老年代
        allocation4 = new byte[4 * oneMB];
    }
}
