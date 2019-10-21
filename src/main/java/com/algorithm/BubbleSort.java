package com.algorithm;

import java.sql.SQLOutput;

/**
 * 冒泡排序 稳定 时间复杂度是O(N^2)
 * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。
 * 如果不满足就让它俩互换。一次冒泡会让至少一个元素移动到它应该在的位置，重复n 次，
 * 就完成了 n 个数据的排序工作。
 * @author liushun
 * @since JDK 1.8
 **/
public class BubbleSort {
    public static void sort(int[] arr) {
        int length = arr.length;

        // 如果只有一个元素就不用排序了
        if(length <= 1) {
            return;
        }

        for(int i = 0; i < length; i++) {
            // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
            boolean flag = false;

            // 此处你可能会疑问的j<length-i-1，因为冒泡是把每轮循环中较大的数飘到最后面，
            // 数组下标又是从0开始的，i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
            for(int j = 0; j < length - i - 1; j++) {
                // 即这两个相邻的数是逆序的，交换
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }

            // 第一次循环没有数据交换，数组已经有序，退出排序
            if(!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 7, 6, 8, 5, 9};
        BubbleSort.sort(arr);
        for(int i = 0, len = arr.length; i < len; i++) {
            System.out.println(arr[i]);
        }
    }
}
