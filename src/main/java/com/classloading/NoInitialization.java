/**
 * @author:
 * @Description:
 * @Data: 2019/1/4 15:16
 **/
package com.classloading;

import java.util.ArrayList;
import java.util.List;

public class NoInitialization
{
    public static void main(String[] args)
    {
        // 通过子类引用父类静态字段，不会导致子类初始化
        // System.out.println(SubClass.value);

        // 通过数组或集合定义来引用类，不会触发此类的初始化
        // SuperClass[] sac = new SuperClass[10];
        // List<SuperClass> list = new ArrayList<>();

        // 常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，不会触发此类的初始化
        // System.out.println(ConstClass.HELLO);
    }
}