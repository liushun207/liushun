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

        // // 将arr[low...mid]和A[mid+1...high]合并为A[low...high]
        merge(arr, low, mid, high);
    }

    /**
     * 合并 将arr[low...mid]和A[mid+1...high]合并为A[low...high]
     * @param arr 合并好的有序数组，需要放到这个位置上
     * @param low
     * @param mid
     * @param high
     */
    private static void merge(int[] arr, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = 0;
        int length = high - low + 1;

        // 临时数组
        int[] tmp = new int[length];

        // 最少把一个数组中的数据取完。
        while(i <= mid && j <= high) {
            // 对比两个数组，小的放左边 大的放右边
            if(arr[i] <= arr[j]) {
                // 小于等于，则放入 arr[i] 元素，继续对比 i++ 和 arr[j]
                tmp[k++] = arr[i++];
            } else {
                // 大于，则放入 arr[j] 元素，继续对比 j++ 和 arr[i]
                tmp[k++] = arr[j++];
            }
        }

        // 判断哪个子数组中有剩余的数据。
        int start = i;
        int end = mid;
        if(j <= high) {
            start = j;
            end = high;
        }

        // 将剩余的数据copy到临时数组 tmp。
        while(start <= end) {
            tmp[k++] = arr[start++];
        }

        //将 tmp 中的数据拷贝回 a 中
        System.arraycopy(tmp, 0, arr, low, length);
    }

    // endregion

    public static void main(String[] args) {
        int[] arr = {78, 33, 20, 43, 22, 71, 40, 93, 20, 50};

        MergeSort.sort(arr);

        for(int i = 0, len = arr.length; i < len; i++) {
            System.out.println(arr[i]);
        }
    }
}
