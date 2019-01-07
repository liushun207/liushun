/**
 * @author:
 * @Description:
 * @Data: 2019/1/4 16:36
 **/
package com.classloading;

/**
 * The type Static test.
 */
public class StaticTest
{
    static class Parent
    {
        public static int A = 1;

        static
        {
            A = 2;
        }
    }

    static class Sub extends Parent
    {
        public static int B = A;
    }

    public static void main(String[] args)
    {
        // 验证 父类静态块优先于子类赋值
        System.out.println(Sub.B);
    }
}
