package com;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type List equals.
 * @author liushun
 */
public class ListEquals {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);

        List<Integer> list = new ArrayList<>();
        list.add(1);

        // 执行结果 true，源码中只进行了子类是`list`类型判断，对比每个列表的下标
        System.out.println(linkedList.equals(list));
    }
}
