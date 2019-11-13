package com.designmode.observer;

/**
 * 抽象被观察者接口.
 * @author liushun
 */
public interface Observerable {
    /**
     * 添加 observer.
     * @param o the o
     */
    void registerObserver(Observer o);

    /**
     * 删除 observer.
     * @param o the o
     */
    void removeObserver(Observer o);

    /**
     * 通知 observer.
     */
    void notifyObserver();
}
