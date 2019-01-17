/**
 * @author:
 * @Description:
 * @Data: 2019/1/17 10:10
 **/
package com.classloading;

import java.io.Serializable;

/**
 * 重载方法匹配优先级演示.仅演示
 */
public class Overload
{
    public static void sayHello(Object arg)
    {
        System.out.println("hello object");
    }

    public static void sayHello(int arg)
    {
        System.out.println("hello int");
    }

    public static void sayHello(long arg)
    {
        System.out.println("hello long");
    }

    public static void sayHello(Character arg)
    {
        System.out.println("hello character");
    }

    public static void sayHello(char arg)
    {
        System.out.println("hello char");
    }

    public static void sayHello(char... arg)
    {
        System.out.println("hello char...");
    }

    public static void sayHello(Serializable arg)
    {
        System.out.println("hello serializable");
    }

    public static void main(String[] args)
    {
        // 1. 输出 hello char
        // 2. 注释 sayHello(char arg) 输出 hello int, 类型自动转换 a 可以代表 97
        // 3. 注释 sayHello(int arg) 输出 hello long
        // 4. 注释 sayHello(long arg) 输出 hello character
        // 5. 注释 sayHello(Character arg) 输出 hello serializable  因为 Serializable 是 Character类实现的一个接口
        // 6. 注释 sayHello(Serializable arg) 输出 hello object
        // 7. 注释 sayHello(Object arg) 输出 hello char...
        sayHello('a');
    }
}
