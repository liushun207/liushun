/**
 * @author:
 * @Description:
 * @Data: 2019/1/4 15:15
 **/
package com.classloading;

/**
 * The type Sub class.
 */
public class SubClass extends SuperClass
{
    static
    {
        System.out.println("SubClass init!");
    }
}
