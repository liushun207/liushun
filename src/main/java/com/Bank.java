package com;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 银行
 **/
@Data
public class Bank {
    private String name;
    private String address;
    private int age;

    private final Map<String, Integer> map = new HashMap<>();

    public Bank(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Bank bank = (Bank) o;
        return age == bank.age && Objects.equals(name, bank.name) && Objects.equals(address, bank.address);
    }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(name, address, age);
    // }

    public static void main(String[] args) {
        Bank bank1 = new Bank("1", "1", 1);
        Bank bank2 = new Bank("1", "1", 1);
        System.out.println(bank1.equals(bank2));
    }
}
