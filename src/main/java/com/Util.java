package com;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 工具
 **/
public class Util {
    /**
     * 求集合最大值
     * @param <T> 实现了`Comparable`的泛型类型
     * @param collection 集合
     * @return 集合最大值
     */
    public static <T extends Comparable> T max(Collection<T> collection) {
        if(collection == null || collection.isEmpty()) {
            throw new NoSuchElementException();
        }

        // 迭代器
        Iterator<T> iterator = collection.iterator();

        // 取默认最大值
        T largest = iterator.next();

        // 循环计算最大值
        while(iterator.hasNext()) {
            T next = iterator.next();

            // 比较值大小，如果，小于0，next大，更新最大的值
            if(largest.compareTo(next) < 0) {
                largest = next;
            }
        }

        return largest;
    }
}