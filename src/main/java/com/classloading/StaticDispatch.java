/**
 * @author:
 * @Description:
 * @Data: 2019/1/16 17:48
 **/
package com.classloading;

/**
 * 方法静态分派演示.
 */
public class StaticDispatch
{
    static abstract class Human
    {

    }

    static class Man extends Human
    {

    }

    static class Woman extends Human
    {

    }

    public void sayHello(Human guy)
    {
        System.out.println("hello, guy!");
    }

    public void sayHello(Man guy)
    {
        System.out.println("hello, guy man!");
    }

    public void sayHello(Woman guy)
    {
        System.out.println("hello, guy woman!");
    }

    public static void main(String[] args)
    {
        // Human man = new Man();
        // 对上面解释：Human 属于静态类型  new Man() 属于实际类型

        // region 测试1, 结果都是 hello, guy!

        // Human man = new Man();
        // Human woman = new Woman();
        //
        // StaticDispatch sr = new StaticDispatch();
        // sr.sayHello(man);
        // sr.sayHello(woman);

        // endregion

        // region 测试2

        // 实际类型变化 结果都是 hello, guy!
        Human man = new Man();
        man = new Woman();

        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);

        // 静态类型变化 类型转换出错
        //sr.sayHello((Man) man);
        //sr.sayHello((Woman) man);

        // endregion

    }
}
