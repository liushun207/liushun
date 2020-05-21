package com;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @since JDK 1.8
 **/
public class TestA {

    protected Map<String, Integer> map;

    public TestA(Map<String, Integer> map) {
        this.map = map;
    }

    public static void main(String[] args) {
        Bank bank = new Bank("1", "1", 1);
        TestA testA = new TestA(bank.getMap());

        System.out.println(bank.getMap());
        System.out.println("============");

        testA.map.put("5", 5);

        System.out.println(bank.getMap());
    }
}
