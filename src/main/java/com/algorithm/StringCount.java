package com.algorithm;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

/**
 * 字符串算法
 * @author Administrator
 * @since JDK 1.8
 */
public class StringCount {
    /**
     * 在一个字符串中查找某个子字符串出现的次数
     * @param str 原始字符串
     * @param subStr 需要查询的字符串
     * @return the int
     */
    public static int countStr(String str, String subStr) {
        if(StringUtils.isEmpty(str) || StringUtils.isEmpty(subStr)) {
            return 0;
        }

        int num = 0;
        // 判断字符串是否存在字符串中
        while(str.contains(subStr)) {
            str = str.substring(str.indexOf(subStr) + subStr.length());
            num++;
        }

        return num;
    }

    /**
     * 查找字符串中出现次数最多的字符以及出现的次数 并排序
     * @param str 字符串
     * @param ignore 忽略字符串
     * @return map string
     */
    public static Map<String, Integer> getMapString(String str, String ignore) {
        if(StringUtils.isEmpty(str)) {
            return null;
        }

        boolean ignored = StringUtils.isEmpty(ignore) ? false : true;

        char[] chars = str.toCharArray();

        Map<String, Integer> map = new HashMap<>();
        for(char aChar : chars) {
            String s = String.valueOf(aChar);

            // 如果忽略，直接跳出
            if(ignored && ignore.equals(aChar)) {
                continue;
            }

            // 获取map中的值
            Integer i = map.get(s);
            int value = i == null ? 0 : i;
            map.put(s, ++value);
        }

        if(map.size() <= 0) {
            return null;
        }

        // 排序
        Map<String, Integer> result = map.entrySet().stream()
                // 升序排序
                //.sorted(comparingByValue())
                // 降序
                .sorted(Collections.reverseOrder(comparingByValue()))
                .collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue, (e1, e2) -> e2, LinkedHashMap :: new));

        return result;
    }

    /**
     * The entry point of application.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String str = "ilovejavajavacde我你javailha123java34javali3java@#@R#我FDSFAFDAjavai23o2fjai23javii2o3java";
        String sToFind = "我";
        int num = countStr(str, sToFind);
        System.out.println("共找到" + num + "个" + sToFind);

        Map<String, Integer> mapString = getMapString(str, sToFind);
        System.out.println(mapString);
    }
}
