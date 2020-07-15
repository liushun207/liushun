/**
 * @author:
 * @Description:
 * @Data: 2018/11/5 10:23
 **/
package com;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private static Map<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        String str1 = "  ";
        if(StringUtils.isBlank(str1)){
            System.out.println("有空格");
        } else {
            System.out.println("没有空格");
        }

        // region 测试


        // Message message = new Message();
        //
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
        // map.put("4", "4");
        //
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

        // endregion

        int size = 10;
        List<Integer> intervals = new ArrayList<>(size);
        intervals.add(30);
        intervals.add(60);
        intervals.add(300);
        intervals.add(600);
        intervals.add(1200);
        intervals.add(1200);
        intervals.add(1200);
        intervals.add(1200);
        intervals.add(1200);
        intervals.add(1200);

        List<List<Integer>> streamList = new ArrayList<>();
        int a = intervals.size();
        for (int i = 0; i < a; i += 3) {
            int j = Math.min((i + 3), a);
            List<Integer> subList = intervals.subList(i, j);
            streamList.add(subList);
        }

        System.out.println(streamList);

        for(int i = 8; i > 0; i--) {
            if(i >= size) {
                System.out.println("剩余次数：" + i + "; 时间：" + intervals.get(0));
            } else {
                System.out.println("剩余次数：" + i + "; 时间：" + intervals.get(size - i));
            }
        }

        Collection<Integer> notifyRecords = intervals;
        for(Integer notifyRecord : notifyRecords) {
            System.out.println(notifyRecord);
        }
    }
}

