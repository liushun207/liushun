package com.designmode.template;

/**
 * BaoCai
 * 炒包菜
 * 继承模板抽象类，实现尚未实现的两种抽象方法
 * @author liushun
 * @since JDK 1.8
 **/
public class BaoCai extends TemplateClass {
    @Override
    protected void pourVegetable() {
        System.out.println("下锅的蔬菜是包菜");
    }

    @Override
    protected void pourSauce() {
        System.out.println("下锅的酱料是辣椒");
    }

}
