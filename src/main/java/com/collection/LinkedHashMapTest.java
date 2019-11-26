package com.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The type Linked hash map test.
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        // 1. 传人3个参数 第三个参数 `accessOrder` 为 true
        // Map<String, Integer> map = new LinkedHashMap<String, Integer>(5, 0.75f, true);
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);

        // 重新插入，当 `accessOrder` 为 true 时 2会在链尾， 否则不会修改顺序
        map.put("2", 2);

        System.out.println(map.toString());
    }
}
