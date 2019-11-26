/**
 * @author:
 * @Description:
 * @Data: 2018/11/25 0025 17:51
 **/
package com.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 集合转数组.
 */
public class ListToArray
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<>(3);
        list.add("one");
        list.add("two");
        list.add("three");

        // 泛型丢失，无法使用String[]接收无参数方法返回的结果
        Object[] array1 = list.toArray();
        System.out.println(Arrays.asList(array1));

        // array2 数组长度小于元素个数
        String[] array2 = new String[2];
        list.toArray(array2);
        System.out.println(Arrays.asList(array2));

        // array3 数组长度等于元素个数
        String[] array3 = new String[3];
        list.toArray(array3);
        System.out.println(Arrays.asList(array3));
    }
}
