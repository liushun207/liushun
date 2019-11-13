package com;

import java.text.MessageFormat;

/**
 * The type String test.
 * @author liushun
 */
public class StringTest {

    private static String stringStatic = "old string";

    /**
     * 测试拼接字符串速度
     */
    private static void test1() {
        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            String s = "Hi " + i + "; Hi to you " + i * 2;
        }
        long end = System.currentTimeMillis();
        System.out.println("Concatenation = " + ((end - start)) + " millisecond");

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            String s = String.format("Hi %s; Hi to you %s", i, +i * 2);
        }
        end = System.currentTimeMillis();
        System.out.println("Format = " + ((end - start)) + " millisecond");

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            String s = MessageFormat.format("Hi %s; Hi to you %s", i, +i * 2);
        }
        end = System.currentTimeMillis();
        System.out.println("MessageFormat = " + ((end - start)) + " millisecond");

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            StringBuilder bldString = new StringBuilder("Hi ");
            bldString.append(i).append("; Hi to you ").append(i * 2).toString();
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuilder = " + ((end - start)) + " millisecond");
    }

    /**
     * 将参数修改为 `stringStatic` 输出会是 `old string`
     * @param str
     */
    private static void method(String str){
        stringStatic = "new string";
    }

    public static void main(String[] args) {
        // method(stringStatic);
        // System.out.println(stringStatic);
        test1();
    }
}
