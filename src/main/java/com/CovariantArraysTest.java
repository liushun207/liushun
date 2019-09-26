package com;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 逆变和协变测试.
 * java 中 `通配符` ? 引入协变和逆变
 * 协变 <? extends A> B是A的子类，那么List<B>是List<? extends A>的子类
 * 逆变 <? supper A> B是Ａ的子类，那么List<B>是List<? super A>的父类
 * @author liushun
 */
public class CovariantArraysTest {
    static class Fruit {
        public String returnMeat() {
            return "generic fruit meat";
        }
    }

    static class Apple extends Fruit {
        @Override
        public String returnMeat() {
            return "apple meat";
        }
    }

    static class GreenApple extends Apple {
        @Override
        public String returnMeat() {
            return "green apple meat";
        }
    }

    /**
     * 协变约束
     * 协变方法支持对传入参数的读操作，但不支持修改操作
     */
    private static void covariance(List<? extends Apple> fruits) {
        fruits.forEach(fruit -> System.out.println("eat " + fruit.returnMeat()));

        // fruits.add(new Apple());//编译错误2
        // fruits.add(new Fruit());//编译错误3
        // fruits.add(new Object());//编译错误4
    }

    /**
     * 逆变约束
     * 逆变主要在写的场景，即只能向逆变的容器中添加，下界类型本身或其子类
     */
    private static void contravariance(List<? super Apple> fruits) {
        // fruits.add(new Fruit());//编译错误2
        fruits.add(new Apple());
        fruits.add(new GreenApple());
    }

    public static void main(String[] args) {

        List<GreenApple> greenApples = new ArrayList<>();
        List<Fruit> fruits = new ArrayList<>();
        List<Apple> apples = new ArrayList<>();

        // region 协变约束测试

        covariance(greenApples);
        // covariance(fruits);//编译错误1
        covariance(apples);

        // endregion

        // region 逆变约束测试

        contravariance(fruits);
        contravariance(apples);
        // contravariance(greenApples);//编译错误1

        // endregion
    }
}
