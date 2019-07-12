package com;

import lombok.Data;

/**
 * 银行
 **/
@Data
public class Bank
{
    private String name;
    private String address;
    private int age;

    public Bank(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
}
