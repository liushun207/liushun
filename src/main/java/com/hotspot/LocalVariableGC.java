/**
 * @author:
 * @Description:
 * @Data: 2019/1/7 14:20
 **/
package com.hotspot;

/**
 * 局部变量对垃圾收集器的影响.
 * VM Args: -verbose:gc -Xms100m -Xmx100m  -XX:+PrintGCDetails -Xloggc:./gclog3
 */
public class LocalVariableGC
{
    public static void main(String[] args)
    {
        // 测试一  会发现没有回收placeholder所占用的内存
        // 因为在执行System.gc()时 placeholder还在作用域之内
        // byte[] placeholder = new byte[64 * 1024 * 1024];
        // System.gc();

        // 测试二 在执行gc时 placeholder 不可访问了，但执行后还是没有回收
        // {
        //     byte[] placeholder = new byte[64 * 1024 * 1024];
        // }
        // System.gc();

        // 测试三 被回收了
        // {
        //     byte[] placeholder = new byte[64 * 1024 * 1024];
        // }
        // int a = 0;
        // System.gc();

        // 说明 上面测试 由于 placeholder 没有任何对局部变量的读写操作 没有被其它变量使用，
        // 所以作为GC Roots一部分的局部变量表 任然保存着联系。
        // 这种关联没有被及时打断，在绝大多数情况下是没什么问题的。
        // 举例：如果一个遇到一个方法，其后面的方法执行时间较长，
        // 而前面又占用了大量的内存、实际不会使用的变量，请设置值为null

        // 测试四 会回收
        byte[] placeholder = new byte[64 * 1024 * 1024];
        placeholder = null;
        System.gc();
    }
}
