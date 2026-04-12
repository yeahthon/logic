package com.yeahthon.app;

import java.util.Arrays;

/**
 * func：基数排序
 */
public class SortByRadix {
    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] array) {
        // 获取数组中最大数的位数
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int maxLength = (max + "").length();

        // 定义一个二维数组，表示10个桶，每个桶都是一个一维数组
        int[][] bucket = new int[10][array.length];
        // 定义一个一维数组，表示每个桶中的元素个数
        // 例如 bucketElementCounts[0] 表示第0个桶中存放元素的个数
        int[] bucketElementCounts = new int[10];

        // 循环处理
        // 第一轮处理个位、第二轮处理十位、第三轮处理百位...
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < array.length; j++) {
                // 取出每个元素对应位的值（个、十、百...）
                int digitOfElement = array[j] / n % 10;
                // 放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
                bucketElementCounts[digitOfElement]++;
            }
            // 按照桶的顺序取出数据放入原来的数组
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 如果桶中有数据才放入到原来的数组
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        array[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
    }
}
