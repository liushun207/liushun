/**
 * @author:
 * @Description:
 * @Data: 2018/11/5 10:23
 **/
package com;

import com.util.ToolUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

interface ITest {
    /**
     * default 默认方法可以实现 可以不实现
     * 如果 实现类继承了2个接口 有相同的默认实现 则必须实现
     * @return
     */
    default String test1() {
        return "hello";
    }

    /**
     * 接口中静态方法 实现类不能调用
     * @return
     */
    static String test2() {
        return "test2";
    }
}

class Message implements ITest {
    @Override
    public String test1() {
        return ITest.test2();
    }
}

public class Test {

    private static LinkedHashMap<String, String> map = new LinkedHashMap<>();

    public static void main(String[] args) {
        System.out.println("测试冲突");

        List<String> orderNos = Collections.synchronizedList(new ArrayList<String>());
        // 并发操作
        IntStream.range(0,100).parallel().forEach(i->{
            orderNos.add(Thread.currentThread().getId() + "-" + LocalDateTime.now());
        });

        List<String> filterOrderNos = orderNos.stream().distinct().collect(Collectors.toList());

        System.out.println("总数："+orderNos.size());
        System.out.println("过滤重复后总数："+filterOrderNos.size());


        // long threadId = Thread.currentThread().getId();
        // System.out.println(threadId);

        // String str1 = "  ";
        // if(StringUtils.isBlank(str1)){
        //     System.out.println("有空格");
        // } else {
        //     System.out.println("没有空格");
        // }

        // region 测试


        // Message message = new Message();
        // System.out.println(message instanceof Message);
        // System.out.println(message instanceof ITest);

        // System.out.println(message.test1());
        // System.out.println(ITest.test2());
        //
        // String str = "1234567890";
        //
        // String substring = StringUtils.substring(str, 5, 7);
        // System.out.println(substring);

        // Date date = DateTimeUtil.asDate("2020-07-07 12:04:16");
        // Date date1 = DateTimeUtil.plusSecond(date, 20);
        // System.out.println(date);
        // System.out.println(date1);
        //
        // System.out.println(DateTimeUtil.asLocalDateTime(date1));

        // map.put("1", "1");
        // map.put("2", "2");
        // map.put("3", "3");
        // map.put("5", "5");
        // map.put("4", "4");

        // System.out.println(map);
        //
        // map.clear();
        //
        // System.out.println(map.get("1"));
        //
        // map.remove("1");
        //
        // System.out.println(map);
        // map.put("4", "4");
        // System.out.println(map);

        // Set<Map.Entry<String, String>> entries = map.entrySet();
        // Map.Entry<String, String> entry = entries.stream().findFirst().get();
        // System.out.println(entry);
        //
        // Map.Entry<String, String> next = map.entrySet().iterator().next();
        // System.out.println(next);
        //
        // Map.Entry<String, String> tailByReflection = ToolUtil.getTailByReflection(map);
        // System.out.println(tailByReflection);

        // endregion

        // region 测试

        // int size = 10;
        // List<Integer> intervals = new ArrayList<>(size);
        // intervals.add(30);
        // intervals.add(60);
        // intervals.add(300);
        // intervals.add(600);
        // intervals.add(1200);
        // intervals.add(1200);
        // intervals.add(1200);
        // intervals.add(1200);
        // intervals.add(1200);
        // intervals.add(1200);
        //
        // List<List<Integer>> streamList = new ArrayList<>();
        // int a = intervals.size();
        // for (int i = 0; i < a; i += 3) {
        //     int j = Math.min((i + 3), a);
        //     List<Integer> subList = intervals.subList(i, j);
        //     streamList.add(subList);
        // }
        //
        // System.out.println(streamList);
        //
        // for(int i = 8; i > 0; i--) {
        //     if(i >= size) {
        //         System.out.println("剩余次数：" + i + "; 时间：" + intervals.get(0));
        //     } else {
        //         System.out.println("剩余次数：" + i + "; 时间：" + intervals.get(size - i));
        //     }
        // }
        //
        // Collection<Integer> notifyRecords = intervals;
        // for(Integer notifyRecord : notifyRecords) {
        //     System.out.println(notifyRecord);
        // }

        // endregion

        // String a = "pZW4apTkNMwN4O6L8EVp5eJMj+zzJyqCv7wjcR+yzZMzwSBLjwewuCvrTeXlZMJQjTMIWvft8HRe\n" + "xbC6Cs7PDXpwNSlP2xkVlD8bB78KRYB0WGbbuckbxOHoGYg4rEhKG286p6oE8oXRtvaVMaZPZFjb\n" + "0mNtUOrhTFK6me1XF4nZ/pgYW247pOVUqkjM8fQKC11TCh3m1z/2nsv/B+zBXmHtLaP3nmIMLnYU\n" + "l4qs/NgXkcJx29XjnXz5E36MOT+wuyJEySMMiK/XM5Ygtnam4Z2tt3QxdUen2w9x2di9Q7PZ0S4S\n" + "RKZLair3wAzz3lat9fBu95gh8C720e5jBTJ9VA==\n";
        // System.out.println(a.length());
        // String[] split = a.split("\n");
        // for(String s : split) {
        //     System.out.println(s);
        //     System.out.println(s.length());
        // }
        //
        // System.out.println(a.replaceAll("\n", "").length());

        // long start = System.currentTimeMillis();
        // for(int i = 0; i < 1000000; i++) {
        //     ToolUtil.getUUID();
        // }
        // System.out.println("ToolUtil.getUUID() = " + ((System.currentTimeMillis() - start)) + " millisecond");

        // System.out.println(ToolUtil.getUUID());
        // System.out.println("===================");
        // System.out.println(ToolUtil.getUUID1());

        // long start1 = System.currentTimeMillis();
        // for(int i = 0; i < 1000000; i++) {
        //     ToolUtil.getUUID1();
        // }
        // System.out.println("ToolUtil.getUUID1() = " + ((System.currentTimeMillis() - start1)) + " millisecond");

        // System.out.println(JSONObject.toJSONString(map));
    }
}

