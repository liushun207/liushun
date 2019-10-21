package com.algorithm;

/**
 * 快速排序 不稳定 最差的情况下时间复杂度为：O( n^2 )平均时间复杂度也是：O(nlogn)
 * 假设我们现在对“6 1 2 7 9 3 4 5 10 8”这个10个数进行排序。
 * 首先在这个序列中随便找一个数作为基准数（不要被这个名词吓到了，就是一个用来参照的数，待会你就知道它用来做啥的了）。
 * 为了方便，就让第一个数6作为基准数吧。
 * 接下来，需要将这个序列中所有比基准数大的数放在6的右边，比基准数小的数放在6的左边，类似下面这种排列：
 * 3 1 2 5 4 6 9 7 10 8
 * @author liushun
 * @since JDK 1.8
 **/
public class QuickSort {
    /**
     * 排序
     * @param arr 数组
     * @param low 低位
     * @param high 高位
     */
    public static void sort(int[] arr, int low, int high) {
        int length = arr.length;

        // 如果只有一个元素就不用排序了
        if(length <= 1) {
            return;
        }

        // 校验数字是否正常
        if(length < high || high < low) {
            return;
        }

        // 左边，右边，基准位
        int left = low, right = high, temp = arr[low], t;

        while(left < right) {
            // 先看右边，依次往左递减，直到找到小于基准数的值
            while(arr[right] >= temp && left < right) {
                right--;
            }

            // 再看左边，依次往右递增，直到找到大于基准数的值
            while(arr[left] <= temp && left < right) {
                left++;
            }

            // 如果满足条件则交换
            if(left < right) {
                t = arr[right];
                arr[right] = arr[left];
                arr[left] = t;
            }
        }

        // 最后将基准位与left和right相等位置的数字交换
        arr[low] = arr[left];
        arr[left] = temp;

        //递归调用左半数组
        sort(arr, low, right - 1);

        //递归调用右半数组
        sort(arr, right + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        QuickSort.sort(arr, 0, arr.length - 1);
        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
