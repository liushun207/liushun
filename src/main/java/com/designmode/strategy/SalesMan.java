package com.designmode.strategy;

/**
 * SalesMan
 * 定义销售人员选择策略
 * @author liushun
 * @since JDK 1.8
 **/
public class SalesMan {
    /**
     * 持有抽象策略角色的引用
     */
    private Strategy strategy;

    /**
     * 生成销售员实例时告诉销售员什么节日（构造方法）
     * 使得让销售员根据传入的参数（节日）选择促销活动（这里使用一个简单的工厂模式）
     * @param festival
     */
    public SalesMan(String festival) {
        switch(festival) {
            //春节就使用春节促销活动
            case "A":
                strategy = new StrategyA();
                break;
            //中秋节就使用中秋节促销活动
            case "B":
                strategy = new StrategyB();
                break;
            default:
                strategy = new StrategyA();
        }
    }

    /**
     * 向客户展示促销活动
     */
    public void SalesManShow() {
        strategy.show();
    }

    /***
     * 行为型模式：策略模式
     * 定义一系列算法，将每个算法封装到具有公共接口的一系列策略类中，
     * 从而使它们可以相互替换 & 让算法可在不影响客户端的情况下发生变化
     * 优点：
     *    策略类之间可以自由切换
     *    易于扩展
     *    避免使用多重条件选择语句（if else），充分体现面向对象设计思想。
     */
    public static void main(String[] args) {
        SalesMan mSalesMan ;
        //春节来了，使用春节促销活动
        System.out.println("对于春节：");
        mSalesMan = new SalesMan("A");
        mSalesMan.SalesManShow();

        //中秋节来了，使用中秋节促销活动
        System.out.println("对于中秋节：");
        mSalesMan =  new SalesMan("B");
        mSalesMan.SalesManShow();
    }
}
