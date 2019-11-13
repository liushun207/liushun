package com.designmode.observer;

/**
 * Test
 * @author liushun
 * @since JDK 1.8
 **/
public class Test {
    public static void main(String[] args) {
        WechatObserver server = new WechatObserver();

        // 实例化观察者
        Observer userZhang = new User("ZhangSan");
        Observer userLi = new User("LiSi");
        Observer userWang = new User("WangWu");

        // 注册观察者
        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);

        server.setInfomation("PHP是世界上最好用的语言！");

        System.out.println("----------------------------------------------");
        server.removeObserver(userZhang);
        server.setInfomation("JAVA是世界上最好用的语言！");
    }
}
