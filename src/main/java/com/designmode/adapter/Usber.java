package com.designmode.adapter;

/**
 * Usber
 * @author liushun
 * @since JDK 1.8
 **/
public class Usber implements Usb {
    @Override
    public void isUsb() {
        System.out.println("USB口");
    }
}