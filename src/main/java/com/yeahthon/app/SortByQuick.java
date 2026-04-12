package com.yeahthon.app;

import java.util.Arrays;

/**
 * func：快速排序法
 */
public class SortByQuick {
    public static void main(String[] args) {
        int[] arr = {6, 3, 8, 2, 7, 1, 5};
        sortQuick(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sortQuick(int[] array, int left, int right) {
        // 递归终止条件
        if (left >= right) {
            return;
        }

        // 分区，返回基准位置
        int pivotIndex = partition(array, left, right);

        // 递归排序左右子数组
        sortQuick(array, left, pivotIndex - 1);
        sortQuick(array, pivotIndex + 1, right);
    }

    // 分区
    private static int partition(int[] array, int left, int right) {
        // 选择基准值，这里选第一个
        int pivot = array[left];

        int i = left;
        int j = right;

        while (i < j) {
            // 从右往左找比pivot小的
            while (i < j && array[j] >= pivot) {
                j--;
            }

            // 从左往右找比pivot大的
            while (i < j && array[i] <= pivot) {
                i++;
            }

            // 交换
            if (i < j) {
                swap(array, i, j);
            }
        }

        //将pivot放到正确位置
        swap(array, left, i);

        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
