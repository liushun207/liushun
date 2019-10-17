package com;

import java.util.ArrayList;
import java.util.List;

/**
 * 特殊例子. 3个元素能正常运行，多一个会fail-fast
 * 由于遍历集合时维护了一个初始值为0的游标，执行删除操作，所有元素往前拷贝，
 * size=size-1即为2 这时游标也为2，在执行hasNext()时 结果为false，退出了循环体
 * 并没有机会执行到next()的第一行代码checkForComodification()
 * @author liushun
 */
public class ArrayListFailFast {
    public static void main(String[] args) {
        List<String> list= new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        for(String item : list) {
            if("2".equals(item)){
                list.remove(item);
            }
        }

        System.out.println(list);
    }
}
