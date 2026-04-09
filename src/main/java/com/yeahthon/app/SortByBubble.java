package com.yeahthon.app;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class SortByBubble {
    public static void main(String[] args) {
        int[] array = {3, -1, 9, -3, 7, -4};

        // 临时变量用于数据交换
        int temp;
        // 标志位：用于表值某次扫描时存在数据交换
        boolean flag = false;
        // 外层循环：控制扫描的次数，扫描Size-1次即可
        for (int i = 0; i < array.length - 1; i++) {
            // 内层循环：控制每次扫描，每次扫描排序都将一个元素冒到最后
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    flag = true;
                }
            }

            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }

        System.out.println(Arrays.toString(array));
    }
}
