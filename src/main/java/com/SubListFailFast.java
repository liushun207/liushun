/**
 * @author:
 * @Description:
 * @Data: 2018/11/26 0026 21:29
 **/
package com;

import java.util.ArrayList;
import java.util.List;

public class SubListFailFast
{
    public static void main(String[] args)
    {
        List masterList = new ArrayList();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");

        List branchList = masterList.subList(0, 3);

        System.out.println(masterList);
        System.out.println(branchList);

        // 下面三行代码直接操作父列表，删除或增加 都会导致子列表fail-fast异常
        // 如果不注释，则会导致branchList操作出现ConcurrentModificationException异常
        //masterList.remove(0);
        //masterList.add("ten");
        //masterList.clear();

        // 子列表修改导致主列表也被改动
        branchList.clear();

        System.out.println(branchList);
        System.out.println(masterList);

        branchList.add("six");
        branchList.add("seven");

        System.out.println(masterList);

        branchList.remove(0);

        System.out.println(masterList);
        System.out.println(branchList);
    }
}
