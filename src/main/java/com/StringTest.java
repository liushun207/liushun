package com;

import java.text.MessageFormat;

/**
 * The type String test.
 */
public class StringTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String s = "Hi " + i + "; Hi to you " + i * 2;
        }
        long end = System.currentTimeMillis();
        System.out.println("Concatenation = " + ((end - start)) + " millisecond");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String s = String.format("Hi %s; Hi to you %s", i, +i * 2);
        }
        end = System.currentTimeMillis();
        System.out.println("Format = " + ((end - start)) + " millisecond");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String s = MessageFormat.format("Hi %s; Hi to you %s", i, +i * 2);
        }
        end = System.currentTimeMillis();
        System.out.println("MessageFormat = " + ((end - start)) + " millisecond");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            StringBuilder bldString = new StringBuilder("Hi ");
            bldString.append(i).append("; Hi to you ").append(i * 2).toString();
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuilder = " + ((end - start)) + " millisecond");
    }
}
