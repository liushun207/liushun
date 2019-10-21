package com;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The type Tree map test.
 * @author liushun
 * @since JDK 1.8
 */
public class TreeMapTest {
    public static void main(String[] args) {
        SortedMap<Integer, String> map = new TreeMap<>();
        map.put(3, "3");
        map.put(1, "1");
        map.put(2, "2");
        map.put(2, "3");

        System.out.println(map);
        System.out.println(map.tailMap(3));

        SortedMap<String, String> map1 = new TreeMap<>();
        map1.put("1", "1");
        map1.put("2", "2");
        map1.put("a", "3");
        map1.put("b", "3");

        System.out.println(map1);
    }
}
