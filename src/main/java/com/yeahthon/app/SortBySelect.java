package com.yeahthon.app;

import java.util.Arrays;

/**
 * func：选择排序
 */
public class SortBySelect {
    public static void main(String[] args) {
        int[] array = {9, 2, 1, 8, 3};
        selectSort(array);
    }

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // 最小数字的索引
            int minIndex = i;
            // 最小值
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }

            // 将最小值放在数组首位
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
        }

        System.out.println(Arrays.toString(array));
    }
}
