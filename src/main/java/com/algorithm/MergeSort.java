package com.algorithm;

/**
 * 归并排序 算法稳定 时间复杂度都是nlog2n
 * 指的是将两个顺序序列合并成一个顺序序列的方法。
 * <p>
 * 如　设有数列{6，202，100，301，38，8，1}
 * 初始状态：6,202,100,301,38,8,1
 * 第一次归并后：{6,202},{100,301},{8,38},{1}，比较次数：3；
 * 第二次归并后：{6,100,202,301}，{1,8,38}，比较次数：4；
 * 第三次归并后：{1,6,8,38,100,202,301},比较次数：4；
 * 总的比较次数为：3+4+4=11；
 * <p>
 * 逆序数为14
 * 为什么使用low + (high - low) / 2而不使用(high + low) / 2呢？目的是防止溢出！
 * (high + low 的运算结果可能超出当前类型所表示的范围的。)
 * 为什么这样就防止溢出了呢？看下面的例子。
 * high = 0100 0000 0000 0000 0000 0000 0000 0000 = 1073741824
 * low = 0100 0000 0000 0000 0000 0000 0000 0000 = 1073741824
 * <p>
 * high + low =  1000 0000 0000 0000 0000 0000 0000 0000
 * =  2147483648 as unsigned 32-bit integer
 * = -2147483648 as signed   32-bit integer
 * (high + low) / 2   = 1100 0000 0000 0000 0000 0000 0000 0000 = -1073741824
 * (high + low) >>> 1 = 0100 0000 0000 0000 0000 0000 0000 0000 =  1073741824
 * low + (high - low) / 2 = 0100 0000 0000 0000 0000 0000 0000 0000 =  1073741824
 * @author liushun
 * @since JDK 1.8
 */
public class MergeSort {
    /**
     * Sort.
     * @param arr 数组
     */
    public static void sort(int[] arr) {
        int length = arr.length;

        // 如果只有一个元素就不用排序了
        if(length <= 1) {
            return;
        }

        sort(arr, 0, length - 1);
    }

    // region 私有方法

    /**
     * 排序
     * @param arr
     * @param low
     * @param high
     */
    private static void sort(int[] arr, int low, int high) {
        // 当切割的数组只有一个元素时返回
        if(low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;

        // 递归分割左边数组
        sort(arr, low, mid);

        // 递归分割右边数组
        sort(arr, mid + 1, high);

        // 合并两个数组
        merge(arr, low, mid, high);
    }

    /**
     * 合并
     * @param arr
     * @param low
     * @param mid
     * @param high
     */
    private static void merge(int[] arr, int low, int mid, int high) {
        int length = arr.length;

        //辅助数组
        int[] tmp = new int[length];

        // p1、p2是检测指针，k是存放指针
        int p1 = low, p2 = mid + 1, k = high;


    }

    // endregion
}
