package com.designmode.adapter;

/**
 * UsbAdapter
 * 类适配器实现思路: 适配器继承标准类，实现需要适配的接口，实现接口内方法即可
 * @author liushun
 * @since JDK 1.8
 **/
public class UsbAdapter extends Usber implements Ps2  {
    @Override
    public void isPs2() {
        super.isUsb();
    }

    /**
     * 结构型模式：适配器模式 --- 类适配器
     * Usb接口，Ps2接口，无法直接互相使用，因此通过类适配器的方式，进行适配
     *
     * 类适配器与对象适配器的使用场景一致，仅仅是实现手段稍有区别，二者主要用于如下场景：
     * （1）想要使用一个已经存在的类，但是它却不符合现有的接口规范，导致无法直接去访问，这时创建一个适配器就能间接去访问这个类中的方法
     * （2）我们有一个类，想将其设计为可重用的类（可被多处访问），我们可以创建适配器来将这个类来适配其他没有提供合适接口的类
     * @param args
     */
    public static void main(String[] args) {
        Ps2 ps2 = new UsbAdapter();
        ps2.isPs2();
    }
}
