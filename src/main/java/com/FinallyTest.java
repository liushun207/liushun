package com;

import java.util.HashMap;
import java.util.Map;

/**
 * try..finally 测试
 * 不会运行的情况
 * 1. finally 在代码没有进入 try语句时不会运行
 * 2. 在前面的代码中用了System.exit()退出程序, 是终止Java虚拟机JVM的，连JVM都停止了，所有都结束了，当然finally语句也不会被执行到。
 * 3. 程序所在的线程死亡了
 * 结论：
 * 1. finally 语句是在 try的return语句 执行之后，return返回之前执行
 * 2. finally 块中的return语句会覆盖try或catch中的return返回
 * 3. finally里的修改语句可能影响也可能不影响try或catch中 return已经确定的返回值
 * 其它情况请自行测试
 * 综上：不要在 finally 中写 return 或 修改返回实体
 * @author liushun
 * @since JDK 1.8
 **/
public class FinallyTest {
    public static void main(String[] args) {
        System.out.println("--------------test1----------------");
        System.out.println(test1());
        System.out.println("--------------test2----------------");
        System.out.println(test2());
        System.out.println("--------------test4----------------");
        System.out.println(test4());
        System.out.println("--------------test5----------------");
        System.out.println(test5());
        System.out.println("--------------test6----------------");
        System.out.println(test6());
    }

    /**
     * 测试1
     * 运行结果：
     * try block
     * finally block
     * b>25, b = 100
     * 100
     * @return
     */
    public static int test1() {
        int b = 20;

        try {
            System.out.println("try block");

            return b += 80;
        } catch(Exception e) {
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");

            if(b > 25) {
                System.out.println("b>25, b = " + b);
            }
        }

        return b;
    }

    /**
     * 测试2
     * 运行结果：
     * try block
     * return statement
     * finally block
     * after return
     * @return
     */
    public static String test2() {
        try {
            System.out.println("try block");

            return test3();
        } finally {
            System.out.println("finally block");
        }
    }

    private static String test3() {
        System.out.println("return statement");

        return "after return";
    }

    /**
     * 测试4 finally块中的return语句会覆盖try块中的return返回
     * 运行结果：
     * try block
     * finally block
     * b>25, b = 100
     * 200
     * @return
     */
    public static int test4() {
        int b = 20;

        try {
            System.out.println("try block");

            return b += 80;
        } catch(Exception e) {

            System.out.println("catch block");
        } finally {

            System.out.println("finally block");

            if(b > 25) {
                System.out.println("b>25, b = " + b);
            }

            return 200;
        }
    }

    /**
     * 测试5
     * 运行结果
     * try block
     * finally block
     * b>25, b = 100
     * 100
     * @return
     */
    public static int test5() {
        int b = 20;

        try {
            System.out.println("try block");

            return b += 80;
        } catch(Exception e) {

            System.out.println("catch block");
        } finally {

            System.out.println("finally block");

            if(b > 25) {
                System.out.println("b>25, b = " + b);
            }

            b = 150;
        }

        return 2000;
    }

    /**
     * 测试6
     * 运行结果
     * FINALLY
     * @return
     */
    public static Map test6() {
        Map map = new HashMap();
        map.put("KEY", "INIT");

        try {
            map.put("KEY", "TRY");
            return map;
        } catch(Exception e) {
            map.put("KEY", "CATCH");
        } finally {
            map.put("KEY", "FINALLY");
            // java中只有传值没有传址 不会生效
            map = null;
        }

        return map;
    }
}
