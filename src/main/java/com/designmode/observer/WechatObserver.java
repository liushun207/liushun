package com.designmode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者，也就是微信公众号服务
 * 实现了Observerable接口，对Observerable接口的三个方法进行了具体实现
 * @author liushun
 * @since JDK 1.8
 **/
public class WechatObserver implements Observerable {

    /**
     * 观察者
     */
    private List<Observer> observers;
    private String message;

    public WechatObserver() {
        this.observers = new ArrayList<>();
    }

    /**
     * 添加 observer.
     * @param o the o
     */
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    /**
     * 删除 observer.
     * @param o the o
     */
    @Override
    public void removeObserver(Observer o) {
        if(!observers.isEmpty()) {
            observers.remove(o);
        }
    }

    /**
     * 通知 observer.
     */
    @Override
    public void notifyObserver() {
        for(int i = 0, len = observers.size(); i < len; i++) {
            Observer oserver = observers.get(i);
            oserver.update(message);
        }
    }

    /**
     * 设置信息
     * @param s
     */
    public void setInfomation(String s) {
        this.message = s;
        System.out.println("微信服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }
}
