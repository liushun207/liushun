/**
 * @author:
 * @Description:
 * @Data: 2018/12/21 11:59
 **/
package com.hotspot;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机直接内存
 * VM Args: -Xmx20m -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM
{
    private static final int oneMB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException
    {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);

        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        while(true)
        {
            unsafe.allocateMemory(oneMB);
        }
    }
}
