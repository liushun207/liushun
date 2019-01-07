/**
 * @author:
 * @Description:
 * @Data: 2019/1/4 15:22
 **/
package com.classloading;

public class ConstClass
{
    static
    {
        System.out.println("ConstClass init!");
    }

    public static final String HELLO = "hello world";
}
