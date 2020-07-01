package com.guava;

import com.google.common.base.Function;

import java.util.HashMap;
import java.util.Map;

/**
 * [Google Guava] 4-函数式编程
 * @author liushun
 * @since JDK 1.8
 **/
public class FunctionTest {
    private static Map<String, Function> funcMap = new HashMap<>();

    static {
        funcMap.put("car", new CarFunction());
    }

    public static void main(String[] args) {
        Function car = funcMap.get("car");
        Object jeep = car.apply("Jeep");
        System.out.println(jeep);
    }
}
