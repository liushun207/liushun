/**
 * @author:
 * @Description:
 * @Data: 2018/11/25 0025 17:48
 **/
package com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数组转集合.
 */
public class ArrayToList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        // String[] strings = new  String[3];
        //
        // strings[0] = "one";
        // strings[1] = "two";
        // strings[2] = "three";
        //
        // List<String> stringList = Arrays.asList(strings);
        //
        // // 推荐使用
        // // List<String> stringList =  new java.util.ArrayList<>(Arrays.asList(strings));
        //
        // stringList.set(0, "oneList");
        //
        // System.out.println(strings[0]);
        //
        // // 以下方法会报错， 可查看 Arrays.asList() 方法，
        // // 返回的是 Arrays 的内部类 ArrayList 而不是 java.util.ArrayList
        // stringList.add("four");
        // stringList.remove(2);
        // stringList.clear();
    }
}
