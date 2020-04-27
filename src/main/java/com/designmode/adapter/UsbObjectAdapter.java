package com.designmode.adapter;

/**
 * UsbObjectAdapter
 * 对象适配器实现思路: 适配器实现被适配的接口，通过构造方法获取到标准对象，再把需要被适配的方法重写，替换成标准方法
 * @author liushun
 * @since JDK 1.8
 **/
public class UsbObjectAdapter implements Ps2 {
    private Usber usb;
    public UsbObjectAdapter(Usber usber){
        this.usb = usber;
    }

    @Override
    public void isPs2() {
        usb.isUsb();
    }

    public static void main(String[] args) {
        Usber usber = new Usber();
        Ps2 ps2 = new UsbObjectAdapter(usber);
        ps2.isPs2();
    }
}
