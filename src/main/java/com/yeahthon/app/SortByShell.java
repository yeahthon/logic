package com.yeahthon.app;

import java.util.Arrays;

/**
 * func：希尔排序
 */
public class SortByShell {
    public static void main(String[] args) {
        int[] array = {1, 9, 2, 7, 4, 3, 8};
//        shellByMove(array);
        shellBySwap(array);
    }

    // 希尔排序移位式
    public static void shellByMove(int[] array) {
        System.out.println("初始的数组为：" + Arrays.toString(array));
        int n = array.length;

        // gap初始为n/2，不断减小
        // 从后往前进行比较
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 对每一组做“间隔为gap的插入排序”
            for (int i = gap; i < n; i++) {
                int current = array[i];
                int j = i;

                // 类似插入排序，但步长时gap
                // 如果从大到小排序：array[j - gap] < current
                while (j >= gap && array[j - gap] > current) {
                    array[j] = array[j - gap];
                    j -= gap;
                }

                array[j] = current;
            }
        }

        System.out.println("排序后的数组为：" + Arrays.toString(array));
    }

    // 希尔排序交换式
    public static void shellBySwap(int[] array) {
        System.out.println("初始的数组为：" + Arrays.toString(array));
        // 数组的长度
        int n = array.length;
        // 临时变量用于交换
        int temp = 0;

        // gap初始为n/2，不断减小
        // 从后往前进行比较
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 对每一组做“间隔为gap的插入排序”
            for (int i = gap; i < n; i++) {
                // 从后往前遍历分组种的所有元素，步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长的那个元素，说明需要交换
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }

        System.out.println("排序后的数组为：" + Arrays.toString(array));
    }
}
