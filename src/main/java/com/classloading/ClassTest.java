package com.classloading;

import java.lang.reflect.Field;

/**
 * The type Class test.
 * @author liushun
 */
public class ClassTest {
    private static int[] array = new int[3];
    private static int length = array.length;

    /**
     * 任何小写的class定义的类，有一个魔法属性：class， 来获取此类的大写class类对象
     */
    private static Class<One> one = One.class;
    private static Class<Another> another = Another.class;

    public static void main(String[] args) throws Exception {
        // 通过newInstance方法创建类对象
        One oneObject = one.newInstance();
        oneObject.call();

        Another anotherObject = another.newInstance();
        anotherObject.speak();

        // 通过Class对象获取私有成员属性对象Field
        Field privateFieldInOne = one.getDeclaredField("inner");

        // 设置私有对象可以访问和修改
        privateFieldInOne.setAccessible(true);

        privateFieldInOne.set(oneObject, "world changed.");

        // 成功修改了私有属性inner变量的值
        System.out.println(oneObject.getInner());
    }

}

class One {
    private String inner = "time flies.";

    public void call() {
        System.out.println("hello world,");
    }

    public String getInner() {
        return inner;
    }
}

class Another {
    public void speak() {
        System.out.println("easy coding.");
    }
}