/**
 * @author:
 * @Description:
 * @Data: 2018/11/5 10:23
 **/
package com;

import org.apache.commons.lang3.StringUtils;

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

    public static void main(String[] args) {

        // Message message = new Message();
        //
        // System.out.println(message.test1());
        // System.out.println(ITest.test2());

        String str = "1234567890";

        String substring = StringUtils.substring(str, 5, 7);
        System.out.println(substring);
    }
}

