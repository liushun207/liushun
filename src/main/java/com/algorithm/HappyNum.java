package com.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断一个数字是否为快乐数字 19 就是快乐数字  11就不是快乐数字.
 * 规则如下：
 * 19
 * 1*1+9*9=82
 * 8*8+2*2=68
 * 6*6+8*8=100
 * 1*1+0*0+0*0=1
 * <p>
 * 11
 * 1*1+1*1=2
 * 2*2=4
 * 4*4=16
 * 1*1+6*6=37
 * 3*3+7*7=58
 * 5*5+8*8=89
 * 8*8+9*9=145
 * 1*1+4*4+5*5=42
 * 4*4+2*2=20
 * 2*2+0*0=2
 * <p>
 * 这里结果 1*1+1*1=2 和 2*2+0*0=2 重复，所以不是快乐数字
 * @author liushun
 */
public class HappyNum {
    /**
     * 判断一个数字是否为快乐数字
     * @param number
     * @return
     */
    public static boolean isHappy(int number) {
        Set<Integer> set = new HashSet<>(30);
        while(number != 1) {
            int sum = 0;

            while(number > 0) {
                // 取数字的最后一位
                int remainder = number % 10;

                // 计算当前数字的平方和
                sum += remainder * remainder;

                // 除去数字的最后一位
                number = number / 10;
            }

            // 放入set中，如果存在相同的就认为不是 happy数字
            if(set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
            }

            number = sum;
        }

        return true;
    }

    public static void main(String[] args) {
        int num = 345;
        int i = num % 10;
        int i1 = num / 10;
        int i2 = i1 / 10;
        System.out.println(i);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i2/10);
        System.out.println(isHappy(19));
    }
}
