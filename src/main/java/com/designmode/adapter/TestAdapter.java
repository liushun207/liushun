package com.designmode.adapter;

/**
 * TestAdapter
 * @author liushun
 * @since JDK 1.8
 **/
public class TestAdapter extends Adapter {
    @Override
    public void a(){
        System.out.println("实现方法被调用");
    }

    /***
     * 接口适配器模式
     * 原理：通过抽象类来实现适配，用来减少不必要代码的效果 --- MouseAdapter
     *
     * 接口适配器使用场景：
     * （1）想要使用接口中的某个或某些方法，但是接口中有太多方法，
     *  我们要使用时必须实现接口并实现其中的所有方法，可以使用抽象类来实现接口，
     *  并不对方法进行实现（仅置空），然后我们再继承这个抽象类来通过重写想用的方法的方式来实现。这个抽象类就是适配器。
     *
     *  好处：不需要完全实现内部的所有方法，只需要选择有需要的去使用
     */
    public static void main(String[] args) {
        TestAdapter adapter = new TestAdapter();
        adapter.a();
        adapter.b();
        adapter.c();
    }
}
