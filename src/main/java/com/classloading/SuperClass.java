/**
 * @author:
 * @Description:
 * @Data: 2019/1/4 15:12
 **/
package com.classloading;

/**
 * The type Super class.
 */
public class SuperClass
{
    static
    {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}

