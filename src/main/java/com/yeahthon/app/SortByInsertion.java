package com.yeahthon.app;

import java.util.Arrays;

/**
 * 插入排序
 */
public class SortByInsertion {
    public static void main(String[] args) {
        int[] array = {9, 2, 8, 4, 7};
        sortByInsertion(array);
    }

    public static void sortByInsertion(int[] array) {
        System.out.println("初始的数组为：" + Arrays.toString(array));
        // 从第二个元素开始
        for (int i = 1; i < array.length; i++) {
            // 将待插入的元素先临时保存
            int current = array[i];
            // 已经排序区间的尾指针
            int j = i - 1;

            // 在已经排序区间种从后向前比较
            // j>=0保证不越界
            // 如果从大到小排序：array[j] < current
            while (j >= 0 && array[j] > current) {
                // 元素后移
                array[j + 1] = array[j];
                // j从后向前移动
                j--;
            }

            // 插入到正确的位置
            array[j + 1] = current;
        }
        System.out.println("排序后的数组为：" + Arrays.toString(array));
    }
}
