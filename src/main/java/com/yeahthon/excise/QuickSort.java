package com.yeahthon.excise;

import java.util.Arrays;

// 快速排序
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {6, 2, 8, 7, 1, 9, 3,4};
        recurse(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 递归分区
     * @param array 待递归的数组
     * @param left 数组的左起始边界索引
     * @param right 数组的右结束边界索引
     */
    public static void recurse(int[] array, int left, int right) {
        // 1、递归的结束条件
        // 递归终止条件
        if (left >= right) {
            return;
        }

        // 2、递归的处理逻辑
        // 每递归一次，对数组进行一次分区
        int pivotIndex = partition(array, left, right);

        // 3、递归的逻辑
        // 开始递归
        recurse(array, left, pivotIndex - 1);
        recurse(array, pivotIndex + 1, right);
    }

    /**
     * 1、按照左右边界索引，对数组进行分区
     * 2、小于基准值的数据位于左侧，大于基准值的数据位于右侧
     * @param array 待分区数组
     * @param left 数组的起始左边界索引
     * @param right 数组的结束右边界索引
     * @return 返回基准值的索引位置
     */
    private static int partition(int[] array, int left, int right) {
        // 选择第一个元素为基准值
        int pivot = array[left];

        int i = left;
        int j = right;

        while (i < j) {
            // 从右向左找比基准值小的元素
            while (i < j && array[j] >= pivot) {
                j--;
            }
            // 从左向右找比基准值大的元素
            while (i < j && array[i] <= pivot) {
                i++;
            }
            // 交换两个元素，即将元素放入到对应的分区
            if (i < j) {
                swap(array, i, j);
            }
        }

        // 此时i对应的位置即为此轮基准值的最终位置
        swap(array, left, i);
        return i;
    }

    // 根据索引交换两个元素位置
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
