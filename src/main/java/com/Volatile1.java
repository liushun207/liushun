/**
 * @author:
 * @Description:
 * @Data: 2018/10/30 15:20
 **/
package com;

import java.util.Map;

/**
 * Volatile 指令重排.
 * 这里就能看出问题了，
 * 当 flag 没有被 volatile 修饰时，JVM 对 第 1 步 和 第 2 步 进行重排，
 * 导致 value 都还没有被初始化就有可能被线程 B 使用了。
 */
public class Volatile1
{
    private static Map<String, String> value;
    private static volatile boolean flag = false;

    /**
     * 以下方法发生在线程 A 中 初始化 Map
     */
    public void initMap()
    {
        //耗时操作

        //第 1 步
        value = null;

        // 第 2 步
        flag = true;
    }

    /**
     * 发生在线程 B中 等到 Map 初始化成功进行其他操作
     */
    public void doSomeThing()
    {
        while(!flag)
        {
            //sleep();
        }

        //dosomething
        //doSomeThing(value);
    }
}
