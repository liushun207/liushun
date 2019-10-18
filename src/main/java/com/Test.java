/**
 * @author:
 * @Description:
 * @Data: 2018/11/5 10:23
 **/
package com;

interface ITest {

}

class Message implements ITest {

}

public class Test {

    public static void main(String[] args) {
        // LocalDateTime dateTime = LocalDateTime.of(2017, 10, 22, 10, 10, 10);
        // System.out.println(LocalDateTime.now().isAfter(dateTime));
        //
        // Long a = null;
        // System.out.println(a > 0);

        retry:
        for(int i = 0; i < 10; i++) {
            if(i == 5){
                // continue retry;
                break retry;
            }

            System.out.println(i);
        }

        System.out.println("结束");
    }
}

