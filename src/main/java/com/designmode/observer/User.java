package com.designmode.observer;

/**
 * 观察者
 * @author liushun
 * @since JDK 1.8
 **/
public class User implements Observer {
    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    /**
     * 当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调.
     * @param message the message
     */
    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + " 收到推送消息： " + message);
    }
}
