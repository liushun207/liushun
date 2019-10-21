package com.consistenthash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ConsistentHashTest
 * @author liushun
 * @since JDK 1.8
 **/
public class ConsistentHashTest {
    public static void main(String[] args) {

       String[] groups = {
                "192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
                "192.168.0.3:111", "192.168.0.4:111"
        };

        // 生成随机数进行测试
        Map<String, Long> resMap = new HashMap<>();

        AbstractConsistentHash hash = new TreeMapConsistentHash();
        hash.add(Arrays.asList(groups));

        for (long i = 0; i < 100000; i++) {
            Long widgetId = i;
            String group = hash.getServer(widgetId.toString());
            if (resMap.containsKey(group)) {
                resMap.put(group, resMap.get(group) + 1);
            } else {
                resMap.put(group, 1L);
            }
        }

        resMap.forEach(
                (k, v) -> {
                    System.out.println("group " + k + ": " + v + "(" + (v/100000.0 * 100) +"%)");
                }
        );
    }
}
