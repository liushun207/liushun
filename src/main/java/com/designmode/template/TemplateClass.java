package com.designmode.template;

/**
 * TemplateClass
 * 模版方法抽象类
 * @author liushun
 * @since JDK 1.8
 **/
public abstract class TemplateClass {
    /**
     * 模板方法，用来控制炒菜的流程 （炒菜的流程是一样的-复用）
     * 申明为final，不希望子类覆盖这个方法，防止更改流程的执行顺序
     */
    public final void cookProcess() {
        // 第一步：倒油 --- 一样的
        this.pourOil();
        // 第二步：热油 --- 一样的
        this.HeatOil();
        // 第三步：倒蔬菜 -- 不一样
        this.pourVegetable();
        // 第四步：倒调味料 -- 不一样
        this.pourSauce();
        // 第五步：翻炒 --- 一样的
        this.fry();
    }

    /**
     * 定义结构里哪些方法是所有过程都是一样的可复用的，哪些是需要子类进行实现的
     * 第一步：倒油是一样的，所以直接实现
     */
    private void pourOil() {
        System.out.println("倒油");
    }

    /**
     * 第二步：热油是一样的，所以直接实现
     */
    private void HeatOil() {
        System.out.println("热油");
    }

    /**
     * 第三步：倒蔬菜是不一样的（一个下包菜，一个是下菜心）
     * 所以声明为抽象方法，具体由子类实现
     */
    protected abstract void pourVegetable();

    /**
     * 第四步：倒调味料是不一样的（一个下辣椒，一个是下蒜蓉）
     * 所以声明为抽象方法，具体由子类实现
     */
    protected abstract void pourSauce();

    /**
     * 第五步：翻炒是一样的，所以直接实现
     */
    private void fry() {
        System.out.println("炒啊炒啊炒到熟啊");
    }

    /**
     * 行为型模式：模版方法模式
     * 核心：抽象父类定义相同的部分，实现相同的方法，子类实现不同的部分
     * 即：现在有炒菜这个公共行为，但是炒的两个菜不同，具体来说是蔬菜和佐料，不同，因此需要重写的也是这两个部分的方法
     */
    public static void main(String[] args) {
        BaoCai baoCai = new BaoCai();
        SuanRong suanRong = new SuanRong();
        baoCai.cookProcess();
        System.out.println("================== 分割线  ===============");
        suanRong.cookProcess();
    }
}
