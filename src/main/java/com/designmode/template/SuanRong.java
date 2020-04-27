package com.designmode.template;

/**
 * SuanRong
 * @author liushun
 * @since JDK 1.8
 **/
public class SuanRong extends TemplateClass {
    @Override
    protected void pourVegetable() {
        System.out.println("下锅的蔬菜是菜心");
    }

    @Override
    protected void pourSauce() {
        System.out.println("下锅的酱料是蒜蓉");
    }
}
